buildscript {
    extra.apply {
        set("queryDslVersion", "5.0.0")
    }

   // var querydslversion =rootProject.extra.get("queryDslVersion") as String
}

plugins {
    java
    id("org.springframework.boot") version "2.7.15"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id ("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
}

group = "org.zerock"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }

}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.projectlombok:lombok:1.18.28")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.1.0")

    implementation("com.querydsl:querydsl-jpa:${rootProject.extra["queryDslVersion"]}")

    annotationProcessor(
            "jakarta.persistence:jakarta.persistence-api",
            "jakarta.annotation:jakarta.annotation-api",
            "com.querydsl:querydsl-apt:${rootProject.extra["queryDslVersion"]}"
    )

}

tasks.withType<Test> {
    useJUnitPlatform()
}


sourceSets {
    main {
        java.srcDirs("$projectDir/src/main/java", "$projectDir/build/generated")
    }
}