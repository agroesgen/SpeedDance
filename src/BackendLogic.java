import java.util.LinkedList;

public class BackendLogic {
	
	private int anzahlMaenner;
	private int anzahlFrauen;
	private int maxKombinationen; 
	private int shuffleCounter;
	private int kleinereMenge;
	
	LinkedList<LinkedList<Integer>> moeglichePaare =new LinkedList<LinkedList<Integer>>();

	
	public void setAnzahlMaenner(int anzahlMaenner) {
		this.anzahlMaenner=anzahlMaenner;
		
	}


	public void setAnzahlFrauen(int anzahlFrauen) {
		this.anzahlFrauen= anzahlFrauen;
	}
	
	
	
	void setMoeglichePaare () {
		
		if (anzahlFrauen<anzahlMaenner) {
			kleinereMenge = anzahlFrauen;
		}
		else {
			kleinereMenge = anzahlMaenner;
		}
		
		shuffleCounter = 0;
		maxKombinationen = anzahlFrauen*anzahlMaenner;
		int aktuelleFrau=0;
		int aktuellerMann=0;
		for (int i = 0; i<maxKombinationen;) {
			for (aktuelleFrau=0;aktuelleFrau<anzahlFrauen;) {
				
				if (aktuellerMann>=anzahlMaenner) {
					aktuellerMann = 0;
				}
					
					LinkedList<Integer> paar = new LinkedList<Integer>();
					paar.add(aktuellerMann+1);
					paar.add(aktuelleFrau+1);
					
					if (moeglichePaare.contains(paar)) {
						aktuellerMann++;
					}
					else {
						moeglichePaare.add(paar);
						aktuelleFrau++;
					}
				
					i++;
				
			}
		}
		
		
		
	}

		
	public String starteMatching () {
		String aktuellePaare="";
	
		float verhindereFreeze=1;
		
		if (shuffleCounter<maxKombinationen) {
			LinkedList<Integer> ausgewaehlteMaenner = new LinkedList<Integer>();
			LinkedList<Integer> ausgewaehlteFrauen = new LinkedList<Integer>();
			LinkedList<Integer> selectedPaar;
			if (!moeglichePaare.isEmpty()) {
			int pickCounter = 0;
			int randomPick = 0;
			verhindereFreeze=0;

				while (pickCounter<kleinereMenge) {
				if (moeglichePaare.isEmpty()) {
					shuffleCounter = maxKombinationen;
					break;
				}
				randomPick = (int) ((Math.random()*100)%moeglichePaare.size());
				selectedPaar = moeglichePaare.get(randomPick);
				
				if(ausgewaehlteMaenner.contains(selectedPaar.get(0))|| ausgewaehlteFrauen.contains(selectedPaar.get(1))) {
					
					
					if(verhindereFreeze>maxKombinationen) {
					shuffleCounter+=1;
					pickCounter+=1;
					
					}
					
					verhindereFreeze+=0.1/maxKombinationen;
					
				}
					
				
				else {
					
				aktuellePaare += " " + selectedPaar + " \n";
				ausgewaehlteMaenner.add(selectedPaar.get(0));
				ausgewaehlteFrauen.add(selectedPaar.get(1));
				moeglichePaare.remove(randomPick);
				shuffleCounter+=1;
				pickCounter+=1;
				
				}
			}
			}
			
			else {
				shuffleCounter = maxKombinationen;
			}
			
			
		}
		
		else {
			aktuellePaare = "alle möglichen Kombinationen ausgeschöpft.";
		}
		
		
		
		return aktuellePaare;
	}
	
	
	



	
}
