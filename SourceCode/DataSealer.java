import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class DataSealer extends JFrame implements ActionListener
{
	JPanel recover,info,about,seal;
	JPanel one,two,twoA,twoB,twoC,sealone,sealoneA,sealoneB;
	JButton edit,ok,cancel,loader;
	JTextField nus;
	JPasswordField npass;
	List mainFolders;
	JTabbedPane tabs = new JTabbedPane();
	CardLayout cc,cl;
	DataSealer(){ super("    Data Sealer Version 1.0 (beta)");}
	
	void createTabbedFrame(String[] foldrs)
	{
		createRecover(foldrs);
		createEdit(foldrs);
		createSeal();
		createAbout();
		tabs.add("Recover",recover);
		tabs.add("Seal Folder",seal);
		tabs.add("Edit Profile",info);
		tabs.add("About",about);
		add(tabs);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200,300,510,236);
		setVisible(true);
		/*try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
			//chooser.updateUI();
		}catch(Exception e){}*/
	}
	
	void createRecover(String[] foldrs)
	{	
		recover=new JPanel(new BorderLayout());
				JLabel msg=new JLabel("                      Select the Folder to Recover");
			recover.add(msg,BorderLayout.NORTH);
				List folders=new List(); mainFolders=folders;
					int i=-1;
					for(String s:foldrs) 
					{	
						++i;
						if(i<=3)continue;
						if(i%2==0) folders.add(s);
					}
				JScrollPane js=new JScrollPane(folders);
			recover.add(js);
			recover.add(new JLabel(""),BorderLayout.SOUTH);
			folders.addMouseListener(new FoldersHandler());
	}
	
	void createEdit(String[] foldrs)
	{
		cl=new CardLayout();
		info=new JPanel(cl);
			one=new JPanel(new BorderLayout());
				one.add(new JLabel("                     Are you Sure You want to edit the Profile?"),BorderLayout.NORTH);
				edit=new JButton("PUSH TO CHANGE YOUR USERNAME AND PASSWORD");
				one.add(edit);
			two=new JPanel(new BorderLayout());
					twoA=new JPanel();
						twoA.add(new JLabel("  ENTER USERNAME")); 
						nus=new JTextField(15);
						twoA.add(nus);
					twoB=new JPanel();
						twoB.add(new JLabel(" ENTER PASSWORD"));
						npass=new JPasswordField(15);
						twoB.add(npass);
					twoC=new JPanel();
						ok=new JButton("Enter");
						cancel=new JButton("Cancel");
						twoC.add(ok); 
						twoC.add(cancel);
				two.add(twoA,BorderLayout.NORTH);
				two.add(twoB);
				two.add(twoC,BorderLayout.SOUTH);
			info.add(one,"one");
			info.add(two,"two");
			edit.addActionListener(this);
			ok.addActionListener(this);
	}
	
	void createSeal()
	{
		seal=new JPanel(new BorderLayout());
			seal.add(new JLabel("                      DO YOU REALLY WANT TO SEAL A FOLDER??? >B-)"),BorderLayout.NORTH);
			seal.add(new JLabel("PRESS THE SEAL BUTTON TO CHOOSE UR FOLDER   "),BorderLayout.WEST);
				loader=new JButton("SEAL FOLDER");
			//seal.add(loader,BorderLayout.EAST);
			seal.add(loader);
			seal.add(new JLabel("     "),BorderLayout.EAST);
			cc=new CardLayout();
				sealone=new JPanel(cc);
						sealoneA=new JPanel();
							sealoneA.add(new JLabel(""));
						sealoneB=new JPanel();
							JProgressBar progress = new JProgressBar(10,1000);
								progress.setIndeterminate(true);
								sealoneB.add(progress);
					sealone.add(sealoneA,"sealoneA");
					sealone.add(sealoneB,"sealoneB");
			seal.add(sealone,BorderLayout.SOUTH);		
		loader.addActionListener(this);
	}
	
	void createAbout()
	{
		about=new JPanel(new BorderLayout());
		about.add(new JLabel("Software developed by Gaush, 4 Queries & Suggestion, mail at gaurav.sgr@gmail.com"),BorderLayout.NORTH);
		about.add(new JLabel("Data Sealer V.1.0 (beta)"));
		about.add(new JLabel("Thanx for trying out this Software"),BorderLayout.SOUTH);
	}
	//----------------------------------Event Handling-----------------------
	public void actionPerformed(ActionEvent ae)
	{
		if((ae.getSource()==loader)&(ae.getActionCommand()=="SEAL FOLDER"))
		{
			cc.show(sealone,"sealoneB");
			loader.setText("Please WAIT");
			DirectoryChooser dc=new DirectoryChooser();
			if(dc.original==null)
			{
				loader.setText("SEAL FOLDER");
				cc.show(sealone,"sealoneA");
				return;
			}
			try{
			new Loader().encrypt(dc.original);
			Encoder.changeDirectory(dc.original);
			Encoder.list.delete();
			try{
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(Encoder.list));
				oos.writeObject(Encoder.entry);
				oos.close();
			}catch(Exception e){System.out.println("Exception at line 78"+e);}//recently added try catch
			mainFolders.add(dc.original.getCanonicalPath());
			}catch(Exception s){System.out.println("at125"+s);}
			loader.setText("SEAL FOLDER");
			cc.show(sealone,"sealoneA");
		}
		else if(ae.getSource()==edit) cl.show(info,"two");
		else if(ae.getSource()==ok)
		{
			Encoder.entry.set(0,nus.getText());
			Encoder.entry.set(1,npass.getText());
			Encoder.list.delete();
			try{
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(Encoder.list));
				oos.writeObject(Encoder.entry);
				oos.close();
			}catch(Exception e){System.out.println("Exception at line 78"+e);}
			cl.show(info,"one");
			JOptionPane.showMessageDialog(this," Profile Information Updated ");
		}
		else if(ae.getSource()==cancel) cl.show(info,"one");
	}
}