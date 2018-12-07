package PreProcessing;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.File;

public class CsvToArff {

   public static void main(String[] args) throws Exception {


    // load CSV
    CSVLoader loader = new CSVLoader();
    loader.setSource(new File("Provide the input file location (.csv) "));
    Instances data = loader.getDataSet();

    // save ARFF
    ArffSaver saver = new ArffSaver();
    saver.setInstances(data);
    saver.setFile(new File("Provide the output file location (.arff) "));
    saver.writeBatch();
    // .arff file will be created in the output location
  }
}
