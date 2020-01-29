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

import es.caib.scsp.pinbal.ws.recobriment.facade.TransmisionDatosClientAdapter;
import es.caib.pinbal.ws.recobriment.Consentimiento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdeignacio
 * @param <TDatosEspecificosRespuesta>
 */
public class RespuestaClientAdapter<TDatosEspecificosRespuesta> implements Serializable {

    RespuestaClientAdapter(){
        this.transmisionesClient = new ArrayList<TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>>();
    }
    
    // Estado
    private String codigoEstado;
    private String codigoEstadoSecundario;
    private String literalError;
    private String literalErrorSec;
    private Integer tiempoEstimadoRespuesta;
    
    
   // Atributos
    private String codigoCertificado;
    private String idPeticion;
    private String numElementos;
    private String timeStamp;

    // Solicitante
    
    private String codigoUnidadTramitadora;
    private Consentimiento consentimiento;
    private String finalidad;
    private String idExpediente;
    private String identificadorSolicitante;
    private String nombreSolicitante;
    private String unidadTramitadora;
    
    // Funcionario
    
    private String nifFuncionario;
    private String nombreCompletoFuncionario;
    private String seudonimo;
    
    // Procedimiento
    private String codProcedimiento;
    private String nombreProcedimiento;
    
    
    private List<TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>> transmisionesClient;
    
    
    
    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getCodigoEstadoSecundario() {
        return codigoEstadoSecundario;
    }

    public void setCodigoEstadoSecundario(String codigoEstadoSecundario) {
        this.codigoEstadoSecundario = codigoEstadoSecundario;
    }

    public String getLiteralError() {
        return literalError;
    }

    public void setLiteralError(String literalError) {
        this.literalError = literalError;
    }

    public String getLiteralErrorSec() {
        return literalErrorSec;
    }

    public void setLiteralErrorSec(String literalErrorSec) {
        this.literalErrorSec = literalErrorSec;
    }

    public Integer getTiempoEstimadoRespuesta() {
        return tiempoEstimadoRespuesta;
    }

    public void setTiempoEstimadoRespuesta(Integer tiempoEstimadoRespuesta) {
        this.tiempoEstimadoRespuesta = tiempoEstimadoRespuesta;
    }

    public String getCodigoCertificado() {
        return codigoCertificado;
    }

    public void setCodigoCertificado(String codigoCertificado) {
        this.codigoCertificado = codigoCertificado;
    }

    public String getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(String idPeticion) {
        this.idPeticion = idPeticion;
    }

    public String getNumElementos() {
        return numElementos;
    }

    public void setNumElementos(String numElementos) {
        this.numElementos = numElementos;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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

    public List<TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>> getTransmisionesClient() {
        return transmisionesClient;
    }

    public void setTransmisionesClient(List<TransmisionDatosClientAdapter<TDatosEspecificosRespuesta>> transmisionesClient) {
        this.transmisionesClient = transmisionesClient;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idPeticion != null ? this.idPeticion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RespuestaClientAdapter<?> other = (RespuestaClientAdapter<?>) obj;
        if ((this.idPeticion == null) ? (other.idPeticion != null) : !this.idPeticion.equals(other.idPeticion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RespuestaClientAdapter{" + "codigoEstado=" + codigoEstado + ", codigoEstadoSecundario=" + codigoEstadoSecundario + ", literalError=" + literalError + ", literalErrorSec=" + literalErrorSec + ", tiempoEstimadoRespuesta=" + tiempoEstimadoRespuesta + ", codigoCertificado=" + codigoCertificado + ", idPeticion=" + idPeticion + ", numElementos=" + numElementos + ", timeStamp=" + timeStamp + ", codigoUnidadTramitadora=" + codigoUnidadTramitadora + ", consentimiento=" + consentimiento + ", finalidad=" + finalidad + ", idExpediente=" + idExpediente + ", identificadorSolicitante=" + identificadorSolicitante + ", nombreSolicitante=" + nombreSolicitante + ", unidadTramitadora=" + unidadTramitadora + ", nifFuncionario=" + nifFuncionario + ", nombreCompletoFuncionario=" + nombreCompletoFuncionario + ", seudonimo=" + seudonimo + ", codProcedimiento=" + codProcedimiento + ", nombreProcedimiento=" + nombreProcedimiento + ", transmisionesClient=" + transmisionesClient + '}';
    }
    
    
    
    
    
    
}
