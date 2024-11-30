import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_excel('House_Rates.xls')
plt.scatter(data['sqft_lot'], data['price'])
plt.title('Scatter Plot of House Size vs House Price')
plt.xlabel('House Size')
plt.ylabel('House Price')
plt.show()