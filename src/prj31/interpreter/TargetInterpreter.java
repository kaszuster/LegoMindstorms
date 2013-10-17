package prj31.interpreter;

import lejos.nxt.Sound;
import prj31.components.DetectHit;
import prj31.components.TargetMovements;
import prj31.debug.RemoteConsole;

public class TargetInterpreter implements Interpreter {
    
    public ActionCode interpret(ActionCode actionCode) {
	int code = actionCode.getCode();
	if (code == ActionCode.TARGET_MOVE.getCode()) {
	    move();
	    return ActionCode.FINISHED;
	} else if (code == ActionCode.TARGET_CHECK_HIT.getCode()) {
	    return checkForHit();
	}
	
	return ActionCode.UNKNOWN_COMMAND;	    
    }
    
    private void move() {
	RemoteConsole.getInstance().print("do: move");
	
	TargetMovements.getInstance().drive();
    }
    
    private ActionCode checkForHit() {
	RemoteConsole.getInstance().print("do: check hit");
	
	if (DetectHit.getInstance().wasHit()) {
	    Sound.beep();
	    return ActionCode.TARGET_WAS_HIT;
	} else {
	    return ActionCode.TARGET_WAS_NOT_HIT;
	}
    }
    
}
