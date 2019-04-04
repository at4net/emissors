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
package es.caib.scsp.pinbal.ws.recobriment.client;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gdeignacio
 */
public class RecobrimentClientTest {
    
    public RecobrimentClientTest() {
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
     * Test of getClient method, of class RecobrimentClient.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        RecobrimentClient expResult = null;
        RecobrimentClient result = RecobrimentClient.getClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPropertyBase method, of class RecobrimentClient.
     */
    @Test
    public void testSetPropertyBase() {
        System.out.println("setPropertyBase");
        String propertyBase = "";
        RecobrimentClient instance = null;
        instance.setPropertyBase(propertyBase);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of peticionSincrona method, of class RecobrimentClient.
     */
    @Test
    public void testPeticionSincrona() {
        System.out.println("peticionSincrona");
        es.caib.pinbal.ws.recobriment.Peticion pet = null;
        RecobrimentClient instance = null;
        es.caib.pinbal.ws.recobriment.Respuesta expResult = null;
        es.caib.pinbal.ws.recobriment.Respuesta result = instance.peticionSincrona(pet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of peticionAsincrona method, of class RecobrimentClient.
     */
    @Test
    public void testPeticionAsincrona() {
        System.out.println("peticionAsincrona");
        es.caib.pinbal.ws.recobriment.Peticion pet = null;
        RecobrimentClient instance = null;
        es.caib.pinbal.ws.recobriment.ConfirmacionPeticion expResult = null;
        es.caib.pinbal.ws.recobriment.ConfirmacionPeticion result = instance.peticionAsincrona(pet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRespuesta method, of class RecobrimentClient.
     */
    @Test
    public void testGetRespuesta() {
        System.out.println("getRespuesta");
        String idPeticion = "";
        RecobrimentClient instance = null;
        es.caib.pinbal.ws.recobriment.Respuesta expResult = null;
        es.caib.pinbal.ws.recobriment.Respuesta result = instance.getRespuesta(idPeticion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class RecobrimentClient.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        RecobrimentClient.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
