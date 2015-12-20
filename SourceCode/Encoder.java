import java.io.*;
import java.util.*;
class Encoder
{
	static File source;
	static PrintWriter pw;
	static ArrayList<String> entry=null;
	static File list=new File("."+File.separator+"37OHSSV.dll");
	public static void main(String...arg) 
	{
		Console c=System.console();
		int choice=Integer.parseInt(c.readLine("Enter choice \n1. LOADER \t2. UNLOADER"));
		if(choice==1)
		{
			File original=new File(arg[0]);
			Loader l=new Loader();
			l.encrypt(original);
			changeDirectory(original);
		}
		//-------------------------------
		else
		{
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(list)));
				Object obj=ois.readObject();
				entry=(ArrayList<String>)obj;
				ois.close();
			}catch(Exception e){System.out.println("Exception at line 28" +e);}
			boolean flag=false;
			String hiddenDirectory="";
			String entryArray[]=new String[entry.size()];
			entryArray=entry.toArray(entryArray);
			for(int i=0;i<entryArray.length;i++) if(arg[0].equalsIgnoreCase(entryArray[i])) 
			{
				hiddenDirectory=entryArray[++i];
				entry.remove(arg[0]);
				entry.remove(hiddenDirectory);
				break;
			}
			source=new File(hiddenDirectory);
			System.out.println("hidden Directory is "+hiddenDirectory);
			Unloader u=new Unloader();
			u.decrypt(source);
			//source.renameTo(new File(arg[0]));
			CopyDir.copyDirectory(source,new File(arg[0]));
		}
		list.delete();
		try
		{
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(list));
			oos.writeObject(entry);
			oos.close();
		}catch(Exception e){System.out.println("Exception at line 52"+e);}
	}
	
	
	static void changeDirectory(File actualDir)
	{
		File s=new File("c:\\WindowsSupport");
		s.mkdirs();
		/*for(File f:s.listFiles()) 
		{
			if((f.isDirectory())&(!(f.getName().equalsIgnoreCase("Windows"))|(f.getName().equalsIgnoreCase("Program Files"))))
			{
				s=f;
				break;
			}
		}*/
		try
		{
			int i=2101;
			File newDirectory=null;
			while(true)
			{
				
				newDirectory=new File(s.getCanonicalPath()+File.separator+"R"+i);
				if(!newDirectory.exists()) break;
				i++;
			}
			//actualDir.renameTo(newDirectory);
			CopyDir.copyDirectory(actualDir,newDirectory);
			
			if(!list.exists())
			{
				list.createNewFile();
				entry=new ArrayList<String>();
			}
			else
			{
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(list)));
				Object obj=ois.readObject();
				ois.close();
				entry=(ArrayList<String>)obj;
			}
			entry.add(actualDir.getCanonicalPath());
			entry.add(newDirectory.getCanonicalPath());	
			System.out.println(newDirectory.getCanonicalPath());
		}catch(Exception e){System.out.println("Exception at 60"+e);}
	}
}