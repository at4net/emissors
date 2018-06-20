
SCSP_COMMONS_VERSIO=`cat versio.txt`

echo ]]$SCSP_COMMONS_VERSIO[[

zip -r scsp-commons$1-$SCSP_COMMONS_VERSIO.zip scripts versio.txt utils/target/scsp-commons.jar  -x "**/.git**" -x "**/.svn**"  -x "scripts/templates/**" -x "scripts/pom.xml"
