import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class App {
    public static final String RESET = "\033[0m";
    public static final String CYAN = "\033[0;36m";
    private static Scanner input;
    private static final String FILE_PATH = "data.json";
    
    public static JSONObject obj = new JSONObject();
    

    public static void main(String[] args) throws Exception {
    
        // Load existing JSON data if file exists
        loadJsonData();
        input = new Scanner(System.in);
        mainMenu();
        input.close();
    }

    public static void loadJsonData(){
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
                if (!content.trim().isEmpty()) {
                    obj = new JSONObject(content);
                }
            }
        } catch (Exception e) {
            obj = new JSONObject();
        }
    }

    // Save JSON data to file
    public static void saveJsonData() {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(obj.toString(4));
            System.out.println(">> Passwords saved.");
        } catch (IOException e) {
            System.out.println(">> Error saving data: " + e.getMessage());
        }
    }

    public static String createTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public static void mainMenu() {
        lines();
        while (true) {

            System.out.println("1. Check Password Strength");
            System.out.println("2. Generate New Password");
            System.out.println("3. custimsie your own password");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (!input.hasNextInt()) {
                System.out.println(">> Invalid input, please enter a number.");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();  // Add this to consume the newline

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
                    System.out.println(CYAN + "Tip: It's often better to have longer passwords than shorter, more complex ones" + RESET);
                    makurown();
                    lines();
                    break;
                case 4:
                    return;
                default:
                    System.out.println(">> Invalid choice, please try again.");
            }
        }
    }


    public static void choice1() {
        System.out.print("Enter password to check: ");
        String inputPass = input.nextLine();
        boolean goodlenght = inputPass.length() >= 8 && inputPass.length() <= 20;

        if (!goodlenght && !(inputPass.length()>0)) {
            System.out.println(">> Error: Password length must be between 8 and 20 characters.");
            return;
        }

        byte x = (byte)PasswordChecker.checkpasswordstrength(inputPass);

        System.out.println(">> Strength Score: " + x + "/10");
        System.out.println(">> Strength Level: " + getStrengthLabel(x));

        System.out.println("   length: " + inputPass.length());
        System.out.println("   Uppercase Letters: " + PasswordChecker.countUppercase(inputPass));
        System.out.println("   Lowercase Letters: " + PasswordChecker.countLowercase(inputPass));
        System.out.println("   Digits: " + PasswordChecker.countDigits(inputPass));
        System.out.println("   Special Characters: " + PasswordChecker.countSpecialChars(inputPass));
        lines();
    }

    public static void choice2() {
        System.out.print("Enter desired length (minimum 8): ");

        if (input.hasNextInt()) {
            int len = input.nextInt();
            input.nextLine();

            if (len < 8 || len > 20) {
                System.out.println(">> Error: Rules state minimum length is 8 characters.");
                mainMenu();
            } 
            else {
                System.out.println("here are few passwords:");
                JSONArray passwords = new JSONArray();
                for (int i = 0; i < 5; i++) {
                    String p = PasswordGenerator.generatePassword(len);
                    System.out.println(">> " + p);
                    passwords.put(p);    
                }
                passwords.put(">> Passwords generated with length: " + len);

                // Save generated passwords with timestamp
                String timestamp = createTimestamp();
                obj.put(timestamp, passwords);
                saveJsonData();
            }
        } 
        else {
            System.out.println(">> Invalid length!");
            input.nextLine();
        }
    }

    public static void makurown(){
        System.out.print("Enter desired length: ");

        
        if (input.hasNextInt()) {
            int len = input.nextInt();
            input.nextLine();

            if (len < 8 || len > 20) {
                System.out.println(">> Error: Rules state minimum length is 8 characters and smaller than 20.");
                mainMenu();
            } 
            else {
                boolean symbols=false;

                System.out.println(CYAN+"it's better to include symbols to increase password strength"+RESET);

                System.out.println("include symbols? (y/n)");
                switch (input.nextLine().trim().toLowerCase()) {
                    case "y":
                        symbols = true;
                        break;
                    default:
                        System.out.println(">> assuming 'n'");
                }



                System.out.println("here are few passwords:");
                JSONArray passwords = new JSONArray();


                for (int i=0;i<5;i++) {
                    String password = PasswordGenerator.generatePassword(len, symbols);
                    System.out.println(">> " + password);
                    passwords.put(password);
                }
                // Save generated passwords with timestamp
                String timestamp = createTimestamp();
                obj.put(timestamp, passwords);
                obj.put("length", len);
                saveJsonData();

            }
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