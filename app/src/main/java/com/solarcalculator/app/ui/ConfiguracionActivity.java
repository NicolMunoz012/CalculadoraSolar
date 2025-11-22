package com.solarcalculator.app.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.solarcalculator.app.R;
import com.solarcalculator.app.model.Configuracion;
import com.solarcalculator.app.utils.CalculadoraSolar;

/**
 * Actividad para configurar los parámetros avanzados del sistema solar.
 * Permite al usuario ajustar las constantes utilizadas en los cálculos.
 * 
 * @author SolarCalculatorApp
 * @version 1.0
 */
public class ConfiguracionActivity extends AppCompatActivity {
    
    // Vistas
    private TextInputEditText etProduccionPanel;
    private TextInputEditText etPotenciaPanel;
    private TextInputEditText etAreaPanel;
    private TextInputEditText etPrecioKwh;
    private TextInputEditText etCostoPanel;
    
    // Modelo
    private Configuracion configuracion;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        
        // Habilitar botón de retroceso
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        // Inicializar configuración
        configuracion = new Configuracion(this);
        
        // Inicializar vistas
        inicializarVistas();
        
        // Cargar valores actuales
        cargarValoresActuales();
        
        // Configurar listeners
        configurarListeners();
    }
    
    /**
     * Inicializa todas las vistas de la actividad.
     */
    private void inicializarVistas() {
        etProduccionPanel = findViewById(R.id.etProduccionPanel);
        etPotenciaPanel = findViewById(R.id.etPotenciaPanel);
        etAreaPanel = findViewById(R.id.etAreaPanel);
        etPrecioKwh = findViewById(R.id.etPrecioKwh);
        etCostoPanel = findViewById(R.id.etCostoPanel);
    }
    
    /**
     * Carga los valores actuales de configuración en los campos.
     */
    private void cargarValoresActuales() {
        etProduccionPanel.setText(String.valueOf(configuracion.getProduccionPanel()));
        etPotenciaPanel.setText(String.valueOf(configuracion.getPotenciaPanel()));
        etAreaPanel.setText(String.valueOf(configuracion.getAreaPanel()));
        etPrecioKwh.setText(String.valueOf(configuracion.getPrecioKwh()));
        etCostoPanel.setText(String.valueOf(configuracion.getCostoPanel()));
    }
    
    /**
     * Configura los listeners de los botones.
     */
    private void configurarListeners() {
        // Botón guardar
        findViewById(R.id.btnGuardar).setOnClickListener(v -> guardarConfiguracion());
        
        // Botón restaurar
        findViewById(R.id.btnRestaurar).setOnClickListener(v -> mostrarDialogoRestaurar());
    }
    
    /**
     * Valida y guarda la configuración.
     */
    private void guardarConfiguracion() {
        try {
            // Obtener y validar valores
            String strProduccion = etProduccionPanel.getText().toString();
            String strPotencia = etPotenciaPanel.getText().toString();
            String strArea = etAreaPanel.getText().toString();
            String strPrecio = etPrecioKwh.getText().toString();
            String strCosto = etCostoPanel.getText().toString();
            
            // Validar que todos los campos estén llenos
            if (strProduccion.isEmpty() || strPotencia.isEmpty() || strArea.isEmpty() ||
                strPrecio.isEmpty() || strCosto.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Parsear valores
            double produccion = CalculadoraSolar.parseDouble(strProduccion);
            double potencia = CalculadoraSolar.parseDouble(strPotencia);
            double area = CalculadoraSolar.parseDouble(strArea);
            double precio = CalculadoraSolar.parseDouble(strPrecio);
            double costo = CalculadoraSolar.parseDouble(strCosto);
            
            // Validar rangos
            if (produccion <= 0 || produccion > 100) {
                Toast.makeText(this, "Producción debe estar entre 0 y 100 kWh/día", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (potencia <= 0 || potencia > 10000) {
                Toast.makeText(this, "Potencia debe estar entre 0 y 10,000 W", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (area <= 0 || area > 100) {
                Toast.makeText(this, "Área debe estar entre 0 y 100 m²", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (precio <= 0 || precio > 10000) {
                Toast.makeText(this, "Precio kWh debe estar entre 0 y 10,000 COP", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (costo <= 0 || costo > 100000000) {
                Toast.makeText(this, "Costo del panel debe estar entre 0 y 100,000,000 COP", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Guardar configuración
            configuracion.setProduccionPanel(produccion);
            configuracion.setPotenciaPanel(potencia);
            configuracion.setAreaPanel(area);
            configuracion.setPrecioKwh(precio);
            configuracion.setCostoPanel(costo);
            
            // Mostrar confirmación
            Toast.makeText(this, R.string.mensaje_guardado, Toast.LENGTH_SHORT).show();
            
            // Volver a la pantalla anterior
            finish();
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingresa valores numéricos válidos", Toast.LENGTH_SHORT).show();
        }
    }
    
    /**
     * Muestra un diálogo de confirmación para restaurar valores predeterminados.
     */
    private void mostrarDialogoRestaurar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Restaurar valores");
        builder.setMessage("¿Deseas restaurar los valores predeterminados? Se perderán los cambios actuales.");
        
        builder.setPositiveButton("Restaurar", (dialog, which) -> {
            configuracion.restaurarValoresPorDefecto();
            cargarValoresActuales();
            Toast.makeText(this, R.string.mensaje_restaurado, Toast.LENGTH_SHORT).show();
        });
        
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        
        builder.create().show();
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
