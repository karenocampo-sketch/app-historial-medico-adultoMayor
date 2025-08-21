package com.example.historialmedico.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.historialmedico.R;

/**
 * Actividad principal de la aplicación
 * Gestiona la interfaz de usuario y coordina los diferentes servicios
 */
public class MainActivity extends AppCompatActivity {

    // Vistas de la UI
    private ImageView imgFotoPaciente;
    private TextView tvNombrePaciente;
    private TextView tvHistorialInfo;
    private TextView tvMedicamentosInfo;
    private Button btnHistorial;
    private Button btnMedicamentos;
    private Button btnContactoEmergencia;
    private Button btnLlamar123;
    private Button btnEditarPerfil;
    private Button btnBorrarHistorial;
    private Button btnSalir;
    
    // Elementos de contactos de emergencia
    private LinearLayout layoutContactosEmergencia;
    private LinearLayout layoutContacto1, layoutContacto2, layoutContacto3;
    private TextView tvContacto1, tvTelefono1, tvContacto2, tvTelefono2, tvContacto3, tvTelefono3;
    private Button btnLlamarContacto1, btnLlamarContacto2, btnLlamarContacto3;
    
    // Variables para controlar el estado de los botones toggle
    private boolean historialVisible = false;
    private boolean medicamentosVisible = false;
    private boolean contactosVisible = false;
    
