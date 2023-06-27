package com.mrmelon54.BetterResourcePackSorting.mixin;

import com.mrmelon54.BetterResourcePackSorting.BetterResourcePackSorting;
import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameGetter;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Pack.class)
public class MixinPack {
    @Inject(method = "readPackInfo", at = @At(value = "RETURN", ordinal = 1), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void injectedReadPackInfo(String string, Pack.ResourcesSupplier resourcesSupplier, CallbackInfoReturnable<Pack.Info> cir, PackResources packResources, PackMetadataSection packMetadataSection) {
        if (packMetadataSection instanceof PackResourceCustomNameGetter getter)
            BetterResourcePackSorting.mapPackIdDisplayName.put(string, getter.getCustomName());
    }
}
