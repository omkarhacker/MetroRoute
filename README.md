# 🚇 MetroRoute Planner

A Java-based console application to simulate Delhi Metro navigation using a graph-based structure and Dijkstra’s algorithm. This project allows users to find the shortest distance or travel time between stations on a simplified metro map.

## 📌 Features

- Graph representation of metro stations and routes
- Shortest **distance** and **time** calculation using Dijkstra’s algorithm
- Console-based display of the full metro map and available stations
- Clean, simple implementation using Java Collections (no custom heap)

## 🔧 Technologies Used

- Java
- Collections Framework (`HashMap`, `PriorityQueue`)
- Object-Oriented Programming
- Graphs & Dijkstra’s Algorithm (Greedy approach)

## 🧠 How It Works

- Each station is represented as a **vertex** in a graph.
- Each route between stations is an **edge** with an associated distance.
- Dijkstra’s algorithm is used to compute the shortest path (distance or time).
- Travel time is calculated as `2 × distance` (for simulation purposes).

## ▶️ Example Usage

```bash
> Run the program

Delhi Metro Map
------------------
Noida Sector 62~B =>
    Botanical Garden~B        8
...

Shortest Distance (Noida Sector 62 to Dwarka): 45 km
Shortest Time (Noida Sector 62 to Dwarka): 90 units
