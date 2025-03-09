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

    // When the player jumps, this function activates.
    @EventHandler
    fun playerJumpEvent(event: PlayerJumpEvent) {
        val player = event.player

        // Currently this code is not in use
        //if (!player.isOp) {
        //    event.isCancelled = true
        //}

        val jumps = jumpCount[player.uniqueId] ?: 0
        jumpCount[player.uniqueId] = jumps + 1

        // List of what message is sent to the player after they jump depending on how many times they have already jumped.
        val messages = listOf(
            "you can't jump",
            "hey! I said you can't jump!",
            "don't you listen?",
            "I'm warning you!",
            "Alright. That's it. §4You asked for this!"
        )


        val msg = messages.getOrNull(jumps)
        // Checks to see if "msg" is a null.
        if (msg != null) {
            player.sendMessage(msg)
        }

        // When player jumps 5 times this launches them in the air. If they don't die from that they are killed manually by the server.
        if (jumps == 5) {
            Bukkit.getScheduler().runTaskLater(TrollMinecraftEvent.instance, Runnable {
                val vel = Vector(0.0, 3.0, 0.0)
                player.velocity = vel
            }, 1)
            jumpCount[player.uniqueId] = 0

            // Checks to see if the player survived the fall. If they did the server will kill them.
            if (player.isOnGround) {
                if (!player.isDead) {
                    player.sendMessage("What?! You survived? Oh well. §4Time for plan B.")
                    Thread.sleep(1000)
                    player.health = 0.0
                }
            }
            return
        }
    }
}