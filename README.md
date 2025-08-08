# 🏦 Smart Bank Management System

A simple **Java Swing** desktop application that simulates a banking management system.  
It allows users to **create accounts, log in, deposit, withdraw, transfer funds**, and check their account status — all with a clean and user-friendly GUI.

---

## ✨ Features

- **Create Account** – Register with your name, date of birth, phone number, and password.
- **Login / Logout** – Secure login system with password authentication.
- **Deposit Money** – Add funds to your account.
- **Withdraw Money** – Withdraw cash if sufficient balance is available.
- **Transfer Money** – Send money to another account using their phone number.
- **Account Status** – View your personal details and current balance when logged in.
- **Show All Accounts** – View the list of all accounts (name & account number only).
- **Persistent Storage** – Data is saved using Java serialization (`accounts.ser`).

---

## 🖼 Screenshots

![Banking System Screenshot](https://raw.githubusercontent.com/yourusername/bank-management-system/main/screenshots/screenshot1.png)

---

## 🛠️ Technologies Used

- **Java SE 8+**
- **Java Swing** (GUI)
- **Serialization** (for saving account data)
- **OOP Concepts** – Encapsulation, Classes & Objects

---

## 📂 Project Structure

<pre>
bank-management-system/
│
├── bankingsystem/
│   ├── Account.java          # Account model class
│   ├── BankingSystem.java    # Core banking logic
│   └── BankingSystemGUI.java # Main GUI application
│
└── accounts.ser              # Serialized data file (auto-created after first run)
</pre>

---

## 🚀 How to Run

### 1️⃣ Clone the repository
```bash
git clone https://github.com/yourusername/bank-management-system.git
cd bank-management-system
2️⃣ Compile the project

javac bankingsystem/*.java

3️⃣ Run the application

java bankingsystem.BankingSystemGUI

📖 Usage Guide

Create AccountFill in your details and click "Create Account".

LoginEnter your phone number and password.

Deposit / WithdrawUse the respective menu options after logging in.

TransferEnter the recipient's phone number and the amount.

StatusView your account details (name, DOB, account number, balance).

Show All AccountsDisplays all account names and numbers (without balances).

📌 Notes

Phone number acts as your account number.

All data is stored locally in accounts.ser.

Make sure accounts.ser is in the same folder as your .java files when running the app again, so your data is preserved.