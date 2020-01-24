package es.caib.scsp.pinbal.ws.recobriment.example.client;


import java.util.ArrayList;
import java.util.List;
import es.caib.pinbal.ws.recobriment.Atributos;
import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.DatosGenericos;
import es.caib.pinbal.ws.recobriment.Emisor;
import es.caib.pinbal.ws.recobriment.Estado;
import es.caib.pinbal.ws.recobriment.Funcionario;
import es.caib.pinbal.ws.recobriment.Peticion;
import es.caib.pinbal.ws.recobriment.Procedimiento;
import es.caib.pinbal.ws.recobriment.Respuesta;
import es.caib.pinbal.ws.recobriment.Solicitante;
import es.caib.pinbal.ws.recobriment.SolicitudTransmision;
import es.caib.pinbal.ws.recobriment.Solicitudes;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import es.caib.pinbal.ws.recobriment.Titular;
import es.caib.pinbal.ws.recobriment.Transmision;
import es.caib.scsp.pinbal.ws.recobriment.client.DadesConnexioRecobriment;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentClient;
import es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentUtils;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SCDHPAJUv3PeticionDatosEspecificos;
import javax.xml.bind.JAXB;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author gdeignacio
 */
public class SCDHPAJUv3Example {
    
   
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
    private String codProcedimiento = "CODSVDR_GBA_20121107";
    private String nombreProcedimiento = "";
    
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
    
  
      
    
    private Estado estado;
    private Atributos atributos;
    private Emisor emisor;
    private Funcionario funcionario;
    private Procedimiento procedimiento;
    private Solicitante solicitante;
    private Titular titular;
    private Transmision transmision;
    
    private DatosGenericos datosGenericos;
    
    private SCDHPAJUv3PeticionDatosEspecificos datosEspecificos;
    
    private SolicitudTransmision solicitudTransmision; 
    private List<SolicitudTransmision> solicitudesTransmision;
    private Solicitudes solicitudes;
    
    private Peticion peticion;
    
    
    protected static final Logger LOG = Logger.getLogger(SCDHPAJUv3Example.class.getName());

    private RecobrimentClient client;
    
    public SCDHPAJUv3Example(RecobrimentClient client){
        this.client = client;
        this.estado = establecerEstado();
        this.atributos = establecerAtributos();
        this.emisor = establecerEmisor();
        this.funcionario = establecerFuncionario();
        this.procedimiento = establecerProcedimiento();
        this.solicitante = establecerSolicitante();
        this.titular = establecerTitular();
        this.transmision = establecerTransmision();
        this.datosGenericos = establecerDatosGenericos();
        this.solicitudTransmision = establecerSolicitudTransmision();
        this.solicitudesTransmision = establecerSolicitudesTransmision();
        this.solicitudes = establecerSolicitudes();
        this.peticion = establecerPeticion();
    }
    
    
    public void dummy(){
        _dummy();
    }
    
    private static void _dummy() {
        LOG.log(Level.INFO, "Invoking dummy...");
    }
   
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
            Logger.getLogger(SCDHPAJUv3Example.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SolicitudTransmision();    
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
    
    
    public DatosGenericos establecerDatosGenericos(){
        return RecobrimentUtils.establecerDatosGenericos(
                emisor,
                solicitante,
                titular,
                transmision
        );
    }
    
    public Transmision establecerTransmision(){
        return RecobrimentUtils.establecerTransmision(
                codigoCertificado, 
                fechaGeneracion,
                idSolicitud, 
                idTransmision
        );
    }
    
    public Titular establecerTitular(){
        return RecobrimentUtils.establecerTitular(
                apellido1, 
                apellido2, 
                documentacion, 
                nombre,
                nombreCompleto,
                tipoDocumentacion
        );
    }
    
    public Solicitante establecerSolicitante(){
        return RecobrimentUtils.establecerSolicitante(
                consentimiento, 
                finalidad, 
                funcionario, 
                idExpediente, 
                identificadorSolicitante, 
                nombreSolicitante, 
                procedimiento, 
                unidadTramitadora
        );
    }
    
    public Procedimiento establecerProcedimiento() {
        return RecobrimentUtils.establecerProcedimiento(
                codProcedimiento, 
                nombreProcedimiento
        );
    }  
    
    public Funcionario establecerFuncionario() {
        return RecobrimentUtils.establecerFuncionario(
                nifFuncionario, 
                nombreCompletoFuncionario
        );
    }  
    
 
    public Emisor establecerEmisor() {
        return RecobrimentUtils.establecerEmisor(
                nifEmisor,
                nombreEmisor
        );
    }   
    
    
    public Atributos establecerAtributos(){
        return RecobrimentUtils.establecerAtributos(
                codigoCertificado, 
                estado, 
                idPeticion, 
                numElementos, 
                timeStamp
        );
       
    }
    
    
    public Estado establecerEstado(){
        return RecobrimentUtils.establecerEstado(
                codigoEstado,
                codigoEstadoSecundario,
                literalError,
                tiempoEstimadoRespuesta
        );
    }
    
    
    public Respuesta getPeticionSincrona(Peticion pet){
        Respuesta response = client.peticionSincrona(pet);
        
        return response;
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
