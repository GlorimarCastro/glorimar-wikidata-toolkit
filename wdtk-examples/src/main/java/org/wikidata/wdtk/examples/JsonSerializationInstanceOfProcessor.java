package org.wikidata.wdtk.examples;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Set;

import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.wikidata.wdtk.datamodel.helpers.DatamodelConverter;
import org.wikidata.wdtk.datamodel.interfaces.EntityDocumentProcessor;
import org.wikidata.wdtk.datamodel.interfaces.ItemDocument;
import org.wikidata.wdtk.datamodel.interfaces.ItemIdValue;
import org.wikidata.wdtk.datamodel.interfaces.PropertyDocument;
import org.wikidata.wdtk.datamodel.interfaces.PropertyIdValue;
import org.wikidata.wdtk.datamodel.interfaces.Statement;
import org.wikidata.wdtk.datamodel.interfaces.StatementGroup;
import org.wikidata.wdtk.datamodel.interfaces.Value;
import org.wikidata.wdtk.datamodel.interfaces.ValueSnak;
import org.wikidata.wdtk.datamodel.json.jackson.JacksonObjectFactory;
import org.wikidata.wdtk.datamodel.json.jackson.JsonSerializer;
import org.wikidata.wdkt.enums.*;

/**
 * Class to extract all entities that are "instance of" a specified class. 
 * @author Glorimar Castro-Noriega
 *
 */
public class JsonSerializationInstanceOfProcessor implements EntityDocumentProcessor{
	
	public static  String 	OUTPUT_FILE_NAME;
	
	/**
	 * Property Id to look for in the instance of value
	 */
	public static 	String propertyClass;
	
	/**
	 * To copy referance or not
	 */
	public static boolean copyReferences = false;
	
	
	/**
	 * Json Serializer to use to process the items
	 */
	private static 	JsonSerializer jsonSerializer;
	
	private static DatamodelConverter datamodelConverter;
	
	private final static String INSTANCE_OF_PROPERTY = Property.INTANCE_OF.toString();
	
	private static 	JsonSerializationInstanceOfProcessor jsonProcessor;
	
	//--------------------------------------------------
	//Constructor
	//--------------------------------------------------
	/**
	 * Creates a new JsonSerializationInstanceOfProcessor object. By default
	 * the instance of to look for is set to human. The properties filter are not
	 * set  and the default language is English. Also the default value for revisions
	 * are set to get the current revisions. 
	 * @throws IOException 
	 */
	private  JsonSerializationInstanceOfProcessor() throws IOException {
		propertyClass 		= Entity.HUMAN.toString();
		OUTPUT_FILE_NAME 	= "json-serialization-instanceOf.json.gz";
		
		datamodelConverter = new DatamodelConverter(new JacksonObjectFactory());
		datamodelConverter.setOptionDeepCopyReferences(copyReferences);
		datamodelConverter.setOptionLanguageFilter(Collections.singleton(Language.ENGLISH.toString()));
		// Do not copy any sitelinks:
		datamodelConverter.setOptionSiteLinkFilter(Collections.<String> emptySet());
	
		// The (compressed) file we write to.
		OutputStream outputStream = new GzipCompressorOutputStream(
				new BufferedOutputStream(
						ExampleHelpers
								.openExampleFileOuputStream(OUTPUT_FILE_NAME)));
		jsonSerializer = new JsonSerializer(outputStream);

		
	}
	
	
	
	
	
	
	
	//--------------------------------------------------
	//MATHODS
	//--------------------------------------------------
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public static JsonSerializationInstanceOfProcessor getInstanceOf() throws IOException{
		if(jsonProcessor == null){
			return new JsonSerializationInstanceOfProcessor();
		}
		return jsonProcessor;
	}
	
	/**
	 *Set the properties filter to the datamodel converter object. Use this method
	 *if only some statements of some properties are wanted. 
	 */
	public void setPropertiesFilters(Set<PropertyIdValue> propertyFilter){
		datamodelConverter.setOptionPropertyFilter(propertyFilter);
	}
	
	
	public void openJsonSerializer(){
		jsonSerializer.open();
	}
	
	public void closeJsonSerializer(){
		jsonSerializer.close();
	}
	
	/**
	 * This method process the items and serialize the ones that qualify
	 * with the specified requirements. 
	 * 
	 */
	@Override
	public void processItemDocument(ItemDocument itemDocument) {
		if (includeDocument(itemDocument)) {
			this.jsonSerializer.processItemDocument(this.datamodelConverter
					.copy(itemDocument));
		}
		
	}

	@Override
	public void processPropertyDocument(PropertyDocument propertyDocument) {
		//throw new UnsupportedOperationException("Property Document are not being processed");
		
	}
	
	/**
	 * Returns true if the given document should be included in the
	 * serialization.
	 *
	 * @param itemDocument
	 *            the document to check
	 * @return true if the document should be serialized
	 */
	private boolean includeDocument(ItemDocument itemDocument) {
		for (StatementGroup sg : itemDocument.getStatementGroups()) {
			if (!Property.INTANCE_OF.toString().equals(sg.getProperty().getId())) {
				continue;
			}
			for (Statement s : sg.getStatements()) {
				if (s.getClaim().getMainSnak() instanceof ValueSnak) {
					Value v = ((ValueSnak) s.getClaim().getMainSnak())
							.getValue();
					if (v instanceof ItemIdValue
							&& Entity.HUMAN.toString().equals(((ItemIdValue) v).getId())) {
						System.out.println(v.toString());
						System.out.println(s.getClaim().toString());
						return true;
					}
				}
			}
		}
		return false;
	}

}
