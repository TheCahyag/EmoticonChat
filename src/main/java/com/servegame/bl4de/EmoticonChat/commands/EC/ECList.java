package com.servegame.bl4de.EmoticonChat.commands.EC;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.ArrayList;
import java.util.Map;

/**
 * File: ECList.java
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class ECList implements CommandExecutor {

    private Map<String, String> emoticonMapping;
    private int longestKey;

    public ECList(Map<String, String> map){
        this.emoticonMapping = map;
        this.longestKey = longest(map);
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        ArrayList<Text> textArray = new ArrayList<>();
        textArray.add(Text.of(TextColors.GRAY, "------------------"));
        textArray.add(Text.of(TextColors.GOLD, "Replacement Rules"));
        textArray.add(Text.of(TextColors.GRAY, "--------------------\n"));
        if (this.emoticonMapping.size() == 0){
            textArray.add(Text.of(TextColors.RED, "There are no mappings in the list..."));
            textArray.add(Text.of(TextColors.GRAY, "-----------------------------------------------------"));
            src.sendMessage(Text.builder().append(textArray).build());
            return CommandResult.success();
        }
        for (Map.Entry<String, String> entry :
                this.emoticonMapping.entrySet()) {
            textArray.add(Text.of(
                    TextColors.YELLOW,
                    ":",
                    entry.getKey(),
                    new String(new char[(this.longestKey - entry.getKey().length()) + 20]).replace("\0", " "),
                    entry.getValue(), "\n"));
        }
        textArray.add(Text.of(TextColors.GRAY, "-----------------------------------------------------"));
        src.sendMessage(Text.builder().append(textArray).build());
        return CommandResult.success();
    }

    private int longest(Map<String, String> map){
        int longest = 0;
        for (Map.Entry<String, String> entry :
                map.entrySet()) {
            if (entry.getKey().length() > longest)
                longest = entry.getKey().length();
        }
        return longest;
    }
}
