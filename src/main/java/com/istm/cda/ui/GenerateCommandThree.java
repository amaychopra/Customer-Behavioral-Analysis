package com.istm.cda.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.istm.cda.data.DataConnection;
import com.istm.cda.data.DataResult;
import com.istm.cda.data.Statement;

public class GenerateCommandThree implements Command{
	
	private static DataConnection datasource = null;
	private static Statement statement =null;
	private Console console;
	
	public GenerateCommandThree(Console console) {
		this.console = new Console("CDA");
	}

	@Override
	public void execute(String[] args) {
		generateTable();
		console.write("Report for 'Order frequency per year' generated");
		console.close();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		generateTable();
		}
	
	private static void generateTable() {
		try {
				datasource = new DataConnection();
				statement = new Statement(datasource);
				List<String> years= Arrays.asList("y2013","y2014","y2015","y2016","y2017");
				List<String> regions=Arrays.asList("Rural","Urban");
				List<String> shopType=Arrays.asList("Mobile","Desktop");
				List<String> category=new ArrayList<String>();
				List<List<Integer>> superData=new ArrayList<List<Integer>>();
				List<Integer> data;
				for (int i = 0; i < regions.size(); i++) {
					for (int j = 0; j < shopType.size(); j++) {
					   data=new ArrayList<Integer>();
					   category.add(regions.get(i)+" - "+shopType.get(j));
						for (int y = 0; y < years.size(); y++)   {
							data.add(executeQuery(regions.get(i),shopType.get(j),years.get(y)));
						}
					   superData.add(data);
				    }
				}
				
				displayResult(category, superData);
               
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				datasource.disconnect();
			}
	}

	private static Integer executeQuery(String region, String shoptype, String year) {
		String query = "MATCH (c1:Customer)" + 
				"WHERE c1.demographic_region = '"+region+"' AND c1.shop_type = '"+shoptype +"' "+
				"CREATE (c1)-[r:relation { relation: c1.demographic_region + '<->' + c1.shop_type }]->(c1) "+
				"return sum(toint(c1."+year+"))";
		DataResult dataResult = statement.get(query);
		return Integer.parseInt(dataResult.get(0));
	}
	
	private static void displayResult(List<String> category, List<List<Integer>> superData) {
		    int i=0;
		    System.out.println(String.format("%15s %15s %15s %15s %15s %15s", " ", 2013,2014,2015,2016,2017));
		    for (List<Integer> list : superData) {
					System.out.println(String.format("%15s %15s %15s %15s %15s %15s",category.get(i), list.get(0), list.get(1),
							list.get(2), list.get(3), list.get(4)));
					i++;
			}
	}

}
