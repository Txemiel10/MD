package Clasificadores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.rules.OneR;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class One_R {
		
		
		public void ejecutar(Instances datos,String OPath) throws Exception{
			
			OneR oneR = new OneR();
			oneR.buildClassifier(datos);
			System.out.println(oneR.toString());
			Evaluation evaluatorkFold = new Evaluation(datos);
			evaluatorkFold.crossValidateModel(oneR, datos, 10 , new Random(10));
			StringBuilder resultado = new StringBuilder();
			resultado.append("K-FOLD 10\n");
			resultado.append(evaluatorkFold.toSummaryString());
			resultado.append("\n");
			resultado.append(evaluatorkFold.toClassDetailsString());
			resultado.append("\n");
			resultado.append(evaluatorkFold.toMatrixString());
			resultado.append("\n");
			
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(OPath));
			bw.write(resultado.toString());
			bw.close(); 
			System.out.println(resultado);
		}
		

}
