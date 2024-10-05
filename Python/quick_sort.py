import time
import matplotlib.pyplot as plt
import random

def quick_sort(arr):
    if len(arr) <= 1:
        return arr

    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]

    return quick_sort(left) + middle + quick_sort(right)

def analyze_quick_sort(n):
    arr = [i for i in range(n)]
    random.shuffle(arr)

    start_time = time.time()
    sorted_arr = quick_sort(arr)
    end_time = time.time()

    execution_time = end_time - start_time
    return execution_time

def plot_time_complexity(n_values):
    execution_times = []

    for n in n_values:
        execution_time = analyze_quick_sort(n)
        execution_times.append(execution_time)

    plt.plot(n_values, execution_times, label="Quick Sort")
    plt.xlabel("Number of Elements")
    plt.ylabel("Execution Time (seconds)")
    plt.title("Time Complexity of Quick Sort")
    plt.legend()
    plt.show()

n_values = [100, 200, 300, 400, 500]
plot_time_complexity(n_values)