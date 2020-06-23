package braviner.java_inference;

import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;

public class Inference {

    public static void main(String[] args) throws XGBoostError {

        Booster booster = XGBoost.loadModel("../serialized_model");

        float[] inference_features = new float[] {1.0F};

        DMatrix inference_dmatrix = new DMatrix(inference_features, 1, 1, Float.NaN);

        float[][] prediction = booster.predict(inference_dmatrix);
    }

}
