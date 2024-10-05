class Graph:
    def __init__(self, vertices):
        self.vertices = vertices
        self.graph = [[0] * vertices for _ in range(vertices)]

    def add_edge(self, u, v):
        self.graph[u][v] = 1
        self.graph[v][u] = 1

    def dfs(self, start):
        visited = [False] * self.vertices
        self.dfs_util(start, visited)

    def dfs_util(self, v, visited):
        visited[v] = True
        print(v, end=" ")

        for i in range(self.vertices):
            if self.graph[v][i] == 1 and not visited[i]:
                self.dfs_util(i, visited)


# Example usage
g = Graph(4)
g.add_edge(0, 1)
g.add_edge(0, 2)
g.add_edge(1, 2)
g.add_edge(2, 3)

print("Depth-First Search:")
g.dfs(0)