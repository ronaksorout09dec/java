import pandas as pd
import numpy as np
# Load the data from the Excel file
data = pd.read_excel('House_Rates.xls')

# Extract the price attribute
prices = data['price']

# Calculate the minimum and maximum values
min_price = np.min(prices)
max_price = np.max(prices)

# Scale the price attribute using Min-Max normalization
scaled_prices = (prices - min_price) / (max_price - min_price)

# Print the scaled prices
print(scaled_prices)