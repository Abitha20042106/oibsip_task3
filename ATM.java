import java.util.Scanner;

public class ATM {
    private ATMSystem atmSystem;
    private User currentUser;
    private Scanner scanner;

    public ATM(ATMSystem atmSystem) {
        this.atmSystem = atmSystem;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM system");

        while (true) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            currentUser = atmSystem.authenticateUser(userId, pin);
            if (currentUser != null) {
                System.out.println("Authentication successful!");
                showMenu();
            } else {
                System.out.println("Invalid User ID or PIN. Please try again.");
            }
        }
    }

    private void showMenu() {
        boolean quit = false;
        while (!quit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    quit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : currentUser.getAccount().getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (currentUser.getAccount().withdraw(amount)) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        currentUser.getAccount().deposit(amount);
        System.out.println("Deposit successful!");
    }

    private void transfer() {
        System.out.print("Enter recipient User ID: ");
        String recipientId = scanner.nextLine();
        User recipient = atmSystem.findUserById(recipientId);

        if (recipient == null) {
            System.out.println("Recipient not found.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (currentUser.getAccount().transfer(recipient.getAccount(), amount)) {
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}