    // Datos del paciente cargados desde SharedPreferences
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nuevo);
        
        // Inicializar SharedPreferences
        prefs = getSharedPreferences("HistorialMedicoApp", MODE_PRIVATE);
        
        // Inicializar las vistas
        inicializarVistas();
        
        // Configurar los listeners
        configurarListeners();
        
        // Verificar si hay datos del paciente, si no los hay ir al registro
        if (!hayDatosPaciente()) {
            irARegistro();
            return;
        }
        
        // Cargar información del paciente
        cargarInformacionPaciente();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        // Recargar información cuando la actividad regrese al primer plano
        if (hayDatosPaciente()) {
            cargarInformacionPaciente();
        }
    }
    
    /**
     * Inicializa las referencias a las vistas de la UI
     */
    private void inicializarVistas() {
        imgFotoPaciente = findViewById(R.id.imgFotoPaciente);
        tvNombrePaciente = findViewById(R.id.tvNombrePaciente);
        tvHistorialInfo = findViewById(R.id.tvHistorialInfo);
        tvMedicamentosInfo = findViewById(R.id.tvMedicamentosInfo);
        btnHistorial = findViewById(R.id.btnHistorial);
        btnMedicamentos = findViewById(R.id.btnMedicamentos);
        btnContactoEmergencia = findViewById(R.id.btnContactoEmergencia);
        btnLlamar123 = findViewById(R.id.btnLlamar123);
        btnEditarPerfil = findViewById(R.id.btnEditarPerfil);
        btnBorrarHistorial = findViewById(R.id.btnBorrarHistorial);
        btnSalir = findViewById(R.id.btnSalir);
        
        // Elementos de contactos de emergencia
        layoutContactosEmergencia = findViewById(R.id.layoutContactosEmergencia);
        layoutContacto1 = findViewById(R.id.layoutContacto1);
        layoutContacto2 = findViewById(R.id.layoutContacto2);
        layoutContacto3 = findViewById(R.id.layoutContacto3);
        
        tvContacto1 = findViewById(R.id.tvContacto1);
        tvTelefono1 = findViewById(R.id.tvTelefono1);
        tvContacto2 = findViewById(R.id.tvContacto2);
        tvTelefono2 = findViewById(R.id.tvTelefono2);
        tvContacto3 = findViewById(R.id.tvContacto3);
        tvTelefono3 = findViewById(R.id.tvTelefono3);
        
        btnLlamarContacto1 = findViewById(R.id.btnLlamarContacto1);
        btnLlamarContacto2 = findViewById(R.id.btnLlamarContacto2);
        btnLlamarContacto3 = findViewById(R.id.btnLlamarContacto3);
    }
    
    /**
     * Configura los listeners para los botones
     */
    private void configurarListeners() {
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarHistorial();
            }
        });
        
        btnMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMedicamentos();
            }
        });
        
        btnContactoEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarContactosEmergencia();
            }
        });
        
        btnLlamar123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamar123();
            }
        });
        
        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarPerfil();
            }
        });
        
        btnBorrarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarHistorialMedico();
            }
        });
        
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirDeApp();
            }
        });
        
        // Listeners para botones de llamar contactos individuales
        btnLlamarContacto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono = prefs.getString("telefono1", "");
                llamarContactoDirecto("Contacto 1", telefono);
            }
        });
        
        btnLlamarContacto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono = prefs.getString("telefono2", "");
                llamarContactoDirecto("Contacto 2", telefono);
            }
        });
        
        btnLlamarContacto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono = prefs.getString("telefono3", "");
                llamarContactoDirecto("Contacto 3", telefono);
            }
        });
    }
    
    /**
     * Verifica si hay datos del paciente guardados
     */
    private boolean hayDatosPaciente() {
        String nombre = prefs.getString("nombre_completo", "");
        return !nombre.isEmpty();
    }
    
    /**
     * Redirige al formulario de registro si no hay datos
     */
    private void irARegistro() {
        Intent intent = new Intent(this, RegistroPacienteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    
    /**
     * Carga la información del paciente desde SharedPreferences
     */
    private void cargarInformacionPaciente() {
        String nombreCompleto = prefs.getString("nombre_completo", "Sin nombre");
        tvNombrePaciente.setText(nombreCompleto);
        
        // Cargar imagen del paciente
        cargarImagenPaciente();
    }
    
    /**
     * Carga la imagen del paciente desde la ruta guardada en SharedPreferences
     */
    private void cargarImagenPaciente() {
        String rutaFoto = prefs.getString("foto_ruta", "");
        
        if (!rutaFoto.isEmpty()) {
            try {
                Uri fotoUri = Uri.parse(rutaFoto);
                imgFotoPaciente.setImageURI(fotoUri);
            } catch (SecurityException e) {
                // Si hay error de permisos, mostrar imagen por defecto y limpiar la ruta
                imgFotoPaciente.setImageResource(R.drawable.ic_person_placeholder);
                
                // Limpiar la ruta incorrecta de las preferencias
                SharedPreferences.Editor editor = prefs.edit();
                editor.remove("foto_ruta");
                editor.apply();
            } catch (Exception e) {
                // Si hay cualquier otro error cargando la foto, mostrar imagen por defecto
                imgFotoPaciente.setImageResource(R.drawable.ic_person_placeholder);
                
                // Opcional: limpiar la ruta incorrecta de las preferencias
                SharedPreferences.Editor editor = prefs.edit();
                editor.remove("foto_ruta");
                editor.apply();
            }
        } else {
            // Si no hay ruta de foto, mostrar imagen por defecto
            imgFotoPaciente.setImageResource(R.drawable.ic_person_placeholder);
        }
    }
    
    /**
     * Toggle para mostrar/ocultar el historial médico del paciente
     */
    private void mostrarHistorial() {
        if (historialVisible) {
            // Ocultar historial
            tvHistorialInfo.setVisibility(View.GONE);
            btnHistorial.setText("🩺 Ver Historial Médico");
            historialVisible = false;
        } else {
            // Mostrar historial
            StringBuilder historial = new StringBuilder();
            historial.append("                    🩺 HISTORIAL MÉDICO\n\n");
            
            String alergias = prefs.getString("alergias", "");
            String cirugias = prefs.getString("cirugias", "");
            String enfermedades = prefs.getString("enfermedades", "");
            
            historial.append("🤧 ALERGIAS:\n");
            if (!alergias.isEmpty()) {
                historial.append("• ").append(alergias).append("\n");
            } else {
                historial.append("• No reportadas\n");
            }
            
            historial.append("\n🏥 CIRUGÍAS ANTERIORES:\n");
            if (!cirugias.isEmpty()) {
                historial.append("• ").append(cirugias).append("\n");
            } else {
                historial.append("• Ninguna registrada\n");
            }
            
            historial.append("\n🫀 ENFERMEDADES:\n");
            if (!enfermedades.isEmpty()) {
                historial.append("• ").append(enfermedades).append("\n");
            } else {
                historial.append("• No reportadas\n");
            }
            
            tvHistorialInfo.setText(historial.toString());
            tvHistorialInfo.setVisibility(View.VISIBLE);
            btnHistorial.setText("🩺 Ocultar Historial Médico");
            historialVisible = true;
        }
        
        // Ocultar medicamentos si están visibles
        if (medicamentosVisible) {
            tvMedicamentosInfo.setVisibility(View.GONE);
            btnMedicamentos.setText("💊 Ver Medicamentos");
            medicamentosVisible = false;
        }
    }
    
    /**
     * Toggle para mostrar/ocultar los medicamentos del paciente
     */
    private void mostrarMedicamentos() {
        if (medicamentosVisible) {
            // Ocultar medicamentos
            tvMedicamentosInfo.setVisibility(View.GONE);
            btnMedicamentos.setText("💊 Ver Medicamentos");
            medicamentosVisible = false;
        } else {
            // Mostrar medicamentos
            StringBuilder medicamentos = new StringBuilder();
            medicamentos.append("                         💊 MEDICAMENTOS\n\n");
            
            String medicamentosText = prefs.getString("medicamentos", "");
            String alergiasMedicamentos = prefs.getString("alergias_medicamentos", "");
            
            medicamentos.append("💊 MEDICAMENTOS ACTUALES:\n");
            if (!medicamentosText.isEmpty()) {
                medicamentos.append("• ").append(medicamentosText).append("\n");
            } else {
                medicamentos.append("• No reportados\n");
            }
            
            medicamentos.append("\n⚠️ ALERGIAS A MEDICAMENTOS:\n");
            if (!alergiasMedicamentos.isEmpty()) {
                medicamentos.append("• ").append(alergiasMedicamentos).append("\n");
            } else {
                medicamentos.append("• No reportadas\n");
            }
            
            tvMedicamentosInfo.setText(medicamentos.toString());
            tvMedicamentosInfo.setVisibility(View.VISIBLE);
            btnMedicamentos.setText("💊 Ocultar Medicamentos");
            medicamentosVisible = true;
        }
        
        // Ocultar historial si está visible
        if (historialVisible) {
            tvHistorialInfo.setVisibility(View.GONE);
            btnHistorial.setText("🩺 Ver Historial Médico");
            historialVisible = false;
        }
    }
    
    /**
     * Toggle para mostrar/ocultar contactos de emergencia con botones individuales
     */
    private void mostrarContactosEmergencia() {
        if (contactosVisible) {
            // Ocultar contactos
            layoutContactosEmergencia.setVisibility(View.GONE);
            btnContactoEmergencia.setText("📞 Ver Contactos de Emergencia");
            contactosVisible = false;
        } else {
            // Mostrar contactos
            cargarContactosEmergencia();
            layoutContactosEmergencia.setVisibility(View.VISIBLE);
            btnContactoEmergencia.setText("📞 Ocultar Contactos");
            contactosVisible = true;
        }
        
        // Ocultar otras secciones si están visibles
        if (historialVisible) {
            tvHistorialInfo.setVisibility(View.GONE);
            btnHistorial.setText("🩺 Ver Historial Médico");
            historialVisible = false;
        }
        
        if (medicamentosVisible) {
            tvMedicamentosInfo.setVisibility(View.GONE);
            btnMedicamentos.setText("💊 Ver Medicamentos");
            medicamentosVisible = false;
        }
    }
    
    /**
     * Carga los contactos de emergencia en el layout
     */
    private void cargarContactosEmergencia() {
        String contacto1 = prefs.getString("contacto1", "");
        String telefono1 = prefs.getString("telefono1", "");
        String contacto2 = prefs.getString("contacto2", "");
        String telefono2 = prefs.getString("telefono2", "");
        String contacto3 = prefs.getString("contacto3", "");
        String telefono3 = prefs.getString("telefono3", "");
        
        // Configurar contacto 1
        if (!contacto1.isEmpty() && !telefono1.isEmpty()) {
            tvContacto1.setText(contacto1);
            tvTelefono1.setText(telefono1);
            layoutContacto1.setVisibility(View.VISIBLE);
        } else {
            layoutContacto1.setVisibility(View.GONE);
        }
        
        // Configurar contacto 2
        if (!contacto2.isEmpty() && !telefono2.isEmpty()) {
            tvContacto2.setText(contacto2);
            tvTelefono2.setText(telefono2);
            layoutContacto2.setVisibility(View.VISIBLE);
        } else {
            layoutContacto2.setVisibility(View.GONE);
        }
        
        // Configurar contacto 3
        if (!contacto3.isEmpty() && !telefono3.isEmpty()) {
            tvContacto3.setText(contacto3);
            tvTelefono3.setText(telefono3);
            layoutContacto3.setVisibility(View.VISIBLE);
        } else {
            layoutContacto3.setVisibility(View.GONE);
        }
    }
    
    /**
     * Llama directamente a un contacto de emergencia específico
     */
    private void llamarContactoDirecto(String nombreContacto, String telefono) {
        if (telefono.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("❌ Error");
            builder.setMessage("No hay teléfono registrado para " + nombreContacto);
            builder.setPositiveButton("OK", null);
            builder.show();
            return;
        }
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar Llamada");
        builder.setMessage("¿Llamar a " + nombreContacto + "?\n\n" + telefono);
        builder.setIcon(android.R.drawable.ic_menu_call);
        
        builder.setPositiveButton("SÍ, LLAMAR", (dialog, which) -> {
            // Simular llamada
            AlertDialog.Builder successBuilder = new AlertDialog.Builder(this);
            successBuilder.setTitle("📞 LLAMANDO...");
            successBuilder.setMessage("Conectando con " + nombreContacto + "\n\n" +
                                    telefono + "\n\n" +
                                    "Mantenga la calma mientras se conecta\n" +
                                    "Si no contesta, pruebe con otro contacto");
            successBuilder.setPositiveButton("OK", null);
            successBuilder.show();
            
            // Aquí se implementaría la llamada real
            // Intent callIntent = new Intent(Intent.ACTION_CALL);
            // callIntent.setData(Uri.parse("tel:" + telefono));
            // startActivity(callIntent);
        });
        
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
    
    /**
     * Maneja la llamada al 123 (emergencias)
     */
    private void llamar123() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("🚨 CONFIRMACIÓN DE EMERGENCIA");
        builder.setMessage("¿Está seguro que desea llamar al 123?\n\nEsto es para EMERGENCIAS REALES únicamente.");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        
        builder.setPositiveButton("SÍ, LLAMAR", (dialog, which) -> {
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("🚨 LLAMANDO AL 123 🚨\n\n");
            mensaje.append("EMERGENCIA ACTIVADA\n");
            mensaje.append("Los servicios de emergencia han sido contactados.\n\n");
            
            // Agregar información del paciente para emergencias
            String nombre = prefs.getString("nombre_completo", "No especificado");
            String direccion = prefs.getString("direccion", "No especificada");
            String tipoSangre = prefs.getString("tipo_sangre", "No especificado");
            int edad = prefs.getInt("edad", 0);
            
            mensaje.append("📋 INFORMACIÓN DEL PACIENTE:\n");
            mensaje.append("👤 Nombre: ").append(nombre).append("\n");
            mensaje.append("🎂 Edad: ").append(edad).append(" años\n");
            mensaje.append("🩸 Tipo sangre: ").append(tipoSangre).append("\n");
            mensaje.append("🏠 Dirección: ").append(direccion).append("\n\n");
            
            mensaje.append("INSTRUCCIONES:\n");
            mensaje.append("• Mantén la calma\n");
            mensaje.append("• Respira profundamente\n");
            mensaje.append("• La ayuda está en camino\n");
            mensaje.append("• Proporciona tu ubicación claramente\n");
            mensaje.append("• No cuelgues hasta que te lo indiquen");
            
            // Mostrar mensaje de emergencia en un diálogo
            AlertDialog.Builder emergencyBuilder = new AlertDialog.Builder(this);
            emergencyBuilder.setTitle("🚑 EMERGENCIA 123");
            emergencyBuilder.setMessage(mensaje.toString());
            emergencyBuilder.setPositiveButton("OK", null);
            emergencyBuilder.show();
            
            // Aquí se implementaría la llamada real al 123
            // Intent callIntent = new Intent(Intent.ACTION_CALL);
            // callIntent.setData(Uri.parse("tel:123"));
            // startActivity(callIntent);
        });
        
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
    
    /**
     * Abre el formulario de registro para editar el perfil
     */
    private void editarPerfil() {
        Intent intent = new Intent(this, RegistroPacienteActivity.class);
        startActivity(intent);
    }
    
    /**
     * Borra toda la información del paciente y redirige al formulario de registro
     */
    private void borrarHistorialMedico() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("🗑️ Borrar Todos los Datos del Paciente");
        builder.setMessage("¿Está seguro de que desea eliminar TODA la información del paciente actual?\n\n" +
                          "Se eliminarán TODOS los datos:\n" +
                          "• Información personal completa\n" +
                          "• Historial médico\n" +
                          "• Medicamentos\n" +
                          "• Contactos de emergencia\n" +
                          "• Toda la configuración\n\n" +
                          "⚠️ Esta acción NO se puede deshacer.\n" +
                          "Será redirigido al formulario para registrar un nuevo paciente.");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        
        builder.setPositiveButton("SÍ, BORRAR TODO", (dialog, which) -> {
            // Eliminar TODOS los datos del paciente
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); // Esto borra todo
            editor.apply();
            
            // Mostrar mensaje de confirmación breve
            AlertDialog.Builder successBuilder = new AlertDialog.Builder(this);
            successBuilder.setTitle("✅ Datos Eliminados");
            successBuilder.setMessage("Toda la información del paciente ha sido eliminada.\n\n" +
                                    "Será redirigido al formulario de registro para ingresar un nuevo paciente.");
            successBuilder.setPositiveButton("CONTINUAR", (dialogSuccess, whichSuccess) -> {
                // Redirigir al formulario de registro
                Intent intent = new Intent(MainActivity.this, RegistroPacienteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Cerrar la MainActivity actual
            });
            successBuilder.setCancelable(false); // No permitir cancelar
            successBuilder.show();
        });
        
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
    
    /**
     * Maneja la salida de la aplicación
     */
    private void salirDeApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("🚪 Salir de la Aplicación");
        builder.setMessage("¿Está seguro que desea salir de la aplicación?");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        
        builder.setPositiveButton("SÍ, SALIR", (dialog, which) -> {
            // Cerrar la aplicación
            finishAffinity(); // Cierra todas las actividades
            System.exit(0);   // Termina el proceso
        });
        
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
    
    /**
     * Método para limpiar todos los datos guardados (solo para desarrollo)
     */
    public void limpiarDatos() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
        
        // Reiniciar la actividad
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    
}
