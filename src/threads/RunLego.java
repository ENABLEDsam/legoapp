package threads;


import data.*;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class RunLego implements Runnable{
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (Robot.getRun()==1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Simple line detection
			// Motors stop if a black line is detected
			int color = Robot.getColor();

			if (color == 7) { // color7 = black
				motorA.stop();
				motorB.stop();
				continue;
			}

			// Simple distance detection
			// Motors stop if an object is detected within 10 cm
			int distance = Robot.getDistance();

			if (distance < 10) { // 10 cm
				motorA.stop();
				motorB.stop();
				continue;
			}

			motorA.setPower(Robot.turnRight());
			motorB.setPower(Robot.turnLeft());
		}
	}

}
