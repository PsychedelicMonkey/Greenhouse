package greenhouse.controller;

import greenhouse.Gui;

public class HumidityController extends Controller implements Runnable {

	public HumidityController(Gui gui) {
		super(gui);
	}
	
	public synchronized void start() {
		init(5, 6, 7, 8, 9);
		running = true;
		thread = new Thread(this, "HumidityController");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		run(3, 4);
	}

}
