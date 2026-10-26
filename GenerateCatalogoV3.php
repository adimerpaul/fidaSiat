<?php

require_once __DIR__ . '/datos_generales.php';

/**
 * Etapa II - V.3: sincronizacion de catalogos por los servicios SOAP V3 (/sfd/).
 *
 * 18 metodos x $contador vueltas por punto de venta.
 * Por defecto PDV 0, 5 vueltas = 90 casos. Junto con GenerateCatalogoV3_2.php
 * (PDV 1) suman los 180 que pide el seguimiento.
 *
 * Uso: php GenerateCatalogoV3.php [contador] [pdv,pdv,...]
 */

$contador = (int) ($argv[1] ?? 5);
$puntosVenta = array_map('intval', explode(',', $argv[2] ?? '0'));

$metodos = [
    'sincronizarActividades',
    'sincronizarFechaHora',
    'sincronizarListaActividadesDocumentoSector',
    'sincronizarListaLeyendasFactura',
    'sincronizarListaMensajesServicios',
    'sincronizarListaProductosServicios',
    'sincronizarParametricaEventosSignificativos',
    'sincronizarParametricaMotivoAnulacion',
    'sincronizarParametricaPaisOrigen',
    'sincronizarParametricaTipoDocumentoIdentidad',
    'sincronizarParametricaTipoDocumentoSector',
    'sincronizarParametricaTipoEmision',
    'sincronizarParametricaTipoHabitacion',
    'sincronizarParametricaTipoMetodoPago',
    'sincronizarParametricaTipoMoneda',
    'sincronizarParametricaTipoPuntoVenta',
    'sincronizarParametricaTiposFactura',
    'sincronizarParametricaUnidadMedida',
];

$correctos = 0;
$fallidos = 0;

foreach ($puntosVenta as $codigoPuntoVenta) {
    $siat = obtenerDatosSiat($codigoPuntoVenta);

    if ($siat['cuis'] === '') {
        exit("Falta el CUIS del punto de venta $codigoPuntoVenta. Ejecutar primero: php GenerateCuiV3.php $codigoPuntoVenta" . PHP_EOL);
    }

    $client = new \SoapClient(obtenerUrlSiatV3('FacturacionSincronizacion'),  [
        'stream_context' => stream_context_create([
            'http' => [
                'header' => "apikey: TokenApi " . $siat['token'],
            ]
        ]),
        'cache_wsdl' => WSDL_CACHE_NONE,
        'compression' => SOAP_COMPRESSION_ACCEPT | SOAP_COMPRESSION_GZIP | SOAP_COMPRESSION_DEFLATE,
        'trace' => 1,
        'use' => SOAP_LITERAL,
        'style' => SOAP_DOCUMENT,
    ]);

    $data = [
        "SolicitudSincronizacion" => [
            "codigoAmbiente" => $siat['codigoAmbiente'],
            "codigoPuntoVenta" => $codigoPuntoVenta,
            "codigoSistema" => $siat['codigoSistema'],
            "codigoSucursal" => $siat['codigoSucursal'],
            "cuis" => $siat['cuis'],
            "nit" => $siat['nit'],
        ]
    ];

    for ($i = 0; $i < $contador; $i++) {
        foreach ($metodos as $metodo) {
            try {
                $result = $client->$metodo($data);
                // Cada respuesta trae un unico objeto Respuesta* con 'transaccion'.
                $respuesta = current((array) $result);
                $ok = ($respuesta->transaccion ?? false) === true;
            } catch (\SoapFault $e) {
                $ok = false;
                $respuesta = $e->getMessage();
            }

            if ($ok) {
                $correctos++;
            } else {
                $fallidos++;
                echo "PDV $codigoPuntoVenta vuelta " . ($i + 1) . " $metodo FALLO:" . PHP_EOL;
                var_dump($respuesta);
            }
        }
        echo "PDV $codigoPuntoVenta vuelta " . ($i + 1) . "/$contador lista" . PHP_EOL;
    }
}

echo PHP_EOL . "Correctos: $correctos  Fallidos: $fallidos" . PHP_EOL;
