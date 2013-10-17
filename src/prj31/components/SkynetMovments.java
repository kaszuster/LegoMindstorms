package prj31.components;

import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;

public class SkynetMovments {
    
    private static SkynetMovments instance = null;
    private NXTRegulatedMotor motor = null;
    private boolean found = false;
    
    private SkynetMovments() {
	motor = new NXTRegulatedMotor(MotorPort.A);
	motor.resetTachoCount();
    }
    
    public static SkynetMovments getInstance() {
	if (instance == null) {
	    instance = new SkynetMovments();
	}
	
	return instance;
    }
    
    private void travelBack() {
	boolean travel = true;
	motor.backward();
	
	while(travel) {
	    if (motor.getTachoCount() <= 0 || DetectDistance.getInstance().getDistance() < 80) {
		motor.stop();
		System.out.println(DetectDistance.getInstance().getDistance() + "");
		travel = false;
		if (DetectDistance.getInstance().getDistance() < 80) {
		    found = true;
		}
	    }
	}
	
	travel = true;
	int goTo = motor.getTachoCount() - 350;
	motor.backward();
	while(travel) {
	    if (motor.getTachoCount() <= 0 || motor.getTachoCount() <= goTo) {
		motor.stop();
		System.out.println(DetectDistance.getInstance().getDistance() + "");
		travel = false;
	    }
	}
    }
    
    private void travelForward() {
	boolean travel = true;
	motor.forward();
	
	while(travel) {
	    
	    if (DetectDistance.getInstance().getDistance() < 80) {
		motor.stop();
		System.out.println(DetectDistance.getInstance().getDistance() + "");
		travel = false;
		found = true;
	    }
	    
	}
	
	travel = true;
	int goTo = motor.getTachoCount() + 250;
	motor.forward();
	while(travel) {
	    if (motor.getTachoCount() >= goTo) {
		motor.stop();
		System.out.println(DetectDistance.getInstance().getDistance() + "");
		travel = false;
	    }
	}
    }
    
    public void findTarget() {
	LCD.clear();
	found = false;
	if (motor.getTachoCount() > 0) {
	    travelBack();
	}
	if (found == false) {
	    travelForward();
	}
    }
    
}
