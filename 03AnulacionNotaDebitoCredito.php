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
$codigoDocumentoSector=24; //1 compra venta, 13 servicios basicos, 24 nota credito debito, 29 nota conciliacion
$codigoEmision=1;
$codigoModalidad = $siat['codigoModalidad'];

$codigoSistema = $siat['codigoSistema'];
$codigoSucursal = $siat['codigoSucursal'];

$nit = $siat['nit'];
$razonSocialEmisor = $siat['razonSocial'];
$municipio = $siat['municipio'];
$direccion = $siat['direccion'];
$telefonoEmisor = etiquetaTelefonoSiat($siat['telefono']);
$tipoFacturaDocumento=3;


$temision=1; //1 online, 2 offline, 3 masiva
$cdf=3;
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
    $cuf = $cuf->obtenerCUF($nit, date("YmdHis$miliSegundo"), $codigoSucursal, $codigoModalidad, $temision, $cdf, $codigoDocumentoSector, $nf, $codigoPuntoVenta);
    $cuf=$cuf.$codigoControl;
    $xml = new SimpleXMLElement("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>
<notaFiscalElectronicaCreditoDebito xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
                                    xsi:noNamespaceSchemaLocation='notaElectronicaCreditoDebito.xsd'>
    <cabecera>
        <nitEmisor>$nit</nitEmisor>
        <razonSocialEmisor>$razonSocialEmisor</razonSocialEmisor>
        <municipio>$municipio</municipio>
        $telefonoEmisor
        <numeroNotaCreditoDebito>1</numeroNotaCreditoDebito>
        <cuf>$cuf</cuf>
        <cufd>$cufd</cufd>
        <codigoSucursal>0</codigoSucursal>
        <direccion>$direccion</direccion>
        <codigoPuntoVenta>$codigoPuntoVenta</codigoPuntoVenta>
        <fechaEmision>$fechaEnvio</fechaEmision>
        <nombreRazonSocial>Juan Valdez</nombreRazonSocial>
        <codigoTipoDocumentoIdentidad>1</codigoTipoDocumentoIdentidad>
        <numeroDocumento>5115889</numeroDocumento>
        <complemento xsi:nil='true'/>
        <codigoCliente>51158891</codigoCliente>
        <numeroFactura>1</numeroFactura>
        <numeroAutorizacionCuf>dsa564dsa54d6as5</numeroAutorizacionCuf>
        <fechaEmisionFactura>3919-02-01T10:14:36.000</fechaEmisionFactura>
        <montoTotalOriginal>775.00</montoTotalOriginal>
        <montoTotalDevuelto>75.00</montoTotalDevuelto>
        <montoDescuentoCreditoDebito xsi:nil='true'/>
        <montoEfectivoCreditoDebito>9.75</montoEfectivoCreditoDebito>
        <codigoExcepcion xsi:nil='true'/>
        <leyenda>Ley N° 453: Los servicios deben suministrarse en condiciones de inocuidad, calidad y seguridad.
        </leyenda>
        <usuario>vjcm</usuario>
        <codigoDocumentoSector>24</codigoDocumentoSector>
    </cabecera>
    <detalle>
        <actividadEconomica>451010</actividadEconomica>
        <codigoProductoSin>49111</codigoProductoSin>
        <codigoProducto>123456</codigoProducto>
        <descripcion>Amortiguadores</descripcion>
        <cantidad>1</cantidad>
        <unidadMedida>1</unidadMedida>
        <precioUnitario>775.00</precioUnitario>
        <montoDescuento xsi:nil='true'/>
        <subTotal>775.00</subTotal>
        <codigoDetalleTransaccion>1</codigoDetalleTransaccion>
    </detalle>
    <detalle>
        <actividadEconomica>451010</actividadEconomica>
        <codigoProductoSin>49111</codigoProductoSin>
        <codigoProducto>123456</codigoProducto>
        <descripcion>Tornillos</descripcion>
        <cantidad>1</cantidad>
        <unidadMedida>1</unidadMedida>
        <precioUnitario>75.00</precioUnitario>
        <montoDescuento xsi:nil='true'/>
        <subTotal>75.00</subTotal>
        <codigoDetalleTransaccion>2</codigoDetalleTransaccion>
    </detalle>
</notaFiscalElectronicaCreditoDebito>
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
    if (!$xml->schemaValidate('./notaElectronicaCreditoDebito.xsd')) {
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

    $client = new \SoapClient(obtenerUrlSiat('ServicioFacturacionDocumentoAjuste'),  [
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
    $result= $client->recepcionDocumentoAjuste([
        "SolicitudServicioRecepcionDocumentoAjuste"=>[
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
    $result= $client->anulacionDocumentoAjuste([
        "SolicitudServicioAnulacionDocumentoAjuste"=>[
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
            "codigoMotivo"=>2,
            "cuf"=>$cuf,
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
    $objKey->loadKey('key/privatekey.pem', TRUE);

    $objDSig->sign($objKey);

    $objDSig->add509Cert(file_get_contents('key/selaimpuestos.pem'));

    $objDSig->appendSignature($doc->documentElement);
    $doc->save($fileName);
}
?>
