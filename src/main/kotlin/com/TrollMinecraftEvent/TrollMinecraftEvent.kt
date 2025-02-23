package com.TrollMinecraftEvent

import com.TrollMinecraftEvent.listeners.NoJumpingAllowed
import com.TrollMinecraftEvent.listeners.NoSprintingAllowed
import org.bukkit.plugin.java.JavaPlugin

class TrollMinecraftEvent : JavaPlugin() {

    companion object {
        lateinit var instance: TrollMinecraftEvent
    }


    override fun onEnable() {
        // Plugin startup logic
        logger.info("Plugin has loaded")
        instance = this

        registerListeners()
    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(NoJumpingAllowed(), this)
        server.pluginManager.registerEvents(NoSprintingAllowed(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("Plugin has unloaded")
    }
}
