import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
class Start 
{
	public static void main(String...arg) throws Exception
	{
		Thread.currentThread().sleep(2000);
		if(!Encoder.list.exists())
		{
			Welcome w=new Welcome();
			w.createFrame();
		}
		else
		{
			Login l=new Login();
			l.createFrame();
		}
	}
}