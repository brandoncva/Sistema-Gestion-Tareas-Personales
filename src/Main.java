import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static GestorTareas gestor = new GestorTareas();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opcion: ");
            switch (opcion) {
                case 1 -> opcionAgregar();
                case 2 -> gestor.listarTareas();
                case 3 -> opcionMarcarCompletada();
                case 4 -> opcionEditar();
                case 5 -> opcionListarPorEstado();
                case 6 -> opcionEliminar();
                case 7 -> System.out.println("Saliendo... ¡Éxitos con tu proyecto!");
                default -> System.out.println("Opcion invalida. Intenta nuevamente.");
            }
        } while (opcion != 7);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== GESTOR DE TAREAS PERSONALES ===");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar todas las tareas");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Editar una tarea");
        System.out.println("5. Listar por estado (pendientes/completadas)");
        System.out.println("6. Eliminar tarea");
        System.out.println("7. Salir");
    }

    private static void opcionAgregar() {
        System.out.println("\n--- Agregar nueva tarea ---");
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine().trim();
        System.out.print("Fecha límite (texto): ");
        String fecha = sc.nextLine().trim();
        String prioridad = leerPrioridad();
        String categoria = leerCategoria();

        Tarea t = new Tarea(descripcion, fecha, prioridad, categoria);
        gestor.agregarTarea(t);
    }

    private static void opcionMarcarCompletada() {
        System.out.println("\n--- Marcar tarea como completada ---");
        if (gestor.cantidadTareas() == 0) {
            System.out.println("No hay tareas para marcar.");
            return;
        }
        gestor.listarTareas();
        int idx = leerEntero("Ingresa el numero de la tarea a marcar: ");
        boolean ok = gestor.marcarTareaComoCompletada(idx);
        System.out.println(ok ? "Tarea marcada como completada." : "Índice inválido.");
    }

    private static void opcionEditar() {
        System.out.println("\n--- Editar tarea ---");
        if (gestor.cantidadTareas() == 0) {
            System.out.println("No hay tareas para editar.");
            return;
        }
        gestor.listarTareas();
        int idx = leerEntero("Numero de tarea a editar: ");
        Tarea t = gestor.obtenerTarea(idx);
        if (t == null) {
            System.out.println("Indice invalido.");
            return;
        }
        System.out.println("Deja el campo vacio para mantener el valor actual.");
        System.out.println("Valor actual - Descripcion: " + t.getDescripcion());
        System.out.print("Nueva descripción: ");
        String nuevaDesc = sc.nextLine().trim();
        System.out.println("Valor actual - Fecha límite: " + t.getFechaLimite());
        System.out.print("Nueva fecha límite: ");
        String nuevaFecha = sc.nextLine().trim();

        System.out.println("Valor actual - Prioridad: " + t.getPrioridad());
        String nuevaPrioridad = leerPrioridadConDefault(t.getPrioridad());

        System.out.println("Valor actual - Categoría: " + t.getCategoria());
        String nuevaCategoria = leerCategoriaConDefault(t.getCategoria());

        boolean ok = gestor.editarTarea(idx,
                nuevaDesc.isBlank() ? null : nuevaDesc,
                nuevaFecha.isBlank() ? null : nuevaFecha,
                nuevaPrioridad,
                nuevaCategoria);
        System.out.println(ok ? "Tarea actualizada." : "Error al actualizar tarea.");
    }

    private static void opcionListarPorEstado() {
        System.out.println("\n1. Ver pendientes\n2. Ver completadas");
        int opt = leerEntero("Elige: ");
        if (opt == 1) gestor.listarTareasPorEstado(false);
        else if (opt == 2) gestor.listarTareasPorEstado(true);
        else System.out.println("Opción inválida.");
    }

    private static void opcionEliminar() {
        System.out.println("\n--- Eliminar tarea ---");
        if (gestor.cantidadTareas() == 0) {
            System.out.println("No hay tareas para eliminar.");
            return;
        }
        gestor.listarTareas();
        int idx = leerEntero("Número de tarea a eliminar: ");
        boolean ok = gestor.eliminarTarea(idx);
        System.out.println(ok ? "Tarea eliminada." : "Indice inválido.");
    }

    // Helpers
    private static int leerEntero(String mensaje) {
        int num = -1;
        System.out.print(mensaje);
        String linea = sc.nextLine();
        try {
            num = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            num = -1;
        }
        return num;
    }

    private static String leerPrioridad() {
        while (true) {
            System.out.println("Prioridad (1-Alta, 2-Media, 3-Baja): ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1", "Alta", "alta" -> { return "Alta"; }
                case "2", "Media", "media" -> { return "Media"; }
                case "3", "Baja", "baja" -> { return "Baja"; }
                default -> System.out.println("Opcion invalida. Intenta de nuevo.");
            }
        }
    }

    private static String leerCategoria() {
        while (true) {
            System.out.println("Categoría (1-Trabajo, 2-Estudio, 3-Personal, 4-Otro): ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1", "Trabajo", "trabajo" -> { return "Trabajo"; }
                case "2", "Estudio", "estudio" -> { return "Estudio"; }
                case "3", "Personal", "personal" -> { return "Personal"; }
                case "4", "Otro", "otro" -> { return "Otro"; }
                default -> System.out.println("Opcion invalida. Intenta de nuevo.");
            }
        }
    }

    private static String leerPrioridadConDefault(String defecto) {
        while (true) {
            System.out.println("Prioridad (1-Alta, 2-Media, 3-Baja) - Enter para mantener (" + defecto + "): ");
            String opt = sc.nextLine().trim();
            if (opt.isBlank()) return defecto;
            switch (opt) {
                case "1", "Alta", "alta" -> { return "Alta"; }
                case "2", "Media", "media" -> { return "Media"; }
                case "3", "Baja", "baja" -> { return "Baja"; }
                default -> System.out.println("Opcion invalida. Intenta de nuevo.");
            }
        }
    }

    private static String leerCategoriaConDefault(String defecto) {
        while (true) {
            System.out.println("Categoría (1-Trabajo, 2-Estudio, 3-Personal, 4-Otro) - Enter para mantener (" + defecto + "): ");
            String opt = sc.nextLine().trim();
            if (opt.isBlank()) return defecto;
            switch (opt) {
                case "1", "Trabajo", "trabajo" -> { return "Trabajo"; }
                case "2", "Estudio", "estudio" -> { return "Estudio"; }
                case "3", "Personal", "personal" -> { return "Personal"; }
                case "4", "Otro", "otro" -> { return "Otro"; }
                default -> System.out.println("Opcion invalida. Intenta de nuevo.");
            }
        }
    }
}
