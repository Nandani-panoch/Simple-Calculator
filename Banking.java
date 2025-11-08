import java.util.ArrayList;
import java.util.Scanner;

public class Banking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        BankAccount acc1 = new BankAccount("xyz", "01");
        BankAccount acc2 = new BankAccount("abc", "02");

        System.out.println("Which account do you want to access?");
        System.out.println("1. " + acc1.customerName + " (ID: " + acc1.customerId + ")");
        System.out.println("2. " + acc2.customerName + " (ID: " + acc2.customerId + ")");
        System.out.print("Enter choice (1 or 2): ");
        int choice = sc.nextInt();

        BankAccount currentAccount;
        BankAccount receiverAccount;

        if (choice == 1) {
            currentAccount = acc1;
            receiverAccount = acc2;
        } else {
            currentAccount = acc2;
            receiverAccount = acc1;
        }

        
        currentAccount.showMenu(receiverAccount);
    }
}

class BankAccount {
    int balance;
    int previousTransaction;
    String customerName;
    String customerId;

    
    ArrayList<String> transactionHistory = new ArrayList<>();

    BankAccount(String cname, String cid) {
        customerName = cname;
        customerId = cid;
    }

    
    void deposit(int amount) {
        if (amount > 0) {
            balance = balance + amount;
            previousTransaction = amount;
            transactionHistory.add("Deposited ₹" + amount);
        }
    }

    
    void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            previousTransaction = -amount;
            transactionHistory.add("Withdrew ₹" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        }
    }

    
    void transfer(BankAccount receiver, int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            receiver.balance += amount;
            System.out.println("Transferred ₹" + amount + " to " + receiver.customerName);
            previousTransaction = -amount;

            transactionHistory.add("Transferred ₹" + amount + " to " + receiver.customerName);
            receiver.transactionHistory.add("Received ₹" + amount + " from " + customerName);
        } else {
            System.out.println("Transfer failed! Check amount or balance.");
        }
    }
    void getPreviousTransaction() {
        if (previousTransaction > 0) {
            System.out.println("Deposited: ₹" + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Withdrawn: ₹" + Math.abs(previousTransaction));
        } else {
            System.out.println("No transaction occurred!");
        }
    }

   
    void showAllTransactions() {
        System.out.println("----------- Transaction History -----------");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet!");
        } else {
            for (String record : transactionHistory) {
                System.out.println(record);
            }
        }
        System.out.println("-------------------------------------------");
    }

  
    void showMenu(BankAccount receiver) {
        char option = '\0';
        Scanner sc = new Scanner(System.in);

        System.out.println("\nWelcome " + customerName);
        System.out.println("Your ID is: " + customerId);
        System.out.println();

        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous Transaction");
        System.out.println("E. Transfer Funds");
        System.out.println("F. View Transaction History");
        System.out.println("G. Exit");

        do {
            System.out.println("==================================");
            System.out.print("Enter an option: ");
            System.out.println("==================================");
            option = Character.toUpperCase(sc.next().charAt(0));

            switch (option) {
                case 'A':
                    System.out.println("----------------------------------");
                    System.out.println("Balance: ₹" + balance);
                    System.out.println("----------------------------------");
                    break;

                case 'B':
                    System.out.println("----------------------------------");
                    System.out.print("Enter amount to deposit: ₹");
                    int amount = sc.nextInt();
                    deposit(amount);
                    System.out.println("----------------------------------");
                    break;

                case 'C':
                    System.out.println("----------------------------------");
                    System.out.print("Enter amount to withdraw: ₹");
                    int amount2 = sc.nextInt();
                    withdraw(amount2);
                    System.out.println("----------------------------------");
                    break;

                case 'D':
                    System.out.println("----------------------------------");
                    getPreviousTransaction();
                    System.out.println("----------------------------------");
                    break;

                case 'E':
                    System.out.println("----------------------------------");
                    System.out.print("Enter amount to transfer: ₹");
                    int amount3 = sc.nextInt();
                    transfer(receiver, amount3);
                    System.out.println("----------------------------------");
                    break;

                case 'F':
                    showAllTransactions();
                    break;

                case 'G':
                    System.out.println("**********************************");
                    System.out.println("Exiting the system... Thank you!");
                    System.out.println("**********************************");
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        } while (option != 'G');
    }
}
