import actions.*;
import java.util.*;

public class Wallet {
    private double totalAmount;

    private List<Transaction> transactions;

    public Wallet(double total){
        this.totalAmount = total;
        this.transactions = new ArrayList<>();
    }

    public double getTotalAmount(){
        return this.totalAmount;
    }

    public List<Transaction> getAllTransactions(){
        return transactions;
    }

    public void addTransaction(Transaction movement){
        if(movement instanceof Ingreso){
            this.totalAmount += movement.getAmount();
        } else if(movement instanceof Gasto){
            this.totalAmount -= movement.getAmount();
        }

        this.transactions.add(movement);
    }

    public void showTotalAmount(){
        System.out.println("--- Total Amount: " + totalAmount + "$");
    }

    public void showTransactions(){
        System.out.println("--- Movements");
        for(Transaction t: transactions){
            t.showInfo();
        }
    }

}
