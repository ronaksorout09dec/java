import pandas as pd

file_path = 'House_rates.xls'
df = pd.read_excel(file_path)

house_prices = df['price']

min_price = house_prices.min()
max_price = house_prices.max()

df['house_price_normalized'] = (house_prices - min_price) / (max_price - min_price)

min_normalized = df['house_price_normalized'].min()
max_normalized = df['house_price_normalized'].max()

print(f"New minimum house price (after normalization): {min_normalized}")
print(f"New maximum house price (after normalization): {max_normalized}")
