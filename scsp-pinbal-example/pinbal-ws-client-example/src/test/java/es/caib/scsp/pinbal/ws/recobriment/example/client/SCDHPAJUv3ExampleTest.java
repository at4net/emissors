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
package es.caib.scsp.pinbal.ws.recobriment.example.client;

import es.caib.scsp.pinbal.ws.recobriment.client.DadesConnexioRecobriment;
import es.caib.scsp.pinbal.ws.recobriment.facade.RespuestaClientAdapter;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SCDHPAJUv3RespuestaDatosEspecificos;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author gdeignacio
 */
public class SCDHPAJUv3ExampleTest {
    
    
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
        
        example = new SCDHPAJUv3Example(app);
        
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of dummy method, of class SCDHPAJUv3Example.
     */
    @Test
    public void testDummy() {
        System.out.println("dummy");
        SCDHPAJUv3Example instance = new SCDHPAJUv3Example();
        instance.dummy();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of peticionSincrona method, of class SCDHPAJUv3Example.
     */
    @Test
    public void testPeticionSincrona() {
        System.out.println("peticionSincrona");
        SCDHPAJUv3Example instance = new SCDHPAJUv3Example();
        RespuestaClientAdapter<SCDHPAJUv3RespuestaDatosEspecificos> expResult = null;
        RespuestaClientAdapter<SCDHPAJUv3RespuestaDatosEspecificos> result = instance.peticionSincrona();
        
        System.out.println("APELLIDO  " + result.getTransmisionesClient().get(0).getDatosEspecificos().getResultado().getApellido1());
        
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class SCDHPAJUv3Example.
     */
    /*
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        SCDHPAJUv3Example.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
*/
    
}
