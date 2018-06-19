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
import es.caib.scsp.utils.util.DataHandlers;
import es.caib.scsp.utils.xml.XmlManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBException;

/**
 *
 * @author gdeignacio
 */
public abstract class ProjectGenerator {
    /*
    protected ProjectGenerator(String projectFolderName, String artifactName){
        super();
        this.projectFolderName=projectFolderName;
        this.artifactName=artifactName;
    }*/
   
    protected static final Logger LOG=Logger.getLogger(ProjectGenerator.class.getName());;
    //private String projectFolderName;
    //private String artifactName;

    /*
    protected String getProjectFolderName() {
        return projectFolderName;
    }

    protected void setProjectFolderName(String projectFolderName) {
        this.projectFolderName = projectFolderName;
    }

    protected String getArtifactName() {
        return artifactName;
    }

    protected void setArtifactName(String artifactName) {
        this.artifactName = artifactName;
    }*/
    
    protected Path getMainProjectFolderPath(){
        
        String strExecutionPath = System.getProperty("user.dir");
        LOG.log(Level.INFO, "Directorio ejecucion: {0}", strExecutionPath);
        Path executionPath = FileSystems.getDefault().getPath(strExecutionPath);
        
        if (executionPath == null) return null;
        if (executionPath.getParent() == null) return null;
        if (executionPath.getParent() == executionPath.getRoot()) return executionPath.getRoot();
        
        Path projectFolderPath = executionPath.getParent().getParent();
        LOG.log(Level.INFO, "Directorio principal generación {0}", projectFolderPath);
        return projectFolderPath;
        
    }
    
    protected abstract File getProjectFolder();
    protected abstract Project getProject() throws JAXBException, IOException;
    protected abstract void generateContent(File projectFolder) throws IOException;
    
    private final void generatePomXmlDescriptor(Path p, Project project) throws IOException, JAXBException {

        File projectFolder = p.toFile();
        generatePomXmlDescriptor(projectFolder, project);

    }
    
    private final void generatePomXmlDescriptor(File projectFolder, Project project) throws IOException, JAXBException{
        
        File pom = new File(projectFolder, "pom.xml");
        pom.createNewFile();
        XmlManager<Project> manager = new XmlManager<Project>(Project.class);
        DataHandler dh = manager.generateXml(project);
        FileOutputStream fos = new FileOutputStream(pom);
        byte[] b = DataHandlers.dataHandlerToByteArray(dh);
        fos.write(b);
        fos.close();
        
    }
    
    public void generate() throws JAXBException, IOException {
        
        File projectFolder = getProjectFolder();
        generateContent(projectFolder);
        Project project = getProject();
        generatePomXmlDescriptor(projectFolder, project);
        
    }
    
    protected Map<String, List<String>> getResourcesMap() throws IOException {

        Map<String, List<String>> resourcesMapByFolder = new HashMap<String, List<String>>();
        LOG.log(Level.INFO, "Recuperando fuentes de esquemas");
        CodeSource src = XmlHelper.class.getProtectionDomain().getCodeSource();

        if (src == null) {
            LOG.log(Level.INFO, "No encontrado. Saliendo");
            return resourcesMapByFolder;
        }
        LOG.log(Level.INFO, "Fuentes en src : {0}", src);
        URL jar = src.getLocation();
        LOG.log(Level.INFO, "Fuentes en jar : {0}", jar);

        ZipInputStream zip = new ZipInputStream(jar.openStream());
        ZipEntry e;
        while ((e = zip.getNextEntry()) != null) {
            String name = e.getName();
            if (!name.startsWith("schemas") || "schemas/".equals(name)) {
                continue;
            }
            LOG.log(Level.INFO, "Procesando recurso : {0}", name);
            String[] arrayName = name.split("/");
            if (arrayName.length == 1) {
                LOG.log(Level.INFO, "{0} es un directorio vacio. No hacemos nada.", name);
                continue;
            }
            String key = arrayName[1];
            LOG.log(Level.INFO, "Procesando clave : {0}", key);
            if (e.isDirectory()) {
                LOG.log(Level.INFO, "{0} es un directorio. No hacemos nada.", name);
                continue;
            }
            String schema = arrayName[arrayName.length - 1];
            LOG.log(Level.INFO, "Procesando archivo : {0}", schema);
            List<String> schemas = (resourcesMapByFolder.containsKey(key)) ? resourcesMapByFolder.get(key) : new ArrayList<String>();
            schemas.add(schema);
            resourcesMapByFolder.put(key, schemas);
        }
        zip.close();
        LOG.log(Level.INFO, "Listando esquemas");
        for (String key : resourcesMapByFolder.keySet()) {
            LOG.log(Level.INFO, "Esquemas para : {0}", key);
            List<String> schemas = resourcesMapByFolder.get(key);
            LOG.log(Level.INFO, "{0}", schemas.toString());
        }
        
        return resourcesMapByFolder;
    }
    
    
}
