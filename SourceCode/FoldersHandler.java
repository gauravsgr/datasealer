import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class FoldersHandler extends MouseAdapter
{
	public void mouseClicked(MouseEvent e)
	{
		if(e.getClickCount()>1) 
		{
			java.awt.List l=(java.awt.List)e.getSource();
			String rec=l.getSelectedItem();
			System.out.println("item clicked "+rec);
			String entryArray[]=new String[Encoder.entry.size()];
			entryArray=Encoder.entry.toArray(entryArray);
			String hiddenDirectory="";
			for(int i=0;i<entryArray.length;i++) if(rec.equals(entryArray[i])) 
			{
				hiddenDirectory=entryArray[++i];
				Encoder.entry.remove(rec); l.remove(rec);
				Encoder.entry.remove(hiddenDirectory);
				break;
			}
			File source=new File(hiddenDirectory);
			System.out.println("hidden Directory is "+hiddenDirectory);
			new Unloader().decrypt(source);
			//source.renameTo(new File(rec));
			CopyDir.copyDirectory(source,new File(rec));
			Encoder.list.delete();
			try
			{
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(Encoder.list));
				oos.writeObject(Encoder.entry);
				oos.close();
			}catch(Exception es){System.out.println("Exception in Folders Handler"+es);}
			JOptionPane.showMessageDialog(null,"Folder Recovered Successfully");
		}
	}	
}	