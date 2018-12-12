import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import Clasificadores.One_R;
import Clasificadores.RandForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.BestFirst;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;
import weka.classifiers.rules.OneR;

public class Runable {

/*
* El ejecutable necesita N argumentos:
* 1º args[0] path donde coger los datos -> ej: /some/where/breast-cancer.arff
* 2º args[1] path donde guardar los resultados -> ej: /some/where/results.txt
* 3º args[2] path donde cargar o guardar el modelo -> ej: /some/where/oner.model
* 
*/ 
	
	/**
	 * Ejecuta el programa realizando 
	 * TF-IDF.
	 *
	 * @param args
	 *            Parámetros de entrada. En caso de no introducir ninguno se
	 *            muestra una descripción de estos.
	 *            
	 * @param args[0]   path donde coger los datos -> ej: /some/where/breast-cancer.arff
	 * @param args[1] path donde guardar los resultados -> ej: /some/where/results.txt
	 * @param args[2]
	 * @throws Throwable 
	 * 
	 * 
	 */
	public static void main(String[] args) throws Throwable {
 
	String DPath = "C:/tmp/Autopsias_Tfidf2.arff";//args[0];
	String OPath = "C:/tmp/Res.arff";//args[1];
	String Classif = "OneR";//args[2];
	String Filtro = "Si";//args[3];
	Instances datos = null;
	try {
		DataSource source = new DataSource(DPath);
		datos = source.getDataSet();
	if (datos.classIndex() == -1)
		datos.setClassIndex(datos.numAttributes()-1);
	} catch (Exception e) {
		System.out.println("Error al leer los datos de " + DPath);
		e.printStackTrace();
		System.exit(1);
	}
	
		if(Classif == "rf"){
		RandForest rf = new RandForest();
		rf.ejecutar(datos,Filtro,OPath);
		}
		else{
		One_R or = new One_R();
		or.ejecutar(datos,Filtro,OPath);
		}
	}
}