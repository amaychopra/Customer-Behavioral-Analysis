package com.istm.cda;

import com.istm.cda.data.DataConnection;
import com.istm.cda.ui.Console;
import com.istm.cda.ui.Invoker;

/**
 * Proof of architecture - Main class
 * @author Team 4 (601)
 */
public class Main {

	/**
	 * The database connection gets established and queries for retrieving data are executed
	 */
	public static void main(String[] args) {
		displayTitle();
		DataConnection datasource = null;
		try {
			datasource = new DataConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			datasource.disconnect();
		}
		Console console = new Console("CDA");
		Invoker invoker = new Invoker(console);
		invoker.start();
	}
	
	private static void displayTitle() {
		System.out.println("Customer Data Analysis (CDA) v0.4");
	}
}
