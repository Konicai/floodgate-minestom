import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    `java-library`
    `maven-publish`
    id("net.kyori.indra.git") // used for getting branch/commit info
    id("com.github.johnrengelman.shadow")
    id("idea") // used to download sources and documentation
}

group = "me.konicai"
version = "1.0.0"

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
        // Disable creating of test report files
        reports.html.required.set(false)
        reports.junitXml.required.set(false)
    }

    processResources {
        val build = System.getenv("BUILD_NUMBER") ?: "??"
        val branch = System.getenv("GIT_BRANCH") ?: indraGit.branchName() ?: "local/dev"
        val commit = indraGit.commit()?.name?.substring(0, 7)
        val version = "b${build}-${branch}-${commit}"

        expand(
            "id" to "floodgate",
            "version" to version,
            "url" to "https://github.com/Konicai/floodgate-minestom",
            "author" to "konicai, HEROOSTECH"
        )
    }

    withType<Javadoc>().all { enabled = false }
}

tasks.named("build") {
    dependsOn(tasks.named<Test>("test"))
    dependsOn(tasks.named("shadowJar"))
}

dependencies {
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")

    annotationProcessor("org.projectlombok:lombok:1.18.22")

    api("org.geysermc.floodgate:api:2.2.1-20221228.122810-7")
    api("org.geysermc.floodgate:core:2.2.1-20221228.122811-7")
    api("com.github.Konicai", "cloud-minestom", "1.5.0-SNAPSHOT")

    // Provided by minestom dependency system
    compileOnly("io.netty", "netty-transport", "4.1.49.Final")
    compileOnly("io.netty", "netty-codec", "4.1.49.Final")

    // Provided by the server
    compileOnly("com.github.Minestom:Minestom:-SNAPSHOT")
    compileOnly("org.apache.logging.log4j", "log4j-core", "2.11.2")
    compileOnly("org.projectlombok:lombok:1.18.24")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2") // nullability annotations
}

tasks.withType<ShadowJar> {
    archiveFileName.set("floodgate-minestom.jar")

    dependencies {
        shadow {
            fun simpleRelocate(pattern: String, name: String) {
                relocate(pattern, "me.konicai.floodgate.shaded.$name")
            }

            simpleRelocate("org.bstats", "bstats")
        }

        // todo: exclusions and relocations. instead of relocations, maybe use Minestom's external dependency system
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}