package com.TrollMinecraftEvent.listeners

import com.TrollMinecraftEvent.TrollMinecraftEvent
import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.*
import org.bukkit.util.Vector

class NoJumpingAllowed : Listener {

    val jumpCount = mutableMapOf<UUID, Int>()

    @EventHandler
    fun playerJumpEvent(event: PlayerJumpEvent) {
        val player = event.player

        //if (!player.isOp) {
        //    event.isCancelled = true
        //}

        val jumps = jumpCount[player.uniqueId] ?: 0
        jumpCount[player.uniqueId] = jumps + 1

        val messages = listOf(
            "you can't jump",
            "hey! I said you can't jump!",
            "don't you listen?",
            "I'm warning you!",
            "Alright. That's it. §4You asked for this!"
        )

        val msg = messages.getOrNull(jumps)
        if (msg == null) {

        } else player.sendMessage(msg)

        if (jumps == 5) {
            Bukkit.getScheduler().runTaskLater(TrollMinecraftEvent.instance, Runnable {
                val vel = Vector(0.0, 3.0, 0.0)
                player.velocity = vel
            }, 1)
            jumpCount[player.uniqueId] = 0
            return
        }
    }
}