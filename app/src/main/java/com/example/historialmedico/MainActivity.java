package com.example.historialmedico;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView infoLabel;
    private int clickCount = 0;
    private long lastClickTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        infoLabel = findViewById(R.id.infoLabel);
        Button btnHistorial = findViewById(R.id.btnHistorial);
        Button btnMedicamentos = findViewById(R.id.btnMedicamentos);
        Button btnEmergencia = findViewById(R.id.btnEmergencia);

        // Configurar listeners
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

        btnEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manejarEmergencia();
            }
        });
    }

    private void mostrarHistorial() {
        String historial = "Historial Médico:\n\n" +
                "• Diabetes tipo 2\n" +
                "• Hipertensión arterial\n" +
                "• Cirugía de cadera (2020)\n" +
                "• Alergia al polen";
        
        infoLabel.setText(historial);
        infoLabel.setVisibility(View.VISIBLE);
    }

    private void mostrarMedicamentos() {
        String medicamentos = "Medicamentos y Alergias:\n\n" +
                "• Metformina 850mg (1 por la mañana)\n" +
                "• Losartán 50mg (1 por la noche)\n" +
                "• Alergia: Penicilina";
        
        infoLabel.setText(medicamentos);
        infoLabel.setVisibility(View.VISIBLE);
    }

    private void manejarEmergencia() {
        long now = SystemClock.elapsedRealtime();
        
        // Reiniciar contador si han pasado más de 5 segundos
        if ((now - lastClickTime) > 5000) {
            clickCount = 0;
        }
        
        clickCount++;
        lastClickTime = now;

        if (clickCount == 2) {
            Toast.makeText(this, "🚨 EMERGENCIA: Llamando a contacto de emergencia...", 
                    Toast.LENGTH_LONG).show();
            infoLabel.setText("Contactando a familiar de emergencia...");
            infoLabel.setVisibility(View.VISIBLE);
        } else if (clickCount >= 3) {
            Toast.makeText(this, "🚨 EMERGENCIA: Llamando al 911...\nEspere con calma mientras llega la ayuda", 
                    Toast.LENGTH_LONG).show();
            infoLabel.setText("🚨 LLAMANDO AL 911 🚨\n\nEspere con calma.\nLa ayuda está en camino.");
            infoLabel.setVisibility(View.VISIBLE);
            clickCount = 0; // Reiniciar contador
        }
    }
}
