/*
 * Copyright 2019 gdeignacio.
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
package es.caib.ajuviv.pinbal.ws.recobriment.datosespecificos;
        
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gdeignacio
 */
@XmlRootElement(name = "datosEspecificos")
public class ECOT103Iv3PeticionDatosEspecificos
        extends es.caib.scsp.esquemas.ECOT103Iv3.peticion.datosespecificos.DatosEspecificos {
    public ECOT103Iv3PeticionDatosEspecificos(){
        super();
    }
}
