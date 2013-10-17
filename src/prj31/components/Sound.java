/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prj31.components;

import java.io.IOException;
import lejos.nxt.remote.NXTCommand;




public class Sound {	
	private static final NXTCommand nxtCommand = NXTCommand.getSingleton();
	
	
	private Sound() {}
	

	public static byte playSoundFile(String fileName, boolean repeat) {
		try {
			return nxtCommand.playSoundFile(fileName, repeat);
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			return -1;
		}
	}
	

	public static byte playSoundFile(String fileName) {
		return Sound.playSoundFile(fileName, false);
	}

}