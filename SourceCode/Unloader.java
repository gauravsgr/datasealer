import java.io.*;
class Unloader
{
	void decrypt(File directory)
	{
		try
		{
			BufferedReader bf=new BufferedReader(new FileReader(directory+File.separator+"GS_21.dll"));
			while(true)
			{
				String maskedName=bf.readLine(); if(maskedName==null) break;
				String actualName=bf.readLine();
				File masked=new File(directory+File.separator+maskedName);
				File actual=new File(directory+File.separator+actualName);
				if(masked.isDirectory()) decrypt(masked);
				masked.renameTo(actual);
			}
			bf.close();
		}catch(Exception e){System.out.println("error at line 17"+e);}
		new File(directory+File.separator+"GS_21.dll").delete();
	}
}