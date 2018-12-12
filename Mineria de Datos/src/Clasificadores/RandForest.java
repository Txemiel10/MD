package Clasificadores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Evaluation;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class RandForest {
	
	
	//EXPLICAR CODIGO

	public void ejecutar (Instances datos,String F, String OPath) throws Throwable {
		
		
		if(F=="Si"){
		AttributeSelection filtro = new AttributeSelection();
		InfoGainAttributeEval eval = new InfoGainAttributeEval();
		Ranker search = new Ranker();
		filtro.setEvaluator(eval);
		filtro.setSearch(search);
		filtro.setInputFormat(datos);
		Instances DatosFiltrados = Filter.useFilter(datos, filtro);
		datos=DatosFiltrados;
		}
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
