import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import java.util.Properties

plugins {
    id("com.android.application")
    id("com.vanniktech.android.junit.jacoco")
    id("io.fabric")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("realm-android")
}

android {
    compileSdkVersion(Versions.compileSdk)

    defaultConfig {
        applicationId = "com.mgaetan89.showsrage"
        versionCode = 38
        versionName = "1.7"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        resConfigs("en", "fr")
    }

    signingConfigs {
        create("release") {
            keyAlias = SigningConfigs.Release.keyAlias
            keyPassword = SigningConfigs.Release.keyPassword
            storeFile = SigningConfigs.Release.storeFile
            storePassword = SigningConfigs.Release.storePassword
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".dev"
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName(name)
        }
    }

    sourceSets {
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    lintOptions {
        isAbortOnError = false
    }

    testOptions {
        animationsDisabled = true

        unitTests.apply {
            isReturnDefaultValues = true
        }
    }
}

androidExtensions {
    // Workaround for https://github.com/gradle/kotlin-dsl/issues/644
    configure(delegateClosureOf<AndroidExtensionsExtension> {
        isExperimental = true
    })
}

junitJacoco {
    jacocoVersion = Versions.jacoco
    isIncludeNoLocationClasses = true
}

realm {
    setKotlinExtensionsEnabled(false)
}

tasks.withType<Test> {
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}

dependencies {
    implementation(Dependencies.appCompat)
    implementation(Dependencies.cardView)
    implementation(Dependencies.crashlytics)
    implementation(Dependencies.customTabs)
    implementation(Dependencies.design)
    implementation(Dependencies.fastscroll)
    implementation(Dependencies.firebaseAnalytics)
    implementation(Dependencies.firebaseCore)
    implementation(Dependencies.firebaseJobDispatcher)
    implementation(Dependencies.glide)
    implementation(Dependencies.glideOkHttp)
    implementation(Dependencies.kotlin)
    implementation(Dependencies.mediaRouter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpUrlConnection)
    implementation(Dependencies.palette)
    implementation(Dependencies.playServicesCast)
    implementation(Dependencies.preferences)
    implementation(Dependencies.realmAdapters)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.supportAnnotations)
    implementation(Dependencies.supportV4)
    implementation(Dependencies.supportVectorDrawable)

    testImplementation(Dependencies.assertJAndroid)
    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.mockito)

    androidTestImplementation(Dependencies.supportAnnotations)
    androidTestImplementation(Dependencies.supportTestLibraryRules)
    androidTestImplementation(Dependencies.supportTestLibraryRunner)
    androidTestImplementation(Dependencies.assertJAndroid)
    androidTestImplementation(Dependencies.jUnit)
}

apply(mapOf("plugin" to "com.google.gms.google-services"))
