/*
 * Copyright 2016 gdeignacio.
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

import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author gdeignacio
 */
public class XmlJAXBManager {

    private final Class<?> clazz;

    private final JAXBContext jaxbContext;

    public XmlJAXBManager(Class<?> clazz) throws JAXBException {

        this.clazz = clazz;
        this.jaxbContext = JAXBContext.newInstance(clazz);
    }

    public JAXBContext getContext() {
        return this.jaxbContext;
    }

    private ByteArrayOutputStream marshal(Class<?> item) throws JAXBException {

        return marshal(item, Boolean.TRUE);

    }

    private ByteArrayOutputStream marshal(Class<?> item, boolean formattedOutput) throws JAXBException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);

        jaxbMarshaller.marshal(item, baos);

        return baos;
    }

    private Object unmarshal(InputStream is) throws JAXBException {

       Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
       return jaxbUnmarshaller.unmarshal(new StreamSource(is), clazz).getValue();

    }

    public DataHandler generateXml(Class<?> item) throws JAXBException {

        byte[] b = marshal(item).toByteArray();
        String mimetype = "application/xml";
        DataSource ds = new ByteArrayDataSource(b, mimetype);
        return new DataHandler(ds);

    }

    public DataHandler generateXml(List<Class<?>> items) throws JAXBException {

        byte[] b = generateXmlString(items).getBytes();
        String mimetype = "text/plain";
        DataSource ds = new ByteArrayDataSource(b, mimetype);
        return new DataHandler(ds);

    }

    public DataHandler generateFlatXml(List<Class<?>> items) throws JAXBException {

        byte[] b = generateFlatXmlString(items).getBytes();
        String mimetype = "text/plain";
        DataSource ds = new ByteArrayDataSource(b, mimetype);
        return new DataHandler(ds);

    }

    public Object generateItem(DataHandler document) throws JAXBException, IOException {
        return unmarshal(document.getInputStream());
    }
    
    public Object generateItem(InputStream is) throws JAXBException, IOException {
        return unmarshal(is);
    }
    

    public byte[] generateXmlByteArray(Class<?> item) throws JAXBException {

        return marshal(item).toByteArray();

    }

    public String generateFlatXmlString(Class<?> item) throws JAXBException {

        return marshal(item, Boolean.FALSE).toString();

    }

    public String generateFlatXmlString(List<Class<?>> items) throws JAXBException {

        StringBuilder mensajes = new StringBuilder();
        for (Class<?> item : items) {
            mensajes.append(generateFlatXmlString(item));
            mensajes.append(IOUtils.LINE_SEPARATOR);
        }
        return mensajes.toString();

    }

    public String generateXmlString(Class<?> item) throws JAXBException {

        return marshal(item).toString();

    }

    public String generateXmlString(List<Class<?>> items) throws JAXBException {

        StringBuilder mensajes = new StringBuilder();
        for (Class<?> item : items) {
            mensajes.append(generateXmlString(item));
        }
        return mensajes.toString();

    }
    
    
    public boolean validateItem(Class<?> item, DataHandler xsd) throws JAXBException{
        
        return XmlValidation.validateXMLSchema(xsd, generateXml(item));
    
    }

}
