<?php
use RobRichards\XMLSecLibs\XMLSecurityDSig;
use RobRichards\XMLSecLibs\XMLSecurityKey;
require 'vendor/autoload.php';

require 'CUF.php';
require_once __DIR__ . '/datos_generales.php';



$siat = obtenerDatosSiat(1);
$codigoPuntoVenta = $siat['codigoPuntoVenta'];
$codigoControl = $siat['codigoControl'];
$cufd = $siat['cufd'];
$cuis = $siat['cuis'];
$token = $siat['token'];
$codigoAmbiente = $siat['codigoAmbiente'];
$codigoDocumentoSector=1;
$codigoEmision=1;
$codigoModalidad = $siat['codigoModalidad'];
//error_log("puntoVenta: " . $codigoPuntoVenta." cufd: ".$cufd." cuis: ".$cuis." token: ".$token." codigoAmbiente: ".$codigoAmbiente." codigoDocumentoSector: ".$codigoDocumentoSector." codigoEmision: ".$codigoEmision." codigoModalidad: ".$codigoModalidad);
//error_log("codigoControl: $codigoControl");
//exit();
$cantidad=150;

$codigoSistema = $siat['codigoSistema'];
$codigoSucursal = $siat['codigoSucursal'];

$nit = $siat['nit'];
$razonSocialEmisor = $siat['razonSocial'];
$municipio = $siat['municipio'];
$direccion = $siat['direccion'];
$telefonoEmisor = etiquetaTelefonoSiat($siat['telefono']);
$tipoFacturaDocumento=1;//1 con credito 2 sin credito 3 nota


$temision=1;
$cdf=1;
$tds=1;//1 compra venta, 2 servicios basicos, 3 nota credito debito, 4 nota conciliacion
$nf=1;
deleteFile();
//     * @param nit NIT emisor
//     * @param fh Fecha y Hora en formato yyyyMMddHHmmssSSS
//     * @param sucursal
//     * @param mod Modalidad
//     * @param temision Tipo de Emision
//     * @param cdf Codigo Documento Fiscal
//     * @param tds Tipo Documento Sector
//     * @param nf Numero de Factura
//     * @param pos Punto de Venta

/*
 * Operaciones a ejecutar por factura. Cada una es una llamada SOAP (~0.33 s),
 * asi que apagar las que no necesites multiplica la velocidad:
 *   solo recepcion        -> ~0.33 s por factura
 *   las tres (por defecto) -> ~1.00 s por factura
 */
$hacerAnulacion = true;
$hacerReversion = true;

// El SIAT a veces responde codigoEstado -1 "Request timeout ... after 45000 ms".
// Cortamos antes y reintentamos, en vez de perder 46 s esperando.
ini_set('default_socket_timeout', '10');
$reintentos = 2;

/*
 * El cliente SOAP se crea UNA sola vez: antes se instanciaba dentro del bucle
 * y eso volvia a descargar y parsear el WSDL (~0.11 s) en cada factura.
 */
function crearClienteSiat(string $token): SoapClient
{
    return new \SoapClient(obtenerUrlSiat('ServicioFacturacionCompraVenta'),  [
        'stream_context' => stream_context_create([
            'http' => [
                // Connection: close evita el keep-alive. Reutilizar la conexion
                // hacia el SIAT provoca "Error Fetching http headers" y cada uno
                // de esos se come el socket_timeout completo esperando.
                'header' => "apikey: TokenApi " . $token . "
Connection: close",
                'timeout' => 10,
            ]
        ]),
        'cache_wsdl' => WSDL_CACHE_DISK,
        'compression' => SOAP_COMPRESSION_ACCEPT | SOAP_COMPRESSION_GZIP | SOAP_COMPRESSION_DEFLATE,
        'trace' => 0,
        'use' => SOAP_LITERAL,
        'style' => SOAP_DOCUMENT,
        'connection_timeout' => 10,
    ]);
}

$client = crearClienteSiat($token);

/**
 * Ejecuta una operacion del SIAT reintentando cuando devuelve codigoEstado -1
 * (timeout interno del SIAT) o cuando revienta la conexion.
 */
