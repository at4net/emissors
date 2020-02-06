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
package es.caib.scsp.pinbal.ws.recobriment.client;

import java.io.ByteArrayOutputStream;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author gdeignacio
 */
public class RecobrimentSOAPHandler implements
        javax.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {

    public static String DATOS_ESPECIFICOS = "es.caib.scsp.pinbal.ws.recobriment.client.datosespecificos";
    public static String RESPONSE_BODY = "es.caib.scsp.pinbal.ws.recobriment.client.response";
            
    protected static final Logger LOG = Logger.getLogger(RecobrimentSOAPHandler.class.getName());

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext smc) {
        obtenerDatosEspecificos(smc);
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext smc) {
        obtenerDatosEspecificos(smc);
        return true;
    }

    @Override
    public void close(MessageContext messageContext) {
    }

    
    private void handleTransmisionDatosElement(SOAPMessageContext soapMessageContext, Element transmisionDatosElement){
        
        NodeList idSolicitudList = transmisionDatosElement.getElementsByTagName("idSolicitud");
        
        if (idSolicitudList==null) return;
        if (idSolicitudList.getLength()==0) return;
        
        NodeList idTransmisionList = transmisionDatosElement.getElementsByTagName("idTransmision");
        
        if (idTransmisionList==null) return;
        if (idTransmisionList.getLength()==0) return;
        
        NodeList datosEspecificosList = transmisionDatosElement.getElementsByTagName("datosEspecificos");
        if (datosEspecificosList==null) return;
        if (datosEspecificosList.getLength()==0) return;
        
        String idSolicitud = idSolicitudList.item(0).getTextContent();
        String idTransmision = idTransmisionList.item(0).getTextContent();
        Node datosEspecificosNode = datosEspecificosList.item(0);
        
        //LOG.log(Level.INFO, "Valor TransmisionDatosElement Handler: {0}", datosEspecificosNode.getTextContent());
        
        
        String key = DATOS_ESPECIFICOS + "." + idSolicitud + "." + idTransmision;
        Element value = (Element)datosEspecificosNode;
        
        //LOG.log(Level.INFO, "Clave contexto Handler: {0}", key);
        //LOG.log(Level.INFO, "Valor contexto Handler: {0}", value.getTextContent());
        
        soapMessageContext.put(key, value);
        soapMessageContext.setScope(key, MessageContext.Scope.APPLICATION);
        
        //LOG.log(Level.INFO, "Full context: {0}", soapMessageContext.toString());
        
        
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String message;
        try {
        
            soapMessageContext.getMessage().writeTo(baos);
            message = baos.toString();
            key = RESPONSE_BODY;
            soapMessageContext.put(key, baos.toString("UTF-8"));
            soapMessageContext.setScope(key, MessageContext.Scope.APPLICATION);

        } catch (Exception ex) {
            message = "Error al processar el missatge XML: " + ex.getMessage();
        }
        //LOG.info(message);
        
        
        
    
    }
    
    private void obtenerDatosEspecificos(SOAPMessageContext soapMessageContext) {

        boolean isRequest = (Boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (isRequest) {
            return;
        }

        SOAPMessage soapMessage = soapMessageContext.getMessage();

        try {
            NodeList transmisionDatosNodeList = soapMessage.getSOAPBody().getElementsByTagName("transmisionDatos");
            Node transmisionDatosNode = null;
            for (int i = 0; i < transmisionDatosNodeList.getLength(); i++) {
                transmisionDatosNode = transmisionDatosNodeList.item(i);
                handleTransmisionDatosElement(soapMessageContext, (Element)transmisionDatosNode);
            }

        } catch (SOAPException ex) {
            Logger.getLogger(RecobrimentSOAPHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
