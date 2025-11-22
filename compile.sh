#!/bin/bash

# Script de compilación para Calculadora Solar
# Genera el APK de la aplicación

echo "🌞 Calculadora Solar - Script de Compilación"
echo "=============================================="
echo ""

# Verificar que estamos en el directorio correcto
if [ ! -f "settings.gradle" ]; then
    echo "❌ Error: Ejecuta este script desde la raíz del proyecto"
    exit 1
fi

# Limpiar builds anteriores
echo "🧹 Limpiando builds anteriores..."
./gradlew clean

if [ $? -ne 0 ]; then
    echo "❌ Error en la limpieza. Verifica que Gradle esté instalado."
    exit 1
fi

echo ""
echo "⚙️  Compilando APK de Debug..."
./gradlew assembleDebug

if [ $? -ne 0 ]; then
    echo "❌ Error en la compilación"
    exit 1
fi

echo ""
echo "✅ ¡Compilación exitosa!"
echo ""
echo "📱 APK generado en:"
echo "   app/build/outputs/apk/debug/app-debug.apk"
echo ""
echo "Para instalar en un dispositivo conectado:"
echo "   adb install app/build/outputs/apk/debug/app-debug.apk"
echo ""
echo "Para compilar APK de Release (optimizado):"
echo "   ./gradlew assembleRelease"
echo ""
