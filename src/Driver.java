import java.util.Scanner;
import java.util.ArrayList;

void main(){
    Scanner scan = new Scanner(System.in);
    int input = 0;
    int mInput = 0;
    int s = 0;
    int am = 0;
    boolean correctCredentials = false, exists = false,  stockExists = false;
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Stock> stock1 = new ArrayList<Stock>();
    User hadi = new User(10000,"HD88","Hadi@1234",stock1);
    users.add(hadi);
    String uUserName;
    String uPassWord;
    User currentUser = new User();
    String loginUsername = "";
    String loginPassword = "";
    Stock apple = new Stock("APPL",220,300);
    Stock tesla = new Stock("TESL",315,125);
    Stock amd = new Stock("AMD",95,500);
    Stock nvidia = new Stock ("NVID",60,980);
    Stock microsoft = new Stock("MICR",410,240);

    ArrayList<Stock> allStocks = new ArrayList<Stock>();
    allStocks.add(apple);
    allStocks.add(tesla);
    allStocks.add(amd);
    allStocks.add(nvidia);
    allStocks.add(microsoft);
    try {
        while (input == 0) {

            System.out.println("\n\nWelcome to the stock market simulator!\n*****************************");
            System.out.print("Enter the number that corresponds to the action that you want\n\n1.Login\n2.Register\n3.Exit\n\nInput: ");

            input = scan.nextInt();


            if(input == 3) {
                System.out.println("Goodbye👋");
                 break;
            }
            while (input == 2) {

                String newUsername;
                String newPassword;

                ArrayList<Stock> stocks = new ArrayList<Stock>();
                System.out.print("\n\nRegister your account:\n**********************\n");
                System.out.print("Enter a username: ");
                newUsername = scan.next().trim();


                System.out.print("Enter a secure password (min. 8 characters ,includes @, one uppercase letter (A), and one lower case (a)): ");
                newPassword = scan.next().trim();


                if (newUsername.contains(" ") || newUsername.length() < 3) {
                    System.out.println("Username cannot contain spaces and / or less than 3 characters.");
                    uUserName = "";
                } else {
                    uUserName = newUsername;
                }

                boolean hasUpper = false;

                for(int i = 0; i < newPassword.length(); i++){
                    if(Character.isUpperCase(newPassword.charAt(i))){
                        hasUpper = true;
                        break;
                    }
                }

                boolean hasLower = false;

                for(int i = 0; i < newPassword.length(); i++ )
                {
                    if(Character.isLowerCase(newPassword.charAt(i))){
                        hasLower = true;
                        break;
                    }
                }

                if(newPassword.length() < 8 || !newPassword.contains("@") || !hasUpper || !hasLower) {
                    System.out.println("Password didn't follow the guidelines.");
                    uPassWord = "";
                    input = 0;
                } else {
                    uPassWord = newPassword;
                    input = 0;
                }

                for (int i = 0; i < users.size(); i++) {

                    if (uUserName.equals(users.get(i).getUsername())) {
                        exists = true;
                    }
                }
                if (!exists) {
                    User user = new User(10000, uUserName, uPassWord, stocks);
                    users.add(user);
                    input = 0;
                } else {

                    System.out.println("User already exists.");
                }
                exists = false;


            }
            if (input == 1) {

                System.out.println("\n\nLogin with your credentials:\n**********************\n");
                System.out.println("Enter your username: ");
                loginUsername = scan.next().trim();
                System.out.println("Enter your password: ");
                loginPassword = scan.next().trim();

            }

            for (int i = 0; i < users.size(); i++) {

                if (loginUsername.equals(users.get(i).getUsername()) && loginPassword.equals(users.get(i).getPassword())) {
                    correctCredentials = true;
                    currentUser.setStocks(users.get(i).getStocks());
                    currentUser.setUsername(users.get(i).getUsername());
                    currentUser.setBalance(users.get(i).getBalance());
                }

                else correctCredentials = false;
            }
            if (correctCredentials) {

                System.out.println("\n\nLogin Successful!");
                input = 4;
            } else {

//               System.out.println("Login Failed!");
                input = 0;
            }

            while (input == 4) {

                System.out.println("\n\n==============================\n\tSTOCK MARKET SIMULATOR\n==============================");
                System.out.println("Welcome!, " + currentUser.getUsername());
                System.out.println("Current Balance: " + currentUser.getBalance() + "$");
                System.out.println("==============================\n\n");
                System.out.println("1. View Market\n2. View Portfolio\n3. Deposit Money\n4. Refresh Market\n5. Logout\n\n");
                System.out.print("Input: ");
                mInput = scan.nextInt();

                while(mInput == 1){

                    System.out.println("---------------------------------------------");
                    System.out.printf(
                            "%-15s %-12s %-10s %-5s%n",
                            "Stock",
                            "Price",
                            "Available",
                            "ID"
                    );

                    System.out.println("---------------------------------------------");

                    for(int i = 0; i < allStocks.size(); i++){
                        allStocks.get(i).print();
                    }
                    System.out.println("---------------------------------------------");

                    System.out.println("1. Buy Stock\n2. Return\n");

                    if(scan.nextInt() == 1){

                        System.out.print("\n\nChoose the stock you want to buy by typing the S-ID: ");
                        s = scan.nextInt();

                        System.out.print("Type in the quantity: ");
                        am = scan.nextInt();

                        for(int i = 0; i < allStocks.size() ; i++){
                            stockExists = false;
                            if(s == allStocks.get(i).getSID() && am <= allStocks.get(i).getStockAmount()){
                                stockExists = true;
                                Stock st = new Stock(allStocks.get(i).getName(),allStocks.get(i).getStockPrice(),am);

                                if(currentUser.getBalance() >= (allStocks.get(i).getStockPrice() * am) ) {

                                    allStocks.get(i).setStockAmount(allStocks.get(i).getStockAmount() - am);
                                    currentUser.getStocks().add(st);
                                    currentUser.setBalance(currentUser.getBalance() - (st.getStockPrice() * am));
                                    System.out.println("You bought " + am + " of " + st.getName() + ".\nYour balance is now: " + currentUser.getBalance() + "$");
                                    System.out.println("There are " + allStocks.get(i).getStockAmount() + " left of " +allStocks.get(i).getName());
                                        break;
                                }
                            }
                            if(s == allStocks.get(i).getSID() && am > allStocks.get(i).getStockAmount() && stockExists){
                                System.out.println("Quantity entered not available.");
                                break;
                            }

                        }

                         if(!stockExists) {
                            System.out.println("Unknown ID entered.");
                        }

                    }
                    else if(scan.nextInt() == 2)
                        break;

                }
            }

        }
    }  catch(Exception ex){
            System.out.println("An error has occurred: " + ex);
            input = 0;
        }
}