function llamarSiat(SoapClient &$client, string $operacion, array $datos, int $reintentos, string $token)
{
    for ($intento = 0; $intento <= $reintentos; $intento++) {
        try {
            $t0 = microtime(true);
            $result = $client->$operacion($datos);
            if (getenv('SIAT_DEBUG')) { printf("      %-28s %.2f s
", $operacion, microtime(true)-$t0); }
        } catch (Throwable $e) {
            $perdido = microtime(true) - $t0;
            if ($intento === $reintentos) { return null; }
            printf("    ! %s fallo tras %.1f s (%s), reintento %d" . PHP_EOL,
                $operacion, $perdido, $e->getMessage(), $intento + 1);
            // "Error Fetching http headers" = el SIAT cerro la conexion keep-alive;
            // el cliente queda inservible y hay que rearmarlo.
            $client = crearClienteSiat($token);
            continue;
        }

        $r = $result->RespuestaServicioFacturacion ?? null;
        if (($r->codigoEstado ?? 0) !== -1) {
            return $result;
        }
        if ($intento < $reintentos) {
            echo "    ! $operacion devolvio timeout del SIAT, reintento " . ($intento + 1) . PHP_EOL;
        }
    }
    return $result ?? null;
}

$inicio = microtime(true);
$validadas = 0;
$rechazadas = 0;
$anuladas = 0;
$revertidas = 0;

for ($i=1;$i<=$cantidad;$i++){
    $numeroFactura = $i;   // antes iba fijo en 1 para las 125 facturas
    $nf = $i;
    $miliSegundo=str_pad($i, 3, '0', STR_PAD_LEFT);
    $fechaEnvio=date("Y-m-d\TH:i:s").".$miliSegundo";
    $cuf = new CUF();
    $cuf = $cuf->obtenerCUF($nit, date("YmdHis$miliSegundo"), $codigoSucursal, $codigoModalidad, $temision, $cdf, $tds, $nf, $codigoPuntoVenta);
    $cuf=$cuf.$codigoControl;

    $data ="<?xml version='1.0' encoding='UTF-8' standalone='yes'?>
<facturaElectronicaCompraVenta xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='facturaElectronicaCompraVenta.xsd'>
<cabecera>
        <nitEmisor>$nit</nitEmisor>
        <razonSocialEmisor>$razonSocialEmisor</razonSocialEmisor>
        <municipio>$municipio</municipio>
        $telefonoEmisor
        <numeroFactura>$numeroFactura</numeroFactura>
        <cuf>$cuf</cuf>
        <cufd>$cufd</cufd>
        <codigoSucursal>$codigoSucursal</codigoSucursal>
        <direccion>$direccion</direccion>
        <codigoPuntoVenta>$codigoPuntoVenta</codigoPuntoVenta>
        <fechaEmision>$fechaEnvio</fechaEmision>
        <nombreRazonSocial>Mi razon social</nombreRazonSocial>
        <codigoTipoDocumentoIdentidad>1</codigoTipoDocumentoIdentidad>
        <numeroDocumento>5115889</numeroDocumento>
        <complemento xsi:nil='true'/>
        <codigoCliente>51158891</codigoCliente>
        <codigoMetodoPago>1</codigoMetodoPago>
        <numeroTarjeta xsi:nil='true'/>
        <montoTotal>99</montoTotal>
        <montoTotalSujetoIva>99</montoTotalSujetoIva>
        <codigoMoneda>1</codigoMoneda>
        <tipoCambio>1</tipoCambio>
        <montoTotalMoneda>99</montoTotalMoneda>
        <montoGiftCard xsi:nil='true'/>
        <descuentoAdicional>1</descuentoAdicional>
        <codigoExcepcion xsi:nil='true'/>
        <cafc xsi:nil='true'/>
        <leyenda>Ley N° 453: Tienes derecho a recibir información sobre las características y contenidos de los productos que consumes.</leyenda>
        <usuario>pperez</usuario>
        <codigoDocumentoSector>1</codigoDocumentoSector>
    </cabecera>
        <detalle>
        <actividadEconomica>472000</actividadEconomica>
        <codigoProductoSin>99100</codigoProductoSin>
        <codigoProducto>1004693</codigoProducto>
        <descripcion>JUGO DE NARANJA EN VASO</descripcion>
        <cantidad>1</cantidad>
        <unidadMedida>1</unidadMedida>
        <precioUnitario>100</precioUnitario>
        <montoDescuento>0</montoDescuento>
        <subTotal>100</subTotal>
        <numeroSerie>124548</numeroSerie>
        <numeroImei>545454</numeroImei>
    </detalle>
</facturaElectronicaCompraVenta>";
    /*
    $data="<?xml version='1.0' encoding='UTF-8' standalone='yes'?>
<facturaComputarizadaCompraVenta xsi:noNamespaceSchemaLocation='facturaComputarizadaCompraVenta.xsd' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>
    <cabecera>
        <nitEmisor>$nit</nitEmisor>
        <razonSocialEmisor>$razonSocialEmisor</razonSocialEmisor>
        <municipio>$municipio</municipio>
        $telefonoEmisor
        <numeroFactura>$numeroFactura</numeroFactura>
        <cuf>$cuf</cuf>
        <cufd>$cufd</cufd>
        <codigoSucursal>$codigoSucursal</codigoSucursal>
        <direccion>$direccion</direccion>
    <codigoPuntoVenta>$codigoPuntoVenta</codigoPuntoVenta>
        <fechaEmision>$fechaEnvio</fechaEmision>
        <nombreRazonSocial>Mi razon social</nombreRazonSocial>
        <codigoTipoDocumentoIdentidad>1</codigoTipoDocumentoIdentidad>
        <numeroDocumento>5115889</numeroDocumento>
        <complemento xsi:nil='true'/>
        <codigoCliente>51158891</codigoCliente>
        <codigoMetodoPago>1</codigoMetodoPago>
        <numeroTarjeta xsi:nil='true'/>
        <montoTotal>99</montoTotal>
        <montoTotalSujetoIva>99</montoTotalSujetoIva>
        <codigoMoneda>1</codigoMoneda>
        <tipoCambio>1</tipoCambio>
        <montoTotalMoneda>99</montoTotalMoneda>
        <montoGiftCard xsi:nil='true'/>
        <descuentoAdicional>1</descuentoAdicional>
        <codigoExcepcion xsi:nil='true'/>
        <cafc xsi:nil='true'/>
        <leyenda>Ley N° 453: Tienes derecho a recibir información sobre las características y contenidos de los productos que consumes.</leyenda>
        <usuario>pperez</usuario>
        <codigoDocumentoSector>1</codigoDocumentoSector>
    </cabecera>
    <detalle>
        <actividadEconomica>1102200</actividadEconomica>
        <codigoProductoSin>1000701</codigoProductoSin>
        <codigoProducto>JN-131231</codigoProducto>
        <descripcion>JUGO DE NARANJA EN VASO</descripcion>
        <cantidad>1</cantidad>
        <unidadMedida>1</unidadMedida>
        <precioUnitario>100</precioUnitario>
        <montoDescuento>0</montoDescuento>
        <subTotal>100</subTotal>
        <numeroSerie>124548</numeroSerie>
        <numeroImei>545454</numeroImei>
    </detalle>
</facturaComputarizadaCompraVenta>";
    */

    $xml = new SimpleXMLElement($data);
    $dom = new DOMDocument('1.0');
    $dom->preserveWhiteSpace = false;
    $dom->formatOutput = true;
    $dom->loadXML($xml->asXML());
    $nameFile=microtime();
    $dom->save("archivos/".$nameFile.'.xml');

    firmar("archivos/".$nameFile.'.xml');

    $xml = new DOMDocument();
    $xml->load("archivos/".$nameFile.'.xml');
    if (!$xml->schemaValidate('./facturaElectronicaCompraVenta.xsd')) {
        echo "invalid";
    }
    else {
//    echo "validated";
    }
//    exit();
    $file = "archivos/".$nameFile.'.xml';
    $gzfile = "archivos/".$nameFile.'.xml'.'.gz';
    $fp = gzopen ($gzfile, 'w9');
    gzwrite ($fp, file_get_contents($file));
    gzclose($fp);

    $archivo=getFileGzip("archivos/".$nameFile.'.xml'.'.gz');
    $hashArchivo=hash('sha256', $archivo);

    $result = llamarSiat($client, 'recepcionFactura', [
        "SolicitudServicioRecepcionFactura"=>[
            "codigoAmbiente"=>$codigoAmbiente,
            "codigoDocumentoSector"=>$codigoDocumentoSector,
            "codigoEmision"=>$codigoEmision,
            "codigoModalidad"=>$codigoModalidad,
            "codigoPuntoVenta"=>$codigoPuntoVenta,
            "codigoSistema"=>$codigoSistema,
            "codigoSucursal"=>$codigoSucursal,
            "cufd"=>$cufd,
            "cuis"=>$cuis,
            "nit"=>$nit,
            "tipoFacturaDocumento"=>$tipoFacturaDocumento,
            "archivo"=>$archivo,
            "fechaEnvio"=>$fechaEnvio,
            "hashArchivo"=>$hashArchivo,
        ]
    ], $reintentos, $token);
    $r = $result->RespuestaServicioFacturacion ?? null;
    $estado = $r->codigoDescripcion ?? 'SIN RESPUESTA';
    if (($r->codigoEstado ?? 0) === 908) {
        $validadas++;
    } else {
        $rechazadas++;
        $msg = $r->mensajesList->descripcion ?? json_encode($r);
        echo "  [$i/$cantidad] factura $numeroFactura: $estado - $msg" . PHP_EOL;
    }

    if ($hacerAnulacion) {
    $result = llamarSiat($client, 'anulacionFactura', [
        "SolicitudServicioAnulacionFactura"=>[
            "codigoAmbiente"=>$codigoAmbiente,
            "codigoDocumentoSector"=>$codigoDocumentoSector,
            "codigoEmision"=>$codigoEmision,
            "codigoModalidad"=>$codigoModalidad,
            "codigoPuntoVenta"=>$codigoPuntoVenta,
            "codigoSistema"=>$codigoSistema,
            "codigoSucursal"=>$codigoSucursal,
            "cufd"=>$cufd,
            "cuis"=>$cuis,
            "nit"=>$nit,
            "tipoFacturaDocumento"=>$tipoFacturaDocumento,
            "codigoMotivo"=>"1",
            "cuf"=>$cuf,
        ]
    ], $reintentos, $token);
    $anuladas += (($result->RespuestaServicioFacturacion->codigoEstado ?? 0) === 905) ? 1 : 0;
    }

    if ($hacerReversion) {
    $result = llamarSiat($client, 'reversionAnulacionFactura', [
        "SolicitudServicioReversionAnulacionFactura"=>[
            "codigoAmbiente"=>$codigoAmbiente,
            "codigoDocumentoSector"=>$codigoDocumentoSector,
            "codigoEmision"=>$codigoEmision,
            "codigoModalidad"=>$codigoModalidad,
            "codigoPuntoVenta"=>$codigoPuntoVenta,
            "codigoSistema"=>$codigoSistema,
            "codigoSucursal"=>$codigoSucursal,
            "cufd"=>$cufd,
            "cuis"=>$cuis,
            "nit"=>$nit,
            "tipoFacturaDocumento"=>$tipoFacturaDocumento,
            "cuf"=>$cuf,
        ]
    ], $reintentos, $token);
    $revertidas += (($result->RespuestaServicioFacturacion->codigoEstado ?? 0) === 907) ? 1 : 0;
    }

    // progreso cada 10 facturas, con la velocidad real medida
    if ($i % 10 === 0 || $i === $cantidad) {
        $transcurrido = microtime(true) - $inicio;
        printf(
            "  %d/%d  validadas=%d anuladas=%d revertidas=%d rechazadas=%d  %.2f s/factura  (faltan ~%.0f s)" . PHP_EOL,
            $i, $cantidad, $validadas, $anuladas, $revertidas, $rechazadas,
            $transcurrido / $i,
            ($cantidad - $i) * ($transcurrido / $i)
        );
    }
}

$total = microtime(true) - $inicio;
printf(
    PHP_EOL . "Listo: %d facturas en %.1f s (%.2f s por factura) | validadas=%d anuladas=%d revertidas=%d rechazadas=%d" . PHP_EOL,
    $cantidad, $total, $total / max($cantidad, 1), $validadas, $anuladas, $revertidas, $rechazadas
);



function getFileGzip($fileName)
{
    $fileName = $fileName;

    $handle = fopen($fileName, "rb");
    $contents = fread($handle, filesize($fileName));
    fclose($handle);

    return $contents;
}
function deleteFile()
{
    $files = glob('archivos/*'); //obtenemos todos los nombres de los ficheros
    foreach($files as $file){
        if(is_file($file))
            unlink($file); //elimino el fichero
    }
}

function firmar($fileName){
    $doc = new DOMDocument();
    $doc->load($fileName);

    $objDSig = new XMLSecurityDSig();
    $objDSig->setCanonicalMethod(XMLSecurityDSig::EXC_C14N);
    $objDSig->addReference(
        $doc,
        XMLSecurityDSig::SHA256,
        array('http://www.w3.org/2000/09/xmldsig#enveloped-signature','http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments'),
        array('force_uri' => true)
    );
    $objKey = new XMLSecurityKey(XMLSecurityKey::RSA_SHA256, array('type'=>'private'));
    /*
    If key has a passphrase, set it using
    $objKey->passphrase = '<passphrase>';
    */
    $objKey->loadKey('key/privateKey.pem', TRUE);

    $objDSig->sign($objKey);

    $objDSig->add509Cert(file_get_contents('key/publicKey.pem'));

    $objDSig->appendSignature($doc->documentElement);
    $doc->save($fileName);
}
?>
