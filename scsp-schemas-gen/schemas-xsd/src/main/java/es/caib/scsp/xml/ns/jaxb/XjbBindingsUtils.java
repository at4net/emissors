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
package es.caib.scsp.xml.ns.jaxb;

import com.google.common.base.CaseFormat;
import com.sun.java.xml.ns.jaxb.GlobalBindings;
import com.sun.java.xml.ns.jaxb.NameXmlTransformRule;
import com.sun.java.xml.ns.jaxb.NameXmlTransformType;
import com.sun.java.xml.ns.jaxb.PackageType;
import com.sun.java.xml.ns.jaxb.SchemaBindings;
import es.caib.scsp.genschemas.managers.XjbBindingsXmlManager;
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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author gdeignacio
 */
public class XjbBindingsUtils {

    public static XjbBindings getXjbBindings() throws JAXBException, IOException {

        XjbBindings xjbBindings = new XjbBindings();
        InputStream bindingsInputStream = xjbBindings.getClass().getClassLoader().getResourceAsStream("jaxb/templates/bindings.xjb.template");
        XjbBindingsXmlManager manager = new XjbBindingsXmlManager();

        xjbBindings = manager.generateItem(bindingsInputStream);

        return xjbBindings;

    }
    
    //private static final String SCHEMA_SCOPE = "schemas";
    //private static final String SPECIFIC_SCOPE = "specific";
    //private static final String SOAP_SCOPE = "soap";
    //private static final String WSDL_SCOPE = "wsdl";
    
    
    private static Document getDocumentFromXml(String xsd){
        
        InputStream is = XjbBindings.class.getClassLoader().getResourceAsStream(xsd);
        Document doc = null;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XjbBindingsUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XjbBindingsUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XjbBindingsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
    
    /*
    <jxb:bindings schemaLocation="xsd/CommonTypes.xsd">
        <jxb:bindings node="//xs:complexType[@name='AnXSDType']">
            <jxb:class name="AnXSDTypeFromCommonTypes"/>
        </jxb:bindings>
    </jxb:bindings>
    <jxb:bindings schemaLocation="xsd/EvenMoreCommonTypes.xsd">
        <jxb:bindings node="//xs:complexType[@name='AnXSDType']">
            <jxb:class name="AnXSDTypeFromEvenMoreCommonTypes"/>
        </jxb:bindings>
    </jxb:bindings>
    */
    
    
    public static XjbBindings getXjbBindings(String key, String folder, Map<String,List<String>> xsds) throws JAXBException, IOException {

        System.out.println("key " + key + ": " + xsds);

        XjbBindings xjbBindings = getXjbBindings();

        //GlobalBindings globalBindings = new GlobalBindings();
        //globalBindings.setEnableJavaNamingConventions(Boolean.TRUE);
        //globalBindings.setGenerateElementClass(Boolean.TRUE);
        //globalBindings.setGenerateElementProperty(Boolean.TRUE);
        //globalBindings.setLocalScoping("toplevel");
        //xjbBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(globalBindings);
        for (String xsd : xsds.get(folder)) {

            XjbBindings xsdBindings = new XjbBindings();
            //xsdBindings.setSchemaLocation("../../schemas/" + key + "/" + xsd);
            xsdBindings.setSchemaLocation(xsd);
            //xsdBindings.setNode("//xs:schema");
            if (xsd.contains("comun")) continue;
            if (xsd.contains("VolanteEmpadronamientoTipos")) continue;

            SchemaBindings schemaBindings = new SchemaBindings();
            PackageType packageType = new PackageType();
            System.out.println("Binding para: " + xsd);

            String legalizedKey;
            
            legalizedKey = (Character.isDigit(key.charAt(0)))?"_".concat(key):key;
            
            String packageName = "es.caib.scsp.esquemas." + legalizedKey + "." + folder + ".";
            packageName = packageName.concat(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.replace(".", "_").replace("_xsd", "")).toLowerCase());

            System.out.println(packageName);

            packageType.getName().add(packageName);
            schemaBindings.setPackage(packageType);

            xsdBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(schemaBindings);
            //xjbBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(xsdBindings);

            xjbBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(xsdBindings);

        }
        return xjbBindings;
    }
}


