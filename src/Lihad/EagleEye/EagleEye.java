package Lihad.EagleEye;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import Lihad.EagleEye.Command.CommandRunner;
import Lihad.EagleEye.Listeners.BeyondPlayerListener;



public class EagleEye extends JavaPlugin {
	public static File log = new File("plugins/EagleEye/log.txt");

	public static boolean toggle;
	public static CommandExecutor cmd;
	public static Long lastknown;
	
	public static Map<String,PlayerInfo> map = new HashMap<String, PlayerInfo>();

	private final BeyondPlayerListener playerListener = new BeyondPlayerListener(this);

	public enum ListenerFired{
		INTERACT,JOIN,QUIT,PORTAL,RESPAWN,TOGGLE_SNEAK,TOGGLE_SPRINT,LEVEL_CHANGE,CHANGED_WORLD,EGG_THROW,FISH,KICK,ITEM_BREAK,DROP_ITEM,
		INTERACT_ENTITY,ITEM_HELD,EXP_CHANGE,TELEPORT
	}
	//IMBEDDED CLASSES
	public static class PlayerInfo{
		public Long timestamp;
		public ListenerFired fired;
		public String data;
		
		public Long getTimeStamp(){return timestamp;}
		public ListenerFired getListenerFired(){return fired;}
		public String getStringData(){return data;}

		
		public PlayerInfo(Long t, ListenerFired f, String s){timestamp = t; fired = f; data = s;}
	}
	
	@Override
	public void onDisable() {
		
	}

	@Override
	public void onEnable() {
		toggle = false;
		//FileExist
		try {
			log.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//PluginManager
		PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        
		//CommandManager
		cmd = new CommandRunner(this);
		getCommand("eagle").setExecutor(cmd);
		getCommand("eaglecrash").setExecutor(cmd);

        
        //OutputManager
		System.out.println("[EagleEye] Working behind the scenes");
		
	}

	public void consoleRead(){
		if(toggle){
			System.out.println("[EagleEye] Working behind the scenes");
			toggle = false;
		}
		else{
			System.out.println("[EagleEye] Now printing to Console");
			toggle = true;
		}
	}

}
