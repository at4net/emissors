package es.caib.ajuviv.pinbal.ws.recobriment.client;


import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.caib.scsp.pinbal.ws.recobriment.facade.RespuestaClientAdapter;
import es.caib.ajuviv.pinbal.ws.recobriment.datosespecificos.NIVRENTIv3RespuestaDatosEspecificos;
import es.caib.ajuviv.pinbal.ws.recobriment.facade.NIVRENTIv3RecobrimentFacade;


/**
 *
 * @author gdeignacio
 */
public class NIVRENTIv3Client {
    
    private static String APP = "es.caib.ajuviv.";
    
     // Estado

    private String codigoEstado = null;
    private String codigoEstadoSecundario = null;
    private String literalError = null;
    private String literalErrorSec = null;
    private Integer tiempoEstimadoRespuesta = null;

    //Atributos

    private String codigoCertificado = "NIVRENTI";
    private String idPeticion = null;
    private String numElementos = "1";
    private String timeStamp = null;

    //Emisor (obtingut de la documentació SCSP del servei)

    private String nifEmisor = "Q2826000H";
    private String nombreEmisor = "AEAT";

    //Funcionario

    private String nifFuncionario = "78210244D";
    private String nombreCompletoFuncionario = "SANS AGUILAR, CATALINA";
    private String seudonimo = null;

    //Procedimiento

    private String codProcedimiento = "CODSVDR_GBA_20121107";
    private String nombreProcedimiento = "PRUEBAS DE INTEGRACION PARA GOBIERNO DE BALEARES";
   
    //Solicitante
    private String codigoUnidadTramitadora = null;
    private Consentimiento consentimiento = Consentimiento.LEY;
    private String finalidad = "Baremacions per les ajudes a lloguer";
    private String idExpediente = "";//"Q9WREU";
    private String identificadorSolicitante = "S0711001H";
    private String nombreSolicitante = "Govern de les Illes Balears";
    private String unidadTramitadora = "FORMACIO LOT3";

    //Titular
    private String apellido1 = "";
    private String apellido2 = "";
    private String documentacion = "99999998T";
    private String nombre = "";
    private String nombreCompleto = "";
    private TipoDocumentacion tipoDocumentacion = TipoDocumentacion.NIF;

    // Transmision

    private String fechaGeneracion = "";
    private String idSolicitud = "";
    private String idTransmision = "";

    
    // Datos Especificos
    // PeticionDatosEspecificos
    private Integer ejercicio= new Integer(2015);
    // Fin datos especificos
    
    /*
    <?xml version="1.0" encoding="UTF-8"?>
<SolicitudTransmision 
    xmlns="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion">
    <DatosGenericos>
        <Emisor>
            <NifEmisor>Q2826000H</NifEmisor>
            <NombreEmisor>AEAT</NombreEmisor>
        </Emisor>
        <Solicitante>
            <IdentificadorSolicitante>S0711001H</IdentificadorSolicitante>
            <NombreSolicitante>Govern de les Illes Balears</NombreSolicitante>
            <UnidadTramitadora>Unitat de test</UnidadTramitadora>
            <Procedimiento>
                <CodProcedimiento>CODSVDR_GBA_20121107</CodProcedimiento>
                <NombreProcedimiento>PRUEBAS DE INTEGRACION PARA GOBIERNO DE BALEARES</NombreProcedimiento>
            </Procedimiento>
            <Finalidad>CODSVDR_GBA_20121107#::##::#CODSVDR_GBA_20121107#::##::#test</Finalidad>
            <Consentimiento>Ley</Consentimiento>
            <Funcionario>
                <NombreCompletoFuncionario>Guillem Arrom Oliver</NombreCompletoFuncionario>
                <NifFuncionario>43190731C</NifFuncionario>
            </Funcionario>
        </Solicitante>
        <Titular>
            <TipoDocumentacion>NIF</TipoDocumentacion>
            <Documentacion>99999998T</Documentacion>
        </Titular>
        <Transmision>
            <CodigoCertificado>NIVRENTI</CodigoCertificado>
            <IdSolicitud>PINBAL00000000000000009495</IdSolicitud>
            <FechaGeneracion>2020-04-14T17:25:18.147+02:00</FechaGeneracion>
        </Transmision>
    </DatosGenericos>
    <DatosEspecificos 
        xmlns="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos">
        <Ejercicio>2015</Ejercicio>
    </DatosEspecificos>
</SolicitudTransmision>
    */
    
  
    
    protected static final Logger LOG = Logger.getLogger(NIVRENTIv3Client.class.getName());

    private NIVRENTIv3RecobrimentFacade facade;
    
    public NIVRENTIv3Client(){
        this(APP);
    }
    
    public NIVRENTIv3Client(String app){
        this.facade = new NIVRENTIv3RecobrimentFacade(app);
    }
    
    public void dummy(){
        _dummy();
    }
    
    private static void _dummy() {
        LOG.log(Level.INFO, "Invoking dummy...");
    }
   
    
    
    public RespuestaClientAdapter<NIVRENTIv3RespuestaDatosEspecificos> peticionSincrona(){
        
        RespuestaClientAdapter<NIVRENTIv3RespuestaDatosEspecificos> respuestaClient =
            facade.peticionSincrona(codigoEstado, codigoEstadoSecundario, literalError, literalErrorSec, tiempoEstimadoRespuesta, codigoCertificado, idPeticion, numElementos, timeStamp, nifEmisor, nombreEmisor, nifFuncionario, nombreCompletoFuncionario, seudonimo, codProcedimiento, nombreProcedimiento, codigoUnidadTramitadora, consentimiento, finalidad, idExpediente, identificadorSolicitante, nombreSolicitante, unidadTramitadora, apellido1, apellido2, documentacion, nombre, nombreCompleto, tipoDocumentacion, fechaGeneracion, idSolicitud, idTransmision, ejercicio);
        return respuestaClient;
    }
    
    
    public static void main(String args[]) throws Exception {
        
     

    }

}
