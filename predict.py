import tensorflow as tf
import numpy as np

def predict(pm10, so2, co, o3, no2):
    model = tf.keras.models.load_model('best_model.h5')
    X = np.array([pm10, so2, co, o3, no2], dtype=float)[np.newaxis]
    result = model.predict_classes(X)[0]
    return result