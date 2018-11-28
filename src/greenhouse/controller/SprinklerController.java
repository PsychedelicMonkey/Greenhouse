package greenhouse.controller;

import greenhouse.Gui;

public class SprinklerController extends Controller implements Runnable {

	public SprinklerController(Gui gui) {
		super(gui);
	}
	
	public synchronized void start() {
		init(10, 11, 12, 13, 14);
		running = true;
		thread = new Thread(this, "SprinklerController");
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
		run(5, 6);
	}

}
