package de.duckulus.rpspoof.mixins;

import de.duckulus.rpspoof.RPSpoofMod;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundResourcePackPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class MixinClientPacketListener {

    @Inject(
            at = @At("HEAD"),
            method = "handleResourcePack"
    )
    public void onHandleResourcePack(ClientboundResourcePackPacket clientboundResourcePackPacket, CallbackInfo ci) {
        RPSpoofMod.getInstance().setShouldMakeNotRequired(false);
    }

}
