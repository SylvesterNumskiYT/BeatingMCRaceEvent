package com.TrollMinecraftEvent

import com.TrollMinecraftEvent.listeners.NoJumpingAllowed
import com.TrollMinecraftEvent.listeners.NoSprintingAllowed
import org.bukkit.plugin.java.JavaPlugin

class TrollMinecraftEvent : JavaPlugin() {

    companion object {
        lateinit var instance: TrollMinecraftEvent
    }

    // Any code inside this section of code will run when the server starts the plugin.
    override fun onEnable() {
        logger.info("Plugin has loaded")
        instance = this

        // This calls the function on line 22-25
        registerListeners()
    }

    // This function registers the listener class files allowing them to run when the server starts.
    private fun registerListeners() {
        server.pluginManager.registerEvents(NoJumpingAllowed(), this)
        server.pluginManager.registerEvents(NoSprintingAllowed(), this)
    }

    // Any code inside this section of code will run when the server starts to shut down.
    override fun onDisable() {
        logger.info("Plugin has unloaded")
    }
}
