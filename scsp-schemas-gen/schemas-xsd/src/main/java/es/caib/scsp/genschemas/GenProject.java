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
    
    private static final String PROJECT_FOLDER_NAME = "scsp-schemas-xsd";
    private static final String ARTIFACT_NAME = "scsp-schemas-xsd";
    
    
    private GenProject(){
        super();
    }
 
    public static GenProject getGen(){
        return gen;
    }
    
    
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
    
    private void projectGeneration(Path path) throws JAXBException, IOException {

        LOG.log(Level.INFO, "Generando proyecto " + PROJECT_FOLDER_NAME + " en : {0}", path);

        File folder = new File(path.toFile(), PROJECT_FOLDER_NAME);

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

        CodeSource src = XmlHelper.class.getProtectionDomain().getCodeSource();

        if (src == null) {
            return;
        }

        Map<String, String> executionMap = new HashMap<String,String>();
        Map<String, String> bindingMap = new HashMap<String,String>();
        HashMap<String, List<String>> xsdMap = new HashMap<String,List<String>>();
        
        
        URL jar = src.getLocation();
        
        ZipInputStream zip = new ZipInputStream(jar.openStream());
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
        
        
    }
    
    private static final String SCHEMA_SCOPE = "schemas";
    private static final String SPECIFIC_SCOPE = "specific";
    private static final String SOAP_SCOPE = "soap";
    private static final String WSDL_SCOPE = "wsdl";

    public void generate() throws JAXBException, IOException{

        LOG.log(Level.INFO, "Directorio ejecucion: {0}", System.getProperty("user.dir"));
        
        String strPath = System.getProperty("user.dir");
        
        Path path = FileSystems.getDefault().getPath(strPath);
        
        if (path == null) return;
        if (path.getParent() == null) return;
        
        path = path.getParent();
        path = (path!=path.getRoot())?path.getParent():path.getRoot();
        
        projectGeneration(path);

    }
    
    
    public static void main(String args[]) throws Exception {

        GenProject gen = GenProject.getGen();

        gen.generate();

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

    }

   
}
