import java.io.*;
class CopyDir
{
	public static void main(String...arg)
	{
		File sourc=new File(arg[0]);
		File destinatin=new File(arg[1]);
		copyDirectory(sourc,destinatin);
	}
	static public void copyDirectory(File source,File destination)
	{
		FileUtilities f=new FileUtilities();
		try{
		f.copy(source, destination );
		}catch(Exception e){System.out.println("Exception at 11 "+e);}	
		deleteFiles(source);
		source.delete();
	}
	
	static public void deleteFiles(File del)
	{
		File[] list=del.listFiles();
		for(int i=0;i<list.length;i++) 
		{
			if(list[i].isFile()) list[i].delete();
			else deleteFiles(list[i]);
			list[i].delete();
		}
	}
}