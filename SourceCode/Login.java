import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
public class Login extends JFrame implements ActionListener
{
	JLabel username,password;
	JTextField user_nam;
	JPasswordField pass;
	JButton enter,forgot_pass;
	JPanel one,two,three;
	Login()
	{
		super("Login");
	}
	public void createFrame()
	{
		username=new JLabel(" USERNAME ");
		password=new JLabel("PASSWORD");
		user_nam=new JTextField(15);
		pass=new JPasswordField(15);
		enter=new JButton(" Submit ");
		forgot_pass=new JButton("Forgot Password");
		one=new JPanel();
		one.add(username); one.add(user_nam);
		two=new JPanel();
		two.add(password); two.add(pass);
		three=new JPanel();
		three.add(enter); three.add(forgot_pass);
		add(one,BorderLayout.NORTH);
		add(two);
		add(three,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(450,300,300,130);
		setVisible(true);
		user_nam.addActionListener(this);
		pass.addActionListener(this);
		enter.addActionListener(this);
		forgot_pass.addActionListener(this);
	}
	//---------------Doing up Event Handling---------------------
	public void actionPerformed(ActionEvent ae)
	{
		try
			{
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(Encoder.list)));
				Object obj=ois.readObject();
				Encoder.entry=(ArrayList<String>)obj;
				ois.close();
			}catch(Exception e){System.out.println("Exception at line 28" +e);}
			String entryArray[]=new String[Encoder.entry.size()];
			entryArray=Encoder.entry.toArray(entryArray);
		if(ae.getSource()==enter)
		{
			if((entryArray[0].equals(user_nam.getText()))&(entryArray[1].equals(pass.getText())))
			{
				setVisible(false);
				new DataSealer().createTabbedFrame(entryArray);
			}
			else JOptionPane.showMessageDialog(this,"Invalid Username and Password. Please Try Again.");
		}
		if(ae.getSource()==forgot_pass)
		{
			this.setVisible(false);
			String response = JOptionPane.showInputDialog(this,"Please Answer Your Security Question:\nQ: "+entryArray[2]);
			if(!response.equals(entryArray[3])) 
			{	
				JOptionPane.showMessageDialog(this,"Invalid Answer too.");
				System.exit(0);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Recovery Successful\n\nYour Username: "+entryArray[0]+" \nYour Password: "+entryArray[1]);
				this.setVisible(true);
			}
		}
		
	}
}