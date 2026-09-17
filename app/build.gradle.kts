import java.io.File
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

/*
 * ---------------------------------------------------------------------------
 * Release signing
 * ---------------------------------------------------------------------------
 * An APK that is NOT signed cannot be installed on any Android phone:
 * the package installer rejects it with "App not installed" / "failed install"
 * and security scanners (Play Protect / Samsung / MIUI) flag it as unsafe.
 *
 * The signing values are read from (first match wins):
 *   1. keystore.properties at the project root  (local builds, never committed)
 *   2. Gradle properties: -PstoreFile=... -PstorePassword=... -PkeyAlias=...
 *   3. Environment variables: SENSITIVE_PRO_STORE_FILE, SENSITIVE_PRO_STORE_PASSWORD,
 *      SENSITIVE_PRO_KEY_ALIAS, SENSITIVE_PRO_KEY_PASSWORD, SENSITIVE_PRO_STORE_TYPE
 *
 * If none of them are provided the release variant falls back to being unsigned
 * (same as before) and prints a warning at configuration time.
 * ---------------------------------------------------------------------------
 */
val keystorePropertiesFile: File = rootProject.file("keystore.properties")
val keystoreProperties = Properties().apply {
    if (keystorePropertiesFile.exists()) {
        keystorePropertiesFile.inputStream().use { load(it) }
    }
}

fun signingValue(propertyKey: String, envKey: String): String? =
    (keystoreProperties.getProperty(propertyKey)
        ?: providers.gradleProperty(propertyKey).orNull
        ?: System.getenv(envKey))
        ?.trim()
        ?.takeIf { it.isNotEmpty() }

val releaseStoreFile: File? = signingValue("storeFile", "SENSITIVE_PRO_STORE_FILE")
    ?.let { path ->
        val file = File(path)
        if (file.isAbsolute) file else rootProject.file(path)
    }
    ?.takeIf { it.isFile }

val releaseStorePassword: String? = signingValue("storePassword", "SENSITIVE_PRO_STORE_PASSWORD")
val releaseKeyAlias: String? = signingValue("keyAlias", "SENSITIVE_PRO_KEY_ALIAS")
val releaseKeyPassword: String? = signingValue("keyPassword", "SENSITIVE_PRO_KEY_PASSWORD")
val releaseStoreType: String = signingValue("storeType", "SENSITIVE_PRO_STORE_TYPE") ?: "PKCS12"

val canSignRelease: Boolean =
    releaseStoreFile != null &&
        !releaseStorePassword.isNullOrBlank() &&
        !releaseKeyAlias.isNullOrBlank()

if (canSignRelease) {
    logger.lifecycle("✅ Release signing ENABLED (keystore: ${releaseStoreFile?.name}, alias: $releaseKeyAlias)")
} else {
    logger.warn(
        "⚠️  No release keystore found - the release APK will be UNSIGNED and CANNOT be installed.\n" +
            "    Create keystore.properties (see keystore.properties.example) or set the " +
            "SENSITIVE_PRO_* environment variables / RELEASE_KEYSTORE_BASE64 GitHub secret."
    )
}

android {
    namespace = "com.sensitivepro.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sensitivepro.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            if (canSignRelease) {
                storeFile = releaseStoreFile
                storePassword = releaseStorePassword
                keyAlias = releaseKeyAlias
                keyPassword = releaseKeyPassword ?: releaseStorePassword
                storeType = releaseStoreType
                // APK Signature Scheme v2 landed in Android 7.0 (API 24), which is
                // our minSdk, so v2+v3 already cover every supported device.
                // v1 (JAR signing) is only needed if minSdk ever drops below 24;
                // it stays enabled for compatibility. Confirmed by
                // `apksigner verify` in CI: v2 = true, v3 = true.
                enableV1Signing = true
                enableV2Signing = true
                enableV3Signing = true
                enableV4Signing = false
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            isJniDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = if (canSignRelease) signingConfigs.getByName("release") else null
        }
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    androidResources {
        generateLocaleConfig = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("com.google.code.gson:gson:2.10.1")
    // ViewPager2 for categories
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
