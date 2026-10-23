<?php

require_once __DIR__ . '/datos_generales.php';

/**
 * Obtiene el CUFD + codigoControl del punto de venta y los guarda en
 * datos_generales.php. CADUCAN CADA 24 H: ejecutar antes de cada tanda
 * de pruebas.
 *
 * Uso: php GenerateCuf.php [codigoPuntoVenta]
 */

$codigoPuntoVenta = (int) ($argv[1] ?? 0);

$siat = obtenerDatosSiat($codigoPuntoVenta);
$cuis = $siat['cuis'];
$nit = $siat['nit'];
$token = $siat['token'];
$codigoAmbiente = $siat['codigoAmbiente'];
$codigoSistema = $siat['codigoSistema'];
$codigoSucursal = $siat['codigoSucursal'];
$modalidad = $siat['codigoModalidad'];

if ($cuis === '') {
    exit("Falta el CUIS del punto de venta $codigoPuntoVenta. Ejecutar primero: php GenerateCui.php $codigoPuntoVenta" . PHP_EOL);
}

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

$result = $client->cufd([
    "SolicitudCufd" => [
        "codigoAmbiente" => $codigoAmbiente,
        "codigoModalidad" => $modalidad,
        "codigoPuntoVenta" => $codigoPuntoVenta,
        "codigoSistema" => $codigoSistema,
        "codigoSucursal" => $codigoSucursal,
        "cuis" => $cuis,
        "nit" => $nit,
    ]
]);
var_dump($result);

$respuesta = $result->RespuestaCufd ?? null;
$cufd = $respuesta->codigo ?? null;
$codigoControl = $respuesta->codigoControl ?? null;

if ($cufd && $codigoControl) {
    guardarCufdSiat($codigoPuntoVenta, (string) $cufd, (string) $codigoControl);
    echo PHP_EOL . "CUFD guardado en datos_generales.php (PDV $codigoPuntoVenta)." . PHP_EOL
        . "  cufd:          $cufd" . PHP_EOL
        . "  codigoControl: $codigoControl" . PHP_EOL
        . "  vigente hasta: " . ($respuesta->fechaVigencia ?? '?') . PHP_EOL;
} else {
    echo PHP_EOL . "No se obtuvo CUFD. Revisar la respuesta de arriba." . PHP_EOL;
}
