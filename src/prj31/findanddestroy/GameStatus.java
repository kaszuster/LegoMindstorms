/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prj31.findanddestroy;

/**
 *
 * @author kamil02
 */
public class GameStatus {
    private static GameStatus instance = null;
    private boolean wasHit = false;
    
    private GameStatus() {
	
    }
    
    public static GameStatus getInstance() {
	if (instance == null) {
	    instance = new GameStatus();
	}
	
	return instance;
    }

    public boolean getWasHit() {
	return wasHit;
    }

    public void setWasHit(boolean wasHit) {
	this.wasHit = wasHit;
    }
    
    
}
