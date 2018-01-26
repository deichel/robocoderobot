package test;

import robocode.*;

import java.awt.Color;

public class TestBot2 extends AdvancedRobot {

	public void run() {
		// Set colors
		setBodyColor(Color.cyan);
		setGunColor(Color.red);
		setRadarColor(Color.black);
		setScanColor(Color.yellow);
		setBulletColor(Color.yellow);

		// Loop forever
		while (true) {
			
			setAhead(10000);
			setTurnRight(45);
			setMaxVelocity(10);
			turnGunRight(360);
			waitFor(new TurnCompleteCondition(this));
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		smartFire(e.getDistance());
	}

	public void smartFire(double robotDistance) {
		if (robotDistance > 200 || getEnergy() < 15) {
			fire(1);
		} else if (robotDistance > 50) {
			fire(2);
		} else {
			fire(3);
		}
	}
	
	/**
	 * reverseDirection:  Switch from ahead to back & vice versa
	 */
	public void reverseDirection() {
	
			setBack(40000);
	}

	/**
	 * onHitWall:  Handle collision with wall.
	 */
	public void onHitWall(HitWallEvent e) {
		// Bounce off!
		reverseDirection();
	}

	/**
	 * onHitRobot:  Back up!
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If we're moving the other robot, reverse!
		if (e.isMyFault()) {
			reverseDirection();
		}
	}
	
}
