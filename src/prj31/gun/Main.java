package prj31.gun;

import lejos.nxt.LCD;
import prj31.bluetooth.slave.BluetoothSlave;
import prj31.components.Gun;
import prj31.debug.RemoteConsole;
import prj31.interpreter.GunInterpreter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	RemoteConsole remoteConsole = RemoteConsole.getInstance();
	//remoteConsole.activate();
	
	GunInterpreter gunInterpreter = new GunInterpreter();
	
	
	BluetoothSlave bts = new BluetoothSlave(gunInterpreter);

	bts.waitForCommand();

	LCD.clear();
	LCD.drawString("game over", 0, 0);
	LCD.refresh();
	Thread.sleep(2000);
    }
}
