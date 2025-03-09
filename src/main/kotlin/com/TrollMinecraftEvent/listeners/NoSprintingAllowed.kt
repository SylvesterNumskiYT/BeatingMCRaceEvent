package com.TrollMinecraftEvent.listeners

import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerToggleSprintEvent

class NoSprintingAllowed : Listener {

    @EventHandler
    fun playerSprintEvent(event: PlayerToggleSprintEvent) {
        val player = event.player
        if (!player.isOp) {
            event.isCancelled = true
            player.isSprinting = false
        }
    }
}