#!/bin/bash

for i in "$@"
do
case $i in
    -app=*|--codapp=*)
    CODAPP="${i#*=}"
    shift # past argument=value
    ;;
    --*)
    CODAPP="${i#*--}"
    shift # past argument with no value
    ;;
    *)
          # unknown option
    ;;
esac
done

LOWERCODAPP="${CODAPP,,}"
CUSTOM=$LOWERCODAPP-pinbal

if [ -z "$LOWERCODAPP" ]
then
      echo "app value is NULL"
      exit 1
else
      echo Setting CODAPP to $CODAPP
      echo Setting codapp-pinbal to $CUSTOM
fi

cp -avr codapp-pinbal $CUSTOM

mv $CUSTOM/codapp-pinbal-ws-client $CUSTOM/$CUSTOM-ws-client

poms=( "./$CUSTOM/pom.xml" "./$CUSTOM/scripts/pom.xml" "./$CUSTOM/$CUSTOM-ws-client/pom.xml" )

for pom in "${poms[@]}"
do
      echo Modifying $pom
      sed -i "s/codapp/$LOWERCODAPP/g" $pom
      sed -i "s/CODAPP/$CODAPP/g" $pom
done
exit 0