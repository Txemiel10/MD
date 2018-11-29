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
		private Instances DatosFiltrados;
		
	public OneR InstanciadoOneR(Instances DatosEntrenamiento) throws Throwable {
		OneR oneR = new OneR();
		oneR.buildClassifier(DatosEntrenamiento);
		return oneR;
	}
	
	public void EvaluarDatos(OneR oneR, Instances DatosTest) throws Throwable{
		Evaluation evaluator1 = new Evaluation(DatosFiltrados);
		evaluator1.evaluateModel(oneR, DatosTest);
	}
}
