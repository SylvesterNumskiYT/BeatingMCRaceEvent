package com.TrollMinecraftEvent.listeners

import com.TrollMinecraftEvent.TrollMinecraftEvent
import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import java.util.*
import org.bukkit.util.Vector

class NoJumpingAllowed : Listener {

    private val jumpCount = mutableMapOf<UUID, Int>()
    private val shouldDie = mutableMapOf<UUID, Int>()

    // When the player jumps, this sends a message depending on how many times they jumped so far. When they jumped 5 times
    // they get launched high into the sky next jump.
    @EventHandler
    fun playerJumpEvent(event: PlayerJumpEvent) {
        val player = event.player

        // This code is currently not in use.
        //if (!player.isOp) {
        //    event.isCancelled = true
        //}

        val jumps = (jumpCount[player.uniqueId] ?: 0) + 1
        jumpCount[player.uniqueId] = jumps

        // List of messages the server will send the player depending on how many times you jump.
        val messages = listOf(
            "you can't jump",
            "hey! I said you can't jump!",
            "don't you listen?",
            "I'm warning you!",
            "Alright. That's it. §4You asked for this!"
        )

        val msg = messages.getOrNull(jumps - 1)
        if (msg != null) {
            player.sendMessage(msg)
        }

        // When the player jumps 5 times after they jump one more time they are launched into the air. If they don't die from that
        // The server manually kills them.
        if (jumps == 5) {
            Bukkit.getScheduler().runTaskLater(TrollMinecraftEvent.instance, Runnable {
                val vel = Vector(0.0, 3.0, 0.0)
                player.velocity = vel

            }, 1)

            shouldDie[player.uniqueId] = 1
            jumpCount[player.uniqueId] = 0
            return
        }
    }
    @EventHandler
    fun playerMoveEvent(event: PlayerMoveEvent) {
        val player = event.player
        if (shouldDie[player.uniqueId] == 1) {
            if (player.isOnGround || player.isInWater || player.isInLava || player.isClimbing || player.isInPowderedSnow
                || player.isGliding) {
                player.health = 0.0
                if (player.isDead) {
                    shouldDie[player.uniqueId] = 0
                }
            }

        }
    }
}