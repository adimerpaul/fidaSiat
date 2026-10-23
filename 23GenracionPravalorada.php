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
$codigoDocumentoSector=23; //1 compra venta,// 2qlauilres, 13 servicios basicos, 24 nota credito debito, 29 nota conciliacion
$codigoEmision=1;
$codigoModalidad = $siat['codigoModalidad'];

$codigoSistema = $siat['codigoSistema'];
$codigoSucursal = $siat['codigoSucursal'];

$nit = $siat['nit'];
$razonSocialEmisor = $siat['razonSocial'];
$municipio = $siat['municipio'];
$direccion = $siat['direccion'];
$telefonoEmisor = etiquetaTelefonoSiat($siat['telefono']);
$tipoFacturaDocumento=1;//1 con credito 2 sin credito 3 nota


$temision=1; //1 online, 2 offline, 3 masiva
//$cdf=3;
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

for ($i=1;$i<=125;$i++){
    $miliSegundo=str_pad($i, 3, '0', STR_PAD_LEFT);
    $fechaEnvio=date("Y-m-d\TH:i:s").".$miliSegundo";
    $cuf = new CUF();
    $cuf = $cuf->obtenerCUF($nit, date("YmdHis$miliSegundo"), $codigoSucursal, $codigoModalidad, $temision, $tipoFacturaDocumento, $codigoDocumentoSector, $nf, $codigoPuntoVenta);
    $cuf=$cuf.$codigoControl;
    $xml = new SimpleXMLElement("
<facturaElectronicaPrevalorada xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='facturaElectronicaPrevalorada.xsd'>
<cabecera>
<nitEmisor>$nit</nitEmisor>
<razonSocialEmisor>$razonSocialEmisor</razonSocialEmisor>
<municipio>$municipio</municipio>
$telefonoEmisor
<numeroFactura>$nf</numeroFactura>
<cuf>$cuf</cuf>
<cufd>$cufd</cufd>
<codigoSucursal>0</codigoSucursal>
<direccion>$direccion</direccion>
<codigoPuntoVenta>$codigoPuntoVenta</codigoPuntoVenta>
<fechaEmision>$fechaEnvio</fechaEmision>
<nombreRazonSocial>S/N</nombreRazonSocial>
<codigoTipoDocumentoIdentidad>5</codigoTipoDocumentoIdentidad>
<numeroDocumento>0</numeroDocumento>
<codigoCliente>N/A</codigoCliente>
<codigoMetodoPago>1</codigoMetodoPago>
<numeroTarjeta xsi:nil='true'/>
<montoTotal>100</montoTotal>
<montoTotalSujetoIva>100</montoTotalSujetoIva>
<codigoMoneda>1</codigoMoneda>
<tipoCambio>1</tipoCambio>
<montoTotalMoneda>100</montoTotalMoneda>
<leyenda>Ley N° 453: Tienes derecho a recibir información sobre las características y contenidos de los servicios que utilices.</leyenda>
<usuario>pperez</usuario>
<codigoDocumentoSector>23</codigoDocumentoSector>
</cabecera>
<detalle>
<actividadEconomica>522000</actividadEconomica>
<codigoProductoSin>67430</codigoProductoSin>
<codigoProducto>JN-131231</codigoProducto>
<descripcion>PASAJE A COCHABAMBA</descripcion>
<cantidad>1</cantidad>
<unidadMedida>62</unidadMedida>
<precioUnitario>100</precioUnitario>
<montoDescuento>0</montoDescuento>
<subTotal>100</subTotal>
</detalle>
</facturaElectronicaPrevalorada>
    ");
    $dom = new DOMDocument('1.0');
    $dom->preserveWhiteSpace = false;
    $dom->formatOutput = true;
    $dom->loadXML($xml->asXML());
    $nameFile=microtime();
    $dom->save("archivos/".$nameFile.'.xml');

    firmar("archivos/".$nameFile.'.xml');

    $xml = new DOMDocument();
    $xml->load("archivos/".$nameFile.'.xml');
    if (!$xml->schemaValidate('./facturaElectronicaPrevalorada.xsd')) {
        echo "invalid";
    }
    else {
//        echo "validated";
    }
//    exit;
    $file = "archivos/".$nameFile.'.xml';
    $gzfile = "archivos/".$nameFile.'.xml'.'.gz';
    $fp = gzopen ($gzfile, 'w9');
    gzwrite ($fp, file_get_contents($file));
    gzclose($fp);

    $archivo=getFileGzip("archivos/".$nameFile.'.xml'.'.gz');
    $hashArchivo=hash('sha256', $archivo);

    $client = new \SoapClient(obtenerUrlSiat('ServicioFacturacionElectronica'),  [
        'stream_context' => stream_context_create([
            'http' => [
                'header' => "apikey: TokenApi " . $token,
            ]
        ]),
        'cache_wsdl' => WSDL_CACHE_NONE,
        'compression' => SOAP_COMPRESSION_ACCEPT | SOAP_COMPRESSION_GZIP | SOAP_COMPRESSION_DEFLATE,
        'trace' => 1,
        'use' => SOAP_LITERAL,
        'style' => SOAP_DOCUMENT,
    ]);
    $result= $client->recepcionFactura([
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
    ]);
    var_dump($result);
}



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
