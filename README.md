# 📈 Stock Market Simulator

A console-based Java Stock Market Simulator developed as a learning project to practice Java fundamentals, Object-Oriented Programming, collections, exception handling, input validation, and file persistence.

The project was built incrementally by implementing features, testing them, finding problems, and improving the program rather than trying to design the entire application perfectly from the beginning.

> **Status:** Core functionality is complete. Some persistence, architecture, and code-quality improvements are still planned.

---

## 🎯 Project Goals

This project was created to practice:

- Java fundamentals
- Classes and objects
- Encapsulation
- ArrayLists
- Loops and conditional logic
- Exception handling
- Input validation
- Basic authentication
- File reading and writing
- CSV-style text-file persistence
- Portfolio calculations
- Managing application state
- Debugging and handling edge cases

---

## ✨ Features

### 👤 User System

- User registration
- Username validation
- Password validation
- Duplicate username checking
- Login authentication
- Logout
- Starting account balance
- Persistent user information

Password registration currently requires:

- At least 8 characters
- At least one uppercase character
- At least one lowercase character
- An `@` character

---

### 📊 Stock Market

The simulator currently contains sample market data including:

- APPL
- TESL
- AMD
- NVID
- MICR

Each stock contains:

- Stock name
- Stock price
- Available quantity
- Stock ID

The market can be displayed from the console.

---

### 🛒 Buying Stocks

Users can:

1. View the market
2. Select a stock using its ID
3. Enter a quantity
4. Check stock availability
5. Check available funds
6. Purchase the stock
7. Reduce the available market quantity
8. Add the stock to their portfolio
9. Increase an existing holding when buying the same stock again
10. Deduct the purchase cost from their balance

The program therefore maintains two different states:

```text
Market
    ↓
Available stock

User Portfolio
    ↓
Owned stock
