import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_excel('House_Rates.xls')

num_bedrooms = data['bedrooms']

hist = num_bedrooms.hist(bins=30)

hist.set_title('Distribution of Number of Bedrooms')
hist.set_xlabel('Number of Bedrooms')
hist.set_ylabel('Frequency')

plt.show()