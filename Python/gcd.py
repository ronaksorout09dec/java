def gcd_recursive(a, b, steps=0):
    if b == 0:
        return a, steps
    else:
        return gcd_recursive(b, a % b, steps + 1)

# Example usage
num1 = 48
num2 = 18
gcd, steps = gcd_recursive(num1, num2)
print(f"GCD of {num1} and {num2} is: {gcd}")
print(f"Number of steps required: {steps}")