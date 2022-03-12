package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
//constructor to load the file 
	public ReadConfig() {
		File src = new File("./Configuration/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);

		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());

		}
	}

	public String getApplicationURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}

	public String getUsername() {
		String txtUsername = pro.getProperty("username");
		return txtUsername;
	}

	public String getPassword() {
		String txtPassword = pro.getProperty("password");
		return txtPassword;
	}

	public String getChromePath() {
		String pathChrome = pro.getProperty("chromepath");
		return pathChrome;
	}

	public String getFireFoxPath() {
		String pathFirefox = pro.getProperty("firefoxpath");
		return pathFirefox;
	}

}
