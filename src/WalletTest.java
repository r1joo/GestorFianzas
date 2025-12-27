import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext; // Necesario para el contexto
import org.junit.jupiter.api.extension.RegisterExtension; // Para registrar el vigilante
import org.junit.jupiter.api.extension.TestWatcher; // El espía
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import actions.*;
import java.util.List;
import java.util.Optional;

class WalletTest {

    private Wallet wallet;
    private final double DELTA = 0.001;

    // --- VARIABLES GLOBALES PARA ESTADÍSTICAS ---
    static int testsPasados = 0;
    static int testsFallados = 0;

    // --- COLORES PARA LA CONSOLA (ANSI) ---
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    // --- EL "VIGILANTE" (TEST WATCHER) ---
    // Este bloque de código se ejecuta automáticamente después de cada test individual
    @RegisterExtension
    TestWatcher watcher = new TestWatcher() {
        @Override
        public void testSuccessful(ExtensionContext context) {
            testsPasados++;
            imprimirResultado("✅ PASSED", context.getDisplayName(), ANSI_GREEN);
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            testsFallados++;
            imprimirResultado("❌ FAILED", context.getDisplayName(), ANSI_RED);
        }

        @Override
        public void testDisabled(ExtensionContext context, Optional<String> reason) {
            imprimirResultado("⚠️ SKIPPED", context.getDisplayName(), ANSI_YELLOW);
        }
    };

    // Método auxiliar para imprimir bonito
    private void imprimirResultado(String estado, String nombre, String color) {
        System.out.println(color + String.format("%-10s", estado) + ANSI_RESET + " | " + nombre);
    }

    // --- RESUMEN FINAL ---
    @AfterAll
    static void mostrarResumen() {
        int total = testsPasados + testsFallados;
        System.out.println("\n" + "=".repeat(40));
        System.out.println(ANSI_BLUE + "       RESUMEN DE EJECUCIÓN       " + ANSI_RESET);
        System.out.println("=".repeat(40));
        System.out.println("Total Tests lanzados : " + total);
        System.out.println(ANSI_GREEN + "Completados con éxito: " + testsPasados + "/" + total + ANSI_RESET);
        
        if (testsFallados > 0) {
            System.out.println(ANSI_RED + "Fallidos             : " + testsFallados + "/" + total + ANSI_RESET);
            System.out.println(ANSI_RED + "\n¡ALERTA! Hay errores que revisar." + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "\n¡ENHORABUENA! Todo el código funciona." + ANSI_RESET);
        }
        System.out.println("=".repeat(40));
    }

    // ==========================================
    //       AQUÍ EMPIEZAN TUS TESTS NORMALES
    // ==========================================

    @BeforeEach
    void setUp() {
        wallet = new Wallet(0.0);
    }

    @Test
    @DisplayName("Saldo Inicial")
    void testSaldoInicial() {
        Wallet miCartera = new Wallet(100.50);
        assertEquals(100.50, miCartera.getTotalAmount(), DELTA);
    }

    @Test
    @DisplayName("Añadir Ingreso")
    void testIngreso() {
        Ingreso ing = new Ingreso(500.0, "Nómina");
        wallet.addTransaction(ing);
        assertEquals(500.0, wallet.getTotalAmount(), DELTA);
    }

    @Test
    @DisplayName("Añadir Gasto")
    void testGasto() {
        wallet.addTransaction(new Ingreso(1000.0, "Base"));
        wallet.addTransaction(new Gasto(200.0, "Alquiler"));
        assertEquals(800.0, wallet.getTotalAmount(), DELTA);
    }

    @Test
    @DisplayName("Operaciones Mixtas")
    void testOperacionesMixtas() {
        wallet.addTransaction(new Ingreso(100.0, "In"));
        wallet.addTransaction(new Gasto(20.0, "Out"));
        wallet.addTransaction(new Ingreso(10.0, "In"));
        assertEquals(90.0, wallet.getTotalAmount(), DELTA);
    }

    @ParameterizedTest(name = "Ingreso de {0} eur") // {0} se sustituye por el valor
    @ValueSource(doubles = { 10.0, 99.99, 0.01, 5000.0 })
    @DisplayName("Multiples Ingresos")
    void testMultiplesIngresos(double cantidad) {
        wallet.addTransaction(new Ingreso(cantidad, "Test"));
        assertEquals(cantidad, wallet.getTotalAmount(), DELTA);
    }

    @ParameterizedTest(name = "Desc: \"{0}\"")
    @ValueSource(strings = { "Compra", " ", "1234", "Ñandú" })
    @DisplayName("Descripciones Varias")
    void testDescripciones(String texto) {
        wallet.addTransaction(new Gasto(10.0, texto));
        assertEquals(texto, wallet.getAllTransactions().get(0).getDescription());
    }
}