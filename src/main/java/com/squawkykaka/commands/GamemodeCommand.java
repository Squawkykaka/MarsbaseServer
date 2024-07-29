package com.squawkykaka.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super("gamemode", "gm");

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("You need to supply a gamemode!");
        });

        var gamemodeArgument = ArgumentType.Enum("gamemode", GameMode.class);

        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("You must be a player to use this command!");
                return;
            }

            player.setGameMode(context.get("gamemode"));
        }, gamemodeArgument.setFormat(ArgumentEnum.Format.LOWER_CASED));
    }
}
