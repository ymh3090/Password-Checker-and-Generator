import java.util.*;

public class App {
    public static final String RESET = "\033[0m";
    public static final String CYAN = "\033[0;36m";
    private static Scanner input; // so it's accessible to all methods

    public static void main(String[] args) throws Exception {
        input = new Scanner(System.in);
        System.out.println();
        System.out.println();
        mainMenu();
        input.close();
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("1. Check Password Strength");
            System.out.println("2. Generate New Password");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            if (!input.hasNextInt()) {
                System.out.println(">> Invalid input, please enter a number.");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();  // Add this to consume the newline

            if( choice < 1 || choice > 3 ) {
                System.out.println(">> Invalid choice, please try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    choice1();
                    break;
                case 2:
                    System.out.println(CYAN + "Tip: It's often better to have longer passwords than shorter, more complex ones" + RESET);
                    choice2();
                    lines();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println(">> Invalid choice, please try again.");
            }
        }
    }

    public static void choice1() {
        System.out.print("Enter password to check: ");
        String inputPass = input.nextLine();

        if (inputPass.trim().isEmpty()) {
            System.out.println(">> Invalid input! Please enter a password.");
            lines();
            return;
        }

        byte x = (byte)PasswordChecker.checkpasswordstrength(inputPass);

        System.out.println(">> Strength Score: " + x + "/10");
        System.out.println(">> Strength Level: " + getStrengthLabel(x));

        System.out.println("   length: " + inputPass.length());
        System.out.println("   Uppercase Letters: " + PasswordChecker.countUppercase(inputPass));
        System.out.println("   Lowercase Letters: " + PasswordChecker.countLowercase(inputPass));
        System.out.println("   Digits: " + PasswordChecker.countDigits(inputPass));
        System.out.print("   Special Characters: " + PasswordChecker.countSpecialChars(inputPass));
        lines();
    }

    public static void choice2() {
        System.out.print("Enter desired length (minimum 8): ");

        if (input.hasNextInt()) {
            int len = input.nextInt();
            input.nextLine();

            if (len < 8) {
                System.out.println(">> Error: Rules state minimum length is 8 characters.");
            } 
            else {
                System.out.println("here are few passwords:");
                for (int i = 0; i < 5; i++) {
                    System.out.println(">> " + PasswordGenerator.generatePassword(len));
                }
            }
        } 
        else {
            System.out.println(">> Invalid length!");
            input.nextLine();
        }
    }

    public static String getStrengthLabel(int score) {
        if (score >= 9) return "Very Strong";
        if (score >= 7) return "Strong";
        if (score >= 5) return "Moderate";
        if (score >= 3) return "Weak";
        return "Very Weak";
    }

    public static void lines() {
        System.out.println();
        System.out.println();
    }
    
}