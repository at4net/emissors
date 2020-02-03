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
package es.caib.scsp.pinbal.ws.recobriment.cxf;

import es.caib.pinbal.ws.recobriment.Atributos;
import es.caib.pinbal.ws.recobriment.Peticion;
import es.caib.pinbal.ws.recobriment.Solicitudes;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author gdeignacio
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.apache.cxf.jaxb.JAXBToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;


/**
 * <p>Java class for peticion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="peticion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="atributos" type="{http://www.caib.es/pinbal/ws/recobriment}atributos" minOccurs="0"/>
 *         &lt;element name="solicitudes" type="{http://www.caib.es/pinbal/ws/recobriment}solicitudes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "peticionPinbalClient", propOrder = {
    "atributos",
    "solicitudes"
})
public class PeticionPinbalClient extends Peticion {

    protected Atributos atributos;
    protected Solicitudes solicitudes;

    /**
     * Gets the value of the atributos property.
     * 
     * @return
     *     possible object is
     *     {@link Atributos }
     *     
     */
    public Atributos getAtributos() {
        return atributos;
    }

    /**
     * Sets the value of the atributos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Atributos }
     *     
     */
    public void setAtributos(Atributos value) {
        this.atributos = value;
    }

    /**
     * Gets the value of the solicitudes property.
     * 
     * @return
     *     possible object is
     *     {@link Solicitudes }
     *     
     */
    public Solicitudes getSolicitudes() {
        return solicitudes;
    }

    /**
     * Sets the value of the solicitudes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Solicitudes }
     *     
     */
    public void setSolicitudes(Solicitudes value) {
        this.solicitudes = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    public String toString() {
        return JAXBToStringBuilder.valueOf(this, JAXBToStringStyle.DEFAULT_STYLE);
    }

}
