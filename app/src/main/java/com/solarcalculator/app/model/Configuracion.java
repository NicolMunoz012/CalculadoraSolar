package com.solarcalculator.app.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Clase que gestiona la configuración y constantes del sistema solar.
 * Utiliza SharedPreferences para persistir los valores configurables.
 * 
 * @author SolarCalculatorApp
 * @version 1.0
 */
public class Configuracion {
    
    // Nombre del archivo de preferencias
    private static final String PREFS_NAME = "SolarCalculatorPrefs";
    
    // Claves para SharedPreferences
    private static final String KEY_PRODUCCION_PANEL = "produccion_panel";
    private static final String KEY_POTENCIA_PANEL = "potencia_panel";
    private static final String KEY_AREA_PANEL = "area_panel";
    private static final String KEY_PRECIO_KWH = "precio_kwh";
    private static final String KEY_COSTO_PANEL = "costo_panel";
    
    // Valores por defecto (según especificaciones del proyecto)
    public static final double DEFAULT_PRODUCCION_PANEL = 2.2;  // kWh/día
    public static final double DEFAULT_POTENCIA_PANEL = 550.0;  // Watts (0.55 kW)
    public static final double DEFAULT_AREA_PANEL = 2.0;        // m²
    public static final double DEFAULT_PRECIO_KWH = 926.0;      // COP
    public static final double DEFAULT_COSTO_PANEL = 2100000.0; // COP
    
    private SharedPreferences preferences;
    
    /**
     * Constructor que inicializa las preferencias compartidas.
     * 
     * @param context Contexto de la aplicación
     */
    public Configuracion(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    /**
     * Obtiene la producción diaria de un panel solar.
     * 
     * @return Producción en kWh/día
     */
    public double getProduccionPanel() {
        return getDouble(KEY_PRODUCCION_PANEL, DEFAULT_PRODUCCION_PANEL);
    }
    
    /**
     * Establece la producción diaria de un panel solar.
     * 
     * @param produccion Producción en kWh/día
     */
    public void setProduccionPanel(double produccion) {
        putDouble(KEY_PRODUCCION_PANEL, produccion);
    }
    
    /**
     * Obtiene la potencia nominal de un panel solar.
     * 
     * @return Potencia en Watts
     */
    public double getPotenciaPanel() {
        return getDouble(KEY_POTENCIA_PANEL, DEFAULT_POTENCIA_PANEL);
    }
    
    /**
     * Establece la potencia nominal de un panel solar.
     * 
     * @param potencia Potencia en Watts
     */
    public void setPotenciaPanel(double potencia) {
        putDouble(KEY_POTENCIA_PANEL, potencia);
    }
    
    /**
     * Obtiene el área de un panel solar.
     * 
     * @return Área en m²
     */
    public double getAreaPanel() {
        return getDouble(KEY_AREA_PANEL, DEFAULT_AREA_PANEL);
    }
    
    /**
     * Establece el área de un panel solar.
     * 
     * @param area Área en m²
     */
    public void setAreaPanel(double area) {
        putDouble(KEY_AREA_PANEL, area);
    }
    
    /**
     * Obtiene el precio del kWh de energía eléctrica.
     * 
     * @return Precio en COP
     */
    public double getPrecioKwh() {
        return getDouble(KEY_PRECIO_KWH, DEFAULT_PRECIO_KWH);
    }
    
    /**
     * Establece el precio del kWh de energía eléctrica.
     * 
     * @param precio Precio en COP
     */
    public void setPrecioKwh(double precio) {
        putDouble(KEY_PRECIO_KWH, precio);
    }
    
    /**
     * Obtiene el costo de instalación por panel.
     * 
     * @return Costo en COP
     */
    public double getCostoPanel() {
        return getDouble(KEY_COSTO_PANEL, DEFAULT_COSTO_PANEL);
    }
    
    /**
     * Establece el costo de instalación por panel.
     * 
     * @param costo Costo en COP
     */
    public void setCostoPanel(double costo) {
        putDouble(KEY_COSTO_PANEL, costo);
    }
    
    /**
     * Restaura todos los valores a sus valores por defecto.
     */
    public void restaurarValoresPorDefecto() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
    
    /**
     * Método auxiliar para obtener un double de SharedPreferences.
     * SharedPreferences no soporta double directamente, así que se usa float.
     * 
     * @param key Clave del valor
     * @param defaultValue Valor por defecto
     * @return Valor almacenado o valor por defecto
     */
    private double getDouble(String key, double defaultValue) {
        return (double) preferences.getFloat(key, (float) defaultValue);
    }
    
    /**
     * Método auxiliar para guardar un double en SharedPreferences.
     * 
     * @param key Clave del valor
     * @param value Valor a guardar
     */
    private void putDouble(String key, double value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, (float) value);
        editor.apply();
    }
    
    /**
     * Calcula la producción mensual de un panel (30 días).
     * 
     * @return Producción en kWh/mes
     */
    public double getProduccionMensualPanel() {
        return getProduccionPanel() * 30;
    }
}
