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

import es.caib.pinbal.ws.recobriment.RecobrimentService;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 *
 * @author gdeignacio
 */
@WebServiceClient(name = "RecobrimentService", 
                  wsdlLocation = "https://proves.caib.es/pinbal/ws/recobriment?wsdl",
                  targetNamespace = "http://www.caib.es/pinbal/ws/recobriment") 
public class RecobrimentServicePinbalClient extends RecobrimentService {

    public RecobrimentServicePinbalClient(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }
    
      /**
     * 
     * @return
     *     returns Recobriment
     */
    @WebEndpoint(name = "RecobrimentServicePort")
    public RecobrimentPinbalClient getRecobrimentServicePinbalClientPort() {
        return super.getPort(RecobrimentServicePort, RecobrimentPinbalClient.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Recobriment
     */
    @WebEndpoint(name = "RecobrimentServicePort")
    public RecobrimentPinbalClient getRecobrimentServicePinbalClientPort(WebServiceFeature... features) {
        return super.getPort(RecobrimentServicePort, RecobrimentPinbalClient.class, features);
    }
    
}
