package es.caib.scsp.pinbal.ws.recobriment.client;

import es.caib.pinbal.ws.recobriment.Atributos;
import es.caib.pinbal.ws.recobriment.ConfirmacionPeticion;
import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.DatosGenericos;
import es.caib.pinbal.ws.recobriment.Emisor;
import es.caib.pinbal.ws.recobriment.Estado;
import es.caib.pinbal.ws.recobriment.Funcionario;
import es.caib.pinbal.ws.recobriment.Peticion;
import es.caib.pinbal.ws.recobriment.Procedimiento;
import es.caib.pinbal.ws.recobriment.Recobriment;
import es.caib.pinbal.ws.recobriment.RecobrimentService;
import es.caib.pinbal.ws.recobriment.Respuesta;
import es.caib.pinbal.ws.recobriment.Solicitante;
import es.caib.pinbal.ws.recobriment.SolicitudTransmision;
import es.caib.pinbal.ws.recobriment.Solicitudes;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import es.caib.pinbal.ws.recobriment.Titular;
import es.caib.pinbal.ws.recobriment.Transmision;
import es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.DatosEspecificos;
import es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Solicitud;
import es.caib.scsp.utils.util.DataHandlers;
import es.caib.scsp.utils.ws.connexio.DadesConnexioSOAP;
import es.caib.scsp.utils.xml.XmlManager;
import java.io.FileOutputStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
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
     * Objecte que emmagatzema la instancia de la classe segons el patro singleton
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
                return new PasswordAuthentication(dadesConnexio.getUserName(),
                        dadesConnexio.getPassword().toCharArray());
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

    public Respuesta peticionSincrona(Peticion pet) {
        Recobriment port = getServicePort();
        Respuesta response;
        response = peticionSincrona(port, pet);
        return response;
    }

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

    private static final String CODIGO_CERTIFICADO = "VDRSFWS02";
    private static final Consentimiento CONSENTIMIENTO = Consentimiento.LEY;
    private static final String FINALIDAD = "Test recobriment";
    private static final String FUNCIONARIO_NIF = "97669911C";
    private static final String FUNCIONARIO_NOMBRE = "MPR TESTER";
    private static final String SOLICITANTE_ID = "G07896004";
    private static final String SOLICITANTE_NOMBRE = "Fundació BIT";
    private static final String PROCEDIMIENTO_CODIGO = "IBIT_20101223_PRUEBA";
    private static final String PROCEDIMIENTO_NOMBRE = "PROCEDIMIENTO DE PRUEBA FUNDACION IBIT";
    private static final String UNIDAD_CODIGO = null;
    private static final String UNIDAD_NOMBRE = "Unitat de test";
    private static final String EXPEDIENTE_ID = null;

    public static void main(String args[]) throws Exception {

        String app = "es.caib.scsp.";

        LOG.info("Valor app: " + app);

        // String str = JAXBToStringBuilder.valueOf(app,
        // JAXBToStringStyle.DEFAULT_STYLE);

        DadesConnexioRecobriment dadesConnexio = new DadesConnexioRecobriment(app);

        System.setProperty(app + dadesConnexio.getCodClient() + ".username", "");
        System.setProperty(app + dadesConnexio.getCodClient() + ".password", "");
        System.setProperty(app + dadesConnexio.getCodClient() + ".baseURL", "http://pinbal.fundaciobit.org/pinbal");

        RecobrimentClient client = RecobrimentClient.getClient();

        String codigoEstado = null;
        String codigoEstadoSecundario = null;
        String literalError = null;
        Integer tiempoEstimadoRespuesta = null;
        Estado estado = RecobrimentUtils.establecerEstado(codigoEstado, codigoEstadoSecundario, literalError,
                tiempoEstimadoRespuesta);

        String codigoCertificado = "SCDHPAJU";
        String idPeticion = null;
        String numElementos = "1";
        String timeStamp = "2019-03-29T12:47:11.830+01:00";
        Atributos atributos = RecobrimentUtils.establecerAtributos(codigoCertificado, estado, idPeticion, numElementos,
                timeStamp);

        List<SolicitudTransmision> lSolicitudTransmision = new ArrayList<SolicitudTransmision>();

        String nifEmisor = "S0711001H";
        String nombreEmisor = "CAIB";
        Emisor emisor = RecobrimentUtils.establecerEmisor(nifEmisor, nombreEmisor);

        String nifFuncionario = "43087806C";
        String nombreCompletoFuncionario = "OLIVER BESTARD, MARIA MAGDALENA";
        Funcionario funcionario = RecobrimentUtils.establecerFuncionario(nifFuncionario, nombreCompletoFuncionario);

        String codProcedimiento = "EC_ESCOOBL_2014";
        String nombreProcedimiento = "Esco. obligatòria";
        Procedimiento procedimiento = RecobrimentUtils.establecerProcedimiento(codProcedimiento, nombreProcedimiento);

        Consentimiento consentimiento = Consentimiento.SI;
        String finalidad = "Baremacions per el proces d'escolaritzacio";
        String idExpediente = "W517QR";
        String identificadorSolicitante = "S0711001H";
        String nombreSolicitante = "Conselleria d'Educació i Universitat";
        String unidadTramitadora = "Servei d'escolarització";

        Solicitante solicitante = RecobrimentUtils.establecerSolicitante(consentimiento, finalidad, funcionario,
                idExpediente, identificadorSolicitante, nombreSolicitante, procedimiento, unidadTramitadora);

        String apellido1 = "";
        String apellido2 = "";
        String documentacion = "465610290";
        String nombre = "";
        String nombreCompleto = "";
        TipoDocumentacion tipoDocumentacion = TipoDocumentacion.PASAPORTE;

        Titular titular = RecobrimentUtils.establecerTitular(apellido1, apellido2, documentacion, nombre,
                nombreCompleto, tipoDocumentacion);

        String fechaGeneracion = "";
        String idSolicitud = "";
        String idTransmision = "";

        Transmision transmision = RecobrimentUtils.establecerTransmision(codigoCertificado, fechaGeneracion,
                idSolicitud, idTransmision);

        DatosGenericos datosGenericos = RecobrimentUtils.establecerDatosGenericos(emisor, solicitante, titular,
                transmision);

        DatosEspecificos datosEspecificos = new DatosEspecificos();

        Solicitud solicitud = new Solicitud();
        solicitud.setMunicipioSolicitud("040");
        solicitud.setNumeroAnyos("20");
        solicitud.setProvinciaSolicitud("07");
        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Titular titul = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Titular();
        titul.setNIA("GT00261007");
        solicitud.setTitular(titul);
        datosEspecificos.setSolicitud(solicitud);

        XmlManager<DatosEspecificos> manager = new XmlManager<DatosEspecificos>(DatosEspecificos.class);
        DataHandler dh = manager.generateXml(datosEspecificos);
        byte[] b = DataHandlers.dataHandlerToByteArray(dh);

        SolicitudTransmision solicitudTransmision = RecobrimentUtils.establecerSolicitudTransmision(datosGenericos);
        solicitudTransmision.setDatosEspecificos(b);
        lSolicitudTransmision.add(solicitudTransmision);

        Solicitudes solicitudes = RecobrimentUtils.establecerSolicitudes(lSolicitudTransmision);

        Peticion peticion = RecobrimentUtils.establecerPeticion(atributos, solicitudes);

        LOG.info("Previ a petició sincrona: " + peticion.toString());

        client.peticionSincrona(peticion);

    }

}
