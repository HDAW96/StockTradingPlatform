import SMS.Stock;
import SMS.User;

import java.util.Scanner;
import java.util.ArrayList;

void main(){

    Scanner scan = new Scanner(System.in);

    // Variables of 2 program phases, w/ SID and Amount
    int input = 0,  mInput = 0, s = 0, am = 0;
    //Login + SMS.Stock variables
    boolean correctCredentials = false, exists = false,  stockExists = false;

    // SMS.User and SMS.Stock Array Lists
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Stock> stock1 = new ArrayList<Stock>();


    User hadi = new User(10000,"HD88","Hadi@1234",stock1);
    User currentUser = new User();
    users.add(hadi);


    String uUserName;
    String uPassWord;


    String loginUsername = "";
    String loginPassword = "";

    //Dummy SMS.Stock Data
    ArrayList<Stock> allStocks = new ArrayList<Stock>();


    Stock apple = new Stock("APPL",220,300);
    Stock tesla = new Stock("TESL",315,125);
    Stock amd = new Stock("AMD",95,500);
    Stock nvidia = new Stock ("NVID",60,980);
    Stock microsoft = new Stock("MICR",410,240);


    allStocks.add(apple);
    allStocks.add(tesla);
    allStocks.add(amd);
    allStocks.add(nvidia);
    allStocks.add(microsoft);


        while (input == 0) {

            try {

                System.out.println("\n\n📈Welcome to the stock market simulator!📉\n******************************************");
                System.out.print("Enter the number that corresponds to the action that you want\n\n1. 🔑LOGIN\n2. 📝REGISTER\n3. 👋EXIT\n\n👉Input: ");


                input = scan.nextInt();


                if (input == 3) {
                    System.out.println("👋Goodbye!");
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

                    //Checking Upper and Lower case letters in the new password:
                    boolean hasUpper = false;


                    for (int i = 0; i < newPassword.length(); i++) {

                        if (Character.isUpperCase(newPassword.charAt(i))) {

                            hasUpper = true;
                            break;

                        }
                    }


                    boolean hasLower = false;


                    for (int i = 0; i < newPassword.length(); i++) {

                        if (Character.isLowerCase(newPassword.charAt(i))) {
                            hasLower = true;
                            break;

                        }
                    }

                    if (newPassword.length() < 8 || !newPassword.contains("@") || !hasUpper || !hasLower) {

                        System.out.println("Password didn't follow the guidelines.");
                        uPassWord = "";
                        input = 0;

                    }
                    else {

                        uPassWord = newPassword;
                        input = 0;

                    }

                    for (int i = 0; i < users.size(); i++) {

                        if (uUserName.equals(users.get(i).getUsername())) {

                            exists = true;

                        }

                    }
                    if (!exists) {

                        System.out.println("✅SMS.User registered successfully!");
                        User user = new User(10000, uUserName, uPassWord, stocks);
                        users.add(user);
                        input = 0;

                    } else {

                        System.out.println("❌SMS.User already exists.");

                    }

                    exists = false;

                }
                if (input == 1) {

                    System.out.println("\n\nLogin with your credentials:\n****************************\n");
                    System.out.println("🔑Enter your username: ");
                    loginUsername = scan.next().trim();
                    System.out.println("🔒Enter your password: ");
                    loginPassword = scan.next().trim();

                }
            } catch(InputMismatchException e){

                System.out.println("Enter the input as a number.");
                scan.next();
                continue;

            } catch (Exception e) {

                System.out.println("❗❗An error has occurred.❗❗");
                scan.next();
                continue;

            }

            correctCredentials = false;

            for (int i = 0; i < users.size(); i++) {

                if (loginUsername.equals(users.get(i).getUsername()) && loginPassword.equals(users.get(i).getPassword())) {


                   currentUser  = users.get(i);
                    correctCredentials = true;
                    break;

                }

                else correctCredentials = false;

            }
            if (correctCredentials) {

                System.out.println("\n\n✅Login Successful!");
                input = 4;

            } else {

//              System.out.println("Login Failed!");
                input = 0;

            }

            while (input == 4) {

                System.out.println("\n\n==============================\n📈STOCK MARKET SIMULATOR📉\n==============================");
                System.out.println("👋Welcome!, " + currentUser.getUsername());
                System.out.println("💰Current Balance: " + currentUser.getBalance() + "$");
                System.out.println("==============================\n\n");
                System.out.println("1. 📊VIEW MARKET\n2. 📂VIEW PORTFOLIO\n3. 💵DEPOSIT MONEY\n4. 🔃REFRESH MARKET\n5. 🚪LOGOUT\n\n");

                try {

                    System.out.print("👉Input: ");
                    mInput = scan.nextInt();

                }
                catch (InputMismatchException e){

                    System.out.println("Enter the input as a number.");
                    scan.next();
                    continue;

                }
                catch (Exception e) {

                    System.out.println("❗❗An error has occurred.❗❗");
                    scan.next();
                    continue;

                }
                if (mInput == 5) {

                    System.out.println("Logging Out...");
                    input = 0;
                    correctCredentials = false;
                    currentUser = new User();
                    loginUsername = "";
                    loginPassword = "";
                    break;


                }

                double depositAmount;

                try{
                if (mInput == 3) {

                    System.out.println("💰Current Balance: "+ currentUser.getBalance()+ "$\n💵Enter the amount of $$$ you want to add to your balance: ");
                    depositAmount = scan.nextDouble();

                    if(depositAmount < 0)
                        System.out.println("❌Cannot add negative amount.");
                    else {

                        currentUser.setBalance(currentUser.getBalance() + depositAmount);
                        System.out.println("✅You've added " + depositAmount + "$\n💰Your balance is now " + currentUser.getBalance()+"$.");

                    }
                }
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Enter a positive number.");
                    scan.next();
                    continue;

                }
                catch (Exception e) {

                    System.out.println("❗❗An error has occurred.❗❗");
                    scan.next();
                    continue;

                }
                while (mInput == 1) {

                    System.out.println("\n📊 MARKET OVERVIEW");
                    System.out.println("---------------------------------------------");
                    System.out.printf(
                            "%-15s %-12s %-10s %-5s%n",
                            "SMS.Stock",
                            "Price",
                            "Available",
                            "ID"
                    );

                    System.out.println("---------------------------------------------");

                    for (int i = 0; i < allStocks.size(); i++) {

                        allStocks.get(i).print();

                    }
                    System.out.println("---------------------------------------------");

                    System.out.println("1. 🛒Buy SMS.Stock\n2. ↩Return\n");



                    try {

                        int viewInput = scan.nextInt();

                    if (viewInput == 1) {

                        System.out.print("\n\n🎯Enter the SMS.Stock ID: ");
                        s = scan.nextInt();

                        System.out.print("📦Enter the quantity: ");
                        am = scan.nextInt();

                        stockExists = false;

                        for (int i = 0; i < allStocks.size() ; i++) {



                            if(s == allStocks.get(i).getSID() && am <= allStocks.get(i).getStockAmount() && am >=0){

                                stockExists = true;
                                Stock st = new Stock(allStocks.get(i).getName(),allStocks.get(i).getStockPrice(),am);

                                if(currentUser.getBalance() >= (allStocks.get(i).getStockPrice() * am) ) {

                                    allStocks.get(i).setStockAmount(allStocks.get(i).getStockAmount() - am);
                                    currentUser.getStocks().add(st);
                                    currentUser.setBalance(currentUser.getBalance() - (st.getStockPrice() * am));
                                    System.out.println("✅You bought " + am + " of " + st.getName() + ".\nYour balance is now: " + currentUser.getBalance() + "$");
                                    System.out.println("❕❕There are " + allStocks.get(i).getStockAmount() + " left of " +allStocks.get(i).getName() + "❕❕");
                                        break;

                                }
                            }
                            if(s == allStocks.get(i).getSID() && am > allStocks.get(i).getStockAmount() && stockExists || am < 0){

                                System.out.println("❌Quantity entered not available.");
                                break;

                            }
                            if(s == allStocks.get(i).getSID() && am <=  allStocks.get(i).getStockAmount() && currentUser.getBalance() < (allStocks.get(i).getStockPrice() * am)){

                                System.out.println("❌Insufficient Funds.");
                                break;

                            }

                        }

                         if(!stockExists) {

                            System.out.println("❌Unknown ID entered.");

                        }

                    }
                    else if(viewInput == 2)
                        break;

                }
                    catch(java.util.InputMismatchException e){

                        System.out.println("\n❌Wrong Input. Follow the input type.\n");
                        scan.next();
                        continue;

                    }
                    catch(Exception ex){

                        System.out.println("❗❗An error has occurred.❗❗");
                        scan.next();
                        continue;

                    }
                }

            }

        }
    }
