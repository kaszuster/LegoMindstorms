package prj31.findanddestroy;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import prj31.bluetooth.master.BluetoothMaster;
import prj31.components.DetectDistance;
import prj31.components.Gun;
import prj31.components.SkynetMovments;
import prj31.interpreter.ActionCode;
import prj31.interpreter.SkynetInterpreter;

public class FindAndDestroy {

    private final String idGun = "00165311B0A8";
    private final String idTarget = "00165301F545";
    public BluetoothMaster btGun = null;
    public BluetoothMaster btTarget = null;

    public FindAndDestroy() {
	btGun = initBtMaster(idGun);
	btTarget = initBtMaster(idTarget);
    }

    private BluetoothMaster initBtMaster(String connectTo) {
	SkynetInterpreter skynetInterpreter = new SkynetInterpreter();
	BluetoothMaster btm = new BluetoothMaster(connectTo, skynetInterpreter);

	LCD.clear();
	LCD.drawString("press to connect", 0, 0);
	LCD.drawString("to " + connectTo, 0, 1);
	LCD.refresh();

	Button.waitForPress();
	try {
	    btm.connect();
	} catch (Exception ex) {
	    LCD.clear();
	    LCD.drawString("cant connect to " + connectTo, 0, 0);
	    LCD.refresh();
	}

	return btm;
    }

    public void initialiseGame() {
	calibrateGun();
    }

    public void startGame() {
	//TODO bedingung wieviele runden gespielt werden
	for (int a = 0; a < 3; a++) {
	    btTarget.send(ActionCode.TARGET_MOVE.getCode());
	    btTarget.waitForCommand();

	    findTarget();

	    //TODO 3 schuss, aber nur solange kein hit gesendet wurde
	    for (int i = 0; i < 3; i++) {
		btGun.send(ActionCode.GUN_SHOOT.getCode());
		btGun.waitForCommand();

		btTarget.send(ActionCode.TARGET_CHECK_HIT.getCode());
		btTarget.waitForCommand();
		//wenn target getroffen rufe methode SkynetPoints auf. sonst TargetPoints aufrufen und Display ausgeben
		
		if (GameStatus.getInstance().getWasHit()) {
		    break;
		}
	    }
	}
    }

    private void calibrateGun() {
	//TODO waffe anhand der entfernung ausrichten
	int distance = 0;
	while (true) {
	    distance = DetectDistance.getInstance().getDistance();

	    if (distance < 60 || distance > 90) {
		LCD.clear();
		LCD.drawString("Distance invalid!", 0, 0);
		LCD.drawString("press to try again", 0, 1);
		LCD.refresh();
		Button.waitForPress();
	    } else {
		break;
	    }
	}

	if (distance >= 60 && distance <= 71) {
	    Gun.getInstance().adjust(-95);
	} else if (distance >= 72 && distance <= 81) {
	    Gun.getInstance().adjust(-118);
	} else if (distance >= 82 && distance <= 89) {
	    Gun.getInstance().adjust(-166);
	}

    }

    private void findTarget() {
	SkynetMovments.getInstance().findTarget();
	calibrateGun();
    }
}
