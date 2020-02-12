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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author gdeignacio
 */
public class DocumentsTest {
    
    public DocumentsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of stringToElement method, of class XmlUtils.
     */
    @Test
    public void testStringToElement() {
        
        String xml = "<datosEspecificos xmlns=\"http://intermediacion.redsara.es/scsp/esquemas/datosespecificos\"><Retorno><Estado><CodigoEstado>0</CodigoEstado><Literal>TRAMITADA</Literal></Estado><Consulta><Matricula>6201CXP</Matricula></Consulta><DatosVehiculo><DatosGenerales><DescripcionVehiculo><Bastidor>VF1BBR8EF31799191</Bastidor><Marca><Codigo>01551</Codigo><Descripcion>RENAULT</Descripcion></Marca><Modelo>CLIO</Modelo></DescripcionVehiculo><DomicilioVehiculoDGT><Municipio>CAMPANET</Municipio></DomicilioVehiculoDGT><DomicilioVehiculoINE><Municipio>CAMPANET</Municipio></DomicilioVehiculoINE><Matriculacion><FechaMatriculacion>2004-06-29</FechaMatriculacion><Matricula>6201CXP</Matricula></Matriculacion><Titular><DomicilioDGT><NombreVia>CAN LLIS POL 2 PARCELA 20</NombreVia><CodigoPostal>07310</CodigoPostal><Municipio>CAMPANET</Municipio><Provincia><Codigo>PM</Codigo><Descripcion>ILLES BALEARS</Descripcion></Provincia></DomicilioDGT></Titular></DatosGenerales><DatosTecnicos><NivelEmisiones>EURO 3A</NivelEmisiones><Masas><MasaMaximaTecnica>0</MasaMaximaTecnica><MasaServicio>0</MasaServicio><MasaMaxima>1540</MasaMaxima><Tara>995</Tara></Masas><Plazas><Mixtas>000</Mixtas><Normales>5</Normales><NumeroPlazasPie>0</NumeroPlazasPie></Plazas><Potencias><Cilindrada>1461.0</Cilindrada><PotenciaFiscal>11.02</PotenciaFiscal><PotenciaNetaMaxima>0.0</PotenciaNetaMaxima><RelacionPotenciaPeso>0.0</RelacionPotenciaPeso></Potencias></DatosTecnicos><DatosTramites><FechaTransferencia>2013-05-30</FechaTransferencia></DatosTramites></DatosVehiculo></Retorno></datosEspecificos>";
        //String xml = "<datosEspecificos><Retorno><Estado><CodigoEstado>0</CodigoEstado><Literal>TRAMITADA</Literal></Estado><Consulta><Matricula>6201CXP</Matricula></Consulta><DatosVehiculo><DatosGenerales><DescripcionVehiculo><Bastidor>VF1BBR8EF31799191</Bastidor><Marca><Codigo>01551</Codigo><Descripcion>RENAULT</Descripcion></Marca><Modelo>CLIO</Modelo></DescripcionVehiculo><DomicilioVehiculoDGT><Municipio>CAMPANET</Municipio></DomicilioVehiculoDGT><DomicilioVehiculoINE><Municipio>CAMPANET</Municipio></DomicilioVehiculoINE><Matriculacion><FechaMatriculacion>2004-06-29</FechaMatriculacion><Matricula>6201CXP</Matricula></Matriculacion><Titular><DomicilioDGT><NombreVia>CAN LLIS POL 2 PARCELA 20</NombreVia><CodigoPostal>07310</CodigoPostal><Municipio>CAMPANET</Municipio><Provincia><Codigo>PM</Codigo><Descripcion>ILLES BALEARS</Descripcion></Provincia></DomicilioDGT></Titular></DatosGenerales><DatosTecnicos><NivelEmisiones>EURO 3A</NivelEmisiones><Masas><MasaMaximaTecnica>0</MasaMaximaTecnica><MasaServicio>0</MasaServicio><MasaMaxima>1540</MasaMaxima><Tara>995</Tara></Masas><Plazas><Mixtas>000</Mixtas><Normales>5</Normales><NumeroPlazasPie>0</NumeroPlazasPie></Plazas><Potencias><Cilindrada>1461.0</Cilindrada><PotenciaFiscal>11.02</PotenciaFiscal><PotenciaNetaMaxima>0.0</PotenciaNetaMaxima><RelacionPotenciaPeso>0.0</RelacionPotenciaPeso></Potencias></DatosTecnicos><DatosTramites><FechaTransferencia>2013-05-30</FechaTransferencia></DatosTramites></DatosVehiculo></Retorno></datosEspecificos>";
        /*
        String xml = "" +
//                "<?xml version=\"1.0\"?>\n" +
//"<!-- Edited by XMLSpy -->\n" +
"<catalog>\n" +
"   <product description=\"Cardigan Sweater\" product_image=\"cardigan.jpg\">\n" +
"      <catalog_item gender=\"Men's\">\n" +
"         <item_number>QWZ5671</item_number>\n" +
"         <price>39.95</price>\n" +
"         <size description=\"Medium\">\n" +
"            <color_swatch image=\"red_cardigan.jpg\">Red</color_swatch>\n" +
"            <color_swatch image=\"burgundy_cardigan.jpg\">Burgundy</color_swatch>\n" +
"         </size>\n" +
"         <size description=\"Large\">\n" +
"            <color_swatch image=\"red_cardigan.jpg\">Red</color_swatch>\n" +
"            <color_swatch image=\"burgundy_cardigan.jpg\">Burgundy</color_swatch>\n" +
"         </size>\n" +
"      </catalog_item>    \n" +
"   </product>\n" +
"</catalog>";
        */
        
        System.out.println(xml);
        
        Element expResult = null;
        Element result = null;
        try {
            result = XmlUtils.stringToElement(xml);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos datosEspecificos = null;
       
       System.out.println(SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos.class.getSuperclass().getPackage().getAnnotation(XmlSchema.class));
        
       //datosEspecificos = JAXB.unmarshal(xml, SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos.class);
        
       SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos respuesta = new SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos();
       
       
       
     
        
        try {
            XmlManager<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> manager =
                    new XmlManager<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos>(
                            SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos.class
                    );
            
            datosEspecificos = manager.generateItem(xml);
            
            System.out.println(datosEspecificos);
            
        } catch (JAXBException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println(datosEspecificos.getRetorno());
        
        
        System.out.println(result);
        
        
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
