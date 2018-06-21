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

import es.caib.scsp.genschemas.GenMultipleProjects;
import es.caib.scsp.genschemas.managers.ProjectXmlManager;
import es.caib.scsp.utils.util.DataHandlers;
import es.caib.scsp.utils.xml.XmlManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.maven.pom._4_0.Build;
import org.apache.maven.pom._4_0.Plugin;
import org.apache.maven.pom._4_0.PluginExecution;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author gdeignacio
 */
public class ProjectUtils {
    
    protected static final Logger LOG = Logger.getLogger(GenMultipleProjects.class.getName());
    
    @Deprecated
    public static void setPomXmlDescriptor(File f, Project project) throws JAXBException, FileNotFoundException, IOException {
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
    
    public static void generatePomXmlDescriptor(Path p, Project project) throws IOException, JAXBException {

        File projectFolder = p.toFile();
        generatePomXmlDescriptor(projectFolder, project);

    }

    public static void generatePomXmlDescriptor(File projectFolder, Project project) throws IOException, JAXBException {
        
        projectFolder.mkdirs();
        File pom = new File(projectFolder, "pom.xml");
        pom.createNewFile();
        XmlManager<Project> manager = new XmlManager<Project>(Project.class);
        DataHandler dh = manager.generateXml(project);
        FileOutputStream fos = new FileOutputStream(pom);
        byte[] b = DataHandlers.dataHandlerToByteArray(dh);
        fos.write(b);
        fos.close();
        
    }
    
    
    
    public static Project getMainProject() throws JAXBException, IOException {

        Project project = new Project();
        InputStream pomInputStream = project.getClass().getClassLoader().getResourceAsStream("jaxb/templates/pom_main.xml.template");
        XmlManager<Project> manager = new XmlManager<Project>(Project.class);
        project = manager.generateItem(pomInputStream);
        return project;

    }
    
    public static Project getMainProject(List<String> keys) throws JAXBException, IOException{
        
        Project project = getMainProject();
        for (String key: keys){
            String module = "scsp-schemas-xsd-service".replace("service", key);
            project.getModules().getModule().add(module);
        }
        return project;
        
    }
    
    
    public static Project getServiceProject() throws JAXBException, IOException {

        Project project = new Project();
        InputStream pomInputStream = project.getClass().getClassLoader().getResourceAsStream("jaxb/templates/pom_service.xml.template");
        XmlManager<Project> manager = new XmlManager<Project>(Project.class);
        project = manager.generateItem(pomInputStream);
        return project;

    }
    
    public static Project getServiceProject(List<String> keys) throws JAXBException, IOException {
        Project project = getServiceProject();
        for (Plugin plugin : project.getBuild().getPlugins().getPlugin()) {
            if ("maven-jaxb2-plugin".equals(plugin.getArtifactId())) {
                for (String key : keys) {
                    for (String subfolder : new String[]{"peticion", "respuesta"}) {
                        PluginExecution execution;
                        execution = getExecution(key,subfolder);
                        plugin.getExecutions().getExecution().add(execution);
                    }
                }
            }
        }
        return project;
    }
    
    public static Project getNamedServiceProject(String key, Project project){
        project.setArtifactId(project.getArtifactId().replace("service", key));
        project.setName(project.getName().replace("Service", key));
        Build build = project.getBuild();
        build.setFinalName(build.getFinalName().replace("service", key));
        project.setBuild(build);
        return project;
    }
    
    private static PluginExecution getExecution(String codigo, String subfolder){
        
        PluginExecution execution = new PluginExecution();
        String id = codigo + "_" + subfolder;
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
            
            String executionSchemaDirectory = "src/main/resources/jaxb/"
                    .concat(codigo).concat("/").concat(subfolder);
            
            Element schemaDirectory = doc.createElementNS(namespace, "schemaDirectory");
            schemaDirectory.appendChild(doc.createTextNode(executionSchemaDirectory));
            configuration.getAny().add(schemaDirectory);
            
            Element schemaIncludes = doc.createElementNS(namespace, "schemaIncludes");
            Element include = doc.createElementNS(namespace, "include");
            include.appendChild(doc.createTextNode("*.xsd"));
            schemaIncludes.appendChild(include);
            configuration.getAny().add(schemaIncludes);

            /*
            Element schemaExcludes = doc.createElementNS(namespace, "schemaExcludes");
            Element exclude;
            exclude = doc.createElementNS(namespace, "exclude");
            exclude.appendChild(doc.createTextNode("*soap*"));
            schemaExcludes.appendChild(exclude);
            configuration.getAny().add(schemaExcludes);
            */

            Element generateDirectory = doc.createElementNS(namespace, "generateDirectory");
            String directory = "${project.build.directory}/generated-sources/xjc-" + codigo + "-" + subfolder;
            generateDirectory.appendChild(doc.createTextNode(directory));
            configuration.getAny().add(generateDirectory);
            Element episodeFile = doc.createElementNS(namespace, "episodeFile");
            String file = "${project.build.directory}/generated-sources/xjc/META-INF/jaxb-schemas-" + codigo + "-" + subfolder +".episode";
            episodeFile.appendChild(doc.createTextNode(file));
            configuration.getAny().add(episodeFile);

            Element bindingDirectory = doc.createElementNS(namespace, "bindingDirectory");
            bindingDirectory.appendChild(doc.createTextNode(executionSchemaDirectory));
            configuration.getAny().add(bindingDirectory);
            
            Element bindingIncludes = doc.createElementNS(namespace, "bindingIncludes");
            Element bindingInclude = doc.createElementNS(namespace, "include");
            bindingInclude.appendChild(doc.createTextNode("*.xjb"));
            bindingIncludes.appendChild(bindingInclude);
            configuration.getAny().add(bindingIncludes);
            
            /*
            Element bindingExcludes = doc.createElementNS(namespace, "bindingExcludes");
            Element bindingExclude = doc.createElementNS(namespace, "exclude");
            bindingExclude.appendChild(doc.createTextNode("*.xjb"));
            bindingExcludes.appendChild(bindingExclude);
            configuration.getAny().add(bindingExcludes);            
            */

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProjectUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        execution.setConfiguration(configuration);

        return execution;
        
    } 
    
   @Deprecated
    public static Project getProject() throws JAXBException, IOException {

        Project project = new Project();
        InputStream pomInputStream = project.getClass().getClassLoader().getResourceAsStream("jaxb/templates/pom.xml.template");
        ProjectXmlManager manager = new ProjectXmlManager();

        project = manager.generateItem(pomInputStream);

        return project;

    }
    
    @Deprecated
    public static Project getProject(List<String> keys) throws JAXBException, IOException {
        Project project = getProject();
        for (Plugin plugin : project.getBuild().getPlugins().getPlugin()) {
            if ("maven-jaxb2-plugin".equals(plugin.getArtifactId())) {
                for (String key : keys) {
                    for (String subfolder : new String[]{"peticion", "respuesta"}) {
                        PluginExecution execution;
                        execution = getExecution(key,subfolder);
                        plugin.getExecutions().getExecution().add(execution);
                    }
                }
            }
        }
        return project;
    }

}
