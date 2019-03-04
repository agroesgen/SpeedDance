import java.util.LinkedList;
import java.util.List;

public class BackendLogic {
	
	private int anzahlMaenner;
	private int anzahlFrauen;
	 
	
	
	public void setAnzahlMaenner(int anzahlMaenner) {
		this.anzahlMaenner=anzahlMaenner;
		
	}


	public void setAnzahlFrauen(int anzahlFrauen) {
		this.anzahlFrauen= anzahlFrauen;
	}
	
	
	/*Speichert die bisher schon gemachen Paare ausgehend von
		der kleineren Anzahl an Leuten*/
    LinkedList<LinkedList<Integer>> speicherVonPaaren;
	LinkedList<Integer> partnerinnen;
	
	public void initialisiereSpeicher () {
		System.out.println("speicher wurde initialisiert");
		speicherVonPaaren = new LinkedList<LinkedList<Integer>>();
		for (int i=0;i<anzahlMaenner;i++) {
			partnerinnen = new LinkedList<Integer>();
			speicherVonPaaren.add(i, partnerinnen);
		}
	}
	
	
	public String starteMatching () {
		String aktuellePaare="";
		LinkedList<Integer> bereitsGewaehlteFrauen = new LinkedList<Integer>();
		for (int i=0;i<speicherVonPaaren.size();i++) {
			LinkedList<Integer> bereitsGematchtePaare=speicherVonPaaren.get(i);
			int randomFrau = (int) ((Math.random()*100)%anzahlFrauen)+1;
			while(bereitsGematchtePaare.contains(randomFrau) || bereitsGewaehlteFrauen.contains(randomFrau)) {
				 randomFrau = (int) ((Math.random()*100)%anzahlFrauen)+1;
			}
			bereitsGewaehlteFrauen.add(randomFrau);
			speicherVonPaaren.get(i).add(randomFrau);
			
			aktuellePaare += ""+(i+1)+" " + randomFrau + " \n";
		}
		return aktuellePaare;
	}


	
}
