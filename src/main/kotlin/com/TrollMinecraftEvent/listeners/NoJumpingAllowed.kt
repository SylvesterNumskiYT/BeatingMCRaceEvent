package com.TrollMinecraftEvent.listeners

import com.TrollMinecraftEvent.TrollMinecraftEvent
import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.*
import org.bukkit.util.Vector

class NoJumpingAllowed : Listener {

    private val jumpCount = mutableMapOf<UUID, Int>()

    // When the player jumps, this sends a message depending on how many times they jumped so far. When they jumped 5 times
    // they get launched high into the sky next jump.
    @EventHandler
    fun playerJumpEvent(event: PlayerJumpEvent) {
        val player = event.player

        // This code is currently not in use.
        //if (!player.isOp) {
        //    event.isCancelled = true
        //}

        val jumps = jumpCount[player.uniqueId] ?: 0
        jumpCount[player.uniqueId] = jumps + 1

        // List of messages the server will send the player depending on how many times you jump.
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

        // When the player jumps 5 times after they jump one more time they are launched into the air. If they don't die from that
        // The server manually kills them.
        if (jumps == 5) {
            Bukkit.getScheduler().runTaskLater(TrollMinecraftEvent.instance, Runnable {
                val vel = Vector(0.0, 3.0, 0.0)
                player.velocity = vel
            }, 1)

            // If the player survived the fall, the server will manually kill them.
            if (player.isOnGround) {
                if (!player.isDead) {
                    player.sendMessage("What?! You survived? Oh well. §4Time for plan B.")
                    player.health = 0.0
                } else player.sendMessage("I warned you.")
            }

            jumpCount[player.uniqueId] = 0
            return
        }
    }
}