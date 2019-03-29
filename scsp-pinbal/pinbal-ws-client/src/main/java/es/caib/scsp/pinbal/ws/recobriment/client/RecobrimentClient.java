package es.caib.scsp.pinbal.ws.recobriment.client;

import es.caib.pinbal.ws.recobriment.Atributos;
import es.caib.pinbal.ws.recobriment.ConfirmacionPeticion;
import es.caib.pinbal.ws.recobriment.DatosGenericos;
import es.caib.pinbal.ws.recobriment.Estado;
import es.caib.pinbal.ws.recobriment.Peticion;
import es.caib.pinbal.ws.recobriment.Recobriment;
import es.caib.pinbal.ws.recobriment.RecobrimentService;
import es.caib.pinbal.ws.recobriment.Respuesta;
import es.caib.pinbal.ws.recobriment.SolicitudTransmision;
import es.caib.pinbal.ws.recobriment.Solicitudes;
import es.caib.scsp.utils.ws.connexio.DadesConnexioSOAP;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;



/**
 *
 * @author gdeignacio
 */
public class RecobrimentClient {

    private String propertyBase = "es.caib.scsp.";

    protected static final Logger LOG = Logger.getLogger(RecobrimentClient.class.getName());

    /**
     * Objecte que emmagatzema la instancia de la classe segons el patro
     * singleton
     *
     */
    private static final RecobrimentClient client = new RecobrimentClient();

    /**
     * Construeix un objecte de la classe. Aquest metode es privat per forcar el
     * patro singleton.
     */
    private RecobrimentClient() {
        super();
    }

    /**
     * Recupera l objecte singleton.
     *
     * @return objete singleton de la clase CMAIBDocumentOrganismeWsClient.
     */
    public static RecobrimentClient getClient() {
        return client;
    }

    public void setPropertyBase(String propertyBase) {
        this.propertyBase = propertyBase;
    }

    private static final QName SERVICE_NAME = new QName(DadesConnexioRecobriment._QNAME,
            DadesConnexioRecobriment._SERVICE_NAME);

    private Recobriment getServicePort() {

        URL wsdlURL = null;

        final DadesConnexioSOAP dadesConnexio = new DadesConnexioRecobriment(propertyBase);

        try {
            LOG.info(dadesConnexio.getWsdlLocation());
            wsdlURL = new URL(dadesConnexio.getWsdlLocation());
        } catch (MalformedURLException ex) {
            Logger.getLogger(RecobrimentClient.class.getName()).log(Level.SEVERE, null, ex);
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


        RecobrimentService ss = new RecobrimentService(wsdlURL, SERVICE_NAME);
        Recobriment port = ss.getRecobrimentServicePort();

        Map<String, Object> req = ((BindingProvider) port).getRequestContext();

        req.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dadesConnexio.getEndPoint());

        req.put(BindingProvider.USERNAME_PROPERTY, dadesConnexio.getUserName());
        req.put(BindingProvider.PASSWORD_PROPERTY, dadesConnexio.getPassword());


        return port;

    }

    private static void dummy(Recobriment port) {

        LOG.log(Level.INFO, "Invoking dummy...");

        // port.consultaFormulariTasca(_CODAPP, _CODAPP)
    }

    
    public Respuesta peticionSincrona(Peticion pet){
        Recobriment port = getServicePort();
        Respuesta response;
        response = peticionSincrona(port, pet);
        return response;
    }
  
    private static Respuesta peticionSincrona(Recobriment port, Peticion pet){
        Respuesta _peticionSincrona__return = port.peticionSincrona(pet);
        return _peticionSincrona__return;        
    }
    
    
    public ConfirmacionPeticion peticionAsincrona(Peticion pet) {
        Recobriment port = getServicePort();
        ConfirmacionPeticion response;
        response = peticionAsincrona(port, pet);
        return response;
    }

    
    private static ConfirmacionPeticion peticionAsincrona(Recobriment port, Peticion pet){
        ConfirmacionPeticion _peticionAsincrona__return = port.peticionAsincrona(pet);
        return _peticionAsincrona__return;        
    }
    
    
    public Respuesta getRespuesta(String idPeticion){
        Recobriment port = getServicePort();
        Respuesta response;
        response = getRespuesta(port, idPeticion);
        return response;
    }
  
    private static Respuesta getRespuesta(Recobriment port, String idPeticion){
        Respuesta _getRespuesta__return = port.getRespuesta(idPeticion);
        return _getRespuesta__return;        
    }
    

   
    public static void main(String args[]) throws Exception {

        String app = "es.caib.scsp.";
        
        LOG.info("Valor app: " + app);

        //String str = JAXBToStringBuilder.valueOf(app, JAXBToStringStyle.DEFAULT_STYLE);

        DadesConnexioRecobriment dadesConnexio = new DadesConnexioRecobriment(app);

        System.setProperty(app + dadesConnexio.getCodClient() + ".username", "");
        System.setProperty(app + dadesConnexio.getCodClient() + ".password", "");
        System.setProperty(app + dadesConnexio.getCodClient() + ".baseURL", "http://pinbal.fundaciobit.org/pinbal");

        RecobrimentClient client = RecobrimentClient.getClient();
        Estado est = null;
        
        Atributos atr = RecobrimentUtils.establecerAtributos("SCDHPAJU", est , null, "1", "2019-03-29T12:47:11.830+01:00");
        List<SolicitudTransmision> lSol = new ArrayList<SolicitudTransmision>();
      
        Emisor emisor = RecobrimentUtils.establecerEmisor(app, app);
        
        
        DatosGenericos datosGenericos = RecobrimentUtils.establecerDatosGenericos(emisor, solicitante, titular, transmision);
        
        SolicitudTransmision sol = RecobrimentUtils.establecerSolicitudTransmision(datosGenericos, dadesConnexio);
        
        Solicitudes sols = RecobrimentUtils.establecerSolicitudes(lSol);
        
        Peticion pet = RecobrimentUtils.establecerPeticion(atr, sols);

        client.peticionSincrona(pet);
        
        
      

    }

}
