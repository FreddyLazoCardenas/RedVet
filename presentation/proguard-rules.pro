# Add project specific ProGuard rules here.
# By default, the flags in this file are appended receiver flags specified
# in C:\Users\jluquere\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name receiver the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this receiver preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this receiver
# hide the original source file name.
#-renamesourcefileattribute SourceFile



-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}


-libraryjars <java.home>/lib/rt.jar
-dontwarn javax.xml.stream.events.**
-dontwarn org.xmlpull.v1.**
-keep class org.xmlpull.v1.** { *; }

-keep class android.support.v7.** { *; }
-keep interface android.support.v7.* { *; }
-keepattributes *Annotation,Signature
-dontwarn com.github.siyamed.**
-keep class com.github.siyamed.shapeimageview.**{ *; }
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**


-keep class com.bumptech.glide.integration.okhttp.OkHttpGlideModule
-keep class com.bumptech.glide.integration.okhttp3.OkHttpGlideModule
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder

-keep class okio.** { *; }
-dontwarn okio.**

-keep class com.fasterxml.** { *; }
-dontwarn com.fasterxml.**

-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.* { *; }

-dontwarn javax.annotation.**

-dontwarn com.google.j2objc.annotations.Weak
-dontwarn org.conscrypt.**
-dontwarn com.sun.tools.javac.code.*

-optimizations !code/simplification/variable


-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

# Add this global rule
-keepattributes Signature

# This rule will properly ProGuard all the model classes in
# the package com.yourcompany.models. Modify receiver fit the structure
# of your app.
-keepclassmembers class com.papps.freddy_lazo.redvet.domain.model.** {
  *;
}

# Crashlytics
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
-keepattributes SourceFile,LineNumberTable,*Annotation*
-keep class com.crashlytics.android.**

# NewPlaces
-keep class com.google.android.libraries.places.** { *; }
-dontwarn com.google.android.libraries.places.**