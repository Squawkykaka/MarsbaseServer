package com.squawkykaka.commands;

import com.squawkykaka.Main;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceManager;

import java.util.Dictionary;

public class InstanceChangeCommand extends Command {
    public InstanceChangeCommand() {
        super("server", "s");
        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("Supply a server to go to.");
        });

        var serverArgument = ArgumentType.String("server");

        addSyntax((sender, context) -> {
            if(!(sender instanceof Player player)) return;
            Instance instance = player.getInstance();

        });

    }
}
