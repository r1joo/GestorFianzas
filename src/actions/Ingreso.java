package actions;

//Clase para realzar el ingreso
public class Ingreso extends Transaction{

    public Ingreso(double amount, String des){
        super(amount, des);
    }
    public void showInfo(){
        System.out.println("Ingreso: " + getAmount());
        System.out.println("Description: " + getDescription());
        System.out.println("-------------------------");
    }
}
