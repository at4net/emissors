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

import es.caib.scsp.pinbal.ws.recobriment.facade.SolicitudTransmisionClientAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdeignacio
 */
public class PeticionClientAdapter<TDatosEspecificosPeticion> implements Serializable {
    
    PeticionClientAdapter(){
        this.solicitudesClient = new ArrayList<SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>>();
    }
    
    // Estado
    private String codigoEstado;
    private String codigoEstadoSecundario;
    private String literalError;
    private String literalErrorSec;
    private Integer tiempoEstimadoRespuesta;
    
    //Atributos
    private String codigoCertificado;
    private String idPeticion;
    private String numElementos;
    private String timeStamp;
    
    private List<SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>> solicitudesClient;

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


    public List<SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>> getSolicitudesClient() {
        return solicitudesClient;
    }

    public void setSolicitudesClient(List<SolicitudTransmisionClientAdapter<TDatosEspecificosPeticion>> solicitudesClient) {
        this.solicitudesClient = solicitudesClient;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.idPeticion != null ? this.idPeticion.hashCode() : 0);
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
        final PeticionClientAdapter other = (PeticionClientAdapter) obj;
        if ((this.idPeticion == null) ? (other.idPeticion != null) : !this.idPeticion.equals(other.idPeticion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PeticionClientAdapter{" + "codigoEstado=" + codigoEstado + ", codigoEstadoSecundario=" + codigoEstadoSecundario + ", literalError=" + literalError + ", literalErrorSec=" + literalErrorSec + ", tiempoEstimadoRespuesta=" + tiempoEstimadoRespuesta + ", codigoCertificado=" + codigoCertificado + ", idPeticion=" + idPeticion + ", numElementos=" + numElementos + ", timeStamp=" + timeStamp + ", solicitudesClient=" + solicitudesClient + '}';
    }

 
    
}
