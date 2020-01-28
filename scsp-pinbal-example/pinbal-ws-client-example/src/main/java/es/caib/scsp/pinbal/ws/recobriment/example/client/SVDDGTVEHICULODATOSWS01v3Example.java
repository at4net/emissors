package es.caib.scsp.pinbal.ws.recobriment.example.client;


import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.caib.scsp.pinbal.ws.recobriment.client.RespuestaClientAdapter;
import es.caib.scsp.pinbal.ws.recobriment.example.datosespecificos.SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos;
import es.caib.scsp.pinbal.ws.recobriment.example.facade.SVDDGTVEHICULODATOSWS01v3RecobrimentFacade;


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
    private String literalErrorSec = null;
    private Integer tiempoEstimadoRespuesta = null;

    //Atributos

    private String codigoCertificado = "SVDDGTVEHICULODATOSWS01";
    private String idPeticion = null;
    private String numElementos = "1";
    private String timeStamp = null;

    //Emisor (obtingut de la documentació SCSP del servei)

    private String nifEmisor = "Q2816003D";
    private String nombreEmisor = "Dirección General de Tráfico";

    //Funcionario

    private String nifFuncionario = "43014534A";
    private String nombreCompletoFuncionario = "GINARD PRATS, BERNARDO";
    private String seudonimo = null;

    //Procedimiento

    private String codProcedimiento = "CODSVDR_GBA_20121107";         
    private String nombreProcedimiento = "PRUEBAS DE INTEGRACION PARA GOBIERNO DE BALEARES";

    //Solicitante
    private String codigoUnidadTramitadora = null;
    private Consentimiento consentimiento = Consentimiento.SI;
    private String finalidad = "Consulta dades vehicle";
    private String idExpediente = "Q9WREU";                                                                
    private String identificadorSolicitante = "S0711001H";
    private String nombreSolicitante = "Govern de les Illes Balears";
    private String unidadTramitadora = "Secció Tributaria";

    //Titular

    private String apellido1 = "";
    private String apellido2 = "";
    private String documentacion = "";
    private String nombre = "";
    private String nombreCompleto = "";
    private TipoDocumentacion tipoDocumentacion = TipoDocumentacion.NIF;

    // Transmision

    private String fechaGeneracion = "";
    private String idSolicitud = "";
    private String idTransmision = "";

    // Datos Especificos

    private String matricula="6201CXP";
    private String bastidor = null;
    private String NIVE = null;
    
  
    
    protected static final Logger LOG = Logger.getLogger(SVDDGTVEHICULODATOSWS01v3Example.class.getName());

    private SVDDGTVEHICULODATOSWS01v3RecobrimentFacade facade;
    
    public SVDDGTVEHICULODATOSWS01v3Example(){
        this(APP);
    }
    
    public SVDDGTVEHICULODATOSWS01v3Example(String app){
        this.facade = new SVDDGTVEHICULODATOSWS01v3RecobrimentFacade(app);
    }
    
    public void dummy(){
        _dummy();
    }
    
    private static void _dummy() {
        LOG.log(Level.INFO, "Invoking dummy...");
    }
   
    
    
    public RespuestaClientAdapter<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> peticionSincrona(){
        
        RespuestaClientAdapter<SVDDGTVEHICULODATOSWS01v3RespuestaDatosEspecificos> respuestaClient =
                facade.peticionSincrona(codigoEstado, codigoEstadoSecundario, literalError, literalErrorSec , tiempoEstimadoRespuesta, codigoCertificado, idPeticion, numElementos, timeStamp, nifEmisor, nombreEmisor, nifFuncionario, nombreCompletoFuncionario, seudonimo, codProcedimiento, nombreProcedimiento, codigoUnidadTramitadora, consentimiento, finalidad, idExpediente, identificadorSolicitante, nombreSolicitante, unidadTramitadora, apellido1, apellido2, documentacion, nombre, nombreCompleto, tipoDocumentacion, fechaGeneracion, idSolicitud, idTransmision, bastidor, matricula, NIVE);
        
        
        return respuestaClient;
    }
    
    
    public static void main(String args[]) throws Exception {
        
     

    }

}
