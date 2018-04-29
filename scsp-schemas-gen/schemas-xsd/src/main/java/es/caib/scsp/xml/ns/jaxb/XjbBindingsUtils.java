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
package es.caib.scsp.xml.ns.jaxb;

import com.google.common.base.CaseFormat;
import com.sun.java.xml.ns.jaxb.PackageType;
import com.sun.java.xml.ns.jaxb.SchemaBindings;
import es.caib.scsp.genschemas.managers.XjbBindingsXmlManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 *
 * @author gdeignacio
 */
public class XjbBindingsUtils {

    public static XjbBindings getXjbBindings() throws JAXBException, IOException {

        XjbBindings xjbBindings = new XjbBindings();
        InputStream bindingsInputStream = xjbBindings.getClass().getClassLoader().getResourceAsStream("jaxb/templates/bindings.xjb.template");
        XjbBindingsXmlManager manager = new XjbBindingsXmlManager();

        xjbBindings = manager.generateItem(bindingsInputStream);

        return xjbBindings;

    }

    public static XjbBindings getXjbBindings(String key, List<String> xsds) throws JAXBException, IOException {

        XjbBindings xjbBindings = getXjbBindings();

        System.out.println("key " + key + ": " + xsds);
        for (String xsd : xsds) {

            XjbBindings xsdBindings = new XjbBindings();

            xsdBindings.setSchemaLocation("../../schemas/" + key + "/" + xsd);

            SchemaBindings schemaBindings = new SchemaBindings();
            PackageType packageType = new PackageType();
            String packageName = "es.caib.scsp.esquemas." + key + "." 
                    + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.replace(".", "_")).toLowerCase();
            packageType.getName().add(packageName);
            schemaBindings.setPackage(packageType);
            xsdBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(schemaBindings);

            
            /*
            XjbBindings nodeBindings = new XjbBindings();
            String clazzName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, xsd.split("\\.")[0]);
            nodeBindings.setNode("//xs:complexType[@name='" + clazzName + "'] | //xs:element[@name='" + clazzName + "']");

            com.sun.java.xml.ns.jaxb.Class clazz = new com.sun.java.xml.ns.jaxb.Class();

            clazz.getName().add(clazzName);

            nodeBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(clazz);
            xsdBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(nodeBindings);
            */
            
            
            xjbBindings.getGlobalBindingsOrSchemaBindingsOrClazz().add(xsdBindings);

            //System.out.println("Recurso en   jaxb/schemas/" + key + "/" + xsd);
        }

        return xjbBindings;

    }

/*
    <jxb:bindings schemaLocation="xsd/CommonTypes.xsd">
        <jxb:bindings node="//xs:complexType[@name='AnXSDType']">
            <jxb:class name="AnXSDTypeFromCommonTypes"/>
        </jxb:bindings>
    </jxb:bindings>
    <jxb:bindings schemaLocation="xsd/EvenMoreCommonTypes.xsd">
        <jxb:bindings node="//xs:complexType[@name='AnXSDType']">
            <jxb:class name="AnXSDTypeFromEvenMoreCommonTypes"/>
        </jxb:bindings>
    </jxb:bindings>
 */   
    
}
