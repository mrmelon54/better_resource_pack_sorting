package com.mrmelon54.BetterResourcePackSorting.mixin;

import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameGetter;
import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameSetter;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Pack.Info.class)
public class MixinPack_Info implements PackResourceCustomNameGetter, PackResourceCustomNameSetter {
    private Component customName;

    @Override
    public Component getCustomName() {
        return customName;
    }

    @Override
    public void setCustomName(Component name) {
        customName = name;
    }
}
