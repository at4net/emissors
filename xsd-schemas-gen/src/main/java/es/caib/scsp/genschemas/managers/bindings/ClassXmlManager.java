/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.scsp.genschemas.managers.bindings;

import javax.xml.bind.JAXBException;
import es.caib.scsp.utils.xml.XmlManager;
/**
 *
 * @author gdeignacio
 */
public class ClassXmlManager extends XmlManager<com.sun.java.xml.ns.jaxb.Class> {

    public ClassXmlManager() throws JAXBException {
        super(com.sun.java.xml.ns.jaxb.Class.class);
    }
    
}
