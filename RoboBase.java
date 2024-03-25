package robos;
import robocode.*;
import java.awt.Color;

public class RoboBase extends AdvancedRobot
{
	public void run() {
		setColors(Color.red, Color.black, Color.black,Color.red, Color.green);
		
		while(true) {
			//turnRadarRightRadians(Math.PI / 4);
			turnGunRight(360);
			
			

		}
	}


	public void onScannedRobot(ScannedRobotEvent e) {
		double enemyDistance = e.getDistance();
		double enemyBearing = e.getBearing();
		
			System.out.println(enemyDistance);
			System.out.println(enemyBearing);
		if (enemyDistance < 135) {
			fire(3);
		}else{
			fire(1);
		}
	}


	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	

	public void onHitWall(HitWallEvent e) {
		back(20);
	}	
}
