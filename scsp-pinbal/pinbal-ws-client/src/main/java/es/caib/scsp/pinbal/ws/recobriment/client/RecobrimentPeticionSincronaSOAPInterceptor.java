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

import static es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentClient.LOG;
import java.util.logging.Level;
import javax.xml.soap.SOAPMessage;
 
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

/**
 *
 * @author gdeignacio
 */


public class RecobrimentPeticionSincronaSOAPInterceptor extends AbstractSoapInterceptor {

    public RecobrimentPeticionSincronaSOAPInterceptor() {
        super(Phase.USER_PROTOCOL);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        SOAPMessage sm = message.getContent(SOAPMessage.class);

        LOG.log(Level.INFO, sm.toString());
             
        
        //try {
        //    SOAPHeader sh = sm.getSOAPHeader();
        //    /* Note in real use validity checking should be done
        //       (really two terms present? namespaces? etc.) */
        //    NodeList termNodes = sh.getElementsByTagName("term");
        //    message.put("termOne", termNodes.item(0).getTextContent());
        //    message.put("termTwo", termNodes.item(1).getTextContent());
        //    /* JAX-WS Handler "setScope()" (HANDLER/APPLICATION) 
        //       not available with interceptors, APPLICATION is standard
        //       meaning both properties readable by service bean */
        //} catch (SOAPException e) {
        //    throw new Fault(e);
        //}
        
    }
}
