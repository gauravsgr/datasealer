import java.io.*;
class Loader
{
	void encrypt(File directory)
	{
	int i=100;
		try
		{
			PrintWriter pw=new PrintWriter(directory.getCanonicalPath()+File.separator+"GS_21.dll");
			for(File f:directory.listFiles())
			{	
				String name="GS"+i+++"_.dll";
				String dirName="en-US"+i;
				if(f.isFile()&(f.getName().equals("GS_21.dll"))) continue;
				if(f.isDirectory())
				{
					pw.println(dirName);
					pw.println(f.getName());
					encrypt(f);
					f.renameTo(new File(f.getCanonicalPath()+File.separator+".."+File.separator+dirName));
				}
				else 
				{
					pw.println(name);
					pw.println(f.getName());
					f.renameTo(new File(f.getCanonicalPath()+File.separator+".."+File.separator+name));
				}
			}
			pw.close();
		}catch(Exception er){System.out.println(er);}
	}
}