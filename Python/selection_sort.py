def selection_sort(arr):
    n = len(arr)
    comparisons = 0
    for i in range(n):
        min_idx = i
        for j in range(i+1, n):
            comparisons += 1
            if arr[j] < arr[min_idx]:
                min_idx = j
        arr[i], arr[min_idx] = arr[min_idx], arr[i]
    return arr, comparisons

# Example usage
array = [5, 2, 8, 1, 9, 3]
sorted_array, comparisons = selection_sort(array)
print("Sorted Array:", sorted_array)
print("Number of Comparisons:", comparisons)