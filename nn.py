from keras.models import Sequential
from keras.layers import Dense

import numpy
import math
import sklearn

def isclose(a, b, rel_tol=1e-09, abs_tol=0.0):
    return abs(a-b) <= max(rel_tol * max(abs(a), abs(b)), abs_tol)

numpy.random.seed(10)


dataset = numpy.loadtxt("pima-indians-diabetes.csv", delimiter=",")


Z = dataset[:,0:8]
Q = dataset[:,8]


model = Sequential()
model.add(Dense(12, input_dim=8, activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(1, activation='sigmoid'))


model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])


model.fit(Z, Q, epochs=150, batch_size=10)


predictions = model.predict(Z)

rounded = [round(x[0]) for x in predictions]
print(rounded)


scores = model.evaluate(Z, Q)
print("\n%s: %.2f%%" % (model.metrics_names[1], scores[1]*100))

pmodel = Sequential()
pmodel.add(Dense(12, input_dim=8, activation='relu'))
pmodel.add(Dense(8, activation='relu'))
pmodel.add(Dense(1, activation='sigmoid'))

oldw=model.get_weights()
pmodel.set_weights(oldw)

pmodel.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])

res=pmodel.predict(numpy.asmatrix("[['1','85','66','29','0','26.6','0.351','31.0']]"))

rounded = [round(x[0]) for x in res]
#if numpy.testing.assert_almost_equal(rounded,0):
#print(type(rounded[0]))
if isclose(rounded[0],0.0,rel_tol=1e-5):
    #if rounded=='0.0':
    print("Patient will likely not have diabetes")
else:
    print("Patient is likely to have diabetes")
print (res)