import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        // Main Loop
        while (keepRunning) {
            System.out.println("\n--------------------------------");
            System.out.println("   Password Checker & Generator");
            System.out.println("--------------------------------");
            System.out.println("1. Check Password Strength");
            System.out.println("2. Generate New Password");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            // Input validation
            if (!scanner.hasNextInt()) {
                System.out.println(">> Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // start of password strength checking
                    System.out.print("Enter password to check: ");
                    String inputPass = scanner.nextLine();

                    // for checking password strength
                    int score = PasswordChecker.checkPasswordStrength(inputPass);

                    System.out.println(">> Strength Score: " + score + "/10");
                    displayStrengthMessage(score);
                    break;

                case 2:
                    // start of password generation
                    System.out.print("Enter desired length (minimum 8): ");
                    if (scanner.hasNextInt()) {
                        int len = scanner.nextInt();
                        scanner.nextLine();

                        // validation for length
                        if (len < 8) {
                            System.out.println(">> Error: Rules state minimum length is 8 characters.");
                        } else {
                            // generate password
                            String newPass = PasswordGenerator.generatePassword(len);
                            System.out.println(">> Generated Password: " + newPass);
                        }
                    } else {
                        System.out.println(">> Invalid length!");
                        scanner.nextLine();
                    }
                    break;

                case 3:
                    // Exit option
                    keepRunning = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println(">> Invalid choice, please try again.");
            }
        }
        scanner.close();
    }

    // for displaying strength message
    private static void displayStrengthMessage(int score) {
        if (score < 5)
            System.out.println("(Weak Password)");
        else if (score < 8)
            System.out.println("(Moderate Password)");
        else
            System.out.println("(Strong Password)");
    }
}