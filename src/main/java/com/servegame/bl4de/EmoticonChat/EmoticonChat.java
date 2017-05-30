package com.servegame.bl4de.EmoticonChat;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.plugin.Plugin;

/**
 * File: EmoticonChat.java
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
@Plugin(id = "emoticonchat", name = "EmoticonChat", version = "0.0.1")
public class EmoticonChat {
    /* Constants */
    private char INDICATOR;



    /* Injects */
    @Inject
    private Game game;

    @Inject
    private Logger logger;

    /* Server loading events */
    @Listener
    public void onLoad(GameLoadCompleteEvent event){
        this.INDICATOR = '~';
        // would need to add keyword equivalents here TODO
        this.logger.info("EmoticonChat has loaded");
    }

    @Listener
    public void onInit(GameInitializationEvent event){

    }
}
