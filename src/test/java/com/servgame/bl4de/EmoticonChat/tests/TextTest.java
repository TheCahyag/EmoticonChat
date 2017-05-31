package com.servgame.bl4de.EmoticonChat.tests;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.HashMap;
import java.util.Map;

/**
 * File: TextTest.java
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class TextTest {
    public static Map<String, String> emoticonMapping;
    static char indicator = ':';

    public static void main(String[] args) {
        emoticonMapping = new HashMap<>();
        emoticonMapping.put("ft", "ヽ(`Д´)ﾉ︵ ┻━┻");
        emoticonMapping.put("fliptable", "ヽ(`Д´)ﾉ︵ ┻━┻");
        String[] sections = "&2&4:ft".split("&");
        String format = "a";
        System.out.println("result: " + format.toLowerCase().matches("^[a-fk-or0-9]+$"));
        for (String s :
                sections) {
            System.out.println(s);
        }

        String body = ":ft";
        System.out.println(replace(body));


    }

    private static String replace(String string) {
        String newBody = "";
        int lastIndex = 0, finalIndex = 0;
        while (lastIndex != -1) {
            // Iterate until there is no more '~' chars
            lastIndex = string.indexOf(indicator, lastIndex);
            if (lastIndex != -1) {
                newBody += string.substring(finalIndex, lastIndex);
                String sub = string.substring(lastIndex + 1, string.length());
                String key = sub.split("[\"',.<>?/\\-+=*!@#$~%^&(){}\\[\\]` ]")[0];
                if (emoticonMapping.containsKey(key)) {
                    // If the word preceding the indicator is in the map the replacement is made
                    newBody += emoticonMapping.get(key);
                    lastIndex++;
                    finalIndex = lastIndex + key.length();
                } else {
                    // If the word preceding the indicator is not in the map the original text is put back
                    newBody += "~" + key;
                    lastIndex++;
                    finalIndex = lastIndex + key.length();
                }
            }
        }
        newBody += string.substring(finalIndex, string.length());
        return newBody;
    }
    private boolean hasIndicator(String string){
        return string.indexOf(this.indicator) != -1;
    }
}
