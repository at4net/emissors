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
package es.caib.scsp.pom._4_0;

import es.caib.scsp.genschemas.managers.ProjectXmlManager;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBException;
import org.apache.maven.pom._4_0.License;
import org.apache.maven.pom._4_0.Model.Licenses;
import org.apache.maven.pom._4_0.Plugin;

/**
 *
 * @author gdeignacio
 */
public class ProjectUtils {
    
    
    
    
    public static Project getProject() throws JAXBException, IOException{
        
        Project project = new Project();
        InputStream pomInputStream = project.getClass().getClassLoader().getResourceAsStream("jaxb/templates/pom.xml");
        ProjectXmlManager manager = new ProjectXmlManager();
        
        project = manager.generateItem(pomInputStream);
        
        for (Plugin plugin:project.getBuild().getPlugins().getPlugin()){
            if ("maven-jaxb2-plugin".equals(plugin.getArtifactId())){
                
               // todo
                
                
            }
        }
        
        
        /*
                    <execution>
                        <id>Q2827003ATGSS001v3</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <schemaDirectory>src/main/resources/schemas/Q2827003ATGSS001v3</schemaDirectory>
                            <schemaIncludes>
                                <include>*.xsd</include>
                            </schemaIncludes>
                            <generateDirectory>${project.build.directory}/generated-sources/xjc-Q2827003ATGSS001v3</generateDirectory>
                            <episodeFile>${project.build.directory}/generated-sources/xjc/META-INF/jaxb-carga.episode</episodeFile>
                        </configuration>
                    </execution>
        */
        
      
        return project;
        
    
    
    }
    
    
}
