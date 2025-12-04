import java.util.Random;

public class PasswordGenerator {
    
    public static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-+";
        String all = upper + lower + digits + special;
        StringBuilder pass = new StringBuilder();
        Random rnd = new Random();

        pass.append(upper.charAt(rnd.nextInt(upper.length())));
        pass.append(lower.charAt(rnd.nextInt(lower.length())));
        pass.append(digits.charAt(rnd.nextInt(digits.length())));
        pass.append(special.charAt(rnd.nextInt(special.length())));

        for (int i = 4; i < length; i++) {
            pass.append(all.charAt(rnd.nextInt(all.length())));
        }

        return pass.toString();
    }
}