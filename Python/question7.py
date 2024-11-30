import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_excel('House_Rates.xls')
plt.boxplot(data['price'])
plt.title('Boxplot of House Prices')
plt.ylabel('Price')
plt.show()