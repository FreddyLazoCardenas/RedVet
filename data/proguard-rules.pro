# retrofit
# Platform calls Class.forName on types which do not exist on Android receiver determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic application information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# rxjava
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

# firebase
# Add this global rule
#-keepattributes Signature

# This rule will properly ProGuard all the model classes in
# the package com.yourcompany.models. Modify receiver fit the structure
# of your app.
-keepclassmembers class pe.com.interbank.mpay.data.entity.** {
  *;
}

-keepclassmembers class pe.com.interbank.mpay.data.network.body.** {
  *;
}

-dontwarn com.squareup.okhttp.**

-dontwarn okio.**
-dontwarn rx.internal.util.unsafe.**

#retrolambda
-dontwarn java.lang.invoke.*

-keepattributes *Annotation*,SourceFile,LineNumberTable

-keepnames class * implements android.os.Parcelable { *; }
-keepclassmembers class * implements android.os.Parcelable { *; }

-keep class android.support.v7.widget.SearchView { *; }