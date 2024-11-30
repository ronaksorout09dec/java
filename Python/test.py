def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1  # Index of the smaller element

    for j in range(low, high):
        # If current element is smaller than or equal to pivot
        if arr[j] <= pivot:
            i += 1
            # Swap arr[i] with arr[j]
            arr[i], arr[j] = arr[j], arr[i]
    
    # Swap the pivot element with the element at i+1
    arr[i+1], arr[high] = arr[high], arr[i+1]
    
    return i + 1  # Return the partition index

def quick_sort(arr, low, high):
    if low < high:
        # pi is the partition index
        pi = partition(arr, low, high)

        # Recursively apply quicksort to the left and right sub-arrays
        quick_sort(arr, low, pi - 1)
        quick_sort(arr, pi + 1, high)

# Example usage
arr = [120, 110, 12, 40, 15, 60, 35, 26]
n = len(arr)
quick_sort(arr, 0, n-1)
print("Sorted array:", arr)
