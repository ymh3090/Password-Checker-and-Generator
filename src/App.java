public class App {
    public static void main(String[] args) throws Exception {
        
    }







    //a method to return password strength on a scale of 1-10
    public int checkpasswordstrength(String password) {
        int strength = 0;

        if (password.length() >= 8) {
            strength += 2;
        } else if (password.length() >= 5) {
            strength += 1;
        }

        if (password.matches(".*[a-z].*")) {
            strength += 2;
        }

        if (password.matches(".*[A-Z].*")) {
            strength += 2;
        }

        if (password.matches(".*\\d.*")) {
            strength += 2;
        }

        if (password.matches(".*[!@#$%^&*()-+].*")) {
            strength += 2;
        }

        return Math.min(strength, 10);
        
    }
}
