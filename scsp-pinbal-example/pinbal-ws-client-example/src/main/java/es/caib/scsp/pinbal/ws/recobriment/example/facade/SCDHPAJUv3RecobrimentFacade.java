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
package es.caib.scsp.pinbal.ws.recobriment.example.facade;

import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import es.caib.scsp.pinbal.ws.recobriment.facade.RecobrimentFacade;
import es.caib.scsp.pinbal.ws.recobriment.facade.RespuestaClientAdapter;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SCDHPAJUv3PeticionDatosEspecificos;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SCDHPAJUv3RespuestaDatosEspecificos;
import es.caib.scsp.utils.xml.XmlManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;

/**
 *
 * @author gdeignacio
 */
public class SCDHPAJUv3RecobrimentFacade
        extends RecobrimentFacade<
        SCDHPAJUv3PeticionDatosEspecificos, SCDHPAJUv3RespuestaDatosEspecificos> {

    public SCDHPAJUv3RecobrimentFacade(String app) {
        super(app);
    }

    @Override
    protected Class<SCDHPAJUv3RespuestaDatosEspecificos> getDatosEspecificosRespuestaClazz() {
        return SCDHPAJUv3RespuestaDatosEspecificos.class;
    }

    /**
     *
     * @param datosEspecificosPeticion
     * @return
     */
    @Override
    protected Element datosEspecificos2Element(SCDHPAJUv3PeticionDatosEspecificos datosEspecificosPeticion){
        
        Element elementDatosEspecificos;
        
        try {
            XmlManager<SCDHPAJUv3PeticionDatosEspecificos> manager
                    = new XmlManager<SCDHPAJUv3PeticionDatosEspecificos>(SCDHPAJUv3PeticionDatosEspecificos.class);
            elementDatosEspecificos = manager.generateElement(datosEspecificosPeticion);
            return elementDatosEspecificos;
        } catch (JAXBException ex) {
            Logger.getLogger(SCDHPAJUv3RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SCDHPAJUv3RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
            
 
    }

    @Override
    protected RespuestaClientAdapter<SCDHPAJUv3RespuestaDatosEspecificos> peticionSincronaEspecifica(
                String codigoEstado, String codigoEstadoSecundario, 
                String literalError, String literalErrorSec, Integer tiempoEstimadoRespuesta, 
                String codigoCertificado, String idPeticion, 
                String numElementos, String timeStamp, String nifEmisor, 
                String nombreEmisor, String nifFuncionario, 
                String nombreCompletoFuncionario, String seudonimo, String codProcedimiento, 
                String nombreProcedimiento, String codigoUnidadTramitadora, Consentimiento consentimiento, 
                String finalidad, String idExpediente, 
                String identificadorSolicitante, String nombreSolicitante, 
                String unidadTramitadora, String apellido1, String apellido2, 
                String documentacion, String nombre, String nombreCompleto, 
                TipoDocumentacion tipoDocumentacion, String fechaGeneracion, 
                String idSolicitud, String idTransmision, 
                SCDHPAJUv3PeticionDatosEspecificos datosEspecificosPeticion) {
        
        return this.peticionSincrona(
                codigoEstado,
                codigoEstadoSecundario,
                literalError,
                literalErrorSec,
                tiempoEstimadoRespuesta,
                codigoCertificado,
                idPeticion,
                numElementos,
                timeStamp,
                nifEmisor,
                nombreEmisor,
                nifFuncionario,
                nombreCompletoFuncionario,
                seudonimo,
                codProcedimiento,
                nombreProcedimiento,
                codigoUnidadTramitadora,
                consentimiento,
                finalidad,
                idExpediente,
                identificadorSolicitante,
                nombreSolicitante,
                unidadTramitadora,
                apellido1,
                apellido2,
                documentacion,
                nombre,
                nombreCompleto,
                tipoDocumentacion,
                fechaGeneracion,
                idSolicitud,
                idTransmision,
                datosEspecificosPeticion
        );
    }
    
    
    private SCDHPAJUv3PeticionDatosEspecificos establecerDatosEspecificosPeticion(
            String municipioSolicitud, String numeroAnyos, String provinciaSolicitud,
            String nombreTipoDocumentacion, String valorDocumentacion, String NIA
    ){
    
        
        
       
        
        
        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Documentacion documentacion
                = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Documentacion();
        documentacion.setTipo(nombreTipoDocumentacion);
        documentacion.setValor(valorDocumentacion);
        
        
        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Titular titular = 
                 new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Titular();
        
        
        titular.setDocumentacion(documentacion);
        titular.setNIA(NIA);
        
        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Solicitud solicitud =
                new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Solicitud();
        
        solicitud.setMunicipioSolicitud(municipioSolicitud);
        solicitud.setNumeroAnyos(numeroAnyos);
        solicitud.setProvinciaSolicitud(provinciaSolicitud);
        solicitud.setTitular(titular);
        
        SCDHPAJUv3PeticionDatosEspecificos datosEspecificos = new SCDHPAJUv3PeticionDatosEspecificos();
        
        datosEspecificos.setSolicitud(solicitud);
        
        return datosEspecificos;
    }
    
    
    public RespuestaClientAdapter<SCDHPAJUv3RespuestaDatosEspecificos> peticionSincrona(
                String codigoEstado, String codigoEstadoSecundario, 
                String literalError, String literalErrorSec, Integer tiempoEstimadoRespuesta, 
                String codigoCertificado, String idPeticion, 
                String numElementos, String timeStamp, String nifEmisor, 
                String nombreEmisor, String nifFuncionario, 
                String nombreCompletoFuncionario, String seudonimo, String codProcedimiento, 
                String nombreProcedimiento, 
                String codigoUnidadTramitadora, Consentimiento consentimiento, 
                String finalidad, String idExpediente, 
                String identificadorSolicitante, String nombreSolicitante, 
                String unidadTramitadora, String apellido1, String apellido2, 
                String documentacion, String nombre, String nombreCompleto, 
                TipoDocumentacion tipoDocumentacion, String fechaGeneracion, 
                String idSolicitud, String idTransmision,
                String municipioSolicitud, String numeroAnyos, String provinciaSolicitud,
                String nombreTipoDocumentacion, String valorDocumentacion, String NIA
                ) {
        
        
        SCDHPAJUv3PeticionDatosEspecificos datosEspecificosPeticion = 
                establecerDatosEspecificosPeticion(municipioSolicitud, numeroAnyos,
                provinciaSolicitud, nombreTipoDocumentacion,valorDocumentacion, NIA);
        
        
        return this.peticionSincronaEspecifica(
                codigoEstado,
                codigoEstadoSecundario,
                literalError,
                literalErrorSec,
                tiempoEstimadoRespuesta,
                codigoCertificado,
                idPeticion,
                numElementos,
                timeStamp,
                nifEmisor,
                nombreEmisor,
                nifFuncionario,
                nombreCompletoFuncionario,
                seudonimo,
                codProcedimiento,
                nombreProcedimiento,
                codigoUnidadTramitadora,
                consentimiento,
                finalidad,
                idExpediente,
                identificadorSolicitante,
                nombreSolicitante,
                unidadTramitadora,
                apellido1,
                apellido2,
                documentacion,
                nombre,
                nombreCompleto,
                tipoDocumentacion,
                fechaGeneracion,
                idSolicitud,
                idTransmision,
                datosEspecificosPeticion
        );
    }

    @Override
    protected SCDHPAJUv3RespuestaDatosEspecificos element2DatosEspecificos(Element elementDatosEspecificos) {
        
        SCDHPAJUv3RespuestaDatosEspecificos datosEspecificos;
        try {
            XmlManager<SCDHPAJUv3RespuestaDatosEspecificos> manager
                    = new XmlManager<SCDHPAJUv3RespuestaDatosEspecificos>(SCDHPAJUv3RespuestaDatosEspecificos.class);
            datosEspecificos = manager.generateItem(elementDatosEspecificos);
            return datosEspecificos;
        } catch (JAXBException ex) {
            Logger.getLogger(SCDHPAJUv3RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
