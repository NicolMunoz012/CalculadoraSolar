package com.solarcalculator.app.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Adapter para el ViewPager2 que muestra las pestañas de explicación de cálculos.
 */
public class ExplicacionAdapter extends FragmentStateAdapter {

    private static final int NUM_TABS = 7;

    public ExplicacionAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return CalculoDetalleFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }

    /**
     * Obtiene el título de la pestaña según la posición.
     */
    public static String getTituloTab(int position) {
        switch (position) {
            case 0:
                return "Paneles";
            case 1:
                return "Potencia";
            case 2:
                return "Ahorro";
            case 3:
                return "Costo";
            case 4:
                return "ROI";
            case 5:
                return "Área";
            case 6:
                return "Producción";
            default:
                return "";
        }
    }
}