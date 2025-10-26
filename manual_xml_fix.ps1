# ============================================
# Manueller XML-Fixer (falls Hauptskript nicht funktioniert)
# ============================================

$proj = "C:\Users\newwo\Desktop\GPT\Cloude\IronReignPro"

Write-Host "Manuelle XML-Reparatur" -ForegroundColor Cyan
Write-Host "======================" -ForegroundColor Cyan
Write-Host ""

# Funktion zum Säubern einer XML-Datei
function Fix-XmlFile {
    param($FilePath)
    
    Write-Host "Bearbeite: $FilePath" -ForegroundColor Yellow
    
    # Datei lesen
    $content = Get-Content $FilePath -Raw
    
    # Erste 10 Bytes anzeigen (zum Debuggen)
    $bytes = [System.IO.File]::ReadAllBytes($FilePath) | Select-Object -First 10
    Write-Host "  Erste Bytes: $($bytes -join ' ')" -ForegroundColor Gray
    
    # BOM entfernen (UTF-8 BOM = 239 187 191)
    $content = $content -replace '^\uFEFF', ''
    
    # Führende Leerzeichen/Tabs/Newlines vor dem ersten < entfernen
    $content = $content -replace '^\s+(?=<)', ''
    
    # Trailing Whitespace am Ende entfernen
    $content = $content.TrimEnd()
    
    # UTF-8 ohne BOM schreiben
    $enc = New-Object System.Text.UTF8Encoding($false)
    [System.IO.File]::WriteAllText($FilePath, $content, $enc)
    
    # Validierung
    $newBytes = [System.IO.File]::ReadAllBytes($FilePath) | Select-Object -First 10
    Write-Host "  Neue Bytes: $($newBytes -join ' ')" -ForegroundColor Gray
    
    if ($newBytes[0] -eq 60) {  # 60 = '<'
        Write-Host "  ✓ OK - Datei beginnt mit '<'" -ForegroundColor Green
    } else {
        Write-Host "  ✗ FEHLER - Datei beginnt NICHT mit '<'" -ForegroundColor Red
    }
    Write-Host ""
}

# Alle XML-Dateien finden und reparieren
Write-Host "Suche XML-Dateien..." -ForegroundColor Yellow
Write-Host ""

$xmlFiles = Get-ChildItem -Path "$proj\app\src\main\res" -Recurse -Filter *.xml

Write-Host "Gefunden: $($xmlFiles.Count) XML-Dateien" -ForegroundColor Cyan
Write-Host ""

foreach ($file in $xmlFiles) {
    Fix-XmlFile -FilePath $file.FullName
}

Write-Host "=====================================" -ForegroundColor Green
Write-Host "Alle XML-Dateien wurden repariert!" -ForegroundColor Green
Write-Host "=====================================" -ForegroundColor Green
Write-Host ""
Write-Host "Nächster Schritt: Build starten mit:" -ForegroundColor Yellow
Write-Host "  cd $proj" -ForegroundColor White
Write-Host "  .\gradlew.bat :app:assembleDebug" -ForegroundColor White
