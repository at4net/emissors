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
import java.util.List;
import java.util.logging.Logger;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.DatosGenericos;
import es.caib.pinbal.ws.recobriment.Emisor;
import es.caib.pinbal.ws.recobriment.Funcionario;
import es.caib.pinbal.ws.recobriment.Procedimiento;
import es.caib.pinbal.ws.recobriment.Solicitante;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import es.caib.pinbal.ws.recobriment.Titular;
import es.caib.pinbal.ws.recobriment.Transmision;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;

/**
 *
 * @author gdeignacio
 * @param <TDatosEspecificosPeticion>
 * @param <TDatosEspecificosRespuesta>
 */
public abstract class RecobrimentFacade<TDatosEspecificosPeticion, TDatosEspecificosRespuesta> {
    
    private static String APP = "es.caib.scsp.";
    
    protected static final Logger LOG = Logger.getLogger(RecobrimentFacade.class.getName());
     
    private RecobrimentClient client;
    
    public RecobrimentFacade(){
        this(APP);
    }
 
    public RecobrimentFacade(String app){
        DadesConnexioRecobriment dadesConnexio = new DadesConnexioRecobriment(app);
        this.client = RecobrimentClient.getClient(dadesConnexio);
    }
    
    public Respuesta peticionSincrona(Peticion peticion){
        return this.client.peticionSincrona(peticion);
    } 
    
    public RespuestaClientAdapter<TDatosEspecificosRespuesta> peticionSincrona(PeticionClientAdapter peticionClient){
        Peticion peticion = peticionClient2Peticion(peticionClient);
        Respuesta response = peticionSincrona(peticion);
        RespuestaClientAdapter<TDatosEspecificosRespuesta> respuesta = respuesta2RespuestaClientAdapter(response);
        return respuesta;
    }
    
    /**
     *
     * @param codigoEstado
     * @param codigoEstadoSecundario
     * @param literalError
     * @param tiempoEstimadoRespuesta
     * @param codigoCertificado
     * @param idPeticion
     * @param numElementos
     * @param timeStamp
     * @param nifEmisor
     * @param nombreEmisor
     * @param nifFuncionario
     * @param nombreCompletoFuncionario
     * @param codProcedimiento
     * @param nombreProcedimiento
     * @param consentimiento
     * @param finalidad
     * @param idExpediente
     * @param identificadorSolicitante
     * @param nombreSolicitante
     * @param unidadTramitadora
     * @param apellido1
     * @param apellido2
     * @param documentacion
     * @param nombre
     * @param nombreCompleto
     * @param tipoDocumentacion
     * @param fechaGeneracion
     * @param idSolicitud
     * @param idTransmision
     * @param datosEspecificosPeticion
     * @return
     */
    public abstract RespuestaClientAdapter<TDatosEspecificosRespuesta> peticionSincrona(
            String codigoEstado, String codigoEstadoSecundario, String literalError, Integer tiempoEstimadoRespuesta,
            String codigoCertificado, String idPeticion, String numElementos, String timeStamp,
            String nifEmisor, String nombreEmisor, String nifFuncionario, String nombreCompletoFuncionario,
            String codProcedimiento, String nombreProcedimiento, Consentimiento consentimiento, String finalidad, 
            String idExpediente, String identificadorSolicitante, String nombreSolicitante, String unidadTramitadora,
            String apellido1, String apellido2, String documentacion, String nombre, String nombreCompleto,
            TipoDocumentacion tipoDocumentacion, String fechaGeneracion, String idSolicitud, String idTransmision
    );
    
