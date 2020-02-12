package es.caib.scsp.pinbal.ws.recobriment.client;

import es.caib.pinbal.ws.recobriment.ConfirmacionPeticion;
import es.caib.pinbal.ws.recobriment.Peticion;
import es.caib.pinbal.ws.recobriment.Recobriment;
import es.caib.pinbal.ws.recobriment.RecobrimentService;
import es.caib.pinbal.ws.recobriment.Respuesta;
import es.caib.pinbal.ws.recobriment.Transmision;
import es.caib.pinbal.ws.recobriment.TransmisionDatos;
import es.caib.scsp.pinbal.ws.recobriment.cxf.RecobrimentPinbalClient;
import es.caib.scsp.pinbal.ws.recobriment.cxf.RecobrimentServicePinbalClient;
import es.caib.scsp.utils.ws.connexio.DadesConnexioSOAP;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import es.caib.scsp.utils.cxf.authentication.AuthenticatorReplacer;
import es.caib.scsp.utils.xml.XmlUtils;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.handler.Handler;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.w3c.dom.Element;


/**
 *
 * @author gdeignacio
 */
public class RecobrimentClient {

    private DadesConnexioSOAP dadesConnexio;

    public DadesConnexioSOAP getDadesConnexio() {
        return dadesConnexio;
    }

    public void setDadesConnexio(DadesConnexioSOAP dadesConnexio) {
        this.dadesConnexio = dadesConnexio;
    }

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
     * @return objete singleton de la clase.
     */
    private static RecobrimentClient _getClient(DadesConnexioRecobriment dadesConnexio) {
        client.setDadesConnexio(dadesConnexio);
        return client;
    }

    /**
     * Recupera el singleton amb dadesConnexio prèviament inicialitzat new
     * DadesConnexioRecobriment("foo.bar") properties foo.bar.helium.client.xxx
     *
     * @param dadesConnexio
     * @return
     * @see DadesConnexioRecobriment
     * @see RecobrimentClient
     */
    public static RecobrimentClient getClient(DadesConnexioRecobriment dadesConnexio) {
        DadesConnexioRecobriment dct = (dadesConnexio != null) ? dadesConnexio : new DadesConnexioRecobriment("");
        return _getClient(dct);
    }

    /**
     * Recupera el singleton i inicialitza DadesConnexio new DadesConnexio("")
     * properties helium.client.xxx
     *
     * @return
     * @see DadesConnexioRecobriment
     * @see RecobrimentClient
     */
    public static RecobrimentClient getClient() {
        DadesConnexioRecobriment dct = new DadesConnexioRecobriment("");
        return _getClient(dct);
    }

    private static final QName SERVICE_NAME = new QName(DadesConnexioRecobriment._QNAME,
            DadesConnexioRecobriment._SERVICE_NAME);

