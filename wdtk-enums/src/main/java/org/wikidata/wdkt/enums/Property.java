package org.wikidata.wdkt.enums;

public enum Property {
	INTANCE_OF("P31", "instance of"),
	OCCUPATION("P106", "occupation"),
	GENDER("P21", "gender"),
//	DATE_OF_BIRTH("P569"),
//	PLACE_OF_BIRTH("P19"),
//	BIRTH_NAME("P1477"),
//	DATE_OF_DEATH("P570"),
//	PLACE_OF_DEATH("P20"),
//	CAUSE_OF_DEATH("P509"),
//	MANNER_OF_DEATH("P1196"),
//	KILLED_BY("P157"),
//	PLACE_OF_BURIAL("P119"),
//	NAME_IN_NATIVE_LANGUAGE("1559"),
	MEMBER_OF("P463", "member of"),
//	ETHNIC_GROUP("P172"),
//	NATIVE_LANGUAGE("P103"),
//	COUNTRY_OF_CITIZENSHIP("P27"),
//	EDUCATED_AT("P69"),
//	FIELD_OF_WORK("P101"),
//	MEMBER_OF_POLITICAL_PARY("P102"),
//	OFFICIAL_RESIDENCE("P263"),
	RELIGION("P140", "religion");
//	ACADEMIC_DEGREE("P512"),
//	WEBSITE_ACCOUNTS_ON("P553"),
//	PSEUDONYM("P742"),
//	MEMBER_OF_SPORTS_TEAM("P54"),
//	POSITION_PLAYED_ON_TEAM("P413");
	
	
	
	
	
	private String property;
	private String text;

	private Property(String property, String text) {
		this.property = property;
		this.text = text;
	}
	
	public String toString(){
		return property;
	}
	
	public String getText(){
		return text;
	}
}
