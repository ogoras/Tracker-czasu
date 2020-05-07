package text_interface;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditActivityWindow extends JPanel{
	void EditActivityWindow(){  
		JFrame a = new JFrame("Aktywnoœæ");

		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	}         
	
	public static void main(String[] args) {    
	    new EditActivityWindow();    
	}    
}   

