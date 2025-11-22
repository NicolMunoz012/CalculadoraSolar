package com.solarcalculator.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.solarcalculator.app.R;
import com.solarcalculator.app.model.CalculosSolares;
import com.solarcalculator.app.model.Configuracion;
import com.solarcalculator.app.utils.CalculadoraSolar;

/**
 * Actividad principal de la aplicación.
 * Permite al usuario ingresar su consumo mensual de energía y calcular el sistema solar necesario.
 *
 * @author SolarCalculatorApp
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    // Constantes
    private static final double MAX_CONSUMO_KWH = 100000.0; // Límite máximo de consumo

    // Vistas
    private TextInputLayout tilConsumo;
    private TextInputEditText etConsumo;

    // Modelo
    private Configuracion configuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar configuración
        configuracion = new Configuracion(this);

        // Inicializar vistas
        inicializarVistas();

        // Configurar listeners
        configurarListeners();
    }

    /**
     * Inicializa todas las vistas de la actividad.
     */
    private void inicializarVistas() {
        tilConsumo = findViewById(R.id.tilConsumo);
        etConsumo = findViewById(R.id.etConsumo);
    }

    /**
     * Configura los listeners de los botones.
     */
    private void configurarListeners() {
        // Botón calcular
        findViewById(R.id.btnCalcular).setOnClickListener(v -> calcularSistema());

        // Botón configuración
        findViewById(R.id.btnConfiguracion).setOnClickListener(v -> abrirConfiguracion());

        // NUEVO: Botón de explicación de cálculos
        findViewById(R.id.btnExplicacion).setOnClickListener(v -> abrirExplicacion());

        // Botón ayuda
        findViewById(R.id.btnAyuda).setOnClickListener(v -> mostrarAyuda());
    }

    /**
     * Valida el input y calcula el sistema solar.
     */
    private void calcularSistema() {
        String inputConsumo = etConsumo.getText().toString().trim();

        // Validar campo vacío
        if (inputConsumo.isEmpty()) {
            tilConsumo.setError(getString(R.string.error_campo_vacio));
            etConsumo.requestFocus();
            return;
        }

        // Limpiar error previo
        tilConsumo.setError(null);

        // Validar que sea un número válido
        if (!CalculadoraSolar.esValorValido(inputConsumo)) {
            tilConsumo.setError(getString(R.string.error_valor_invalido));
            etConsumo.requestFocus();
            return;
        }

        try {
            // Parsear el valor
            double consumoMensual = CalculadoraSolar.parseDouble(inputConsumo);

            // Validar rango
            if (consumoMensual > MAX_CONSUMO_KWH) {
                tilConsumo.setError(getString(R.string.error_valor_muy_grande));
                etConsumo.requestFocus();
                return;
            }

            // Realizar cálculo
            CalculosSolares calculos = CalculadoraSolar.calcular(consumoMensual, configuracion);

            // Navegar a pantalla de resultados
            Intent intent = new Intent(this, ResultadosActivity.class);
            intent.putExtra("calculos", calculos);
            startActivity(intent);

        } catch (Exception e) {
            tilConsumo.setError(getString(R.string.error_valor_invalido));
            etConsumo.requestFocus();
        }
    }

    /**
     * Abre la actividad de configuración avanzada.
     */
    private void abrirConfiguracion() {
        Intent intent = new Intent(this, ConfiguracionActivity.class);
        startActivity(intent);
    }

    /**
     * NUEVO: Abre la actividad de explicación de cálculos.
     */
    private void abrirExplicacion() {
        Intent intent = new Intent(this, ExplicacionCalculosActivity.class);
        startActivity(intent);
    }

    /**
     * Muestra el diálogo de ayuda con información sobre la aplicación.
     */
    private void mostrarAyuda() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_ayuda);

        // Cargar el contenido HTML desde strings.xml
        String contenidoHtml = getString(R.string.contenido_ayuda);

        builder.setMessage(Html.fromHtml(contenidoHtml, Html.FROM_HTML_MODE_LEGACY));
        builder.setPositiveButton(R.string.boton_entendido, (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Limpiar errores al volver a la actividad
        tilConsumo.setError(null);
    }
}