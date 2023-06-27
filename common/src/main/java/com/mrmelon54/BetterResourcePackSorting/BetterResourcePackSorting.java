package com.mrmelon54.BetterResourcePackSorting;

import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.packs.repository.Pack;

import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;

public class BetterResourcePackSorting {
    public static final String MOD_ID = "better_resource_pack_sorting";
    public static HashMap<String, Component> mapPackIdDisplayName = new HashMap<>();

    public static void init() {
    }

    public static String getPackSortableName(Pack pack) {
        return getTextAsSortable(getPackDisplayName(pack));
    }

    public static Component getPackDisplayName(Pack pack) {
        return pack instanceof PackResourceCustomNameGetter getter ? getter.getCustomName() : pack.getTitle();
    }

    public static String getTextAsSortable(Component a) {
        if (a == null) return "";
        Optional<String> o = a.toFlatList(Style.EMPTY).stream().map(text -> {
            String s = text.getString().toUpperCase(Locale.ROOT);
            return removeFormattingCodes(s);
        }).reduce((s, s2) -> s + s2);
        return o.orElse("");
    }

    private static String removeFormattingCodes(String a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '§') {
                i++;
                continue;
            }
            sb.append(a.charAt(i));
        }
        return sb.toString();
    }
}
