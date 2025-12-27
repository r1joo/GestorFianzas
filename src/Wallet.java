import actions.*;
import java.util.*;

public class Wallet {
    private double totalAmount;

    private List<Transaction> transactions = new ArrayList<>();

    public Wallet(double total){
        this.totalAmount = total;
        this.transactions;
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
        System.out.println("CANTIDAD TOTAL: " + totalAmount + "$");
    }

    public void showTransactions(){
        for(Transaction t: transactions){
            t.showInfo();
        }
    }

}
