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
package es.caib.scsp.pinbal.ws.recobriment.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 *
 * @author gdeignacio
 */
@XmlRegistry
public class ObjectFactoryPinbalClient extends es.caib.pinbal.ws.recobriment.ObjectFactory  {

    private final static QName _PeticionSincronaResponsePinbalClient_QNAME = new QName("http://www.caib.es/pinbal/ws/recobriment", "peticionSincronaResponse");
    private final static QName _PeticionSincronaPinbalClient_QNAME = new QName("http://www.caib.es/pinbal/ws/recobriment", "peticionSincrona");
    
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.caib.pinbal.ws.recobriment
     * 
     */
    public ObjectFactoryPinbalClient() {
    }

 
     /**
     * Create an instance of {@link PeticionPinbalClient }
     * 
     */
    public PeticionPinbalClient createPeticionPinbalClient() {
        return new PeticionPinbalClient();
    }

    /**
     * Create an instance of {@link RespuestaPinbalClient }
     * 
     */
    public RespuestaPinbalClient createRespuestaPinbalClient() {
        return new RespuestaPinbalClient();
    }

    /**
     * Create an instance of {@link TransmisionDatosPinbalClient }
     * 
     */
    public TransmisionDatosPinbalClient createTransmisionDatosPinbalClient() {
        return new TransmisionDatosPinbalClient();
    }

  


    /**
     * Create an instance of {@link TransmisionesPinbalClient }
     * 
     */
    public TransmisionesPinbalClient createTransmisionesPinbalClient() {
        return new TransmisionesPinbalClient();
    }

   

    /**
     * Create an instance of {@link PeticionSincronaResponse }
     * 
     */
    public PeticionSincronaResponsePinbalClient createPeticionSincronaResponsePinbalClient() {
        return new PeticionSincronaResponsePinbalClient();
    }

    

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PeticionSincronaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.caib.es/pinbal/ws/recobriment", name = "peticionSincronaResponse")
    public JAXBElement<PeticionSincronaResponsePinbalClient> createPeticionSincronaResponse(PeticionSincronaResponsePinbalClient value) {
        return new JAXBElement<PeticionSincronaResponsePinbalClient>(_PeticionSincronaResponsePinbalClient_QNAME, PeticionSincronaResponsePinbalClient.class, null, value);
    }
    
      /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PeticionSincrona }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.caib.es/pinbal/ws/recobriment", name = "peticionSincrona")
    public JAXBElement<PeticionSincronaPinbalClient> createPeticionSincrona(PeticionSincronaPinbalClient value) {
        return new JAXBElement<PeticionSincronaPinbalClient>(_PeticionSincronaPinbalClient_QNAME, PeticionSincronaPinbalClient.class, null, value);
    }



}

