package com.springlab.biz.board.controller2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HandlerMapping {
	private Map<String, Controller> mapping = null;
	
	public HandlerMapping(String configFile) {
		mapping = new HashMap<String, Controller>();
		Properties props = new Properties();
		
		try {
			InputStream input = new FileInputStream(new File(configFile));
			if (input != null) {
			    props.load(input);
			    
//			    Enumeration e = props.propertyNames();
//			    while (e.hasMoreElements()) {
//			    	String key = (String)e.nextElement();
//			    	String className = props.getProperty(key);
//			    	Class controllerClass = Class.forName(className);
//			    	Controller controller = (Controller) controllerClass.getDeclaredConstructor().newInstance();
//			    	
//			    	mapping.put(key,  controller);
//			    }
			    
			    props.forEach((key, className) -> {
			    	Class controllerClass;
					try {
						controllerClass = Class.forName((String) className);
						Controller controller = (Controller) controllerClass.getDeclaredConstructor().newInstance();
				    	
				    	mapping.put((String) key,  controller);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    });
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		mapping.put("/login.do", new LoginController());
//		mapping.put("/getBoardList.do", new GetBoardListController());
		
	}
	
	public Controller getController(String path) {
		return mapping.get(path);
	}
}
