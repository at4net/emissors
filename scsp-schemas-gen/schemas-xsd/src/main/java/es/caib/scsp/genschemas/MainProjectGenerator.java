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
import es.caib.scsp.utils.xml.XmlManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author gdeignacio
 */
public class MainProjectGenerator extends ProjectGenerator {
    
    private static final String PROJECT_FOLDER_NAME = "scsp-schemas-xsd-ced";
    private static final String ARTIFACT_NAME = "scsp-schemas-xsd-ced";

    /*
    public MainProjectGenerator() {
        //super(PROJECT_FOLDER_NAME, ARTIFACT_NAME);
        super();
    }*/

    @Override
    protected File getProjectFolder() {
        
        Path mainProjectFolderPath = getMainProjectFolderPath();
        LOG.log(Level.INFO, "Generando proyecto " + PROJECT_FOLDER_NAME + " en : {0}", mainProjectFolderPath);
        File projectFolderFile = new File(mainProjectFolderPath.toFile(), PROJECT_FOLDER_NAME);
        LOG.log(Level.INFO, "Generando proyecto " + PROJECT_FOLDER_NAME + " con carpeta : {0}", projectFolderFile);
        return projectFolderFile;
        
    }

    @Override
    protected Project getProject() throws IOException, JAXBException {
        return getMainProject(new ArrayList<String>(getResourcesMap().keySet()));
    }
    
    private Project getMainProject() throws JAXBException, IOException {

        Project project = new Project();
        InputStream pomInputStream = project.getClass().getClassLoader().getResourceAsStream("jaxb/templates/pom_main.xml.template");
        XmlManager<Project> manager = new XmlManager<Project>(Project.class);
        project = manager.generateItem(pomInputStream);
        return project;

    }
    
    private Project getMainProject(List<String> keys) throws JAXBException, IOException{
        
        Project project = getMainProject();
        for (String key: keys){
            String module = "scsp-schemas-xsd-prefix".replace("prefix", key);
            project.getModules().getModule().add(module);
        }
        return project;
        
    }
    

    @Override
    protected void generateContent(File projectFolder) throws IOException {
        
        for (String template : new String[]{
            "deploy.bat.template",
            "deploy.sh.template",
            "dozip.bat.template",
            "dozip.sh.template",
            "help.bat.template",
            "help.sh.template",
            "help.txt.template",
            "nuevaVersion.bat.template",
            "nuevaVersion.sh.template",
            "versio.txt.template"
        }) {
            File contentFile = new File(projectFolder,template.replace(".template", ""));
            contentFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(contentFile);
            InputStream is = XmlHelper.class.getClassLoader().getResourceAsStream("content/parent/".concat(template));
            IOUtils.copy(is, fos);
            is.close();
            fos.close();
        }
        
        // TODO
        // ProjectGenerator projectGenerator = new ProjectGenerator();
        // 
        //
    }
    
}
