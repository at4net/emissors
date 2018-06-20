
for /f "tokens=* delims=" %%x in (versio.txt) do set SCSP_COMMONS_VERSIO=%%x

zip -r scscp-commons%1-%SCSP_COMMONS_VERSIO%.zip scripts versio.txt utils/target/scsp-commons.jar  -x "**/.git**" -x "**/.svn**"  -x "./scripts/pom.xml" -x "./scripts/templates/**"  -x "./scripts/templates/"
