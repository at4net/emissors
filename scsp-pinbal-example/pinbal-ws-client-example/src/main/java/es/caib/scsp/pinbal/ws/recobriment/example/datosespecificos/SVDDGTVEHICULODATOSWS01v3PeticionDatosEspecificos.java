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
package es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos;
        
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gdeignacio
 */
@XmlRootElement(name = "DatosEspecificos")
public class SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos 
        extends es.caib.scsp.esquemas.SVDDGTVEHICULODATOSWS01v3.peticion.datosespecificos.DatosEspecificos {
    public SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos(){
        super();
    }
}
