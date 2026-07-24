public class Stock {
    private String name;
    private double stockPrice ;
    private int stockAmount;
    private static int nextSID = 1;
    private int SID;
    public Stock(String name, double stockPrice, int stockAmount){
        this.name = name;
        this.stockPrice = stockPrice;
        this.stockAmount = stockAmount;
        this.SID = nextSID;
        nextSID++;
    }

    public String getName() {
        return name;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStockPrice(double price) {
        this.stockPrice = price;
    }

    public int getSID() {
        return SID;
    }

    public void print() {
        System.out.printf(
                "%-15s $%-10.2f %-11d %-6d%n",
                name,
                stockPrice,
                stockAmount,
                SID
        );
    }
}
