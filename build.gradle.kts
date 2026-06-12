plugins {
    kotlin("jvm") version "2.1.21"
    id("earth.terrarium.cloche") version "0.18.14"
}

repositories {
    cloche {
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenForge()
        mavenFabric()
        mavenParchment()
        librariesMinecraft()
        main()
    }
    mavenLocal()
    mavenCentral()
    maven("https://api.modrinth.com/maven")
    maven("https://maven.terraformersmc.com/")
    maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
}

group = "net.regions_unexplored"
version = "0.6.1"
val lithostitchedVersion = "1.7.9"
val wikifulVersion = "0.3.1"

cloche {
    metadata {
        modId = "regions_unexplored"
        name = "Regions Unexplored"
        description = "A stack of new biomes spread across the Overworld and Nether!"
        license = "All Rights Reserved"
        icon = "pack.png"

        url = "https://modrinth.com/mod/regions-unexplored"
        issues = "https://github.com/Apollounknowndev/RegionsUnexplored/issues"
        sources = "https://github.com/Apollounknowndev/RegionsUnexplored"

        author("Apollo")
        author("UHQ_Games")
        contributor("KelloVerra (Texture Artist)")
        contributor("KirboSoftware")
        contributor("voidsongdragonfly")
    }

    common {
        mixins.from(file("src/common/main/regions_unexplored.mixins.json"))
        accessWideners.from(file("src/common/main/regions_unexplored.accesswidener"))

        dependencies {
            compileOnly("org.spongepowered:mixin:0.8.5")
            implementation("de.marhali:json5-java:3.0.0")
            implementation("com.electronwill.night-config:core:3.8.3")
            implementation("com.electronwill.night-config:toml:3.8.3")
            modCompileOnlyApi("maven.modrinth:lithostitched:1.6.5-neoforge-21.1")
        }

        data()

        metadata {
            dependencies {
                dependency {
                    modId = "lithostitched"
                    version(lithostitchedVersion)
                }
            }
        }
    }

    val sharedOld = common("shared:21.1") {
        //mixins.from(file("src/shared/21.1/main/regions_unexplored.21.1.mixins.json"))
    }

    /*val sharedNew = common("shared:26.1") {
        //mixins.from(file("src/shared/26.1/main/regions_unexplored.26.1.mixins.json"))
    }*/

    fabric("fabric:21.1") {
        dependsOn(sharedOld)
        mixins.from(file("src/fabric/21.1/main/regions_unexplored.fabric.mixins.json"))

        loaderVersion = "0.19.2"
        minecraftVersion = "1.21.1"

        mappings {
            official()
            custom(project.dependencies.create(files("mappings/1.21.1.tiny")))
        }

        dependencies {
            fabricApi("0.116.8")

            include("de.marhali:json5-java:3.0.0")
            include("com.electronwill.night-config:core:3.8.3")
            include("com.electronwill.night-config:toml:3.8.3")

            modRuntimeOnly("maven.modrinth:world-preview:qc0AtV3T")
            modImplementation("maven.modrinth:lithostitched:$lithostitchedVersion-fabric-21.1")
            //modImplementation("maven.modrinth:wikiful:$wikifulVersion-fabric-1.21.1")

            modImplementation("com.terraformersmc:modmenu:11.0.3")
        }

        data()
        datagenDirectory = file("src/shared/21.1/main/generated")

        includedClient()
        runs {
            client()
            server()
            data()
        }

        metadata {
            entrypoint("main") {
                value = "net.regions_unexplored.RegionsUnexploredFabric"
            }
            entrypoint("client") {
                value = "net.regions_unexplored.client.RegionsUnexploredFabricClient"
            }
            entrypoint("modmenu") {
                value = "net.regions_unexplored.compat.ModMenuIntegration"
            }
        }
    }

    /*fabric("fabric:26.1") {
        dependsOn(sharedNew)
        mixins.from(file("src/fabric/26.1/main/regions_unexplored.fabric.mixins.json"))

        loaderVersion = "0.19.2"
        minecraftVersion = "26.1.2"

        dependencies {
            fabricApi("0.150.0")

            include("de.marhali:json5-java:3.0.0")
            include("com.electronwill.night-config:core:3.8.3")
            include("com.electronwill.night-config:toml:3.8.3")

            modImplementation("maven.modrinth:lithostitched:$lithostitchedVersion-fabric-26.1")
            //modImplementation("maven.modrinth:wikiful:$wikifulVersion-fabric-1.21.1")

            modImplementation("com.terraformersmc:modmenu:18.0.0-beta.1")
        }

        data()
        datagenDirectory = file("src/shared/26.1/main/generated")

        includedClient()
        runs {
            client()
            server()
            data()
        }

        metadata {
            entrypoint("main") {
                value = "net.regions_unexplored.RegionsUnexploredFabric"
            }
            entrypoint("client") {
                value = "net.regions_unexplored.client.RegionsUnexploredFabricClient"
            }
            entrypoint("modmenu") {
                value = "net.regions_unexplored.compat.ModMenuIntegration"
            }
        }
    }*/

    neoforge("neoforge:21.1") {
        dependsOn(sharedOld)

        mixins.from(file("src/neoforge/21.1/main/regions_unexplored.neoforge.mixins.json"))
        loaderVersion = "21.1.218"
        minecraftVersion = "1.21.1"

        mappings {
            official()
            custom(project.dependencies.create(files("mappings/1.21.1.tiny")))
        }

        dependencies {
            legacyClasspath("de.marhali:json5-java:3.0.0")
            include("de.marhali:json5-java:3.0.0")
            modImplementation("maven.modrinth:lithostitched:$lithostitchedVersion-neoforge-21.1")
        }

        data {
            legacyClasspath("de.marhali:json5-java:3.0.0")
        }

        datagenDirectory = file("src/shared/21.1/main/generated")

        runs {
            client()
            server()
            data()
        }
    }
}

tasks.named("runFabric211Data") {
    enabled = false
}

tasks.named("runNeoforge211Data") {
    enabled = false
}