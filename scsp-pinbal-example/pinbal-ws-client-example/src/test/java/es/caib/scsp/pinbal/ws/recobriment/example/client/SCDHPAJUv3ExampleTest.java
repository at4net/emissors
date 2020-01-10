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
package es.caib.scsp.pinbal.ws.recobriment.example.client;

import es.caib.pinbal.ws.recobriment.Atributos;
import es.caib.pinbal.ws.recobriment.Emisor;
import es.caib.pinbal.ws.recobriment.Estado;
import es.caib.pinbal.ws.recobriment.Funcionario;
import es.caib.pinbal.ws.recobriment.Peticion;
import es.caib.pinbal.ws.recobriment.Procedimiento;
import es.caib.pinbal.ws.recobriment.Respuesta;
import es.caib.pinbal.ws.recobriment.Solicitante;
import es.caib.scsp.pinbal.ws.recobriment.client.DadesConnexioRecobriment;
import es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;


/**
 *
 * @author gdeignacio
 */
public class SCDHPAJUv3ExampleTest {
    
    private RecobrimentClient client;
    private SCDHPAJUv3Example example;
    
    public SCDHPAJUv3ExampleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {        
        String app = "es.caib.scsp.";
        DadesConnexioRecobriment dadesConnexio = new DadesConnexioRecobriment(app);
        System.setProperty(app  + "pinbal.client.username", "$xestib_pinbal");
        System.setProperty(app  + "pinbal.client.password", "xestib_pinbal");
        System.setProperty(app  + "pinbal.client.baseURL", "https://proves.caib.es/pinbal");
        client = RecobrimentClient.getClient(dadesConnexio);
        example = new SCDHPAJUv3Example(client);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class SCDHPAJUv3Example.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        SCDHPAJUv3Example.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    /**
     * Test of dummy method, of class SCDHPAJUv3Example.
     */
    @Test
    public void testDummy() throws Exception {
        System.out.println("dummy");
        example.dummy();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
   
    /**
     * Test of  method getEstado, of class SCDHPAJUv3Example.
     */
    @Test
    public void testEstablecerEstado() throws Exception {
        Estado estado = example.establecerEstado();
    }
    
    
    /**
     * Test of  method establecerAtributos, of class SCDHPAJUv3Example.
     */
    @Test
    public void testEstablecerAtributos() throws Exception {
        Atributos atributos = example.establecerAtributos();
    }
    
    
    
    
    
    
    /**
     * Test of  method establecerEmisor, of class SCDHPAJUv3Example.
     */
    @Test
    public void testEstablecerEmisor() throws Exception {
        Emisor emisor = example.establecerEmisor();
    }
    
    /**
     * Test of  method establecerFuncionario, of class SCDHPAJUv3Example.
     */
    @Test
    public void testEstablecerFuncionario() throws Exception {
        Funcionario funcionario = example.establecerFuncionario();
    }
    
    /**
     * Test of  method establecerProcedimiento, of class SCDHPAJUv3Example.
     */
    @Test
    public void testEstablecerProcedimiento() throws Exception {
        Procedimiento procedimiento = example.establecerProcedimiento();
    }
    
    /**
     * Test of  method establecerSolicitante, of class SCDHPAJUv3Example.
     */
    @Test
    public void testEstablecerSolicitante() throws Exception {
        Solicitante solicitante = example.establecerSolicitante();
    }
    
    
    /**
     * Test of main method, of class SCDHPAJUv3Example.
     */
    //@Test
    //public void testPeticionSincrona() throws Exception {
    //    Peticion pet =  example.getPeticion();
    //    Respuesta response = example.getPeticionSincrona(pet);
    //}
    
    
}
