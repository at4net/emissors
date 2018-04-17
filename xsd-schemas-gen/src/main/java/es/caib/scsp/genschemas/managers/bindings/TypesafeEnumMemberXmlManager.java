/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.scsp.genschemas.managers.bindings;

import com.sun.java.xml.ns.jaxb.TypesafeEnumMember;
import es.caib.scsp.utils.xml.XmlManager;
import javax.xml.bind.JAXBException;


/**
 *
 * @author gdeignacio
 */
public class TypesafeEnumMemberXmlManager extends XmlManager<TypesafeEnumMember> {

    public TypesafeEnumMemberXmlManager() throws JAXBException {
        super(TypesafeEnumMember.class);
    }
    
}
