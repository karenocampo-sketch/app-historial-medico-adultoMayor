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

                infoLabel = findViewById(R.id.infoLabel);
                Button btnHistorial = findViewById(R.id.btnHistorial);
                Button btnMedicamentos = findViewById(R.id.btnMedicamentos);
                Button btnEmergencia = findViewById(R.id.btnEmergencia);

                btnHistorial.setOnClickListener(v -> {
                        infoLabel.setText(
                                        "Historial Médico:\n" +
                                                        "- Diabetes tipo 2\n" +
                                                        "- Hipertensión arterial\n" +
                                                        "- Cirugía de cadera (2020)\n" +
                                                        "- Alergia al polen");
                        infoLabel.setVisibility(View.VISIBLE);
                });

                btnMedicamentos.setOnClickListener(v -> {
                        infoLabel.setText(
                                        "Medicamentos y Alergias:\n" +
                                                        "- Metformina 850mg (1 por la mañana)\n" +
                                                        "- Losartán 50mg (1 por la noche)\n" +
                                                        "- Alergia: Penicilina");
                        infoLabel.setVisibility(View.VISIBLE);
                });

                btnEmergencia.setOnClickListener(v -> {
                        long now = SystemClock.elapsedRealtime();
                        if ((now - lastClickTime) > 5000) {
                                clickCount = 0;
                        }
                        clickCount++;
                        lastClickTime = now;

                        if (clickCount == 2) {
                                Toast.makeText(this, "🚨 Llamando a contacto de emergencia...", Toast.LENGTH_SHORT)
                                                .show();
                        } else if (clickCount == 3) {
                                Toast.makeText(this, "🚨 Llamando al 911...", Toast.LENGTH_SHORT).show();
                                clickCount = 0;
                        }
                });
        }
}
