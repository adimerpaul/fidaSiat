<?php

require_once __DIR__ . '/datos_generales.php';

/**
 * Obtiene el CUIS del punto de venta y lo guarda en datos_generales.php.
 *
 * El CUIS se solicita UNA sola vez por punto de venta (vigencia ~1 anio).
 * Uso: php GenerateCui.php [codigoPuntoVenta]
 */

$codigoPuntoVenta = 1;

$siat = obtenerDatosSiat($codigoPuntoVenta);
$cuis = $siat['cuis'];
$nit = $siat['nit'];
$token = $siat['token'];
$codigoAmbiente = $siat['codigoAmbiente'];
$codigoSistema = $siat['codigoSistema'];
$codigoSucursal = $siat['codigoSucursal'];
$modalidad = $siat['codigoModalidad'];

$client = new \SoapClient(obtenerUrlSiat('FacturacionCodigos'),  [
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

$solicitud = [
    "codigoAmbiente" => $codigoAmbiente,
    "codigoModalidad" => $modalidad,
    "codigoPuntoVenta" => $codigoPuntoVenta,
    "codigoSistema" => $codigoSistema,
    "codigoSucursal" => $codigoSucursal,
    "nit" => $nit,
];

// Solo se envia el CUIS cuando ya existe (consulta de vigencia).
if ($cuis !== '') {
    $solicitud["cuis"] = $cuis;
}

$result = $client->cuis(["SolicitudCuis" => $solicitud]);
var_dump($result);

$nuevoCuis = $result->RespuestaCuis->codigo ?? null;

if ($nuevoCuis) {
    guardarCufdSiat($codigoPuntoVenta, '', '', (string) $nuevoCuis);
    echo PHP_EOL . "CUIS guardado en datos_generales.php (PDV $codigoPuntoVenta): $nuevoCuis" . PHP_EOL;
} else {
    echo PHP_EOL . "No se obtuvo CUIS. Revisar la respuesta de arriba." . PHP_EOL;
}

/*
 * Registro de un punto de venta nuevo (solo si hace falta el PDV distinto de 0):
 *
 * POST obtenerUrlSiat('FacturacionOperaciones') -> registroPuntoVenta
 *   <SolicitudRegistroPuntoVenta>
 *      <codigoAmbiente>2</codigoAmbiente>
 *      <codigoModalidad>1</codigoModalidad>
 *      <codigoSistema>8D7FF311828ECC5CEE</codigoSistema>
 *      <codigoSucursal>0</codigoSucursal>
 *      <codigoTipoPuntoVenta>5</codigoTipoPuntoVenta>
 *      <cuis>CUIS DEL PDV 0</cuis>
 *      <descripcion>Punto de venta 1</descripcion>
 *      <nit>120987022</nit>
 *      <nombrePuntoVenta>PDV 1</nombrePuntoVenta>
 *   </SolicitudRegistroPuntoVenta>
 */
