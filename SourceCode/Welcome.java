import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
public class Welcome extends JFrame implements ActionListener
{
	JLabel msg,new_un,new_pass,con_pass,sec_question,ans_question,dummy;
	JTextField username,security_ques,security_ans;
	JPasswordField password,conpassword;
	JButton register,later;
	JPanel end,one,two,three,four,oneA,twoA,twoB,threeA,fourA,fourB;
	public Welcome()
	{
		super("Welcome New User");
	}
	public void createFrame()
	{
		dummy=new JLabel("      ");
		msg=new JLabel("                                    PLEASE CREATE YOUR PROFILE");
		new_un=new JLabel  ("   ENTER USERNAME   ");
		new_pass=new JLabel("   ENTER PASSWORD  ");
		con_pass=new JLabel("CONFIRM PASSWORD");
		sec_question=new JLabel("                                        ENTER A SECURITY QUESTION");
		ans_question=new JLabel("                              ENTER ANSWER OF SECURITY QUESTION");
		username=new JTextField(15);
		password=new JPasswordField(15);
		conpassword=new JPasswordField(15);
		security_ques=new JTextField(35);
		security_ans=new JTextField(35);
		register=new JButton("Register Now");
		later=new JButton("Register Later");
		end=new JPanel(new BorderLayout()); one=new JPanel(new BorderLayout()); two=new JPanel(new BorderLayout()); three=new JPanel(new BorderLayout());four=new JPanel(new BorderLayout());
		oneA=new JPanel(); twoA=new JPanel(); twoB=new JPanel(); threeA=new JPanel(); fourA=new JPanel();fourB=new JPanel();
		one.add(msg,BorderLayout.NORTH);
		oneA.add(new_un); oneA.add(username);
		one.add(oneA);
		
		twoA.add(new_pass); twoA.add(password);
		twoB.add(con_pass); twoB.add(conpassword);
		two.add(twoA,BorderLayout.NORTH); two.add(twoB);
		
		three.add(sec_question,BorderLayout.NORTH); 
		threeA.add(dummy); threeA.add(security_ques); threeA.add(dummy);
		three.add(threeA,BorderLayout.CENTER); 
		three.add(ans_question,BorderLayout.SOUTH);
		
		fourA.add(dummy);fourA.add(security_ans);fourA.add(dummy);
		fourB.add(register); fourB.add(later);
		four.add(fourA,BorderLayout.NORTH);
		four.add(fourB);
				
		end.add(three,BorderLayout.NORTH);
		end.add(four);
		add(one,BorderLayout.NORTH);
		add(two);
		add(end,BorderLayout.SOUTH);
		setBounds(450,300,420,275);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		//---------------------------Doing Up Event Handling---------------------
		username.addActionListener(this);
		password.addActionListener(this);
		conpassword.addActionListener(this);
		security_ques.addActionListener(this);
		security_ans.addActionListener(this);
		register.addActionListener(this);
		later.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==register)
		{
			Encoder.entry=new ArrayList<String>();
			String pss=password.getText(); String cpss=conpassword.getText();
			if(!pss.equals(cpss)) pss= JOptionPane.showInputDialog(this,"Please Enter Password Again");
			while(pss==null) pss= JOptionPane.showInputDialog(this,"Please Enter Password Again");
			Encoder.entry.add(username.getText());
			Encoder.entry.add(pss);			
			Encoder.entry.add(security_ques.getText());
			Encoder.entry.add(security_ans.getText());
			try
			{
				Encoder.list.createNewFile();
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(Encoder.list));
				oos.writeObject(Encoder.entry);
				oos.close();
			}catch(Exception e){System.out.println("Exception at line 78"+e);}
			setVisible(false);
			new Login().createFrame();
		}
		else if(ae.getSource()==later) System.exit(0);
	}
	
}