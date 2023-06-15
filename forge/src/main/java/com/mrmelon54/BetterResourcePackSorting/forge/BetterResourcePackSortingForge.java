package com.mrmelon54.BetterResourcePackSorting.forge;

import dev.architectury.platform.forge.EventBuses;
import com.mrmelon54.BetterResourcePackSorting.BetterResourcePackSorting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterResourcePackSorting.MOD_ID)
public class BetterResourcePackSortingForge {
    public BetterResourcePackSortingForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(BetterResourcePackSorting.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        BetterResourcePackSorting.init();
    }
}
