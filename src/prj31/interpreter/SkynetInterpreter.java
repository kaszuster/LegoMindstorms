package prj31.interpreter;

import prj31.findanddestroy.GameStatus;

public class SkynetInterpreter implements Interpreter {
    
    public ActionCode interpret(ActionCode actionCode) {
	int code = actionCode.getCode();
	if (code == ActionCode.TARGET_WAS_HIT.getCode()) {
	    targetWasHit();
	    return ActionCode.DO_NOT_RESPOND;
	} else if (code == ActionCode.TARGET_WAS_NOT_HIT.getCode()) {
	    targetWasNotHit();
	    return ActionCode.DO_NOT_RESPOND;
	}
	
	return ActionCode.UNKNOWN_COMMAND;	    
    }
    
    private void targetWasHit() {
	GameStatus.getInstance().setWasHit(true);
    }
    
    private void targetWasNotHit() {
	GameStatus.getInstance().setWasHit(false);
    }
}
