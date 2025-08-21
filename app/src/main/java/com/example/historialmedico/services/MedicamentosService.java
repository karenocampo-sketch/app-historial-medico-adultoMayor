package com.example.historialmedico.services;

import com.example.historialmedico.models.Medicamento;
import com.example.historialmedico.utils.FormateoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar medicamentos y alergias a medicamentos
 * Proporciona funcionalidades para agregar, consultar y formatear información de medicamentos
 */
public class MedicamentosService {
    
    private List<Medicamento> medicamentos;
    private List<String> alergiasMedicamentos;
    
    /**
     * Constructor por defecto que inicializa con medicamentos de ejemplo
     */
    public MedicamentosService() {
        this.medicamentos = new ArrayList<>();
        this.alergiasMedicamentos = new ArrayList<>();
        
        // Inicializar con datos de ejemplo
        inicializarDatosEjemplo();
    }
    
    /**
     * Inicializa datos de ejemplo
     */
    private void inicializarDatosEjemplo() {
        // Agregar medicamentos de ejemplo
        medicamentos.add(new Medicamento("Ibuprofeno", "400mg cada 8 horas"));
        medicamentos.add(new Medicamento("Paracetamol", "500mg cada 6 horas"));
        medicamentos.add(new Medicamento("Aspirina", "100mg diaria"));
        
        // Agregar alergias de ejemplo
        alergiasMedicamentos.add("Penicilina");
        alergiasMedicamentos.add("Sulfonamidas");
    }
    
    /**
     * Obtiene la lista de medicamentos activos
     */
    public List<Medicamento> getMedicamentosActivos() {
        List<Medicamento> activos = new ArrayList<>();
        for (Medicamento medicamento : medicamentos) {
            if (medicamento != null && medicamento.isActivo()) {
                activos.add(medicamento);
            }
        }
        return activos;
    }
    
    /**
     * Obtiene todos los medicamentos (activos e inactivos)
     */
    public List<Medicamento> getTodosMedicamentos() {
        return new ArrayList<>(medicamentos);
    }
    
    /**
     * Obtiene la lista de alergias a medicamentos
     */
    public List<String> getAlergiasMedicamentos() {
        return new ArrayList<>(alergiasMedicamentos);
    }
    
    /**
     * Agrega un nuevo medicamento
     */
    public void agregarMedicamento(Medicamento medicamento) {
        if (medicamento != null && !medicamentos.contains(medicamento)) {
            medicamentos.add(medicamento);
        }
    }
    
    /**
     * Agrega una nueva alergia a medicamento
     */
    public void agregarAlergia(String alergia) {
        if (alergia != null && !alergia.trim().isEmpty() && !alergiasMedicamentos.contains(alergia.trim())) {
            alergiasMedicamentos.add(alergia.trim());
        }
    }
    
    /**
     * Elimina un medicamento
     */
    public boolean eliminarMedicamento(Medicamento medicamento) {
        return medicamentos.remove(medicamento);
    }
    
    /**
     * Elimina una alergia
     */
    public boolean eliminarAlergia(String alergia) {
        return alergiasMedicamentos.remove(alergia);
    }
    
    /**
     * Busca medicamentos por nombre
     */
    public List<Medicamento> buscarMedicamentosPorNombre(String nombre) {
        List<Medicamento> encontrados = new ArrayList<>();
        if (nombre == null || nombre.trim().isEmpty()) {
            return encontrados;
        }
        
        String nombreBuscado = nombre.toLowerCase().trim();
        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getNombre().toLowerCase().contains(nombreBuscado)) {
                encontrados.add(medicamento);
            }
        }
        return encontrados;
    }
    
    /**
     * Verifica si existe una alergia específica
     */
    public boolean tieneAlergia(String medicamento) {
        if (medicamento == null || medicamento.trim().isEmpty()) {
            return false;
        }
        
        String medicamentoBuscado = medicamento.toLowerCase().trim();
        for (String alergia : alergiasMedicamentos) {
            if (alergia.toLowerCase().contains(medicamentoBuscado)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obtiene la información de medicamentos formateada para mostrar
     */
    public String getMedicamentosFormateados() {
        StringBuilder sb = new StringBuilder();
        sb.append("💊 MEDICAMENTOS ACTUALES:\n\n");
        
        List<Medicamento> activos = getMedicamentosActivos();
        if (activos.isEmpty()) {
            sb.append("• No hay medicamentos registrados\n");
        } else {
            for (Medicamento medicamento : activos) {
                sb.append("• ").append(medicamento.getNombre())
                  .append(" - ").append(medicamento.getDosificacion()).append("\n");
            }
        }
        
        sb.append("\n⚠️ ALERGIAS A MEDICAMENTOS:\n\n");
        if (alergiasMedicamentos.isEmpty()) {
            sb.append("• No hay alergias registradas\n");
        } else {
            for (String alergia : alergiasMedicamentos) {
                sb.append("• ").append(alergia).append("\n");
            }
        }
        
        return sb.toString().trim();
    }
    
    /**
     * Obtiene un resumen rápido de medicamentos para emergencias
     */
    public String getResumenEmergencia() {
        StringBuilder sb = new StringBuilder();
        sb.append("🚨 INFORMACIÓN MÉDICA DE EMERGENCIA:\n\n");
        
        // Medicamentos activos
        List<Medicamento> activos = getMedicamentosActivos();
        sb.append("Medicamentos actuales: ");
        if (activos.isEmpty()) {
            sb.append("Ninguno\n");
        } else {
            boolean primero = true;
            for (Medicamento med : activos) {
                if (!primero) sb.append(", ");
                sb.append(med.getNombre());
                primero = false;
            }
            sb.append("\n");
        }
        
        // Alergias
        sb.append("Alergias: ");
        if (alergiasMedicamentos.isEmpty()) {
            sb.append("Ninguna conocida\n");
        } else {
            sb.append(String.join(", ", alergiasMedicamentos)).append("\n");
        }
        
        return sb.toString();
    }
}
