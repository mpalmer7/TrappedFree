/*
 * Copyright (C) 2018  Skatem
 *
 * This file is part of TrappedFree.
 *
 * TrappedFree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * TrappedFree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TrappedFree.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.mpalmer7.trappedfree;

import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.asset.Asset;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.io.IOException;
import java.nio.file.Path;

@Plugin(
        id = "trappedfree",
        name = "TrappedFree",
        authors = {
                "skatem"
        }
)
public class TrappedFree {

    @Inject
    private Logger logger;

    @Inject
    private PluginContainer instance;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path defaultConfig;

    private int cooldown;
    private int warmup;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        getLogger().info(
                instance.getName() + " version " + instance.getVersion().orElse("")
                        + " enabled!");
    }



    @Listener
    public void onGameInit(GameInitializationEvent event) {

        ConfigurationLoader<CommentedConfigurationNode> configLoader = HoconConfigurationLoader.builder().setPath(defaultConfig).build();

        // Generate default config if it doesn't exist
        if (!defaultConfig.toFile().exists()) {
            Asset defaultConfigAsset = getInstance().getAsset("DefaultConfig.conf").get();
            try {
                defaultConfigAsset.copyToFile(defaultConfig);
                configLoader.save(configLoader.load());
            } catch (IOException e) {
                logger.warn("Error loading default config! Error: " + e.getMessage());
            }
        }

        try {
            CommentedConfigurationNode configNode = configLoader.load();
            configNode = configLoader.load();
            cooldown = configNode.getNode("cooldown").getInt();
            warmup = configNode.getNode("warmup").getInt();
        } catch (IOException e) {
            logger.error("Error loading config! Error: " + e.getMessage());
        }





        CommandSpec TrappedFreeCommand = CommandSpec.builder()
                .description(Text.of("TrappedFree Command"))
                .permission("trappedfree.commands.tf")
                .executor(new TrappedFreeCommand(this))
                .build();

        //executes when player types '/trapped'
        Sponge.getCommandManager().register(this, TrappedFreeCommand, "trapped", "trappedfree");
    }


    public int getCooldown() {
        return cooldown;
    }

    public int getWarmup() {
        return warmup;
    }

    public Logger getLogger() {
        return logger;
    }

    public PluginContainer getInstance() {
        return instance;
    }
}
