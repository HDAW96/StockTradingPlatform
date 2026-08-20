//Importing Models from SMS.Models Package
import SMS.Models.Stock;
import SMS.Models.User;

//Importing File Reading & Writing,IOException, w/ ArrayList and Scanner
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


 void main(){

    Scanner scan = new Scanner(System.in);

    // Variables of 2 program phases, w/ SID and Amount
    int input = 0,  mInput = 0, s = 0, am = 0;

    //Login + Stock variables
    boolean correctCredentials = false, exists = false,  stockExists = false, validUsername = false, validPassword = false;

    // SMS.Models.User and SMS.Models.Stock Array Lists
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Stock> stock1 = new ArrayList<Stock>();
    String userFilePath = "Users.txt";



    //Test User
//    User hadi = new User(15430,"HD88","Hadi@1234",stock1);
    User currentUser = new User();
//    users.add(hadi);


    String uUserName;
    String uPassWord;
    double startingBalance = 15000;

    String loginUsername = "";
    String loginPassword = "";

    //Dummy SMS.Models.Stock Data
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

 try(FileWriter writer = new FileWriter("Market.txt")){

     for (Stock allStock : allStocks) {
         writer.write(allStock.getName() + "," + allStock.getStockPrice() + "," + allStock.getStockAmount() + "," + allStock.getSID() + System.lineSeparator());
     }

     writer.flush();

 } catch (Exception e) {
     throw new RuntimeException(e);
 }

// try(BufferedReader br = new BufferedReader(new FileReader("UserPortfolios.txt")))
// {
//     String line;
//
//     while ((line = br.readLine()) != null)
//     {
//         String []data = line.split(",");
//
//         if(data.length == 4){
//
//             String username = data[0];
//             String stockName = data[1];
//             double stockPrice = Double.parseDouble(data[2]);
//             int stockAmount = Integer.parseInt(data[3]);
//             Stock userStock = new Stock();
//             if(username.equals(currentUser.getUsername())) {
//                 userStock = new Stock(stockName, stockPrice, stockAmount);
//                 currentUser.getStocks().add(userStock);
//             }
//
//
//         }
//     }
// } catch (Exception e) {
//     throw new RuntimeException(e);
// }


     try (BufferedReader br = new BufferedReader(new FileReader(userFilePath)))
     {
         String line;

         while((line = br.readLine()) != null){
             String [] data  = line.split(",");
             if(data.length == 3){
                 String username = data[0];
                 String password = data[1];
                 double balance;
                 balance = Double.parseDouble(data[2]);


                 users.add(new User(balance,username,password, new ArrayList<Stock>()));

             }

         }

     }
     catch (IOException e)
     {
         System.out.println("An error has occurred: "+e);
     }
     catch (NumberFormatException e)
     {
         System.out.println("Error in balance." + e);
     }


        while (input == 0) {

            try {

                System.out.println("\n\n📈Welcome to the stock market simulator!📉\n******************************************");
                System.out.print("Enter the number that corresponds to the action that you want\n\n1. 🔑LOGIN\n2. 📝REGISTER\n3. 👋EXIT\n\n👉Input: ");


                input = scan.nextInt();


                if (input == 3) {
                    System.out.println("\n👋Goodbye!\n");
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
                        validUsername = false;

                    } else {

                        uUserName = newUsername;
                        validUsername = true;
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
                        validPassword = false;
                    }
                    else {

                        uPassWord = newPassword;
                        input = 0;
                        validPassword = true;

                    }

                    for (int i = 0; i < users.size(); i++) {

                        if (uUserName.equals(users.get(i).getUsername())) {

                            exists = true;

                        }

                    }
                    if (!exists && validUsername && validPassword) {

                            User user = new User(startingBalance, uUserName, uPassWord, stocks);
                            users.add(user);
                            System.out.println("✅User registered successfully!");
                            input = 0;

                    } else {
                        System.out.println("❌User not added.");
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

                if (loginUsername.equals(users.get(i).getUsername()) && loginPassword.equals(users.get(i).getPassword()))
                {
                    currentUser  = users.get(i);
                    correctCredentials = true;
                    break;

                }

                else correctCredentials = false;

            }
            if (correctCredentials)
            {

                System.out.println("\n\n✅Login Successful!");
                input = 4;

            } else
            {
//              System.out.println("Login Failed!");
                input = 0;

            }

            try(BufferedReader br = new BufferedReader(new FileReader("UserPortfolios.txt")))
            {
                String line;
                while ((line = br.readLine()) != null)
                {
                    String []data = line.split(",");

                    if(data.length == 4){

                        String username = data[0];
                        String stockName = data[1];
                        double stockPrice = Double.parseDouble(data[2]);
                        int stockAmount = Integer.parseInt(data[3]);
                        Stock userStock = new Stock();
                        if(username.equals(currentUser.getUsername())) {
                            userStock = new Stock(stockName, stockPrice, stockAmount);
                            currentUser.getStocks().add(userStock);
                        }


                    }
                }

            }
            catch (IOException e)
            {
                System.out.println("An error has occurred: " + e);
            }
//            try (FileWriter writer = new FileWriter("UserPortfolios.txt",true))
//            {
//
//                for(int i = 0; i < currentUser.getStocks().size(); i++) {
//
//                    writer.write(currentUser.getUsername() + "," + currentUser.getStocks().get(i).getName() + "," +
//                            currentUser.getStocks().get(i).getStockPrice() + "," +
//                            currentUser.getStocks().get(i).getStockAmount() +
//                            System.lineSeparator());
//                }
//
//                writer.flush();
//
//            }
//            catch (IOException e)
//            {
//                System.out.println("An error has occurred: " + e);
//            }
            while (input == 4)
            {

                System.out.println("\n\n=================================\n   📈STOCK MARKET SIMULATOR📉\n=================================");
                System.out.println("👋Welcome!, " + currentUser.getUsername());
                System.out.println("💰Current Balance: " + currentUser.getBalance() + "$");
                System.out.println("=================================\n\n");
                System.out.println("1. 📊VIEW MARKET\n2. 📂VIEW PORTFOLIO\n3. 💵DEPOSIT MONEY\n4. 🚪LOGOUT\n\n");

                try {

                    System.out.print("👉Input: ");
                    mInput = scan.nextInt();

                }
                catch (InputMismatchException e)
                {

                    System.out.println("Enter the input as a number.");
                    scan.next();
                    continue;

                }
                catch (Exception e)
                {

                    System.out.println("❗❗An error has occurred.❗❗");
                    scan.next();
                    continue;

                }
                if (mInput == 4)
                {

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
                if (mInput == 3)
                {

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
                while (mInput == 1)
                {

                    System.out.println("\n📊 MARKET OVERVIEW");
                    System.out.println("---------------------------------------------");
                    System.out.printf(
                            "%-15s %-12s %-10s %-5s%n",
                            "Stock",
                            "Price",
                            "Available",
                            "ID"
                    );

                    System.out.println("---------------------------------------------");

                    for (int i = 0; i < allStocks.size(); i++)
                    {

                        allStocks.get(i).print();

                    }
                    System.out.println("---------------------------------------------");

                    System.out.println("1. 🛒Buy Stock\n2. ↩Return\n");



                    try {

                        int viewInput = scan.nextInt();

                    if (viewInput == 1)
                    {

                        System.out.print("\n\n🎯Enter the Stock ID: ");
                        s = scan.nextInt();

                        System.out.print("📦Enter the quantity: ");
                        am = scan.nextInt();

                        stockExists = false;
                        boolean sameStock = false;

                        for (int i = 0; i < allStocks.size() ; i++)
                        {



                            if(s == allStocks.get(i).getSID() && am <= allStocks.get(i).getStockAmount() && am >=0)
                            {

                                stockExists = true;
                                Stock st = new Stock(allStocks.get(i).getName(),allStocks.get(i).getStockPrice(),am);

                                if(currentUser.getBalance() >= (allStocks.get(i).getStockPrice() * am) )
                                {

                                    for(int j = 0; j < currentUser.getStocks().size(); j++)
                                    {
                                        if (currentUser.getStocks().get(j).getName().equals(st.getName())) {
                                            currentUser.getStocks().get(j).setStockAmount(currentUser.getStocks().get(j).getStockAmount() + am);
                                            sameStock = true;
                                            break;
                                        }

                                    }
                                    allStocks.get(i).setStockAmount(allStocks.get(i).getStockAmount() - am);
                                    if (!sameStock)
                                    {
                                        currentUser.getStocks().add(st);
                                    }

                                    //Deducting
                                    currentUser.setBalance(currentUser.getBalance() - (st.getStockPrice() * am));


                                    System.out.println("✅You bought " + am + " of " + st.getName() + ".\nYour balance is now: " + currentUser.getBalance() + "$");
                                    System.out.println("❕❕There are " + allStocks.get(i).getStockAmount() + " left of " +allStocks.get(i).getName() + "❕❕");
                                        break;

                                }
                            }
                            if(s == allStocks.get(i).getSID() && am > allStocks.get(i).getStockAmount() && stockExists || am < 0)
                            {

                                System.out.println("❌Quantity entered not available.");
                                break;

                            }
                            if(s == allStocks.get(i).getSID() && am <=  allStocks.get(i).getStockAmount() && currentUser.getBalance() < (allStocks.get(i).getStockPrice() * am))
                            {

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


                while (mInput == 2){
                    try {


                        double totalPortfolioSum = 0;

                        System.out.println("\n\n📂YOUR PORTFOLIO:\n====================================\n\n👋Welcome, " + currentUser.getUsername() + "\n💰Current Balance: " + currentUser.getBalance() + "$\n\n");
                        System.out.println("------------------------------------------------\n");
                        System.out.printf("%-15s %-12s %-10s %-12s%n", "Stock", "Price", "Amount", "Subtotal");
                        System.out.println("------------------------------------------------\n");

                        for (int i = 0; i < currentUser.getStocks().size(); i++) {

                            double subtotal = currentUser.getStocks().get(i).getStockPrice() *
                                    currentUser.getStocks().get(i).getStockAmount();

                            totalPortfolioSum += subtotal;

                            System.out.printf("%-15s $%-12.2f %-10d $%-12.2f%n",
                                    currentUser.getStocks().get(i).getName(),
                                    currentUser.getStocks().get(i).getStockPrice(),
                                    currentUser.getStocks().get(i).getStockAmount(),
                                    subtotal);
                        }
                        System.out.println("------------------------------------------------\n");
                        System.out.printf("📊Portfolio Value: %.2f$\n", totalPortfolioSum);
//                        System.out.printf("💰Current Balance: %.2f$\n", currentUser.getBalance());
                        System.out.printf("💼Total Account Value: %.2f$\n", totalPortfolioSum + currentUser.getBalance());
                        System.out.println("================================================\n\n");

                        System.out.print("Type in any number to ↩Return:");
                        int r = scan.nextInt();
                        if (r == 1) {

                            break;

                        } else {

                            break;

                        }

                    } catch (InputMismatchException e) {

                        System.out.println("Enter an integer.");
                        scan.next();
                        continue;

                    } catch (Exception e) {

                        System.out.println("❗❗An error has occurred.❗❗");
                        scan.next();
                        continue;

                    }

                }

            }

        }
        try(FileWriter writer = new FileWriter("Users.txt")){

            for(int i = 0 ; i < users.size(); i++){
                writer.write(users.get(i).getUsername() + "," + users.get(i).getPassword() + "," + users.get(i).getBalance()+System.lineSeparator());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try(FileWriter writer = new FileWriter("UserPortfolios.txt",true)){

            for(int i = 0; i< users.size(); i++){
             for(int j = 0; j < users.get(i).getStocks().size();j++){
                 writer.write(users.get(i).getUsername() + "," + users.get(i).getStocks().get(j).getName() + "," + users.get(i).getStocks().get(j).getStockPrice() + "," + users.get(i).getStocks().get(j).getStockAmount() + System.lineSeparator());
             }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }