package jenaapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;

import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class RDFTopology{
	
	/**
	 * Feedback Sophie: don't take subqueries, makes results uncomparable with others, use Queries 1, 3, 4, 5, 10, 11
	 * For query 4: add more bolts
	 * Start with the simple ones (4th later)
	 * 
	 * Extra package for benchmark tests, for testing, images of the queries are at http://swat.cse.lehigh.edu/projects/lubm/lubm.jpg
	 * The original text queries can be found at http://swat.cse.lehigh.edu/projects/lubm/queries-sparql.txt
	 * 
	 */
	
public static BufferedReader reader;
	
	public static void main(String[] args) throws Exception{
		System.out.println("Jenaapi");
		 // create an empty model
		 Model model = ModelFactory.createDefaultModel();

		 String inputFileName="./data/University0_0.daml";
		 
		 
		 // use the FileManager to find the input file
		 InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File: " + inputFileName + " not found");
		}

		// read the RDF/XML file
		model.read(in, null);
		
		
		// list the statements in the Model
		StmtIterator iter = model.listStatements();
		

		// print out the predicate, subject and object of each statement
		while (iter.hasNext()) {
		    Statement stmt      = iter.nextStatement();  // get next statement
		    Resource  subject   = stmt.getSubject();     // get the subject
		    Property  predicate = stmt.getPredicate();   // get the predicate
		    RDFNode   object    = stmt.getObject();      // get the object

		    System.out.print(subject.toString());
		    System.out.print(" " + predicate.toString() + " ");
		    if (object instanceof Resource) {
		       System.out.print(object.toString());
		    } else {
		        // object is a literal
		        System.out.print("\"" + object.toString() + "\"");
		    }

		    System.out.println(" .");
		}
		
		//not really working way
		/*
		Model rdfModel = ModelFactory.createDefaultModel().read("./data/generated_data/University0_0.daml");
		String NS = "http://www.Department0.University0.edu";
		Resource prof;
		for(int i = 0; i < 100; i++){
			System.out.println("prof: " + i);
			prof = rdfModel.getResource( NS + "FullProfessor" + i);
			for (StmtIterator iter = prof.listProperties(); iter.hasNext(); ) {

				System.out.println("iteration");
			    Statement s = iter.next();
			    System.out.println( "FullProffesor " + i + " has property " + s.getPredicate() + 
			                        " with value " + s.getObject() );
			}
		} 
		*/
	
		//rdfModel

		//old stuff - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		/*		
		String filePath="./data/rdfdata.txt";
		//String filePath="./data/generated_data/University0_0.daml";
		File file = new File(filePath);
		reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
			stormCall();
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader != null){
				try{
					reader.close();
				}catch(IOException e1){
					//Do nothing
				}
			}
		}
		*/
	}

}
