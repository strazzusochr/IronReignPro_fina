# Fix-Build-Gradle-KTS.ps1
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   FIX BUILD.GRADLE.KTS (Kotlin DSL)" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

$buildGradleKts = "app\build.gradle.kts"

if (Test-Path $buildGradleKts) {
    Write-Host "Lese build.gradle.kts..." -ForegroundColor Yellow
    $content = Get-Content $buildGradleKts -Raw
    
    # Entferne die falsch hinzugefuegten Zeilen (Groovy Syntax)
    Write-Host "Entferne falsche Groovy Syntax..." -ForegroundColor Yellow
    $content = $content -replace "    implementation 'androidx.navigation:navigation-fragment-ktx:2.7.5'", ""
    $content = $content -replace "    implementation 'androidx.navigation:navigation-ui-ktx:2.7.5'", ""
    
    # Pruefe ob Navigation schon korrekt vorhanden ist
    if ($content -notmatch 'implementation\("androidx.navigation:navigation-fragment') {
        Write-Host "Fuege Navigation mit KORREKTER Kotlin Syntax hinzu..." -ForegroundColor Yellow
        
        # Finde dependencies Block
        if ($content -match "dependencies\s*\{") {
            $newDeps = @"

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
"@
            # Fuege Navigation nach dem dependencies { ein
            $content = $content -replace "(dependencies\s*\{)", "`$1$newDeps"
            
            # Speichere
            $content | Out-File -FilePath $buildGradleKts -Encoding UTF8 -Force
            Write-Host "OK: Navigation Dependencies mit Kotlin Syntax hinzugefuegt!" -ForegroundColor Green
        }
    } else {
        Write-Host "OK: Navigation bereits korrekt vorhanden" -ForegroundColor Green
    }
    
    Write-Host ""
    Write-Host "Bereinige Build-Cache..." -ForegroundColor Yellow
    if (Test-Path ".gradle") {
        Remove-Item -Path ".gradle" -Recurse -Force
        Write-Host "OK: .gradle geloescht" -ForegroundColor Green
    }
    if (Test-Path "app\build") {
        Remove-Item -Path "app\build" -Recurse -Force
        Write-Host "OK: app\build geloescht" -ForegroundColor Green
    }
    
    Write-Host ""
    Write-Host "Starte Build..." -ForegroundColor Yellow
    Write-Host ""
    .\gradlew.bat clean assembleDebug
    
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "           FERTIG!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "APK: app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor Cyan
} else {
    Write-Host "FEHLER: app\build.gradle.kts nicht gefunden!" -ForegroundColor Red
}
