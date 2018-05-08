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
import com.sun.java.xml.ns.jaxb.NameXmlTransformRule;
import com.sun.java.xml.ns.jaxb.NameXmlTransformType;
import com.sun.java.xml.ns.jaxb.PackageType;
import com.sun.java.xml.ns.jaxb.SchemaBindings;
import es.caib.scsp.genschemas.managers.XjbBindingsXmlManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
    
    private static final String SCHEMA_SCOPE = "schemas";
    private static final String SPECIFIC_SCOPE = "specific";
    private static final String SOAP_SCOPE = "soap";
    private static final String WSDL_SCOPE = "wsdl";
    
    
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
    

    public static XjbBindings getXjbBindings(String key, List<String> xsds, String scope) throws JAXBException, IOException{

        System.out.println("key " + key + ": " + xsds);
        
        XjbBindings xjbBindings = getXjbBindings();
        
        for (String xsd : xsds) {
            
            XjbBindings xsdBindings = new XjbBindings();

            xsdBindings.setSchemaLocation("../../schemas/" + key + "/" + xsd);
            
         
            //xsdBindings.setNode("//xs:schema");

            if (WSDL_SCOPE.equals(scope)) {
                if (xsd.endsWith("xsd")) continue;
            } else if (SOAP_SCOPE.equals(scope)) {
                if (xsd.endsWith("wsdl")) continue;
                if (!xsd.startsWith("soap")) continue;
                //include.appendChild(doc.createTextNode("soap*.xsd"));
            } else if (SCHEMA_SCOPE.equals(scope)) {
                
                if (xsd.endsWith("wsdl")) continue;
                if (xsd.startsWith("soap")) continue;
                if (xsd.contains("comun")) continue;
                
                   
                SchemaBindings schemaBindings = new SchemaBindings();
                
                PackageType packageType = new PackageType();
                String packageName = "es.caib.scsp.esquemas." + key + "." //datosespecificos"
                        + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.replace(".", "_")).toLowerCase();

                packageType.getName().add(packageName);
                schemaBindings.setPackage(packageType); 
                
                
                /*
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
                */

                xsdBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(schemaBindings);
                
                if (xsd.contains("especificos")) {    

                    XjbBindings nodeBindings = new XjbBindings();
                    
                    nodeBindings.setRequired("no");
                    nodeBindings.setMultiple("true");
                    
                    String clazzName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.split("\\.")[0]);

                    //String xpath = "//xs:element[@name='" + clazzName + "']";
                    String xpath = "//xs:elementType[@name='DatosEspecificos'] | //xs:complexType[@name='DatosEspecificos']"; 

                    //String xpath = "//xs:complexType[@name='DatosEspecificos']"; 
                    
                    //String xpath = "//xs:schema";
                    
                    nodeBindings.setNode(xpath);

                    com.sun.java.xml.ns.jaxb.Class clazz = new com.sun.java.xml.ns.jaxb.Class();

                    clazz.getName().add(clazzName + "DatosEspecificos");

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
    
}
