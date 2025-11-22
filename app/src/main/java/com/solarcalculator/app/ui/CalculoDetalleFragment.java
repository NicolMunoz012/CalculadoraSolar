package com.solarcalculator.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.solarcalculator.app.R;

/**
 * Fragment que muestra los detalles de un cálculo específico.
 */
public class CalculoDetalleFragment extends Fragment {

    private static final String ARG_TIPO_CALCULO = "tipo_calculo";

    private TextView tvTituloCalculo;
    private TextView tvDescripcion;
    private TextView tvFormula;
    private TextView tvVariables;
    private TextView tvEjemplo;
    private TextView tvNotas;
    private MaterialCardView cardNotas;

    public static CalculoDetalleFragment newInstance(int tipoCalculo) {
        CalculoDetalleFragment fragment = new CalculoDetalleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TIPO_CALCULO, tipoCalculo);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculo_detalle, container, false);

        inicializarVistas(view);
        cargarContenido();

        return view;
    }

    private void inicializarVistas(View view) {
        tvTituloCalculo = view.findViewById(R.id.tvTituloCalculo);
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        tvFormula = view.findViewById(R.id.tvFormula);
        tvVariables = view.findViewById(R.id.tvVariables);
        tvEjemplo = view.findViewById(R.id.tvEjemplo);
        tvNotas = view.findViewById(R.id.tvNotas);
        cardNotas = view.findViewById(R.id.cardNotas);
    }

    private void cargarContenido() {
        int tipoCalculo = getArguments() != null ? getArguments().getInt(ARG_TIPO_CALCULO) : 0;

        switch (tipoCalculo) {
            case 0: // Número de Paneles
                mostrarCalculoPaneles();
                break;
            case 1: // Potencia del Sistema
                mostrarCalculoPotencia();
                break;
            case 2: // Ahorro Mensual
                mostrarCalculoAhorro();
                break;
            case 3: // Costo de Instalación
                mostrarCalculoCosto();
                break;
            case 4: // Retorno de Inversión
                mostrarCalculoRetorno();
                break;
            case 5: // Área Requerida
                mostrarCalculoArea();
                break;
            case 6: // Producción del Sistema
                mostrarCalculoProduccion();
                break;
        }
    }

    private void mostrarCalculoPaneles() {
        tvTituloCalculo.setText("🔆 Número de Paneles");

        tvDescripcion.setText("Este cálculo determina cuántos paneles solares son necesarios " +
                "para cubrir tu consumo energético mensual. Se basa en la producción mensual " +
                "promedio de cada panel fotovoltaico.");

        tvFormula.setText("N° Paneles = Consumo Mensual / Producción por Panel\n\n" +
                "N° Paneles = ⌈resultado⌉");

        tvVariables.setText(
                "• Consumo Mensual: kWh consumidos al mes (dato del usuario)\n\n" +
                        "• Producción por Panel: kWh generados por 1 panel al mes\n" +
                        "  = Producción Diaria × 30 días\n" +
                        "  = 2,2 kWh/día × 30 = 66 kWh/mes (valor por defecto)\n\n" +
                        "• ⌈⌉: Función techo (redondeo hacia arriba)\n" +
                        "  Ejemplo: 6,82 → 7 paneles"
        );

        tvEjemplo.setText(
                "Consumo: 450 kWh/mes\n" +
                        "Producción: 66 kWh/mes por panel\n\n" +
                        "Cálculo:\n" +
                        "450 / 66 = 6,82\n" +
                        "⌈6,82⌉ = 7 paneles\n\n" +
                        "✓ Se necesitan 7 paneles completos"
        );

        tvNotas.setText("• Siempre se redondea HACIA ARRIBA para garantizar que se cubra " +
                "todo el consumo\n\n" +
                "• La producción por panel depende de las horas de sol pico en tu región\n\n" +
                "• Un panel típico de 550W con 4-5 horas de sol genera ~2,2 kWh/día");
    }

    private void mostrarCalculoPotencia() {
        tvTituloCalculo.setText("⚡ Potencia del Sistema");

        tvDescripcion.setText("Calcula la capacidad total instalada del sistema fotovoltaico " +
                "en kilovatios (kW). Suma la potencia nominal de todos los paneles.");

        tvFormula.setText("Potencia Sistema (kW) = N° Paneles × Potencia Panel (kW)");

        tvVariables.setText(
                "• N° Paneles: Cantidad calculada de paneles solares\n\n" +
                        "• Potencia Panel: Potencia nominal en Watts (W)\n" +
                        "  Valor por defecto: 550 W = 0,55 kW\n" +
                        "  (Los paneles típicos van de 400W a 600W)"
        );

        tvEjemplo.setText(
                "N° Paneles: 7\n" +
                        "Potencia Panel: 550 W = 0,55 kW\n\n" +
                        "Cálculo:\n" +
                        "7 × 0,55 = 3,85 kW\n\n" +
                        "✓ Sistema de 3,85 kW de potencia instalada"
        );

        tvNotas.setText("• Esta es la potencia PICO del sistema (máxima capacidad)\n\n" +
                "• La potencia real varía según la radiación solar del momento\n\n" +
                "• Sistemas residenciales típicos: 2-10 kW");
    }

    private void mostrarCalculoAhorro() {
        tvTituloCalculo.setText("💰 Ahorro Mensual");

        tvDescripcion.setText("Calcula el dinero que dejarás de pagar mensualmente en tu " +
                "factura de electricidad al generar tu propia energía solar.");

        tvFormula.setText("Ahorro Mensual (COP) = Consumo Mensual × Precio kWh");

        tvVariables.setText(
                "• Consumo Mensual: kWh que consumes (y ahora generas)\n\n" +
                        "• Precio kWh: Tarifa eléctrica en pesos por kWh\n" +
                        "  Valor por defecto: $926 COP/kWh\n" +
                        "  (Promedio residencial en Colombia 2024)"
        );

        tvEjemplo.setText(
                "Consumo: 450 kWh/mes\n" +
                        "Tarifa: $926 COP/kWh\n\n" +
                        "Cálculo:\n" +
                        "450 × 926 = $416.700 COP/mes\n\n" +
                        "✓ Ahorro anual: $416.700 × 12 = $5.000.400 COP/año"
        );

        tvNotas.setText("• Este es el ahorro BRUTO (no incluye mantenimiento)\n\n" +
                "• Las tarifas eléctricas suelen aumentar 3-5% anual\n\n" +
                "• El ahorro real puede ser mayor si hay incrementos de tarifa");
    }

    private void mostrarCalculoCosto() {
        tvTituloCalculo.setText("💵 Costo de Instalación");

        tvDescripcion.setText("Estima la inversión inicial total necesaria para instalar el " +
                "sistema fotovoltaico completo, incluyendo paneles, inversor, estructura " +
                "e instalación.");

        tvFormula.setText("Costo Total (COP) = N° Paneles × Costo por Panel");

        tvVariables.setText(
                "• N° Paneles: Cantidad de paneles solares\n\n" +
                        "• Costo por Panel: Precio unitario completo\n" +
                        "  Valor por defecto: $2.100.000 COP\n\n" +
                        "  Incluye:\n" +
                        "  - Panel solar (~$800.000)\n" +
                        "  - Inversor prorrateado (~$700.000)\n" +
                        "  - Estructura y cables (~$300.000)\n" +
                        "  - Instalación (~$300.000)"
        );

        tvEjemplo.setText(
                "N° Paneles: 7\n" +
                        "Costo Unitario: $2.100.000 COP\n\n" +
                        "Cálculo:\n" +
                        "7 × 2.100.000 = $14.700.000 COP\n\n" +
                        "✓ Inversión inicial total"
        );

        tvNotas.setText("• Los precios varían según región y proveedor\n\n" +
                "• No incluye baterías (sistema conectado a red)\n\n" +
                "• Pueden existir subsidios o incentivos fiscales");
    }

    private void mostrarCalculoRetorno() {
        tvTituloCalculo.setText("📅 Retorno de Inversión");

        tvDescripcion.setText("Calcula en cuántos años recuperarás tu inversión inicial " +
                "mediante los ahorros en la factura de electricidad. Es un indicador clave " +
                "de rentabilidad del proyecto.");

        tvFormula.setText("ROI (años) = Costo Total / Ahorro Anual\n\n" +
                "Donde:\n" +
                "Ahorro Anual = Ahorro Mensual × 12");

        tvVariables.setText(
                "• Costo Total: Inversión inicial completa (COP)\n\n" +
                        "• Ahorro Mensual: Ahorro en factura cada mes (COP)\n\n" +
                        "• Ahorro Anual: Ahorro mensual × 12 meses (COP)"
        );

        tvEjemplo.setText(
                "Costo Total: $14.700.000 COP\n" +
                        "Ahorro Mensual: $416.700 COP\n" +
                        "Ahorro Anual: $416.700 × 12 = $5.000.400 COP\n\n" +
                        "Cálculo:\n" +
                        "14.700.000 / 5.000.400 = 2,94 años\n\n" +
                        "✓ Recuperas la inversión en ~3 años"
        );

        tvNotas.setText("• Los paneles tienen vida útil de 25-30 años\n\n" +
                "• No considera incrementos futuros en tarifas eléctricas\n\n" +
                "• ROI típico en Colombia: 3-6 años");

        cardNotas.setCardBackgroundColor(0xFFE8F5E9); // Verde claro - buena noticia
    }

    private void mostrarCalculoArea() {
        tvTituloCalculo.setText("📏 Área Requerida");

        tvDescripcion.setText("Calcula el espacio físico necesario en tu techo o terreno " +
                "para instalar todos los paneles solares del sistema.");

        tvFormula.setText("Área Total (m²) = N° Paneles × Área por Panel");

        tvVariables.setText(
                "• N° Paneles: Cantidad de paneles a instalar\n\n" +
                        "• Área por Panel: Espacio que ocupa 1 panel\n" +
                        "  Valor por defecto: 2,0 m²\n" +
                        "  (Paneles típicos: 1,7 - 2,3 m² cada uno)\n\n" +
                        "  Dimensiones comunes:\n" +
                        "  - Alto: ~2 metros\n" +
                        "  - Ancho: ~1 metro"
        );

        tvEjemplo.setText(
                "N° Paneles: 7\n" +
                        "Área Unitaria: 2,0 m²\n\n" +
                        "Cálculo:\n" +
                        "7 × 2,0 = 14 m²\n\n" +
                        "✓ Se necesitan 14 m² de espacio\n" +
                        "≈ Un cuarto de 3,5m × 4m"
        );

        tvNotas.setText("• Considera espacios entre paneles para mantenimiento\n\n" +
                "• El techo debe soportar ~15 kg por panel\n\n" +
                "• Orientación ideal: Sur (en Colombia)\n\n" +
                "• Evitar sombras de árboles o edificios");
    }

    private void mostrarCalculoProduccion() {
        tvTituloCalculo.setText("🔋 Producción del Sistema");

        tvDescripcion.setText("Calcula la energía total que generará tu sistema solar " +
                "fotovoltaico cada mes, sumando la producción de todos los paneles.");

        tvFormula.setText("Producción Mensual (kWh) = N° Paneles × Producción por Panel");

        tvVariables.setText(
                "• N° Paneles: Cantidad total de paneles\n\n" +
                        "• Producción por Panel: kWh generados por panel al mes\n" +
                        "  = Producción Diaria × 30 días\n" +
                        "  = 2,2 kWh/día × 30 = 66 kWh/mes\n\n" +
                        "  La producción diaria depende de:\n" +
                        "  - Horas de sol pico (HSP)\n" +
                        "  - Eficiencia del panel\n" +
                        "  - Inclinación y orientación"
        );

        tvEjemplo.setText(
                "N° Paneles: 7\n" +
                        "Producción: 66 kWh/mes por panel\n\n" +
                        "Cálculo:\n" +
                        "7 × 66 = 462 kWh/mes\n\n" +
                        "✓ El sistema genera 462 kWh/mes\n\n" +
                        "Cobertura:\n" +
                        "Si consumes 450 kWh/mes\n" +
                        "→ Cobertura: 102,7%"
        );

        tvNotas.setText("• La producción es mayor en verano y menor en invierno\n\n" +
                "• Rendimiento disminuye ~0,5% anual por degradación\n\n" +
                "• El exceso de energía puede venderse a la red (Ley 1715)");
    }
}