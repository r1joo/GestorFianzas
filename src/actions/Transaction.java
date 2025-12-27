package actions;

public abstract class Transaction {

    private double amount;
    private String description;

    public Transaction(double amount, String des){
        this.amount = amount;
        this.description = des;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double a){
        this.amount = a;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String des){
        this.description = des;
    }
    
    public abstract void showInfo();
}
