package com.solarcalculator.app.utils;

import com.solarcalculator.app.model.CalculosSolares;
import com.solarcalculator.app.model.Configuracion;

/**
 * Clase utilitaria que contiene la lógica de cálculo del sistema solar fotovoltaico.
 * Todos los métodos son estáticos y pueden ser probados unitariamente.
 * 
 * @author SolarCalculatorApp
 * @version 1.0
 */
public class CalculadoraSolar {
    
    /**
     * Calcula todos los parámetros del sistema solar basándose en el consumo mensual.
     * 
     * @param consumoMensual Consumo promedio en kWh/mes
     * @param config Configuración con las constantes del sistema
     * @return Objeto CalculosSolares con todos los resultados
     * @throws IllegalArgumentException si el consumo es <= 0
     */
    public static CalculosSolares calcular(double consumoMensual, Configuracion config) {
        if (consumoMensual <= 0) {
            throw new IllegalArgumentException("El consumo mensual debe ser mayor a 0");
        }
        
        // Obtener constantes de configuración
        double produccionMensualPorPanel = config.getProduccionMensualPanel(); // kWh/mes
        double potenciaPorPanel = config.getPotenciaPanel() / 1000.0; // Convertir W a kW
        double areaPorPanel = config.getAreaPanel();
        double precioKwh = config.getPrecioKwh();
        double costoPorPanel = config.getCostoPanel();
        
        // Cálculo 1: Número de paneles necesarios
        double numeroPanelesExacto = consumoMensual / produccionMensualPorPanel;
        int numeroPaneles = (int) Math.ceil(numeroPanelesExacto); // Redondear hacia arriba
        
        // Cálculo 2: Potencia total del sistema (kW)
        double potenciaSistema = numeroPaneles * potenciaPorPanel;
        
        // Cálculo 3: Producción mensual total del sistema
        double produccionMensualSistema = numeroPaneles * produccionMensualPorPanel;
        
        // Cálculo 4: Ahorro mensual (COP)
        double ahorroMensual = consumoMensual * precioKwh;
        
        // Cálculo 5: Costo total de instalación (COP)
        double costoInstalacion = numeroPaneles * costoPorPanel;
        
        // Cálculo 6: Retorno de inversión en años
        double ahorroAnual = ahorroMensual * 12;
        double retornoInversion = costoInstalacion / ahorroAnual;
        
        // Cálculo 7: Área total requerida (m²)
        double areaRequerida = numeroPaneles * areaPorPanel;
        
        // Crear y retornar el objeto con todos los resultados
        return new CalculosSolares(
            consumoMensual,
            potenciaSistema,
            numeroPaneles,
            numeroPanelesExacto,
            ahorroMensual,
            costoInstalacion,
            retornoInversion,
            areaRequerida,
            produccionMensualSistema
        );
    }
    
    /**
     * Valida que un valor sea un número válido y positivo.
     * 
     * @param valor Valor a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean esValorValido(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }
        
        try {
            // Reemplazar coma por punto para manejar ambos formatos
            String valorNormalizado = valor.trim().replace(',', '.');
            double numero = Double.parseDouble(valorNormalizado);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Convierte un string a double, manejando comas y puntos decimales.
     * 
     * @param valor String a convertir
     * @return Valor convertido a double
     * @throws NumberFormatException si el valor no es un número válido
     */
    public static double parseDouble(String valor) throws NumberFormatException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new NumberFormatException("Valor vacío");
        }
        
        // Reemplazar coma por punto para manejar formato español
        String valorNormalizado = valor.trim().replace(',', '.');
        return Double.parseDouble(valorNormalizado);
    }
    
    /**
     * Calcula el porcentaje de cobertura del sistema respecto al consumo.
     * 
     * @param produccionSistema Producción mensual del sistema en kWh
     * @param consumoMensual Consumo mensual en kWh
     * @return Porcentaje de cobertura (0-100+)
     */
    public static double calcularPorcentajeCobertura(double produccionSistema, double consumoMensual) {
        if (consumoMensual == 0) {
            return 0;
        }
        return (produccionSistema / consumoMensual) * 100.0;
    }
}
