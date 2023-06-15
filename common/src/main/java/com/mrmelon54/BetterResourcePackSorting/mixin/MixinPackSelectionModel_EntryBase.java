package com.mrmelon54.BetterResourcePackSorting.mixin;

import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameGetter;
import net.minecraft.client.gui.screens.packs.PackSelectionModel;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PackSelectionModel.EntryBase.class)
public class MixinPackSelectionModel_EntryBase implements PackResourceCustomNameGetter {
    @Shadow
    @Final
    private Pack pack;

    @Override
    public Component getCustomName() {
        return pack instanceof PackResourceCustomNameGetter getter ? getter.getCustomName() : null;
    }
}
