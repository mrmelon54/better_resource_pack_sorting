package com.mrmelon54.BetterResourcePackSorting.quilt;

import com.mrmelon54.BetterResourcePackSorting.fabriclike.BetterResourcePackSortingFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class BetterResourcePackSortingQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        BetterResourcePackSortingFabricLike.init();
    }
}
