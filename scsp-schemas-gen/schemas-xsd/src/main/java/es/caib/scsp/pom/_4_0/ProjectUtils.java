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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.maven.pom._4_0.Plugin;
import org.apache.maven.pom._4_0.PluginExecution;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author gdeignacio
 */
public class ProjectUtils {
    
    
    
     public static Project getProject() throws JAXBException, IOException{
        
        Project project = new Project();
        InputStream pomInputStream = project.getClass().getClassLoader().getResourceAsStream("jaxb/templates/pom.xml.template");
        ProjectXmlManager manager = new ProjectXmlManager();
        
        project = manager.generateItem(pomInputStream);
        
        return project;
        
     }
     
    private static final String SCHEMA_SCOPE = "schemas";
    private static final String SPECIFIC_SCOPE = "specific";
    private static final String SOAP_SCOPE = "soap";
    private static final String WSDL_SCOPE = "wsdl";
     
    private static PluginExecution getExecution(
            String codigo, 
            String executionSchemaDirectory, 
            String executionBindingDirectory, 
            String scope){
        
        PluginExecution execution = new PluginExecution();
        String id = codigo + "_" + scope;
        execution.setId(id);
        execution.setPhase("generate-sources");
        PluginExecution.Goals goals = new PluginExecution.Goals();
        execution.setGoals(goals);

        execution.getGoals().getGoal().add("generate");
        PluginExecution.Configuration configuration = new PluginExecution.Configuration();

        try {

            String namespace = "http://maven.apache.org/POM/4.0.0";

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element xjcArgs = doc.createElementNS(namespace, "xjcArgs");
            Element xjcArg = doc.createElementNS(namespace, "xjcArg");
            xjcArg.appendChild(doc.createTextNode("-XautoNameResolution"));
            xjcArgs.appendChild(xjcArg);
            configuration.getAny().add(xjcArgs);
            
            Element schemaDirectory = doc.createElementNS(namespace, "schemaDirectory");
            schemaDirectory.appendChild(doc.createTextNode(executionSchemaDirectory));
            configuration.getAny().add(schemaDirectory);
            
            Element schemaIncludes = doc.createElementNS(namespace, "schemaIncludes");
            Element include = doc.createElementNS(namespace, "include");
            
            if (WSDL_SCOPE.equals(scope)){
                include.appendChild(doc.createTextNode("*.wsdl"));
            }
            else if (SOAP_SCOPE.equals(scope)){
                include.appendChild(doc.createTextNode("soap*.xsd"));
            }
            else if (SCHEMA_SCOPE.equals(scope)){
                include.appendChild(doc.createTextNode("*.xsd"));
            }
            //else if (SPECIFIC_SCOPE.equals(scope)){
            //    include.appendChild(doc.createTextNode("*specifico*.xsd"));
            //}
            else {
                include.appendChild(doc.createTextNode("*.xsd"));
            }
            
            schemaIncludes.appendChild(include);
            configuration.getAny().add(schemaIncludes);

            Element schemaExcludes = doc.createElementNS(namespace, "schemaExcludes");
            Element exclude;
            
            if (WSDL_SCOPE.equals(scope)) {
                exclude = doc.createElementNS(namespace, "exclude");
                exclude.appendChild(doc.createTextNode("*.xsd"));
                schemaExcludes.appendChild(exclude);
                configuration.getAny().add(schemaExcludes);
            } else if (SOAP_SCOPE.equals(scope)) {

                //include.appendChild(doc.createTextNode("*.wsdl"));
            } else if (SCHEMA_SCOPE.equals(scope)) {
                
                /*
                exclude = doc.createElementNS(namespace, "exclude");
                exclude.appendChild(doc.createTextNode("*atos-especifico*.xsd"));
                schemaExcludes.appendChild(exclude);
                exclude = doc.createElementNS(namespace, "exclude");
                exclude.appendChild(doc.createTextNode("*atos-especificos-en*.xsd"));
                schemaExcludes.appendChild(exclude);
                exclude = doc.createElementNS(namespace, "exclude");
                exclude.appendChild(doc.createTextNode("*atos-especificos-sa*.xsd"));
                schemaExcludes.appendChild(exclude);
                */
                
                exclude = doc.createElementNS(namespace, "exclude");
                exclude.appendChild(doc.createTextNode("*soap*"));
        
                schemaExcludes.appendChild(exclude);
                configuration.getAny().add(schemaExcludes);
            } else if (SPECIFIC_SCOPE.equals(scope)) {
                exclude = doc.createElementNS(namespace, "exclude");
                exclude.appendChild(doc.createTextNode("soap*.xsd"));
                schemaExcludes.appendChild(exclude);
                configuration.getAny().add(schemaExcludes);
            } else {
                exclude = doc.createElementNS(namespace, "exclude");
                exclude.appendChild(doc.createTextNode("*.xsd"));
                schemaExcludes.appendChild(exclude);
                configuration.getAny().add(schemaExcludes);
            }


            Element generateDirectory = doc.createElementNS(namespace, "generateDirectory");
            String directory = "${project.build.directory}/generated-sources/xjc-" + id;
            generateDirectory.appendChild(doc.createTextNode(directory));
            configuration.getAny().add(generateDirectory);
            Element episodeFile = doc.createElementNS(namespace, "episodeFile");
            String file = "${project.build.directory}/generated-sources/xjc/META-INF/jaxb-schemas-" + id + ".episode";
            episodeFile.appendChild(doc.createTextNode(file));
            configuration.getAny().add(episodeFile);

            Element bindingDirectory = doc.createElementNS(namespace, "bindingDirectory");
            bindingDirectory.appendChild(doc.createTextNode(executionBindingDirectory));
            configuration.getAny().add(bindingDirectory);
            
            Element bindingIncludes = doc.createElementNS(namespace, "bindingIncludes");
            Element bindingInclude = doc.createElementNS(namespace, "include");
            
            if (WSDL_SCOPE.equals(scope)) {
                bindingInclude.appendChild(doc.createTextNode("*" + WSDL_SCOPE + "*.xjb"));
                bindingIncludes.appendChild(bindingInclude);
                configuration.getAny().add(bindingIncludes);
            } else if (SOAP_SCOPE.equals(scope)) {
                bindingInclude.appendChild(doc.createTextNode("*" + SOAP_SCOPE + "*.xjb"));
                bindingIncludes.appendChild(bindingInclude);
                configuration.getAny().add(bindingIncludes);
            } else if (SCHEMA_SCOPE.equals(scope)) {
                bindingInclude.appendChild(doc.createTextNode("*" + SCHEMA_SCOPE + "*.xjb"));
                bindingIncludes.appendChild(bindingInclude);
                configuration.getAny().add(bindingIncludes);
            //} else if (SPECIFIC_SCOPE.equals(scope)) {
            //    bindingInclude.appendChild(doc.createTextNode("*" + SPECIFIC_SCOPE + "*.xjb"));
            //    bindingIncludes.appendChild(bindingInclude);
            //    configuration.getAny().add(bindingIncludes);
            }else {
                bindingInclude.appendChild(doc.createTextNode("*.xjb"));
                bindingIncludes.appendChild(bindingInclude);
                configuration.getAny().add(bindingIncludes);
            }
            
            /*
            Element bindingExcludes = doc.createElementNS(namespace, "bindingExcludes");
            Element bindingExclude = doc.createElementNS(namespace, "exclude");
            
            if (WSDL_SCOPE.equals(scope)) {
                bindingExclude.appendChild(doc.createTextNode("*.xjb"));
                bindingExcludes.appendChild(bindingExclude);
                configuration.getAny().add(bindingExcludes);
            } else if (SOAP_SCOPE.equals(scope)) {
                bindingExclude.appendChild(doc.createTextNode("*.xjb"));
                bindingExcludes.appendChild(bindingExclude);
                configuration.getAny().add(bindingExcludes);
            } else if (SCHEMA_SCOPE.equals(scope)) {
                bindingExclude.appendChild(doc.createTextNode("*.xjb"));
                bindingExcludes.appendChild(bindingExclude);
                configuration.getAny().add(bindingExcludes);
            } else {
                bindingExclude.appendChild(doc.createTextNode("*.xjb"));
                bindingExcludes.appendChild(bindingExclude);
                configuration.getAny().add(bindingExcludes);
            }
            */
            
            

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProjectUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        execution.setConfiguration(configuration);

        return execution;
        
    } 
     
    public static Project getProject(Map<String, String> executionMap, Map<String, String> bindingMap) throws JAXBException, IOException {

        Project project = getProject();
        for (Plugin plugin : project.getBuild().getPlugins().getPlugin()) {
            if ("maven-jaxb2-plugin".equals(plugin.getArtifactId())) {
                for (String key : executionMap.keySet()){
                    PluginExecution execution;
                    execution = getExecution(
                            key,
                            (String) executionMap.get(key),
                            (String) bindingMap.get(key),
                            SCHEMA_SCOPE
                    );
                    plugin.getExecutions().getExecution().add(execution);
                }
            }
        }
        return project;
    }

    


}
