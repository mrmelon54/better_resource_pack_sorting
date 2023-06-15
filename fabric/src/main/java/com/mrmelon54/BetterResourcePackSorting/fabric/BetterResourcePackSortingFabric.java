package com.mrmelon54.BetterResourcePackSorting.fabric;

import com.mrmelon54.BetterResourcePackSorting.fabriclike.BetterResourcePackSortingFabricLike;
import net.fabricmc.api.ModInitializer;

public class BetterResourcePackSortingFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BetterResourcePackSortingFabricLike.init();
    }
}
