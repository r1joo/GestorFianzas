import actions.*; // Importamos tus clases Ingreso, Gasto y Transaction

public class Main {

    // CORRECCIÓN 1: La firma correcta para que Java arranque
    public static void main(String[] args) {

        System.out.println("--- Iniciando Gestor de Finanzas ---");

        // 1. Instanciamos la Cartera (Wallet) con 100 euros iniciales
        Wallet miCartera = new Wallet(100.0);

        // 2. Creamos un Ingreso y un Gasto de prueba
        Ingreso nomina = new Ingreso(1200.0, "Nomina de Enero");
        Gasto compraSuper = new Gasto(50.0, "Compra Semanal Mercadona");

        // 3. Mostramos la info individualmente (para probar que las clases van bien)
        System.out.println("\n--- Probando objetos individuales ---");
        nomina.showInfo();
        compraSuper.showInfo(); // <--- Fíjate si aquí sale "Ingreso" o "Gasto" ;)

        // 4. EL RETO PENDIENTE:
        // Queremos meter 'nomina' y 'compraSuper' dentro de 'miCartera'.
        // Pero... ¡Wallet no tiene un método para recibir transacciones!
        
        // Descomenta la línea de abajo cuando crees ese método en Wallet.java:
        miCartera.addTransaction(nomina);
        miCartera.addTransaction(compraSuper);

        // 5. Mostrar el estado de la cartera
        System.out.println("\n------- RESUMEN DE LA CARTERA ------");
        miCartera.showTotalAmount();
        miCartera.showTransactions(); // Ahora mismo saldrá vacía
    }
}
