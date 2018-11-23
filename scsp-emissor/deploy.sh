#!/usr/bin/env bash

cat help.txt

env mvn $@ -DskipTests install

if [ $? == 0 ]; then
  if [ "$SCSP_EMISSOR_DEPLOY_DIR" == "" ];  then
    echo  =================================================================
    echo    Definex la variable d\'entorn SCSP_EMISSOR_DEPLOY_DIR apuntant al
    echo    directori lib del projecte  i automaticament s\'hi copiara
    echo    el jar generat.
    echo  =================================================================
  else
    if [ -f 'versio.txt' ]; then
	echo --------- COPIANT JAR `cat versio.txt` ---------
    else
	echo --------- COPIANT JAR ---------
    fi
    if [ -f './utils/target/scsp-emissor.jar' ]; then
      cp ./utils/target/scsp-emissor.jar $SCSP_EMISSOR_DEPLOY_DIR
    else
      echo NO S\'HA TROBAT scsp-emissor.jar!
    fi
  fi
fi
