import os
import requests
import json

# Per-mod: Update this for each mod!!!

MOD_ID = "regions-unexplored"
MOD_VERSION = "0.6"
CHANGELOG = """
# Regions Unexplored 0.6

This is the first major update for the mod developed by me, Apollo. This update focuses on improving many existing features and placing the groundwork for future content updates.

- Added 2 new biomes:
    - Tundra: The spiritual successor of the Frozen Tundra.
        - It no longer generates snow, but still generates crimson grass.
        - You'll find small patches of trees throughout the biome, sprinkled among the flowers, rocks, and shrubs.
    - Wisteria Grove: The spiritual successor of the Mauve Hills.
        - Mauve and Enchanted Birch block sets have been replaced with the Wisteria block set.
        - Wisteria blocks come in three colors: Sky, Lavender, and Salmon.
        - The Wisteria Grove generates trees in all three colors, with hanging Wisteria Vines off of all of them.
        - A special thank you to KelloVerra who made the new Wisteria textures!
- Added a new mob: the Ashen!
    - The Ashen is a Zombie variant that spawns in Ashen Woodlands and Inferno biomes.
    - You can also find it in Trial Chambers.
    - They're immune to Lava and Magma Blocks.
    - Currently, they drop nothing.
- Added a new item: the Iridescent Ring!
    - The item has a special rainbow name! :3
    - It can be crafted with a Prismarite Cluster and 3 Gold Ingots.
    - When held in your hand, it spawns sparkle particles around you!
- Removed, merged, or renamed several biomes:
    - Arid Mountains has been removed.
    - Barley Fields has been merged with Prairie.
    - Cold Deciduous Forest has been removed.
    - Deciduous Forest has been renamed to Old Growth Forest.
    - Frozen Tundra has been removed.
    - Mauve Hills has been removed.
    - Mountains has been removed.
    - Pumpkin Fields has been merged with Autumnal Maple Forest.
    - Redstone Abyss has been removed.
    - Rocky Meadow has been removed.
    - Scorching Caves has been renamed to Inferno.
    - Steppe has been removed.
    - Temperate Grove has been renamed to Windswept Maple Forest.
    - All taken into account, this leaves the biome count at 64.
- Improved the feature placement in the following biomes:
    - Ashen Woodlands (less clutter, more variation, better water color)
    - Autumnal Maple Forest (better leaf litter placement)
    - Bamboo Forest (varying tree density, can get pretty dark in some places)
    - Blackwood Taiga (denser, now actually consistently dark)
    - Boreal Taiga (smaller trees, the larger trees have been moved to Old Growth Boreal Taiga)
    - Chalk Cliffs (no chalk grass blocks on steep cliffs, less dirt/stone breaking through)
    - Clover Plains (less chaotic Lupine placement)
    - Cold Boreal Taiga (smaller trees)
    - Dry Bushland (less chaotic surface block placement)
    - Highland Fields (less flower/rock noise)
    - Inferno (less Basalt, more magma, generally more navigable, less leaking of features into structures like Ancient Cities and Trial Chambers)
    - Joshua Desert (better surface blocks, less cluttered grass)
    - Marsh (murkier grass colors, mud pools)
    - Muddy River (less Cattail spam)
    - Orchard (better large oak tree shape, slightly less Apple Oak Leaves)
    - Outback (better surface blocks)
    - Poppy Fields (less chaotic surface blocks, now actually generates Poppies, added Salmon Poppy)
    - Prairie (added noise-based Barley patches)
    - Redwoods (varying tree density, more foliage, more tree height variation)
    - Sparse Rainforest (now actually sparse!)
    - Windswept Maple Forest (less oak, more maple trees)
- Added nearly two dozen additions / changes to vanilla biomes. Every individual change is configurable. The changes by default are:
    - Saguaro Cacti in Badlands
    - Steppe Grass in Badlands
    - Ash Vents in Basalt Deltas
    - Palm Trees on Beaches
    - Aspen-shaped birch trees
    - Orange Coneflowers in Birch Forests
    - Grass Sprouts in all biomes with Short Grass
    - Shrubs in most forested biomes
    - Sandy Grass in Deserts
    - More fancy oak trees in Forests
    - RU's tall flowers in most forests
    - Bamboo Trees in Jungles
    - Elephant Ears in Jungles
    - Hibiscuses in Jungles
    - Flowering Lilies replacing Lily Pads in Mangrove Swamps
    - Taller oak trees
    - Small bushes in Plains and Savannas
    - Frozen Grass in snowy biomes
    - Cattails in Swamps and Mangrove Swamps
    - Willow trees in Swamps
    - Pine trees using Pine logs/leaves
    - Purple Coneflowers in Taigas
- In addition to the changes above, small tweaks were made to vanilla structures.
    - Shipwrecks can spawn with RU wood blocks.
    - Villages in biomes with Silt and Peat dirt now correctly spawn Silt Dirt Path / Peat Dirt Path blocks, respectively.
    - Trial Chambers now spawn in all overworld RU biomes except the Inferno.
- Rewrote how RU's biomes are placed.
    - Terrablender has been removed as a dependency, Lithostitched takes it place
    - Several biomes have more sensible biome placements, such as Redwoods no longer bordering Jungles
    - RU and non-RU biomes are more integrated as they're not split into distinct "regions"
    - Most biomes let you configure an individual biome weight, see config file for details
    - Individual biomes remain toggleable in the config screen
- Added several new config options.
    - Branch Mode: determines how branches are placed. You can now replace all branches with their log variants, or remove the branches entirely.
    - Small Oak Trees: determines if oak trees with the Small Oak Log block should be placed. Defaults to false.
    - Painted Planks: determines if Painted Planks should be craftable. Defaults to false.
- Added/changed new particles.
    - Most RU leaves have falling leaf particles.
        - Blackwood, Larch, and Pine leaves have pine needle particles instead.
        - Magnolia leaves each have special particle textures.
    - Added particles to several Bioshroom blocks.
        - Viridescent Nylium has subtle particles similar to Mycelium.
        - Bioshrooms have a small amount of floating spore particles around them similar to Spore Blossoms.
        - Glowing Bioshrom Blocks have dripping particles similar to Spore Blossoms.
    - Added floating particles around the Hyacinth Stock.
    - Sparkle particles in the Prismachasm are now tinted to the Prismarite color at their position.
    - Most biomes had their ambient particles reduced / removed, as these new particles do the same job.
- Added special sounds to a handful of block types.
    - Leaf Litter have their sounds backported.
    - Windswept Grass now has a rustling sound.
    - Baobab and Redwood wood blocks have unique sound types.
- Renamed a handful of blocks and items.
    - Mauve wood blocks are now Wisteria wood blocks.
    - Mauve natural blocks are now Lavender Wisteria natural blocks.
    - Enchanted Birch natural blocks are now Sky Wisteria natural blocks.
    - Leaf Piles are now Leaf Litter.
    - Cactus Flower is now Saguaro Cactus Flower.
    - Medium Grass and Stone Bud are merged into a single block: Grass Sprouts.
- Made various other small changes.
    - Most RU blocks and items can now be found in vanilla item tabs in addition to the RU-dedicated tab.
    - Prismaglass is no longer bonemealable and correctly culls when placed next to one another.
    - Silt and Peat Farmland now act more like normal Farmland: Modded crops can be planted on them and Villagers can replant crops on them.
    - Other changes that I've forgotten at this point. This update took months, I don't remember everything I did.
"""
UPLOAD_VERSIONS = [
    ("fabric", "21.1"),
    ("neoforge", "21.1"),
]

