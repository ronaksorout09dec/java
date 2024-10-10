import random
import time
import matplotlib.pyplot as plt

def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    else:
        pivot = arr[len(arr) // 2]
        left = [x for x in arr if x < pivot]
        middle = [x for x in arr if x == pivot]
        right = [x for x in arr if x > pivot]
        return quick_sort(left) + middle + quick_sort(right)

def measure_time_for_nnn(nnn_values, num_trials=5):
    times = []
    for nnn in nnn_values:
        total_time = 0
        for _ in range(num_trials):
            arr = [random.randint(0, 100000) for _ in range(nnn)]
            start_time = time.time()
            quick_sort(arr)
            end_time = time.time()
            total_time += (end_time - start_time)
        average_time = total_time / num_trials
        times.append(average_time)
    return times

nnn_values = [100,200,300,400,500]

times_for_nnn = measure_time_for_nnn(nnn_values)

plt.figure(figsize=(10, 6))
plt.plot(nnn_values, times_for_nnn, marker='o', color='b')
plt.title('Time Complexity of Quick Sort for Different nnn Values')
plt.xlabel('Number of elements (nnn)')
plt.ylabel('Average Time taken (seconds)')
plt.grid(True)
plt.show()
