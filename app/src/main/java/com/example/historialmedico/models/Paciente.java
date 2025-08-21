package com.example.historialmedico.models;

/**
 * Clase modelo que representa los datos básicos de un paciente adulto mayor
 * Contiene información personal y datos médicos básicos
 */
public class Paciente {
    
    // Atributos de información personal
    private String nombreCompleto;
    private int edad;
    private String direccion;
    private String tipoSangre;
    private double estatura;
    private double peso;
    private String correo;
    private String rutaFoto; // Ruta de la foto del paciente
    
    // Atributos de contactos de emergencia (3 números)
    private String contactoEmergencia1;
    private String telefonoEmergencia1;
    private String contactoEmergencia2;
    private String telefonoEmergencia2;
    private String contactoEmergencia3;
    private String telefonoEmergencia3;
    
    /**
     * Constructor por defecto vacío
     */
    public Paciente() {
        this.nombreCompleto = "";
        this.edad = 0;
        this.direccion = "";
        this.tipoSangre = "";
        this.estatura = 0.0;
        this.peso = 0.0;
        this.correo = "";
        this.rutaFoto = "";
        
        // Contactos de emergencia
        this.contactoEmergencia1 = "";
        this.telefonoEmergencia1 = "";
        this.contactoEmergencia2 = "";
        this.telefonoEmergencia2 = "";
        this.contactoEmergencia3 = "";
        this.telefonoEmergencia3 = "";
    }
    
    /**
     * Constructor completo
     */
    public Paciente(String nombreCompleto, int edad, String direccion, String tipoSangre, 
                   double estatura, double peso, String correo, String rutaFoto,
                   String contacto1, String telefono1, String contacto2, String telefono2,
                   String contacto3, String telefono3) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.direccion = direccion;
        this.tipoSangre = tipoSangre;
        this.estatura = estatura;
        this.peso = peso;
        this.correo = correo;
        this.rutaFoto = rutaFoto;
        this.contactoEmergencia1 = contacto1;
        this.telefonoEmergencia1 = telefono1;
        this.contactoEmergencia2 = contacto2;
        this.telefonoEmergencia2 = telefono2;
        this.contactoEmergencia3 = contacto3;
        this.telefonoEmergencia3 = telefono3;
    }
    
    // Getters y Setters
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getTipoSangre() {
        return tipoSangre;
    }
    
    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }
    
    public double getEstatura() {
        return estatura;
    }
    
    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }
    
    public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getRutaFoto() {
        return rutaFoto;
    }
    
    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
    
    // Getters y Setters para contactos de emergencia
    public String getContactoEmergencia1() {
        return contactoEmergencia1;
    }
    
    public void setContactoEmergencia1(String contactoEmergencia1) {
        this.contactoEmergencia1 = contactoEmergencia1;
    }
    
    public String getTelefonoEmergencia1() {
        return telefonoEmergencia1;
    }
    
    public void setTelefonoEmergencia1(String telefonoEmergencia1) {
        this.telefonoEmergencia1 = telefonoEmergencia1;
    }
    
    public String getContactoEmergencia2() {
        return contactoEmergencia2;
    }
    
    public void setContactoEmergencia2(String contactoEmergencia2) {
        this.contactoEmergencia2 = contactoEmergencia2;
    }
    
    public String getTelefonoEmergencia2() {
        return telefonoEmergencia2;
    }
    
    public void setTelefonoEmergencia2(String telefonoEmergencia2) {
        this.telefonoEmergencia2 = telefonoEmergencia2;
    }
    
    public String getContactoEmergencia3() {
        return contactoEmergencia3;
    }
    
    public void setContactoEmergencia3(String contactoEmergencia3) {
        this.contactoEmergencia3 = contactoEmergencia3;
    }
    
    public String getTelefonoEmergencia3() {
        return telefonoEmergencia3;
    }
    
    public void setTelefonoEmergencia3(String telefonoEmergencia3) {
        this.telefonoEmergencia3 = telefonoEmergencia3;
    }
    
    /**
     * Método para obtener información básica del paciente formateada
     */
    public String getInfoBasica() {
        return "👤 Nombre: " + nombreCompleto + "\n" +
               "🎂 Edad: " + edad + " años\n" +
               "🏠 Dirección: " + direccion + "\n" +
               "🩸 Tipo de Sangre: " + tipoSangre + "\n" +
               "📏 Estatura: " + estatura + " m\n" +
               "⚖️ Peso: " + peso + " kg\n" +
               "📧 Correo: " + correo;
    }
    
    /**
     * Obtiene la lista de contactos de emergencia
     */
    public String[] getContactosEmergencia() {
        return new String[]{
            contactoEmergencia1 + " - " + telefonoEmergencia1,
            contactoEmergencia2 + " - " + telefonoEmergencia2,
            contactoEmergencia3 + " - " + telefonoEmergencia3
        };
    }
    
    /**
     * Obtiene solo los teléfonos de emergencia
     */
    public String[] getTelefonosEmergencia() {
        return new String[]{telefonoEmergencia1, telefonoEmergencia2, telefonoEmergencia3};
    }
    
    /**
     * Método para calcular el IMC (Índice de Masa Corporal)
     */
    public double calcularIMC() {
        return peso / (estatura * estatura);
    }
    
    /**
     * Verifica si el paciente tiene foto configurada
     */
    public boolean tieneFoto() {
        return rutaFoto != null && !rutaFoto.trim().isEmpty();
    }
    
    /**
     * Valida si el correo tiene formato básico válido
     */
    public boolean esCorreoValido() {
        return correo != null && correo.contains("@") && correo.contains(".");
    }
    
    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombreCompleto + '\'' +
                ", edad=" + edad +
                ", tipoSangre='" + tipoSangre + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
