package com.oblivioussp.spartanweaponry.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

public class Log
{
	protected static final Logger LOGGER = LogManager.getLogger(ModSpartanWeaponry.Name);
	
	/*public static void preInit(Logger logger)
	{
		LogHelper.logger = logger;
	}*/
	
	/*public static void log(Level logLevel, Object object)
	{
		FMLLog.log(Reference.ModName, logLevel, String.valueOf(object));
	}*/
	
	//public static void all(Object object) {	log(Level.ALL, object); }
	
	public static void debug(Object object) 
	{
		//log(Level.DEBUG, object);
		LOGGER.debug(object);
	}

	public static void error(Object object)
	{
		//log(Level.ERROR, object);
		LOGGER.error(object);
	}

	public static void fatal(Object object) 
	{
		//log(Level.FATAL, object); 
		LOGGER.fatal(object);
	}

	public static void info(Object object)
	{
		//log(Level.INFO, object);
		LOGGER.info(object);
	}

	public static void trace(Object object)
	{
		//log(Level.TRACE, object);
		LOGGER.trace(object);
	}

	public static void warn(Object object) 
	{
		//log(Level.WARN, object);
		LOGGER.warn(object);
	}
}
