package com.solarcalculator.app.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.solarcalculator.app.R;
import com.solarcalculator.app.model.CalculosSolares;
import com.solarcalculator.app.utils.FormatoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Actividad que muestra los resultados del cálculo del sistema solar.
 * Incluye tarjetas con información detallada y un gráfico de barras comparativo.
 * 
 * @author SolarCalculatorApp
 * @version 1.0
 */
public class ResultadosActivity extends AppCompatActivity {
    
    // Vistas
    private TextView tvSubtitulo;
    private BarChart barChart;
    
    // Datos
    private CalculosSolares calculos;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        
        // Habilitar botón de retroceso en ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        // Obtener datos del intent
        calculos = getIntent().getParcelableExtra("calculos");
        
        if (calculos == null) {
            finish();
            return;
        }
        
        // Inicializar vistas
        inicializarVistas();
        
        // Mostrar resultados
        mostrarResultados();
        
        // Configurar gráfico
        configurarGrafico();
        
        // Configurar listeners
        configurarListeners();
    }
    
    /**
     * Inicializa las vistas de la actividad.
     */
    private void inicializarVistas() {
        tvSubtitulo = findViewById(R.id.tvSubtitulo);
        barChart = findViewById(R.id.barChart);
    }
    
    /**
     * Muestra todos los resultados en las tarjetas.
     */
    private void mostrarResultados() {
        // Subtítulo con consumo
        String subtitulo = getString(R.string.subtitulo_resultados, calculos.getConsumoMensual());
        tvSubtitulo.setText(subtitulo);
        
        // Card 1: Potencia del Sistema
        configurarCard(
            R.id.cardPotencia,
            android.R.drawable.ic_menu_compass,
            getString(R.string.titulo_potencia),
            FormatoUtils.formatearPotencia(calculos.getPotenciaSistema()),
            null,
            getString(R.string.descripcion_potencia)
        );
        
        // Card 2: Número de Paneles
        configurarCard(
            R.id.cardPaneles,
            android.R.drawable.ic_menu_day,
            getString(R.string.titulo_paneles),
            String.format(getString(R.string.valor_paneles), calculos.getNumeroPaneles()),
            String.format(getString(R.string.valor_paneles_decimal), calculos.getNumeroPanelesExacto()),
            getString(R.string.descripcion_paneles)
        );
        
        // Card 3: Ahorro Mensual
        configurarCard(
            R.id.cardAhorro,
            android.R.drawable.ic_menu_sort_by_size,
            getString(R.string.titulo_ahorro),
            "$" + FormatoUtils.formatearMoneda(calculos.getAhorroMensual()) + " COP",
            null,
            getString(R.string.descripcion_ahorro)
        );
        
        // Card 4: Costo de Instalación
        configurarCard(
            R.id.cardCosto,
            android.R.drawable.ic_menu_today,
            getString(R.string.titulo_costo),
            "$" + FormatoUtils.formatearMoneda(calculos.getCostoInstalacion()) + " COP",
            null,
            getString(R.string.descripcion_costo)
        );
        
        // Card 5: Retorno de Inversión
        configurarCard(
            R.id.cardRetorno,
            android.R.drawable.ic_menu_my_calendar,
            getString(R.string.titulo_retorno),
            FormatoUtils.formatearAnos(calculos.getRetornoInversion()),
            null,
            getString(R.string.descripcion_retorno)
        );
        
        // Card 6: Área Requerida
        configurarCard(
            R.id.cardArea,
            android.R.drawable.ic_menu_mapmode,
            getString(R.string.titulo_area),
            FormatoUtils.formatearArea(calculos.getAreaRequerida()),
            null,
            getString(R.string.descripcion_area)
        );
    }
    
    /**
     * Configura una tarjeta de resultado con los datos proporcionados.
     */
    private void configurarCard(int cardId, int iconoRes, String titulo, 
                               String valorPrincipal, String valorSecundario, String descripcion) {
        View card = findViewById(cardId);
        
        ImageView ivIcono = card.findViewById(R.id.ivIcono);
        TextView tvTitulo = card.findViewById(R.id.tvTituloResultado);
        TextView tvValorPrincipal = card.findViewById(R.id.tvValorPrincipal);
        TextView tvValorSecundario = card.findViewById(R.id.tvValorSecundario);
        TextView tvDescripcion = card.findViewById(R.id.tvDescripcion);
        
        ivIcono.setImageResource(iconoRes);
        tvTitulo.setText(titulo);
        tvValorPrincipal.setText(valorPrincipal);
        tvDescripcion.setText(descripcion);
        
        if (valorSecundario != null && !valorSecundario.isEmpty()) {
            tvValorSecundario.setVisibility(View.VISIBLE);
            tvValorSecundario.setText(valorSecundario);
        } else {
            tvValorSecundario.setVisibility(View.GONE);
        }
    }
    
    /**
     * Configura el gráfico de barras comparativo.
     */
    private void configurarGrafico() {
        // Preparar datos
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, (float) calculos.getConsumoMensual()));
        entries.add(new BarEntry(1, (float) calculos.getProduccionMensualSistema()));
        
        // Crear dataset
        BarDataSet dataSet = new BarDataSet(entries, "Energía (kWh)");
        
        // Colores personalizados
        int colorConsumo = ContextCompat.getColor(this, R.color.chart_consumo);
        int colorProduccion = ContextCompat.getColor(this, R.color.chart_produccion);
        dataSet.setColors(colorConsumo, colorProduccion);
        
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.BLACK);
        
        // Formato de valores en las barras
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return FormatoUtils.formatearDecimal(value, 0);
            }
        });
        
        // Crear BarData
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.6f);
        
        // Configurar gráfico
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setFitBars(true);
        barChart.animateY(1000);
        
        // Configurar eje X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(2);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{
            getString(R.string.etiqueta_consumo_grafico),
            getString(R.string.etiqueta_produccion_grafico)
        }));
        
        // Configurar eje Y izquierdo
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f);
        
        // Deshabilitar eje Y derecho
        barChart.getAxisRight().setEnabled(false);
        
        // Configurar leyenda
        barChart.getLegend().setEnabled(false);
        
        // Refrescar
        barChart.invalidate();
    }
    
    /**
     * Configura los listeners de los botones.
     */
    private void configurarListeners() {
        // Botón compartir
        findViewById(R.id.btnCompartir).setOnClickListener(v -> compartirResultados());
        
        // Botón nuevo cálculo
        findViewById(R.id.btnNuevoCalculo).setOnClickListener(v -> finish());
    }
    
    /**
     * Comparte los resultados mediante un Intent de compartir.
     */
    private void compartirResultados() {
        String textoCompartir = getString(R.string.texto_compartir,
            calculos.getConsumoMensual(),
            calculos.getPotenciaSistema(),
            calculos.getNumeroPaneles(),
            FormatoUtils.formatearMoneda(calculos.getAhorroMensual()),
            FormatoUtils.formatearMoneda(calculos.getCostoInstalacion()),
            calculos.getRetornoInversion(),
            (int) calculos.getAreaRequerida()
        );
        
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, textoCompartir);
        
        startActivity(Intent.createChooser(shareIntent, getString(R.string.compartir_via)));
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
