import pandas as pd

df = pd.read_excel('House_Rates.xls')

missing_prices = df['price'].isnull().sum()
print(f"Missing values in 'price' column: {missing_prices}")

df['price'].fillna(df['price'].median(), inplace=True)

print(df['price'].describe())
