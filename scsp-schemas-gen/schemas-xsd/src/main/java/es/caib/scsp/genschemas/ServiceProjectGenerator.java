/*
 * Copyright 2018 gdeignacio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.caib.scsp.genschemas;

import es.caib.pinbal.scsp.XmlHelper;
import es.caib.scsp.pom._4_0.Project;
import es.caib.scsp.pom._4_0.ProjectUtils;
import es.caib.scsp.xml.ns.jaxb.XjbBindings;
import es.caib.scsp.xml.ns.jaxb.XjbBindingsUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author gdeignacio
 */
public class ServiceProjectGenerator extends ProjectGenerator {
    
    public ServiceProjectGenerator(String serviceKey, List<String> resources) {
        super();
        this.serviceKey = serviceKey;
        this.projectFolderName = SERVICE_PROJECT_FOLDER_NAME.replace("service", serviceKey);
        this.artifactName = SERVICE_ARTIFACT_NAME.replace("service", serviceKey);
        this.resourcesMap.put(serviceKey, resources);
    }
    
    private final String serviceKey;
    private final String projectFolderName;
    private final String artifactName;
    
    @Override
    protected File getProjectFolder() {
        
        Path mainProjectFolderPath = getMainProjectFolderPath();
        LOG.log(Level.INFO, "Generando proyecto " + artifactName + " en : {0}", mainProjectFolderPath.toString().concat(projectFolderName));
        File mainProjectFolderFile = new File(mainProjectFolderPath.toFile(), MAIN_PROJECT_FOLDER_NAME);
        File projectFolderFile = new File(mainProjectFolderFile, projectFolderName);
        LOG.log(Level.INFO, "Generando proyecto " + projectFolderName + " con carpeta : {0}", projectFolderFile);
        return projectFolderFile;
        
    }

    @Override
    protected Project getProject() throws IOException, JAXBException {
        Project serviceProject = ProjectUtils.getServiceProject(new ArrayList<String>(resourcesMap.keySet()));
        return ProjectUtils.getNamedServiceProject(serviceKey, serviceProject);
    }
    
    
    private static final String MAIN_JAVA = "src/main/java";
    private static final String TEST_JAVA = "src/test/java";
    private static final String MAIN_RESOURCES = "src/main/resources";
    private static final String JAXB_RESOURCES = "jaxb";
    private static final String JAXB_SCHEMAS = "schemas";
    private static final String JAXB_BINDINGS = "bindings";
    
    
    @Override
    protected void generateContent(File projectFolder) throws JAXBException, IOException {
        
        projectFolder.mkdirs();

        LOG.log(Level.INFO, "Distribuyendo esquemas");
        
        for (Map.Entry<String, List<String>> pair : resourcesMap.entrySet()){
        
        //for (String key : resourcesMap.keySet()) {
            LOG.log(Level.INFO, "Esquemas para : {0}", pair.getKey());
            String fld = MAIN_RESOURCES + "/" + JAXB_RESOURCES + "/" + pair.getKey();
            LOG.log(Level.INFO, "Creando carpeta : {0}", fld);
            File subFolder = new File(projectFolder, fld);
            File peticion = new File(subFolder, "peticion");
            File respuesta = new File(subFolder, "respuesta");
            if (subFolder.mkdirs()) {
                LOG.log(Level.INFO, "Carpeta {0} creada ", subFolder.getCanonicalPath());
            }
            if (peticion.mkdirs()) {
                LOG.log(Level.INFO, "Carpeta {0} creada ", peticion.getCanonicalPath());
            }
            if (respuesta.mkdirs()) {
                LOG.log(Level.INFO, "Carpeta {0} creada ", respuesta.getCanonicalPath());
            }

            List<String> schemas = pair.getValue(); //resourcesMap.get(pair.getKey());
            Map<String, List<String>> xsdFolderMap = new HashMap<String, List<String>>();
            for (String schema : schemas) {
                if (!schema.endsWith(".xsd")) {
                    LOG.log(Level.INFO, "Extension de esquema distinta de xsd para {0}", schema);
                    continue;
                }
                if (schema.startsWith("soap")) {
                    LOG.log(Level.INFO, "Envoltorio soap para {0}", schema);
                    continue;
                }
                String[] redundantes = new String[]{
                    "peticion_datos-especificos.xsd",
                    "respuesta_datos-especificos.xsd",
                    "xmldsig.xsd"};
                if (Arrays.asList(redundantes).contains(schema)) {
                    LOG.log(Level.INFO, "{0} redundante", schema);
                    continue;
                }

                String schemaFolder = null;
                String[] schemasPeticion = new String[]{"confirmacion-peticion.xsd",
                    "datos-especificos-ent.xsd", "datos-especificos-entrada.xsd",
                    "peticion_datos-especificos.xsd", "peticion.xsd"};
                String[] schemasRespuesta = new String[]{"solicitud-respuesta.xsd",
                    "datos-especificos-sal.xsd", "datos-especificos-salida.xsd",
                    "respuesta_datos-especificos.xsd", "respuesta.xsd"};

                schemaFolder = (Arrays.asList(schemasPeticion).contains(schema)) ? "peticion" : schemaFolder;
                schemaFolder = (Arrays.asList(schemasRespuesta).contains(schema)) ? "respuesta" : schemaFolder;

                for (String s : new String[]{"peticion", "respuesta"}) {
                    if (schemaFolder == null || s.equals(schemaFolder)) {
                        List<String> xsds = (xsdFolderMap.get(s) != null) ? xsdFolderMap.get(s) : new ArrayList<String>();
                        xsds.add(schema);
                        xsdFolderMap.put(s, xsds);
                        File schemaFile = new File(subFolder, s.concat("/").concat(schema));
                        schemaFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(schemaFile);
                        InputStream is = XmlHelper.class.getClassLoader().getResourceAsStream("schemas/".concat(pair.getKey()).concat("/").concat(schema));
                        IOUtils.copy(is, fos);
                        is.close();
                        fos.close();

                    }
                }

            }

            for (File f : new File[]{peticion, respuesta}) {
                String name = f.getName();
                XjbBindings xjbBindings = XjbBindingsUtils.getXjbBindings(pair.getKey(), name, xsdFolderMap.get(name));
                XjbBindingsUtils.generateXjbBindingsXmlDescriptor(f, xjbBindings);
            }

        }

  
        
        
    }
    
}
