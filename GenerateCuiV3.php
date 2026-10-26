<?php

require_once __DIR__ . '/datos_generales.php';

/**
 * Solicita el CUIS del punto de venta usando los servicios SOAP V3 (/sfd/).
 *
 * No guarda en datos_generales.php: el CUIS vigente de V2 sigue funcionando,
 * aqui solo se compara lo que devuelve V3 contra el guardado.
 *
 * Uso: php GenerateCuiV3.php [codigoPuntoVenta]
 */

$codigoPuntoVenta = (int) ($argv[1] ?? 0);

$siat = obtenerDatosSiat($codigoPuntoVenta);
$token = $siat['token'];

$client = new \SoapClient(obtenerUrlSiatV3('FacturacionCodigos'),  [
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

// El WSDL V3 de SolicitudCuis no tiene campo <cuis>, solo estos.
$solicitud = [
    "codigoAmbiente" => $siat['codigoAmbiente'],
    "codigoModalidad" => $siat['codigoModalidad'],
    "codigoPuntoVenta" => $codigoPuntoVenta,
    "codigoSistema" => $siat['codigoSistema'],
    "codigoSucursal" => $siat['codigoSucursal'],
    "nit" => $siat['nit'],
];

$result = $client->cuis(["SolicitudCuis" => $solicitud]);
var_dump($result);

$cuisV3 = $result->RespuestaCuis->codigo ?? null;

if ($cuisV3) {
    $vigencia = $result->RespuestaCuis->fechaVigencia ?? '?';
    echo PHP_EOL . "CUIS V3 (PDV $codigoPuntoVenta): $cuisV3, vigente hasta $vigencia" . PHP_EOL;
    echo $cuisV3 === $siat['cuis']
        ? "Coincide con el CUIS guardado en datos_generales.php." . PHP_EOL
        : "DISTINTO al guardado ('" . $siat['cuis'] . "')." . PHP_EOL;
} else {
    echo PHP_EOL . "No se obtuvo CUIS V3. Revisar la respuesta de arriba." . PHP_EOL;
}
