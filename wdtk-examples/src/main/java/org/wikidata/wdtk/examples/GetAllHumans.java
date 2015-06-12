package org.wikidata.wdtk.examples;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.wikidata.wdtk.datamodel.helpers.Datamodel;
import org.wikidata.wdtk.datamodel.helpers.DatamodelConverter;
import org.wikidata.wdtk.datamodel.interfaces.PropertyIdValue;
import org.wikidata.wdtk.datamodel.json.jackson.JacksonObjectFactory;
import org.wikidata.wdtk.datamodel.json.jackson.JsonSerializer;
import org.wikidata.wdtk.dumpfiles.DumpProcessingController;

/**
 * Save on an output file all the people found in a local dump file.
 * @author gl26163
 *
 */
public class GetAllHumans {
	//Dumpfiles parent directory path. 
	//It is important that the json dump file to be use is saved in a directory with the next format:+
	//		[dumpFilesParentDirectoryPath]\dumpfiles\[wikimedia project name]\json-[date]
	//Where:
	//	wikimedia project 		-	is the wiki project from where the dump file were downloaded (e.g wikidatawiki)
	//	json-date				-	is the json dump file name, which fallow the next format json-[date], where the date have to be YYYYMMDD (e.g json-20150608)
	static final String dumpFileDirectory = "H:\\EclipseWorkspace\\RolesAnnotation\\wdtk-parent\\wdtk-examples"; 
	
	//Results file name
	static final String OUTPUT_FILE_NAME = "json-wikidata-allpeople.json.gz";
	
	//Project name
	static final String PROJECT_NAME = "wikidatawiki";
	
	
	
	//***************************************************************
	//							MAIN METHOD
	//***************************************************************
	public static void main(String[] input) throws IOException{
		
		//Set Logger configuration.
		ExampleHelpers.configureLogging();
		
		//Print basic documentation in the console
		printDocumentation();
		
		
		JsonSerializationInstanceOfProcessor jsonSerializationProcessor = JsonSerializationInstanceOfProcessor.getInstanceOf();
		jsonSerializationProcessor.openJsonSerializer();
		ExampleHelpers
				.processEntitiesFromWikidataDump(jsonSerializationProcessor);
		jsonSerializationProcessor.closeJsonSerializer();

		

		printcompletedMessage();
		
		
	}
	
	
	
	
	/**
	 * Prints some basic documentation about this program.
	 */
	public static void printDocumentation() {
		System.out
				.println("********************************************************************");
		System.out.println("*** Wikidata Toolkit: GetAllPersons");
		System.out.println("*** ");
		System.out
				.println("*** This program will extract all persons from a local dumps from Wikidata.");
		System.out
				.println("*** It will filter the data and store the results in a new JSON file.");
		System.out.println("*** See source code for further details.");
		System.out
				.println("********************************************************************");
	}
	
	/**
	 * Prints completed message.
	 */
	public static void printcompletedMessage() {
		System.out
				.println("\n\n********************************************************************");
		System.out.println("*** Wikidata Toolkit: GetAllPersons");
		System.out.println("*** ");
		System.out
				.println("*** Process completed");
		System.out.println("*** ");
		System.out
				.println("********************************************************************");
	}
	

	

}


