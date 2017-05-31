package com.servegame.bl4de.EmoticonChat.util;

import com.google.common.collect.ImmutableList;
import com.servegame.bl4de.EmoticonChat.EmoticonChat;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.message.MessageEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextElement;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

/**
 * File: MessageParser.java
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class MessageParser {
    private Map<String, String> emoticonMapping;
    private char indicator;
    private Logger logger;

    public MessageParser(Map<String, String> map, char indicator, EmoticonChat plugin){
        this.emoticonMapping = map;
        this.indicator = indicator;
        this.logger = plugin.getLogger();
    }

    /**
     * parseMessage takes a message event, and parses it
     * @param event - message event in which the message can be accessed
     */
    public void parseMessage(MessageEvent event){
        Text message = event.getMessage();
        ImmutableList<Text> list = message.getChildren();
        Optional<Player> player = event.getCause().first(Player.class);
        Player player1;
        if (player.isPresent()) {
            player1 = player.get();
            String string = event.getOriginalMessage().toPlain();
            String sub = string.substring(player1.getDisplayNameData().displayName().get().toPlain().length() + 3, string.length());
            if (hasIndicator(string)){
                event.setMessage(list.get(0), Text.of(formatString(replace(sub))), list.get(2));
            }
        }
    }

    /**
     * replace TODO
     * @param string - TODO
     * @return - TODO
     */
    private String replace(String string){
        String newBody = "";
        int lastIndex = 0, finalIndex = 0;
        while (lastIndex != -1){
            // Iterate until there is no more indicator chars
            lastIndex = string.indexOf(this.indicator, lastIndex);
            if (lastIndex != -1){
                newBody += string.substring(finalIndex, lastIndex);
                String sub = string.substring(lastIndex + 1, string.length());
                String key = sub.split("[\"':;,.<>?/\\-+=*!@#$~%^&(){}\\[\\]` ]")[0];
                if (this.emoticonMapping.containsKey(key)){
                    // If the word preceding the indicator is in the map the replacement is made
                    newBody += this.emoticonMapping.get(key);
                    lastIndex++;
                    finalIndex = lastIndex + key.length();
                } else {
                    // If the word preceding the indicator is not in the map the original text is put back
                    newBody += this.indicator + key;
                    lastIndex++;
                    finalIndex = lastIndex + key.length();
                }
            }
        }
        newBody += string.substring(finalIndex, string.length());
        return newBody;
    }

    /**
     * hasIndicator returns true if the string contains the indicator
     * @param string - string to check
     * @return - boolean - whether or not it has the indicator char
     */
    private boolean hasIndicator(String string){
        return string.contains(this.indicator + "");
    }

    /**
     * TODO
     * @param string - TODO
     * @return - Text - TODO
     */
    private Text formatString(String string){
        if (!string.contains("&")){
            return Text.of(string);
        }
        String[] sections = string.split("&");
        ArrayList<Text> textArray = new ArrayList<>();
        for (int i = 1, j = 0; i < sections.length; i++, j++) {
            String format = sections[i].charAt(0) + "";
            if (format.toLowerCase().matches("^[a-fk-or0-9]+$")){
                textArray.add(Text.of(getFormat(format.charAt(0)), sections[i].substring(1, sections[i].length())));
            } else {
                textArray.add(Text.of("&" + sections[i]));
            }
        }
        return Text.builder().append(textArray).build();
    }

    /**
     * TODO
     * @param format - TODO
     * @return - TextElement - TODO
     */
    private TextElement getFormat(char format){
        switch (format){
            case '0':
                return TextColors.BLACK;
            case '1':
                return TextColors.DARK_GREEN;
            case '2':
                return TextColors.DARK_BLUE;
            case '3':
                return TextColors.DARK_AQUA;
            case '4':
                return TextColors.DARK_RED;
            case '5':
                return TextColors.DARK_PURPLE;
            case '6':
                return TextColors.GOLD;
            case '7':
                return TextColors.GRAY;
            case '8':
                return TextColors.DARK_GRAY;
            case '9':
                return TextColors.BLUE;
            case 'a':
                return TextColors.GREEN;
            case 'b':
                return TextColors.AQUA;
            case 'c':
                return TextColors.RED;
            case 'd':
                return TextColors.LIGHT_PURPLE;
            case 'e':
                return TextColors.YELLOW;
            case 'f':
                return TextColors.WHITE;
            case 'k':
                return TextStyles.OBFUSCATED;
            case 'l':
                return TextStyles.BOLD;
            case 'm':
                return TextStyles.STRIKETHROUGH;
            case 'n':
                return TextStyles.UNDERLINE;
            case 'o':
                return TextStyles.ITALIC;
            case 'r':
                return TextStyles.RESET;
            default:
                // Should never get here
                return null;
        }
    }
}
