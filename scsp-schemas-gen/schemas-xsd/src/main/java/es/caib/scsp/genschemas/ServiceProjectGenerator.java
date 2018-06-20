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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author gdeignacio
 */
public class ServiceProjectGenerator extends ProjectGenerator {
    
    public ServiceProjectGenerator(String key, List<String> resources) {
        super();
        this.key = key;
        this.projectFolderName = SERVICE_PROJECT_FOLDER_NAME.replace("service", key);
        this.artifactName = SERVICE_ARTIFACT_NAME.replace("service", key);
        this.resourcesMap.put(key, resources);
    }
    
    private final String key;
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
        return ProjectUtils.getServiceProject(new ArrayList<String>(resourcesMap.keySet()));
    }
    

    @Override
    protected void generateContent(File projectFolder) throws IOException {
        
        projectFolder.mkdirs();
        
        
        
        
        
        
        
    }
    
}
