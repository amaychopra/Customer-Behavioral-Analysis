package com.istm.cda.data;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.Values;

/**
 * Neo4j Statement implementation
 * @author Team 4 (601)
 */
public class Statement{

	private DataConnection datasource;
	
	public Statement(DataConnection datasource) {
		this.datasource = datasource;
	}
	
	public DataResult get(String query, Object... params) {
		DataResult dataResult = null;
		Session session = null;
		try {
			session = datasource.connect();
			Value value = Values.parameters(params);
			Record record = session.run(query, value).single();
			dataResult = new DataResult(record);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			datasource.disconnect(session);
		}
		return dataResult;
	}

	public List<DataResult> getAll(String query) {
		List<DataResult> results = null;
		Session session = null;
		try {
			session = datasource.connect();
			//Value value = Values.parameters(params);
			List<Record> records = session.run(query).list();
			results = toResults(records);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			datasource.disconnect(session);
		}
		return results;
	}

	public void execute(String query, Object... params) {
		Session session = null;
		try {
			session = datasource.connect();
			Value value = Values.parameters(params);
			session.run(query, value);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			datasource.disconnect(session);
		}
	}
	
	private List<DataResult> toResults(List<Record> records) {
		List<DataResult> results = new ArrayList<DataResult>();
		for (Record record : records) {
			results.add(new DataResult(record));
		}
		return results;
	}

}
