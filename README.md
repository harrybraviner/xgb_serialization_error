XGBoost models serialized from version 1.0.0 fail when loaded by versions 0.90 and 0.82.

# Steps to reproduce

1. Setup a python environment with xgboost version 1.0.0 installed and run the python code
```
python model_gen.py
```

2. Change to the directory containing the java code and compile and run it
```
cd java_inference
mvn compile
mvn exec:java -Dexec.mainClass="braviner.java_inference.Inference"
```

You should see the error
```
java.lang.NullPointerException
	at ml.dmlc.xgboost4j.java.Booster.predict(Booster.java:309)
	at ml.dmlc.xgboost4j.java.Booster.predict(Booster.java:352)
	at braviner.java_inference.Inference.main(Inference.java:18)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.codehaus.mojo.exec.ExecJavaMojo$1.run(ExecJavaMojo.java:282)
	at java.lang.Thread.run(Thread.java:748)
```


If you use version 0.82 (change the `pom.xml` file) you'll get this instead:
```
ml.dmlc.xgboost4j.java.XGBoostError: [21:06:09] /xgboost/src/objective/objective.cc:23: Unknown objective function reg:squarederror

Stack trace returned 6 entries:
[bt] (0) /tmp/libxgboost4j3845889851773273549.so(dmlc::StackTrace(unsigned long)+0x51) [0x7ff16d28ef21]
[bt] (1) /tmp/libxgboost4j3845889851773273549.so(xgboost::ObjFunction::Create(std::string const&)+0x449) [0x7ff16d383719]
[bt] (2) /tmp/libxgboost4j3845889851773273549.so(xgboost::LearnerImpl::Load(dmlc::Stream*)+0x397) [0x7ff16d325ee7]
[bt] (3) /tmp/libxgboost4j3845889851773273549.so(XGBoosterLoadModel+0x37) [0x7ff16d294b47]
[bt] (4) /tmp/libxgboost4j3845889851773273549.so(Java_ml_dmlc_xgboost4j_java_XGBoostJNI_XGBoosterLoadModel+0x2f) [0x7ff16d28a83f]
[bt] (5) [0x7ff175018427]


	at ml.dmlc.xgboost4j.java.XGBoostJNI.checkCall(XGBoostJNI.java:48)
	at ml.dmlc.xgboost4j.java.Booster.loadModel(Booster.java:67)
	at ml.dmlc.xgboost4j.java.XGBoost.loadModel(XGBoost.java:41)
	at braviner.java_inference.Inference.main(Inference.java:12)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.codehaus.mojo.exec.ExecJavaMojo$1.run(ExecJavaMojo.java:282)
	at java.lang.Thread.run(Thread.java:748)
```
