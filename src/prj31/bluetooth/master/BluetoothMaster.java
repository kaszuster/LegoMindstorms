/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prj31.bluetooth.master;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import prj31.bluetooth.BluetoothConnection;
import prj31.interpreter.Interpreter;

public class BluetoothMaster extends BluetoothConnection {
    private String destination = null;
    private Interpreter interpreter = null;

    public BluetoothMaster(String destination, Interpreter interpreter) {
	this.destination = destination;
	this.interpreter = interpreter;
    }
    
    public void connect() throws Exception {
	BTConnection btc = Bluetooth.connect(destination, 1);
	
	if (btc == null) {
            LCD.clear();
	    LCD.drawString("Connection failed", 0, 0);
            LCD.refresh();
            throw new Exception("Could not connect to " + destination);
        } else {
	    LCD.clear();
	    LCD.drawString("Connected to ", 0, 0);
	    LCD.drawString(destination, 0, 1);
            LCD.refresh();
	}
	
	in = new BufferedInputStream(btc.openDataInputStream());
        out = new BufferedOutputStream(btc.openDataOutputStream());
    }
    
    protected Interpreter getInterpreter() {
	return interpreter;
    }
    
}
