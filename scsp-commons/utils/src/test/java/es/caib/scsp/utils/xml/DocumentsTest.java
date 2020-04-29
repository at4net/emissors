/*
 * Copyright 2020 gdeignacio.
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
package es.caib.scsp.utils.xml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author gdeignacio
 */
public class DocumentsTest {
    
    public DocumentsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    public static boolean isJDK7OrLower() {
        boolean jdk7OrLower = true;
        try {
            Class.class.getDeclaredField("annotations");
        } catch (NoSuchFieldException e) {
            //Willfully ignore all exceptions
            jdk7OrLower = false;
        }
        return jdk7OrLower;
    }

    
    /**
     * Test of dummy method, of class AEAT103Iv3Client.
     */
    @Test
    public void testElement() {
        System.out.println("dummy");
        
        
        /*
        
        System.out.println("ATTRIBUTE   1   " + element.getAttribute(XMLConstants.XMLNS_ATTRIBUTE));
        System.out.println("ATTRIBUTE   2  " + element.getAttribute(XMLConstants.XMLNS_ATTRIBUTE_NS_URI));
        System.out.println("ATTRIBUTE   3  " + element.getAttribute(XMLConstants.XML_NS_PREFIX));
        System.out.println("ATTRIBUTE   4  " + element.getAttribute(XMLConstants.XML_NS_URI));
        
        System.out.println(generateXmlString(item));
        
        System.out.println(generateXmlString(item));
        
        System.out.println(xmlSchemaAnnotation);
        
        Document document = element.getOwnerDocument();
        
        String nodeName = element.getNodeName();
        System.out.println(nodeName);
        
  
        element = (Element)document.renameNode(element, xmlSchemaAnnotation.namespace(), nodeName);
        
        element.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, XMLConstants.XMLNS_ATTRIBUTE, xmlSchemaAnnotation.namespace());
        
        System.out.println("ATTRIBUTE   5   " + element.getAttribute(XMLConstants.XMLNS_ATTRIBUTE));
        System.out.println("ATTRIBUTE   6  " + element.getAttribute(XMLConstants.XMLNS_ATTRIBUTE_NS_URI));
        System.out.println("ATTRIBUTE   7  " + element.getAttribute(XMLConstants.XML_NS_PREFIX));
        System.out.println("ATTRIBUTE   8  " + element.getAttribute(XMLConstants.XML_NS_URI));
        
        System.out.println(generateXmlString(item));
        
        
        
        */
        
        
        
        
        
        
        
       
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    

    /**
     * Test of stringToElement method, of class XmlUtils.
     */
    @Test
    public void testStringToElement() {
        
        System.out.println("Is 7 OR LESS: " + isJDK7OrLower());
        
        /*
        System.out.println("--------------------------");
        for (Field f:Class.class.getDeclaredFields()){
            System.out.println(f.getName());
        }
        
        Field annotations;
        try {
            annotations = Class.class.getDeclaredField("annotations");
            annotations.setAccessible(true);
            
            try {
               // Map<Class<? extends Annotation>, Annotation> map =  (Map<Class<? extends Annotation>, Annotation>)
                        System.out.println(annotations.toString());
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        
       /*
        String xml = "" +
//                "<?xml version=\"1.0\"?>\n" +
//"<!-- Edited by XMLSpy -->\n" +
"<catalog>\n" +
"   <product description=\"Cardigan Sweater\" product_image=\"cardigan.jpg\">\n" +
"      <catalog_item gender=\"Men's\">\n" +
"         <item_number>QWZ5671</item_number>\n" +
"         <price>39.95</price>\n" +
"         <size description=\"Medium\">\n" +
"            <color_swatch image=\"red_cardigan.jpg\">Red</color_swatch>\n" +
"            <color_swatch image=\"burgundy_cardigan.jpg\">Burgundy</color_swatch>\n" +
"         </size>\n" +
"         <size description=\"Large\">\n" +
"            <color_swatch image=\"red_cardigan.jpg\">Red</color_swatch>\n" +
"            <color_swatch image=\"burgundy_cardigan.jpg\">Burgundy</color_swatch>\n" +
"         </size>\n" +
"      </catalog_item>    \n" +
"   </product>\n" +
"</catalog>";
        */
        
      /*
       
        System.out.println(xml);
        
        Element expResult = null;
        Element result = null;
        try {
            result = XmlUtils.stringToElement(xml);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        */    
        
       /*
       SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos datosEspecificos = null;
       
       System.out.println(SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos.class.getSuperclass().getPackage().getAnnotation(XmlSchema.class));
        
       SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos respuesta = new SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos();
        
        try {
            XmlManager<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> manager
                    = new XmlManager<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos>(
                            SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos.class
                    );

            datosEspecificos = manager.generateItem(xml);

            System.out.println(datosEspecificos);

        } catch (JAXBException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        */    
        
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
