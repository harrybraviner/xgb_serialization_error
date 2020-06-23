import numpy as np
import xgboost as xgb

assert xgb.__version__ == '1.0.0'

# Make up some synthetic data
rng = np.random.RandomState(123)
N = 1000

x_data = rng.uniform(low=0.0, high=2.0, size=(N, 1))
y_data = x_data * 0.3

data = xgb.DMatrix(data=x_data, label=y_data)

booster = xgb.train(
    params={'booster': 'gbtree',
            'eta': 0.1},
    dtrain=data,
    num_boost_round=3)

booster.save_model('./serialized_model')

