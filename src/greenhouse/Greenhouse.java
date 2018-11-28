package greenhouse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import greenhouse.controller.HumidityController;
import greenhouse.controller.SprinklerController;
import greenhouse.controller.TemperatureController;

public class Greenhouse implements ActionListener {
	
	private Gui gui;
	private Io io;
	private TemperatureController tc;
	private HumidityController hc;
	private SprinklerController sc;
	
	public Greenhouse() {
		gui = new Gui();
		io = new Io(gui);
		tc = new TemperatureController(gui);
		hc = new HumidityController(gui);
		sc = new SprinklerController(gui);
		init();
	}
	
	private void init() {
		for (int i = 0; i < gui.buttons.length; i++) {
			gui.buttons[i].addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Start")) {
			for (int i = 0; i < gui.inputFields.length; i++) {
				if (gui.inputFields[i].getText().isEmpty())
					gui.inputFields[i].setText("0");
			}
			tc.start();
			hc.start();
			sc.start();
		}
		if (command.equals("Stop")) {
			tc.stop();
			hc.stop();
			sc.stop();
		}
		if (command.equals("Reset")) {
			gui.reset();
		}
		if (command.equals("Save")) {
			io.save();
		}
		if (command.equals("Load")) {
			io.load();
		}
	}
	
	public static void main(String[] args) {
		new Greenhouse();
	}
	
}
