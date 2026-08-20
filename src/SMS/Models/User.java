package SMS;

import java.util.ArrayList;

public class User {
    protected static int nextID = 1;
    protected int id;
    protected double balance;
    protected String username;
    protected String password;
    protected ArrayList<Stock> stocks;

    public User(double balance, String username, String password, ArrayList<Stock> stocks){
        this.balance = balance;
        this.username = username;
        this.password = password;
        this.stocks = stocks;
        this.id = nextID;
        nextID++;
    }
    public User(){};

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public String getPassword() {
        return password;
    }
    public double getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        if(password.length() >= 8 && password.contains("@") && !password.contains(" "))
        this.password = password;
        else System.out.println("Password should follow the criteria given above.");
    }

    public  int getId() {
        return id;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public void setBalance(double balance) {
        if (balance >= 0)
        this.balance = balance;
        else System.out.println("Balance cannot be in negative. Operation failed.");
    }

    public void setUsername(String username) {
        if(!username.contains(" "))
        this.username = username;
        else System.out.println("Username should follow the criteria given above.");
    }


    public String toString(){
        return "ID: " + id + "\nUsername: " + username + "\nCurrent Balance: " + balance;
    }
}
