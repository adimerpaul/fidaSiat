<?php

require_once __DIR__ . '/datos_generales.php';

/**
 * Etapa III - V.3: solicita CUFD + codigoControl por los servicios SOAP V3 (/sfd/)
 * para el punto de venta, $contador veces, y guarda
 * el ultimo obtenido en datos_generales.php.
 *
 * El CUFD CADUCA CADA 24 H: ejecutar antes de cada tanda de pruebas.
 *
 * Uso: php GenerateCufV3_2.php [codigoPuntoVenta] [contador]
 *
 * Por defecto 10 solicitudes; junto con el otro GenerateCufV3* suman los 20 casos.
 */

$codigoPuntoVenta = (int) ($argv[1] ?? 1);
$contador = (int) ($argv[2] ?? 10);

$siat = obtenerDatosSiat($codigoPuntoVenta);
$cuis = $siat['cuis'];
$nit = $siat['nit'];
$token = $siat['token'];
$codigoAmbiente = $siat['codigoAmbiente'];
$codigoSistema = $siat['codigoSistema'];
$codigoSucursal = $siat['codigoSucursal'];
$modalidad = $siat['codigoModalidad'];

if ($cuis === '') {
    exit("Falta el CUIS del punto de venta $codigoPuntoVenta. Ejecutar primero: php GenerateCuiV3.php $codigoPuntoVenta" . PHP_EOL);
}

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

$solicitud = [
    "SolicitudCufd" => [
        "codigoAmbiente" => $codigoAmbiente,
        "codigoModalidad" => $modalidad,
        "codigoPuntoVenta" => $codigoPuntoVenta,
        "codigoSistema" => $codigoSistema,
        "codigoSucursal" => $codigoSucursal,
        "cuis" => $cuis,
        "nit" => $nit,
    ]
];

$exitosas = 0;
$fallidas = 0;
$cufd = null;
$codigoControl = null;
$fechaVigencia = null;
$codigoInterno = null;

for ($i = 1; $i <= $contador; $i++) {
    try {
        $result = $client->cufd($solicitud);
    } catch (Throwable $e) {
        $fallidas++;
        echo "[$i/$contador] ERROR: " . $e->getMessage() . PHP_EOL;
        continue;
    }

    $respuesta = $result->RespuestaCufd ?? null;

    if (isset($respuesta->codigo, $respuesta->codigoControl)) {
        $exitosas++;
        $cufd = (string) $respuesta->codigo;
        $codigoControl = (string) $respuesta->codigoControl;
        $fechaVigencia = $respuesta->fechaVigencia ?? '?';
        $codigoInterno = (string) ($respuesta->codigoInterno ?? "");
        echo "[$i/$contador] OK  $codigoControl  (vigente hasta $fechaVigencia)" . PHP_EOL;
    } else {
        $fallidas++;
        echo "[$i/$contador] RECHAZADA: " . json_encode($result) . PHP_EOL;
    }
}

echo PHP_EOL . "PDV $codigoPuntoVenta - solicitudes: $contador | exitosas: $exitosas | fallidas: $fallidas" . PHP_EOL;

if ($cufd !== null && $codigoControl !== null) {
    guardarCufdSiat($codigoPuntoVenta, $cufd, $codigoControl, null, null, (string) $fechaVigencia, $codigoInterno);
    echo PHP_EOL . "Ultimo CUFD guardado en datos_generales.php (PDV $codigoPuntoVenta)." . PHP_EOL
        . "  cufd:          $cufd" . PHP_EOL
        . "  codigoControl: $codigoControl" . PHP_EOL
        . "  vigente hasta: $fechaVigencia" . PHP_EOL
        . "  codigoInterno: $codigoInterno" . PHP_EOL;
} else {
    echo PHP_EOL . "No se obtuvo ningun CUFD. Revisar las respuestas de arriba." . PHP_EOL;
}
