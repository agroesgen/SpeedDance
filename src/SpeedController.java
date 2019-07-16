import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SpeedController implements ActionListener {

	SpeedWindow win;
	BackendLogic logic = new BackendLogic();
	
	public void start() {
		win = new SpeedWindow(this);
		win.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		
		if (command.equals("Zuordnen")) {
			logic.setAnzahlFrauen(win.readText()[1]);
			logic.setAnzahlMaenner(win.readText()[0]);
			//win.paarAusgabe.setText(logic.starteMatching());
			win.printPairs();
			
		}
	}
	
}
