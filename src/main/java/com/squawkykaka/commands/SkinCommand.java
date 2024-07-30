package com.squawkykaka.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkinCommand extends Command {
    public SkinCommand() {
        super("skin", "s");
        setDefaultExecutor(((sender, context) -> {
            sender.sendMessage("You need to supply a skin! Use /skin <username>");
        }));

        var usernameArgument = ArgumentType.String("username");

        addSyntax((sender, context) -> {
            final PlayerSkin playerSkin = PlayerSkin.fromUsername(context.get("username"));

            if (!(sender instanceof Player player)) return;
            player.setSkin(playerSkin);
            var message = Component.text("Set skin to ")
                    .color(TextColor.fromHexString("#cbd466"))
                    .append(Component.text(context.get("username").toString())
                            .color(TextColor.fromHexString("#ff6462")))
                    .append(Component.text("'s skin.")
                            .color(TextColor.fromHexString("#cbd466")));

            sender.sendMessage(message);
        }, usernameArgument);

    }
}
