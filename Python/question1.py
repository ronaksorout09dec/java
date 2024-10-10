import pandas as pd
import numpy as np

file_path = 'House_rates.xls'
df = pd.read_excel(file_path)

house_prices = df['price'].values

mean_price = np.mean(house_prices)
median_price = np.median(house_prices)
std_price = np.std(house_prices)

print(f"Mean house price: {mean_price}")
print(f"Median house price: {median_price}")
print(f"Standard deviation of house prices: {std_price}")
