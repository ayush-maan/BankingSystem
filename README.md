# Java Console Banking System

A console-based banking application built in Java to practice object-oriented programming, exception handling, collections, file handling, serialization, and clean separation of responsibilities.

## Features

- Create a new bank account
- Four-digit PIN validation
- Account number generation
- Login using account number and PIN
- Deposit money
- Withdraw money
- Transfer money between accounts
- Prevent transfers to the same account
- Check current balance
- View transaction history
- Persistent account and transaction data using Java serialization
- Custom `InsufficientBalanceException`
- Input validation through a reusable `InputHelper`
- Console-based menu navigation

## Project Structure

```text
BankingSystem/
├── src/
│   ├── Main.java
│   ├── exception/
│   │   └── InsufficientBalanceException.java
│   ├── model/
│   │   ├── Account.java
│   │   └── Transaction.java
│   ├── service/
│   │   └── BankService.java
│   └── util/
│       ├── FileHandler.java
│       └── InputHelper.java
│
├── data/
│   ├── accounts.dat
│   └── transactions.dat
│
├── .gitignore
└── README.md
```

> The `data/` files are generated locally by the application and are intentionally excluded from GitHub.

## Architecture

The project follows a simple layered structure:

```text
Main
  ↓
BankService
  ↓
Model classes
  ↓
FileHandler
  ↓
.dat files
```

### Main

Handles the console interface, user input, menus, and displaying results.

### Account

Represents a bank account and contains operations such as:

- Deposit
- Withdraw
- Transfer
- Balance management

### Transaction

Represents a transaction with:

- Transaction ID
- Account number
- Transaction type
- Amount
- Date and time

### BankService

Contains the main banking operations and coordinates accounts, transactions, and persistence.

### FileHandler

Handles saving and loading accounts and transactions using Java object serialization.

### InputHelper

Provides reusable methods for safely reading integer, long, and double values from the console.

## Data Persistence

The application uses Java serialization to persist data locally.

Accounts are stored in:

```text
data/accounts.dat
```

Transactions are stored in:

```text
data/transactions.dat
```

These files are generated automatically when the application runs.

## Exception Handling

The project uses custom and built-in exceptions for invalid operations.

Example:

```java
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
```

This is thrown when a withdrawal or transfer exceeds the available balance.

## Technologies

- Java
- IntelliJ IDEA
- Java Collections
- Java I/O
- Object Serialization
- Exception Handling
- `LocalDateTime`

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Make sure a compatible JDK is configured.
4. Run `src/Main.java`.
5. Use the console menu to register or log in.

The application will create the required local data files automatically.

## Example Flow

```text
Main Menu
   │
   ├── Register
   │      └── Create Account
   │
   ├── Login
   │      └── Account Menu
   │            ├── Deposit
   │            ├── Withdraw
   │            ├── Transfer
   │            ├── Check Balance
   │            ├── Transaction History
   │            └── Logout
   │
   └── Exit
```

## Concepts Practiced

This project was built to apply Java concepts in a practical application:

- Classes and objects
- Encapsulation
- Packages
- Constructors
- Methods
- Collections
- Interfaces such as `Serializable`
- Exception handling
- Custom exceptions
- File I/O
- Object serialization/deserialization
- Static utility methods
- Loops and console input
- Basic separation of concerns

## Future Improvements

Possible improvements for future versions:

- Stronger input validation
- Better PIN handling and security
- Transaction type enum
- More robust account-number generation
- Account closing/deletion
- Cleaner console UI
- Refactoring `Main` into a dedicated console/UI class
- Improved transaction display
- Unit testing
- Database-backed persistence

## Disclaimer

This is an educational console project and is **not intended for handling real banking information or real financial transactions**.
