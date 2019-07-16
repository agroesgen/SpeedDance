import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SpeedWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField txHerren, txDamen;
	JTextArea paarAusgabe;
	Random rng = new Random();
	int maxAnz;
	
	public SpeedWindow(ActionListener listener) {
		setTitle("Speed Dance");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// Eingabe Panel
		JPanel eingabeP = new JPanel(new GridLayout(2,1,0,10));
		
		
			// Anzahl Panel
		JPanel anzahlP = new JPanel(new GridLayout(2,1));
		JPanel herrenP = new JPanel(new GridLayout(1,2));
		JPanel damenP = new JPanel(new GridLayout(1,2));
		
		JLabel hAnz = new JLabel("Anzahl Herren: ");
		txHerren = new JTextField();
		herrenP.add(hAnz);
		herrenP.add(txHerren);
		JLabel dAnz = new JLabel("Anzahl Damen: ");
		txDamen = new JTextField();
		damenP.add(dAnz);
		damenP.add(txDamen);
		
		// Button Panel
		JPanel buttonP = new JPanel(new BorderLayout());
		JButton ordner = new JButton("Zuordnen");
		ordner.setPreferredSize(new Dimension(50,50));
		buttonP.add(ordner);
		ordner.addActionListener(listener);
		ordner.setActionCommand("Zuordnen");
		
		// Eingabe Panel Aufteilung
		eingabeP.add(anzahlP);
		eingabeP.add(buttonP);
		// Anzahl Panel Aufteilung
		anzahlP.add(herrenP);
		anzahlP.add(damenP);
		
		
		// Ausgabe Panel
		JPanel ausgabeP = new JPanel(new BorderLayout(0,20));
		JLabel ausgabe = new JLabel("Ausgabe Panel");
		ausgabeP.add(ausgabe, BorderLayout.NORTH);
		paarAusgabe = new JTextArea();
		paarAusgabe.setEditable(false);
		
		JScrollPane paarAusgabeP = new JScrollPane(paarAusgabe);
		ausgabeP.add(paarAusgabeP);
		
		// Toplevel Layout		
		JPanel gesamtP = new JPanel(new GridLayout(1,2,20,0));
		gesamtP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(gesamtP);		
		
		gesamtP.add(eingabeP);
		gesamtP.add(ausgabeP);
		
		pack();
		setSize(600,300);
	}
	
	public int[] readText() {
		int[] x;
		String herrenText, damenText;
		
		herrenText = txHerren.getText();
		damenText = txDamen.getText();
		
		x = new int[2];
		x[0]= Integer.parseInt(herrenText);
		x[1]= Integer.parseInt(damenText);
		
		return x;
	}
	


	

	
	int rundenZaehler=0;
	BackendLogic cooleLogik = new BackendLogic();
	public void printPairs() {

		String finalAusgabe = "";
		 int anzahlMaenner = readText()[0];
		 int anzahlFrauen = readText()[1];
		 cooleLogik.setAnzahlFrauen(anzahlFrauen);
		 cooleLogik.setAnzahlMaenner(anzahlMaenner);
		 if (rundenZaehler==0) {
			 cooleLogik.setMoeglichePaare();
			 rundenZaehler+=1;
		 }
		
		 
		
		
		finalAusgabe= cooleLogik.starteMatching();
		
		paarAusgabe.setText(finalAusgabe);
	}
}
