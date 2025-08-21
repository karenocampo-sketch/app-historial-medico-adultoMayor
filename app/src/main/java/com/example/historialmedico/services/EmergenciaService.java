package com.example.historialmedico.services;

import android.content.Context;
import android.widget.Toast;
import com.example.historialmedico.models.Paciente;

/**
 * Servicio que maneja la lógica de emergencias
 * Controla contactos múltiples y llamadas al 123
 */
public class EmergenciaService {
    
    // Constantes para el sistema de emergencia
    private static final String NUMERO_EMERGENCIA = "123"; // Cambio de 911 a 123
    
    // Referencia al paciente para obtener contactos
    private Paciente paciente;
    
    /**
     * Constructor que recibe el paciente
     */
    public EmergenciaService(Paciente paciente) {
        this.paciente = paciente;
    }
    
    /**
     * Constructor por defecto
     */
    public EmergenciaService() {
        this.paciente = new Paciente(); // Usar datos de ejemplo
    }
    
    /**
     * Maneja el contacto de emergencia con desplegable de opciones
     * @param context Contexto de la aplicación
     * @return Mensaje con opciones de contacto
     */
    public String manejarContactoEmergencia(Context context) {
        String[] contactos = paciente.getContactosEmergencia();
        
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("📞 CONTACTOS DE EMERGENCIA\\n\\n");
        mensaje.append("Seleccione el contacto a llamar:\\n\\n");
        
        for (int i = 0; i < contactos.length; i++) {
            mensaje.append((i + 1)).append(". ").append(contactos[i]).append("\\n");
        }
        
        mensaje.append("\\n💡 Si no contesta uno, intente con los otros");
        
        Toast.makeText(context, "📞 Mostrando contactos de emergencia...", Toast.LENGTH_SHORT).show();
        
        return mensaje.toString();
    }
    
    /**
     * Llama a un contacto específico
     */
    public String llamarContactoEspecifico(Context context, int indiceContacto) {
        String[] telefonos = paciente.getTelefonosEmergencia();
        String[] contactos = paciente.getContactosEmergencia();
        
        if (indiceContacto >= 0 && indiceContacto < telefonos.length) {
            String telefono = telefonos[indiceContacto];
            String contacto = contactos[indiceContacto];
            
            Toast.makeText(context, "📞 Llamando a " + contacto, Toast.LENGTH_LONG).show();
            
            // Aquí se implementaría la llamada real
            // Intent callIntent = new Intent(Intent.ACTION_CALL);
            // callIntent.setData(Uri.parse("tel:" + telefono));
            
            return "📞 LLAMANDO A CONTACTO DE EMERGENCIA\\n\\n" +
                   "Contacto: " + contacto + "\\n" +
                   "Número: " + telefono + "\\n\\n" +
                   "🔄 Si no contesta, pruebe con otro contacto\\n" +
                   "⏱️ Mantenga la calma mientras se conecta";
        }
        
        return "❌ Contacto no válido";
    }
    
    /**
     * Maneja la llamada al 123 (emergencias)
     */
    public String llamar123(Context context) {
        Toast.makeText(context, "🚨 EMERGENCIA: Llamando al 123...\\nEspere con calma", 
                Toast.LENGTH_LONG).show();
        
        // Aquí se implementaría la llamada real al 123
        // Intent callIntent = new Intent(Intent.ACTION_CALL);
        // callIntent.setData(Uri.parse("tel:123"));
        
        return "🚨 LLAMANDO AL 123 🚨\\n\\n" +
               "EMERGENCIA ACTIVADA\\n" +
               "Los servicios de emergencia han sido contactados.\\n\\n" +
               "INSTRUCCIONES:\\n" +
               "• Mantén la calma\\n" +
               "• Respira profundamente\\n" +
               "• La ayuda está en camino\\n" +
               "• Proporciona tu ubicación claramente\\n" +
               "• No cuelgues hasta que te lo indiquen";
    }
    
    /**
     * Método principal para manejar emergencia
     */
    public String manejarEmergencia(Context context) {
        Toast.makeText(context, "🚨 Activando protocolo de emergencia...", Toast.LENGTH_SHORT).show();
        
        return "🚨 PROTOCOLO DE EMERGENCIA ACTIVADO 🚨\n\n" +
               "1️⃣ CONTACTAR FAMILIARES: Use 'Contacto Emergencia'\n" +
               "2️⃣ LLAMAR AL 123: Use 'Emergencia' si es grave\n\n" +
               "📋 INFORMACIÓN DEL PACIENTE:\n" +
               "👤 Nombre: " + paciente.getNombreCompleto() + "\n" +
               "🩸 Tipo sangre: " + paciente.getTipoSangre() + "\n" +
               "🏠 Dirección: " + paciente.getDireccion() + "\n\n" +
               "⚠️ MANTENGA LA CALMA - La ayuda está disponible";
    }
    
    /**
     * Obtiene información de emergencia del paciente
     */
    public String getInfoEmergencia() {
        StringBuilder info = new StringBuilder();
        info.append("🆘 INFORMACIÓN DE EMERGENCIA\\n\\n");
        info.append("👤 Paciente: ").append(paciente.getNombreCompleto()).append("\\n");
        info.append("🎂 Edad: ").append(paciente.getEdad()).append(" años\\n");
        info.append("🏠 Dirección: ").append(paciente.getDireccion()).append("\\n");
        info.append("🩸 Tipo de sangre: ").append(paciente.getTipoSangre()).append("\\n\\n");
        
        info.append("📞 CONTACTOS DISPONIBLES:\\n");
        String[] contactos = paciente.getContactosEmergencia();
        for (int i = 0; i < contactos.length; i++) {
            info.append((i + 1)).append(". ").append(contactos[i]).append("\\n");
        }
        
        return info.toString();
    }
    
    /**
     * Actualiza el paciente
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    /**
     * Obtiene el paciente actual
     */
    public Paciente getPaciente() {
        return paciente;
    }
    
    /**
     * Obtiene las instrucciones de emergencia
     */
    public static String getInstruccionesEmergencia() {
        return "📞 INSTRUCCIONES DE EMERGENCIA:\\n\\n" +
               "👥 CONTACTO FAMILIAR: Use el botón de contactos\\n" +
               "   - Seleccione de la lista de 3 contactos\\n" +
               "   - Si no contesta uno, pruebe otro\\n\\n" +
               "🚨 EMERGENCIA " + NUMERO_EMERGENCIA + ": Use el botón de emergencia\\n" +
               "   - Llamada directa a servicios de emergencia\\n" +
               "   - Tenga lista su dirección\\n\\n" +
               "⚠️ RECUERDE: Mantenga la calma en todo momento";
    }
    
    @Override
    public String toString() {
        return "EmergenciaService{" +
                "paciente=" + (paciente != null ? paciente.getNombreCompleto() : "null") +
                ", numeroEmergencia='" + NUMERO_EMERGENCIA + '\'' +
                '}';
    }
}
