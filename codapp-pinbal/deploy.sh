#!/usr/bin/env bash

cat help.txt

env mvn $@ -DskipTests install

if [ $? == 0 ]; then
  if [ "$SCSP_COMMONS_DEPLOY_DIR" == "" ];  then
    echo  =================================================================
    echo    Definex la variable d\'entorn SCSP_COMMONS_DEPLOY_DIR apuntant al
    echo    directori lib del projecte  i automaticament s\'hi copiara
    echo    el jar generat.
    echo  =================================================================
  else
    if [ -f 'versio.txt' ]; then
	echo --------- COPIANT JAR `cat versio.txt` ---------
    else
	echo --------- COPIANT JAR ---------
    fi
    if [ -f './utils/target/scsp-commons.jar' ]; then
      cp ./utils/target/scsp-commons.jar $SCSP_COMMONS_DEPLOY_DIR
    else
      echo NO S\'HA TROBAT scsp-commons.jar!
    fi
  fi
fi
