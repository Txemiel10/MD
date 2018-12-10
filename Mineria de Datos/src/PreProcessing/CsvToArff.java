package PreProcessing;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CsvToArff {

	static void csvv(File inputFile , File outputFile) {

		try {
		    // load CSV
		    CSVLoader loader = new CSVLoader();
		    loader.setSource(inputFile);
		    Instances data = loader.getDataSet();
		
		    // save ARFF
		    ArffSaver saver = new ArffSaver();
		    saver.setInstances(data);
		    saver.setFile(outputFile);
		    saver.writeBatch();
		    // .arff file will be created in the output location
		 } catch (FileNotFoundException e) {
	            e.printStackTrace();
	     } catch (IOException e) {
	            e.printStackTrace();
	     }
  }
   
   public static void main(String[] args) {
       File inputFile = new File(args[0]);
       File outputFile = new File(args[1]);
       csvv(inputFile, outputFile);
   }

}
