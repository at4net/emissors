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
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
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
     * @throws JAXBException
     * @throws ParserConfigurationException
     */
    @Override
    protected Element datosEspecificos2Element(SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos datosEspecificosPeticion) throws JAXBException, ParserConfigurationException {
        XmlManager<SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos> manager
                = new XmlManager<SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos>(SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos.class);
        Element elementDatosEspecificos = manager.generateElement(datosEspecificosPeticion);
        return elementDatosEspecificos;
    }

    @Override
    public RespuestaClientAdapter<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> peticionSincrona(String codigoEstado, String codigoEstadoSecundario, String literalError, Integer tiempoEstimadoRespuesta, String codigoCertificado, String idPeticion, String numElementos, String timeStamp, String nifEmisor, String nombreEmisor, String nifFuncionario, String nombreCompletoFuncionario, String codProcedimiento, String nombreProcedimiento, Consentimiento consentimiento, String finalidad, String idExpediente, String identificadorSolicitante, String nombreSolicitante, String unidadTramitadora, String apellido1, String apellido2, String documentacion, String nombre, String nombreCompleto, TipoDocumentacion tipoDocumentacion, String fechaGeneracion, String idSolicitud, String idTransmision) {

        return this.peticionSincrona(codigoEstado, codigoEstadoSecundario, literalError, tiempoEstimadoRespuesta, codigoCertificado, idPeticion, numElementos, timeStamp, nifEmisor, nombreEmisor, nifFuncionario, nombreCompletoFuncionario, codProcedimiento, nombreProcedimiento, consentimiento, finalidad, idExpediente, identificadorSolicitante, nombreSolicitante, unidadTramitadora, apellido1, apellido2, documentacion, nombre, nombreCompleto, tipoDocumentacion, fechaGeneracion, idSolicitud, idTransmision, datosEspecificosPeticion);
    }

}
