import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.File;

public class DirectoryChooser
{
	File original=null;
	public DirectoryChooser()
	{
		JFileChooser chooser=new JFileChooser();
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(chooser);
			//chooser.updateUI();
		}catch(Exception e){}
		chooser.setDialogTitle("Select File");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setApproveButtonText("Load Folder");
		chooser.setApproveButtonToolTipText("Seals the Folder Selected");
		if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) original=chooser.getSelectedFile();			
		else System.out.println("No Selection");	
	}
}

//JFrame.setDefaultLookAndFeelDecorated(true);