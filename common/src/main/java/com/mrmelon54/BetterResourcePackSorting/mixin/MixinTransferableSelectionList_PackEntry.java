package com.mrmelon54.BetterResourcePackSorting.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import com.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameGetter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.packs.PackSelectionModel;
import net.minecraft.client.gui.screens.packs.TransferableSelectionList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TransferableSelectionList.PackEntry.class)
public abstract class MixinTransferableSelectionList_PackEntry extends ObjectSelectionList.Entry<TransferableSelectionList.PackEntry> {
    private boolean hovered;

    @Shadow
    protected static FormattedCharSequence cacheName(Minecraft minecraft, Component component) {
        return null;
    }

    @Shadow
    @Final
    protected Minecraft minecraft;
    @Shadow
    @Final
    private PackSelectionModel.Entry pack;
    @Shadow
    @Final
    private FormattedCharSequence nameDisplayCache;

    @Shadow
    protected abstract boolean showHoverOverlay();

    @Shadow
    @Final
    private TransferableSelectionList parent;
    private FormattedCharSequence customNameCache;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectedInit(Minecraft minecraft, TransferableSelectionList transferableSelectionList, PackSelectionModel.Entry entry, CallbackInfo ci) {
        Component component = pack instanceof PackResourceCustomNameGetter getter ? getter.getCustomName() : null;
        customNameCache = cacheName(minecraft, component != null ? component : entry.getTitle());
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void injectedRender(GuiGraphics guiGraphics, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f, CallbackInfo ci) {
        hovered = showHoverOverlay() && (minecraft.options.touchscreen().get() || bl || parent.getSelected() == ((TransferableSelectionList.PackEntry) (Object) this) && parent.isFocused());
    }

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/packs/TransferableSelectionList$PackEntry;nameDisplayCache:Lnet/minecraft/util/FormattedCharSequence;"))
    private FormattedCharSequence redirectDisplayName(TransferableSelectionList.PackEntry instance) {
        if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_LALT) && hovered)
            return nameDisplayCache;
        return customNameCache;
    }
}
