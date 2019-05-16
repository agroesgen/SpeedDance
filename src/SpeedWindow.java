import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	int [][] pairMemory, activePairs ;
	static List<Paar> Memory = new ArrayList<>();
	int maxAnz;
	int [] menArray, womenArray;
	
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
		System.out.println(ordner.getActionCommand());
		
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
	
	public void start() {
		createPairs();
	}
	
	public void readEingabe() {
		int menEingabe = readText()[0];
		int womenEingabe = readText()[1];
		
		// Anzahl der Herren im Array
		menArray = new int[menEingabe];
		for (int i= 1; i<= readText()[0]; i++) {
			menArray[i-1] = i;
		}
		// Anzahl der Damen im Array
		womenArray = new int[womenEingabe];
		for (int i= 1; i<= readText()[1]; i++) {
			womenArray[i-1] = i;
		}
		maxAnz = Math.min(menArray.length, womenArray.length);		
		activePairs = new int[maxAnz][2];
	}
	public void createPairs() {
			
		readEingabe();
		for (int i = Memory.size(); i<maxAnz; i++) { // Paar Index
				createPaar(i);
			System.out.println(Arrays.toString(Memory.get(i).getPair()));
			while (vorhandenesPaar(Memory.get(i))==true) {
				Memory.remove(i);
				createPaar(i);
			}
			
		}
		printPairs(activePairs);
	}
		
	public static boolean personVergeben(int gender, int clone, int[][] Array) {
		
		boolean bool= true;
			for (int i[] : Array) {
				System.out.println("Vorhandene Zahl Array Wert: "+ i[gender]);
				if (clone == i[gender]) {
					bool = true;
					break;
				}
				else
					bool = false;
					System.out.println(bool);
			}
			return bool;
	}
	public void createPaar(int paarIndex){
		
		int rndMan = menArray[rng.nextInt(menArray.length)];
		int rndWoman = womenArray[rng.nextInt(womenArray.length)];
		
		while (personVergeben(0, rndMan,activePairs)==true) {
			rndMan = menArray[rng.nextInt(menArray.length)];
		}
		activePairs[paarIndex][0]=rndMan;
		
		while (personVergeben(1, rndWoman,activePairs)==true) {
		rndWoman = womenArray[rng.nextInt(womenArray.length)];			
		}
		activePairs[paarIndex][1]=rndWoman;
		
		Memory.add(new Paar(rndMan,rndWoman));
	}
	public static boolean vorhandenesPaar(Paar testPaar) {
		boolean bool = false;
		for (Paar clone : Memory)
			if (clone.getPair() == testPaar.getPair()) {
				bool = true;
				return bool;
			}
			else
				bool = false;
		return bool;
	}
	public void printPairs(int [][] paarListe) {
		String FinalAusgabe = "";
		for (int i=0; i<paarListe.length; i++) {
			FinalAusgabe = FinalAusgabe + "\n" + paarListe[i][0] + "    " + paarListe[i][1];
			System.out.println(paarListe[i][0] + "  " + paarListe[i][1]);
		}
		FinalAusgabe = "Paare: " + FinalAusgabe;
		paarAusgabe.setText(FinalAusgabe);
	}
}
