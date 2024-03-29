package robos;
import robocode.*;
import java.awt.Color;

public class RoboBase extends AdvancedRobot
{
	public void run() {
	
		setColors(Color.red, Color.black, Color.black,Color.red, Color.green);

		while(true) {
			checaWall();
			turnRadarRight(20);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		double distanciaInimigo = e.getDistance();
		double anguloRadar = getHeading() + e.getBearing() - getRadarHeading();
		
		
         // Gire o radar na direção do inimigo
        setTurnRadarRight(anguloRadar);   
		
		//Se está muito próximo
		if (distanciaInimigo <= 100) {
       		algoritmoAtaquePerto(e.getBearing());
		}
		else if (distanciaInimigo <= 140 && distanciaInimigo > 200) {
       		algoritmoAtaqueDistancia(e.getBearing(), e.getDistance(),e.getVelocity(),8.5);
       
		}
		else if (distanciaInimigo <= 400 && distanciaInimigo > 200) {
       		algoritmoAtaqueDistancia(e.getBearing(), e.getDistance(),e.getVelocity(), 17);
		}
		else if (distanciaInimigo <= 800 && distanciaInimigo > 400) {
       		algoritmoAtaqueDistancia(e.getBearing(), e.getDistance(),e.getVelocity(), 33);
		}
		else if (distanciaInimigo <= 1000 && distanciaInimigo > 800) {
       		algoritmoAtaqueDistancia(e.getBearing(), e.getDistance(),e.getVelocity(), 41);
		}
		
	
	}

	public void onHitByBullet(HitByBulletEvent e) {
		double anguloBullet = e.getBearing();
		if(anguloBullet >= -90 && anguloBullet <=90){
			algoritmoFuga();
		}
	
		
	}
	
	public void onHitWall(HitWallEvent e) {
		checaWall();
	}	
	
	
	public void onHitRobot(HitRobotEvent e){
		System.out.println("Bearing hit: " + e.getBearing());
	
		double anguloEnemy = e.getBearing();
		double anguloCanhao = getHeading() + e.getBearing() - getGunHeading();
	
	}
	
	public void checaWall(){
		double positionX = getX();
		double positionY = getY();
		
		if(positionX < 19 || positionY < 19 || positionX > 781 || positionY > 582 ){
			setTurnRight(180); //Dar meia volta!
		}
		
	}
	
	public void algoritmoFuga(){
		//Estabelece um algoritmo de fuga para situações adversas
		ahead(100);
	}
	
	public void algoritmoAtaqueDistancia(double bearing, double distance, double velocity, double turns){
		double a = Rules.getBulletSpeed(3) * turns;
		double b = velocity * turns;
		double c = distance;
		double c2 = distance*distance;
		double a2 = a*a;
		double b2 = b*b;
		
		double teta = Math.toDegrees(Math.acos((a2+c2-b2)/(2*a*c)));
		
		if (!Double.isNaN(teta)){
			double sum = bearing + teta;
			
			System.out.println("HEADING: " + getHeading());
			setTurnRight(-getHeading());
			setTurnGunRight(-getGunHeading());
			
			System.out.println("GUN: " + getGunHeading());
			System.out.println("BEARING: " + bearing);
			System.out.println("VELOCITY: " + velocity);
			System.out.println("TETA: " + teta);
			System.out.println("Bearing + teta: " + sum);
			
			if (sum > 0) {
				turnGunRight(-getGunHeading());
				turnGunRight(sum);
			}else if (sum < 0 ){
				turnGunRight(-getGunHeading());
				turnGunLeft(-sum);
			}
			fire(1);
		} 
		
		
	}
	
	public void algoritmoAtaquePerto(double bearing){
		double anguloCanhao = getHeading() + bearing - getGunHeading();
		setTurnGunRight(anguloCanhao);
        setFire(3);
	}
}
