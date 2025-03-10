package com.TrollMinecraftEvent.listeners

import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerToggleSprintEvent

class NoSprintingAllowed : Listener {

    // When the player attempts to run, the player's hunger is set to 3 bars.
    @EventHandler
    fun playerSprintEvent(event: PlayerToggleSprintEvent) {
        val player = event.player
        if (!player.isOp) {
            player.foodLevel = 6
        }
    }
}