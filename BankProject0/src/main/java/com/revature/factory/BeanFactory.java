package com.revature.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BeanFactory {
	private static Logger log = LogManager.getLogger(BeanFactory.class);
	private static BeanFactory bf = null;
	
	private BeanFactory() {}
	
	public static synchronized BeanFactory getFactory() {
		if(bf == null) {
			bf = new BeanFactory();
		}
		return bf;
	}
	
	public Object get(Class<?> inter, Class<?> clazz) {
		
	}

}
