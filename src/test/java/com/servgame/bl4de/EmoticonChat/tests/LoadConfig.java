package com.servgame.bl4de.EmoticonChat.tests;

import com.servegame.bl4de.EmoticonChat.util.Config;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * File: LoadConfig.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class LoadConfig {
    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        try {
            InputStream in = new FileInputStream(new File("C:\\Users\\brand\\Dropbox\\College\\Programming\\Java\\Minecraft Plugins\\Sponge Plugins\\Emoticon Chat\\src\\main\\resources\\assets\\config.yml"));
            Config config = yaml.loadAs(in, Config.class);
            System.out.println(config.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
