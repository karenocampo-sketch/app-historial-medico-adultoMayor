package com.example.historialmedico.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase modelo que representa el historial médico de un paciente
 * Separado en categorías específicas: alergias, cirugías, enfermedades
 */
public class HistorialMedico {
    
    // Atributos del historial médico organizados por categorías
    private List<String> alergias;
    private List<String> cirugias;
    private List<String> enfermedades;
    private List<String> antecedentesRelevantes;
    
    /**
     * Constructor por defecto vacío
     */
    public HistorialMedico() {
        this.alergias = new ArrayList<>();
        this.cirugias = new ArrayList<>();
        this.enfermedades = new ArrayList<>();
        this.antecedentesRelevantes = new ArrayList<>();
    }
    
    /**
     * Constructor con listas personalizadas
     */
    public HistorialMedico(List<String> alergias, List<String> cirugias, 
                          List<String> enfermedades, List<String> antecedentesRelevantes) {
        this.alergias = alergias != null ? alergias : new ArrayList<>();
        this.cirugias = cirugias != null ? cirugias : new ArrayList<>();
        this.enfermedades = enfermedades != null ? enfermedades : new ArrayList<>();
        this.antecedentesRelevantes = antecedentesRelevantes != null ? antecedentesRelevantes : new ArrayList<>();
    }
    
    // Getters y Setters
    public List<String> getAlergias() {
        return alergias;
    }
    
    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }
    
    public List<String> getCirugias() {
        return cirugias;
    }
    
    public void setCirugias(List<String> cirugias) {
        this.cirugias = cirugias;
    }
    
    public List<String> getEnfermedades() {
        return enfermedades;
    }
    
    public void setEnfermedades(List<String> enfermedades) {
        this.enfermedades = enfermedades;
    }
    
    public List<String> getAntecedentesRelevantes() {
        return antecedentesRelevantes;
    }
    
    public void setAntecedentesRelevantes(List<String> antecedentesRelevantes) {
        this.antecedentesRelevantes = antecedentesRelevantes;
    }
    
    // Métodos para agregar elementos
    public void agregarAlergia(String alergia) {
        if (alergia != null && !alergia.trim().isEmpty()) {
            alergias.add(alergia.trim());
        }
    }
    
    public void agregarCirugia(String cirugia) {
        if (cirugia != null && !cirugia.trim().isEmpty()) {
            cirugias.add(cirugia.trim());
        }
    }
    
    public void agregarEnfermedad(String enfermedad) {
        if (enfermedad != null && !enfermedad.trim().isEmpty()) {
            enfermedades.add(enfermedad.trim());
        }
    }
    
    public void agregarAntecedente(String antecedente) {
        if (antecedente != null && !antecedente.trim().isEmpty()) {
            antecedentesRelevantes.add(antecedente.trim());
        }
    }
    
    /**
     * Método para obtener el historial médico completo formateado
     */
    public String getHistorialFormateado() {
        StringBuilder historial = new StringBuilder();
        historial.append("📋 HISTORIAL MÉDICO\\n\\n");
        
        // Enfermedades
        if (!enfermedades.isEmpty()) {
            historial.append("🏥 ENFERMEDADES:\\n");
            for (String enfermedad : enfermedades) {
                historial.append("• ").append(enfermedad).append("\\n");
            }
            historial.append("\\n");
        }
        
        // Cirugías
        if (!cirugias.isEmpty()) {
            historial.append("🔪 CIRUGÍAS:\\n");
            for (String cirugia : cirugias) {
                historial.append("• ").append(cirugia).append("\\n");
            }
            historial.append("\\n");
        }
        
        // Alergias
        if (!alergias.isEmpty()) {
            historial.append("⚠️ ALERGIAS:\\n");
            for (String alergia : alergias) {
                historial.append("• ").append(alergia).append("\\n");
            }
        }
        
        return historial.toString().trim();
    }
    
    /**
     * Obtiene solo las alergias formateadas
     */
    public String getAlergiasFormateadas() {
        if (alergias.isEmpty()) {
            return "Sin alergias registradas";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("⚠️ ALERGIAS:\\n\\n");
        for (String alergia : alergias) {
            sb.append("• ").append(alergia).append("\\n");
        }
        return sb.toString().trim();
    }
    
    /**
     * Obtiene solo las cirugías formateadas
     */
    public String getCirugiasFormateadas() {
        if (cirugias.isEmpty()) {
            return "Sin cirugías registradas";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("🔪 CIRUGÍAS:\\n\\n");
        for (String cirugia : cirugias) {
            sb.append("• ").append(cirugia).append("\\n");
        }
        return sb.toString().trim();
    }
    
    /**
     * Obtiene solo las enfermedades formateadas
     */
    public String getEnfermedadesFormateadas() {
        if (enfermedades.isEmpty()) {
            return "Sin enfermedades registradas";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("🏥 ENFERMEDADES:\\n\\n");
        for (String enfermedad : enfermedades) {
            sb.append("• ").append(enfermedad).append("\\n");
        }
        return sb.toString().trim();
    }
    
    /**
     * Método para verificar si el paciente tiene alguna alergia específica
     */
    public boolean tieneAlergia(String alergia) {
        return alergias.contains(alergia);
    }
    
    /**
     * Método para verificar si el paciente tiene alguna enfermedad específica
     */
    public boolean tieneEnfermedad(String enfermedad) {
        return enfermedades.contains(enfermedad);
    }
    
    /**
     * Obtiene el resumen de cantidades
     */
    public String getResumen() {
        return String.format("Enfermedades: %d | Cirugías: %d | Alergias: %d", 
                           enfermedades.size(), cirugias.size(), alergias.size());
    }
    
    @Override
    public String toString() {
        return "HistorialMedico{" +
                "enfermedades=" + enfermedades.size() +
                ", cirugias=" + cirugias.size() +
                ", alergias=" + alergias.size() +
                '}';
    }
}
