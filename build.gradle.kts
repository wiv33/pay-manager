plugins {
    java
//    `maven-publish`
    id("org.springframework.boot") version "2.3.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("com.palantir.docker") version "0.22.1"
}

//java.sourceCompatibility = JavaVersion.VERSION_13

extra["springCloudVersion"] = "Hoxton.SR5"

/*
configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
*/

repositories {
    mavenCentral()
}

/*
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${extra["springCloudVersion"]}")
    }
}
*/
allprojects {
    apply(plugin = "java")

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }
/*
    dependencies {
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }
*/
}