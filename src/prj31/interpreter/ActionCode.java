package prj31.interpreter;

public enum ActionCode {
    CLOSE_CONNECTION(-1),
    UNKNOWN_COMMAND(1),
    DO_NOT_RESPOND(2),
    FINISHED(3),
    
    GUN_SHOOT(4),
    
    TARGET_MOVE(5),
    TARGET_CHECK_HIT(6),
    TARGET_WAS_HIT(7),
    TARGET_WAS_NOT_HIT(8);
    
    private final int code;
    
    ActionCode(int code) {
        this.code = code;
    }
    
    public int getCode() {
	return code;
    }
}
