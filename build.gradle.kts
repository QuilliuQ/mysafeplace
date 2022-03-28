val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version:String by project
val koin_version:String by project


plugins {

    application
    kotlin("jvm") version "1.6.10"
}

group = "ru.sylas"
version = "0.0.1"
application {
    mainClass.set("ru.sylas.ApplicationKt")
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation ("com.github.papsign:Ktor-OpenAPI-Generator:-SNAPSHOT")
    implementation ("com.github.RedMadRobot:input-mask-android:6.1.0")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-jackson:$ktor_version")
    implementation("io.ktor:ktor-server-tomcat:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation ("io.ktor:ktor-auth:$ktor_version")
    implementation ("io.ktor:ktor-auth-jwt:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    //Exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.postgresql:postgresql:42.2.2")

    //koin
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation ("io.insert-koin:koin-logger-slf4j:$koin_version")
}