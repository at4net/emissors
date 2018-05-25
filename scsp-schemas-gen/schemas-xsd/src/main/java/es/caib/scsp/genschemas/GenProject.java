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

//import es.caib.pinbal.scsp.XmlHelper;
import es.caib.pinbal.scsp.XmlHelper;
import es.caib.scsp.genschemas.managers.XjbBindingsXmlManager;
import es.caib.scsp.pom._4_0.Project;
import es.caib.scsp.genschemas.managers.ProjectXmlManager;
import es.caib.scsp.pom._4_0.ProjectUtils;
import es.caib.scsp.utils.util.DataHandlers;
import es.caib.scsp.xml.ns.jaxb.XjbBindings;
import es.caib.scsp.xml.ns.jaxb.XjbBindingsUtils;
import java.io.ByteArrayOutputStream;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;




//import org.apache.commons.io.IOUtils;

/**
 *
 * @author gdeignacio
 */
public class GenProject {
    
    protected static final Logger LOG = Logger.getLogger(GenProject.class.getName());
    private static final GenProject gen = new GenProject();
    
    private GenProject(){
        super();
    }
 
    public static GenProject getGen(){
        return gen;
    }
    
    private static final String PROJECT_FOLDER_NAME = "scsp-schemas-xsd";
    private static final String ARTIFACT_NAME = "scsp-schemas-xsd";
    
    
    
    
    private void setPomXmlDescriptor(File f, Project project) throws JAXBException, FileNotFoundException, IOException {

        f.mkdirs();
        
        File pomxml = new File(f, "pom.xml");
        
        pomxml.createNewFile();
        
        LOG.log(Level.INFO, "Creando pom: {0}", pomxml.getAbsolutePath());
        
        ProjectXmlManager manager = new ProjectXmlManager();
        
        DataHandler dh = manager.generateXml(project);

        FileOutputStream fos = new FileOutputStream(pomxml);

        byte[] b = DataHandlers.dataHandlerToByteArray(dh);

        fos.write(b);

        fos.close();
    }

    
    private void setXjbBindingsXmlDescriptor(File f, XjbBindings xjbBindings, String scope) throws JAXBException, FileNotFoundException, IOException{
        
        f.mkdirs();
        
        File bindingsxml = new File(f, scope + "_bindings.xjb");
        
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
            if (!name.startsWith("schemas") || "schemas/".equals(name)) continue;
            LOG.log(Level.INFO, "Procesando recurso : {0}", name);
            String[] arrayName = name.split("/");
            if (arrayName.length==1){
                LOG.log(Level.INFO, "{0} es un directorio vacio. No hacemos nada.", name);
                continue;
            }
            String key = arrayName[1];
            LOG.log(Level.INFO, "Procesando clave : {0}", key);
            if (e.isDirectory()){
                LOG.log(Level.INFO, "{0} es un directorio. No hacemos nada.", name);
                continue;
            }
            String schema = arrayName[arrayName.length-1];
            LOG.log(Level.INFO, "Procesando archivo : {0}", schema);
            List<String> schemas = (resourcesMap.containsKey(key))?resourcesMap.get(key):new ArrayList<String>();
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
            if (subFolder.mkdirs()){
                LOG.log(Level.INFO, "Carpeta {0} creada ", subFolder.getCanonicalPath());
            }
        }
        
        

        
        
        
        