/*


            if (WSDL_SCOPE.equals(scope)) {
                if (xsd.endsWith("xsd")) continue;
            } else if (SOAP_SCOPE.equals(scope)) {
                if (xsd.endsWith("wsdl")) continue;
                if (!xsd.startsWith("soap")) continue;
                //include.appendChild(doc.createTextNode("soap*.xsd"));
            } else if (SCHEMA_SCOPE.equals(scope)) {
                if (xsd.endsWith("xml")) continue;
                if (xsd.endsWith("wsdl")) continue;
                if (xsd.startsWith("soap")) continue;
                if (xsd.contains("comun")) continue;
                //if (xsd.contains("datos-especificos-ent")) continue;
                //if (xsd.contains("datos-especificos-sal")) continue;
                
                
                SchemaBindings schemaBindings = new SchemaBindings();
                PackageType packageType = new PackageType();
               
                System.out.println("Binding para: " + xsd);
                
                String packageName = "es.caib.scsp.esquemas." + key + ".";
                
                
                if ("datos-especificos.xsd".equals(xsd)){
                    packageName = packageName.concat("datosespecificos_xsd");
                }
                else if ("datos-especificos-ent.xsd".equals(xsd)){
                    packageName = packageName.concat("peticion_xsd");
                }
                else if ("datos-especificos-sal.xsd".equals(xsd)){
                    packageName = packageName.concat("respuesta_xsd");
                }
                else if ("datos-especificos-entrada.xsd".equals(xsd)){
                    packageName = packageName.concat("peticion_xsd");
                }
                else if ("datos-especificos-salida.xsd".equals(xsd)){
                    packageName = packageName.concat("respuesta_xsd");
                }
                else {
                    packageName = packageName.concat(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.replace(".", "_")).toLowerCase());
                }
                
                packageName = packageName.concat(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.replace(".", "_").replace("_xsd", "")).toLowerCase());
                
                System.out.println(packageName);
               
                packageType.getName().add(packageName);
                schemaBindings.setPackage(packageType); 
               
                
                NameXmlTransformType nameXmlTransform = new NameXmlTransformType();

                NameXmlTransformRule typeNameXmlTransformRule = new NameXmlTransformRule();
                typeNameXmlTransformRule.setPrefix(key + "_"
                        + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.replace(".", "_")).toLowerCase()
                        + "_Type_");

                nameXmlTransform.setTypeName(typeNameXmlTransformRule);

                
                NameXmlTransformRule elementNameXmlTransformRule = new NameXmlTransformRule();
                elementNameXmlTransformRule.setPrefix(key + "_"
                        + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.replace(".", "_")).toLowerCase()
                        + "_Element_");

                nameXmlTransform.setElementName(elementNameXmlTransformRule);
                
                schemaBindings.setNameXmlTransform(nameXmlTransform);
                

                xsdBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(schemaBindings);
                
                
                
                if (xsd.contains("especificos-")) {
                    
                    XjbBindings nodeBindings;
                    String clazzName;
                    String xpath;
                    com.sun.java.xml.ns.jaxb.Class clazz;

                    nodeBindings = new XjbBindings();
                    nodeBindings.setRequired("no");
                    nodeBindings.setMultiple("true");
                    clazzName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.split("\\.")[0]);
                    xpath = "//xs:element[@name='" + clazzName + "']";
                    
                    //String xpath = "//xs:elementType[@name='DatosEspecificos'] | //xs:complexType[@name='DatosEspecificos']"; 
                    //String xpath = "//xs:complexType[@name='DatosEspecificos']"; 
                    //String xpath = "//xs:schema";
                    
                    nodeBindings.setNode(xpath);
                    clazz = new com.sun.java.xml.ns.jaxb.Class();
                    clazz.getName().add(clazzName + "Element");
                    nodeBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(clazz);
                    xsdBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(nodeBindings);
                    
                    
                    nodeBindings = new XjbBindings();
                    nodeBindings.setRequired("no");
                    nodeBindings.setMultiple("true");
                    
                    clazzName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.split("\\.")[0]);
                    xpath = "//xs:complexType[@name='" + clazzName + "']";
                    nodeBindings.setNode(xpath);
                    clazz = new com.sun.java.xml.ns.jaxb.Class();
                    clazz.getName().add(clazzName + "Type");
                    nodeBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(clazz);
                    xsdBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(nodeBindings);

                }
                
                
                
                
                if ("peticion.xsd".equals(xsd) || "respuesta.xsd".equals(xsd)) {
                    
                    XjbBindings nodeBindings;
                    String clazzName;
                    String xpath;
                    com.sun.java.xml.ns.jaxb.Class clazz;

                    nodeBindings = new XjbBindings();
                    nodeBindings.setRequired("no");
                    nodeBindings.setMultiple("true");
                    clazzName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.split("\\.")[0]);
                    xpath = "//xs:element[@name='" + clazzName + "']";
                    
                    nodeBindings.setNode(xpath);
                    clazz = new com.sun.java.xml.ns.jaxb.Class();
                    clazz.getName().add(clazzName + xsd + "Element");
                    nodeBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(clazz);
                    xsdBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(nodeBindings);
                    
                    nodeBindings = new XjbBindings();
                    nodeBindings.setRequired("no");
                    nodeBindings.setMultiple("true");
                    
                    clazzName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.split("\\.")[0]);
                    xpath = "//xs:complexType[@name='" + clazzName + "']";
                    nodeBindings.setNode(xpath);
                    clazz = new com.sun.java.xml.ns.jaxb.Class();
                    clazz.getName().add(clazzName + xsd + "Type");
                    nodeBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(clazz);
                    xsdBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(nodeBindings);
                
                }
                
                 
                
              
            } else {
                //include.appendChild(doc.createTextNode("*.xsd"));
            }
            xjbBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(xsdBindings);

        }

        //xjbBindings = getXjbBindings();
        return xjbBindings;
        
        

    }


    
    
    
}
*/