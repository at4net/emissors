package es.caib.scsp.pinbal.ws.recobriment.example.client;


import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import es.caib.scsp.pinbal.ws.recobriment.client.DadesConnexioRecobriment;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentClient;
import es.caib.scsp.pinbal.ws.recobriment.client.RespuestaClientAdapter;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SCDHPAJUv3PeticionDatosEspecificos;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos;
import es.caib.scsp.pinbal.ws.recobriment.example.facade.SVDDGTVEHICULODATOSWS01RecobrimentFacade;


/**
 *
 * @author gdeignacio
 */
public class SVDDGTVEHICULODATOSWS01v3Example {
    
    private static String APP = "es.caib.scsp.";
    
    // Estado
    private String codigoEstado = null;
    private String codigoEstadoSecundario = null;
    private String literalError = null;
    private Integer tiempoEstimadoRespuesta = null;
    
    //Atributos
    private String codigoCertificado = "SCDHPAJU";
    private String idPeticion = null;
    private String numElementos = "1";
    private String timeStamp = null;
    
    //Emisor
    private String nifEmisor = "S0711001H";
    private String nombreEmisor = "CAIB";
    
    //Funcionario
    private String nifFuncionario = "78210244D";
    private String nombreCompletoFuncionario = "SANS AGUILAR, CATALINA";
      
    //Procedimiento
    private String codProcedimiento = "EC_ESCOOBL_2014";
    private String nombreProcedimiento = "Esco. obligatòria";
    
    //Solicitante
    private Consentimiento consentimiento = Consentimiento.SI;
    private String finalidad = "Baremacions per el proces d'escolaritzacio";
    private String idExpediente = "Q9WREU";
    private String identificadorSolicitante = "S0711001H";
    private String nombreSolicitante = "Conselleria d'Educació i Universitat";
    private String unidadTramitadora = "Servei d'escolarització";
    
    //Titular
    private String apellido1 = "";
    private String apellido2 = "";
    private String documentacion = "78215122B";
    private String nombre = "";
    private String nombreCompleto = "";
    private TipoDocumentacion tipoDocumentacion = TipoDocumentacion.NIF;
    
    // Transmision
    private String fechaGeneracion = "";
    private String idSolicitud = "";
    private String idTransmision = "";
    
    
    // Datos Especificos
    // PeticionDatosEspecificos
    // Solicitud
    private String municipioSolicitud = "029";
    private String numeroAnyos = "20";
    private String provinciaSolicitud = "07";
    // Titular
    private String NIA = "GT00261007";
    // Documentacion
    private String tipo = TipoDocumentacion.NIF.value();
    private String valor = "78215122B";
    // Fin datos especificos
    
  
      
    /*
    private Estado estado;
    private Atributos atributos;
    private Emisor emisor;
    private Funcionario funcionario;
    private Procedimiento procedimiento;
    private Solicitante solicitante;
    private Titular titular;
    private Transmision transmision;
   
    private DatosGenericos datosGenericos;
    */

    /*
    private SCDHPAJUv3PeticionDatosEspecificos datosEspecificos;
    
    private SolicitudTransmision solicitudTransmision; 
    private List<SolicitudTransmision> solicitudesTransmision;
    private Solicitudes solicitudes;
    
    private Peticion peticion;
    */
    
    protected static final Logger LOG = Logger.getLogger(SVDDGTVEHICULODATOSWS01v3Example.class.getName());

    private SVDDGTVEHICULODATOSWS01RecobrimentFacade<
        SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos,
        SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> facade;
    
    public SVDDGTVEHICULODATOSWS01v3Example(){
        this.facade = new SVDDGTVEHICULODATOSWS01RecobrimentFacade<
        SVDDGTVEHICULODATOSWS01v3PeticionDatosEspecificos,
        SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos>(APP);
    }
    
    
    public void dummy(){
        _dummy();
    }
    
