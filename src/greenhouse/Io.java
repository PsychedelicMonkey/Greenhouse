package greenhouse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Io {
	
	private Gui gui;
	private String filename = "save_file.txt";
	
	public Io(Gui gui) {
		this.gui = gui;
	}
	
	public void save() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(filename));
			for (int i = 0; i < gui.inputFields.length; i++) {
				if (gui.inputFields[i].getText().isEmpty())
					gui.inputFields[i].setText("0");
				out.println(gui.inputFields[i].getText());
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line = "";
			int index = 0;
			while ((line = in.readLine()) != null) {
				gui.inputFields[index].setText(line);
				index++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
