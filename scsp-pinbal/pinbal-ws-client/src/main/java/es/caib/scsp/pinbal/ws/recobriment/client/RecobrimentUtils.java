/*
 * Copyright 2018 gdeignacio.
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
import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.DatosGenericos;
import es.caib.pinbal.ws.recobriment.Emisor;
import es.caib.pinbal.ws.recobriment.Estado;
import es.caib.pinbal.ws.recobriment.Funcionario;
import es.caib.pinbal.ws.recobriment.Peticion;
import es.caib.pinbal.ws.recobriment.Procedimiento;
import es.caib.pinbal.ws.recobriment.Solicitante;
import es.caib.pinbal.ws.recobriment.SolicitudTransmision;
import es.caib.pinbal.ws.recobriment.Solicitudes;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import es.caib.pinbal.ws.recobriment.Titular;
import es.caib.pinbal.ws.recobriment.Transmision;
import java.util.List;
import org.w3c.dom.Element;

/**
 *
 * @author gdeignacio
 */
public class RecobrimentUtils {

    public static Estado establecerEstado(String codigoEstado, 
            String codigoEstadoSecundario, 
            String literalError,
            Integer tiempoEstimadoRespuesta
    ){
        Estado estado = new Estado();
        estado.setCodigoEstado(codigoEstado);
        estado.setCodigoEstadoSecundario(codigoEstadoSecundario);
        estado.setLiteralError(literalError);
        estado.setTiempoEstimadoRespuesta(tiempoEstimadoRespuesta);
        return estado;
    }
    
    public static Atributos establecerAtributos(
            String codigoCertificado,
            Estado estado,
            String idPeticion,
            String numElementos,
            String timeStamp
    ) {

        Atributos atributos = new Atributos();
        atributos.setCodigoCertificado(codigoCertificado);
        atributos.setEstado(estado);
        atributos.setIdPeticion(idPeticion);
        atributos.setNumElementos(numElementos);
        atributos.setTimeStamp(timeStamp);
        return atributos;
    }

    public static Emisor establecerEmisor(
            String nifEmisor,
            String nombreEmisor
    ) {
        Emisor emisor = new Emisor();
        emisor.setNifEmisor(nifEmisor);
        emisor.setNombreEmisor(nombreEmisor);
        return emisor;
    }

    public static Solicitante establecerSolicitante(
            Consentimiento consentimiento,
            String finalidad,
            Funcionario funcionario,
            String idExpediente,
            String identificadorSolicitante,
            String nombreSolicitante,
            Procedimiento procedimiento,
            String unidadTramitadora
    ) {
        Solicitante solicitante = new Solicitante();
        solicitante.setConsentimiento(consentimiento);
        solicitante.setFinalidad(finalidad);
        solicitante.setFuncionario(funcionario);
        solicitante.setIdExpediente(idExpediente);
        solicitante.setIdentificadorSolicitante(identificadorSolicitante);
        solicitante.setNombreSolicitante(nombreSolicitante);
        solicitante.setProcedimiento(procedimiento);
        solicitante.setUnidadTramitadora(unidadTramitadora);
        return solicitante;
    }
    
    public static Titular establecerTitular(
            String apellido1,
            String apellido2,
            String documentacion,
            String nombre,
            String nombreCompleto,
            TipoDocumentacion tipoDocumentacion
    ) {
        Titular titular = new Titular();
        titular.setApellido1(apellido1);
        titular.setApellido2(apellido2);
        titular.setDocumentacion(documentacion);
        titular.setNombre(nombre);
        titular.setNombreCompleto(nombreCompleto);
        titular.setTipoDocumentacion(tipoDocumentacion);
        return titular;
    }
    
    public static Transmision establecerTransmision(
            String codigoCertificado,
            String fechaGeneracion,
            String idSolicitud,
            String idTransmision
    ) {
        Transmision transmision = new Transmision();
        transmision.setCodigoCertificado(codigoCertificado);
        transmision.setFechaGeneracion(fechaGeneracion);
        transmision.setIdSolicitud(idSolicitud);
        transmision.setIdTransmision(idTransmision);
        return transmision;
    }
    

    public static DatosGenericos establecerDatosGenericos(
            Emisor emisor,
            Solicitante solicitante,
            Titular titular,
            Transmision transmision
    ) {

        DatosGenericos datosGenericos = new DatosGenericos();
        datosGenericos.setEmisor(emisor);
        datosGenericos.setSolicitante(solicitante);
        datosGenericos.setTitular(titular);
        datosGenericos.setTransmision(transmision);
        return datosGenericos;
    }
  
    public static SolicitudTransmision establecerSolicitudTransmision(
            DatosGenericos datosGenericos, Element datosEspecificos
    ){
        SolicitudTransmision solicitudTransmision = new SolicitudTransmision();
        solicitudTransmision.setDatosGenericos(datosGenericos);
        solicitudTransmision.setDatosEspecificos(datosEspecificos);
        return solicitudTransmision;
    }
    
    public static Solicitudes establecerSolicitudes(
            List<SolicitudTransmision> solicitudesTransmision
    ) {

        Solicitudes solicitudes = new Solicitudes();
        solicitudes.getSolicitudTransmision().addAll(solicitudesTransmision);
        return solicitudes;
    }

    public static Peticion establecerPeticion(
            Atributos atributos,
            Solicitudes solicitudes
    ) {
        Peticion pet = new Peticion();
        pet.setAtributos(atributos);
        pet.setSolicitudes(solicitudes);
        return pet;
    }

    public static Funcionario establecerFuncionario(
            String nifFuncionario,
            String nombreCompletoFuncionario
    ) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNifFuncionario(nifFuncionario);
        funcionario.setNombreCompletoFuncionario(nombreCompletoFuncionario);
        return funcionario;
    }
    
    public static Procedimiento establecerProcedimiento(
            String codProcedimiento,
            String nombreProcedimiento
    ) {
        Procedimiento procedimiento = new Procedimiento();
        procedimiento.setCodProcedimiento(codProcedimiento);
        procedimiento.setNombreProcedimiento(nombreProcedimiento);
        return procedimiento;
    }

    
    

}
