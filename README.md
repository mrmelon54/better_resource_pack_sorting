# Better Resource Pack Sorting

[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/W7W1607S8)

Logo made by [@CarbonGhost](https://github.com/CarbonGhost)

## Setup

- Drop the mod in the mods folder

## Info

This mod changes the sorting order of the available resource packs list

- Uppercase and lowercase are sorted next to each other
- Formatting codes are ignored to reduce confusion

After v1.1.0 this mod also supports adding custom resource pack names:

- Adds a new field `name` into the `pack.mcmeta` which overrides the display name for the resource pack
- Instead of showing `pack-name.zip` it shows `This is a custom name` using the custom styling as well

```json
{
    "pack": {
        "pack_format": 8,
        "name": "\u00A72This is a custom name",
        "description": "\u00A73Custom description\nby MrMelon"
    }
}
```

After v1.1.1: holding alt when hovering over a resource pack will show the original file name

## Discord

- https://discord.gg/usbmdrJ

## Download

- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/better-resource-pack-sorting)
- [Modrinth](https://modrinth.com/mod/better-resource-pack-sorting)
