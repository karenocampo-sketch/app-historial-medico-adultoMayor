package com.example.historialmedico.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.historialmedico.R;
import com.example.historialmedico.models.Paciente;
import com.example.historialmedico.models.HistorialMedico;
import com.example.historialmedico.activities.MainActivity;

/**
 * Activity para el registro inicial del paciente
 * Formulario completo editable para capturar toda la información
 */
public class RegistroPacienteActivity extends AppCompatActivity {
    
    // Request codes
    private static final int REQUEST_IMAGE_PICK = 1001;
    
    // Vistas del formulario - Información personal
    private ImageView imgFotoPaciente;
    private EditText etNombreCompleto;
    private EditText etEdad;
    private EditText etDireccion;
    private EditText etTipoSangre;
    private EditText etEstatura;
    private EditText etPeso;
    private EditText etCorreo;
    
    // Vistas del formulario - Historial médico
    private EditText etAlergias;
    private EditText etCirugias;
    private EditText etEnfermedades;
    
    // Vistas del formulario - Medicamentos
    private EditText etMedicamentos;
    private EditText etAlergiasMedicamentos;
    
    // Vistas del formulario - Contactos de emergencia
    private EditText etContacto1;
    private EditText etTelefono1;
    private EditText etContacto2;
    private EditText etTelefono2;
    private EditText etContacto3;
    private EditText etTelefono3;
    
    // Botones
    private Button btnSeleccionarFoto;
    private Button btnGuardar;
    private Button btnCancelar;
    
    // Variables
    private String rutaFotoSeleccionada = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("RegistroPaciente", "onCreate - Iniciando");
        
