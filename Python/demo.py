import pandas as pd 
import matplotlib.pyplot as plt

Data = {
    'Month' : ['Jan','Feb','Mar','Apr','May','June'],
    'Sales' : ['1520','1800','1700','1600','1500','2100'],
    }

df = pd.DataFrame(Data)
print("Sales Data: \n",df)