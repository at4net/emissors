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
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author gdeignacio
 */
public class RecobrimentSOAPHandler implements
        javax.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {

    public static String RESPONSE_BODY = "es.caib.scsp.pinbal.ws.recobriment.client.response";
    
    protected static final Logger LOG = Logger.getLogger(RecobrimentSOAPHandler.class.getName());

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext smc) {
        obtenerRespuesta(smc);
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext smc) {
        obtenerRespuesta(smc);
        return true;
    }

    @Override
    public void close(MessageContext messageContext) {
    }

    private void obtenerRespuesta(SOAPMessageContext soapMessageContext) {
       
        boolean isRequest = (Boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (isRequest) {
            return;
        }

        SOAPMessage soapMessage = soapMessageContext.getMessage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String message;
        try {
            soapMessage.writeTo(baos);
            message = baos.toString();
            String key = RESPONSE_BODY;
            soapMessageContext.put(RESPONSE_BODY, baos.toString("UTF-8"));
            soapMessageContext.setScope(key, MessageContext.Scope.APPLICATION);

        } catch (Exception ex) {
            message = "Error al processar el missatge XML: " + ex.getMessage();
        }
        LOG.info(message);

    }

}
