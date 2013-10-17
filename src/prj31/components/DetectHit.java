package prj31.components;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

//Singleton Pattern is used

public class DetectHit {
    private static DetectHit instance = null;
    private SensorPort port = SensorPort.S1;
    private LightSensor lightSensor = null;
    
    private DetectHit() {
	lightSensor = new LightSensor(port, true);
    }
    
    public static DetectHit getInstance() {
	if (instance == null) {
	    instance = new DetectHit();
	}
	
	return instance;
    }
    
    public boolean wasHit() {
	LCD.drawString(lightSensor.getLightValue() + "", 0, 0);
	return lightSensor.getLightValue() < 37;
    }
}
