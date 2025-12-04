import java.security.SecureRandom;
import java.util.*;

public class PasswordGenerator {
    private static final SecureRandom rnd = new SecureRandom();
    
    public static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-+_=[]{}|<>?/~";
        String all = upper + lower + digits + special;
        StringBuilder pass = new StringBuilder();

        pass.append(upper.charAt(rnd.nextInt(upper.length())));
        pass.append(lower.charAt(rnd.nextInt(lower.length())));
        pass.append(digits.charAt(rnd.nextInt(digits.length())));
        pass.append(special.charAt(rnd.nextInt(special.length())));

        for (int i = 4; i < length; i++) {
            pass.append(all.charAt(rnd.nextInt(all.length())));
        }

        char[] newpassword=pass.toString().toCharArray();

        for (int i = newpassword.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            // Swap to overcome predictability
            char temp = newpassword[i];
            newpassword[i] = newpassword[j];
            newpassword[j] = temp;
        }

        return new String(newpassword);
    }
}
