package prj31.components;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class DetectDistance {
    private static DetectDistance instance = null;
    private UltrasonicSensor sonic = null;
    private SensorPort port = SensorPort.S3;
    
    private DetectDistance() {
	sonic = new UltrasonicSensor(port);
	sonic.continuous();
    }
    
    public static DetectDistance getInstance() {
	if (instance == null) {
	    instance = new DetectDistance();
	}
	return instance;
    }
    
    public int getDistance() {
	return sonic.getDistance();
    }
    
}
