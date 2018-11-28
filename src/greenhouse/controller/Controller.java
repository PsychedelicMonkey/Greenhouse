package greenhouse.controller;

import greenhouse.Gui;

public abstract class Controller {
	
	protected Gui gui;
	protected double temp, tempRate, min, max, external;
	protected int sampleRate;
	protected Thread thread;
	protected boolean running = false;
	protected boolean applianceOn = false;
	
	public Controller(Gui gui) {
		this.gui = gui;
	}
	
	protected void init(int tempIndex, int tempRateIndex, int minIndex, int maxIndex, int sampleRateIndex) {
		temp = Double.parseDouble(gui.inputFields[tempIndex].getText());
		tempRate = Double.parseDouble(gui.inputFields[tempRateIndex].getText());
		min = Double.parseDouble(gui.inputFields[minIndex].getText());
		max = Double.parseDouble(gui.inputFields[maxIndex].getText());
		sampleRate = Integer.parseInt(gui.inputFields[sampleRateIndex].getText()) * 1000;
	}
	
	protected void run(int tempIndex, int appIndex) {
		while (running) {
			gui.outputFields[tempIndex].setText(Double.toString(temp));
			if (temp <= min) {
				applianceOn = true;
			}
			if (temp >= max) {
				applianceOn = false;
			}
			if (applianceOn) {
				gui.outputFields[appIndex].setText("ON");
				temp += tempRate;
			} else {
				gui.outputFields[appIndex].setText("OFF");
				temp -= tempRate;
			}
			try {
				Thread.sleep(sampleRate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
