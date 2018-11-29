package Clasificadores;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class RandForest {
	
	
	//EXPLICAR CODIGO

	public RandomForest InstanciadoRFconOptimoFM(Instances DatosFiltrados, int NumInstancias) throws Throwable {
		RandomForest rf = new RandomForest();
		Evaluation evaluator = new Evaluation(DatosFiltrados);
		double MFMeasure = 0;
		int IndiceOptimo = 0;
		for (int i = 2; i <= NumInstancias / 2; i++) {
			rf.setNumIterations(i);
			evaluator.crossValidateModel(rf, DatosFiltrados, 10, new Random(19));
			if (MFMeasure <= evaluator.weightedFMeasure()) {
				MFMeasure = evaluator.weightedFMeasure();
				IndiceOptimo = i;
			}
		}
		rf.setNumIterations(IndiceOptimo);
		return rf;
	}
}
