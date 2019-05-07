package com.istm.cda.data;

/**
 * Loads data into Neo4j database.
 * @author Team 4(601)
 */
public class DataLoader {

	public static void main(String[] args) {
		DataConnection datasource = null;
		try {
			datasource = new DataConnection();
			Statement statement = new Statement(datasource);
			deleteAll(statement);
			loadCustomers(statement);
			loadOrders(statement);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			datasource.disconnect();
		}
	}

	/**
	 * This method deletes all nodes and relations
	 */
	private static void deleteAll(Statement statement) {
		System.out.println("Delete Nodes and Relationships");
		String cql = "MATCH (n) DETACH DELETE n";
		statement.execute(cql);
	}

	/**
	 * This method loads customer data into the database
	 */
	private static void loadCustomers(Statement statement) {
		System.out.println("Load Customers");
		String query = "USING PERIODIC COMMIT 1000 " 				
				+"LOAD CSV WITH HEADERS "
				+"FROM 'file:///CustomerDataset.csv' AS row "
				+"CREATE (n:Customer) SET n=row";
		statement.execute(query);
	}

	/**
	 * This method loads order data into the database
	 */
	private static void loadOrders(Statement statement) {
		System.out.println("Load Orders");
		String query = "USING PERIODIC COMMIT 1000 " 				
				+"LOAD CSV WITH HEADERS "
				+"FROM 'file:///OrderDataset.csv' AS row "
				+"CREATE (n:Order) SET n=row";
		statement.execute(query);
	}
	
}
