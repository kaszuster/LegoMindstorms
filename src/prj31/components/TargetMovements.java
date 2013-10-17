package prj31.components;

import java.util.Random;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class TargetMovements {
    private static TargetMovements instance = null;
    
    private DifferentialPilot pilot = null;
    private int maxdistance = 200;
    private int overHeadDistance;
    private int actualDistance;
    private int travel;
    private int leftToEdge;
    private boolean forward;
    
    private TargetMovements() {
	forward = true;
	pilot = new DifferentialPilot(6.25f, 2f, Motor.A, Motor.B);
    }
    
    public static TargetMovements getInstance() {
	if (instance == null) {
	    instance = new TargetMovements();
	}
	
	return instance;
    }
    
    private int rand() {
        Random rand = new Random();
        travel = rand.nextInt(150 - 1) + 50;
        return travel;
    }

    public void drive() {
	int distance = rand();
        overHeadDistance = 0;
        if (forward) {
            actualDistance = actualDistance + distance;
            if (actualDistance > maxdistance) {
                overHeadDistance = maxdistance - actualDistance;
                if (overHeadDistance < 0) {
                    forward = false;
                }
                actualDistance = maxdistance + overHeadDistance;
            }
            leftToEdge = distance + overHeadDistance;

        } 
        else
        {
            distance -= (distance * 2); 
            actualDistance = actualDistance + distance; 

            if (actualDistance < 0) { 
                overHeadDistance = 0 - actualDistance; 
                if (overHeadDistance > 0) {
                    forward = true;
                }
                actualDistance = 0 + overHeadDistance;
            }
            leftToEdge = distance + overHeadDistance;
        }
        pilot.travel(leftToEdge);
        pilot.travel(overHeadDistance);
    }
}
