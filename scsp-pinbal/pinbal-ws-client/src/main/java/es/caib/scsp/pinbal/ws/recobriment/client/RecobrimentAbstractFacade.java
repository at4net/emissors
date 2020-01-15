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

import es.caib.pinbal.ws.recobriment.Atributos;
import es.caib.pinbal.ws.recobriment.Estado;
import es.caib.pinbal.ws.recobriment.Peticion;
import es.caib.pinbal.ws.recobriment.Respuesta;
import es.caib.pinbal.ws.recobriment.SolicitudTransmision;
import es.caib.pinbal.ws.recobriment.Solicitudes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author gdeignacio
 */
public class RecobrimentAbstractFacade {
    
    private static String APP = "es.caib.scsp.";
    
    protected static final Logger LOG = Logger.getLogger(RecobrimentAbstractFacade.class.getName());
     
    private RecobrimentClient client;
    
    public RecobrimentAbstractFacade(){
        this(APP);
    }
 
    public RecobrimentAbstractFacade(String app){
        DadesConnexioRecobriment dadesConnexio = new DadesConnexioRecobriment(app);
        this.client = RecobrimentClient.getClient(dadesConnexio);
    }
    
    public RespuestaClientAdapter peticionSincrona(){
        return null;
    }
    
    
    private RespuestaClientAdapter peticionSincrona(PeticionClientAdapter peticionClient){
        Peticion peticion = peticionClient2Peticion(peticionClient);
        Respuesta response = this.client.peticionSincrona(peticion);
        return null;
    }

    private Peticion peticionClient2Peticion(PeticionClientAdapter peticionClient) {
        
        Estado estado = RecobrimentUtils.establecerEstado(
                peticionClient.getCodigoEstado(),
                peticionClient.getCodigoEstadoSecundario(),
                peticionClient.getLiteralError(),
                peticionClient.getTiempoEstimadoRespuesta()
        );
        
        Atributos atributos = RecobrimentUtils.establecerAtributos(
                peticionClient.getCodigoCertificado(), 
                estado, 
                peticionClient.getIdPeticion(), 
                peticionClient.getNumElementos(), 
                peticionClient.getTimeStamp()
        );
        
        List<SolicitudTransmision> solicitudesTransmision = peticionClient.getSolicitudesTransmision();
         
        Solicitudes solicitudes = RecobrimentUtils.establecerSolicitudes(solicitudesTransmision);
        
        Peticion peticion = RecobrimentUtils.establecerPeticion(atributos, solicitudes);
        
    }
     
    
    
    
    
}
