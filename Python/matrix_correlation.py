import pandas as pd

# Load the data from the Excel file
data = pd.read_excel('House_Rates.xls')

# Select the relevant attributes
attributes = ['price', 'sqft_lot', 'bedrooms']
selected_data = data[attributes]

# Calculate the correlation matrix
correlation_matrix = selected_data.corr()

# Print the correlation matrix
print(correlation_matrix)