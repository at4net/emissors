package es.caib.scsp.pinbal.ws.recobriment.facade;

import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import java.io.Serializable;
import org.w3c.dom.Element;

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

/**
 *
 * @author gdeignacio
 * @param <TDatosEspecificos>
 */
public class SolicitudTransmisionClientAdapter<TDatosEspecificos> implements Serializable {
    
     //Emisor
    private String nifEmisor;
    private String nombreEmisor;
    
    //Funcionario
    private String nifFuncionario;
    private String nombreCompletoFuncionario;
    private String seudonimo;
      
    //Procedimiento
    private String codProcedimiento;
    private String nombreProcedimiento;
    
    //Solicitante
    private String codigoUnidadTramitadora;
    private Consentimiento consentimiento;
    private String finalidad;
    private String idExpediente;
    private String identificadorSolicitante;
    private String nombreSolicitante;
    private String unidadTramitadora;
    
    //Titular
    private String apellido1;
    private String apellido2;
    private String documentacion;
    private String nombre;
    private String nombreCompleto;
    private TipoDocumentacion tipoDocumentacion;
    
    // Transmision
    private String codigoCertificado;
    private String fechaGeneracion;
    private String idSolicitud;
    private String idTransmision;
    
    private TDatosEspecificos datosEspecificos;
    
     public String getNifEmisor() {
        return nifEmisor;
    }

    public void setNifEmisor(String nifEmisor) {
        this.nifEmisor = nifEmisor;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getNifFuncionario() {
        return nifFuncionario;
    }

    public void setNifFuncionario(String nifFuncionario) {
        this.nifFuncionario = nifFuncionario;
    }

    public String getNombreCompletoFuncionario() {
        return nombreCompletoFuncionario;
    }

    public void setNombreCompletoFuncionario(String nombreCompletoFuncionario) {
        this.nombreCompletoFuncionario = nombreCompletoFuncionario;
    }

    public String getSeudonimo() {
        return seudonimo;
    }

    public void setSeudonimo(String seudonimo) {
        this.seudonimo = seudonimo;
    }
    
    public String getCodProcedimiento() {
        return codProcedimiento;
    }

    public void setCodProcedimiento(String codProcedimiento) {
        this.codProcedimiento = codProcedimiento;
    }

    public String getNombreProcedimiento() {
        return nombreProcedimiento;
    }

    public void setNombreProcedimiento(String nombreProcedimiento) {
        this.nombreProcedimiento = nombreProcedimiento;
    }

    public String getCodigoUnidadTramitadora() {
        return codigoUnidadTramitadora;
    }

    public void setCodigoUnidadTramitadora(String codigoUnidadTramitadora) {
        this.codigoUnidadTramitadora = codigoUnidadTramitadora;
    }

    public Consentimiento getConsentimiento() {
        return consentimiento;
    }

    public void setConsentimiento(Consentimiento consentimiento) {
        this.consentimiento = consentimiento;
    }

    public String getFinalidad() {
        return finalidad;
    }

    public void setFinalidad(String finalidad) {
        this.finalidad = finalidad;
    }

    public String getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(String idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getIdentificadorSolicitante() {
        return identificadorSolicitante;
    }

    public void setIdentificadorSolicitante(String identificadorSolicitante) {
        this.identificadorSolicitante = identificadorSolicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getUnidadTramitadora() {
        return unidadTramitadora;
    }

    public void setUnidadTramitadora(String unidadTramitadora) {
        this.unidadTramitadora = unidadTramitadora;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public TipoDocumentacion getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(TipoDocumentacion tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
    }

    public String getCodigoCertificado() {
        return codigoCertificado;
    }

    public void setCodigoCertificado(String codigoCertificado) {
        this.codigoCertificado = codigoCertificado;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getIdTransmision() {
        return idTransmision;
    }

    public void setIdTransmision(String idTransmision) {
        this.idTransmision = idTransmision;
    }

    public TDatosEspecificos getDatosEspecificos() {
        return datosEspecificos;
    }

    public void setDatosEspecificos(TDatosEspecificos datosEspecificos) {
        this.datosEspecificos = datosEspecificos;
    }
    
    
    
}
