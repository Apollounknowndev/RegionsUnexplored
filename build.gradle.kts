plugins {
    kotlin("jvm") version "2.1.21"
    id("earth.terrarium.cloche") version "0.19.11"
}

repositories {
    cloche.librariesMinecraft()
    mavenCentral()
    cloche {
        main()
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenFabric()
    }
    maven("https://api.modrinth.com/maven")
    maven("https://maven.terraformersmc.com/")
    maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
}

group = "net.regions_unexplored"
version = "0.6.1"

// Required dependencies
val lithostitchedVersion = "1.7.13"

// Optional dependencies
val wikifulVersion = "0.3.2"

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
            modCompileOnlyApi("maven.modrinth:lithostitched:$lithostitchedVersion-neoforge-26.1")
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

    fabric {
        mixins.from(file("src/fabric/main/regions_unexplored.fabric.mixins.json"))

        loaderVersion = "0.19.2"
        minecraftVersion = "26.1.2"

        dependencies {
            fabricApi("0.155.0")

            include("de.marhali:json5-java:3.0.0")
            include("com.electronwill.night-config:core:3.8.3")
            include("com.electronwill.night-config:toml:3.8.3")

            modRuntimeOnly("maven.modrinth:world-preview-prime:2.0.0-fabric-26.1")
            modImplementation("maven.modrinth:lithostitched:$lithostitchedVersion-fabric-26.1")
            modImplementation("maven.modrinth:wikiful:$wikifulVersion-fabric-26.1")

            modImplementation("com.terraformersmc:modmenu:18.0.0")
        }

        data()
        datagenClientDirectory = file("src/common/main/generated")

        includedClient()
        runs {
            client()
            server()
            clientData()
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

    neoforge {
        mixins.from(file("src/neoforge/main/regions_unexplored.neoforge.mixins.json"))
        loaderVersion = "26.1.2.81"
        minecraftVersion = "26.1.2"

        dependencies {
            legacyClasspath("de.marhali:json5-java:3.0.0")
            include("de.marhali:json5-java:3.0.0")
            modRuntimeOnly("maven.modrinth:world-preview-prime:2.0.0-neoforge-26.1")
            modApi("maven.modrinth:lithostitched:$lithostitchedVersion-neoforge-26.1")
            modApi("maven.modrinth:wikiful:$wikifulVersion-neoforge-26.1")
        }


        data {
            dependencies {

            }
        }

        datagenClientDirectory = file("src/common/main/generated")

        runs {
            client()
            server()
            clientData()
        }
    }
}

tasks.named("runFabricClientData") {
    enabled = false
}