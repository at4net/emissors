
package es.caib.scsp.pinbal.ws.recobriment.client;

import es.caib.scsp.utils.ws.connexio.DadesConnexioSOAP;

/**
 *
 * @author gdeignacio
 */
public class DadesConnexioRecobriment extends DadesConnexioSOAP  {
    
    public static final String _QNAME = "http://www.caib.es/pinbal/ws/recobriment"; 
                                       
    //public static final String _CODAPP = "es.caib.scsp";
    private static final String _CODCLIENT = "pinbal.client";
    
    public static final String _SERVICE_NAME = "RecobrimentService";
    
    private static final String _SERVICE_CONTEXT = "/ws/Recobriment";

    private static final String _WSDL_ENDING = "?wsdl";

    
    
    public DadesConnexioRecobriment(String codapp) {
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
