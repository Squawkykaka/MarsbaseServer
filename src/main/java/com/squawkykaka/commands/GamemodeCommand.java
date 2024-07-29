package com.squawkykaka.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super("gamemode", "gm");

        setDefaultExecutor((sender, context) -> sender.sendMessage("You need to supply a gamemode! Use /gamemode <gamemode>"));

        var gamemodeArgument = ArgumentType.Enum("gamemode", GameMode.class).setFormat(ArgumentEnum.Format.LOWER_CASED);

        gamemodeArgument.setCallback((sender, exception) -> {
            final String input = exception.getInput();
            Component message = Component.text("The gamemode ")
                    .color(TextColor.fromHexString("#cbd466"))
                    .append(Component.text(input)
                            .color(TextColor.fromHexString("#ff6462")))
                    .append(Component.text(" is not a command")
                            .color(TextColor.fromHexString("#cbd466")));

            sender.sendMessage(message);
        });

        addSyntax((sender, context) -> {
            final GameMode gamemode = context.get("gamemode");


            if (!(sender instanceof Player player)) return;
            if (player.getGameMode().equals(gamemode)) {
                Component message = Component.text("Gamemode already set to ")
                        .color(TextColor.fromHexString("#cbd466"))
                        .append(Component.text(gamemode.name())
                                .color(TextColor.fromHexString("#ff6462")));

                player.sendMessage(message);
                return;
            }

            player.setGameMode(gamemode);

            Component message = Component.text("Set Gamemode to ")
                    .color(TextColor.fromHexString("#cbd466"))
                    .append(Component.text(gamemode.name().toLowerCase())
                            .color(TextColor.fromHexString("#ff6462")));
            sender.sendMessage(message);
        }, gamemodeArgument);
    }
}
