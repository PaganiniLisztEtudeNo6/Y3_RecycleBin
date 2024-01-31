import matplotlib.pyplot as plt
from memory_profiler import profile
import numpy as np

@profile
def my_function(n):
    x = []
    for i in range(n):
        x.append(i)
    return x

def main():
    memory = []
    for i in range(100):
        result = my_function(i)
        memory_usage = result[0]
        memory.append(memory_usage)

 
    print(memory)



if __name__ == "__main__":
    main()
