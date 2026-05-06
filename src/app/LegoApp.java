package app;

import threads.*;

public class LegoApp {

	public static void main(String[] args) {
		RunLego runLego=new RunLego();
		ReadData readData=new ReadData();
		SendData sendData=new SendData();

		System.out.println("Run in Threads");

		Thread runLegoThread=new Thread(runLego);
		Thread readDataThread=new Thread(readData);
		Thread sendDataThread=new Thread(sendData);
		runLegoThread.start(); //Saikeen kaynnistys
		readDataThread.start(); //Virtuaalikone aloittaa saikeen kun ehtii
		sendDataThread.start(); //Virtuaalikone aloittaa saikeen kun ehtii
	}
}
