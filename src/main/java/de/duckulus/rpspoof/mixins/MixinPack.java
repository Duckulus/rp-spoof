package de.duckulus.rpspoof.mixins;

import de.duckulus.rpspoof.RPSpoofMod;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This Mixin is required in order to set a server resource pack as not required
 * if the flag in {@link RPSpoofMod} is set
 */
@Mixin(Pack.class)
public class MixinPack {

    @Shadow
    @Mutable
    @Final
    private boolean fixedPosition;

    @Shadow
    @Mutable
    @Final
    private boolean required;

    @Inject(
            at = @At("TAIL"),
            method = "<init>"
    )
    public void onInit(String string, boolean bl, Pack.ResourcesSupplier resourcesSupplier, Component component, Pack.Info info, PackCompatibility packCompatibility, Pack.Position position, boolean bl2, PackSource packSource, CallbackInfo ci) {
        this.fixedPosition = false;
        if (bl && RPSpoofMod.getInstance().isShouldMakeNotRequired()) {
            this.required = false;
        }
    }

}
