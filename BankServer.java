import java.util.HashMap;
import java.util.Map;

class BankServer {
    private Map<String, Double> accounts;

    public BankServer() {
        accounts = new HashMap<>();
    }

    public synchronized void deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        double balance = accounts.getOrDefault(accountNumber, 0.0);
        balance += amount;
        accounts.put(accountNumber, balance);
        System.out.println("Deposited $" + amount + " into account " + accountNumber);
    }

    public synchronized void withdraw(String accountNumber, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account " + accountNumber + " does not exist.");
            return;
        }

        double balance = accounts.get(accountNumber);
        if (balance >= amount) {
            balance -= amount;
            accounts.put(accountNumber, balance);
            System.out.println("Withdrawn $" + amount + " from account " + accountNumber);
        } else {
            System.out.println("Insufficient funds in account " + accountNumber);
        }
    }

    public synchronized void transfer(String senderAccountNumber, String receiverAccountNumber, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return;
        }

        if (!accounts.containsKey(senderAccountNumber) || !accounts.containsKey(receiverAccountNumber)) {
            System.out.println("One or both accounts do not exist.");
            return;
        }

        double senderBalance = accounts.get(senderAccountNumber);
        if (senderBalance >= amount) {
            double receiverBalance = accounts.get(receiverAccountNumber);
            senderBalance -= amount;
            receiverBalance += amount;
            accounts.put(senderAccountNumber, senderBalance);
            accounts.put(receiverAccountNumber, receiverBalance);
            System.out.println("Transferred $" + amount + " from account " + senderAccountNumber + " to account " + receiverAccountNumber);
        } else {
            System.out.println("Insufficient funds in account " + senderAccountNumber);
        }
    }

    public synchronized void checkBalance(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account " + accountNumber + " does not exist.");
            return;
        }

        double balance = accounts.get(accountNumber);
        System.out.println("Account " + accountNumber + " balance: $" + balance);
    }

    public static void main(String[] args) {
        BankServer bankServer = new BankServer();

        // Sample usage
        bankServer.deposit("12345", 1000);
        bankServer.deposit("67890", 500);
        bankServer.checkBalance("12345");
        bankServer.checkBalance("67890");
        bankServer.withdraw("12345", 200);
        bankServer.transfer("12345", "67890", 300);
    }
}