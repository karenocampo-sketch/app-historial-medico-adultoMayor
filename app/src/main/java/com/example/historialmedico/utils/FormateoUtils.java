package com.example.historialmedico.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Clase utilitaria para formatear datos y texto en la aplicación
 * Proporciona métodos para formatear fechas, números y textos específicos del dominio médico
 */
public class FormateoUtils {
    
    // Formatos de fecha
    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private static final SimpleDateFormat FORMATO_FECHA_HORA = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
    private static final SimpleDateFormat FORMATO_HORA = new SimpleDateFormat("HH:mm", Locale.getDefault());
    
    /**
     * Formatea una fecha en formato dd/MM/yyyy
     */
    public static String formatearFecha(Date fecha) {
        return fecha != null ? FORMATO_FECHA.format(fecha) : "";
    }
    
    /**
     * Formatea una fecha y hora en formato dd/MM/yyyy HH:mm
     */
    public static String formatearFechaHora(Date fecha) {
        return fecha != null ? FORMATO_FECHA_HORA.format(fecha) : "";
    }
    
    /**
     * Formatea una hora en formato HH:mm
     */
    public static String formatearHora(Date fecha) {
        return fecha != null ? FORMATO_HORA.format(fecha) : "";
    }
    
    /**
     * Formatea el peso con unidades
     */
    public static String formatearPeso(double peso) {
        return String.format(Locale.getDefault(), "%.1f kg", peso);
    }
    
    /**
     * Formatea la estatura con unidades
     */
    public static String formatearEstatura(double estatura) {
        return String.format(Locale.getDefault(), "%.2f m", estatura);
    }
    
    /**
     * Calcula y formatea el IMC (Índice de Masa Corporal)
     */
    public static String formatearIMC(double peso, double estatura) {
        if (estatura <= 0) return "N/A";
        
        double imc = peso / (estatura * estatura);
        return String.format(Locale.getDefault(), "%.1f", imc);
    }
    
    /**
     * Obtiene la categoría del IMC
     */
    public static String getCategoriaIMC(double peso, double estatura) {
        if (estatura <= 0) return "No disponible";
        
        double imc = peso / (estatura * estatura);
        
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }
    
    /**
     * Formatea una lista de elementos como una lista con viñetas
     */
    public static String formatearListaConVinetas(List<String> elementos) {
        if (elementos == null || elementos.isEmpty()) {
            return "Sin elementos registrados";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String elemento : elementos) {
            sb.append("• ").append(elemento).append("\n");
        }
        
        return sb.toString().trim();
    }
    
    /**
     * Formatea una lista de elementos como una lista numerada
     */
    public static String formatearListaNumerada(List<String> elementos) {
        if (elementos == null || elementos.isEmpty()) {
            return "Sin elementos registrados";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elementos.size(); i++) {
            sb.append(i + 1).append(". ").append(elementos.get(i)).append("\n");
        }
        
        return sb.toString().trim();
    }
    
    /**
     * Capitaliza la primera letra de cada palabra
     */
    public static String capitalizarPalabras(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }
        
        String[] palabras = texto.toLowerCase().split("\\s+");
        StringBuilder resultado = new StringBuilder();
        
        for (int i = 0; i < palabras.length; i++) {
            if (i > 0) {
                resultado.append(" ");
            }
            
            if (!palabras[i].isEmpty()) {
                resultado.append(Character.toUpperCase(palabras[i].charAt(0)));
                if (palabras[i].length() > 1) {
                    resultado.append(palabras[i].substring(1));
                }
            }
        }
        
        return resultado.toString();
    }
    
    /**
     * Formatea un número de teléfono (formato básico)
     */
    public static String formatearTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return "";
        }
        
        // Remover caracteres no numéricos excepto el +
        String limpio = telefono.replaceAll("[^\\d+]", "");
        
        // Formato básico para números de 10 dígitos
        if (limpio.length() == 10 && !limpio.startsWith("+")) {
            return String.format("(%s) %s-%s", 
                limpio.substring(0, 3), 
                limpio.substring(3, 6), 
                limpio.substring(6));
        }
        
        return limpio;
    }
    
    /**
     * Valida si un texto no está vacío o es null
     */
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
    
    /**
     * Trunca un texto si es muy largo, agregando puntos suspensivos
     */
    public static String truncarTexto(String texto, int longitudMaxima) {
        if (!esTextoValido(texto) || texto.length() <= longitudMaxima) {
            return texto != null ? texto : "";
        }
        
        return texto.substring(0, longitudMaxima - 3) + "...";
    }
    
    /**
     * Formatea un texto médico agregando emojis apropiados
     */
    public static String formatearTextoMedico(String categoria, String texto) {
        if (!esTextoValido(texto)) return "";
        
        String emoji;
        switch (categoria.toLowerCase()) {
            case "medicamento":
            case "medicina":
                emoji = "💊";
                break;
            case "alergia":
                emoji = "⚠️";
                break;
            case "condicion":
            case "enfermedad":
                emoji = "🏥";
                break;
            case "cirugia":
            case "operacion":
                emoji = "🏥";
                break;
            case "emergencia":
                emoji = "🚨";
                break;
            case "contacto":
                emoji = "📞";
                break;
            default:
                emoji = "📝";
                break;
        }
        
        return emoji + " " + texto;
    }
    
    /**
     * Obtiene la fecha actual formateada
     */
    public static String obtenerFechaActual() {
        return formatearFecha(new Date());
    }
    
    /**
     * Obtiene la fecha y hora actual formateada
     */
    public static String obtenerFechaHoraActual() {
        return formatearFechaHora(new Date());
    }
}
