plugins { base }
subprojects {
    apply(plugin = "java-library")
    repositories { mavenCentral() }
    plugins.withId("java-library") {
        extensions.configure<org.gradle.api.plugins.JavaPluginExtension> {
            toolchain.languageVersion.set(JavaLanguageVersion.of(25))
        }
        dependencies {
            "testImplementation"(platform("org.junit:junit-bom:5.13.4"))
            "testImplementation"("org.junit.jupiter:junit-jupiter")
            "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
        }
        tasks.withType<Test>().configureEach {
            useJUnitPlatform()
            testLogging { events("failed", "skipped") }
        }
    }
}
