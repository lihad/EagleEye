package Lihad.EagleEye.Listeners;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

import Lihad.EagleEye.EagleEye;
import Lihad.EagleEye.EagleEye.ListenerFired;
import Lihad.EagleEye.EagleEye.PlayerInfo;


public class BeyondPlayerListener implements Listener {
	public static EagleEye plugin;
	public BeyondPlayerListener(EagleEye instance) {
		plugin = instance;
	}

    @EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){

		String string = event.getMessage();
		Player player = event.getPlayer();
		try {
			Long currentTime = System.currentTimeMillis();
			Date date = new Date(currentTime);
			
			String outputline = (player.getName()+" has entered the command '"+string+"'");

			
			BufferedWriter output = new BufferedWriter(new FileWriter(EagleEye.log, true));
			output.newLine();
			output.write("["+date+"] "+outputline);
			output.close();
			if(EagleEye.toggle)System.out.println("[EagleEye] "+outputline);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //INTERACT,JOIN,QUIT,PORTAL,RESPAWN,TOGGLE_SNEAK,TOGGLE_SPRINT,LEVEL_CHANGE,CHANGED_WORLD,EGG_THROW,FISH,KICK,ITEM_BREAK,DROP_ITEM,
	//INTERACT_ENTITY,ITEM_HELD,EXP_CHANGE
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event){
    	if(event.getClickedBlock() == null)return;
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.INTERACT, event.getAction().toString()+" "+event.getClickedBlock().getTypeId()+" Location = "+event.getPlayer().getLocation()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.JOIN, "Location "+event.getPlayer().getLocation().toString()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event){
    	//EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.QUIT, "Location "+event.getPlayer().getLocation().toString()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerKick(PlayerKickEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.KICK, "Location "+event.getPlayer().getLocation().toString()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerRespawn(PlayerRespawnEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.RESPAWN, "Location "+event.getPlayer().getLocation().toString()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerPortal(PlayerPortalEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.PORTAL, "Location From = "+event.getFrom().toString()+" Location To = "+event.getTo().toString()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerTeleport(PlayerTeleportEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.TELEPORT, "Location From = "+event.getFrom().toString()+" Location To = "+event.getTo().toString()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerExpChange(PlayerExpChangeEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.EXP_CHANGE, "Location "+event.getPlayer().getLocation().toString()+" EXP difference = "+event.getAmount()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLevelChange(PlayerLevelChangeEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.LEVEL_CHANGE, "Location "+event.getPlayer().getLocation().toString()+" LVL OLD = "+event.getOldLevel()+" LVL NEW = "+event.getNewLevel()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.TOGGLE_SNEAK, "Location "+event.getPlayer().getLocation().toString()+" TOGGLE SNEAK = "+event.isSneaking()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.TOGGLE_SPRINT, "Location "+event.getPlayer().getLocation().toString()+" TOGGLE SNEAK = "+event.isSprinting()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerItemHeld(PlayerItemHeldEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.ITEM_HELD, "Location "+event.getPlayer().getLocation().toString()+" SLOT OLD = "+event.getPreviousSlot()+" SLOT NEW = "+event.getNewSlot()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.CHANGED_WORLD, "Location "+event.getPlayer().getLocation().toString()+" FROM WORLD = "+event.getFrom()));
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItem(PlayerDropItemEvent event){
    	EagleEye.map.put(event.getPlayer().getName(), new PlayerInfo(System.currentTimeMillis(), ListenerFired.DROP_ITEM, "Location "+event.getPlayer().getLocation().toString()+" item dropped = "+event.getItemDrop().toString()));
    }
}
