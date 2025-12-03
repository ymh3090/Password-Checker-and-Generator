public class PasswordChecker {

    // دالة الفحص الرئيسية
    public static int checkPasswordStrength(String password) {
        int strength = 0;

        // 1. Length Check
        if (password.length() >= 8) {
            strength += 2;
        } else if (password.length() >= 5) {
            strength += 1;
        }

        // 2. Lowercase Check
        if (hasLowercase(password)) strength += 2;

        // 3. Uppercase Check
        if (hasUppercase(password)) strength += 2;

        // 4. Digit Check
        if (hasDigit(password)) strength += 2;

        // 5. Symbol Check
        if (hasSymbol(password)) strength += 2;

        // 6. Pattern Penalty (كود زميلك للأنماط المتكررة)
        for(int i=0; i<password.length()-2; i++) {
            char first = password.charAt(i);
            char second = password.charAt(i+1);
            char third = password.charAt(i+2);

            if((first==second && second==third)||
            (first+1==second && second+1==third)||
            (first-1==second && second-1==third)) {
                strength -= 2;
                break;
            }
        }

        return Math.min(strength, 10);
    }

    //       --- The Main Functions  ---

    private static boolean hasLowercase(String password) {
        for (char c : password.toCharArray()) {
            if (c >= 'a' && c <= 'z') return true;
        }
        return false;
    }

    private static boolean hasUppercase(String password) {
        for (char c : password.toCharArray()) {
            if (c >= 'A' && c <= 'Z') return true;
        }
        return false;
    }

    private static boolean hasDigit(String password) {
        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') return true;
        }
        return false;
    }

    private static boolean hasSymbol(String password) {
        String specialChars = "!@#$%^&*()-+";
        for (char c : password.toCharArray()) {
            if (specialChars.indexOf(c) != -1) return true;
        }
        return false;
    }


    //    --- The Counting Functions  ---


    public static int countUppercase(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (c >= 'A' && c <= 'Z') count++;
        }
        return count;
    }


    public static int countLowercase(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (c >= 'a' && c <= 'z') count++;
        }
        return count;
    }


    public static int countNumbers(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') count++;
        }
        return count;
    }

    public static int countSymbols(String password) {
        int count = 0;
        String specialChars = "!@#$%^&*()-+";
        for (char c : password.toCharArray()) {
            if (specialChars.indexOf(c) != -1) count++;
        }
        return count;
    }
}