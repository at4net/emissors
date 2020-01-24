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
import es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentFacade;
import es.caib.scsp.pinbal.ws.recobriment.client.RespuestaClientAdapter;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos;
import es.caib.scsp.utils.xml.XmlManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author gdeignacio
 */
public class SVDDGTVEHICULODATOSWS01RecobrimentFacade
        extends RecobrimentFacade<
        SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos, SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> {

    public SVDDGTVEHICULODATOSWS01RecobrimentFacade(String app) {
        super(app);
    }

    /**
     *
     * @param datosEspecificosPeticion
     * @return
     */
    @Override
    protected Element datosEspecificos2Element(SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos datosEspecificosPeticion){
        
        Element elementDatosEspecificos;
        
        
        try {
            XmlManager<SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos> manager
                    = new XmlManager<SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos>(SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos.class);
            elementDatosEspecificos = manager.generateElement(datosEspecificosPeticion);
            return elementDatosEspecificos;
        } catch (JAXBException ex) {
            Logger.getLogger(SVDDGTVEHICULODATOSWS01RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SVDDGTVEHICULODATOSWS01RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
            
        
        
        /*
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            JAXB.marshal(datosEspecificosPeticion, new DOMResult(document));
            elementDatosEspecificos = document.getDocumentElement();

            return elementDatosEspecificos;

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SVDDGTVEHICULODATOSWS01RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
        */
        
    }

    @Override
    protected RespuestaClientAdapter<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> peticionSincronaEspecifica(
                String codigoEstado, String codigoEstadoSecundario, 
                String literalError, Integer tiempoEstimadoRespuesta, 
                String codigoCertificado, String idPeticion, 
                String numElementos, String timeStamp, String nifEmisor, 
                String nombreEmisor, String nifFuncionario, 
                String nombreCompletoFuncionario, String codProcedimiento, 
                String nombreProcedimiento, Consentimiento consentimiento, 
                String finalidad, String idExpediente, 
                String identificadorSolicitante, String nombreSolicitante, 
                String unidadTramitadora, String apellido1, String apellido2, 
                String documentacion, String nombre, String nombreCompleto, 
                TipoDocumentacion tipoDocumentacion, String fechaGeneracion, 
                String idSolicitud, String idTransmision, 
                SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos datosEspecificosPeticion) {
        
        return this.peticionSincrona(
                codigoEstado,
                codigoEstadoSecundario,
                literalError,
                tiempoEstimadoRespuesta,
                codigoCertificado,
                idPeticion,
                numElementos,
                timeStamp,
                nifEmisor,
                nombreEmisor,
                nifFuncionario,
                nombreCompletoFuncionario,
                codProcedimiento,
                nombreProcedimiento,
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
    
    
    private SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos establecerDatosEspecificosPeticion(
            String bastidor, String matricula, String NIVE
    ){
    
        SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos datosEspecificos = new SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos();
        
        es.caib.scsp.esquemas.SVDDGTVEHICULODATOSWS01v3.peticion.datosespecificos.Consulta consulta =
                new es.caib.scsp.esquemas.SVDDGTVEHICULODATOSWS01v3.peticion.datosespecificos.Consulta();
        consulta.setBastidor(bastidor);
        consulta.setMatricula(matricula);
        consulta.setNIVE(NIVE);   
        
        datosEspecificos.setConsulta(consulta);
        
        return datosEspecificos;
    }
    
    
    public RespuestaClientAdapter<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> peticionSincrona(
                String codigoEstado, String codigoEstadoSecundario, 
                String literalError, Integer tiempoEstimadoRespuesta, 
                String codigoCertificado, String idPeticion, 
                String numElementos, String timeStamp, String nifEmisor, 
                String nombreEmisor, String nifFuncionario, 
                String nombreCompletoFuncionario, String codProcedimiento, 
                String nombreProcedimiento, Consentimiento consentimiento, 
                String finalidad, String idExpediente, 
                String identificadorSolicitante, String nombreSolicitante, 
                String unidadTramitadora, String apellido1, String apellido2, 
                String documentacion, String nombre, String nombreCompleto, 
                TipoDocumentacion tipoDocumentacion, String fechaGeneracion, 
                String idSolicitud, String idTransmision,
                String bastidor, String matricula, String NIVE
                ) {
        
        
        SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos datosEspecificosPeticion = 
                establecerDatosEspecificosPeticion(bastidor, matricula, NIVE);
        
        
        return this.peticionSincronaEspecifica(
                codigoEstado,
                codigoEstadoSecundario,
                literalError,
                tiempoEstimadoRespuesta,
                codigoCertificado,
                idPeticion,
                numElementos,
                timeStamp,
                nifEmisor,
                nombreEmisor,
                nifFuncionario,
                nombreCompletoFuncionario,
                codProcedimiento,
                nombreProcedimiento,
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
    
    
    
    
    
    

}
