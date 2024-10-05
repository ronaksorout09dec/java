import numpy as np
import pandas as pd

# Load the data from the Excel file
data = pd.read_excel('House_Rates.xls')

# Extract the house_size attribute
# house_sizes = data['sqft_lot']
house_sizes = data['sqft_living']

# Calculate the five-number summary
min_size = np.min(house_sizes)
Q1_size = np.percentile(house_sizes, 25)
median_size = np.median(house_sizes)
Q3_size = np.percentile(house_sizes, 75)
max_size = np.max(house_sizes)

print(f"Minimum House Size: {min_size}")
print(f"First Quartile (Q1) House Size: {Q1_size}")
print(f"Median House Size: {median_size}")
print(f"Third Quartile (Q3) House Size: {Q3_size}")
print(f"Maximum House Size: {max_size}")