package com.servegame.bl4de.EmoticonChat.commands.EC;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

/**
 * File: ECAdd.java
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class ECAdd implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        src.sendMessage(Text.of("Not implemented yet..."));
        return CommandResult.success();
    }
}
