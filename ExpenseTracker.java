import java.util.Scanner;

class Expense {
    String description;
    int amount;

    public Expense(String description, int amount) {
        this.description = description;
        this.amount = amount;
    }
}

public class ExpenseTracker {
    private static Expense[] expenses;
    private static int expenseCount = 0;
    private static Scanner sc = new Scanner(System.in);
    private static final int MAX_EXPENSES = 10;

    public static void main(String[] args) {
        expenses = new Expense[MAX_EXPENSES]; 
        int choice;
        do {
            System.out.println("Menu");
            System.out.println("1. Add an expense with a description and amount");
            System.out.println("2. Update an expense");
            System.out.println("3. Delete an expense");
            System.out.println("4. View all expenses");
            System.out.println("5. View a summary of all expenses");
            System.out.println("6. View a summary of expenses for a specific month (of the current year)");
            System.out.println("7. Quit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    updateExpense();
                    break;
                case 3:
                    deleteExpense();
                    break;
                case 4:
                    viewAllExpenses();
                    break;
                case 5:
                    viewSummaryOfExpenses();
                    break;
                case 6:
                    viewMonthlySummary();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    // Add an expense
    private static void addExpense() {
        if (expenseCount >= MAX_EXPENSES) {
            System.out.println("Cannot add more expenses. Maximum limit reached.");
            return;
        }
        sc.nextLine();
        System.out.print("Enter the description of the expense: ");
        String description = sc.nextLine();
        System.out.print("Enter the amount of the expense: ");
        int amount = sc.nextInt();
        expenses[expenseCount] = new Expense(description, amount);
        expenseCount++;
        System.out.println("Expense added successfully!");
    }

    private static void updateExpense() {
        System.out.print("Enter the index of the expense to update (0 to " + (expenseCount - 1) + "): ");
        int index = sc.nextInt();

        if (index >= 0 && index < expenseCount) {
            sc.nextLine(); // consume the newline character
            System.out.print("Enter the new description: ");
            String newDescription = sc.nextLine();
            System.out.print("Enter the new amount: ");
            int newAmount = sc.nextInt();

            Expense exp = expenses[index];
            exp.description = newDescription;
            exp.amount = newAmount;
            System.out.println("Expense updated successfully!");
        } else {
            System.out.println("Invalid index. No expense found.");
        }
    }

    private static void deleteExpense() {
        System.out.print("Enter the index of the expense to delete (0 to " + (expenseCount - 1) + "): ");
        int index = sc.nextInt();

        if (index >= 0 && index < expenseCount) {
            for (int i = index; i < expenseCount - 1; i++) {
                expenses[i] = expenses[i + 1];
            }
            expenses[expenseCount - 1] = null; 
            expenseCount--;
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Invalid index. No expense found.");
        }
    }
    private static void viewAllExpenses() {
        if (expenseCount == 0) {
            System.out.println("No expenses found.");
        } else {
            for (int i = 0; i < expenseCount; i++) {
                System.out.println(i + ". " + expenses[i].description + ": " + expenses[i].amount);
            }
        }
    }

    // View a summary of all expenses
    private static void viewSummaryOfExpenses() {
        int total = 0;
        for (int i = 0; i < expenseCount; i++) {
            total += expenses[i].amount;
        }
        System.out.println("Total expenses: " + total);
    }

    private static void viewMonthlySummary() {
        viewSummaryOfExpenses();
    }
}
