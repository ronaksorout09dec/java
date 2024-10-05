import pandas as pd
import matplotlib.pyplot as plt

# Load the data from the Excel file
data = pd.read_excel('House_Rates.xls')

# Extract the num_bedrooms attribute
num_bedrooms = data['bedrooms']

# Create a histogram
hist = num_bedrooms.hist(bins=30)

# Set the title and labels
hist.set_title('Distribution of Number of Bedrooms')
hist.set_xlabel('Number of Bedrooms')
hist.set_ylabel('Frequency')

# Display the histogram
plt.show()