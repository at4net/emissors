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

import es.caib.pinbal.ws.recobriment.Recobriment;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author gdeignacio
 */
@WebService(targetNamespace = "http://www.caib.es/pinbal/ws/recobriment", name = "Recobriment")
@XmlSeeAlso({ObjectFactoryPinbalClient.class})
public interface RecobrimentPinbalClient extends Recobriment{
    
    @WebMethod
    @RequestWrapper(localName = "peticionSincrona", targetNamespace = "http://www.caib.es/pinbal/ws/recobriment", className = "es.caib.scsp.pinbal.ws.recobriment.cxf.PeticionSincronaPinbalClient")
    @ResponseWrapper(localName = "peticionSincronaResponse", targetNamespace = "http://www.caib.es/pinbal/ws/recobriment", className = "es.caib.scsp.pinbal.ws.recobriment.cxf.PeticionSincronaResponsePinbalClient")
    @WebResult(name = "return", targetNamespace = "")
    public es.caib.scsp.pinbal.ws.recobriment.cxf.RespuestaPinbalClient peticionSincronaPinbalClient(
        @WebParam(name = "arg0", targetNamespace = "")
        es.caib.scsp.pinbal.ws.recobriment.cxf.PeticionPinbalClient arg0
    );
    
}
