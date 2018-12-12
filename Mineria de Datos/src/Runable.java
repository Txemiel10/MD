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
	 * El ejecutable necesita N argumentos: 1º args[0] path donde coger los datos ->
	 * ej: /some/where/breast-cancer.arff 2º args[1] path donde guardar los
	 * resultados -> ej: /some/where/results.txt 3º args[2] path donde cargar o
	 * guardar el modelo -> ej: /some/where/oner.model
	 * 
	 */

	/**
	 * Ejecuta el programa realizando TF-IDF.
	 *
	 * @param args Parámetros de entrada. En caso de no introducir ninguno se
	 *             muestra una descripción de estos.
	 * 
	 * @param      args[0] path donde coger los datos -> ej:
	 *             /some/where/breast-cancer.arff
	 * @param      args[1] path donde guardar los resultados -> ej:
	 *             /some/where/results.txt
	 * @param      args[2]
	 * @throws Throwable
	 * 
	 * 
	 */
	public static void main(String[] args) throws Throwable {

		if (args.length == 0) {
			System.out.println("=====Main=====");
			System.out.println("Este programa tiene como función obtener un fichero el cual tiene los resultados"
					+ "de la aplicación del clasificador seleccionado sobre los datos de Autopsias ");
			System.out.println("Este programa necesita que introduzcas 4 argumentos para funcionar correctamente.");
			System.out.println("PRECONDICIONES:\nEl primer argumento será el path del fichero a convertir/n"
					+ " y el segundo es el path del fichero de salida./n"
					+ " el tercer argumento responde al tipo de classificador, si introducimos 'rf' se hara el Random Forest"
					+ " y cualquier otro imput seleccionara el OneR/n"
					+ " el cuarto argumento indica si queremos o no un filtrado de atributos donde se seleccionarán/n"
					+ " los 110 más influentes para la decisión de la clase");
			System.out.println("POSTCONDICIONES:\nEl resultado de esta aplicación será la creación de un fichero .arff "
					+ "en el path especificado en los argumentos\n");
			System.out.println("Lista de argumentos:\n--Opción para el input:\n"
					+ "-- Path de la raíz del fichero en texto plano a convertir."
					+ "\n-- Path del destino donde se guardará el fichero resultante tras la ejecución"
					+ "\n-- 'rf' o 'OneR' Clasificador seleccionado"
					+ "\n-- 'Si' en caso de que se deseen los datos filtrados");
			System.out.println(
					"Ejemplo de una correcta ejecución: java -jar name.jar /path/to/file /path/to/Result.arff rf Si");
			System.exit(0);
		} else if (args.length != 4) {
			System.out.println("Error en el input. Revise su sintaxis.");
			System.exit(1);
		} else {

			String DPath = "C:/tmp/Autopsias_GrandeTfidfs.arff";// args[0];
			String OPath = "C:/tmp/Res.arff";// args[1];
			String Classif = "rf";// args[2];
			String Filtro = "Si";// args[3];
			Instances datos = null;
			try {
				DataSource source = new DataSource(DPath);
				datos = source.getDataSet();
				if (datos.classIndex() == -1)
					datos.setClassIndex(datos.numAttributes() - 1);
			} catch (Exception e) {
				System.out.println("Error al leer los datos de " + DPath);
				e.printStackTrace();
				System.exit(1);
			}

			if (Classif.equals("rf")) {
				System.out.println("Seleccionado RandomForest");
				RandForest rf = new RandForest();
				rf.ejecutar(datos, Filtro, OPath);
			} else {
				System.out.println("Seleccionado OneR");
				One_R or = new One_R();
				or.ejecutar(datos, Filtro, OPath);
			}
		}
	}
}