/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.scsp.genschemas.managers.bindings;

import com.sun.java.xml.ns.jaxb.TypesafeEnumClass;
import es.caib.scsp.utils.xml.XmlManager;
import javax.xml.bind.JAXBException;


/**
 *
 * @author gdeignacio
 */
public class TypesafeEnumClassXmlManager extends XmlManager<TypesafeEnumClass> {

    public TypesafeEnumClassXmlManager() throws JAXBException {
        super(TypesafeEnumClass.class);
    }
    
}
