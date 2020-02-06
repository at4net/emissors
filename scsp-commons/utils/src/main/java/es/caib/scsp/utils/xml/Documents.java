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
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author gdeignacio
 */
public class Documents {
    
     public static Element stringToElement(String xml) throws ParserConfigurationException, SAXException, IOException {
        
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder documentBuilder = factory.newDocumentBuilder();
         InputSource inputStream = new InputSource();

         //For parsing XML document using FileReader
         //inputStream.setCharacterStream(new FileReader("student.xml"));

         //If you have an xml string then you can pass the string into StringReader
         inputStream.setCharacterStream(new StringReader(xml));
         Document document = documentBuilder.parse(inputStream);
         
         return document.getDocumentElement();
         
         /*
         NodeList studentNodeList = document.getElementsByTagName("student");
         for (int index = 0; index < studentNodeList.getLength(); index++) {
             Node node = studentNodeList.item(index);
             if (node.getNodeType() == Node.ELEMENT_NODE) {
                 Element element = (Element) node;
                 NodeList nameNodeList = element.getElementsByTagName("name");
                 for (int eIndex = 0; eIndex < nameNodeList.getLength(); eIndex++) {
                     if (nameNodeList.item(eIndex).getNodeType() == Node.ELEMENT_NODE) {
                         Element nameElement = (Element) nameNodeList.item(eIndex);
                         System.out.println("Name = " + nameElement.getFirstChild().getNodeValue().trim());
                     }
                 }
             }
         }
         */
         
         
        
    }
    
    
    
}
