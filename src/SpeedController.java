import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedController implements ActionListener {

	SpeedWindow win;
	
	public void start() {
		win = new SpeedWindow(this);
		win.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		System.out.println(command);
		if (command.equals("Zuordnen")) {
			
			System.out.println(""+ win.readText()[0] + "   " + win.readText()[1]);
		}
		
		win.start();
	}
	
}
