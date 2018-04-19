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
import com.sun.java.xml.ns.jaxb.Bindings;
import es.caib.pinbal.scsp.XmlHelper;
import es.caib.scsp.genschemas.managers.BindingsXmlManager;
import es.caib.scsp.genschemas.managers.Project;
import es.caib.scsp.genschemas.managers.ProjectXmlManager;
import es.caib.scsp.utils.util.DataHandlers;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.CodeSource;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;
import org.apache.maven.pom._4_0.Model;

import javax.mail.util.ByteArrayDataSource;




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

    
    private void setBindingsXmlDescriptor(File f, Bindings bindings) throws JAXBException, FileNotFoundException, IOException{
        
        f.mkdirs();
        
        File bindingsxml = new File(f, "bindings.xml");
        
        bindingsxml.createNewFile();
        
        BindingsXmlManager manager = new BindingsXmlManager();
        
        DataHandler dh = manager.generateXml(bindings);
        
        FileOutputStream fos = new FileOutputStream(bindingsxml);
        
        byte[] b = DataHandlers.dataHandlerToByteArray(dh);
        
        fos.write(b);
    
        fos.close();
    }
    
    private Project getProject(){
    
        Project project = new Project();
        
        
        
        project.setModelVersion("4.0.0");
        
        
        
        return project;
        
    }
    
    private Bindings getBindings(){
        
        Bindings bindings = new Bindings();
        
        
        
        
        return bindings;
    }
    
    
    private void projectGeneration(Path path) throws JAXBException, IOException {

        LOG.log(Level.INFO,"Generando proyecto " + PROJECT_FOLDER_NAME + " en : {0}", path);
        
        File folder = new File(path.toFile(),PROJECT_FOLDER_NAME);
        
        File srcTestJava = new File(folder, "src/test/java");
        File srcMainJava = new File(folder, "src/main/java");
        File srcMainResources = new File(folder, "src/main/resources");
        
        srcMainJava.mkdirs();
        srcMainResources.mkdirs();
        srcTestJava.mkdirs();
        
        Project project = getProject();
        
        setPomXmlDescriptor(new File(path.toFile(),PROJECT_FOLDER_NAME), project);
        
        
        
        
        
        
    }

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

         System.out.println(src);
         
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
             if (e.isDirectory()) continue;
             String name = e.getName();
             if (name.startsWith("schemas/")) {
                 //System.out.println(name);
             }
         }
        
        
         
     /*
         Enumeration<URL> enume = XmlHelper.class.getClassLoader().
                
         while (enume.hasMoreElements()){
             System.out.println(enume.nextElement());
         }
       */
     
         
         InputStream is = XmlHelper.class.getClassLoader().getResourceAsStream("schemas/AEAT102v2/peticion.xsd");

         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(is, baos);
         is.close();
         baos.close();
         
         
          //System.out.println(baos.toString());
     
     }

   
}
