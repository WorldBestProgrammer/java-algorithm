from collections import deque

n, m, v = map(int, input().split())

graph = [[]  for _ in range(n+1)]
for i in range(m):
  a, b = map(int, input().split())
  graph[a].append(b)
  graph[b].append(a)

for i in range(1, n+1):
  graph[i].sort()

visited = [False] * (n+1)
#print(visited)

def dfs(start, visited):
  visited[start]= True
  print(start, end=" ")
  for i in graph[start]:
    if visited[i] == False:
      dfs(i, visited)


def bfs(start):
  visited= [False] * (n+1)
  q=deque()
  q.append(start)
  while q:
    a=q.popleft()
    if visited[a] == False:
      print(a, end=" ")
      visited[a] = True
      for i in graph[a]:
        q.append(i)
  
dfs(v, visited)
print()
bfs(v)

