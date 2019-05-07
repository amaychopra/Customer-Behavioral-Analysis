package com.istm.cda.data;

import java.io.*;
import java.util.Properties;

import org.neo4j.driver.v1.*;

/**
 * Neo4j Connection
 * @author Team 4 (601)
 */
public class DataConnection {

	private static final String PROPS_FILE = "neo4jcredentials.properties";
	private static final String URL = "url";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	private Driver driver;

	public DataConnection() {
		try {
			this.driver = init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Session connect() {
		return driver.session();
	}

	public void disconnect() {
		if (driver != null) {
			driver.close();
		}
	}
	
	public void disconnect(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	
	private Driver init() throws IOException {
		
		Properties properties = new Properties();
		InputStream stream = DataConnection.class.getClassLoader()
				.getResourceAsStream(PROPS_FILE);
		properties.load(stream);
		
		String url = properties.getProperty(URL);
		String username = properties.getProperty(USERNAME);
		String password = properties.getProperty(PASSWORD);
		
		AuthToken token = AuthTokens.basic(username, password);
		return GraphDatabase.driver(url, token);
		
	}

}
