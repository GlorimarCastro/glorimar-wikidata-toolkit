package org.wikidata.wdkt.enums;

public enum Property {
	INTANCE_OF("P31"),
	OCCUPATION("P106");
	
	private String property;

	private Property(String property) {
		this.property = property;
	}
	
	public String toString(){
		return property;
	}
}
