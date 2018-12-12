package Clasificadores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.rules.OneR;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class One_R {
		
		
		public void ejecutar(Instances datos,String F,String OPath) throws Exception{
			
			//if(F=="Si"){
			System.out.println("Aplicando InfoGain");
				AttributeSelection filtro = new AttributeSelection();
				InfoGainAttributeEval eval = new InfoGainAttributeEval();
				Ranker search = new Ranker();
				search.setNumToSelect(110);
				filtro.setEvaluator(eval);
				filtro.setSearch(search);
				filtro.setInputFormat(datos);
				Instances DatosFiltrados = Filter.useFilter(datos, filtro);
				datos=DatosFiltrados;
				System.out.println("Aplicado Infogain");
				//}
			
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
