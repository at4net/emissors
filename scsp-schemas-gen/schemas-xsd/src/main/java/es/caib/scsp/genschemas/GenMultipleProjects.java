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
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author gdeignacio
 */
public class GenMultipleProjects {
    // En Desuso. Ver GenSplits
    protected static final Logger LOG = Logger.getLogger(GenMultipleProjects.class.getName());
    private static final GenMultipleProjects gen = new GenMultipleProjects();
    
    private GenMultipleProjects(){
        super();
    }
 
    public static GenMultipleProjects getGen(){
        return gen;
    }
    
    private static final String PROJECT_FOLDER_NAME = "scsp-schemas-xsd";
    private static final String ARTIFACT_NAME = "scsp-schemas-xsd";
    
    /*
    private void setXmlDescriptor(File f, String descriptorName, Class<?> clazz,  Object obj) throws JAXBException, FileNotFoundException, IOException {

        f.mkdirs();
        
        File descriptorxml = new File(f, descriptorName);
        
        descriptorxml.createNewFile();
        
        LOG.log(Level.INFO, "Creando descriptor: {0}", descriptorxml.getAbsolutePath());
        
        XmlJAXBManager manager = new XmlJAXBManager(clazz.getClass());
        
        //ProjectXmlManager manager = new ProjectXmlManager();
        
       
        
        LOG.log(Level.INFO, "Creando descriptor: {0}", clazz.getClass().newInstance());
        
        DataHandler dh = manager.generateXml(clazz.getClass().cast(obj));

        FileOutputStream fos = new FileOutputStream(descriptorxml);

        byte[] b = DataHandlers.dataHandlerToByteArray(dh);

        fos.write(b);

        fos.close();
    }
    
    
    private void setPomXmlDescriptor(File f, Project project) throws JAXBException, FileNotFoundException, IOException {

        setXmlDescriptor(f, "pom.xml", Project.class, project);
    }
    */
    
    
    
    
    private static final String MAIN_JAVA = "src/main/java";
    private static final String TEST_JAVA = "src/test/java";
    private static final String MAIN_RESOURCES = "src/main/resources";
    private static final String JAXB_RESOURCES = "jaxb";
    private static final String JAXB_SCHEMAS = "schemas";
    private static final String JAXB_BINDINGS = "bindings";
    
    private void projectGeneration(Path projectFolderPath) throws JAXBException, IOException {

        LOG.log(Level.INFO, "Generando proyecto " + PROJECT_FOLDER_NAME + " en : {0}", projectFolderPath);

        File projectFolderFile = new File(projectFolderPath.toFile(), PROJECT_FOLDER_NAME);

        LOG.log(Level.INFO, "Generando proyecto " + PROJECT_FOLDER_NAME + " con carpeta : {0}", projectFolderFile);
        
        for (String fld: new String[]{MAIN_JAVA, TEST_JAVA, MAIN_RESOURCES}){
            LOG.log(Level.INFO, "Creando carpeta : {0}", fld);
            File subFolder = new File(projectFolderFile, fld);
            if (subFolder.mkdirs()){
                LOG.log(Level.INFO, "Carpeta {0} creada ", subFolder.getCanonicalPath());
            }
        }
        
        LOG.log(Level.INFO, "Recuperando fuentes de esquemas");
        CodeSource src = XmlHelper.class.getProtectionDomain().getCodeSource();
            
        if (src == null) {
            LOG.log(Level.INFO, "No encontrado. Saliendo");
            return;
        }
        
        LOG.log(Level.INFO, "Fuentes en src : {0}", src);
        URL jar = src.getLocation();
        LOG.log(Level.INFO, "Fuentes en jar : {0}", jar);
        
        ZipInputStream zip = new ZipInputStream(jar.openStream());
        Map<String,List<String>> resourcesMap = new HashMap<String,List<String>>();
        ZipEntry e;
        while ((e=zip.getNextEntry())!=null) {
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
            List<String> schemas = (resourcesMap.containsKey(key)) ? resourcesMap.get(key) : new ArrayList<String>();
            schemas.add(schema);
            resourcesMap.put(key, schemas);
        }
        zip.close();
        
        LOG.log(Level.INFO, "Listando esquemas");
        for (String key:resourcesMap.keySet()){
            LOG.log(Level.INFO, "Esquemas para : {0}", key);
            List<String> schemas = resourcesMap.get(key);
            LOG.log(Level.INFO, "{0}", schemas.toString());
        }
        
        
        LOG.log(Level.INFO, "Distribuyendo esquemas");
        for (String key:resourcesMap.keySet()){
            LOG.log(Level.INFO, "Esquemas para : {0}", key);
            String fld = MAIN_RESOURCES + "/" + JAXB_RESOURCES + "/" + key;
            LOG.log(Level.INFO, "Creando carpeta : {0}", fld);
            File subFolder = new File(projectFolderFile, fld);
            File peticion = new File(subFolder, "peticion");
            File respuesta = new File(subFolder, "respuesta");
            if (subFolder.mkdirs()){
                LOG.log(Level.INFO, "Carpeta {0} creada ", subFolder.getCanonicalPath());
            }
            if (peticion.mkdirs()){
                LOG.log(Level.INFO, "Carpeta {0} creada ", peticion.getCanonicalPath());
            }
            if (respuesta.mkdirs()){
                LOG.log(Level.INFO, "Carpeta {0} creada ", respuesta.getCanonicalPath());
            }
            
            List<String> schemas = resourcesMap.get(key);
            Map<String, List<String>> xsdFolderMap = new HashMap<String,List<String>>();
            for (String schema:schemas){
                if (!schema.endsWith(".xsd")){
                    LOG.log(Level.INFO, "Extension de esquema distinta de xsd para {0}", schema);
                    continue;
                }
                if (schema.startsWith("soap")){
                    LOG.log(Level.INFO, "Envoltorio soap para {0}", schema);
                    continue;
                }
                String[] redundantes = new String[]{
                    "peticion_datos-especificos.xsd",
                    "respuesta_datos-especificos.xsd",
                    "xmldsig.xsd"};
                if  (Arrays.asList(redundantes).contains(schema)){
                    LOG.log(Level.INFO, "{0} redundante", schema);
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
                XjbBindingsUtils.setXjbBindingsXmlDescriptor(f, xjbBindings);
            }
            
        }
        
        
        Project project = ProjectUtils.getProject(new ArrayList<String>(resourcesMap.keySet()));
        ProjectUtils.setPomXmlDescriptor(projectFolderFile, project);
        
 
        
    }
    
    
    public void generate() throws JAXBException, IOException{

        String execStrPath = System.getProperty("user.dir");
        
        LOG.log(Level.INFO, "Directorio ejecucion: {0}", execStrPath);
        
        Path projectFolderPath = FileSystems.getDefault().getPath(execStrPath);
        
        if (projectFolderPath == null) return;
        if (projectFolderPath.getParent() == null) return;
        
        projectFolderPath = projectFolderPath.getParent();
        projectFolderPath = (projectFolderPath!=projectFolderPath.getRoot())?projectFolderPath.getParent():projectFolderPath.getRoot();
        
        LOG.log(Level.INFO, "Directorio generación {0}", projectFolderPath);
        
        projectGeneration(projectFolderPath);

    }
    
    
    public static void main(String args[]) throws Exception {

        // Obtenim instància
        GenMultipleProjects gen = GenMultipleProjects.getGen();
        LOG.log(Level.INFO, "Inicio generación : {0}", ARTIFACT_NAME);
        // Generam el projecte
        gen.generate();
        LOG.log(Level.INFO, "Fin generación : {0}", ARTIFACT_NAME);
        
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