    protected RespuestaClientAdapter<TDatosEspecificosRespuesta> peticionSincrona(
            String codigoEstado, String codigoEstadoSecundario, String literalError, Integer tiempoEstimadoRespuesta,
            String codigoCertificado, String idPeticion, String numElementos, String timeStamp,
            String nifEmisor, String nombreEmisor, String nifFuncionario, String nombreCompletoFuncionario,
            String codProcedimiento, String nombreProcedimiento, Consentimiento consentimiento, String finalidad, 
            String idExpediente, String identificadorSolicitante, String nombreSolicitante, String unidadTramitadora,
            String apellido1, String apellido2, String documentacion, String nombre, String nombreCompleto,
            TipoDocumentacion tipoDocumentacion, String fechaGeneracion, String idSolicitud, String idTransmision,
            TDatosEspecificosPeticion datosEspecificosPeticion
    ){
        
        PeticionClientAdapter peticionClient = new PeticionClientAdapter();
        
        peticionClient.setCodigoEstado(codigoEstado);
        peticionClient.setCodigoEstadoSecundario(codigoEstadoSecundario);
        peticionClient.setLiteralError(literalError);
        peticionClient.setTiempoEstimadoRespuesta(tiempoEstimadoRespuesta);
        peticionClient.setCodigoCertificado(codigoCertificado);
        peticionClient.setIdPeticion(idPeticion);
        peticionClient.setNumElementos(numElementos);
        peticionClient.setTimeStamp(timeStamp);
        
        SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion> solicitudTransmisionClient = new SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>();
        
        solicitudTransmisionClient.setNifEmisor(nifEmisor);
        solicitudTransmisionClient.setNombreEmisor(nombreEmisor);
        solicitudTransmisionClient.setNifFuncionario(nifFuncionario);
        solicitudTransmisionClient.setNombreCompletoFuncionario(nombreCompletoFuncionario);
        solicitudTransmisionClient.setCodProcedimiento(codProcedimiento);
        solicitudTransmisionClient.setNombreProcedimiento(nombreProcedimiento);
        solicitudTransmisionClient.setConsentimiento(consentimiento);
        solicitudTransmisionClient.setFinalidad(finalidad);
        solicitudTransmisionClient.setIdExpediente(idExpediente);
        solicitudTransmisionClient.setIdentificadorSolicitante(identificadorSolicitante);
        solicitudTransmisionClient.setNombreSolicitante(nombreSolicitante);
        solicitudTransmisionClient.setUnidadTramitadora(unidadTramitadora);
        solicitudTransmisionClient.setApellido1(apellido1);
        solicitudTransmisionClient.setApellido2(apellido2);
        solicitudTransmisionClient.setDocumentacion(documentacion);
        solicitudTransmisionClient.setNombre(nombre);
        solicitudTransmisionClient.setNombreCompleto(nombreCompleto);
        solicitudTransmisionClient.setTipoDocumentacion(tipoDocumentacion);
        solicitudTransmisionClient.setCodigoCertificado(codigoCertificado);
        solicitudTransmisionClient.setFechaGeneracion(fechaGeneracion);
        solicitudTransmisionClient.setIdSolicitud(idSolicitud);
        solicitudTransmisionClient.setIdTransmision(idTransmision);
        
        solicitudTransmisionClient.setDatosEspecificos(datosEspecificosPeticion);
        
        List<SolicitudTransmisionClientAdapter> solicitudesTransmisionClient = new ArrayList<SolicitudTransmisionClientAdapter>();
        solicitudesTransmisionClient.add(solicitudTransmisionClient);
        
        peticionClient.setSolicitudesClient(solicitudesTransmisionClient);
        
        return peticionSincrona(peticionClient);
    }
    
    protected abstract Element datosEspecificos2Element(TDatosEspecificosPeticion datosEspecificosPeticion) throws JAXBException, ParserConfigurationException;
    
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
        
        List<SolicitudTransmision> solicitudesTransmision = 
                solicitudesTransmisionClientAdapter2SolicitudesTransmision(peticionClient.getSolicitudesClient());
         
        Solicitudes solicitudes = RecobrimentUtils.establecerSolicitudes(solicitudesTransmision);
        
        Peticion peticion = RecobrimentUtils.establecerPeticion(atributos, solicitudes);
        
