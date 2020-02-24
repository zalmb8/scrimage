plugins {
   `java-library`
   kotlin("jvm")
}


dependencies {
   api(project(":scrimage-core"))
   implementation(Libs.TwelveMonkeys.bmp)
   implementation(Libs.TwelveMonkeys.tga)
   implementation(Libs.TwelveMonkeys.tiff)
   implementation(Libs.TwelveMonkeys.pcx)
   implementation(Libs.TwelveMonkeys.pnm)
   implementation(Libs.TwelveMonkeys.iff)
   implementation(Libs.TwelveMonkeys.sgi)
   testImplementation(Libs.Kotest.junit5)
}

java {
   sourceCompatibility = JavaVersion.VERSION_1_8
   targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.named<Test>("test") {
   useJUnitPlatform()
   filter {
      isFailOnNoMatchingTests = false
   }
   testLogging {
      showExceptions = true
      showStandardStreams = true
      events = setOf(
         org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
         org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
      )
      exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
   }
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
   jvmTarget = "1.8"
}

val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
   jvmTarget = "1.8"
}

apply("../publish.gradle.kts")
