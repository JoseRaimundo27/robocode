package robos;
import robocode.*;
import java.awt.Color;

public class RoboBase extends AdvancedRobot
{
	public void run() {
		setColors(Color.red, Color.black, Color.black,Color.red, Color.green);
		setScanColor(Color.green);
		while(true) {
			turnRadarRight(20);

		}
	}


	public void onScannedRobot(ScannedRobotEvent e) {
		double enemyDistance = e.getDistance();
		
		//Se está muito próximo
		if (enemyDistance < 800) {
			
			// Calcule a quantidade de graus necessária para virar o canhão em direção ao inimigo
       		 double angleToEnemy = getHeading() + e.getBearing() - getGunHeading();
        
        // Gire o canhão na direção do inimigo
        	setTurnGunRight(angleToEnemy);
			fire(1);
			System.out.println("Bearing: "+ e.getBearing());
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
