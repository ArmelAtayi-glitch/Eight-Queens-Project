# 8-Queens Solver with Hill-Climbing and Random Restarts

This project solves the classic **8-Queens problem** using the **Hill-Climbing algorithm** with **random restarts**, developed for the *Intro to AI – ITCS 3153* course.

## 📌 Problem Overview

The 8-Queens problem requires placing 8 queens on an 8x8 chessboard such that **no two queens attack each other**. That means:
- No two queens share the same **row**
- No two queens share the same **column**
- No two queens share the same **diagonal**

This program uses the **Hill-Climbing search algorithm** with **random restarts** to find a valid solution.

## 🧠 Algorithm Description

1. Start with a **random state**: one queen per column, placed in a random row.
2. Evaluate the **heuristic** (number of conflicting queen pairs).
3. Generate all **neighboring states** by moving each queen within its column.
4. Choose the neighbor with the **lowest heuristic**.
5. If no better neighbor exists, perform a **random restart**.
6. Repeat until a **solution** (heuristic = 0) is found.

### Heuristic Function
The heuristic is calculated as the number of pairs of queens that are attacking each other.

## 📊 Sample Output Format

Each state is printed as an 8x8 grid of `0`s and `1`s, where:
- `1` represents a queen
- `0` represents an empty cell

Along with each state, the following are displayed:
- Current state's **heuristic**
- Number of **better neighbors**
- Action taken: "Setting new current state" or "**RESTART**"

Once a solution is found, the program outputs:
- Total number of **state changes**
- Total number of **restarts**

## ✅ Requirements

- Solve using **Hill-Climbing with Random Restarts**
- Use an appropriate **heuristic**
- Display required state output and transitions
- Restart when **local minima** is reached

## 🧪 Sample Execution

```text
Current h: 5
Current State
0 0 0 1 0 0 0 0
0 0 0 0 0 1 0 0
1 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0
0 1 0 0 0 0 0 0
0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 1
0 0 1 0 0 0 0 0
Neighbors found with lower h: 3
Setting new current state
...
Solution Found!
State changes: 29
Restarts: 6


📚 Course
UNC Charlotte – ITCS 3153: Intro to Artificial Intelligence

👨‍💻 Author
[Armel Atayi]


Course: Intro to AI – ITCS 3153

📝 License
This project is for educational use under UNC Charlotte’s ITCS 3153 guidelines.
