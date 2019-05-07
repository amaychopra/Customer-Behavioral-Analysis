package com.istm.cda.ui;

import java.util.Scanner;

import com.istm.cda.data.DataConnection;
import com.istm.cda.data.DataResult;
import com.istm.cda.data.Statement;

/**
 * Generate 'Customer demograph - membership' type analysis report
 * @author Team 4(601)
 */
public class GenerateCommandOne implements Command {

	private Console console;
	private DataConnection datasource = null;
	private Statement statement =null;

	public GenerateCommandOne(Console console) {
		this.console = console;
	}

	@Override
	public void execute(String[] args) {

		System.out.println("\n Enter 1 for Rural \n Enter 2 for Urban");
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		switch(s) {
		case "1": 
			generateReport("Rural"); 
			break;
		case "2": 
			generateReport("Urban"); 
			break;
		}

		console.write("Report for Premium customers residing in Rural areas has been generated");
		console.close();
		System.exit(0);
	}

	private void  generateReport(String region) {

		try {
			datasource = new DataConnection();
			statement = new Statement(datasource);
			console.write("Generating graph for showing the relationship between customer membership type and demographic location...");
			String query = "MATCH (c1:Customer)" + 
					"WHERE c1.demographic_region = '"+region+"' AND c1.membership = 'Premium'" + 
					"CREATE (c1)-[r:relation { relation: c1.demographic_region + '<->' + c1.membership }]->(c1) "+
					"return count(c1.customer_id)";
			DataResult dataResult = statement.get(query);
			console.write("Number of customers with Premium membership residing in "+ region + " areas:\n "+dataResult.get(0));
			console.write("Check the data visualizer of Neo4j for viewing the graph");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			datasource.disconnect();
		}
	}

}