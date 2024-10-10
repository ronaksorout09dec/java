import random
from collections import defaultdict, deque

class Graph:
    def __init__(self):
        self.graph = defaultdict(list)

    def add_edge(self, u, v):
        self.graph[u].append(v)
        self.graph[v].append(u)

    def bfs(self, start):
        visited = set()
        queue = deque([start])
        visited.add(start)
        
        bfs_order = []
        
        while queue:
            vertex = queue.popleft()
            bfs_order.append(vertex)

            for neighbor in self.graph[vertex]:
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append(neighbor)

        return bfs_order

def run_bfs_example():
 
    graph = Graph()

    graph.add_edge(0, 1)
    graph.add_edge(0, 2)
    graph.add_edge(1, 3)
    graph.add_edge(2, 4)
    graph.add_edge(3, 4)
    graph.add_edge(3, 5)

    start_node = 0
    bfs_traversal = graph.bfs(start_node)
    
    print(f"BFS Traversal starting from node {start_node}: {bfs_traversal}")

run_bfs_example()