    private static void _dummy() {
        LOG.log(Level.INFO, "Invoking dummy...");
    }
   
    
    
    /*
    public Peticion establecerPeticion(){
        return RecobrimentUtils.establecerPeticion(atributos, solicitudes);
    }
    
    public Solicitudes establecerSolicitudes(){
        return RecobrimentUtils.establecerSolicitudes(solicitudesTransmision);
    }
    
    public List<SolicitudTransmision> establecerSolicitudesTransmision(){
        List<SolicitudTransmision> lst = new ArrayList<SolicitudTransmision>();
        lst.add(solicitudTransmision);
        return lst;
    }
    */
    
    /*
    public SolicitudTransmision establecerSolicitudTransmision(){
        Document document;
        Element elementDatosEspecificos;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            JAXB.marshal(datosEspecificos, new DOMResult(document));
            elementDatosEspecificos = document.getDocumentElement();
            return RecobrimentUtils.establecerSolicitudTransmision(
                datosGenericos, 
                elementDatosEspecificos
            );
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SVDDGTVEHICULODATOSWS01v3Example.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SolicitudTransmision();    
    }
    */
    
    public RespuestaClientAdapter<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> peticionSincrona(){
        
        RespuestaClientAdapter<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> respuestaClient =
                facade.peticionSincrona(codigoEstado, codigoEstadoSecundario, literalError, tiempoEstimadoRespuesta, codigoCertificado, idPeticion, numElementos, timeStamp, nifEmisor, nombreEmisor, nifFuncionario, nombreCompletoFuncionario, codProcedimiento, nombreProcedimiento, consentimiento, finalidad, idExpediente, identificadorSolicitante, nombreSolicitante, unidadTramitadora, apellido1, apellido2, documentacion, nombre, nombreCompleto, tipoDocumentacion, fechaGeneracion, idSolicitud, idTransmision, idPeticion);
        return respuestaClient;
    }
    
    
    
    
    public SCDHPAJUv3PeticionDatosEspecificos establecerDatosEspecificosPeticion(){
        
        
        
        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.DatosPersonales datosPersonales
                = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.DatosPersonales();
        
        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Documentacion datosEspecificosDocumentacion
                = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Documentacion();
        datosEspecificosDocumentacion.setTipo(tipo);
        datosEspecificosDocumentacion.setValor(valor);
        
        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Titular datosEspecificosTitular = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Titular();
        datosEspecificosTitular.setDatosPersonales(datosPersonales);
        datosEspecificosTitular.setDocumentacion(datosEspecificosDocumentacion);
        datosEspecificosTitular.setNIA(NIA);
        
        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Solicitud solicitud
                = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Solicitud();
        solicitud.setMunicipioSolicitud(municipioSolicitud);
        solicitud.setNumeroAnyos(numeroAnyos);
        solicitud.setProvinciaSolicitud(provinciaSolicitud);
        solicitud.setTitular(datosEspecificosTitular);
        
        SCDHPAJUv3PeticionDatosEspecificos datosEspecificos = new SCDHPAJUv3PeticionDatosEspecificos();
        
        datosEspecificos.setSolicitud(solicitud);  
        
        return datosEspecificos;
    }
    
    
   
    
    
    public static void main(String args[]) throws Exception {
        
        String app = "es.caib.scsp.";
        DadesConnexioRecobriment dadesConnexio = new DadesConnexioRecobriment(app);
        System.setProperty(app  + "pinbal.client.username", "$xestib_pinbal");
        System.setProperty(app  + "pinbal.client.password", "xestib_pinbal");
        System.setProperty(app  + "pinbal.client.baseURL", "https://proves.caib.es/pinbal");
        RecobrimentClient cliente = RecobrimentClient.getClient(dadesConnexio);
        
        System.out.println(cliente.getDadesConnexio().getWsdlLocation());
        
        //example = new SCDHPAJUv3Example(cliente);

    }

}
