def factorial_recursive(n):
    if n == 0:
        return 1
    else:
        return n * factorial_recursive(n-1)

# Example usage
number = 5
factorial = factorial_recursive(number)
print(f"Factorial of {number} (Recursive): {factorial}")