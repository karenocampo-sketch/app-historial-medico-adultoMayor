package com.example.historialmedico.models;

/**
 * Clase modelo que representa un medicamento con sus propiedades
 * Incluye nombre, dosis, horarios y información relevante
 */
public class Medicamento {
    
    // Atributos del medicamento
    private String nombre;
    private String dosis;
    private String frecuencia;
    private String horario;
    private String indicaciones;
    private boolean esActivo;
    
    /**
     * Constructor por defecto
     */
    public Medicamento() {
        this.esActivo = true;
    }
    
    /**
     * Constructor simple con nombre y dosis
     */
    public Medicamento(String nombre, String dosis) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.esActivo = true;
    }
    
    /**
     * Constructor completo
     */
    public Medicamento(String nombre, String dosis, String frecuencia, String horario, String indicaciones) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.horario = horario;
        this.indicaciones = indicaciones;
        this.esActivo = true;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDosis() {
        return dosis;
    }
    
    public void setDosis(String dosis) {
        this.dosis = dosis;
    }
    
    public String getFrecuencia() {
        return frecuencia;
    }
    
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    public String getHorario() {
        return horario;
    }
    
    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    public String getIndicaciones() {
        return indicaciones;
    }
    
    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }
    
    public boolean isEsActivo() {
        return esActivo;
    }
    
    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }
    
    /**
     * Método alias para isEsActivo() 
     */
    public boolean isActivo() {
        return esActivo;
    }
    
    /**
     * Método para obtener la dosificación (combinación de dosis y frecuencia)
     */
    public String getDosificacion() {
        if (dosis != null && !dosis.trim().isEmpty()) {
            return dosis;
        }
        return "No especificada";
    }
    
    /**
     * Método para obtener la información del medicamento formateada
     */
    public String getInfoFormateada() {
        StringBuilder info = new StringBuilder();
        info.append("💊 ").append(nombre);
        
        if (dosis != null && !dosis.isEmpty()) {
            info.append(" ").append(dosis);
        }
        
        if (horario != null && !horario.isEmpty()) {
            info.append(" (").append(horario).append(")");
        }
        
        return info.toString();
    }
    
    /**
     * Método para activar el medicamento
     */
    public void activar() {
        this.esActivo = true;
    }
    
    /**
     * Método para desactivar el medicamento
     */
    public void desactivar() {
        this.esActivo = false;
    }
    
    @Override
    public String toString() {
        return "Medicamento{" +
                "nombre='" + nombre + '\'' +
                ", dosis='" + dosis + '\'' +
                ", horario='" + horario + '\'' +
                ", esActivo=" + esActivo +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Medicamento that = (Medicamento) o;
        
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        return dosis != null ? dosis.equals(that.dosis) : that.dosis == null;
    }
    
    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (dosis != null ? dosis.hashCode() : 0);
        return result;
    }
}
