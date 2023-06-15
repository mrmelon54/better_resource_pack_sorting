package com.mrmelon54.BetterResourcePackSorting.mixin;

import com.mrmelon54.BetterResourcePackSorting.BetterResourcePackSorting;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(PackRepository.class)
public class MixinPackRepository {
    @Inject(method = "getAvailablePacks()Ljava/util/Collection;", at = @At("RETURN"), cancellable = true)
    private void getSortedAvailablePacks(CallbackInfoReturnable<Collection<Pack>> cir) {
        Collection<Pack> returnValue = cir.getReturnValue();
        Stream<Pack> sorted = returnValue.stream().sorted((o1, o2) -> {
            String s1 = BetterResourcePackSorting.getPackSortableName(o1);
            String s2 = BetterResourcePackSorting.getPackSortableName(o2);
            return s1.compareTo(s2);
        });
        cir.setReturnValue(sorted.collect(Collectors.toList()));
        cir.cancel();
    }
}
