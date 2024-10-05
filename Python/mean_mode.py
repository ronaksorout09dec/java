import numpy as np
import pandas as pd

# Load the data from the Excel file
data = pd.read_excel('House_Rates.xls')

# Extract the house_price attribute
house_prices = data['price']

# Calculate the mean, median, and standard deviation
mean_price = np.mean(house_prices)
median_price = np.median(house_prices)
std_dev_price = np.std(house_prices)

print(f"Mean House Price: {mean_price}")
print(f"Median House Price: {median_price}")
print(f"Standard Deviation of House Price: {std_dev_price}")