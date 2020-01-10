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
import java.util.logging.Level;
import java.util.logging.Logger;
import es.caib.scsp.pinbal.ws.recobriment.client.DadesConnexioRecobriment;
import es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentClient;
import es.caib.scsp.pinbal.ws.recobriment.client.RecobrimentUtils;
import javax.xml.bind.JAXB;
import javax.xml.parsers.DocumentBuilderFactory;
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
    private String codProcedimiento = "EC_ESCOOBL_2014";
    private String nombreProcedimiento = "Esco. obligatòria";
    
    private Consentimiento consentimiento = Consentimiento.SI;
    private String finalidad = "Baremacions per el proces d'escolaritzacio";
    private String idExpediente = "Q9WREU";
    private String identificadorSolicitante = "S0711001H";
    private String nombreSolicitante = "Conselleria d'Educació i Universitat";
    private String unidadTramitadora = "Servei d'escolarització";
    
    
    
    
    private Estado estado;
    private Atributos atributos;
    private Emisor emisor;
    private Funcionario funcionario;
    private Procedimiento procedimiento;
    private Solicitante solicitante;

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
    }
    
    
    public void dummy(){
        _dummy();
    }
    
    private static void _dummy() {
        LOG.log(Level.INFO, "Invoking dummy...");
    }

    /*
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
    */
    
    
     /*

        //CODSVDR_GBA_20121107

        List<SolicitudTransmision> lSolicitudTransmision = new ArrayList<SolicitudTransmision>();

     
    
        String apellido1 = "";
        String apellido2 = "";
        String documentacion = "78215122B";
        String nombre = "";
        String nombreCompleto = "";
        TipoDocumentacion tipoDocumentacion = TipoDocumentacion.NIF;

        Titular titular = RecobrimentUtils.establecerTitular(apellido1, apellido2, documentacion, nombre,
                nombreCompleto, tipoDocumentacion);

        String fechaGeneracion = "";
        String idSolicitud = "";
        String idTransmision = "";

        Transmision transmision = RecobrimentUtils.establecerTransmision(codigoCertificado, fechaGeneracion,
                idSolicitud, idTransmision);

        DatosGenericos datosGenericos = RecobrimentUtils.establecerDatosGenericos(emisor, solicitante, titular, transmision);

        */
        
        // Datos específicos
        //
        
        /*
        
        PeticionDatosEspecificos datosEspecificos = new PeticionDatosEspecificos();

        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Solicitud solicitud
                = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Solicitud();
        solicitud.setMunicipioSolicitud("029");
        //solicitud.setNumeroAnyos("20");
        solicitud.setProvinciaSolicitud("07");
        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Titular titul = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Titular();
        //titul.setNIA("GT00261007");

        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.DatosPersonales datosPersonales
                = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.DatosPersonales();

        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Documentacion dc
                = new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.Documentacion();

        dc.setTipo(TipoDocumentacion.NIF.value());
        dc.setValor("78215122B");
        
        */

        //titul.setDatosPersonales(datosPersonales);
        
        
        /*
        titul.setDocumentacion(dc);
        solicitud.setTitular(titul);
        datosEspecificos.setSolicitud(solicitud);
        */
        
        /*
        XmlManager<es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.DatosEspecificos> manager = 
                new XmlManager<es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.DatosEspecificos>(
                        es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.DatosEspecificos.class
                );
        DataHandler dh = manager.generateXml(datosEspecificos);
        byte[] b = DataHandlers.dataHandlerToByteArray(dh);
         */
        

        //es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.ObjectFactory objectFactory =
        //        new es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.ObjectFactory();
        //JAXBElement<es.caib.scsp.esquemas.SCDHPAJUv3.peticion.datosespecificos.DatosEspecificos> jaxbDatosEspecificos = 
        //        objectFactory.createDatosEspecificos(datosEspecificos);
       
        /*
        
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        JAXB.marshal(datosEspecificos, new DOMResult(document));
        Element elementDatosEspecificos = document.getDocumentElement();

        
        LOG.log(Level.INFO, "Previ a petici\u00f3 sincrona 1:\n {0}", datosEspecificos.toString());

        */
        
        
        //solicitudTransmision.setDatosEspecificos(elementDatosEspecificos);
        
        
        
        /*
        
        SolicitudTransmision solicitudTransmision = RecobrimentUtils.establecerSolicitudTransmision(datosGenericos, elementDatosEspecificos);
        
        lSolicitudTransmision.add(solicitudTransmision);

        Solicitudes solicitudes = RecobrimentUtils.establecerSolicitudes(lSolicitudTransmision);

        Peticion peticion = RecobrimentUtils.establecerPeticion(atributos, solicitudes);

        LOG.info("Previ a petició sincrona 2:\n" + peticion.toString());

        Respuesta response = client.peticionSincrona(peticion);

        */
    
    
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
  

    }

}
