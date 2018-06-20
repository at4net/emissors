/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.scsp.genschemas.managers;

import javax.xml.bind.JAXBException;
import es.caib.scsp.utils.xml.XmlManager;
import es.caib.scsp.xml.ns.jaxb.XjbBindings;

/**
 *
 * @author gdeignacio
 */
@Deprecated
public class XjbBindingsXmlManager extends XmlManager<XjbBindings> {

    public XjbBindingsXmlManager() throws JAXBException {
        super(XjbBindings.class);
    }
    
}
