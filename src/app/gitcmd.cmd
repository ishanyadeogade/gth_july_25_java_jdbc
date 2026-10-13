
@echo off
setlocal
title Java JDBC GitHub Uploader

echo ==========================================
echo       JAVA JDBC GITHUB UPLOADER
echo ==========================================
echo.

REM Move to the folder containing this script
cd /d "%~dp0"

REM GitHub repository details
set "REPO=ishanyadeogade/gth_july_25_java_jdbc"
set "REPO_URL=https://github.com/%REPO%.git"

REM Check Git installation
git --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Git is not installed or not in PATH.
    goto FAILED
)

REM Check GitHub CLI installation
gh --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: GitHub CLI is not installed.
    goto FAILED
)

REM Check GitHub login
gh auth status
if errorlevel 1 (
    echo ERROR: Please log in to GitHub first.
    echo Run: gh auth login
    goto FAILED
)

REM Verify project folder
if not exist "src" (
    echo WARNING: No src folder found in this directory.
    echo Make sure this script is in your Java project root.
    goto FAILED
)

REM Create .gitignore if missing
if not exist ".gitignore" (
    type nul > ".gitignore"
)

REM Ignore generated files and upload-script artifacts
findstr /x /l /c:"*.class" .gitignore >nul || echo *.class>>.gitignore
findstr /x /l /c:"bin/" .gitignore >nul || echo bin/>>.gitignore
findstr /x /l /c:"target/" .gitignore >nul || echo target/>>.gitignore
findstr /x /l /c:"build/" .gitignore >nul || echo build/>>.gitignore
findstr /x /l /c:"out/" .gitignore >nul || echo out/>>.gitignore
findstr /x /l /c:"/gitcmd.cmd" .gitignore >nul || echo /gitcmd.cmd>>.gitignore
findstr /x /l /c:"/$null" .gitignore >nul || echo /$null>>.gitignore

REM Initialize Git if needed
if not exist ".git" (
    git init
    if errorlevel 1 goto FAILED
)

REM Use main as the branch name
git branch -M main
if errorlevel 1 goto FAILED

REM Remove the accidental root $null file from Git tracking
REM Keep other project files unchanged
git rm --cached --ignore-unmatch -- "$null" >nul 2>&1

REM Stop tracking this uploader script, but keep it on your PC
git rm --cached --ignore-unmatch -- gitcmd.cmd >nul 2>&1

REM Create the GitHub repository if it does not exist
gh repo view "%REPO%" >nul 2>&1
if errorlevel 1 (
    echo Creating GitHub repository...
    gh repo create "%REPO%" --public
    if errorlevel 1 (
        echo ERROR: Could not create the repository.
        goto FAILED
    )
)

REM Configure the origin remote
git remote get-url origin >nul 2>&1
if errorlevel 1 (
    git remote add origin "%REPO_URL%"
) else (
    git remote set-url origin "%REPO_URL%"
)
if errorlevel 1 goto FAILED

REM Stage project changes
git add -A
if errorlevel 1 goto FAILED

REM Commit only when staged changes exist
git diff --cached --quiet
if errorlevel 1 (
    git commit -m "Update Java JDBC practice"
    if errorlevel 1 goto FAILED
) else (
    echo No new changes to commit.
)

REM Upload the main branch
git push -u origin main
if errorlevel 1 goto FAILED

echo.
echo ==========================================
echo       UPLOAD SUCCESSFUL!
echo ==========================================
echo.
echo Repository:
echo https://github.com/%REPO%
echo.
echo Open the repository to verify your files.
echo.
pause
exit /b 0

:FAILED
echo.
echo ==========================================
echo       UPLOAD FAILED
echo ==========================================
echo.
echo Read the error above and correct it.
echo Your project files have not been intentionally deleted.
echo.
pause
exit /b 1
