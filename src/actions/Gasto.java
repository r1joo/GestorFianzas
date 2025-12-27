package actions;
public class Gasto extends Transaction{

    public Gasto(double amount, String des){
        super(amount, des);
    }

    public void showInfo(){
        System.out.println("Ingreso: " + getAmount());
        System.out.println("Description: " + getDescription());
    }

}
