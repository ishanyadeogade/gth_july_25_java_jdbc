
@echo off
setlocal EnableExtensions
title Java JDBC GitHub Upload

REM ==========================================
REM CONFIGURATION
REM ==========================================
set "PROJECT=D:\Workspace\MyWorkspaceSTS\gth_july_25_java_jdbc"
set "OWNER=ishanyadeogade"
set "REPO=gth_july_25_java_jdbc"
set "REMOTE=https://github.com/ishanyadeogade/gth_july_25_java_jdbc.git"

echo.
echo ==========================================
echo       JAVA JDBC GITHUB UPLOADER
echo ==========================================
echo.

REM 1. Check required programs
where git >nul 2>&1
if errorlevel 1 (
    echo ERROR: Git is not installed or not in PATH.
    goto :fail
)

where gh >nul 2>&1
if errorlevel 1 (
    echo ERROR: GitHub CLI is not installed or not in PATH.
    goto :fail
)

REM 2. Check project directory
if not exist "%PROJECT%\." (
    echo ERROR: Project folder does not exist:
    echo %PROJECT%
    goto :fail
)

cd /d "%PROJECT%"
if errorlevel 1 (
    echo ERROR: Cannot enter project directory.
    goto :fail
)

REM 3. Check GitHub authentication
gh auth status >nul 2>&1
if errorlevel 1 (
    echo Please log in to GitHub.
    gh auth login
    if errorlevel 1 goto :fail
)

REM 4. Confirm the active GitHub account
for /f "delims=" %%A in ('gh api user --jq .login') do set "LOGIN=%%A"

if /i not "%LOGIN%"=="%OWNER%" (
    echo ERROR: Logged-in account is %LOGIN%.
    echo Expected account is %OWNER%.
    goto :fail
)

REM 5. Create Git metadata only if missing
if not exist ".git\." (
    echo Initializing Git...
    git init
    if errorlevel 1 goto :fail
)

REM 6. Create or verify .gitignore
if not exist ".gitignore" (
    echo WARNING: .gitignore is missing.
    echo Create and review .gitignore before uploading.
    goto :fail
)

REM 7. Stage files while respecting .gitignore
git add .
if errorlevel 1 goto :fail

REM 8. Commit only if changes are staged
git diff --cached --quiet
if errorlevel 2 goto :fail

git diff --cached --quiet
if errorlevel 1 (
    git commit -m "Update Java JDBC practice"
    if errorlevel 1 goto :fail
) else (
    echo No new changes to commit.
)

REM 9. Ensure main branch
git branch -M main
if errorlevel 1 goto :fail

REM 10. Check GitHub repository
gh repo view "%OWNER%/%REPO%" >nul 2>&1
if errorlevel 1 (
    echo Target repository not found. Checking before creation...
    gh repo create "%OWNER%/%REPO%" --public
    if errorlevel 1 (
        echo ERROR: Repository creation failed.
        echo It may already exist but be inaccessible.
        goto :fail
    )
) else (
    echo Existing GitHub repository found. Reusing it.
)

REM 11. Configure origin safely
git remote get-url origin >nul 2>&1
if errorlevel 1 (
    git remote add origin "%REMOTE%"
) else (
    git remote set-url origin "%REMOTE%"
)
if errorlevel 1 goto :fail

REM 12. Show destination before upload
echo.
echo Local project: %PROJECT%
echo GitHub target: %REMOTE%
echo.

REM 13. Push
git push -u origin main
if errorlevel 1 (
    echo ERROR: Push failed.
    echo Check the error above. No repository was deleted.
    goto :fail
)

echo.
echo SUCCESS: Java project uploaded.
echo %REMOTE%
echo.
pause
exit /b 0

:fail
echo.
echo FAILED: No success is being reported.
echo Review the error above before retrying.
echo Your local project and Git history have not been deleted.
echo.
pause
exit /b 1