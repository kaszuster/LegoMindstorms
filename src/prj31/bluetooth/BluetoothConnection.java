package prj31.bluetooth;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import lejos.nxt.comm.BTConnection;
import prj31.debug.RemoteConsole;
import prj31.interpreter.ActionCode;
import prj31.interpreter.Interpreter;

public abstract class BluetoothConnection {
    protected BTConnection btc = null;
    protected BufferedOutputStream out = null;
    protected BufferedInputStream in = null;
    
    public void send(int msg) {
	try {
            out.write(msg);
            out.flush();
        } catch (IOException ex) {
        }
    }
    
    private int readCommand() {
	try {
	    return in.read();
	} catch (IOException ex) {
	    return -1;
	}
    }
    
    /**
     * Will wait for incomming command, execute this command according to the 
     * interpreter and respond the interpreters answer till 
     * incomming command is CLOSE_CONNECTION or FINISHED.
     */
    public void waitForCommand() {
	int command = 0;
	int respond = 0;
	//ArrayList params = new ArrayList();
	
	while(command != ActionCode.CLOSE_CONNECTION.getCode()) {
	    command = readCommand();
	    RemoteConsole.getInstance().print("command: " + command);
	    if (command == ActionCode.FINISHED.getCode()) {
		break;
	    }
	    
	    respond = getInterpreter().interpret(command);
	    RemoteConsole.getInstance().print("respond: " + respond);
	    if (respond == ActionCode.DO_NOT_RESPOND.getCode()) {
		break;
	    }
	    send(respond);
	}
    }
    
    public void disconnect() {
	btc.close();
	btc = null;
    }
    
    abstract protected Interpreter getInterpreter();
}
