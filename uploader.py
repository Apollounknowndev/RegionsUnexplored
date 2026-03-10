import os
import requests
import json

# Per-mod: Update this for each mod!!!

MOD_ID = "regions-unexplored"
MOD_VERSION = "0.6+beta1"
CHANGELOG = """
## Regions Unexplored 0.6: The Next Chapter
This is the first beta for RU 0.6, the next major update for the mod.
0.6 is the first major update pioneered by me (Apollo) as I've taken over from the original dev UHQ.

**Biomes**
- Made significant changes / improvements to the following biomes:
  - Ashen Woodlands (less clutter, more variation, better water color)
  - Autumnal Maple Forest (better leaf litter placement)
  - Bamboo Forest (varying tree density, can get pretty dark in some places)
  - Chalk Cliffs (no chalk grass blocks on steep cliffs, less dirt/stone breaking through)
  - Highland Fields (less flower/rock noise)
  - Joshua Desert (better surface blocks, less cluttered grass)
  - Outback (better surface blocks)
  - Pumpkin Fields (less pumpkin clutter)
  - Redwoods (varying tree density, more foliage, more tree height variation)
  - Scorched Caves (less basalt leaking into structures like Ancient Cities and Trial Chambers)
- Rewrote the biome placement system, replacing the Terrablender dependency with Blueprint.
  - Blueprint is **temporary** and will be replaced with Lithostitched in the full 0.6 release!
- Added Lithostitched as a dependency for some extra worldgen features, and eventually will be the only non-Fabric API library RU uses.
- Trial Chambers now spawn in RU biomes.
- Shipwrecks now have a chance of generating with RU wood blocks.

**Blocks & Items**
- Most RU blocks and items can now be found in vanilla item tabs in addition to the RU-dedicated tab.
- Merged Medium Grass and Stone Bud into a single block, Grass Sprouts.
- Renamed several blocks in preparation for support of future versions.
  - Cactus Flower -> Saguaro Cactus Flower
  - [Prefix] Leaf Pile -> [Prefix] Leaf Litter
- Fixed Peat Grass Block and Silt Grass Block breaking particle textures being tinted weirdly.

**Particles & Sounds**
- Added leaf particles to most custom leaves. Some such as Joshua and Brimwood are excluded.
  - By default leaves use the new vanilla leaf physics where they swirl around in place rather than float away.
  - Blackwood, Pine and Larch leaves use a needle particle that falls a bit more directly.
- Added custom improved textures for the Magnolia leaf particles.
- Removed the individual leaf particle config options in favor of a singular Leaves Particles option.
- Added ground particles to Viridescent Nylium, similar to the particles on Mycelium.
- Added falling particles to Glowing Bioshroom Blocks, similar to the falling particles on Spore Blossoms.
- Added floating particles to Bioshrooms and their Tall variants, similar to the floating particles on Spore Blossoms (but a lot more tame.)
- Sparkle particles spawn around prismarite blocks, and are tinted based on the same color system as the blocks.
- Reduced/removed generic ambient particles in most biomes, as other particles now do their job.
- Redwood and Baobab wood sets now have unique wood sound sets.
- Windswept Grass now has a custom sound set.

**Other**
- Overhauled the config system. The files are now in new locations, and the custom region configs were removed due to being obsolete.
  - This removes Forge Config API Port as a dependency.
- Fixed errors related to the Prismaglass and Alpha Planks recipes.
"""
UPLOAD_VERSIONS = [
    ("fabric", "1.21.1"),
    ("neoforge", "1.21.1"),
]

MODRINTH_ID = "Tkikq67H"
CURSEFORGE_ID = "659110"

RELEASE_TYPE = "release"

# Global: Should never need to be touched!

BASE_FOLDER = os.path.dirname(os.path.abspath(__file__))


MODRINTH_TOKEN = os.getenv('TOKEN_MR')
if not MODRINTH_TOKEN:
    raise EnvironmentError("MODRINTH_TOKEN is unset!")

CURSEFORGE_TOKEN = os.getenv('TOKEN_CF')
if not CURSEFORGE_TOKEN:
    raise EnvironmentError("CURSEFORGE_TOKEN is unset!")
CURSEFORGE_URL = f"https://minecraft.curseforge.com/api/v1/projects/{CURSEFORGE_ID}/upload-file"
CURSEFORGE_GAME_VERSIONS = {
    "1.20.1": [9990],
    "1.21.1": [11779],
    "1.21.11": [14406],
}
CURSEFORGE_LOADERS = {
    "fabric": 7499,
    "forge": 7498,
    "neoforge": 10150,
}


# Code

def upload_modrinth(loader: str, version: str, file_path: str):
    metadata = {
        "name": f"v{MOD_VERSION} ~ {loader.title()} {version}",
        "version_number": MOD_VERSION,
        "project_id": MODRINTH_ID,
        "game_versions": [version],
        "loaders": [loader],
        "featured": True,
        "changelog": CHANGELOG,
        "version_type": RELEASE_TYPE,
        "file_parts": ["file"],
        "dependencies": []
    }

    with open(file_path, 'rb') as mod_file:
        response = requests.post(
            "https://api.modrinth.com/v2/version",
            headers={
                "Authorization": MODRINTH_TOKEN
            },
            files={
                'file': (os.path.basename(file_path), mod_file, 'application/java-archive'),
            },
            data={
                'data': json.dumps(metadata)
            }
        )

        name = f"MR {loader.title()} {version}: "

        if response.status_code == 200:
            print(name + "Success")
            print(response.json())
        else:
            print(name + f"Failed ({response.status_code})")
            print(response.text)


def upload_curseforge(loader: str, version: str, file_path: str):
    headers = {
        "X-Api-Token": CURSEFORGE_TOKEN
    }

    # Lookup version and loader IDs
    game_version_ids = CURSEFORGE_GAME_VERSIONS.get(version)
    modloader_id = CURSEFORGE_LOADERS.get(loader)

    if not game_version_ids or not modloader_id:
        print(f"Skipping CurseForge upload for {loader} {version}: unknown IDs")
        return

    # Metadata
    metadata = {
        "displayName": f"v{MOD_VERSION} ~ {loader.title()} {version}",
        "gameVersions": game_version_ids + [modloader_id],
        "releaseType": RELEASE_TYPE,
        "changelog": CHANGELOG,
        "changelogType": "markdown"
    }
    metastr = json.dumps(metadata)

    with open(file_path, "rb") as mod_file:
        files = {
            "file": (os.path.basename(file_path), mod_file, "application/java-archive")
        }

        response = requests.post(
            f"https://minecraft.curseforge.com/api/projects/{CURSEFORGE_ID}/upload-file",
            headers=headers,
            files=files,
            data={"metadata": metastr},
            auth=("Apollo", CURSEFORGE_TOKEN)
        )

        name = f"CF {loader.title()} {version}: "

        if response.status_code == 200:
            print(name + "Success")
            print(response.json())
        else:
            print(name + f"Failed ({response.status_code})")
            print(response.text)


for modloader, game_version in UPLOAD_VERSIONS:
    mod_path = os.path.join(
        BASE_FOLDER,
        modloader,
        'build',
        'libs',
        f'regions_unexplored-{MOD_VERSION}-{modloader}-21.1.jar'
    )

    if not os.path.exists(mod_path):
        print(f"File not found, skipping: {mod_path}")
        continue

    upload_modrinth(modloader, game_version, mod_path)
    upload_curseforge(modloader, game_version, mod_path)
input("Press any button to close.")