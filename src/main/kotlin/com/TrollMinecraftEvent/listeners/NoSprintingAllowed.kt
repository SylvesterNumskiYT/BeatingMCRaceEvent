package com.TrollMinecraftEvent.listeners

import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerToggleSprintEvent

class NoSprintingAllowed : Listener {

    // Sets the players hunger points to 3 bars when they try to sprint.
    @EventHandler
    fun playerSprintEvent(event: PlayerToggleSprintEvent) {
        val player = event.player

        // Checks if the player is an op. If not sets hunger 3 bars.
        if (!player.isOp) {
            player.foodLevel = 0
        }
    }
}