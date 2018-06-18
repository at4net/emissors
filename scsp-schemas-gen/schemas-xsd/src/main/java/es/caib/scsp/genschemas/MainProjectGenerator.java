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

import es.caib.scsp.pom._4_0.Project;
import java.io.File;
import java.nio.file.Path;
import java.util.logging.Level;

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
    protected Project getProject() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    protected void projectGeneration(File projectFolder) {
        
        
        
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
}
