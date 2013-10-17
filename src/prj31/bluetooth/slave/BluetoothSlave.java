package prj31.bluetooth.slave;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import prj31.bluetooth.BluetoothConnection;
import prj31.interpreter.Interpreter;

public class BluetoothSlave extends BluetoothConnection {
    private Interpreter interpreter = null;
    
    public BluetoothSlave(Interpreter interpreter) {
	this.interpreter = interpreter;
	waitForConnection();
    }

    private void waitForConnection() {
	LCD.clear();
	LCD.drawString("Waiting for connection", 0, 0);
        LCD.refresh();
	
	btc = Bluetooth.waitForConnection();

	LCD.clear();
        LCD.drawString("Connected", 0, 0);
        LCD.refresh();

        in = new BufferedInputStream(btc.openDataInputStream());
        out = new BufferedOutputStream(btc.openDataOutputStream());
    }
    
    protected Interpreter getInterpreter() {
	return interpreter;
    }
    
}
