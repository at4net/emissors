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
package es.caib.scsp.utils.xml;

import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * @author gdeignacio
 */
//@XmlRootElement(name = "datosEspecificos" , namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos")
@XmlRootElement(name = "datosEspecificos")  
public class NIVRENTIv3PeticionDatosEspecificosTest 
        extends es.caib.scsp.esquemas.NIVRENTIv3.peticion.datosespecificos.DatosEspecificos {
    public NIVRENTIv3PeticionDatosEspecificosTest(){
        super();       
    }
}

