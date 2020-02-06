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
package es.caib.scsp.pinbal.ws.recobriment.facade;

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
import es.caib.pinbal.ws.recobriment.TransmisionDatos;
import es.caib.pinbal.ws.recobriment.Transmisiones;
import es.caib.scsp.pinbal.ws.recobriment.client.DadesConnexioRecobriment;
import es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentClient;
import es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentUtils;
import java.util.ArrayList;
import java.util.logging.Level;
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
    
    protected abstract Class<TDatosEspecificosRespuesta> getDatosEspecificosRespuestaClazz();
    
    public Respuesta peticionSincrona(Peticion peticion){
        return this.client.peticionSincrona(peticion, getDatosEspecificosRespuestaClazz());
    } 
    
    public RespuestaClientAdapter<TDatosEspecificosRespuesta> peticionSincrona(PeticionClientAdapter<TDatosEspecificosPeticion> peticionClient){
        Peticion peticion = peticionClient2Peticion(peticionClient);
        Respuesta response = peticionSincrona(peticion);
        RespuestaClientAdapter<TDatosEspecificosRespuesta> respuesta = respuesta2RespuestaClientAdapter(response);
        return respuesta;
    }
    
   

    protected abstract RespuestaClientAdapter<TDatosEspecificosRespuesta> peticionSincronaEspecifica(
            String codigoEstado, String codigoEstadoSecundario, String literalError, 
            String literalErrorSec, Integer tiempoEstimadoRespuesta,
            String codigoCertificado, String idPeticion, String numElementos, String timeStamp,
            String nifEmisor, String nombreEmisor, String nifFuncionario, String nombreCompletoFuncionario,
            String seudonimo, String codProcedimiento, 
            String nombreProcedimiento, String codigoUnidadTramitadora, Consentimiento consentimiento, String finalidad, 
            String idExpediente, String identificadorSolicitante, String nombreSolicitante, String unidadTramitadora,
            String apellido1, String apellido2, String documentacion, String nombre, String nombreCompleto,
            TipoDocumentacion tipoDocumentacion, String fechaGeneracion, String idSolicitud, String idTransmision,
            TDatosEspecificosPeticion datosEspecificosPeticion
    );
    
    protected RespuestaClientAdapter<TDatosEspecificosRespuesta> peticionSincrona(
            String codigoEstado, String codigoEstadoSecundario, String literalError,
            String literalErrorSec, Integer tiempoEstimadoRespuesta,
            String codigoCertificado, String idPeticion, String numElementos, String timeStamp,
            String nifEmisor, String nombreEmisor, String nifFuncionario, String nombreCompletoFuncionario,
            String seudonimo,
            String codProcedimiento, String nombreProcedimiento, String codigoUnidadTramitadora, 
            Consentimiento consentimiento, String finalidad, 
            String idExpediente, String identificadorSolicitante, String nombreSolicitante, String unidadTramitadora,
            String apellido1, String apellido2, String documentacion, String nombre, String nombreCompleto,
            TipoDocumentacion tipoDocumentacion, String fechaGeneracion, String idSolicitud, String idTransmision,
            TDatosEspecificosPeticion datosEspecificosPeticion
    ){
        
        PeticionClientAdapter<TDatosEspecificosPeticion> peticionClient = new PeticionClientAdapter<TDatosEspecificosPeticion>();
        
        peticionClient.setCodigoEstado(codigoEstado);
        peticionClient.setCodigoEstadoSecundario(codigoEstadoSecundario);
        peticionClient.setLiteralError(literalError);
        peticionClient.setLiteralErrorSec(literalErrorSec);
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
        solicitudTransmisionClient.setSeudonimo(seudonimo);
        solicitudTransmisionClient.setCodProcedimiento(codProcedimiento);
        solicitudTransmisionClient.setNombreProcedimiento(nombreProcedimiento);
        solicitudTransmisionClient.setCodigoUnidadTramitadora(codigoUnidadTramitadora);
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
        
        List<SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>> solicitudesTransmisionClient = new ArrayList<SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>>();
        solicitudesTransmisionClient.add(solicitudTransmisionClient);
        
        peticionClient.setSolicitudesClient(solicitudesTransmisionClient);
        
        return peticionSincrona(peticionClient);
    }
    
    protected abstract Element datosEspecificos2Element(TDatosEspecificosPeticion datosEspecificosPeticion);
    
    private Peticion peticionClient2Peticion(PeticionClientAdapter peticionClient) {
        
        Estado estado = RecobrimentUtils.establecerEstado(
                peticionClient.getCodigoEstado(),
                peticionClient.getCodigoEstadoSecundario(),
                peticionClient.getLiteralError(),
                peticionClient.getLiteralErrorSec(),
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
     
    
    private List<SolicitudTransmision> solicitudesTransmisionClientAdapter2SolicitudesTransmision(
            List<SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>> solicitudesTransmisionClient)
    {
        Function<SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>,SolicitudTransmision> solicitudesTransmision =
                    new Function<SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>, SolicitudTransmision>(){
                        public SolicitudTransmision apply(SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion> solicitudTransmisionClient){
                                return solicitudTransmisionClientAdapter2SolicitudTransmision(solicitudTransmisionClient);
                        }
                    };
        return Lists.transform(solicitudesTransmisionClient, solicitudesTransmision);  
    }
    
  
    private SolicitudTransmision solicitudTransmisionClientAdapter2SolicitudTransmision(SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion> solicitudTransmisionClient){
        
        Procedimiento procedimiento = RecobrimentUtils.establecerProcedimiento(
                solicitudTransmisionClient.getCodProcedimiento(), 
                solicitudTransmisionClient.getNombreProcedimiento()
        );
        
        Funcionario funcionario = RecobrimentUtils.establecerFuncionario(
              solicitudTransmisionClient.getNifFuncionario(),
              solicitudTransmisionClient.getNombreCompletoFuncionario(),
              solicitudTransmisionClient.getSeudonimo()
        );
       
        Solicitante solicitante = RecobrimentUtils.establecerSolicitante(
                solicitudTransmisionClient.getCodigoUnidadTramitadora(),
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

        RespuestaClientAdapter<TDatosEspecificosRespuesta> respuestaClient = new RespuestaClientAdapter<TDatosEspecificosRespuesta>();

        Atributos atributos = response.getAtributos();

        if (atributos != null) {

            respuestaClient.setCodigoCertificado(atributos.getCodigoCertificado());
            respuestaClient.setIdPeticion(atributos.getIdPeticion());
            respuestaClient.setNumElementos(atributos.getNumElementos());
            respuestaClient.setTimeStamp(atributos.getTimeStamp());

            Estado estado = atributos.getEstado();

            if (estado != null) {

                respuestaClient.setCodigoEstado(estado.getCodigoEstado());
                respuestaClient.setCodigoEstadoSecundario(estado.getCodigoEstadoSecundario());
                respuestaClient.setLiteralError(estado.getLiteralError());
                respuestaClient.setLiteralErrorSec(estado.getLiteralErrorSec());
                respuestaClient.setTiempoEstimadoRespuesta(estado.getTiempoEstimadoRespuesta());
            }

            Solicitante solicitante = atributos.getSolicitante();

            if (solicitante != null) {

                respuestaClient.setCodigoUnidadTramitadora(solicitante.getCodigoUnidadTramitadora());
                respuestaClient.setConsentimiento(solicitante.getConsentimiento());
                respuestaClient.setFinalidad(solicitante.getFinalidad());
                respuestaClient.setIdExpediente(solicitante.getIdExpediente());
                respuestaClient.setIdentificadorSolicitante(solicitante.getIdentificadorSolicitante());
                respuestaClient.setNombreSolicitante(solicitante.getNombreSolicitante());
                respuestaClient.setUnidadTramitadora(solicitante.getUnidadTramitadora());

                Funcionario funcionario = solicitante.getFuncionario();

                if (funcionario != null) {
                    respuestaClient.setNifFuncionario(funcionario.getNifFuncionario());
                    respuestaClient.setNombreCompletoFuncionario(funcionario.getNombreCompletoFuncionario());
                    respuestaClient.setSeudonimo(funcionario.getSeudonimo());
                }

                Procedimiento procedimiento = solicitante.getProcedimiento();

                if (procedimiento != null) {
                    respuestaClient.setCodProcedimiento(procedimiento.getCodProcedimiento());
                    respuestaClient.setNombreProcedimiento(procedimiento.getNombreProcedimiento());
                }

            }
        }

        Transmisiones transmisiones = response.getTransmisiones();

        List<TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>> transmisionesClient
                = new ArrayList<TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>>();

        if (transmisiones != null) {
            List<TransmisionDatos> transmisionesDatos = transmisiones.getTransmisionDatos();
            if (transmisionesDatos != null) {
                transmisionesClient = transmisionesDatos2TransmisionesDatosClientAdapter(transmisionesDatos);
            }
        }

        respuestaClient.setTransmisionesClient(transmisionesClient);

        return respuestaClient;

    }
    
    
    
    private List<TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>> transmisionesDatos2TransmisionesDatosClientAdapter(
            List<TransmisionDatos> transmisionesDatos) {
        Function<TransmisionDatos, TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>> transmisionesDatosClient
                = new Function<TransmisionDatos, TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>>() {
            public TransmisionDatosClientAdapter<TDatosEspecificosRespuesta> apply(TransmisionDatos transmisionDatos) {
                return transmisionDatos2TransmisionDatosClientAdapter(transmisionDatos);
            }
        };
        return Lists.transform(transmisionesDatos, transmisionesDatosClient);
    }

    protected abstract TDatosEspecificosRespuesta element2DatosEspecificos(Element elementDatosEspecificos);
    
    private TransmisionDatosClientAdapter<TDatosEspecificosRespuesta> transmisionDatos2TransmisionDatosClientAdapter(TransmisionDatos transmisionDatos) {

        TransmisionDatosClientAdapter<TDatosEspecificosRespuesta> transmisionDatosClient
                = new TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>();

        if (transmisionDatos == null) {
            return transmisionDatosClient;
        }

        transmisionDatosClient.setId(transmisionDatos.getId());

        DatosGenericos datosGenericos = transmisionDatos.getDatosGenericos();

        if (datosGenericos != null) {

            Emisor emisor = datosGenericos.getEmisor();
            if (emisor != null) {
                transmisionDatosClient.setNifEmisor(emisor.getNifEmisor());
                transmisionDatosClient.setNombreEmisor(emisor.getNombreEmisor());
            }

            Solicitante solicitante = datosGenericos.getSolicitante();
            if (solicitante != null) {

                transmisionDatosClient.setCodigoUnidadTramitadora(solicitante.getCodigoUnidadTramitadora());
                transmisionDatosClient.setConsentimiento(solicitante.getConsentimiento());
                transmisionDatosClient.setFinalidad(solicitante.getFinalidad());
                transmisionDatosClient.setIdExpediente(solicitante.getIdExpediente());
                transmisionDatosClient.setIdentificadorSolicitante(solicitante.getIdentificadorSolicitante());
                transmisionDatosClient.setNombreSolicitante(solicitante.getNombreSolicitante());
                transmisionDatosClient.setUnidadTramitadora(solicitante.getUnidadTramitadora());

                Funcionario funcionario = solicitante.getFuncionario();
                if (funcionario != null) {
                    transmisionDatosClient.setNifFuncionario(funcionario.getNifFuncionario());
                    transmisionDatosClient.setNombreCompletoFuncionario(funcionario.getNombreCompletoFuncionario());
                    transmisionDatosClient.setSeudonimo(funcionario.getSeudonimo());
                }

                Procedimiento procedimiento = solicitante.getProcedimiento();
                if (procedimiento != null) {
                    transmisionDatosClient.setCodProcedimiento(procedimiento.getCodProcedimiento());
                    transmisionDatosClient.setNombreProcedimiento(procedimiento.getNombreProcedimiento());
                }
            }

            Titular titular = datosGenericos.getTitular();
            if (titular != null) {
                transmisionDatosClient.setApellido1(titular.getApellido1());
                transmisionDatosClient.setApellido2(titular.getApellido2());
                transmisionDatosClient.setDocumentacion(titular.getDocumentacion());
                transmisionDatosClient.setNombre(titular.getNombre());
                transmisionDatosClient.setNombreCompleto(titular.getNombreCompleto());
                transmisionDatosClient.setTipoDocumentacion(titular.getTipoDocumentacion());
            }

            Transmision transmision = datosGenericos.getTransmision();
            if (transmision != null) {
                transmisionDatosClient.setCodigoCertificado(transmision.getCodigoCertificado());
                transmisionDatosClient.setFechaGeneracion(transmision.getFechaGeneracion());
                transmisionDatosClient.setIdSolicitud(transmision.getIdSolicitud());
                transmisionDatosClient.setIdTransmision(transmision.getIdTransmision());
            }
        }

        
        Element elementDatosEspecificos = (Element) transmisionDatos.getDatosEspecificos();
        
        transmisionDatosClient.setDatosEspecificos(element2DatosEspecificos(elementDatosEspecificos));
        
        return transmisionDatosClient;

    }
    
}
