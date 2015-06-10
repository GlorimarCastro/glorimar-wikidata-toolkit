package org.wikidata.wdkt.enums;

public enum Entity {
	HUMAN("Q5");
	
	private String entityId;

	private Entity(String entityId) {
		this.entityId = entityId;
	}
	
	public String toString(){
		return entityId;
	}
}