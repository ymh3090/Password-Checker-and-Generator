public class App {
public static void mainMenu()
	{
        Scanner in=new Scanner(System.in);
	System.out.println();
	System.out.println();

        while(true)
		{
			
			System.out.println("1. Check Password Strength");
			System.out.println("2. Generate New Password");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");
            if (!in.hasNextInt())
            {
                System.out.println(">> Invalid input, please enter a number.");
                in.nextLine();
                continue;
            }
			int choice = in.nextInt();
			in.nextLine();
			switch (choice)
			{
			case 1:
				System.out.print("Enter password to check: ");
				String inputPass = in.nextLine();
				System.out.println(">> Strength Score: " + checkpasswordstrength(inputPass) + "/10");
				break;
			case 2:
				System.out.print("Enter desired length (minimum 8): ");
				if (in.hasNextInt())
				{
					int len = in.nextInt();
					in.nextLine();
					if (len < 8)
					{
						System.out.println(">> Error: Rules state minimum length is 8 characters.");
					}
					else
					{
                        System.out.println("here are few passwords:");
                        for(int i=0;i<5;i++)
                        {
                            System.out.println(">> " + generatePassword(len));
                        }
					}
				}
				else
				{
					System.out.println(">> Invalid length!");
					in.nextLine();
				}
				break;
			case 3:
				System.out.println("Goodbye!");
                return;
			default:
				System.out.println(">> Invalid choice, please try again.");
			}
		}
	}
	public static void main(String[] args)
	{
		mainMenu();

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
