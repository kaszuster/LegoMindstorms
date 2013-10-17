/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prj31.components;

import lejos.nxt.LCD;

/**
 *
 * @author fr4g0r
 */
public class DisplayCount {

    private int targetPoints;
    private int skyNetPoints;

    public DisplayCount(int targetPoints, int skyNetPoints) {
	this.targetPoints = targetPoints;
	this.skyNetPoints = skyNetPoints;
    }

    public void SkyNetPoints() {
	skyNetPoints++;
    }

    public void TargetPoints() {
	targetPoints++;
    }

    public void drawDisplay() {
	LCD.setAutoRefresh(true);
	LCD.drawString("*******Point********", 0, 0);
	LCD.drawString("**----------------**", 0, 1);
	LCD.drawString("**Target-||-SkyNet**", 0, 2);
	LCD.drawString("**-------||-------**", 0, 3);
	LCD.drawString("**" + targetPoints + "||" + skyNetPoints + "**", 0, 4);
	LCD.drawString("**-------||-------**", 0, 5);
	LCD.drawString("********************", 0, 6);
    }
}
