package com.squawkykaka.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super("teleport", "tp");
        // "my-command" is the main name of the command
        // "hey" is an alias, you can have an unlimited number of those
        setDefaultExecutor(((sender, context) -> {
            sender.sendMessage("You must supply a place to teleport to!");
        }));

        var xArgument = ArgumentType.Float("x");
        var yArgument = ArgumentType.Float("y");
        var zArgument = ArgumentType.Float("z");

        addSyntax((sender, context) -> {
            final float x = context.get(xArgument);
            final float y = context.get(yArgument);
            final float z = context.get(zArgument);

            if (!(sender instanceof Player player)) {
                sender.sendMessage("You must be a player to use this command!");
                return;
            }

            player.teleport(new Pos(x, y, z));

        }, xArgument, yArgument, zArgument);
    }
}