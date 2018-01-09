package io.github.mpalmer7.trappedfree;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

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


    @Listener
    public void onServerStart(GameStartedServerEvent event) {

    }



}
