import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;
    private List<Transaction> transactionHistory;

    public BankAccount() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        transactionHistory.add(new Transaction("Withdraw", amount));
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public boolean transfer(BankAccount recipient, double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        recipient.deposit(amount);
        transactionHistory.add(new Transaction("Transfer to " + recipient, amount));
        return true;
    }

    @Override
    public String toString() {
        return "Account balance: " + balance;
    }
}
