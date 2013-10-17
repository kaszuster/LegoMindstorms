package prj31.debug;

import lejos.nxt.comm.RConsole;

public class RemoteConsole {
    private static RemoteConsole instance = null;
    private boolean active = false;

    public static RemoteConsole getInstance() {
	if (instance == null) {
	    instance = new RemoteConsole();
	}
	
	return instance;
    }
    
    private RemoteConsole() {
    }
    
    public void activate() {
	if (active == false) {
	    RConsole.openUSB(40000);
	    active = true;
	}
    }
    
    public void print(String string) {
	if (active == true) {
	    RConsole.println(string);
	}
    }
}
