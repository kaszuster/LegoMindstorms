package prj31.target;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import prj31.bluetooth.slave.BluetoothSlave;
import prj31.debug.RemoteConsole;
import prj31.interpreter.TargetInterpreter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
	TargetInterpreter interpreter = new TargetInterpreter();
	RemoteConsole remoteConsole = RemoteConsole.getInstance();
	//remoteConsole.activate();
	
	TargetInterpreter targetInterpreter = new TargetInterpreter();
        BluetoothSlave bts = new BluetoothSlave(targetInterpreter);
	
	bts.waitForCommand();
	
	LCD.clear();
	LCD.drawString("game over", 0, 0);
        LCD.refresh();
	Thread.sleep(2000);
    }
}
