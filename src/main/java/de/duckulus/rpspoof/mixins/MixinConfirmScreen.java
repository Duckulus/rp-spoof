package de.duckulus.rpspoof.mixins;

import de.duckulus.rpspoof.RPSpoofMod;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This Mixin is required to add a Button the ConfirmScreen that appears when you try to join a Server
 * which has a forced resource pack and the resource pack status is set to disabled or prompt
 */
@Mixin(ConfirmScreen.class)
public abstract class MixinConfirmScreen extends Screen {

    protected MixinConfirmScreen(Component component) {
        super(component);
    }

    @Shadow
    protected abstract void addExitButton(Button button);

    @Shadow
    @Final
    protected BooleanConsumer callback;

    @Inject(
            at = @At("TAIL"),
            method = "addButtons"
    )
    public void onAddButtons(int i, CallbackInfo ci) {
        if (this.title.equals(Component.translatable("multiplayer.requiredTexturePrompt.line1"))) { // Only render the extra button on the right ConfirmScreen
            addExitButton(Button.builder(Component.literal("Join anyway"), button -> {
                RPSpoofMod.getInstance().setShouldMakeNotRequired(true);
                this.callback.accept(true); // Behave like the pack has been accepted
            }).bounds(this.width / 2 - 155 + 80, i + 30, 150, 20).build());
        }
    }

}
