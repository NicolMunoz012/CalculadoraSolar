# Calculadora Solar - Sistema Fotovoltaico

Aplicación Android nativa para calcular y dimensionar sistemas solares fotovoltaicos basados en el consumo energético mensual.

## 📋 Descripción

Esta aplicación móvil profesional permite a los usuarios calcular todos los parámetros necesarios para instalar un sistema solar fotovoltaico en su hogar o negocio, incluyendo:

- ⚡ **Potencia del sistema** (kW)
  - 🔆 **Número de paneles** necesarios
  - 💰 **Ahorro mensual** estimado (COP)
  - 💵 **Costo total** de instalación
  - 📅 **Retorno de inversión** (años)
  - 📏 **Área requerida** (m²)

## ✨ Características Principales

- ✅ **Interfaz Material Design 3** con tema verde solar
  - ✅ **Gráficos interactivos** con MPAndroidChart
  - ✅ **Configuración avanzada** de constantes
  - ✅ **100% offline** - no requiere conexión a internet
  - ✅ **Tests unitarios** completos con JUnit

## 🛠️ Tecnologías

- **Lenguaje:** Java 8
  - **SDK mínimo:** Android 5.0 (API 21)
  - **SDK objetivo:** Android 14 (API 34)
  - **Gráficos:** MPAndroidChart v3.1.0
  - **Testing:** JUnit 4.13.2

## 🚀 Cómo Abrir el Proyecto

### Prerrequisitos
- Android Studio (Giraffe o superior)
  - JDK 8+

### Pasos
1. Abre Android Studio
   2. `File > Open...`
   3. Selecciona la carpeta `SolarCalculatorApp`
   4. Espera la sincronización de Gradle

## 📲 Generar APK

### APK de Debug (rápido)
```bash
./gradlew assembleDebug
# APK en: app/build/outputs/apk/debug/app-debug.apk
```

### APK de Release (optimizado)
```bash
./gradlew assembleRelease
# APK en: app/build/outputs/apk/release/app-release.apk
```

## 🔧 Configuración de Constantes

Edita `Configuracion.java` o usa la interfaz de configuración en la app:

```java
DEFAULT_PRODUCCION_PANEL = 2.2;  // kWh/día
DEFAULT_POTENCIA_PANEL = 550.0;  // Watts
DEFAULT_AREA_PANEL = 2.0;        // m²
DEFAULT_PRECIO_KWH = 926.0;      // COP
DEFAULT_COSTO_PANEL = 2100000.0; // COP
```

## 🧪 Ejecutar Tests

```bash
# Todos los tests
./gradlew test

# Ver reporte
# app/build/reports/tests/testDebugUnitTest/index.html
```

## 📊 Casos de Prueba

### Caso 1: 450 kWh/mes
- Paneles: 7 (6.82 exacto)
  - Potencia: 3.85 kW
  - Ahorro: $416,700 COP/mes
  - Costo: $14,700,000 COP
  - Retorno: ~2.9 años
  - Área: 14 m²

### Caso 2: 80 kWh/mes
- Paneles: 2
  - Potencia: 1.10 kW
  - Ahorro: $74,080 COP/mes
  - Costo: $4,200,000 COP
  - Retorno: ~4.7 años
  - Área: 4 m²

### Caso 3: 1200 kWh/mes
- Paneles: 19
  - Potencia: 10.45 kW
  - Ahorro: $1,111,200 COP/mes
  - Costo: $39,900,000 COP
  - Retorno: ~3.0 años
  - Área: 38 m²

## 📝 Notas Importantes

### Sobre las Constantes
Los valores por defecto son estimaciones:
- **2.2 kWh/día:** Promedio con 4-5 horas de sol pico
  - **$926 COP:** Tarifa residencial promedio Colombia
  - **$2,100,000 COP:** Panel + inversor + instalación

**Recomendación:** Ajusta según tu región y condiciones locales.

### Limitaciones
- Los cálculos son estimaciones
  - No incluye baterías ni costos de mantenimiento
  - Para evaluación profesional, consulta un especialista

## 🐛 Solución de Problemas

### Error: "SDK location not found"
```bash
echo "sdk.dir=/ruta/a/tu/Android/Sdk" > local.properties
```

### Error al sincronizar Gradle
```bash
./gradlew clean
./gradlew build
```

## 👨‍💻 Autor

**SolarCalculatorApp** v1.0.0 - Noviembre 2024

---

⚡ **¡Energía solar para todos!** ☀️
