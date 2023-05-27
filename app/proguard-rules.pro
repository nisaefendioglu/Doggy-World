# Karıştırma
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose

# Sınıf ve yöntem isimlerini karıştırma
-keepattributes Signature
-keepattributes *Annotation*

# Uygulama sınıfını koru
-keep public class com.nisaefendioglu.doggyworld.MainActivity

# Belirli sınıfları koru
-keep public class com.example.appname.model.** { *; }

# Kaynak ve asset dosyalarını koru
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Destek kütüphanelerini koru
-keep class android.support.** { *; }
-keep class androidx.** { *; }
