import pandas as pd
from matplotlib import pyplot as plt
 
columns = ['AvoreAVL-Ordenada', 'AvoreRubroNegra-Ordenada',  
'ArvoreB-Ordenada-1' , 'ArvoreB-Ordenada-5' , 'ArvoreB-Ordenada-10']

df = pd.read_csv("output.csv", usecols=columns, sep=";")
df.plot()

columns2 = ['AvoreAVL-Aleatoria', 'AvoreRubroNegra-Aleatoria', 
'ArvoreB-Aleatoria-1', 'ArvoreB-Aleatoria-5', 'ArvoreB-Aleatoria-10']

df2 = pd.read_csv("output.csv", usecols=columns2, sep=";")
df2.plot()

plt.show()