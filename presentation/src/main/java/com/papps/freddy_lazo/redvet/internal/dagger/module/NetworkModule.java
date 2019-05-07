package com.papps.freddy_lazo.redvet.internal.dagger.module;


import android.content.Context;
import android.util.Log;

import com.papps.freddy_lazo.data.network.RestApi;
import com.papps.freddy_lazo.data.network.RestApiImpl;
import com.papps.freddy_lazo.data.network.RestService;
import com.papps.freddy_lazo.redvet.BuildConfig;
import com.papps.freddy_lazo.redvet.util.Tls12SocketFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;

import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.papps.freddy_lazo.redvet.util.Tls12SocketFactory.defaultTrustManager;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Content-Type", "application/json");
                    builder.addHeader("Accept", "application/json");
                    return chain.proceed(builder.build());
                });
        if (!BuildConfig.BUILD_TYPE.equals("release")) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(logging);
        }

        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.KITKAT) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                httpClientBuilder.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()), Objects.requireNonNull(defaultTrustManager()));

                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List<ConnectionSpec> specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                httpClientBuilder.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }

        return httpClientBuilder;
    }

    @Provides
    @Singleton
    RestService provideRestService(OkHttpClient.Builder httpClientBuilder) {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://redvet.ctrl.pe/api/v1/")
                .baseUrl("https://admin.redvet.pe/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        return retrofit.create(RestService.class);
    }

    @Provides
    @Singleton
    RestApi provideRestApi(Context context, RestService restService) {
        return new RestApiImpl(context, restService);
    }

}
