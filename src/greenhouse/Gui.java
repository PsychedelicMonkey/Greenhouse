package greenhouse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private String[] buttonNames = { "Start", "Stop", "Reset", "Save", "Load" };
	public JButton[] buttons = new JButton[buttonNames.length];
	
	private String[] outputNames = { "Temperature", "Furnace", "A.C", "Humidity", "Humidifier", "Soil Moisture", "Sprinklers" };
	public JTextField[] outputFields = new JTextField[outputNames.length];
	
	private String[] inputNames = { "Temperature:", "Rate of Change:", "Minimum:", "Maximum:", "Sample Rate (s):",
			"Humidity:", "Rate of Change:", "Minimum:", "Maximum:", "Sample Rate (s):",
			"Sprinkler:", "Rate of Change:", "Minimum:", "Maximum:", "Sample Rate (s):" };
	public JTextField[] inputFields = new JTextField[16];
	
	public Gui() {
		JPanel outputP = new JPanel();
		outputP.setLayout(new BoxLayout(outputP, BoxLayout.Y_AXIS));
		for (int i = 0; i < outputFields.length; i++) {
			JLabel label = new JLabel(outputNames[i]);
			outputFields[i] = new JTextField(5);
			outputFields[i].setEditable(false);
			label.setLabelFor(outputFields[i]);
			outputP.add(label);
			outputP.add(outputFields[i]);
		}
		
		JPanel inputP = new JPanel();
		inputP.setLayout(new BoxLayout(inputP, BoxLayout.Y_AXIS));
		String[] panelNames = { "Temperature", "Humidity", "Soil Moisture" };
		JPanel[] panels = new JPanel[panelNames.length];
		int index = 0;
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBorder(BorderFactory.createTitledBorder(panelNames[i]));
			panels[i].setLayout(new FlowLayout());
			for (int j = 0; j < 5; j++) {
				JLabel label = new JLabel(inputNames[index]);
				inputFields[index] = new JTextField(5);
				label.setLabelFor(inputFields[index]);
				panels[i].add(label);
				panels[i].add(inputFields[index]);
				index++;
			}
			inputP.add(panels[i]);
		}
		JPanel externalP = new JPanel();
		externalP.setBorder(BorderFactory.createTitledBorder("External Factors"));
		externalP.setLayout(new FlowLayout());
		externalP.add(new JLabel("External Factors:"));
		inputFields[15] = new JTextField(5);
		externalP.add(inputFields[15]);
		inputP.add(externalP);
		
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputP, outputP);
		getContentPane().add(jsp, BorderLayout.CENTER);
		
		JPanel buttonP = new JPanel();
		buttonP.setLayout(new FlowLayout());
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			buttonP.add(buttons[i]);
		}
		getContentPane().add(buttonP, BorderLayout.SOUTH);
		
		reset();
		
		setSize(1000, 600);
		setTitle("Greenhouse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void reset() {
		for (int i = 0; i < inputFields.length; i++) {
			inputFields[i].setText("0");
		}
		for (int i = 0; i < outputFields.length; i++) {
			if (i == 0 || i == 3 || i == 5)
				outputFields[i].setText("0.0");
			else
				outputFields[i].setText("OFF");
		}
	}

}
