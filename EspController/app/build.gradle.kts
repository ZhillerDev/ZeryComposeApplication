plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "io.zhiller.esp"
  compileSdk = 34

  defaultConfig {
    applicationId = "io.zhiller.esp"
    minSdk = 29
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  // 下面不关键的部分都使用了最新的Gradle版本管理机制，版本号都保存在libs.version.toml里面去了
  // 你当然也可以不管，直接用原版的版本管理机制

  /*retrofit 网络请求*/
  implementation(libs.retrofit)
  implementation(libs.converter.gson)
  implementation(libs.okhttp)
  implementation(libs.androidx.lifecycle.viewmodel.compose)

  // Lifecycle 生命周期库
  val lifecycleVersion = "2.7.0"
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")

  // Runtime 实时库
  implementation("androidx.work:work-runtime-ktx:2.9.0")

  // Navigation 页面导航库
  implementation("androidx.navigation:navigation-compose:2.7.6")

  // Icons 谷歌提供的ICONS
  implementation("androidx.compose.material:material-icons-extended:1.6.0")

  // preferences 本地存储
  implementation("androidx.datastore:datastore-preferences:1.0.0")
  implementation("com.google.code.gson:gson:2.10")

  // Hilt 依赖注入
  implementation("com.google.dagger:hilt-android:2.48.1")
  kapt("com.google.dagger:hilt-android-compiler:2.48.1")
  implementation("androidx.hilt:hilt-work:1.1.0")
  kapt("androidx.hilt:hilt-compiler:1.1.0")
  implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}