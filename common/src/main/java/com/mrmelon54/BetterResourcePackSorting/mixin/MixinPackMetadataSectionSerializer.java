package com.mrmelon54.BetterResourcePackSorting.mixin;

import com.google.gson.JsonObject;
import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameSetter;
import net.minecraft.network.chat.Component.Serializer;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.metadata.pack.PackMetadataSectionSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PackMetadataSectionSerializer.class)
public class MixinPackMetadataSectionSerializer {
    @Inject(method = "fromJson(Lcom/google/gson/JsonObject;)Lnet/minecraft/server/packs/metadata/pack/PackMetadataSection;", at = @At("RETURN"))
    private void injectedFromJson(JsonObject jsonObject, CallbackInfoReturnable<PackMetadataSection> cir) {
        PackMetadataSection returnValue = cir.getReturnValue();
        if (returnValue instanceof PackResourceCustomNameSetter setter && jsonObject.has("name")) {
            MutableComponent text = Serializer.fromJson(jsonObject.get("name"));
            setter.setCustomName(text);
        }
    }
}
