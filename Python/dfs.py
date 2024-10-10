import random
from collections import defaultdict

class Graph:
    def __init__(self):
        self.graph = defaultdict(list)

    def add_edge(self, u, v):
        self.graph[u].append(v)
        self.graph[v].append(u)

    def dfs_iterative(self, start):
        visited = set()         
        stack = [start]         
        dfs_order = []          
        
        while stack:
            vertex = stack.pop()  
            if vertex not in visited:
                visited.add(vertex)  
                dfs_order.append(vertex)  

                stack.extend(reversed([neighbor for neighbor in self.graph[vertex] if neighbor not in visited]))
        
        return dfs_order  

def run_dfs_example():

    graph = Graph()

    graph.add_edge(0, 1)
    graph.add_edge(0, 2)
    graph.add_edge(1, 3)
    graph.add_edge(2, 4)
    graph.add_edge(3, 4)
    graph.add_edge(3, 5)

    start_node = 0
    dfs_traversal = graph.dfs_iterative(start_node)
    
    print(f"DFS Traversal starting from node {start_node}: {dfs_traversal}")

run_dfs_example()