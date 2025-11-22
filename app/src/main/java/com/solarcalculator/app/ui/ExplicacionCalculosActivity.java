package com.solarcalculator.app.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.solarcalculator.app.R;

/**
 * Activity que muestra la explicación detallada de todos los cálculos
 * del sistema solar fotovoltaico mediante pestañas.
 *
 * @author SolarCalculatorApp
 * @version 1.0
 */
public class ExplicacionCalculosActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ExplicacionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicacion_calculos);

        inicializarVistas();
        configurarToolbar();
        configurarViewPager();
    }

    /**
     * Inicializa las vistas de la activity.
     */
    private void inicializarVistas() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    /**
     * Configura el toolbar con botón de retroceso.
     */
    private void configurarToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Configura el ViewPager2 con el adapter y conecta las pestañas.
     */
    private void configurarViewPager() {
        // Crear adapter
        adapter = new ExplicacionAdapter(this);
        viewPager.setAdapter(adapter);

        // Conectar TabLayout con ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(ExplicacionAdapter.getTituloTab(position));
        }).attach();
    }
}