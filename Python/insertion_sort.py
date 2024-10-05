def insertion_sort(arr):
    n = len(arr)
    comparisons = 0
    for i in range(1, n):
        key = arr[i]
        j = i-1
        while j >= 0 and arr[j] > key:
            comparisons += 1
            arr[j+1] = arr[j]
            j -= 1
        arr[j+1] = key
    return arr, comparisons

# Example usage
array = [5, 2, 8, 1, 9, 3]
sorted_array, comparisons = insertion_sort(array)
print("Sorted Array:", sorted_array)
print("Number of Comparisons:", comparisons)