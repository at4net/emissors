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

/**
 *
 * @author gdeignacio
 */


import es.caib.pinbal.ws.recobriment.DatosGenericos;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.cxf.jaxb.JAXBToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;


/**
 * <p>Java class for transmisionDatos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transmisionDatos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datosEspecificos" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="datosGenericos" type="{http://www.caib.es/pinbal/ws/recobriment}datosGenericos" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transmisionDatosPinbalClient", namespace = "http://www.caib.es/pinbal/ws/recobriment", propOrder = {
    "datosEspecificos",
    "datosGenericos",
    "id"
})
public class TransmisionDatosPinbalClient {

    @XmlAnyElement(lax = true)
    protected Object datosEspecificos;
    protected DatosGenericos datosGenericos;
    protected String id;

    /**
     * Gets the value of the datosEspecificos property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDatosEspecificos() {
        return datosEspecificos;
    }

    /**
     * Sets the value of the datosEspecificos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDatosEspecificos(Object value) {
        this.datosEspecificos = value;
    }

    /**
     * Gets the value of the datosGenericos property.
     * 
     * @return
     *     possible object is
     *     {@link DatosGenericos }
     *     
     */
    public DatosGenericos getDatosGenericos() {
        return datosGenericos;
    }

    /**
     * Sets the value of the datosGenericos property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosGenericos }
     *     
     */
    public void setDatosGenericos(DatosGenericos value) {
        this.datosGenericos = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
