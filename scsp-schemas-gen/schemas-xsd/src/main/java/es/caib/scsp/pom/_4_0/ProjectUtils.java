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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.maven.pom._4_0.Plugin;
import org.apache.maven.pom._4_0.PluginExecution;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
        
        return project;
        
     }
     
     
     
     
    public static Project getProject(Map<String, String> executionMap) throws JAXBException, IOException {

        Project project = getProject();

        for (Plugin plugin : project.getBuild().getPlugins().getPlugin()) {
            if ("maven-jaxb2-plugin".equals(plugin.getArtifactId())) {
                for (String key : executionMap.keySet()) {
                    PluginExecution execution = new PluginExecution();
                    execution.setId(key);
                    execution.setPhase("generate-sources");
                    //List<String> goals = new ArrayList<String>();
                    PluginExecution.Goals goals = new PluginExecution.Goals();
                    execution.setGoals(goals);

                    execution.getGoals().getGoal().add("generate");
                    PluginExecution.Configuration configuration = new PluginExecution.Configuration();

                    
                    
                    try {
                        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                        Element schemaDirectory = doc.createElementNS("http://maven.apache.org/POM/4.0.0","schemaDirectory");

                        schemaDirectory.appendChild(doc.createTextNode((String) executionMap.get(key)));
                        configuration.getAny().add(schemaDirectory);
                        Element schemaIncludes = doc.createElementNS("http://maven.apache.org/POM/4.0.0","schemaIncludes");
                        Element include = doc.createElementNS("http://maven.apache.org/POM/4.0.0","include");
                        include.appendChild(doc.createTextNode("*.xsd"));
                        schemaIncludes.appendChild(include);
                        configuration.getAny().add(schemaIncludes);
                        Element generateDirectory = doc.createElementNS("http://maven.apache.org/POM/4.0.0","generateDirectory");
                        String directory = "${project.build.directory}/generated-sources/xjc-" + key;
                        generateDirectory.appendChild(doc.createTextNode(directory));
                        configuration.getAny().add(generateDirectory);
                        Element episodeFile = doc.createElementNS("http://maven.apache.org/POM/4.0.0","episodeFile");
                        String file = "${project.build.directory}/generated-sources/xjc/META-INF/jaxb-schemas-" + key + ".episode";
                        episodeFile.appendChild(doc.createTextNode(file));
                        configuration.getAny().add(episodeFile);

                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(ProjectUtils.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    execution.setConfiguration(configuration);

                    plugin.getExecutions().getExecution().add(execution);
                }

            }
        }

        return project;

    }


}
