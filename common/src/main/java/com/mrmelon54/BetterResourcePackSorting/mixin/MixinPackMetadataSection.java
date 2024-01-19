package com.mrmelon54.BetterResourcePackSorting.mixin;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameGetter;
import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameSetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PackMetadataSection.class)
public class MixinPackMetadataSection implements PackResourceCustomNameGetter, PackResourceCustomNameSetter {
    // To future self
    // This code is a mess
    // I have no idea how Mojang expects me to mod an extra field into this new codec system
    // This took 4 hours to implement
    // I am never getting that time back
    @Inject(method = "method_52434", at = @At("HEAD"), cancellable = true)
    private static void injectCodecLambda(RecordCodecBuilder.Instance<PackMetadataSection> instance, CallbackInfoReturnable<App<RecordCodecBuilder.Mu<PackMetadataSection>, PackMetadataSection>> cir) {
        cir.setReturnValue(instance.group(ComponentSerialization.CODEC.fieldOf("description").forGetter(PackMetadataSection::description), ComponentSerialization.CODEC.optionalFieldOf("name").forGetter(packMetadataSection -> {
            //noinspection ConstantValue
            return (Object) packMetadataSection instanceof PackResourceCustomNameGetter duck ? duck.better_resource_pack_sorting$getCustomName() : Optional.empty();
        }), Codec.INT.fieldOf("pack_format").forGetter(PackMetadataSection::packFormat), InclusiveRange.codec(Codec.INT).optionalFieldOf("supported_formats").forGetter(PackMetadataSection::supportedFormats)).apply(instance, new Function4<Component, Optional<Component>, Integer, Optional<InclusiveRange<Integer>>, PackMetadataSection>() {
            @Override
            public PackMetadataSection apply(Component component, Optional<Component> component2, Integer integer, Optional<InclusiveRange<Integer>> integerInclusiveRange) {
                PackMetadataSection packMetadataSection = new PackMetadataSection(component, integer, integerInclusiveRange);
                //noinspection ConstantValue
                if ((Object) packMetadataSection instanceof PackResourceCustomNameSetter duck)
                    duck.better_resource_pack_sorting$setCustomName(component2.orElse(null));
                return packMetadataSection;
            }
        }));
    }

    @Unique
    private Component better_resource_pack_sorting$customName;

    @Override
    public Optional<Component> better_resource_pack_sorting$getCustomName() {
        return Optional.ofNullable(better_resource_pack_sorting$customName);
    }

    @Override
    public void better_resource_pack_sorting$setCustomName(Component name) {
        better_resource_pack_sorting$customName = name;
    }
}
