package robos;
import robocode.*;
import java.awt.Color;

public class RoboBase extends AdvancedRobot
{
	public void run() {
	
		setColors(Color.red, Color.black, Color.black,Color.red, Color.green);

		while(true) {
		//System.out.println("Height:" + getBattleFieldHeight());
		//System.out.println("Width:" + getBattleFieldWidth());
		//System.out.println("X:" + getX());
	//	System.out.println("Y:" + getY());
			turnRadarRight(20);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		double distanciaInimigo = e.getDistance();
		double anguloRadar = getHeading() + e.getBearing() - getRadarHeading();
		double anguloCanhao = getHeading() + e.getBearing() - getGunHeading();

		//Gira o robô em direção ao inimigo
		setTurnRight(e.getBearing());
		
         // Gire o radar na direção do inimigo
        setTurnRadarRight(anguloRadar);   
		
		ahead(100);
		//Se está muito próximo
		if (distanciaInimigo <= 80) {
       		algoritmoAtaquePerto(anguloCanhao);
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
		double anguloBullet = e.getBearing();
		//System.out.println("Bearing bullet: " + e.getBearing());
		if(anguloBullet >= -90 && anguloBullet <=90){
			algoritmoFuga();
		}
		//else{
		//}
		
	}
	

	public void onHitWall(HitWallEvent e) {
		checaWall();
	}	
	
	
	public void onHitRobot(HitRobotEvent e){
		System.out.println("Bearing hit: " + e.getBearing());
	
		double anguloEnemy = e.getBearing();
		double anguloCanhao = getHeading() + e.getBearing() - getGunHeading();
		if(anguloEnemy >= -90 && anguloEnemy <=90){
			algoritmoAtaquePerto(anguloCanhao);
		}else{
			algoritmoFuga();
		}
	}
	
	public void checaWall(){
		//Checar se os valores estão na faixa permitida para não chocar wall
	}
	
	public void algoritmoFuga(){
		//Estabelece um algoritmo de fuga para situações adversas
		ahead(100);
	}
	
	public void algoritmoAtaqueDistancia(){
		
	}
	
	public void algoritmoAtaquePerto(double anguloCanhao){
		setTurnGunRight(anguloCanhao);
        fire(3);
	}
}
