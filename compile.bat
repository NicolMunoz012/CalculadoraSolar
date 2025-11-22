@echo off
REM Script de compilación para Calculadora Solar - Windows
REM Genera el APK de la aplicación

echo.
echo ☀️ Calculadora Solar - Script de Compilación
echo ==============================================
echo.

REM Verificar que estamos en el directorio correcto
if not exist "settings.gradle" (
    echo ❌ Error: Ejecuta este script desde la raíz del proyecto
    pause
    exit /b 1
)

REM Limpiar builds anteriores
echo 🧹 Limpiando builds anteriores...
call gradlew.bat clean

if errorlevel 1 (
    echo ❌ Error en la limpieza. Verifica que Gradle esté instalado.
    pause
    exit /b 1
)

echo.
echo ⚙️ Compilando APK de Debug...
call gradlew.bat assembleDebug

if errorlevel 1 (
    echo ❌ Error en la compilación
    pause
    exit /b 1
)

echo.
echo ✅ ¡Compilación exitosa!
echo.
echo 📱 APK generado en:
echo    app\build\outputs\apk\debug\app-debug.apk
echo.
echo Para instalar en un dispositivo conectado:
echo    adb install app\build\outputs\apk\debug\app-debug.apk
echo.
echo Para compilar APK de Release (optimizado):
echo    gradlew.bat assembleRelease
echo.
pause
