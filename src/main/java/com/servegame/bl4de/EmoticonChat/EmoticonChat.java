package com.servegame.bl4de.EmoticonChat;

import com.google.inject.Inject;
import com.servegame.bl4de.EmoticonChat.util.Config;
import com.servegame.bl4de.EmoticonChat.util.MessageParser;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.message.MessageEvent;
import org.spongepowered.api.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * File: EmoticonChat.java
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
@Plugin(id = "emoticonchat", name = "EmoticonChat", version = "0.0.1")
public class EmoticonChat {
    /* Constants */
    private char indicator;


    /* Global Variables */
    private Config config;
    private Map<String, String> emoticonMapping;
    private MessageParser parser;

    /* Injects */
    @Inject
    private Game game;

    @Inject
    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    /* Server loading events */
    @Listener
    public void onPreInit(GamePreInitializationEvent event){
        // Need to test if the config folder and EmoticonChat folder has been made TODO

        //Load config
        Yaml yaml = new Yaml();
        try {
            InputStream in = new FileInputStream(new File("C:\\Users\\brand\\Dropbox\\College\\Programming\\Java\\Minecraft Plugins\\Sponge Plugins\\Emoticon Chat\\src\\main\\resources\\assets\\config.yml"));
            this.config = yaml.loadAs(in, Config.class);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Listener
    public void onInit(GameInitializationEvent event){
        this.indicator = this.config.getIndicator();
        this.emoticonMapping = this.config.getEmoticonMapping();
        this.parser = new MessageParser(this.emoticonMapping, this.indicator, this);
    }

    @Listener
    public void onLoad(GameLoadCompleteEvent event){
        this.logger.info("EmoticonChat has loaded");
    }

    @Listener
    public void onMessage(MessageEvent event){
        this.parser.parseMessage(event);
    }
}
