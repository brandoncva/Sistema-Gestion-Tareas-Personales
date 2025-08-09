import java.util.ArrayList;

public class GestorTareas {
    private ArrayList<Tarea> listaTareas;

    public GestorTareas() {
        this.listaTareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea t) {
        listaTareas.add(t);
        System.out.println("Tarea agregada correctamente.");
    }

    public void listarTareas() {
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas en la lista.");
            return;
        }
        System.out.println("Lista de tareas:");
        System.out.println("------------------------------------");
        for (int i = 0; i < listaTareas.size(); i++) {
            System.out.println("Tarea #" + (i + 1));
            listaTareas.get(i).mostrarInfo();
        }
    }

    public void listarTareasPorEstado(boolean completadas) {
        boolean encontrado = false;
        for (int i = 0; i < listaTareas.size(); i++) {
            if (listaTareas.get(i).isCompletada() == completadas) {
                if (!encontrado) {
                    System.out.println(completadas ? "Tareas completadas:" : "Tareas pendientes:");
                    System.out.println("------------------------------------");
                }
                encontrado = true;
                System.out.println("Tarea #" + (i + 1));
                listaTareas.get(i).mostrarInfo();
            }
        }
        if (!encontrado) {
            System.out.println(completadas ? "No hay tareas completadas." : "No hay tareas pendientes.");
        }
    }

    public boolean marcarTareaComoCompletada(int indice) {
        if (indice < 1 || indice > listaTareas.size()) return false;
        Tarea t = listaTareas.get(indice - 1);
        t.marcarComoCompletada();
        return true;
    }

    public boolean editarTarea(int indice, String descripcion, String fecha, String prioridad, String categoria) {
        if (indice < 1 || indice > listaTareas.size()) return false;
        Tarea t = listaTareas.get(indice - 1);
        t.editarTarea(descripcion, fecha, prioridad, categoria);
        return true;
    }

    public boolean eliminarTarea(int indice) {
        if (indice < 1 || indice > listaTareas.size()) return false;
        listaTareas.remove(indice - 1);
        return true;
    }

    // Acceso para mostrar datos antes de editar por ejemplo
    public Tarea obtenerTarea(int indice) {
        if (indice < 1 || indice > listaTareas.size()) return null;
        return listaTareas.get(indice - 1);
    }

    public int cantidadTareas() {
        return listaTareas.size();
    }
}
