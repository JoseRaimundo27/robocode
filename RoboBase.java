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
		double distanciaInimigo = e.getDistance();
		double anguloRadar = getHeading() + e.getBearing() - getRadarHeading();
		double anguloCanhao = getHeading() + e.getBearing() - getGunHeading();
		
		//Gira o robô em direção ao inimigo
		setTurnRight(e.getBearing());
		ahead(100);
		
         // Gire o radar na direção do inimigo
        setTurnRadarRight(anguloRadar);
        
        // Gire o canhão na direção do inimigo
        setTurnGunRight(anguloCanhao);
		
		//Se está muito próximo
		if (distanciaInimigo <= 80) {
       		setTurnGunRight(anguloCanhao);
        	fire(3);
		}
		else if (distanciaInimigo <= 200 && distanciaInimigo > 80) {
       		setTurnGunRight(anguloCanhao);
        	fire(2);
		}
		else if (distanciaInimigo <= 500 && distanciaInimigo > 200) {
       		setTurnGunRight(anguloCanhao);
        	fire(1.5);
		}
		else if (distanciaInimigo <= 1000 && distanciaInimigo > 500) {
       		setTurnGunRight(anguloCanhao);
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
