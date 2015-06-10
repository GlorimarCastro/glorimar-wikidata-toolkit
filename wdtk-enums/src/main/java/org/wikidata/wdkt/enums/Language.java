package org.wikidata.wdkt.enums;

public enum Language {
	ENGLISH ("en");
	
	
	private String language;

	private Language(String language) {
		this.language = language;
	}
	
	public String toString(){
		return language;
	}
}
