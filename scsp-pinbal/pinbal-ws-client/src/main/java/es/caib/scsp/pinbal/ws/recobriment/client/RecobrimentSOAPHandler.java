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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author gdeignacio
 */
public class RecobrimentSOAPHandler implements
         javax.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {
 
      @Override
      public Set<QName> getHeaders() {
         return null;
      }
 
      @Override
      public void close(MessageContext mc) {
      }
 
      @Override
      public boolean handleFault(SOAPMessageContext mc) {
         return true;
      }
 
      @Override
      public boolean handleMessage(SOAPMessageContext mc) {
          
         SOAPMessage sm = mc.getMessage(); 
          
          try {
              
              LOG.log(Level.INFO, sm.getSOAPBody().getChildNodes().item(0).getNodeValue());
              LOG.log(Level.INFO, "--------------------DATOS ESPECIFICOS---------------------------");
              LOG.log(Level.INFO, sm.getSOAPBody().getAttribute("datosespecificos"));
              
          } catch (SOAPException ex) {
              Logger.getLogger(RecobrimentSOAPHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
 
         return true;
      }
      
      /*
      @Override
      public boolean handleMessage(SOAPMessageContext mc) {
         if (Boolean.TRUE.equals(mc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY))) {
            
            SOAPMessage sm = mc.getMessage();
 
            try {
               SOAPFactory sf = SOAPFactory.newInstance();
               SOAPHeader sh = sm.getSOAPHeader();
               if (sh == null) {
                  sh = sm.getSOAPPart().getEnvelope().addHeader();
               }
 
               Name twoTermName = sf.createName("TwoTerms", "samp", "http://www.example.org");
               SOAPHeaderElement shElement = sh.addHeaderElement(twoTermName);
               SOAPElement firstTerm = shElement.addChildElement("term");
               firstTerm.addTextNode("Apple");
               shElement.addChildElement(firstTerm);
               SOAPElement secondTerm = shElement.addChildElement("term");
               secondTerm.addTextNode("Orange");
               shElement.addChildElement(secondTerm);
            } catch (SOAPException e) {
               throw new ProtocolException(e);
            }
         }
 
         return true;
      }*/
      
   }