            /*
            if (name.startsWith("schemas/")) {
                File schema = new File(srcMainResourcesJaxb, name);
                File bind = new File(srcMainResourcesJaxb, "bindings/" + schema.getName());
                
                if (e.isDirectory()) {
                    
                    if ("schemas/".equals(name)) {
                        
                        //System.out.println("Directorio schemas encontrado");
                        
                        continue;
                    }
                    
                    schema.mkdirs();
                    bind.mkdirs();
                    
                    executionMap.put(schema.getName(), "src/main/resources/jaxb/schemas/" + schema.getName());  
                    bindingMap.put(schema.getName(), "src/main/resources/jaxb/bindings/" + schema.getName());
                    
                    continue;
                }

                
                
                List<String> xsds= (xsdMap.get(schema.getParentFile().getName())!=null)?xsdMap.get(schema.getParentFile().getName()):new ArrayList<String>();
                xsds.add(schema.getName());
                xsdMap.put(schema.getParentFile().getName(), xsds);
                
                schema.createNewFile();
                FileOutputStream fos = new FileOutputStream(schema);
                InputStream is = XmlHelper.class.getClassLoader().getResourceAsStream(name);
                IOUtils.copy(is, fos);
                is.close();
                fos.close();

            }
            */
            
            
            
           
        
        
        
        
        
        
        
        
        
        
        /*
        File srcTestJava = new File(folder, "src/test/java");
        File srcMainJava = new File(folder, "src/main/java");
        File srcMainResources = new File(folder, "src/main/resources");
        File srcMainResourcesJaxb = new File(folder, "src/main/resources/jaxb");
        File srcMainResourcesJaxbSchemas = new File(folder, "src/main/resources/jaxb/schemas");
        File srcMainResourcesJaxbBindings = new File(folder, "src/main/resources/jaxb/bindings");

        srcMainJava.mkdirs();
        srcMainResources.mkdirs();
        srcTestJava.mkdirs();

        srcMainResourcesJaxbSchemas.mkdirs();
        srcMainResourcesJaxbBindings.mkdirs();

        

        Map<String, String> executionMap = new HashMap<String,String>();
        Map<String, String> bindingMap = new HashMap<String,String>();
        HashMap<String, List<String>> xsdMap = new HashMap<String,List<String>>();
        
        
        
        
       
        while (true) {
            
            ZipEntry e = zip.getNextEntry();
            if (e == null) {
                break;
            }
            String name = e.getName();
            
            if (name.startsWith("schemas/")) {
                File schema = new File(srcMainResourcesJaxb, name);
                File bind = new File(srcMainResourcesJaxb, "bindings/" + schema.getName());
                
                if (e.isDirectory()) {
                    
                    if ("schemas/".equals(name)) {
                        
                        //System.out.println("Directorio schemas encontrado");
                        
                        continue;
                    }
                    
                    
                    schema.mkdirs();
                    bind.mkdirs();
                    
                    executionMap.put(schema.getName(), "src/main/resources/jaxb/schemas/" + schema.getName());  
                    bindingMap.put(schema.getName(), "src/main/resources/jaxb/bindings/" + schema.getName());
                    
                    continue;
                }

                
                
                List<String> xsds= (xsdMap.get(schema.getParentFile().getName())!=null)?xsdMap.get(schema.getParentFile().getName()):new ArrayList<String>();
                xsds.add(schema.getName());
                xsdMap.put(schema.getParentFile().getName(), xsds);
                
                schema.createNewFile();
                FileOutputStream fos = new FileOutputStream(schema);
                InputStream is = XmlHelper.class.getClassLoader().getResourceAsStream(name);
                IOUtils.copy(is, fos);
                is.close();
                fos.close();

            }
           
        }
   
        zip.close();
        
        System.out.println(executionMap.keySet().size() + " codigos");
        
        for (String key:bindingMap.keySet()){
            XjbBindings xjbBindings = XjbBindingsUtils.getXjbBindings(key, (List<String>)xsdMap.get(key), SCHEMA_SCOPE);
            File bindingsFolder = new File(srcMainResourcesJaxbBindings, key);
            setXjbBindingsXmlDescriptor(bindingsFolder, xjbBindings, SCHEMA_SCOPE);
        }
        
        Set set = new HashSet();
        for (String key: xsdMap.keySet()){
            set.addAll(xsdMap.get(key));
        }
        
        
        System.out.println(set.size() + " nombres de fichero");
        List<String> ficheros = new ArrayList(set);
        for (String fichero: ficheros){
            System.out.println(fichero);
        }
        
        
  
        Project project = ProjectUtils.getProject(executionMap, bindingMap);
        setPomXmlDescriptor(new File(path.toFile(), PROJECT_FOLDER_NAME), project);
        
        */
        
    }
    
    private static final String SCHEMA_SCOPE = "schemas";
    private static final String SPECIFIC_SCOPE = "specific";
    private static final String SOAP_SCOPE = "soap";
    private static final String WSDL_SCOPE = "wsdl";

    
    
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
        GenProject gen = GenProject.getGen();
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
