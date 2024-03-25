package robos;
import robocode.*;

public class RoboBase extends AdvancedRobot
{

	public void run() {

		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}


	public void onScannedRobot(ScannedRobotEvent e) {
		double enemyDistance = e.getDistance();
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
