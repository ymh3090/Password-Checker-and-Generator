public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(checkpasswordstrength("Ab1!cdef"));  //8
        System.out.println(checkpasswordstrength("P@ssw0rd23232")); //10
        System.out.println(checkpasswordstrength("Abc123!!!")); //(pattern penalty)    }
    }






    //a method to return password strength on a scale of 1-10
    public static  int checkpasswordstrength(String password) {
        int strength = 0;

        //length check

        if (password.length() >= 8) {
            strength += 2;
        } else if (password.length() >= 5) {
            strength += 1;
        }

        
        //lowercase  check 
        boolean hasLower = false;
        for (char c : password.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                hasLower = true;
                break;
            }
        }

        if (hasLower) {
            strength += 2;
        }

        
        
        //uppercase check
        
        boolean hasupper = false;
        for (char c : password.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                hasupper = true;
                break;
            }
        }

        if (hasupper) {
            strength += 2;
        }





        //digit check
        /* 
        if (password.matches(".*\\d.*")) {
            strength += 2;
        }
        */
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                hasDigit = true;
                break;
            }
        }
        if (hasDigit) {
            strength += 2;
        }










        //special character check

        /*if (password.matches(".*[!@#$%^&*()-+].*")) {
            strength += 2;
        }
        */

        boolean hasSpecial = false;
        String specialChars = "!@#$%^&*()-+";
        for (char c : password.toCharArray()) {
            if (specialChars.indexOf(c) != -1) {
                hasSpecial = true;
                break;
            }
        }
        if (hasSpecial) {
            strength += 2;
        }


        //check patterns penalities 
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

    public static int countuppercase(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (c>='A' && c<='Z') {
                count++;
            }
        }
        return count;
    }

    public static int countlowercase(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                count++;
            }
        }
        return count;
    }



}
