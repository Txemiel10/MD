package Caracterizacion;

import java.io.File;
import java.io.FileReader;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Reorder;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class TFIDF {

	/**
	 * Transforma el espacio de atributos del conjunto de entrenamiento a
	 * TF-IDF.
	 *
	 * @param args
	 *            Parámetros de entrada. En caso de no introducir ninguno se
	 *            muestra una descripción de estos.
	 */

	public static void main(String[] args) throws Exception {

		if (args.length == 0) {
			System.out.println("=====fssTFIDF=====");
			System.out.println(
					"Este programa tiene como función transformar el espacio de atributos del conjunto de entrenamiento a TFIDF ");
			System.out.println("Este programa necesita que introduzcas 2 argumentos para funcionar correctamente.");
			System.out.println(
					"PRECONDICIONES:\nEl primer argumento será el path del conjunto de entrenamiento a transformar. "
							+ "El segundo es el path destino. ");
			System.out.println(
					"POSTCONDICIONES:\nEl resultado de esta aplicación será una representación TFIDF del espacio de atributos del conjunto de entrenamiento.\n");
			System.out.println("Lista de argumentos:\n-Path del conjunto de entrenamiento a transformar.\n"
					+ "-Path de salida.\n");
			System.out.println(
					"Ejemplo de una correcta ejecución: java -jar fssTFIDF.jar /path/to/train.arff /path/to/trainTFIDF.arff");
			System.exit(0);
		} else {
			String pathIn = "";
			String pathOut = "";

			if (args.length == 2) {
				pathIn = args[0];
				pathOut = args[1];
			} else {
				printlnError("Error en el input. Revise su sintaxis.");
				System.exit(1);
			}
			Instances data = loadArff(pathIn, -1);
			data.setClassIndex(data.numAttributes() - 1);
			StringToWordVector filter;
			Instances dataFiltered = null;
			String relationName = data.relationName();
			/*
			 * Transformamos el arff raw a TFIDF.
			 */
			filter = new StringToWordVector(99999);
			filter.setTFTransform(true);
			filter.setIDFTransform(true);
			filter.setOutputWordCounts(true);
			// filter.setLowerCaseTokens(true);
			filter.setInputFormat(data);
			dataFiltered = Filter.useFilter(data, filter);
			// SI ELEGIMOS NON-SPARSE
			// SparseToNonSparse sparseFilter = new SparseToNonSparse();
			// sparseFilter.setInputFormat(dataFiltered);
			// dataFiltered = Filter.useFilter(dataFiltered, sparseFilter);
			/*
			 * Hacemos que la clase sea el último atributo
			 */
			Reorder reorderFilter = new Reorder();
			reorderFilter.setInputFormat(dataFiltered);
			reorderFilter.setOptions(new String[] { "-R", "2-last,1" });
			dataFiltered = Filter.useFilter(dataFiltered, reorderFilter);
			dataFiltered.setClassIndex(dataFiltered.numAttributes() - 1);
			/*
			 * Damos a la relación su nombre original
			 */
			dataFiltered.setRelationName(relationName);
			/*
			 * guardamos los datos en el path especificado
			 */
			saveArff(dataFiltered, pathOut);
		}
	}

	/**
	 * Carga el Arff.
	 *
	 * @param path
	 *            Ruta de donde se cargará el fichero.
	 * @param classIndex
	 *            Indice de la clase.
	 */
	public static Instances loadArff(String path, int classIndex) {

		FileReader file;

		try {
			file = new FileReader(path);
			file.close();
		} catch (Exception e) {
			printlnError("ERROR CARGANDO LAS INSTANCIAS: Comprueba el path del fichero: " + path);
			System.exit(1);
		}
		Instances data = null;

		try {
			ConverterUtils.DataSource ds = new ConverterUtils.DataSource(path);
			data = ds.getDataSet();
		} catch (Exception e) {
			printlnError("ERROR LEYENDO LAS INSTANCIAS: Comprueba la estructura interna del fichero: " + path);
			System.exit(1);
		}

		if (classIndex < 0) {
			data.setClassIndex(data.numAttributes() - 1);
		} else {
			data.setClassIndex(classIndex);
		}

		return data;
	}

	/**
	 * Guarda las instancias instances en la ruta pathOut.
	 *
	 * @param instances
	 *            Conjunto de instancias a convertir y guardar en el fichero
	 *            .arff.
	 * @param pathOut
	 *            Ruta donde se guardará el fichero resultante de la conversión.
	 */
	public static void saveArff(Instances instances, String pathOut) {
		try {
			ArffSaver arffSaver = new ArffSaver();
			arffSaver.setInstances(instances);
			arffSaver.setFile(new File(pathOut));
			arffSaver.writeBatch();
		} catch (Exception e) {
			printlnError("ERROR AL GUARDAR LAS INSTANCIAS: Comprueba el path: " + pathOut);
			e.printStackTrace();
		}
	}

	public static void printlnError(String pText) {
		printError(String.format("%s\n", pText));
	}

	private static void printError(String pText) {
		System.out.print(String.format("\33[31m%s\33[0m", pText));
	}

}
