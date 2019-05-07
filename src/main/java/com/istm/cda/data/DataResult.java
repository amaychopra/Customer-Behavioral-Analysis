package com.istm.cda.data;

import org.neo4j.driver.v1.Record;

/**
 * Neo4j Result implementation
 * @author Team 4 (601)
 */
public class DataResult{

	private Record record;
	
	public DataResult(Record record) {
		this.record = record;
	}
	
	public String get(String key) {
		return String.valueOf(record.get(key));
	}

	public String get(int index) {
		return String.valueOf(record.get(index));
	}
	

}
