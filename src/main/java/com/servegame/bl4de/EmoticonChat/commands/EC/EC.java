package com.servegame.bl4de.EmoticonChat.commands.EC;

import com.servegame.bl4de.EmoticonChat.EmoticonChat;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

/**
 * File: EC.java
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class EC implements CommandExecutor {
    public EC(EmoticonChat EC){

    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        src.sendMessage(Text.of("Not implemented yet..."));
        return CommandResult.success();
    }
}
