public class PasswordChecker {

    // a method to return password strength on a scale of 1-10
    public static int checkpasswordstrength(String password) {
        int strength = 0;

        // 1. Length check
        if (password.length() >= 8) {
            strength += 2;
        } else if (password.length() >= 5) {
            strength += 1;
        }

        // 2. Uppercase check
        if (countUppercase(password) > 0) {
            strength += 2;
        }

        //  3. Lowercase check
        if (countLowercase(password) > 0) {
            strength += 2; 
        }

        // 4. Digit check
        if (countDigits(password) > 0) {
            strength += 2;
        }

        // 5. Special character check
        if (countSpecialChars(password) > 0) {
            strength += 2;
        }

        // 6. Check patterns penalties
        for (int i = 0; i < password.length() - 2; i++) {
            char first = password.charAt(i);
            char second = password.charAt(i + 1);
            char third = password.charAt(i + 2);

            if ((first == second && second == third) ||
                (first + 1 == second && second + 1 == third) ||
                (first - 1 == second && second - 1 == third)) {
                
                    strength -= 2;
                    break;
            }
        }

        if (strength < 0) 
            strength = 0;

        return Math.min(strength, 10);
    }

    // counts uppercase in the password
    public static int countUppercase(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (c >= 'A' && c <= 'Z') count++;
        }
        return count;
    }

    // counts lowercase in the password
    public static int countLowercase(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (c >= 'a' && c <= 'z') count++;
        }
        return count;
    }

    // counts digits in the password
    public static int countDigits(String password) { 
        int count = 0;
        for (char c:password.toCharArray()) {
            if (c >= '0' && c <= '9') count++;
        }
        return count;
    }

    // counts special characters in the password
    public static int countSpecialChars(String password) { 
        int count = 0;
        String specialChars = "!@#$%^&*()-+_=[]{}|<>?/~";
        
        for (char c:password.toCharArray()) {
            if (specialChars.indexOf(c) != -1) count++;
        }
        return count;
    }
}