package com.istm.cda.ui;

import java.util.List;

import com.istm.cda.data.DataConnection;
import com.istm.cda.data.DataResult;
import com.istm.cda.data.Statement;

/**
 * Generate 'customer age group - days since last order' analysis report");
 * @author Team 4(601)
 */

public class GenerateCommandTwo implements Command {

	private Console console;
	private DataConnection datasource = null;
	private Statement statement =null;

	public GenerateCommandTwo(Console console) {
		this.console = console;
	}

	@Override
	public void execute(String[] args) {
		generateReport();
		console.write("Report for 'customer age group' versus 'days since last order placed' has been generated");
		console.close();
		System.exit(0);
	}

	private void  generateReport() {

		try {
			datasource = new DataConnection();
			statement = new Statement(datasource);
			console.write("Generating table for showing the relationship between customer age group and days since last order placed...");
			String query = "MATCH (n:Customer) "+
					"RETURN n.age_group AS age, "+ 
					"TOINT(AVG(TOINT(n.days_since_last_order))) AS avg_days_since_last_order "+
					"ORDER BY age";
			console.write("|  AGE |  DAYS SINCE LAST ORDER(AVERAGE) | \n");
			List<DataResult> dataResult = statement.getAll(query);
			for (DataResult data : dataResult) {
				console.write("| "+data.get(0)+" |                 "+data.get(1)+"              | \n");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			datasource.disconnect();
		}
	}

}