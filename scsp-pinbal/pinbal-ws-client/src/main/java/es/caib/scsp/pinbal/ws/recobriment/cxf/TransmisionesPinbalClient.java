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

import es.caib.pinbal.ws.recobriment.TransmisionDatos;
import es.caib.pinbal.ws.recobriment.Transmisiones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdeignacio
 */
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.cxf.jaxb.JAXBToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;


/**
 * <p>Java class for transmisiones complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transmisiones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transmisionDatos" type="{http://www.caib.es/pinbal/ws/recobriment}transmisionDatos" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transmisionesPinbalClient", namespace = "http://www.caib.es/pinbal/ws/recobriment",  propOrder = {
    "transmisionDatos"
})
public class TransmisionesPinbalClient  {

    @XmlElement(nillable = true)
    protected List<TransmisionDatosPinbalClient> transmisionDatos;

    /**
     * Gets the value of the transmisionDatos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transmisionDatos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransmisionDatos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransmisionDatos }
     * 
     * 
     */
    public List<TransmisionDatosPinbalClient> getTransmisionDatos() {
        if (transmisionDatos == null) {
            transmisionDatos = new ArrayList<TransmisionDatosPinbalClient>();
        }
        return this.transmisionDatos;
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
