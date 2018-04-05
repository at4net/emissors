@echo off
@echo
type help.txt
@echo

cmd /C mvn -DskipTests %* install

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED SCSP_COMMONS_DEPLOY_DIR (
      for /f "tokens=* delims=" %%x in (versio.txt) do set SCSP_COMMONS_VERSIO=%%x
	  @echo on
	  echo --------- COPIANT JAR %SCSP_COMMONS_VERSIO% ---------

	  xcopy /Y utils\target\scsp-commons.jar %SCSP_COMMONS_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn SCSP_COMMONS_DEPLOY_DIR apuntant al
	  echo    directori de lib del projecte  i automaticament s'hi copiara
	  echo    el jar generat.
	  echo  =================================================================
	)

)
