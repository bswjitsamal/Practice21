package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class read_Configuration_Propertites {
	


	public static  Properties loadproperty(String filename) throws IOException {
		
		String propertyfilepath = System.getProperty("user.dir")+File.separator+"resources"+File.separator+filename+".propertites";
		File file = new File(propertyfilepath);
		FileInputStream fileinput = new FileInputStream(file);
		Properties configProperties = new Properties();
		configProperties.load(fileinput);
		return configProperties;	
	}

	public static Properties put(String string, String string2,String filename) throws IOException {
		
		String propertyfilepath = System.getProperty("user.dir")+File.separator+"resources"+File.separator+filename+".propertites";
		File file = new File(propertyfilepath);
		FileOutputStream outputStrem = new FileOutputStream(file);
		Properties configProperties = new Properties();
		configProperties.store(outputStrem, "String in file");
		return configProperties;			
	}
	
	


}
