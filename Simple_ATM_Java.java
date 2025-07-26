import java.util.InputMismatchException;
import java.util.Scanner;


public class Simple_ATM_Java {


    private static Scanner scanner = new Scanner(System.in);

    private static final String VALID_USER_ID = "user123";
    private static final int VALID_PIN = 1234;
    private static double accountBalance = 50000.00; // Initial balance

    public static void main(String[] args) {
        System.out.println("Welcome to the Simple ATM!");



        if (authenticateUser()) {
            showMainMenu();
        } else {
            System.out.println("\nToo many failed attempts. Your card has been blocked for security reasons.");
            System.out.println("Please contact your bank.");
        }

        System.out.println("\nThank you for using the Simple ATM. Goodbye!");
        scanner.close();
    }

    private static boolean authenticateUser() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            int pin = -1;

            try {
                pin = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid PIN format. Please enter numbers only.");
            } finally {
                scanner.nextLine();
            }

            if (userId.equals(VALID_USER_ID) && pin == VALID_PIN) {
                System.out.println("\nAuthentication Successful!");
                return true;
            } else {
                attempts++;
                System.out.println("Invalid User ID or PIN. Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
        }
        return false;
    }

    private static void showMainMenu() {
        int choice = 0;
        do {
            System.out.println("\n--- ATM Main Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number for your choice.");
                scanner.nextLine();
                choice = 0;
            }

        } while (choice != 4);
    }

    private static void checkBalance() {
        System.out.printf("Your current account balance is: ₹%.2f%n", accountBalance);
    }


    private static void deposit() {
        System.out.print("Enter the amount to deposit: ₹");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("Deposit amount must be positive.");
            } else {
                accountBalance += amount;
                System.out.printf("Successfully deposited ₹%.2f.%n", amount);
                checkBalance();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine();
        }
    }

    private static void withdraw() {
        System.out.print("Enter the amount to withdraw: ₹");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
            } else if (amount > accountBalance) {
                System.out.println("Insufficient funds. Your withdrawal has been cancelled.");
                checkBalance();
            } else if (amount % 100 != 0) {
                System.out.println("Please enter an amount in multiples of 100.");
            } else {
                accountBalance -= amount;
                System.out.printf("Please collect your cash. ₹%.2f has been withdrawn.%n", amount);
                checkBalance();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine();
        }
    }
}