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
import es.caib.scsp.utils.xml.XmlManager;
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
public abstract class GenericProject {
    
    protected GenericProject(String projectFolderName, String artifactName){
        super();
        this.projectFolderName=projectFolderName;
        this.artifactName=artifactName;
    }
   
    private static final Logger log=Logger.getLogger(GenericProject.class.getName());;
    private String projectFolderName;
    private String artifactName;

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
    
    protected abstract Project getProject();
    
    protected abstract void projectGeneration(Project project, Path path);
    
    
    private void generatePomXmlDescriptor(Path p, Project project) throws IOException, JAXBException{
        
        File projectFolder = p.toFile();
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
        
        
        
        
    }
    
    
    
    
}
