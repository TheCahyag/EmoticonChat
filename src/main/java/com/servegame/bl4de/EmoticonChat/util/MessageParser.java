package com.servegame.bl4de.EmoticonChat.util;

import com.google.common.collect.ImmutableList;
import com.servegame.bl4de.EmoticonChat.EmoticonChat;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.message.MessageEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.ClickAction;
import org.spongepowered.api.text.action.HoverAction;
import org.spongepowered.api.text.action.ShiftClickAction;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextStyle;

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
        //System.out.println(event.getOriginalMessage().toString());
        ImmutableList<Text> list = message.getChildren();
        Optional<Player> player = event.getCause().first(Player.class);
        Player player1 = null;
        if (player.isPresent()) {
            player1 = player.get();
            String string = event.getOriginalMessage().toPlain();
            String sub = string.substring(player1.getDisplayNameData().displayName().get().toPlain().length() + 3, string.length());
            if (hasIndicator(string)){
                event.setMessage(list.get(0), Text.of(replace(sub)), list.get(2));
            }
            //event.setMessage(Text.of(string));


//            player1 = player.get();
//            //player1.hasPermission()
//            for (int i = 0; i < list.size(); i++) {
//                this.logger.info("Child " + i + ": " + list.get(i).toString());
//            }
//            if (list.size() == 3) {
//                // This *should* determine messages sent from players, but might need a better check
//                ImmutableList<Text> bodyChildren = list.get(1).getChildren();
////                    .getChildren().get(0)
////                    .getChildren().get(0)
////                    .getChildren().get(0)
////                    .getChildren(); // Yuck
//                while (bodyChildren.size() == 1) {
//                    bodyChildren = bodyChildren.get(0).getChildren();
//                }
//                Text newBody = Text.of("");
//                for (int i = 0; i < bodyChildren.size(); i++) {
//                    this.logger.info("BodyChild " + i + ": " + bodyChildren.get(i).toString());
//                }
//                for (Text text :
//                        bodyChildren) {
//                    // Iterate through each child in the body of the message
//                    Text tmp;
//                    String content = text.toPlain();
//                    this.logger.info(content);
//                    if (hasIndicator(content)) {
//                        // If the message contains the indicator char
//                        content = replace(content);
//                        TextColor color = text.getColor();
//                        TextStyle style = text.getStyle();
//                        HoverAction hoverAction = null;
//                        ClickAction clickAction = null;
//                        ShiftClickAction shiftClickAction = null;
//                        if (text.getHoverAction().isPresent()) {
//                            // Checks for hover action
//                            hoverAction = text.getHoverAction().get();
//                        }
//                        if (text.getClickAction().isPresent()) {
//                            // Checks for hover action
//                            clickAction = text.getClickAction().get();
//                        }
//                        if (text.getShiftClickAction().isPresent()) {
//                            // Checks for hover action
//                            shiftClickAction = text.getShiftClickAction().get();
//                        }
//                        tmp = Text.of(color, style, hoverAction, clickAction, shiftClickAction, content);
//                    } else {
//                        // If there is no indicator don't change the Text object
//                        tmp = text;
//                    }
//                    newBody.toBuilder().append(tmp).build();
//                }
//                event.setMessage(list.get(0), Text.of(newBody), list.get(2));
//                return;
//            }
        }
    }

    /**
     * replace TODO
     * @param string - TODO
     * @return - TODO
     */
    private String replace(String string){
        String newBody = "";
        int lastIndex = 0;
        int finalIndex = 0;
        while (lastIndex != -1){
            // Iterate until there is no more '~' chars
            lastIndex = string.indexOf(this.indicator, lastIndex);
            if (lastIndex != -1){
                newBody += string.substring(0, lastIndex);
                String sub = string.substring(lastIndex + 1, string.length());
                String key = sub.split("[\"',.<>?/\\-+=*!@#$~%^&(){}\\[\\]` ]")[0];
                if (this.emoticonMapping.containsKey(key)){
                    // If the word preceding the indicator is in the map the replacement is made
                    newBody += this.emoticonMapping.get(key);
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

    /**
     * hasIndicator returns true if the string contains the indicator
     * @param string - string to check
     * @return - boolean - whether or not it has the indicator char
     */
    private boolean hasIndicator(String string){
        return string.indexOf(this.indicator) != -1;
    }
}
