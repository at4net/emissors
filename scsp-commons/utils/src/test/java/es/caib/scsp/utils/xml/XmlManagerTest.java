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

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlSchema;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Element;

/**
 *
 * @author gdeignacio
 */
public class XmlManagerTest {
    
    
    XmlManager<NIVRENTIv3PeticionDatosEspecificosTest> NIVRENTIv3manager;
    
    NIVRENTIv3PeticionDatosEspecificosTest item;
    
    
    
    public XmlManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        try {
            NIVRENTIv3manager = new XmlManager<NIVRENTIv3PeticionDatosEspecificosTest>(NIVRENTIv3PeticionDatosEspecificosTest.class);
            
            item = new NIVRENTIv3PeticionDatosEspecificosTest();
            
            item.setEjercicio(2015);
            
            
        } catch (JAXBException ex) {
            Logger.getLogger(XmlManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getXmlSchemaAnnotation method, of class XmlManager.
     */
    @Test
    public void testGetXmlSchemaAnnotation() {
        
        System.out.println("getXmlSchemaAnnotation");
        
        XmlSchema xmlSchemaAnnotation = NIVRENTIv3manager.getXmlSchemaAnnotation();
  
        System.out.println("NAMESPACE: " + xmlSchemaAnnotation.namespace());
        System.out.println("LOCATION: " + xmlSchemaAnnotation.location());
        
    }

    /**
     * Test of generateElement method, of class XmlManager.
     */
    @Test
    public void testGenerateElement_GenericType() throws Exception {
        System.out.println("generateElement");
        
        Element result = NIVRENTIv3manager.generateElement(item);
        System.out.println(result.toString());
        
    }

    
    
//    /**
//     * Test of generateElement method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateElement_GenericType_boolean() throws Exception {
//        System.out.println("generateElement");
//        Object item = null;
//        boolean noCheckXmlns = false;
//        XmlManager instance = null;
//        Element expResult = null;
//        Element result = instance.generateElement(item, noCheckXmlns);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateXml method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateXml_GenericType() throws Exception {
//        System.out.println("generateXml");
//        Object item = null;
//        XmlManager instance = null;
//        DataHandler expResult = null;
//        DataHandler result = instance.generateXml(item);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateXml method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateXml_List() throws Exception {
//        System.out.println("generateXml");
//        XmlManager instance = null;
//        DataHandler expResult = null;
//        DataHandler result = instance.generateXml(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateFlatXml method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateFlatXml() throws Exception {
//        System.out.println("generateFlatXml");
//        XmlManager instance = null;
//        DataHandler expResult = null;
//        DataHandler result = instance.generateFlatXml(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateItem method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateItem_DataHandler() throws Exception {
//        System.out.println("generateItem");
//        DataHandler document = null;
//        XmlManager instance = null;
//        Object expResult = null;
//        Object result = instance.generateItem(document);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateItem method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateItem_InputStream() throws Exception {
//        System.out.println("generateItem");
//        InputStream is = null;
//        XmlManager instance = null;
//        Object expResult = null;
//        Object result = instance.generateItem(is);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateItem method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateItem_String() throws Exception {
//        System.out.println("generateItem");
//        String xml = "";
//        XmlManager instance = null;
//        Object expResult = null;
//        Object result = instance.generateItem(xml);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateItem method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateItem_Element() throws Exception {
//        System.out.println("generateItem");
//        Element element = null;
//        XmlManager instance = null;
//        Object expResult = null;
//        Object result = instance.generateItem(element);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateItem method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateItem_Element_boolean() throws Exception {
//        System.out.println("generateItem");
//        Element element = null;
//        boolean noCheckXmlns = false;
//        XmlManager instance = null;
//        Object expResult = null;
//        Object result = instance.generateItem(element, noCheckXmlns);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateXmlByteArray method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateXmlByteArray() throws Exception {
//        System.out.println("generateXmlByteArray");
//        Object item = null;
//        XmlManager instance = null;
//        byte[] expResult = null;
//        byte[] result = instance.generateXmlByteArray(item);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateFlatXmlString method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateFlatXmlString_GenericType() throws Exception {
//        System.out.println("generateFlatXmlString");
//        Object item = null;
//        XmlManager instance = null;
//        String expResult = "";
//        String result = instance.generateFlatXmlString(item);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateFlatXmlString method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateFlatXmlString_List() throws Exception {
//        System.out.println("generateFlatXmlString");
//        XmlManager instance = null;
//        String expResult = "";
//        String result = instance.generateFlatXmlString(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateXmlString method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateXmlString_GenericType() throws Exception {
//        System.out.println("generateXmlString");
//        Object item = null;
//        XmlManager instance = null;
//        String expResult = "";
//        String result = instance.generateXmlString(item);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateXmlString method, of class XmlManager.
//     */
//    @Test
//    public void testGenerateXmlString_List() throws Exception {
//        System.out.println("generateXmlString");
//        XmlManager instance = null;
//        String expResult = "";
//        String result = instance.generateXmlString(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of validateItem method, of class XmlManager.
//     */
//    @Test
//    public void testValidateItem() throws Exception {
//        System.out.println("validateItem");
//        Object item = null;
//        DataHandler xsd = null;
//        XmlManager instance = null;
//        boolean expResult = false;
//        boolean result = instance.validateItem(item, xsd);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
