import pandas as pd
import numpy as np

# Load the Excel file
file_path = 'House_rates.xls'  # Replace with the actual path to your file
df = pd.read_excel(file_path)

# Calculate mean, median, and standard deviation for house_price
house_prices = df['house_price']

mean_price = np.mean(house_prices)
median_price = np.median(house_prices)
std_price = np.std(house_prices)

print(f"Mean house price: {mean_price}")
print(f"Median house price: {median_price}")
print(f"Standard deviation of house prices: {std_price}")
