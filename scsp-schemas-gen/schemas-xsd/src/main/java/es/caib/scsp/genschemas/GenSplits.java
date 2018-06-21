/*
 * Copyright 2018 gdeignacio.
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
package es.caib.scsp.genschemas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author gdeignacio
 */
public class GenSplits {
    
    protected static final Logger LOG = Logger.getLogger(GenSplits.class.getName());
    private static final GenSplits gen = new GenSplits();
    
    private GenSplits(){
        super();
    }
 
    public static GenSplits getGen(){
        return gen;
    }
    
    public void generate() throws JAXBException, IOException{
        MainProjectGenerator generator = new MainProjectGenerator();
        generator.generate();
    }
    
    public static void main(String args[]) throws Exception {

        
        // Obtenim instància
        GenSplits gen = GenSplits.getGen();
        LOG.log(Level.INFO, "Inicio generación");
        // Generam el projecte
        gen.generate();
        LOG.log(Level.INFO, "Fin generación");
        
       

    }

   
}