    private Recobriment getServicePort() {

        AuthenticatorReplacer.verifyHost();

        URL wsdlURL = null;

        try {
            LOG.info(dadesConnexio.getWsdlLocation());
            wsdlURL = new URL(dadesConnexio.getWsdlLocation());
        } catch (MalformedURLException ex) {
            Logger.getLogger(RecobrimentClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        String userName = dadesConnexio.getUserName();
        String password = dadesConnexio.getPassword();

        AuthenticatorReplacer.setAuthenticator(userName, password);

        LOG.log(Level.INFO, "Servicio:  {0}", SERVICE_NAME);
        LOG.log(Level.INFO, "URL: {0}", wsdlURL);

        RecobrimentService ss = new RecobrimentService(wsdlURL, SERVICE_NAME);
        Recobriment port = ss.getRecobrimentServicePort();

        Map<String, Object> req = ((BindingProvider) port).getRequestContext();

        req.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dadesConnexio.getEndPoint());

        req.put(BindingProvider.USERNAME_PROPERTY, dadesConnexio.getUserName());
        req.put(BindingProvider.PASSWORD_PROPERTY, dadesConnexio.getPassword());

        return port;

    }

    private RecobrimentPinbalClient getServicePinbalClientPort() {

        AuthenticatorReplacer.verifyHost();

        URL wsdlURL = null;

        try {
            LOG.info(dadesConnexio.getWsdlLocation());
            wsdlURL = new URL(dadesConnexio.getWsdlLocation());
        } catch (MalformedURLException ex) {
            Logger.getLogger(RecobrimentClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        String userName = dadesConnexio.getUserName();
        String password = dadesConnexio.getPassword();

        AuthenticatorReplacer.setAuthenticator(userName, password);

        LOG.log(Level.INFO, "Servicio:  {0}", SERVICE_NAME);
        LOG.log(Level.INFO, "URL: {0}", wsdlURL);

        RecobrimentServicePinbalClient ss = new RecobrimentServicePinbalClient(wsdlURL, SERVICE_NAME);
        RecobrimentPinbalClient port = ss.getRecobrimentServicePinbalClientPort();

        Map<String, Object> req = ((BindingProvider) port).getRequestContext();

        req.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dadesConnexio.getEndPoint());

        req.put(BindingProvider.USERNAME_PROPERTY, dadesConnexio.getUserName());
        req.put(BindingProvider.PASSWORD_PROPERTY, dadesConnexio.getPassword());

        return port;

    }

    private Recobriment getHandledServicePort() {

        Recobriment port = getServicePort();

        RecobrimentSOAPHandler sh = new RecobrimentSOAPHandler();

        List<Handler> handlerChain = new ArrayList<Handler>();
        handlerChain.add(sh);
        
        BindingProvider bindingProvider = ((BindingProvider) port);
        
        bindingProvider.getBinding().setHandlerChain(handlerChain);

        return port;

    }

    private Recobriment getInterceptedServicePort() {

        Recobriment port = getServicePort();

        Client cxfclient = ClientProxy.getClient(port);
        cxfclient.getInInterceptors().add(
                new RecobrimentPeticionSincronaSOAPInterceptor());

        return port;

    }


    private static void dummy(Recobriment port) {
        LOG.log(Level.INFO, "Invoking dummy...");
    }

    public Respuesta peticionSincrona(Peticion pet) {
        
        Recobriment port = getHandledServicePort();
        
        Respuesta response;

        response = peticionSincrona(port, pet);
        
        
        
        Element datosEspecificosPre = (Element)response.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos();
        
        
        
        
        System.out.println("Datos Especificos Pre" + datosEspecificosPre);
        
        Element datosEspecificosPost = XmlUtils.node2Element(datosEspecificosPre);
        
        System.out.println("Datos Especificos Post " + datosEspecificosPost.toString());
        
        
        
        
        /*
        for (TransmisionDatos transmisionDatos:response.getTransmisiones().getTransmisionDatos()){
                //Transmision transmision = transmisionDatos.getDatosGenericos().getTransmision();
             
                //String key = RecobrimentSOAPHandler.DATOS_ESPECIFICOS + "." 
                //        + transmision.getIdSolicitud() + "." + transmision.getIdTransmision();
                //Element datosEspecificos = (Element)res.get(key);
                transmisionDatos.setDatosEspecificos(datosEspecificosPost);
        }
        */
        
        
        
        Map<String, Object> res = ((BindingProvider) port).getResponseContext();
        
        for (TransmisionDatos transmisionDatos:response.getTransmisiones().getTransmisionDatos()){
                Transmision transmision = transmisionDatos.getDatosGenericos().getTransmision();
             
                String key = RecobrimentSOAPHandler.DATOS_ESPECIFICOS + "." 
                        + transmision.getIdSolicitud() + "." + transmision.getIdTransmision();
                Element datosEspecificos = XmlUtils.node2Element((Element)res.get(key));
                transmisionDatos.setDatosEspecificos(datosEspecificos);
        }
        
        
        //LOG.log(Level.INFO, "Client context: {0}", res.toString());
        
        //String body = (String)res.get(RecobrimentSOAPHandler.DATOS_ESPECIFICOS);
        
        //String datosEspecificos = StringUtils.substringBetween(body, "<datosEspecificos>", "</datosEspecificos>");
        
        //datosEspecificos = "<datosEspecificos>".concat(datosEspecificos).concat("</datosEspecificos>");
        
        /*
        String datosEspecificos = "<hello>world</hello>";
        
        try {
            
            Element element = stringToElement(datosEspecificos);
            LOG.log(Level.INFO, "Element: {0}", element.toString());
            
            ObjectFactory of = new ObjectFactory();
            
            TransmisionDatos transmisionDatos = of.createTransmisionDatos();
            
            for (TransmisionDatos transmisionDatosPinbal:response.getTransmisiones().getTransmisionDatos()){
                LOG.log(Level.INFO, "Id Transmision: {0}", transmisionDatosPinbal.getId());
                transmisionDatos = transmisionDatosPinbal;
                break;
            }
            
            LOG.log(Level.INFO, "Element: {0}", element.toString());
            transmisionDatos.setDatosEspecificos((Object)element);
            
            response.getTransmisiones().getTransmisionDatos().set(0, transmisionDatos);
            
        } catch (TransformerException ex) {
            Logger.getLogger(RecobrimentClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RecobrimentClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(RecobrimentClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecobrimentClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        LOG.log(Level.INFO, "Respuesta contexto: {0}", datosEspecificos);
        
        */
        
        
        LOG.log(Level.INFO, "Respuesta Pinbal TRANSFORMADA: {0}", ((Element)response.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos()).getTextContent());
        
        return response;
    }
    
    
    
    
    /*
    public Respuesta peticionSincrona(Peticion pet) {
        RecobrimentPinbalClient port = getServicePinbalClientPort();
        Respuesta response;

        RespuestaPinbalClient respuestaPinbal = peticionSincronaPinbalClient(port, (PeticionPinbalClient) pet);

        LOG.log(Level.INFO, "Respuesta Pinbal: " + respuestaPinbal.toString());

        ObjectFactory of = new ObjectFactory();

        response = of.createRespuesta();

        response.setAtributos(respuestaPinbal.getAtributos());

        Transmisiones transmisiones = of.createTransmisiones();

        for (TransmisionDatosPinbalClient transmisionDatosPinbal : respuestaPinbal.getTransmisiones().getTransmisionDatos()) {

            TransmisionDatos transmisionDatos = of.createTransmisionDatos();

            transmisionDatos.setDatosEspecificos(transmisionDatosPinbal.getDatosEspecificos());
            transmisionDatos.setDatosGenericos(transmisionDatosPinbal.getDatosGenericos());
            transmisionDatos.setId(transmisionDatosPinbal.getId());

            transmisiones.getTransmisionDatos().add(transmisionDatos);
        }

        response.setTransmisiones(transmisiones);

        Map<String, Object> res = ((BindingProvider) port).getResponseContext();

        //LOG.log(Level.INFO, "Respuesta: " +  res.entrySet());
        LOG.log(Level.INFO, "Respuesta: " + response.toString());

        return response;
    }
*/

    private static Respuesta peticionSincrona(Recobriment port, Peticion pet) {
        LOG.log(Level.INFO, "Invoking port...");
        Respuesta _peticionSincrona__return = port.peticionSincrona(pet);
        LOG.log(Level.INFO, "Return port...");
        return _peticionSincrona__return;
    }

    public ConfirmacionPeticion peticionAsincrona(Peticion pet) {
        Recobriment port = getServicePort();
        ConfirmacionPeticion response;
        response = peticionAsincrona(port, pet);
        return response;
    }

    private static ConfirmacionPeticion peticionAsincrona(Recobriment port, Peticion pet) {
        ConfirmacionPeticion _peticionAsincrona__return = port.peticionAsincrona(pet);
        return _peticionAsincrona__return;
    }

    public Respuesta getRespuesta(String idPeticion) {
        Recobriment port = getServicePort();
        Respuesta response;
        response = getRespuesta(port, idPeticion);
        return response;
    }

    private static Respuesta getRespuesta(Recobriment port, String idPeticion) {
        Respuesta _getRespuesta__return = port.getRespuesta(idPeticion);
        return _getRespuesta__return;
    }


    public static void main(String args[]) throws Exception {
       
    }

}
