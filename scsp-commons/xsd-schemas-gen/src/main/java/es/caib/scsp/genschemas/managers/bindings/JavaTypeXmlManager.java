/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.scsp.genschemas.managers.bindings;

import com.sun.java.xml.ns.jaxb.JavaType;
import javax.xml.bind.JAXBException;
import es.caib.scsp.utils.xml.XmlManager;
/**
 *
 * @author gdeignacio
 */
public class JavaTypeXmlManager extends XmlManager<JavaType> {

    public JavaTypeXmlManager() throws JAXBException {
        super(JavaType.class);
    }
    
}
