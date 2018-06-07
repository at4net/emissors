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
import es.caib.scsp.genschemas.managers.XjbBindingsXmlManager;
import es.caib.scsp.pom._4_0.Project;
import es.caib.scsp.genschemas.managers.ProjectXmlManager;
import es.caib.scsp.pom._4_0.ProjectUtils;
import es.caib.scsp.utils.util.DataHandlers;
import es.caib.scsp.xml.ns.jaxb.XjbBindings;
import es.caib.scsp.xml.ns.jaxb.XjbBindingsUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author gdeignacio
 */
public class GenericProject {
    
    
    protected GenericProject(Logger log, String projectFolderName, String artifactName){
        super();
        this.log=log;
        this.projectFolderName=projectFolderName;
        this.artifactName=artifactName;
    }
    
    
    private Logger log;
    private String projectFolderName;
    private String artifactName;

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }
    
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
    }
    
    protected abstract File getProjectFolder();
    
    protected abstract void projectGeneration(Project project, Path path);
    
    private void projectGeneration(Path projectFolderPath) throws JAXBException, IOException {

        log.log(Level.INFO, "Generando proyecto " + PROJECT_FOLDER_NAME + " en : {0}", projectFolderPath);

        File projectFolderFile = new File(projectFolderPath.toFile(), PROJECT_FOLDER_NAME);

        log.log(Level.INFO, "Generando proyecto " + PROJECT_FOLDER_NAME + " con carpeta : {0}", projectFolderFile);
        
        for (String fld: new String[]{MAIN_JAVA, TEST_JAVA, MAIN_RESOURCES}){
            log.log(Level.INFO, "Creando carpeta : {0}", fld);
            File subFolder = new File(projectFolderFile, fld);
            if (subFolder.mkdirs()){
                log.log(Level.INFO, "Carpeta {0} creada ", subFolder.getCanonicalPath());
            }
        }
        
        log.log(Level.INFO, "Recuperando fuentes de esquemas");
        CodeSource src = XmlHelper.class.getProtectionDomain().getCodeSource();
            
        if (src == null) {
            log.log(Level.INFO, "No encontrado. Saliendo");
            return;
        }
        
        log.log(Level.INFO, "Fuentes en src : {0}", src);
        URL jar = src.getLocation();
        log.log(Level.INFO, "Fuentes en jar : {0}", jar);
        
        ZipInputStream zip = new ZipInputStream(jar.openStream());
        Map<String,List<String>> resourcesMap = new HashMap<String,List<String>>();
        ZipEntry e;
        while ((e=zip.getNextEntry())!=null) {
            String name = e.getName();
            if (!name.startsWith("schemas") || "schemas/".equals(name)) {
                continue;
            }
            log.log(Level.INFO, "Procesando recurso : {0}", name);
            String[] arrayName = name.split("/");
            if (arrayName.length == 1) {
                log.log(Level.INFO, "{0} es un directorio vacio. No hacemos nada.", name);
                continue;
            }
            String key = arrayName[1];
            log.log(Level.INFO, "Procesando clave : {0}", key);
            if (e.isDirectory()) {
                log.log(Level.INFO, "{0} es un directorio. No hacemos nada.", name);
                continue;
            }
            String schema = arrayName[arrayName.length - 1];
            log.log(Level.INFO, "Procesando archivo : {0}", schema);
            List<String> schemas = (resourcesMap.containsKey(key)) ? resourcesMap.get(key) : new ArrayList<String>();
            schemas.add(schema);
            resourcesMap.put(key, schemas);
        }
        zip.close();
        
        log.log(Level.INFO, "Listando esquemas");
        for (String key:resourcesMap.keySet()){
            log.log(Level.INFO, "Esquemas para : {0}", key);
            List<String> schemas = resourcesMap.get(key);
            log.log(Level.INFO, "{0}", schemas.toString());
        }
        
        
        log.log(Level.INFO, "Distribuyendo esquemas");
        for (String key:resourcesMap.keySet()){
            log.log(Level.INFO, "Esquemas para : {0}", key);
            String fld = MAIN_RESOURCES + "/" + JAXB_RESOURCES + "/" + key;
            log.log(Level.INFO, "Creando carpeta : {0}", fld);
            File subFolder = new File(projectFolderFile, fld);
            File peticion = new File(subFolder, "peticion");
            File respuesta = new File(subFolder, "respuesta");
            if (subFolder.mkdirs()){
                log.log(Level.INFO, "Carpeta {0} creada ", subFolder.getCanonicalPath());
            }
            if (peticion.mkdirs()){
                log.log(Level.INFO, "Carpeta {0} creada ", peticion.getCanonicalPath());
            }
            if (respuesta.mkdirs()){
                log.log(Level.INFO, "Carpeta {0} creada ", respuesta.getCanonicalPath());
            }
            
            List<String> schemas = resourcesMap.get(key);
            Map<String, List<String>> xsdFolderMap = new HashMap<String,List<String>>();
            for (String schema:schemas){
                if (!schema.endsWith(".xsd")){
                    log.log(Level.INFO, "Extension de esquema distinta de xsd para {0}", schema);
                    continue;
                }
                if (schema.startsWith("soap")){
                    log.log(Level.INFO, "Envoltorio soap para {0}", schema);
                    continue;
                }
                String[] redundantes = new String[]{
                    "peticion_datos-especificos.xsd",
                    "respuesta_datos-especificos.xsd",
                    "xmldsig.xsd"};
                if  (Arrays.asList(redundantes).contains(schema)){
                    log.log(Level.INFO, "{0} redundante", schema);
                    continue;
                }
                
                
                String schemaFolder = null;
                String[] schemasPeticion = new String[]{"confirmacion-peticion.xsd",
                    "datos-especificos-ent.xsd","datos-especificos-entrada.xsd",
                    "peticion_datos-especificos.xsd","peticion.xsd"};
                String[] schemasRespuesta = new String[]{"solicitud-respuesta.xsd",
                    "datos-especificos-sal.xsd","datos-especificos-salida.xsd",
                    "respuesta_datos-especificos.xsd","respuesta.xsd"};
                
                schemaFolder = (Arrays.asList(schemasPeticion).contains(schema))?"peticion":schemaFolder;
                schemaFolder = (Arrays.asList(schemasRespuesta).contains(schema))?"respuesta":schemaFolder;
                
                for (String s:new String[]{"peticion","respuesta"}){
                    if (schemaFolder==null || s.equals(schemaFolder)){
                        List<String> xsds= (xsdFolderMap.get(s)!=null)?xsdFolderMap.get(s):new ArrayList<String>();
                        xsds.add(schema);
                        xsdFolderMap.put(s, xsds);
                        File schemaFile = new File(subFolder, s.concat("/").concat(schema));
                        schemaFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(schemaFile);
                        InputStream is = XmlHelper.class.getClassLoader().getResourceAsStream("schemas/".concat(key).concat("/").concat(schema));
                        IOUtils.copy(is, fos);
                        is.close();
                        fos.close();
                        
                    }
                }
                
            }
            
            for (File f:new File[]{peticion, respuesta}){
                String name = f.getName();
                XjbBindings xjbBindings = XjbBindingsUtils.getXjbBindings(key, name, xsdFolderMap);
                setXjbBindingsXmlDescriptor(f, xjbBindings);
            }
            
        }
        
        
        Project project = ProjectUtils.getProject(new ArrayList<String>(resourcesMap.keySet()));
        setPomXmlDescriptor(projectFolderFile, project);
        
 
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void generate() throws JAXBException, IOException{

        String execStrPath = System.getProperty("user.dir");
        
        log.log(Level.INFO, "Directorio ejecucion: {0}", execStrPath);
        
        Path projectFolderPath = FileSystems.getDefault().getPath(execStrPath);
        
        if (projectFolderPath == null) return;
        if (projectFolderPath.getParent() == null) return;
        
        projectFolderPath = projectFolderPath.getParent();
        projectFolderPath = (projectFolderPath!=projectFolderPath.getRoot())?projectFolderPath.getParent():projectFolderPath.getRoot();
        
        log.log(Level.INFO, "Directorio generación {0}", projectFolderPath);
        
        projectGeneration(projectFolderPath);

    }
    
    
   
    
    /*
    protected static final Logger log = Logger.getLogger(GenericProject.class.getName());
    private static final GenericProject gen = new GenericProject();
    
    private GenericProject(){
        super();
    }
 
    public static GenericProject getGen(){
        return gen;
    }*/
    
    
    
    private static final String PROJECT_FOLDER_NAME = "scsp-schemas-xsd-all";
    private static final String ARTIFACT_NAME = "scsp-schemas-xsd-all";
    
   
    /*
    private void setXmlDescriptor(File f, String descriptorName, Object obj) throws JAXBException, FileNotFoundException, IOException {

        f.mkdirs();
        
        File descriptorxml = new File(f, descriptorName);
        
        descriptorxml.createNewFile();
        
        log.log(Level.INFO, "Creando descriptor: {0}", descriptorxml.getAbsolutePath());
        
        //XmlJAXBManager manager = new XmlJAXBManager(clazz.getClass());
        
        //ProjectXmlManager manager = new ProjectXmlManager();
        
        XmlManager<Project> manager = new XmlManager<Project>(Project.class);
        
        log.log(Level.INFO, "Creando descriptor: {0}", manager);
        
        DataHandler dh = manager.generateXml((Project)obj);

        FileOutputStream fos = new FileOutputStream(descriptorxml);

        byte[] b = DataHandlers.dataHandlerToByteArray(dh);

        fos.write(b);

        fos.close();
    }
    
    
    private void setPomXmlDescriptor(File f, Project project) throws JAXBException, FileNotFoundException, IOException {

        setXmlDescriptor(f, "pom.xml", project);
    }
    
    */
    
    private void setPomXmlDescriptor(File f, Project project) throws JAXBException, FileNotFoundException, IOException {

        f.mkdirs();
        
        File pomxml = new File(f, "pom.xml");
        
        pomxml.createNewFile();
        
        log.log(Level.INFO, "Creando pom: {0}", pomxml.getAbsolutePath());
        
        ProjectXmlManager manager = new ProjectXmlManager();
        
        DataHandler dh = manager.generateXml(project);

        FileOutputStream fos = new FileOutputStream(pomxml);

        byte[] b = DataHandlers.dataHandlerToByteArray(dh);

        fos.write(b);

        fos.close();
    }
    

    
    private void setXjbBindingsXmlDescriptor(File f, XjbBindings xjbBindings) throws JAXBException, FileNotFoundException, IOException{
        
        f.mkdirs();
        
        File bindingsxml = new File(f, "bindings.xjb");
        
        bindingsxml.createNewFile();
        
        XjbBindingsXmlManager manager = new XjbBindingsXmlManager();
        
        DataHandler dh = manager.generateXml(xjbBindings);
        
        FileOutputStream fos = new FileOutputStream(bindingsxml);
        
        byte[] b = DataHandlers.dataHandlerToByteArray(dh);
        
        fos.write(b);
    
        fos.close();
    }
    
    private static final String MAIN_JAVA = "src/main/java";
    private static final String TEST_JAVA = "src/test/java";
    private static final String MAIN_RESOURCES = "src/main/resources";
    private static final String JAXB_RESOURCES = "jaxb";
    private static final String JAXB_SCHEMAS = "schemas";
    private static final String JAXB_BINDINGS = "bindings";
    
    private void projectGeneration(Path projectFolderPath) throws JAXBException, IOException {

        log.log(Level.INFO, "Generando proyecto " + PROJECT_FOLDER_NAME + " en : {0}", projectFolderPath);

        File projectFolderFile = new File(projectFolderPath.toFile(), PROJECT_FOLDER_NAME);

        log.log(Level.INFO, "Generando proyecto " + PROJECT_FOLDER_NAME + " con carpeta : {0}", projectFolderFile);
        
        for (String fld: new String[]{MAIN_JAVA, TEST_JAVA, MAIN_RESOURCES}){
            log.log(Level.INFO, "Creando carpeta : {0}", fld);
            File subFolder = new File(projectFolderFile, fld);
            if (subFolder.mkdirs()){
                log.log(Level.INFO, "Carpeta {0} creada ", subFolder.getCanonicalPath());
            }
        }
        
        log.log(Level.INFO, "Recuperando fuentes de esquemas");
        CodeSource src = XmlHelper.class.getProtectionDomain().getCodeSource();
            
        if (src == null) {
            log.log(Level.INFO, "No encontrado. Saliendo");
            return;
        }
        
        log.log(Level.INFO, "Fuentes en src : {0}", src);
        URL jar = src.getLocation();
        log.log(Level.INFO, "Fuentes en jar : {0}", jar);
        
        ZipInputStream zip = new ZipInputStream(jar.openStream());
        Map<String,List<String>> resourcesMap = new HashMap<String,List<String>>();
        ZipEntry e;
        while ((e=zip.getNextEntry())!=null) {
            String name = e.getName();
            if (!name.startsWith("schemas") || "schemas/".equals(name)) {
                continue;
            }
            log.log(Level.INFO, "Procesando recurso : {0}", name);
            String[] arrayName = name.split("/");
            if (arrayName.length == 1) {
                log.log(Level.INFO, "{0} es un directorio vacio. No hacemos nada.", name);
                continue;
            }
            String key = arrayName[1];
            log.log(Level.INFO, "Procesando clave : {0}", key);
            if (e.isDirectory()) {
                log.log(Level.INFO, "{0} es un directorio. No hacemos nada.", name);
                continue;
            }
            String schema = arrayName[arrayName.length - 1];
            log.log(Level.INFO, "Procesando archivo : {0}", schema);
            List<String> schemas = (resourcesMap.containsKey(key)) ? resourcesMap.get(key) : new ArrayList<String>();
            schemas.add(schema);
            resourcesMap.put(key, schemas);
        }
        zip.close();
        
        log.log(Level.INFO, "Listando esquemas");
        for (String key:resourcesMap.keySet()){
            log.log(Level.INFO, "Esquemas para : {0}", key);
            List<String> schemas = resourcesMap.get(key);
            log.log(Level.INFO, "{0}", schemas.toString());
        }
        
        
        log.log(Level.INFO, "Distribuyendo esquemas");
        for (String key:resourcesMap.keySet()){
            log.log(Level.INFO, "Esquemas para : {0}", key);
            String fld = MAIN_RESOURCES + "/" + JAXB_RESOURCES + "/" + key;
            log.log(Level.INFO, "Creando carpeta : {0}", fld);
            File subFolder = new File(projectFolderFile, fld);
            File peticion = new File(subFolder, "peticion");
            File respuesta = new File(subFolder, "respuesta");
            if (subFolder.mkdirs()){
                log.log(Level.INFO, "Carpeta {0} creada ", subFolder.getCanonicalPath());
            }
            if (peticion.mkdirs()){
                log.log(Level.INFO, "Carpeta {0} creada ", peticion.getCanonicalPath());
            }
            if (respuesta.mkdirs()){
                log.log(Level.INFO, "Carpeta {0} creada ", respuesta.getCanonicalPath());
            }
            
            List<String> schemas = resourcesMap.get(key);
            Map<String, List<String>> xsdFolderMap = new HashMap<String,List<String>>();
            for (String schema:schemas){
                if (!schema.endsWith(".xsd")){
                    log.log(Level.INFO, "Extension de esquema distinta de xsd para {0}", schema);
                    continue;
                }
                if (schema.startsWith("soap")){
                    log.log(Level.INFO, "Envoltorio soap para {0}", schema);
                    continue;
                }
                String[] redundantes = new String[]{
                    "peticion_datos-especificos.xsd",
                    "respuesta_datos-especificos.xsd",
                    "xmldsig.xsd"};
                if  (Arrays.asList(redundantes).contains(schema)){
                    log.log(Level.INFO, "{0} redundante", schema);
                    continue;
                }
                
                
                String schemaFolder = null;
                String[] schemasPeticion = new String[]{"confirmacion-peticion.xsd",
                    "datos-especificos-ent.xsd","datos-especificos-entrada.xsd",
                    "peticion_datos-especificos.xsd","peticion.xsd"};
                String[] schemasRespuesta = new String[]{"solicitud-respuesta.xsd",
                    "datos-especificos-sal.xsd","datos-especificos-salida.xsd",
                    "respuesta_datos-especificos.xsd","respuesta.xsd"};
                
                schemaFolder = (Arrays.asList(schemasPeticion).contains(schema))?"peticion":schemaFolder;
                schemaFolder = (Arrays.asList(schemasRespuesta).contains(schema))?"respuesta":schemaFolder;
                
                for (String s:new String[]{"peticion","respuesta"}){
                    if (schemaFolder==null || s.equals(schemaFolder)){
                        List<String> xsds= (xsdFolderMap.get(s)!=null)?xsdFolderMap.get(s):new ArrayList<String>();
                        xsds.add(schema);
                        xsdFolderMap.put(s, xsds);
                        File schemaFile = new File(subFolder, s.concat("/").concat(schema));
                        schemaFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(schemaFile);
                        InputStream is = XmlHelper.class.getClassLoader().getResourceAsStream("schemas/".concat(key).concat("/").concat(schema));
                        IOUtils.copy(is, fos);
                        is.close();
                        fos.close();
                        
                    }
                }
                
            }
            
            for (File f:new File[]{peticion, respuesta}){
                String name = f.getName();
                XjbBindings xjbBindings = XjbBindingsUtils.getXjbBindings(key, name, xsdFolderMap);
                setXjbBindingsXmlDescriptor(f, xjbBindings);
            }
            
        }
        
        
        Project project = ProjectUtils.getProject(new ArrayList<String>(resourcesMap.keySet()));
        setPomXmlDescriptor(projectFolderFile, project);
        
 
        
    }
    
    
    public void generate() throws JAXBException, IOException{

        String execStrPath = System.getProperty("user.dir");
        
        log.log(Level.INFO, "Directorio ejecucion: {0}", execStrPath);
        
        Path projectFolderPath = FileSystems.getDefault().getPath(execStrPath);
        
        if (projectFolderPath == null) return;
        if (projectFolderPath.getParent() == null) return;
        
        projectFolderPath = projectFolderPath.getParent();
        projectFolderPath = (projectFolderPath!=projectFolderPath.getRoot())?projectFolderPath.getParent():projectFolderPath.getRoot();
        
        log.log(Level.INFO, "Directorio generación {0}", projectFolderPath);
        
        projectGeneration(projectFolderPath);

    }
    
    
    public static void main(String args[]) throws Exception {

        // Obtenim instància
        GenericProject gen = GenericProject.getGen();
        log.log(Level.INFO, "Inicio generación : {0}", ARTIFACT_NAME);
        // Generam el projecte
        gen.generate();
        log.log(Level.INFO, "Fin generación : {0}", ARTIFACT_NAME);
        
        /*
        CodeSource src = XmlHelper.class.getProtectionDomain().getCodeSource();
        if (src == null) {
            return;
        }

        URL jar = src.getLocation();
        ZipInputStream zip = new ZipInputStream(jar.openStream());
        while (true) {
            ZipEntry e = zip.getNextEntry();
            if (e == null) {
                break;
            }
            if (e.isDirectory()) {
                continue;
            }
            String name = e.getName();
            if (name.startsWith("schemas/")) {
                //System.out.println(name);
            }
        }

     
        InputStream is = XmlHelper.class.getClassLoader().getResourceAsStream("schemas/AEAT102v2/peticion.xsd");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(is, baos);
        is.close();
        baos.close();
        */

    }

   
}