        return peticion;
        
    }
     
    
    private List<SolicitudTransmision> solicitudesTransmisionClientAdapter2SolicitudesTransmision(List<SolicitudTransmisionClientAdapter> solicitudesTransmisionClient){
        Function<SolicitudTransmisionClientAdapter,SolicitudTransmision> solicitudesTransmision =
                    new Function<SolicitudTransmisionClientAdapter, SolicitudTransmision>(){
                        public SolicitudTransmision apply(SolicitudTransmisionClientAdapter solicitudTransmisionClient){
                            try {
                                return solicitudTransmisionClientAdapter2SolicitudTransmision(solicitudTransmisionClient);
                            } catch (JAXBException ex) {
                                Logger.getLogger(RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
                                throw new RuntimeException(ex);
                            } catch (ParserConfigurationException ex) {
                                Logger.getLogger(RecobrimentFacade.class.getName()).log(Level.SEVERE, null, ex);
                                throw new RuntimeException(ex);
                            }
                        }
                    };
        return Lists.transform(solicitudesTransmisionClient, solicitudesTransmision);  
    }
    
    
    private SolicitudTransmision solicitudTransmisionClientAdapter2SolicitudTransmision(SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion> solicitudTransmisionClient) throws JAXBException, ParserConfigurationException {
        
        Procedimiento procedimiento = RecobrimentUtils.establecerProcedimiento(
                solicitudTransmisionClient.getCodProcedimiento(), 
                solicitudTransmisionClient.getNombreProcedimiento()
        );
        
        Funcionario funcionario = RecobrimentUtils.establecerFuncionario(
              solicitudTransmisionClient.getNifFuncionario(),
              solicitudTransmisionClient.getNombreCompletoFuncionario()
        );
       
        Solicitante solicitante = RecobrimentUtils.establecerSolicitante(
                solicitudTransmisionClient.getConsentimiento(),
                solicitudTransmisionClient.getFinalidad(),
                funcionario,
                solicitudTransmisionClient.getIdExpediente(),
                solicitudTransmisionClient.getIdentificadorSolicitante(),
                solicitudTransmisionClient.getNombreSolicitante(),
                procedimiento,
                solicitudTransmisionClient.getUnidadTramitadora()
        );
        
        Emisor emisor = RecobrimentUtils.establecerEmisor(
                solicitudTransmisionClient.getNifEmisor(),
                solicitudTransmisionClient.getNombreEmisor()
        );
        
        Titular titular = RecobrimentUtils.establecerTitular(
                solicitudTransmisionClient.getApellido1(),
                solicitudTransmisionClient.getApellido2(),
                solicitudTransmisionClient.getDocumentacion(),
                solicitudTransmisionClient.getNombre(),
                solicitudTransmisionClient.getNombreCompleto(), 
                solicitudTransmisionClient.getTipoDocumentacion()
        );
        
        Transmision transmision = RecobrimentUtils.establecerTransmision(
                solicitudTransmisionClient.getCodigoCertificado(),
                solicitudTransmisionClient.getFechaGeneracion(),
                solicitudTransmisionClient.getIdSolicitud(),
                solicitudTransmisionClient.getIdTransmision()
        );
        
        DatosGenericos datosGenericos = RecobrimentUtils.establecerDatosGenericos(
                emisor, 
                solicitante, 
                titular, 
                transmision
        );
        
        Element elementDatosEspecificos = datosEspecificos2Element(solicitudTransmisionClient.getDatosEspecificos());
        SolicitudTransmision solicitudTransmision = RecobrimentUtils.establecerSolicitudTransmision(
                datosGenericos,
                elementDatosEspecificos
        );
            
        return solicitudTransmision;
    
    }

    private RespuestaClientAdapter<TDatosEspecificosRespuesta> respuesta2RespuestaClientAdapter(Respuesta response) {
        
        RespuestaClientAdapter<TDatosEspecificosRespuesta> respuesta = new RespuestaClientAdapter<TDatosEspecificosRespuesta>();
        
        
        
        
        
        return respuesta;
    }
    
    
}
