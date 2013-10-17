package prj31.skynet;

import lejos.nxt.*;
import prj31.findanddestroy.FindAndDestroy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
	// do all the connection stuff
	FindAndDestroy findAndDestroy = new FindAndDestroy();
	
	LCD.clear();
	LCD.drawString("initialise game", 0, 0);
	LCD.drawString("place skynet and", 0, 1);
	LCD.drawString("target at start-", 0, 2);
	LCD.drawString("position to ", 0, 3);
	LCD.drawString("calibrate gun", 0, 4);
	LCD.drawString("press to cont.", 0, 5);
        LCD.refresh();
	Button.waitForPress();
	LCD.clear();
	
	findAndDestroy.initialiseGame();
	
	LCD.clear();
	LCD.drawString("initialise game", 0, 0);
	LCD.drawString("done", 0, 1);
	LCD.drawString("press to start", 0, 2);
        LCD.refresh();
	Button.waitForPress();
	
	findAndDestroy.startGame();
	
	LCD.clear();
	LCD.drawString("game over", 0, 0);
        LCD.refresh();
	Thread.sleep(2000);
    }
    
    
}
