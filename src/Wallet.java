import actions.*;
import java.util.*;

public class Wallet {
    private double totalAmount;

    private List<Transaction> transactions = new ArrayList<>();

    public Wallet(double total){
        this.totalAmount = total;
        this.transactions = transactions;
    }

    public void showTransactions(){
        for(Transaction t: transactions){
            t.showInfo();
        }
    }

}
