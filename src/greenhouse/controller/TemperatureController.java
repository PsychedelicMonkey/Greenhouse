package greenhouse.controller;

import greenhouse.Gui;

public class TemperatureController extends Controller implements Runnable {
	
	private boolean furnaceOn = false, acOn = false;

	public TemperatureController(Gui gui) {
		super(gui);
	}
	
	public synchronized void start() {
		init(0, 1, 2, 3, 4);
		external = Double.parseDouble(gui.inputFields[15].getText());
		running = true;
		thread = new Thread(this, "TemperatureController");
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
		while (running) {
			gui.outputFields[0].setText(Double.toString(temp));
			if (temp <= min) {
				furnaceOn = true;
				acOn = false;
			}
			if (temp >= max) {
				furnaceOn = false;
				acOn = true;
			}
			if (furnaceOn) {
				gui.outputFields[1].setText("ON");
				gui.outputFields[2].setText("OFF");
				temp += tempRate;
			} else if (acOn) {
				gui.outputFields[1].setText("OFF");
				gui.outputFields[2].setText("ON");
				temp -= tempRate;
			} else {
				gui.outputFields[1].setText("OFF");
				gui.outputFields[2].setText("OFF");
				temp += external;
			}
			try {
				Thread.sleep(sampleRate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
