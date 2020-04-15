package es.caib.ajuviv.pinbal.ws.recobriment.client;


import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.caib.scsp.pinbal.ws.recobriment.facade.RespuestaClientAdapter;
import es.caib.codapp.pinbal.ws.recobriment.datosespecificos.Q2827002CINSS001v3RespuestaDatosEspecificos;
import es.caib.ajuviv.pinbal.ws.recobriment.facade.Q2827002CINSS001v3RecobrimentFacade;


/**
 *
 * @author gdeignacio
 */
public class Q2827002CINSS001v3Client {
    
    private static String APP = "es.caib.codapp.";
    
     // Estado

    private String codigoEstado = null;
    private String codigoEstadoSecundario = null;
    private String literalError = null;
    private String literalErrorSec = null;
    private Integer tiempoEstimadoRespuesta = null;

    //Atributos

    private String codigoCertificado = "Q2827002CINSS001";
    private String idPeticion = null;
    private String numElementos = "1";
    private String timeStamp = null;

    //Emisor (obtingut de la documentació SCSP del servei)

    private String nifEmisor = "Q0700546E";
    private String nombreEmisor = "ATIB";

    //Funcionario

    private String nifFuncionario = "43078067X";
    private String nombreCompletoFuncionario = "Lluis Matias Llinas Alvarez";
    private String seudonimo = null;

    //Procedimiento

    private String codProcedimiento = "CODSVDR_GBA_20121107";         
    private String nombreProcedimiento = "PRUEBAS DE INTEGRACION PARA GOBIERNO DE BALEARES";

    //Solicitante
    private String codigoUnidadTramitadora = null;
    private Consentimiento consentimiento = Consentimiento.SI;
    private String finalidad = "PRUEBAS DE INTEGRACION PRESTACIONES";
    private String idExpediente = "";                                                                
    private String identificadorSolicitante = "S0711001H";
    private String nombreSolicitante = "Govern de les Illes Balears";
    private String unidadTramitadora = "ATIB Recaptacio";

    //Titular

    private String apellido1 = "MOYA";
    private String apellido2 = "BISQUERRA";
    private String documentacion = "43050080Z";
    private String nombre = "BARTOLOME";
    private String nombreCompleto = "MOYA BISQUERRA BARTOLOME";
    private TipoDocumentacion tipoDocumentacion = TipoDocumentacion.NIF;

    // Transmision

    private String fechaGeneracion = "";
    private String idSolicitud = "";
    private String idTransmision = "";

    // Datos Especificos
    
    
    
  
    
    protected static final Logger LOG = Logger.getLogger(Q2827002CINSS001v3Client.class.getName());

    private Q2827002CINSS001v3RecobrimentFacade facade;
    
    public Q2827002CINSS001v3Client(){
        this(APP);
    }
    
    public Q2827002CINSS001v3Client(String app){
        this.facade = new Q2827002CINSS001v3RecobrimentFacade(app);
    }
    
    public void dummy(){
        _dummy();
    }
    
    private static void _dummy() {
        LOG.log(Level.INFO, "Invoking dummy...");
    }
   
    
    
    public RespuestaClientAdapter<Q2827002CINSS001v3RespuestaDatosEspecificos> peticionSincrona(){
        
        RespuestaClientAdapter<Q2827002CINSS001v3RespuestaDatosEspecificos> respuestaClient =
                facade.peticionSincrona(codigoEstado, codigoEstadoSecundario, literalError, literalErrorSec, tiempoEstimadoRespuesta, codigoCertificado, idPeticion, numElementos, timeStamp, nifEmisor, nombreEmisor, nifFuncionario, nombreCompletoFuncionario, seudonimo, codProcedimiento, nombreProcedimiento, codigoUnidadTramitadora, consentimiento, finalidad, idExpediente, identificadorSolicitante, nombreSolicitante, unidadTramitadora, apellido1, apellido2, documentacion, nombre, nombreCompleto, tipoDocumentacion, fechaGeneracion, idSolicitud, idTransmision);
        
        return respuestaClient;
    }
    
    
    public static void main(String args[]) throws Exception {
        
     

    }

}
