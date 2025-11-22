package com.solarcalculator.app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Clase modelo que encapsula los resultados de los cálculos del sistema solar.
 * Implementa Parcelable para poder pasar objetos entre Activities.
 * 
 * @author SolarCalculatorApp
 * @version 1.0
 */
public class CalculosSolares implements Parcelable {
    
    private double consumoMensual;           // kWh/mes
    private double potenciaSistema;          // kW
    private int numeroPaneles;               // Entero redondeado hacia arriba
    private double numeroPanelesExacto;      // Número decimal exacto
    private double ahorroMensual;            // COP
    private double costoInstalacion;         // COP
    private double retornoInversion;         // Años
    private double areaRequerida;            // m²
    private double produccionMensualSistema; // kWh/mes
    
    /**
     * Constructor con todos los parámetros.
     */
    public CalculosSolares(double consumoMensual, double potenciaSistema, 
                          int numeroPaneles, double numeroPanelesExacto,
                          double ahorroMensual, double costoInstalacion,
                          double retornoInversion, double areaRequerida,
                          double produccionMensualSistema) {
        this.consumoMensual = consumoMensual;
        this.potenciaSistema = potenciaSistema;
        this.numeroPaneles = numeroPaneles;
        this.numeroPanelesExacto = numeroPanelesExacto;
        this.ahorroMensual = ahorroMensual;
        this.costoInstalacion = costoInstalacion;
        this.retornoInversion = retornoInversion;
        this.areaRequerida = areaRequerida;
        this.produccionMensualSistema = produccionMensualSistema;
    }
    
    /**
     * Constructor para Parcelable.
     */
    protected CalculosSolares(Parcel in) {
        consumoMensual = in.readDouble();
        potenciaSistema = in.readDouble();
        numeroPaneles = in.readInt();
        numeroPanelesExacto = in.readDouble();
        ahorroMensual = in.readDouble();
        costoInstalacion = in.readDouble();
        retornoInversion = in.readDouble();
        areaRequerida = in.readDouble();
        produccionMensualSistema = in.readDouble();
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(consumoMensual);
        dest.writeDouble(potenciaSistema);
        dest.writeInt(numeroPaneles);
        dest.writeDouble(numeroPanelesExacto);
        dest.writeDouble(ahorroMensual);
        dest.writeDouble(costoInstalacion);
        dest.writeDouble(retornoInversion);
        dest.writeDouble(areaRequerida);
        dest.writeDouble(produccionMensualSistema);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public static final Creator<CalculosSolares> CREATOR = new Creator<CalculosSolares>() {
        @Override
        public CalculosSolares createFromParcel(Parcel in) {
            return new CalculosSolares(in);
        }
        
        @Override
        public CalculosSolares[] newArray(int size) {
            return new CalculosSolares[size];
        }
    };
    
    // Getters
    public double getConsumoMensual() {
        return consumoMensual;
    }
    
    public double getPotenciaSistema() {
        return potenciaSistema;
    }
    
    public int getNumeroPaneles() {
        return numeroPaneles;
    }
    
    public double getNumeroPanelesExacto() {
        return numeroPanelesExacto;
    }
    
    public double getAhorroMensual() {
        return ahorroMensual;
    }
    
    public double getCostoInstalacion() {
        return costoInstalacion;
    }
    
    public double getRetornoInversion() {
        return retornoInversion;
    }
    
    public double getAreaRequerida() {
        return areaRequerida;
    }
    
    public double getProduccionMensualSistema() {
        return produccionMensualSistema;
    }
    
    @Override
    public String toString() {
        return "CalculosSolares{" +
                "consumoMensual=" + consumoMensual +
                ", potenciaSistema=" + potenciaSistema +
                ", numeroPaneles=" + numeroPaneles +
                ", numeroPanelesExacto=" + numeroPanelesExacto +
                ", ahorroMensual=" + ahorroMensual +
                ", costoInstalacion=" + costoInstalacion +
                ", retornoInversion=" + retornoInversion +
                ", areaRequerida=" + areaRequerida +
                ", produccionMensualSistema=" + produccionMensualSistema +
                '}';
    }
}
