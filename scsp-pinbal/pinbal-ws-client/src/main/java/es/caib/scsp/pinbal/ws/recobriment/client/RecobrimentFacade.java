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

/**
 *
 * @author gdeignacio
 * @param <T>
 */
public class RecobrimentFacade<T, S> {
    
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
    
    public RespuestaClientAdapter peticionSincrona(PeticionClientAdapter peticionClient){
        Peticion peticion = peticionClient2Peticion(peticionClient);
        Respuesta response = peticionSincrona(peticion);
        RespuestaClientAdapter respuesta = respuesta2RespuestaClientAdapter(response);
        return respuesta;
    }
    
    
    public RespuestaClientAdapter peticionSincrona(
            String codigoEstado, String codigoEstadoSecundario, String literalError, Integer tiempoEstimadoRespuesta,
            String codigoCertificado, String idPeticion, String numElementos, String timeStamp,
            String nifEmisor, String nombreEmisor, String nifFuncionario, String nombreCompletoFuncionario,
            String codProcedimiento, String nombreProcedimiento, Consentimiento consentimiento, String finalidad, 
            String idExpediente, String identificadorSolicitante, String nombreSolicitante, String unidadTramitadora,
            String apellido1, String apellido2, String documentacion, String nombre, String nombreCompleto,
            TipoDocumentacion tipoDocumentacion, String fechaGeneracion, String idSolicitud, String idTransmision,
            T datosEspecificos
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
        
        SolicitudTransmisionClientAdapter solicitudTransmision = new SolicitudTransmisionClientAdapter();
        
        solicitudTransmision.setNifEmisor(nifEmisor);
        solicitudTransmision.setNombreEmisor(nombreEmisor);
        solicitudTransmision.setNifFuncionario(nifFuncionario);
        solicitudTransmision.setNombreCompletoFuncionario(nombreCompletoFuncionario);
        solicitudTransmision.setCodProcedimiento(codProcedimiento);
        solicitudTransmision.setNombreProcedimiento(nombreProcedimiento);
        solicitudTransmision.setConsentimiento(consentimiento);
        solicitudTransmision.setFinalidad(finalidad);
        solicitudTransmision.setIdExpediente(idExpediente);
        solicitudTransmision.setIdentificadorSolicitante(identificadorSolicitante);
        solicitudTransmision.setNombreSolicitante(nombreSolicitante);
        solicitudTransmision.setUnidadTramitadora(unidadTramitadora);
        solicitudTransmision.setApellido1(apellido1);
        solicitudTransmision.setApellido2(apellido2);
        solicitudTransmision.setDocumentacion(documentacion);
        solicitudTransmision.setNombre(nombre);
        solicitudTransmision.setNombreCompleto(nombreCompleto);
        solicitudTransmision.setTipoDocumentacion(tipoDocumentacion);
        solicitudTransmision.setCodigoCertificado(codigoCertificado);
        solicitudTransmision.setFechaGeneracion(fechaGeneracion);
        solicitudTransmision.setIdSolicitud(idSolicitud);
        solicitudTransmision.setIdTransmision(idTransmision);
        
        //solicitudTransmision.setDatosEspecificos(datosEspecificos);
        
        List<SolicitudTransmisionClientAdapter> solicitudesTransmision = new ArrayList<SolicitudTransmisionClientAdapter>();
        solicitudesTransmision.add(solicitudTransmision);
        
        peticionClient.setSolicitudesClient(solicitudesTransmision);
        
        return peticionSincrona(peticionClient);
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
                            return solicitudTransmisionClientAdapter2SolicitudTransmision(solicitudTransmisionClient);
                        }
                    };
        return Lists.transform(solicitudesTransmisionClient, solicitudesTransmision);  
    }
    
    
    private SolicitudTransmision solicitudTransmisionClientAdapter2SolicitudTransmision(SolicitudTransmisionClientAdapter solicitudTransmisionClient) {
        
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
        
        SolicitudTransmision solicitudTransmision = RecobrimentUtils.establecerSolicitudTransmision(
                datosGenericos, 
                solicitudTransmisionClient.getDatosEspecificos()
        );
        
        return solicitudTransmision;
    
    }

    private RespuestaClientAdapter respuesta2RespuestaClientAdapter(Respuesta response) {
        
        RespuestaClientAdapter respuesta = new RespuestaClientAdapter();
        
        
        
        
        return respuesta;
    }
    
    
}
