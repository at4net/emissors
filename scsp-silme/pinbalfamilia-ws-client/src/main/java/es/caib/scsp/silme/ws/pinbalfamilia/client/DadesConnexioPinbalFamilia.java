
package es.caib.scsp.silme.ws.pinbalfamilia.client;

import es.caib.scsp.utils.ws.connexio.DadesConnexioSOAP;

/**
 *
 * @author gdeignacio
 */
public class DadesConnexioPinbalFamilia extends DadesConnexioSOAP  {
    
    /**
     *
     */
    public static final String _QNAME = "http://pinbalfamilia.silme.es/"; 
                                       
    //public static final String _CODAPP = "es.caib.scsp";
    private static final String _CODCLIENT = "silme.client";
    
    public static final String _SERVICE_NAME = "FamiliaNombrosa";
    
    private static final String _SERVICE_CONTEXT = "/SVDSCTFNWS01.asmx";

    private static final String _WSDL_ENDING = "?WSDL";

    
    
    public DadesConnexioPinbalFamilia(String codapp) {
        super(codapp);
    }
    
    @Override
    protected String getCodClient() {
        return _CODCLIENT;
    }

    @Override
    public String getServiceName() {
        return _SERVICE_NAME;
    }

    @Override
    protected String getServiceContext() {
        return _SERVICE_CONTEXT;
    }

    @Override
    protected String getWsdlEnding() {
        return _WSDL_ENDING;
    }

    @Override
    public String getQNAME() {
        return _QNAME;
    }

   
    
}
