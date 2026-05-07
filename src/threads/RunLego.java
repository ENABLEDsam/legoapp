package threads;


import data.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class RunLego implements Runnable{
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

	EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
    float[] colorData = new float[colorSensor.getRGBMode().sampleSize()];

	EV3UltrasonicSensor usSensor = new EV3UltrasonicSensor(SensorPort.S2);
    SampleProvider distanceSample = usSensor.getDistanceMode();
    float[] distanceData = new float[distanceSample.sampleSize()];

	float colorMult = 10;
	float colorTreshold = 0;
	float distanceTreshold = 0.20f;

	@Override
	public void run() {

		

		// TODO Auto-generated method stub
		while (Robot.getRun()==1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			colorSensor.getRGBMode().fetchSample(colorData, 0);
        	float red = colorData[0] * colorMult;
        	float green = colorData[1] * colorMult;
        	float blue = colorData[2] * colorMult;

			distanceSample.fetchSample(distanceData, 0);
        	float distance = distanceData[0];

			// Simple line detection
			// Motors stop if a black line is detected

			if (red < colorTreshold & blue < colorTreshold & green < colorTreshold) { // black = less than 0 in all colours
				motorA.stop();
				motorB.stop();
				continue;
			}

			// Simple distance detection
			// Motors stop if an object is detected within 10 cm

			if (distance < distanceTreshold) { // 10 cm
				motorA.stop();
				motorB.stop();
				continue;
			}

			motorA.setPower(Robot.turnRight());
			motorB.setPower(Robot.turnLeft());
		}
	}

}
