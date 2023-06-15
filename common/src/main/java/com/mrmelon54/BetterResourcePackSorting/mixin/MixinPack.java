package com.mrmelon54.BetterResourcePackSorting.mixin;

import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameGetter;
import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameSetter;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Pack.class)
public class MixinPack implements PackResourceCustomNameGetter {
    private Component customName;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectedInit(String string, boolean bl, Pack.ResourcesSupplier resourcesSupplier, Component component, Pack.Info info, PackCompatibility packCompatibility, Pack.Position position, boolean bl2, PackSource packSource, CallbackInfo ci) {
        if ((Object) info instanceof PackResourceCustomNameGetter getter) {
            customName = getter.getCustomName();
        }
    }

    @Inject(method = "readPackInfo", at = @At(value = "RETURN", ordinal = 1), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void injectedReadPackInfo(String string, Pack.ResourcesSupplier resourcesSupplier, CallbackInfoReturnable<Pack.Info> cir, PackResources packResources, PackMetadataSection packMetadataSection) {
        Pack.Info returnValue = cir.getReturnValue();
        if (packMetadataSection instanceof PackResourceCustomNameGetter getter && (Object) returnValue instanceof PackResourceCustomNameSetter setter)
            setter.setCustomName(getter.getCustomName());
    }

    @Override
    public Component getCustomName() {
        return customName;
    }
}