        try {
            setContentView(R.layout.activity_registro_paciente);
            Log.d("RegistroPaciente", "setContentView - OK");
            
            inicializarVistas();
            Log.d("RegistroPaciente", "inicializarVistas - OK");
            
            configurarListeners();
            Log.d("RegistroPaciente", "configurarListeners - OK");
            
            cargarDatosExistentes(); // Cargar datos existentes para edición
            Log.d("RegistroPaciente", "cargarDatosExistentes - OK");
            
        } catch (Exception e) {
            Log.e("RegistroPaciente", "Error en onCreate: ", e);
            Toast.makeText(this, "Error al cargar el formulario: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }
    
    /**
     * Inicializa todas las vistas del formulario
     */
    private void inicializarVistas() {
        Log.d("RegistroPaciente", "Iniciando inicializarVistas");
        
        // Información personal
        imgFotoPaciente = findViewById(R.id.imgFotoPaciente);
        if (imgFotoPaciente == null) Log.e("RegistroPaciente", "imgFotoPaciente es null");
        
        etNombreCompleto = findViewById(R.id.etNombreCompleto);
        if (etNombreCompleto == null) Log.e("RegistroPaciente", "etNombreCompleto es null");
        
        etEdad = findViewById(R.id.etEdad);
        etDireccion = findViewById(R.id.etDireccion);
        etTipoSangre = findViewById(R.id.etTipoSangre);
        etEstatura = findViewById(R.id.etEstatura);
        etPeso = findViewById(R.id.etPeso);
        etCorreo = findViewById(R.id.etCorreo);
        
        // Historial médico
        etAlergias = findViewById(R.id.etAlergias);
        etCirugias = findViewById(R.id.etCirugias);
        etEnfermedades = findViewById(R.id.etEnfermedades);
        
        // Medicamentos
        etMedicamentos = findViewById(R.id.etMedicamentos);
        etAlergiasMedicamentos = findViewById(R.id.etAlergiasMedicamentos);
        
        // Contactos de emergencia
        etContacto1 = findViewById(R.id.etContacto1);
        etTelefono1 = findViewById(R.id.etTelefono1);
        etContacto2 = findViewById(R.id.etContacto2);
        etTelefono2 = findViewById(R.id.etTelefono2);
        etContacto3 = findViewById(R.id.etContacto3);
        etTelefono3 = findViewById(R.id.etTelefono3);
        
        // Botones
        btnSeleccionarFoto = findViewById(R.id.btnSeleccionarFoto);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);
    }
    
    /**
     * Configura los listeners de los botones
     */
    private void configurarListeners() {
        btnSeleccionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarFoto();
            }
        });
        
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarInformacion();
            }
        });
        
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    
    /**
     * Carga los datos existentes del paciente desde SharedPreferences
     */
    private void cargarDatosExistentes() {
        SharedPreferences prefs = getSharedPreferences("HistorialMedicoApp", MODE_PRIVATE);
        
        // Cargar datos personales
        String nombre = prefs.getString("nombre_completo", "");
        if (!nombre.isEmpty()) {
            etNombreCompleto.setText(nombre);
        }
        
        int edad = prefs.getInt("edad", 0);
        if (edad > 0) {
            etEdad.setText(String.valueOf(edad));
        }
        
        String direccion = prefs.getString("direccion", "");
        if (!direccion.isEmpty()) {
            etDireccion.setText(direccion);
        }
        
        String tipoSangre = prefs.getString("tipo_sangre", "");
        if (!tipoSangre.isEmpty()) {
            etTipoSangre.setText(tipoSangre);
        }
        
        float estatura = prefs.getFloat("estatura", 0.0f);
        if (estatura > 0) {
            etEstatura.setText(String.valueOf(estatura));
        }
        
        float peso = prefs.getFloat("peso", 0.0f);
        if (peso > 0) {
            etPeso.setText(String.valueOf(peso));
        }
        
        String correo = prefs.getString("correo", "");
        if (!correo.isEmpty()) {
            etCorreo.setText(correo);
        }
        
        // Cargar contactos de emergencia
        String contacto1 = prefs.getString("contacto1", "");
        if (!contacto1.isEmpty()) {
            etContacto1.setText(contacto1);
        }
        
        String telefono1 = prefs.getString("telefono1", "");
        if (!telefono1.isEmpty()) {
            etTelefono1.setText(telefono1);
        }
        
        String contacto2 = prefs.getString("contacto2", "");
        if (!contacto2.isEmpty()) {
            etContacto2.setText(contacto2);
        }
        
        String telefono2 = prefs.getString("telefono2", "");
        if (!telefono2.isEmpty()) {
            etTelefono2.setText(telefono2);
        }
        
        String contacto3 = prefs.getString("contacto3", "");
        if (!contacto3.isEmpty()) {
            etContacto3.setText(contacto3);
        }
        
        String telefono3 = prefs.getString("telefono3", "");
        if (!telefono3.isEmpty()) {
            etTelefono3.setText(telefono3);
        }
        
        // Cargar historial médico
        String alergias = prefs.getString("alergias", "");
        if (!alergias.isEmpty()) {
            etAlergias.setText(alergias);
        }
        
        String cirugias = prefs.getString("cirugias", "");
        if (!cirugias.isEmpty()) {
            etCirugias.setText(cirugias);
        }
        
        String enfermedades = prefs.getString("enfermedades", "");
        if (!enfermedades.isEmpty()) {
            etEnfermedades.setText(enfermedades);
        }
        
        // Cargar medicamentos
        String medicamentos = prefs.getString("medicamentos", "");
        if (!medicamentos.isEmpty()) {
            etMedicamentos.setText(medicamentos);
        }
        
        String alergiasMedicamentos = prefs.getString("alergias_medicamentos", "");
        if (!alergiasMedicamentos.isEmpty()) {
            etAlergiasMedicamentos.setText(alergiasMedicamentos);
        }
        
        // Cargar ruta de foto si existe
        rutaFotoSeleccionada = prefs.getString("foto_ruta", "");
        if (!rutaFotoSeleccionada.isEmpty()) {
            try {
                Uri fotoUri = Uri.parse(rutaFotoSeleccionada);
                imgFotoPaciente.setImageURI(fotoUri);
                Log.d("RegistroPaciente", "Foto cargada correctamente: " + rutaFotoSeleccionada);
            } catch (SecurityException e) {
                Log.w("RegistroPaciente", "Sin permisos para cargar foto: " + e.getMessage());
                // Si hay error de permisos, limpiar la ruta y usar imagen por defecto
                rutaFotoSeleccionada = "";
                imgFotoPaciente.setImageResource(R.drawable.ic_person_placeholder);
                // Limpiar la ruta de las preferencias para evitar futuros errores
                SharedPreferences.Editor editor = prefs.edit();
                editor.remove("foto_ruta");
                editor.apply();
            } catch (Exception e) {
                Log.e("RegistroPaciente", "Error cargando foto: " + e.getMessage());
                // Si hay cualquier otro error, limpiar la ruta y usar imagen por defecto
                rutaFotoSeleccionada = "";
                imgFotoPaciente.setImageResource(R.drawable.ic_person_placeholder);
            }
        } else {
            // Si no hay foto guardada, usar imagen por defecto
            imgFotoPaciente.setImageResource(R.drawable.ic_person_placeholder);
        }
    }
    
    /**
     * Abre el selector de fotos
     */
    private void seleccionarFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri fotoUri = data.getData();
            if (fotoUri != null) {
                imgFotoPaciente.setImageURI(fotoUri);
                rutaFotoSeleccionada = fotoUri.toString();
                Toast.makeText(this, "Foto seleccionada correctamente", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    /**
     * Guarda toda la información del formulario
     */
    private void guardarInformacion() {
        if (!validarFormulario()) {
            return;
        }
        
        try {
            // Si no se seleccionó nueva foto, mantener la existente
            if (rutaFotoSeleccionada.isEmpty()) {
                SharedPreferences prefs = getSharedPreferences("HistorialMedicoApp", MODE_PRIVATE);
                rutaFotoSeleccionada = prefs.getString("foto_ruta", "");
            }
            
            // Crear objeto Paciente con la información del formulario
            Paciente paciente = new Paciente(
                etNombreCompleto.getText().toString().trim(),
                Integer.parseInt(etEdad.getText().toString().trim()),
                etDireccion.getText().toString().trim(),
                etTipoSangre.getText().toString().trim(),
                Double.parseDouble(etEstatura.getText().toString().trim()),
                Double.parseDouble(etPeso.getText().toString().trim()),
                etCorreo.getText().toString().trim(),
                rutaFotoSeleccionada,
                etContacto1.getText().toString().trim(),
                etTelefono1.getText().toString().trim(),
                etContacto2.getText().toString().trim(),
                etTelefono2.getText().toString().trim(),
                etContacto3.getText().toString().trim(),
                etTelefono3.getText().toString().trim()
            );
            
            // Guardar en SharedPreferences
            guardarDatosEnPreferencias(paciente);
            
            Toast.makeText(this, "✅ Información guardada correctamente", Toast.LENGTH_LONG).show();
            
            // Enviar verificación por correo (simulada)
            enviarVerificacionCorreo(paciente.getCorreo());
            
            // Ir a la pantalla principal
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "❌ Error: Verifique que los campos numéricos sean válidos", Toast.LENGTH_LONG).show();
        }
    }
    
    /**
     * Valida que los campos obligatorios estén llenos
     */
    private boolean validarFormulario() {
        // Validar campos obligatorios
        if (etNombreCompleto.getText().toString().trim().isEmpty()) {
            etNombreCompleto.setError("Campo obligatorio");
            return false;
        }
        
        if (etEdad.getText().toString().trim().isEmpty()) {
            etEdad.setError("Campo obligatorio");
            return false;
        }
        
        if (etCorreo.getText().toString().trim().isEmpty()) {
            etCorreo.setError("Campo obligatorio");
            return false;
        }
        
        // Validar formato de correo básico
        String correo = etCorreo.getText().toString().trim();
        if (!correo.contains("@") || !correo.contains(".")) {
            etCorreo.setError("Formato de correo inválido");
            return false;
        }
        
        // Validar al menos un contacto de emergencia
        if (etContacto1.getText().toString().trim().isEmpty() || 
            etTelefono1.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "❌ Debe proporcionar al menos un contacto de emergencia", Toast.LENGTH_LONG).show();
            return false;
        }
        
        return true;
    }
    
    /**
     * Guarda los datos del paciente en SharedPreferences
     */
    private void guardarDatosEnPreferencias(Paciente paciente) {
        SharedPreferences prefs = getSharedPreferences("HistorialMedicoApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        
        // Datos personales
        editor.putString("nombre_completo", paciente.getNombreCompleto());
        editor.putInt("edad", paciente.getEdad());
        editor.putString("direccion", paciente.getDireccion());
        editor.putString("tipo_sangre", paciente.getTipoSangre());
        editor.putFloat("estatura", (float) paciente.getEstatura());
        editor.putFloat("peso", (float) paciente.getPeso());
        editor.putString("correo", paciente.getCorreo());
        editor.putString("foto_ruta", paciente.getRutaFoto());
        
        // Contactos de emergencia
        editor.putString("contacto1", paciente.getContactoEmergencia1());
        editor.putString("telefono1", paciente.getTelefonoEmergencia1());
        editor.putString("contacto2", paciente.getContactoEmergencia2());
        editor.putString("telefono2", paciente.getTelefonoEmergencia2());
        editor.putString("contacto3", paciente.getContactoEmergencia3());
        editor.putString("telefono3", paciente.getTelefonoEmergencia3());
        
        // Historial médico
        editor.putString("alergias", etAlergias.getText().toString().trim());
        editor.putString("cirugias", etCirugias.getText().toString().trim());
        editor.putString("enfermedades", etEnfermedades.getText().toString().trim());
        
        // Medicamentos
        editor.putString("medicamentos", etMedicamentos.getText().toString().trim());
        editor.putString("alergias_medicamentos", etAlergiasMedicamentos.getText().toString().trim());
        
        editor.apply();
    }
    
    /**
     * Simula el envío de verificación por correo
     */
    private void enviarVerificacionCorreo(String correo) {
        // Aquí se implementaría el envío real del correo
        Toast.makeText(this, "📧 Correo de verificación enviado a: " + correo, Toast.LENGTH_LONG).show();
    }
}
