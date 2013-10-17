/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prj31.components;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 *
 * @author Slawa
 */
public class Gun {

    private DifferentialPilot pilot = null;
    private TouchSensor button = null;
    private static Gun instance = null;

    private Gun() {
	pilot = new DifferentialPilot(2.25f, 5.5f, Motor.A, Motor.B);
	pilot.setTravelSpeed(1000);

	button = new TouchSensor(SensorPort.S1);
    }

    public static Gun getInstance() {
	if (instance == null) {
	    instance = new Gun();
	}
	return instance;
    }

    public void shoot() {
	waitForButtonPress();
	pilot.travel(-8);
    }

    private void waitForButtonPress() {
	LCD.clear();
	LCD.drawString("Press to shoot", 0, 0);
	LCD.refresh();

	while (true) {
	    if (button.isPressed()) {
		break;
	    }
	}

	LCD.clear();
	LCD.drawString("!!! SHOOT !!!", 0, 0);
	LCD.refresh();
    }

    public void adjust(int tachoCount) {
	Motor.C.setSpeed(50);

	if (tachoCount <= Motor.C.getTachoCount()) {
	    Motor.C.backward();
	    while (tachoCount <= Motor.C.getTachoCount()) {
	    }
	} else if (tachoCount >= Motor.C.getTachoCount()) {
	    Motor.C.forward();
	    while (tachoCount >= Motor.C.getTachoCount()) {
	    }
	} else {
	    LCD.clear();
	    LCD.drawString("problem in gun.adjust", 0, 0);
	    LCD.refresh();
	}
	
	Motor.C.stop();
    }
}
