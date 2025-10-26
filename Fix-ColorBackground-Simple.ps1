# Fix-ColorBackground-Simple.ps1
Write-Host "=== IronReignPro - colorBackground Fehler beheben ===" -ForegroundColor Cyan
Write-Host ""

# Schritt 1: attrs.xml erstellen
Write-Host "[1/5] Erstelle attrs.xml..." -ForegroundColor Yellow
$attrsPath = "app\src\main\res\values\attrs.xml"
$attrsContent = @"
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <attr name="colorBackground" format="color" />
</resources>
"@

$attrsContent | Out-File -FilePath $attrsPath -Encoding UTF8
Write-Host "OK: attrs.xml erstellt" -ForegroundColor Green
Write-Host ""

# Schritt 2: colors.xml erstellen
Write-Host "[2/5] Erstelle colors.xml..." -ForegroundColor Yellow
$colorsPath = "app\src\main\res\values\colors.xml"
$colorsContent = @"
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="color_background">#FFFFFF</color>
    <color name="color_primary">#6200EE</color>
    <color name="color_primary_variant">#3700B3</color>
    <color name="color_on_surface_variant">#000000</color>
    <color name="color_surface">#FFFFFF</color>
</resources>
"@

$colorsContent | Out-File -FilePath $colorsPath -Encoding UTF8 -Force
Write-Host "OK: colors.xml erstellt" -ForegroundColor Green
Write-Host ""

# Schritt 3: themes.xml erstellen
Write-Host "[3/5] Erstelle themes.xml..." -ForegroundColor Yellow
$themesPath = "app\src\main\res\values\themes.xml"
$themesContent = @"
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="Theme.IronReignPro" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
        <item name="colorPrimary">@color/color_primary</item>
        <item name="colorPrimaryVariant">@color/color_primary_variant</item>
        <item name="colorBackground">@color/color_background</item>
        <item name="colorSurface">@color/color_surface</item>
        <item name="colorOnSurfaceVariant">@color/color_on_surface_variant</item>
    </style>
</resources>
"@

$themesContent | Out-File -FilePath $themesPath -Encoding UTF8 -Force
Write-Host "OK: themes.xml erstellt" -ForegroundColor Green
Write-Host ""

# Schritt 4: Build-Ordner bereinigen
Write-Host "[4/5] Bereinige Build-Ordner..." -ForegroundColor Yellow
if (Test-Path ".gradle") {
    Remove-Item -Path ".gradle" -Recurse -Force
    Write-Host "OK: .gradle geloescht" -ForegroundColor Green
}
if (Test-Path "app\build") {
    Remove-Item -Path "app\build" -Recurse -Force
    Write-Host "OK: app\build geloescht" -ForegroundColor Green
}
Write-Host ""

# Schritt 5: Projekt neu bauen
Write-Host "[5/5] Baue Projekt neu..." -ForegroundColor Yellow
Write-Host "Fuehre aus: .\gradlew.bat clean assembleDebug" -ForegroundColor Cyan
Write-Host ""
.\gradlew.bat clean assembleDebug

Write-Host ""
Write-Host "=== Fertig! ===" -ForegroundColor Green
Write-Host "APK Pfad: app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor Cyan
