import pandas as pd
import numpy as np

file_path = 'House_Rates.xls'
df = pd.read_excel(file_path)

def decimal_scaling(column):
    max_value = column.abs().max()
    scaling_factor = 10 ** np.ceil(np.log10(max_value))
    normalized_column = column / scaling_factor
    return normalized_column

df['sqft_lot_normalized'] = decimal_scaling(df['sqft_lot'])
df['bedrooms_normalized'] = decimal_scaling(df['bedrooms'])

print("Original and Normalized Data:")
print(df[['sqft_lot', 'bedrooms', 'sqft_lot_normalized', 'bedrooms_normalized']])

print("\nSummary Statistics After Decimal Scaling Normalization:")
print(df[['sqft_lot_normalized', 'bedrooms_normalized']].describe())
