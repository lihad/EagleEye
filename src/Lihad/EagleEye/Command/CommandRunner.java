package Lihad.EagleEye.Command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import Lihad.EagleEye.EagleEye;


public class CommandRunner implements CommandExecutor {
	public static EagleEye plugin;

	public CommandRunner(EagleEye instance){
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string,
			String[] arg) {
		if(cmd.getName().equalsIgnoreCase("eagle") && (sender instanceof ConsoleCommandSender)){
			plugin.consoleRead();
			return true;
		}else if(cmd.getName().equalsIgnoreCase("eaglecrash") && (sender instanceof ConsoleCommandSender)){
			System.out.println("Printing Crash Log.....");
			File crash = new File("plugins/EagleEye/crash_"+System.currentTimeMillis()+".txt");
			try {
				crash.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Long max =  0L;
			for(int i = 0;i<EagleEye.map.size();i++){
				Long l = EagleEye.map.get(EagleEye.map.keySet().toArray()[i]).getTimeStamp();
				if(l > max) max = l;
			}
			for(int i = 0;i<EagleEye.map.size();i++){
				Long l = EagleEye.map.get(EagleEye.map.keySet().toArray()[i]).getTimeStamp();
				String s = EagleEye.map.get(EagleEye.map.keySet().toArray()[i]).getListenerFired().toString();
				String s1 = EagleEye.map.get(EagleEye.map.keySet().toArray()[i]).getStringData();
				String s0 = "";
				if(max - l < 50) s0 = " ---> ";
				String outputline = s0+"TIMESTAMP ["+l.toString()+"] -- PLAYER "+EagleEye.map.keySet().toArray()[i].toString()+" FIRED BY ["+s+"] "+s1;
				BufferedWriter output;
				try {
					output = new BufferedWriter(new FileWriter(crash, true));
					output.newLine();
					output.write(outputline);
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Crash Log Finished");
			return true;
		} else return false;

	}
}
