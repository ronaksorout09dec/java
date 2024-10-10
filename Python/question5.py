import pandas as pd

data = pd.read_excel('House_Rates.xls')

attributes = ['price', 'sqft_lot', 'bedrooms']
selected_data = data[attributes]

correlation_matrix = selected_data.corr()

print(correlation_matrix)