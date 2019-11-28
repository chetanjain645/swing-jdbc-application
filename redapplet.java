import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class redapplet extends Applet implements ActionListener
	{
	Button b1,b2,b3;
	public void init()
	{
	b1=new Button("Red");
	b2=new Button("Blue");	
	b3=new Button("Green");
		add(b1);add(b2);add(b3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		}
	public void actionPerformed(ActionEvent e)
	{
	Button b=(Button)e.getSource();
	if(b==b1)
	{
	setBackground(Color.red);
	}
	if(b==b2)
	{
	setBackground(Color.blue);
	}
	if(b==b3)
	{
	setBackground(Color.green);
	}
}
}