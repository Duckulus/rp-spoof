package de.duckulus.rpspoof;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RPSpoofMod implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("rp-spoof");

    private static RPSpoofMod INSTANCE;

    /**
     * This Flag keeps track of whether a current server resource pack should be set as not required
     * This is necessary in order to allow players to freely toggle the server resource pack when
     * selecting the "Join Anyway" Option.
     */
    private boolean shouldMakeNotRequired = false;


    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello World");
        INSTANCE = this;
    }

    public boolean isShouldMakeNotRequired() {
        return shouldMakeNotRequired;
    }

    public void setShouldMakeNotRequired(boolean shouldMakeNotRequired) {
        this.shouldMakeNotRequired = shouldMakeNotRequired;
    }

    public static RPSpoofMod getInstance() {
        return INSTANCE;
    }
}
