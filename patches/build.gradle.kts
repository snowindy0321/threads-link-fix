group = "app.morphe.patches.threads"
version = rootProject.version

patches {
    about {
        name = "Threads Link Fix"
        description = "Morphe patch for Meta Threads (com.instagram.barcelona) to fix cross-app Instagram integration with modded/Morphe Instagram."
        source = "https://github.com/snowindy0321/threads-link-fix"
        author = "snowindy0321"
        contact = "na"
        website = "na"
        license = "GPLv3"
    }
}

val patchListGeneratorClasspath = configurations.create("patchListGeneratorClasspath")

dependencies {
    compileOnly(libs.gson)
    patchListGeneratorClasspath(libs.gson)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build)

        classpath = sourceSets["main"].runtimeClasspath + patchListGeneratorClasspath
        mainClass.set("util.PatchListGeneratorKt")
    }

    publish {
        dependsOn("generatePatchesList")
    }
}
