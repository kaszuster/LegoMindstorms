package prj31.interpreter;

import prj31.components.Gun;
import prj31.debug.RemoteConsole;

public class GunInterpreter implements Interpreter {
    
    public ActionCode interpret(ActionCode actionCode) {
	int code = actionCode.getCode();
	if (code == ActionCode.GUN_SHOOT.getCode()) {
	    shoot();
	    return ActionCode.FINISHED;
	}
	
	return ActionCode.UNKNOWN_COMMAND;	    
    }
    
    private void shoot() {
	RemoteConsole.getInstance().print("do: shoot");
	Gun.getInstance().shoot();
    }
    
}
