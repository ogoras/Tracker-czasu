package text_interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;      
public class ButtonExample extends JPanel{    
	public List<JButton> List;
	public List<EditActivityWindow> WindowList;
	private int height = 100;
	
	ButtonExample(){    
		JFrame f=new JFrame("Tracker czasu");            
		JButton b=new JButton("Add Activity");  
		List = new ArrayList<JButton>();
		WindowList = new ArrayList<EditActivityWindow>();
		
		b.setBounds(0,0,300,50);    
		b.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				
				JButton activityBtn = new JButton("str");
				EditActivityWindow activityWnd = new EditActivityWindow();
				
				f.add(activityBtn);
				f.add(activityWnd);
				activityBtn.setBounds(200,height,500,50);
				height += 60;
			    f.validate();
			    activityBtn.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e) {
						
						activityWnd.setVisible(true);
						activityWnd.setSize(1000,1000);    
						activityWnd.setLayout(null);
						f.setVisible(false);
					}
				});
			}
		});
		
		
		f.add(b);    
		f.setSize(1000,1000);    
		f.setLayout(null);
		f.setVisible(true);
		

	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    }         
	
	public static void main(String[] args) {    
	    new ButtonExample();    
	}    
}   