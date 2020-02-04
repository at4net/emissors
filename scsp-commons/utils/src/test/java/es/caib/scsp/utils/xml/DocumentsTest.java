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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

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

    /**
     * Test of stringToElement method, of class Documents.
     */
    @Test
    public void testStringToElement() {
        System.out.println("stringToElement");
        
        String xml = "<?xml version=\"1.0\"?>\n" +
"<!-- Edited by XMLSpy -->\n" +
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
        
        
        Element expResult = null;
        Element result = null;
        try {
            result = Documents.stringToElement(xml);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(result.toString());
        
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
