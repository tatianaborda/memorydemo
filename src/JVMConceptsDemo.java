import java.util.ArrayList;
import java.util.List;

public class JVMConceptsDemo {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACION DE CONCEPTOS JVM ===");
        System.out.println("Seleccione el escenario:");
        System.out.println("1. Simulacion de OutOfMemoryError");
        System.out.println("2. Simulacion de StackOverflowError");
        System.out.println("3. Demostracion de Garbage Collectors");

        int choice = 3; // Cambiar el valor para probar diferentes escenarios

        switch (choice) {
            case 1:
                simulateOutOfMemoryError();
                break;
            case 2:
                simulateStackOverflowError();
                break;
            case 3:
                demonstrateGarbageCollectors();
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    // Simula un OutOfMemoryError al consumir todo el espacio del Heap
    private static void simulateOutOfMemoryError() {
        System.out.println("=== Simulacion de OutOfMemoryError ===");
        List<int[]> memoryHog = new ArrayList<>();
        try {
            while (true) {
                memoryHog.add(new int[1_000_000]); // Crear grandes bloques de memoria
                printMemoryStatus();
            }
        } catch (OutOfMemoryError e) {
            System.err.println("OutOfMemoryError! : " + e.getMessage());
        }
    }

    // Simula un StackOverflowError con recursion infinita
    private static void simulateStackOverflowError() {
        System.out.println("=== Simulacion de StackOverflowError ===");
        try {
            recursiveMethod();
        } catch (StackOverflowError e) {
            System.err.println("StackOverflowError! : " + e.getMessage());
        }
    }

    private static void recursiveMethod() {
        recursiveMethod(); // Llamada recursiva infinita
    }

    // Demuestra el uso de Garbage Collectors con objetos temporales
    private static void demonstrateGarbageCollectors() {
        System.out.println("=== Demostracion de Garbage Collectors ===");
        for (int i = 0; i < 10; i++) {
            createGarbage(i);
            System.out.println("Ciclo " + i + " completado. Forzando GC...");
            System.gc(); // Sugerir la ejecucion del Garbage Collector
            printMemoryStatus();
        }
    }

    private static void createGarbage(int iteration) {
        List<int[]> temporaryGarbage = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            temporaryGarbage.add(new int[1000]); // Crear objetos temporales
        }
        System.out.println("Se genero basura en la iteracion " + iteration);
    }

    // Metodo para monitorear el estado de la memoria
    private static void printMemoryStatus() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("=================================");
        System.out.println("Memoria total: " + totalMemory / (1024 * 1024) + " MB");
        System.out.println("Memoria libre: " + freeMemory / (1024 * 1024) + " MB");
        System.out.println("Memoria usada: " + usedMemory / (1024 * 1024) + " MB");
        System.out.println("=================================");
    }
}
