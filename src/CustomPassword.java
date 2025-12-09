import java.security.SecureRandom;

public class CustomPassword {
    private static final SecureRandom rnd = new SecureRandom();

    public static String generate(int uppercount, int lowercount, int digitspecialcount, int specialcount) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-+_=[]{}|<>?/~";

        StringBuilder pass = new StringBuilder();

        appendRandomChars(pass, upper, uppercount);
        appendRandomChars(pass, lower, lowercount);
        appendRandomChars(pass, digits, digitspecialcount);
        appendRandomChars(pass, special, specialcount);

        char[] newpassword = pass.toString().toCharArray();

        for (int i = newpassword.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            // Swap to overcome predictability
            char temp = newpassword[i];
            newpassword[i] = newpassword[j];
            newpassword[j] = temp;
        }

        return new String(newpassword);
    }

    private static void appendRandomChars(StringBuilder sb, String source, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(source.charAt(rnd.nextInt(source.length())));
        }
    }
}
