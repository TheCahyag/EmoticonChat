package com.servegame.bl4de.EmoticonChat.util;

import java.util.HashMap;
import java.util.Map;

/**
 * File: Config.java
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public final class Config {
    private Map<String, String> emoticonMapping;
    private char indicator;

    public void setIndicator(char indicator){
        this.indicator = indicator;
    }

    public void setMappings(Map<String, String> emoticonMappings){
        Map<String, String> tmp = new HashMap<>();
        for (Map.Entry<String, String> entry :
                emoticonMappings.entrySet()) {
            // Iterate through each key and split on ',' to account for aliases
            String key = entry.getKey(), value = entry.getValue();
            String[] keys = key.split(",");
            for (String keyop :
                    keys) {
                // Add each alias to our new map
                tmp.put(keyop, value);
            }
        }
        this.emoticonMapping = tmp;
    }

    public char getIndicator() {
        return indicator;
    }

    public Map<String, String> getEmoticonMapping() {
        return this.emoticonMapping;
    }

    @Override
    public String toString() {
        return "Indicator: " + this.indicator +"\n" + this.emoticonMapping.toString();
    }
}
