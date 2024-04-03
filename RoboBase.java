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
       		algoritmoAtaque(e.getBearing(), e.getHeading(), e.getDistance(),e.getVelocity(),6,3); //Usar Fire(3) e 6 turnos
		}
		else if (distanciaInimigo <= 140 && distanciaInimigo > 200) {
       		algoritmoAtaque(e.getBearing(), e.getHeading(), e.getDistance(),e.getVelocity(),10,2);//Usar Fire(2) e 10 turnos
   
		}
		else if (distanciaInimigo <= 400 && distanciaInimigo > 200) {
       		algoritmoAtaque(e.getBearing(), e.getHeading(), e.getDistance(),e.getVelocity(), 25,2);//Usar Fire(2) e 25 turnos
		}
		else if (distanciaInimigo <= 800 && distanciaInimigo > 400) {
       		algoritmoAtaque(e.getBearing(),e.getHeading(), e.getDistance(),e.getVelocity(), 33,1); //Usar Fire(1) e 33 turnos
		}
		else if (distanciaInimigo <= 1000 && distanciaInimigo > 800) {
       		algoritmoAtaque(e.getBearing(),e.getHeading(),e.getDistance(),e.getVelocity(), 41,1);//Usar Fire(1) e 41 turnos
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
	
	public void algoritmoAtaque(double enemyBearing, double enemyHeading, double distance, double velocity, double turns, double bulletSpeed){
		double a = Rules.getBulletSpeed(bulletSpeed) * turns;
		double b = velocity * turns;
		double c = distance;
		double c2 = distance*distance;
		double a2 = a*a;
		double b2 = b*b;
		
		double teta = Math.toDegrees(Math.acos((a2+c2-b2)/(2*a*c)));
		
		if (!Double.isNaN(teta)){
			
			setTurnGunRight(-getGunHeading()); //Zera o eixo do canhão
			setTurnRight(-getHeading()); //Zera o eixo do tanque
		
			if(enemyBearing < 0 && (enemyHeading > 0  && enemyHeading < 90) || (enemyHeading > 270  && enemyHeading < 360)){ //Na esquerda e tem q subtrair angulos
				double sum = enemyBearing - teta;
				setTurnGunRight(sum);
				fire(bulletSpeed);
				System.out.println("1");
			}
			else if (enemyBearing < 0 && enemyHeading >= 90  && enemyHeading <=270){
				double sum = enemyBearing  + teta;
				setTurnGunRight(sum);
				fire(bulletSpeed);
				System.out.println("2");
			}
			
			else if (enemyBearing > 0 && (enemyHeading > 0  && enemyHeading <= 180)){ //Se está a direita
				double sum = enemyBearing + teta;
				setTurnGunRight(sum);
				fire(bulletSpeed);
				System.out.println("3");
			}
			else if (enemyBearing > 0 && (enemyHeading > 180  && enemyHeading < 360)){ //Se está a direita
				double sum = enemyBearing - teta;
				setTurnGunRight(sum);
				fire(bulletSpeed);
				System.out.println("4");
			}
			
		} 
		
		
	}
	
	public void algoritmoAtaquePerto(double bearing){
		double anguloCanhao = getHeading() + bearing - getGunHeading();
		setTurnGunRight(anguloCanhao);
        setFire(3);
	}
}
