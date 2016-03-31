package com.dev.frontend.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils
{
	private static Properties properties;
	private static final String PROPERTIES_URI = "frontend.properties";
	private static final String URI_SERVICES = "uri.services";
	
	public static Double parseDouble(String value)
	{
		if(value == null||value.isEmpty())
			return 0D;
		try
		{
			return Double.parseDouble(value);
		}
		catch(Exception e)
		{
			return 0D;
		}
	}

	private static InputStream getInput(String uri){
		return Utils.class.getClassLoader().getResourceAsStream(uri);
	}
	
	private static Properties getProperties() throws IOException{
		if (properties== null){
	    	properties = new Properties();
	    	InputStream i = getInput(PROPERTIES_URI); 
			properties.load(i);
		}
		return properties;
	}
	
	/**
	 * Returns the value of a property on the frontend.properties file.
	 * @param propertyName The key to the property.
	 * @return The value.
	 */
	public static String getProperty(String propertyName){
		String propertyValue;
		try {
			propertyValue = getProperties().getProperty(propertyName);
		} catch (IOException e){
			e.printStackTrace();
			throw new InstantiationError("There is no 'frontend.properties' file in the classpath!");
		}
        return propertyValue;
	}
	
	/**
	 * Return the base URI, as defined on the frontend.properties file, that must be on the classpath.
	 * @return
	 */
	public static String getBaseUriServices(){
		return getProperty(URI_SERVICES);
	}

	public static Integer parseInteger(String value) {
		if(value == null||value.isEmpty())
			return 0;
		try
		{
			return Integer.parseInt(value);
		}
		catch(Exception e)
		{
			return 0;
		}
	}
}
