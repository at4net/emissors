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
package es.caib.ajuviv.pinbal.ws.recobriment.facade;

import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import es.caib.scsp.pinbal.ws.recobriment.facade.RecobrimentFacade;
import es.caib.scsp.pinbal.ws.recobriment.facade.RespuestaClientAdapter;
import es.caib.ajuviv.pinbal.ws.recobriment.datosespecificos.AEAT103Iv3PeticionDatosEspecificos;
import es.caib.ajuviv.pinbal.ws.recobriment.datosespecificos.AEAT103Iv3RespuestaDatosEspecificos;
import es.caib.scsp.utils.xml.XmlManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;

/**
 *
 * @author gdeignacio
 */
public class AEAT103Iv3RecobrimentFacade
        extends RecobrimentFacade<
        AEAT103Iv3PeticionDatosEspecificos, AEAT103Iv3RespuestaDatosEspecificos> {

    public AEAT103Iv3RecobrimentFacade(String app) {
        super(app);
    }

    /**
     *
     * @param datosEspecificosPeticion
     * @return
     */
    @Override
    protected Element datosEspecificos2Element(AEAT103Iv3PeticionDatosEspecificos datosEspecificosPeticion){
        
        Element elementDatosEspecificos;
        
        try {
            XmlManager<AEAT103Iv3PeticionDatosEspecificos> manager
                    = new XmlManager<AEAT103Iv3PeticionDatosEspecificos>(AEAT103Iv3PeticionDatosEspecificos.class);
            elementDatosEspecificos = manager.generateElement(datosEspecificosPeticion);
            return elementDatosEspecificos;
        } catch (JAXBException ex) {
            Logger.getLogger(AEAT103Iv3RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AEAT103Iv3RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
            
 
    }

    @Override
    protected RespuestaClientAdapter<AEAT103Iv3RespuestaDatosEspecificos> peticionSincronaEspecifica(
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
                AEAT103Iv3PeticionDatosEspecificos datosEspecificosPeticion) {
        
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
    
    
    private AEAT103Iv3PeticionDatosEspecificos establecerDatosEspecificosPeticion(
            String municipioSolicitud, String numeroAnyos, String provinciaSolicitud,
            String nombreTipoDocumentacion, String valorDocumentacion, String NIA
    ){
    
        es.caib.scsp.esquemas.AEAT103Iv3.peticion.datosespecificos.Documentacion documentacion
                = new es.caib.scsp.esquemas.AEAT103Iv3.peticion.datosespecificos.Documentacion();
        documentacion.setTipo(nombreTipoDocumentacion);
        documentacion.setValor(valorDocumentacion);
        
        
        es.caib.scsp.esquemas.AEAT103Iv3.peticion.datosespecificos.Titular titular = 
                 new es.caib.scsp.esquemas.AEAT103Iv3.peticion.datosespecificos.Titular();
        
        
        titular.setDocumentacion(documentacion);
        titular.setNIA(NIA);
        
        es.caib.scsp.esquemas.AEAT103Iv3.peticion.datosespecificos.Solicitud solicitud =
                new es.caib.scsp.esquemas.AEAT103Iv3.peticion.datosespecificos.Solicitud();
        
        solicitud.setMunicipioSolicitud(municipioSolicitud);
        solicitud.setNumeroAnyos(numeroAnyos);
        solicitud.setProvinciaSolicitud(provinciaSolicitud);
        solicitud.setTitular(titular);
        
        AEAT103Iv3PeticionDatosEspecificos datosEspecificos = new AEAT103Iv3PeticionDatosEspecificos();
        
        datosEspecificos.setSolicitud(solicitud);
        
        return datosEspecificos;
    }
    
    
    public RespuestaClientAdapter<AEAT103Iv3RespuestaDatosEspecificos> peticionSincrona(
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
        
        
        AEAT103Iv3PeticionDatosEspecificos datosEspecificosPeticion = 
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
    protected AEAT103Iv3RespuestaDatosEspecificos element2DatosEspecificos(Element elementDatosEspecificos) {
        
        AEAT103Iv3RespuestaDatosEspecificos datosEspecificos;
        try {
            XmlManager<AEAT103Iv3RespuestaDatosEspecificos> manager
                    = new XmlManager<AEAT103Iv3RespuestaDatosEspecificos>(AEAT103Iv3RespuestaDatosEspecificos.class);
            datosEspecificos = manager.generateItem(elementDatosEspecificos);
            
            return datosEspecificos;
            
        } catch (JAXBException ex) {
            Logger.getLogger(AEAT103Iv3RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AEAT103Iv3RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
