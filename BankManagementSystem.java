import java.io.*;
import java.util.*;

class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    String dob;
    int phone;
    String password;
    float balance;

    public Account(String name, String dob, int phone, String password) {
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.password = password;
        this.balance = 0.0f;
    }
}

public class BankManagementSystem {
    static final String FILE_NAME = "accounts.ser";
    static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        loadAccounts();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Balance");
            System.out.println("6. Account Status");
            System.out.println("7. Show All Accounts");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createAccount(scanner);
                case 2 -> checkBalance(scanner);
                case 3 -> deposit(scanner);
                case 4 -> withdraw(scanner);
                case 5 -> transferBalance(scanner);
                case 6 -> accountStatus(scanner);
                case 7 -> showAllAccounts();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);
        scanner.close();
        saveAccounts();
    }

    @SuppressWarnings("unchecked")
    static void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (ArrayList<Account>) ois.readObject();
        } catch (Exception ignored) {}
    }

    static void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Failed to save accounts.");
        }
    }

    static Account findAccount(int phone, String password) {
        for (Account acc : accounts) {
            if (acc.phone == phone && acc.password.equals(password)) {
                return acc;
            }
        }
        return null;
    }

    static Account findAccountByPhone(int phone) {
        for (Account acc : accounts) {
            if (acc.phone == phone) return acc;
        }
        return null;
    }

    static void createAccount(Scanner scanner) {
        System.out.print("Enter Phone Number (as Account Number): ");
        int phone = scanner.nextInt();

        if (findAccountByPhone(phone) != null) {
            System.out.println("\n**Error: This Account Number already exists.**");
            return;
        }

        scanner.nextLine(); // Clear buffer
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Date of Birth (DD-MM-YYYY): ");
        String dob = scanner.nextLine();

        String password;
        while (true) {
            System.out.print("Set Password (4 to 8 characters): ");
            password = scanner.next();
            if (password.length() >= 4 && password.length() <= 8) break;
            else System.out.println("**Password must be 4 to 8 characters.**");
        }

        Account newAcc = new Account(name, dob, phone, password);
        accounts.add(newAcc);
        saveAccounts();
        System.out.println("\nAccount created successfully!");
    }

    static void checkBalance(Scanner scanner) {
        Account acc = authenticate(scanner);
        if (acc != null) {
            System.out.printf("Current Balance: $%.2f\n", acc.balance);
        }
    }

    static void deposit(Scanner scanner) {
        Account acc = authenticate(scanner);
        if (acc != null) {
            System.out.print("Enter Amount to Deposit: $");
            float amount = scanner.nextFloat();
            acc.balance += amount;
            System.out.println("== Deposit Successful ==");
            saveAccounts();
        }
    }

    static void withdraw(Scanner scanner) {
        Account acc = authenticate(scanner);
        if (acc != null) {
            System.out.print("Enter Amount to Withdraw: $");
            float amount = scanner.nextFloat();
            if (amount > acc.balance) {
                System.out.println("**Insufficient Balance!**");
            } else {
                acc.balance -= amount;
                System.out.println("== Withdrawal Successful ==");
                saveAccounts();
            }
        }
    }

    static void transferBalance(Scanner scanner) {
        System.out.print("Enter Your Account Number: ");
        int senderPhone = scanner.nextInt();
        System.out.print("Enter Your Password: ");
        String password = scanner.next();

        Account sender = findAccount(senderPhone, password);
        if (sender == null) {
            System.out.println("**Sender not found or wrong password.**");
            return;
        }

        System.out.print("Enter Receiver's Account Number: ");
        int receiverPhone = scanner.nextInt();
        Account receiver = findAccountByPhone(receiverPhone);
        if (receiver == null) {
            System.out.println("**Receiver account not found.**");
            return;
        }

        System.out.print("Enter Amount to Transfer: $");
        float amount = scanner.nextFloat();
        if (amount > sender.balance) {
            System.out.println("**Insufficient Balance!**");
        } else {
            sender.balance -= amount;
            receiver.balance += amount;
            System.out.println("== Transfer Successful ==");
            saveAccounts();
        }
    }

    static void accountStatus(Scanner scanner) {
        Account acc = authenticate(scanner);
        if (acc != null) {
            System.out.println("\n=== Account Status ===");
            System.out.println("Name: " + acc.name);
            System.out.println("Account Number: 0" + acc.phone);
            System.out.printf("Current Balance: $%.2f\n", acc.balance);
        }
    }

    static void showAllAccounts() {
        Set<Integer> seenPhones = new HashSet<>();
        System.out.println("\n=== All Accounts Summary ===");
        System.out.printf("%-20s | %-15s\n", "Name", "Account Number");
        System.out.println("----------------------------------------");
        for (Account acc : accounts) {
            if (!seenPhones.contains(acc.phone)) {
                System.out.printf("%-20s | 0%d\n", acc.name, acc.phone);
                seenPhones.add(acc.phone);
            }
        }
    }

    static Account authenticate(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        int phone = scanner.nextInt();
        System.out.print("Enter Password: ");
        String password = scanner.next();
        Account acc = findAccount(phone, password);
        if (acc == null) {
            System.out.println("**Account not found or incorrect password.**");
        }
        return acc;
    }
}
