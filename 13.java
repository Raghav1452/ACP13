import java.util.Scanner;

// Custom exceptions
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidMenuOptionException extends Exception {
    public InvalidMenuOptionException(String message) {
        super(message);
    }
}

public class SimpleBanking {

    private double balance;

    public SimpleBanking(double initialBalance) throws Exception {
        if (initialBalance < 500) {
            throw new Exception("Opening balance must be at least 500.");
        }
        balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter initial balance (at least 500): ");
            double initialBalance = scanner.nextDouble();
            SimpleBanking account = new SimpleBanking(initialBalance);

            boolean exit = false;
            while (!exit) {
                System.out.println("\n1. Check Balance\n2. Deposit\n3. Withdraw\n4. Exit");
                System.out.print("Select option: ");
                int option = scanner.nextInt();

                try {
                    switch (option) {
                        case 1:
                            System.out.println("Current balance: " + account.getBalance());
                            break;
                        case 2:
                            System.out.print("Deposit amount: ");
                            double depositAmount = scanner.nextDouble();
                            account.deposit(depositAmount);
                            break;
                        case 3:
                            System.out.print("Withdraw amount: ");
                            double withdrawAmount = scanner.nextDouble();
                            account.withdraw(withdrawAmount);
                            break;
                        case 4:
                            exit = true;
                            System.out.println("Exiting...");
                            break;
                        default:
                            throw new InvalidMenuOptionException("Invalid option selected.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
