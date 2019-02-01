package es.caib.scsp.silme.ws.pinbalfamilia.client;

import es.caib.scsp.utils.ws.connexio.DadesConnexioSOAP;
import es.redsara.intermediacion.scsp.esquemas.v3.peticion.DatosAdicionales;
import es.redsara.intermediacion.scsp.esquemas.v3.peticion.Titular;
import es.silme.pinbalfamilia.FamiliaNombrosa;
import es.silme.pinbalfamilia.FamiliaNombrosaSoap;
import es.silme.pinbalfamilia.SVDSCTFNWS01;
import es.silme.pinbalfamilia.SVDSCTFNWS01Response;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;



/**
 *
 * @author gdeignacio
 */
public class PinbalFamiliaClient {

    private String propertyBase = "es.caib.scsp.";

    protected static final Logger LOG = Logger.getLogger(PinbalFamiliaClient.class.getName());

    /**
     * Objecte que emmagatzema la instancia de la classe segons el patro
     * singleton
     *
     */
    private static final PinbalFamiliaClient client = new PinbalFamiliaClient();

    /**
     * Construeix un objecte de la classe. Aquest metode es privat per forcar el
     * patro singleton.
     */
    private PinbalFamiliaClient() {
        super();
    }

    /**
     * Recupera l objecte singleton.
     *
     * @return objete singleton de la clase CMAIBDocumentOrganismeWsClient.
     */
    public static PinbalFamiliaClient getClient() {
        return client;
    }

    public void setPropertyBase(String propertyBase) {
        this.propertyBase = propertyBase;
    }

    private static final QName SERVICE_NAME = new QName(DadesConnexioPinbalFamilia._QNAME,
            DadesConnexioPinbalFamilia._SERVICE_NAME);

    
    
    private FamiliaNombrosaSoap getServicePort() {

        URL wsdlURL = null;

        final DadesConnexioSOAP dadesConnexio = new DadesConnexioPinbalFamilia(propertyBase);

        try {
            LOG.info(dadesConnexio.getWsdlLocation());
            wsdlURL = new URL(dadesConnexio.getWsdlLocation());
        } catch (MalformedURLException ex) {
            Logger.getLogger(PinbalFamiliaClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        dadesConnexio.getUserName(),
                        dadesConnexio.getPassword().toCharArray()
                );
            }
        });

        FamiliaNombrosa ss = new FamiliaNombrosa(wsdlURL, SERVICE_NAME);
        FamiliaNombrosaSoap port = ss.getFamiliaNombrosaSoap();

        Map<String, Object> req = ((BindingProvider) port).getRequestContext();

        req.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dadesConnexio.getEndPoint());

        req.put(BindingProvider.USERNAME_PROPERTY, dadesConnexio.getUserName());
        req.put(BindingProvider.PASSWORD_PROPERTY, dadesConnexio.getPassword());


        return port;

    }

    
    private static void dummy(FamiliaNombrosaSoap port) {

        LOG.log(Level.INFO, "Invoking dummy...");
        // port.consultaFormulariTasca(_CODAPP, _CODAPP)
    }
  
    private static SVDSCTFNWS01Response svdsctfnws01(FamiliaNombrosaSoap port, SVDSCTFNWS01 parameters){
        SVDSCTFNWS01Response _svdsctfnws01__return = port.svdsctfnws01(parameters);
        return _svdsctfnws01__return;
    }
    
    public SVDSCTFNWS01Response svdsctfnws01(SVDSCTFNWS01 parameters){
        FamiliaNombrosaSoap port = getServicePort();
        SVDSCTFNWS01Response response;
        response = svdsctfnws01(port, parameters);
        return response;
    }
    
    

   
    public static void main(String args[]) throws Exception {

        String app = "es.caib.scsp.";
        
        LOG.info("Valor app: " + app);

        //String str = JAXBToStringBuilder.valueOf(app, JAXBToStringStyle.DEFAULT_STYLE);

        DadesConnexioPinbalFamilia dadesConnexio = new DadesConnexioPinbalFamilia(app);

        System.setProperty(app + dadesConnexio.getCodClient() + ".username", "");
        System.setProperty(app + dadesConnexio.getCodClient() + ".password", "");
        System.setProperty(app + dadesConnexio.getCodClient() + ".baseURL", "http://pinbalfamilia.silme.es");

        
        PinbalFamiliaClient client = PinbalFamiliaClient.getClient();
        
        //Peticion pet = null;
        
        System.out.println("Conectado");
        
        SVDSCTFNWS01 parameters = new SVDSCTFNWS01();
        
        
        Titular titular = new Titular();
        titular.setTipoDocumentacion("NIF");
        titular.setDocumentacion("41503905Z");
        
        titular.setCodigoComunidadAutonoma("07");
        
        
        parameters.setTitular(titular);
        
        DatosAdicionales datosAdicionales = new DatosAdicionales();
        
        datosAdicionales.setFechaConsulta(null);
        
        parameters.setDatosAdicionales(datosAdicionales);
        
        SVDSCTFNWS01Response response = client.svdsctfnws01(parameters);
        
        System.out.println(response.toString());
        
    }

}
