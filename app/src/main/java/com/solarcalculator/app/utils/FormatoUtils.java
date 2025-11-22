package com.solarcalculator.app.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Clase utilitaria para formatear números, monedas y otros valores.
 * Utiliza formato colombiano para moneda (COP).
 * 
 * @author SolarCalculatorApp
 * @version 1.0
 */
public class FormatoUtils {
    
    // Locale para Colombia
    private static final Locale LOCALE_COLOMBIA = new Locale("es", "CO");
    
    /**
     * Formatea un valor monetario en pesos colombianos (COP).
     * Ejemplo: 417000 -> "417.000"
     * 
     * @param valor Valor a formatear
     * @return String formateado con separadores de miles
     */
    public static String formatearMoneda(double valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(LOCALE_COLOMBIA);
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return formatter.format(Math.round(valor));
    }
    
    /**
     * Formatea un número decimal con n decimales.
     * 
     * @param valor Valor a formatear
     * @param decimales Número de decimales a mostrar
     * @return String formateado
     */
    public static String formatearDecimal(double valor, int decimales) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(LOCALE_COLOMBIA);
        symbols.setDecimalSeparator(',');
        
        StringBuilder pattern = new StringBuilder("#0");
        if (decimales > 0) {
            pattern.append(".");
            for (int i = 0; i < decimales; i++) {
                pattern.append("0");
            }
        }
        
        DecimalFormat formatter = new DecimalFormat(pattern.toString(), symbols);
        return formatter.format(valor);
    }
    
    /**
     * Formatea un número entero con separadores de miles.
     * 
     * @param valor Valor a formatear
     * @return String formateado
     */
    public static String formatearEntero(int valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(LOCALE_COLOMBIA);
        symbols.setGroupingSeparator('.');
        
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return formatter.format(valor);
    }
    
    /**
     * Formatea un porcentaje con un decimal.
     * 
     * @param valor Valor del porcentaje (ej: 85.5)
     * @return String formateado (ej: "85,5%")
     */
    public static String formatearPorcentaje(double valor) {
        return formatearDecimal(valor, 1) + "%";
    }
    
    /**
     * Formatea potencia en kW con 2 decimales.
     * 
     * @param potencia Potencia en kW
     * @return String formateado (ej: "3,85 kW")
     */
    public static String formatearPotencia(double potencia) {
        return formatearDecimal(potencia, 2) + " kW";
    }
    
    /**
     * Formatea área en m² con 0 decimales.
     * 
     * @param area Área en m²
     * @return String formateado (ej: "14 m²")
     */
    public static String formatearArea(double area) {
        return formatearEntero((int) Math.round(area)) + " m²";
    }
    
    /**
     * Formatea años con 1 decimal.
     * 
     * @param anos Años
     * @return String formateado (ej: "2,9 años")
     */
    public static String formatearAnos(double anos) {
        return formatearDecimal(anos, 1) + " años";
    }
    
    /**
     * Formatea energía en kWh con 2 decimales.
     * 
     * @param energia Energía en kWh
     * @return String formateado (ej: "450,00 kWh")
     */
    public static String formatearEnergia(double energia) {
        return formatearDecimal(energia, 2) + " kWh";
    }
    
    /**
     * Formatea un valor monetario con el símbolo de pesos.
     * 
     * @param valor Valor a formatear
     * @return String formateado (ej: "$417.000 COP")
     */
    public static String formatearMonedaCompleta(double valor) {
        return "$" + formatearMoneda(valor) + " COP";
    }
}
