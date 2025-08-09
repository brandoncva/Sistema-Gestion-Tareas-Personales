public class Tarea {
    private String descripcion;
    private String fechaLimite; // texto libre (ej: "2025-08-20")
    private String prioridad;   // "Alta", "Media", "Baja"
    private String categoria;   // "Trabajo", "Estudio", "Personal", "Otro"
    private boolean completada;

    // Constructor
    public Tarea(String descripcion, String fechaLimite, String prioridad, String categoria) {
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.categoria = categoria;
        this.completada = false;
    }

    // Getters y setters
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(String fechaLimite) { this.fechaLimite = fechaLimite; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }

    // Mostrar información completa de la tarea
    public void mostrarInfo() {
        System.out.println("Descripción : " + descripcion);
        System.out.println("Fecha límite: " + fechaLimite);
        System.out.println("Prioridad   : " + prioridad);
        System.out.println("Categoría   : " + categoria);
        System.out.println("Completada  : " + (completada ? "Sí" : "No"));
        System.out.println("------------------------------------");
    }

    // Marcar como completada
    public void marcarComoCompletada() {
        this.completada = true;
    }

    // Editar tarea: si un parámetro es null o vacío, no lo cambia
    public void editarTarea(String nuevaDescripcion, String nuevaFecha, String nuevaPrioridad, String nuevaCategoria) {
        if (nuevaDescripcion != null && !nuevaDescripcion.isBlank()) this.descripcion = nuevaDescripcion;
        if (nuevaFecha != null && !nuevaFecha.isBlank()) this.fechaLimite = nuevaFecha;
        if (nuevaPrioridad != null && !nuevaPrioridad.isBlank()) this.prioridad = nuevaPrioridad;
        if (nuevaCategoria != null && !nuevaCategoria.isBlank()) this.categoria = nuevaCategoria;
    }
}
