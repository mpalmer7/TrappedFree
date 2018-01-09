package io.github.mpalmer7.trappedfree;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

public class TrappedFreeCommand implements CommandExecutor {
    private int cooldown;
    private int warmup;
    public TrappedFreeCommand(TrappedFree tf) {
        cooldown = tf.getCooldown();
        warmup = tf.getWarmup();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        //do stuff

        return CommandResult.success();
    }
}