DEPENDENCIES = [
    {
        "name": "Lithostitched",
        "project_id": "XaDC71GB",
        "dependency_type": "required",
        "modId": 936015,
        "relationType": 3,
    }
]

MODRINTH_ID = "Tkikq67H"
CURSEFORGE_ID = "659110"

RELEASE_TYPE = "release"

# Global: Should never need to be touched!

BASE_FOLDER = os.path.dirname(os.path.abspath(__file__))


MODRINTH_TOKEN = os.getenv('TOKEN_MR')
if not MODRINTH_TOKEN:
    raise EnvironmentError("MODRINTH_TOKEN is unset!")
MODRINTH_GAME_VERSIONS = {
    "21.1": ["1.21.1"],
    "26.1": ["26.1"],
}

CURSEFORGE_TOKEN = os.getenv('TOKEN_CF')
if not CURSEFORGE_TOKEN:
    raise EnvironmentError("CURSEFORGE_TOKEN is unset!")
CURSEFORGE_URL = f"https://minecraft.curseforge.com/api/v1/projects/{CURSEFORGE_ID}/upload-file"
CURSEFORGE_GAME_VERSIONS = {
    "21.1": [11779],
    "26.1": [15933],
}
CURSEFORGE_LOADERS = {
    "fabric": 7499,
    "forge": 7498,
    "neoforge": 10150,
}


# Code

def upload_modrinth(loader: str, version: str, file_path: str, dependencies):

    game_versions = MODRINTH_GAME_VERSIONS.get(version)

    metadata = {
        "name": f"v{MOD_VERSION} ~ {loader.title()} {version}",
        "version_number": f"{MOD_VERSION}-{loader}-{version}",
        "project_id": MODRINTH_ID,
        "game_versions": game_versions,
        "loaders": [loader],
        "featured": True,
        "changelog": CHANGELOG,
        "version_type": RELEASE_TYPE,
        "file_parts": ["file"],
        "dependencies": dependencies
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


def upload_curseforge(loader: str, version: str, file_path: str, dependencies):
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
        "changelogType": "markdown",
        "dependencies": dependencies
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
        'build',
        'libs',
        f'{MOD_ID}-{MOD_VERSION}-{modloader}-{game_version}.jar'
    )

    dependencies = DEPENDENCIES.copy()
    if (modloader == "fabric"):
        dependencies.append(
            {
                "mod_name": "Fabric API",
                "modId": 306612,
                "relationType": 3,
                "project_id": "P7dR8mSH",
                "dependency_type": "required"
            }
        )

    if not os.path.exists(mod_path):
        print(f"File not found, skipping: {mod_path}")
        continue

    upload_modrinth(modloader, game_version, mod_path, dependencies)
    upload_curseforge(modloader, game_version, mod_path, dependencies)

input("Press any key to close")