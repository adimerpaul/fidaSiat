<?php

declare(strict_types=1);

date_default_timezone_set('America/La_Paz');

/**
 * Configuracion SIAT centralizada.
 *
 * EMPRESA ACTIVA: 'sfida' (SFIDA INVERSIONES S.A., NIT 120987022, Oruro).
 *
 * AMBIENTE 2 = PRUEBAS (piloto). Todos los scripts apuntan al host piloto
 * via obtenerUrlSiat(); para pasar a produccion basta cambiar
 * 'codigoAmbiente' => 1 en la empresa activa.
 *
 * MODALIDAD 1 = ELECTRONICA EN LINEA (segun solicitud R-1359 Nro. 9909).
 * En esta modalidad los XML usan la raiz <facturaElectronicaCompraVenta>,
 * se validan contra facturaElectronicaCompraVenta.xsd y DEBEN llevar firma
 * digital XMLDSig (por eso firmar() queda activo en los flujos).
 *
 * Constantes SIAT:
 *   codigoAmbiente:  1 = PRODUCCION, 2 = PRUEBAS
 *   codigoModalidad: 1 = ELECTRONICA EN LINEA, 2 = COMPUTARIZADA EN LINEA
 *   codigoEmision:   1 = EN LINEA, 2 = FUERA DE LINEA, 3 = MASIVA
 */

const SIAT_EMPRESA_ACTIVA = 'sfida';

/**
 * Endpoints por ambiente (equivale a la tabla `tburls` del sistema Aron-9).
 * codigoAmbiente 1 = produccion, 2 = pruebas/piloto.
 */
function obtenerUrlSiat(string $servicio, ?int $codigoAmbiente = null): string
{
    if ($codigoAmbiente === null) {
        $codigoAmbiente = obtenerAmbienteSiat();
    }

    $base = $codigoAmbiente === 1
        ? 'https://siatrest.impuestos.gob.bo/v2/'
        : 'https://pilotosiatservicios.impuestos.gob.bo/v2/';

    return $base . $servicio . '?WSDL';
}

/**
 * Ambiente de la empresa activa, sin exigir que exista un punto de venta.
 */
function obtenerAmbienteSiat(?string $empresa = null): int
{
    return obtenerEmpresaSiat($empresa)['codigoAmbiente'];
}

/**
 * Configuracion cruda de la empresa (sin resolver punto de venta).
 */
function obtenerEmpresaSiat(?string $empresa = null): array
{
    $empresa = $empresa ?? SIAT_EMPRESA_ACTIVA;

    $empresas = [

        /* ------------------------------------------------------------------
         * SFIDA INVERSIONES S.A. - NIT 120987022 - Oruro - PRUEBAS
         * Solicitud R-1359 Nro. 9909 del 21/10/2026, Gerencia Distrital Oruro.
         * Sistema "SFIDA PROPIOS" (propio), version 1.
         * Modalidad autorizada: ELECTRONICA EN LINEA (codigoModalidad 1).
         * Funcionalidades declaradas: PUNTO DE VENTA, EMISIONES FUERA DE LINEA.
         * Usuario SIAT: info@sfida.com.bo
         * Token JWT: emitido 2026-10-23, expira 2027-10-23 (nitDelegado 120987022).
         * ------------------------------------------------------------------ */
        'sfida' => [
            'razonSocial' => 'SFIDA INVERSIONES S.A.',
            'nit' => '120987022',
            'token' => 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbmZvQHNmaWRhLmNvbS5ibyIsImNvZGlnb1Npc3RlbWEiOiI4RDdGRjMxMTgyOEVDQzVDRUUiLCJuaXQiOiJINHNJQUFBQUFBQUFBRE0wTXJDME1EY3dNZ0lBTnJUY2Jna0FBQUE9IiwiaWQiOjUwMDMwMzgsImV4cCI6MTgyNDI4NDEzOCwiaWF0IjoxNzkyNzYyNTA4LCJuaXREZWxlZ2FkbyI6MTIwOTg3MDIyLCJzdWJzaXN0ZW1hIjoiU0ZFIn0.iguDlJUbbzT2DIqYj65yx6Dny_5BJbFvdnMohzaLh3-l0Iu7cCLuteIjlwPB9sA9gwz9OkBwekNobe1jxQKNGg',
            'codigoAmbiente' => 2,   // 2 = PRUEBAS
            'codigoSistema' => '8D7FF311828ECC5CEE',
            'codigoSucursal' => 0,
            'codigoModalidad' => 1,  // 1 = ELECTRONICA EN LINEA
            // Datos del emisor que van en la <cabecera> de cada XML.
            // TODO: confirmar telefono y direccion legal reales de SFIDA.
            'municipio' => 'Oruro',
            'telefono' => null,
            'direccion' => 'ORURO',
            'puntosVenta' => [
                // CUIS se obtiene una sola vez por punto de venta (GenerateCui.php).
                // CUFD y codigoControl CADUCAN CADA 24 H: regenerar con GenerateCuf.php
                // antes de emitir. Vacio = pendiente de generar.
                0 => [
                    'cuis' => '',
                    'cufd' => '',
                    'codigoControl' => '',
                ],
            ],
        ],

        /* ------------------------------------------------------------------
         * ALMACEN SOFIA - NIT 3544875019 - Oruro - PRODUCCION
         * Configuracion anterior, se conserva como historico.
         * Certificado asociado: ../SIAT/electronic/CARLOS_TANTACHUCO_QUEVEDO.pfx
         * ------------------------------------------------------------------ */
        'sofia' => [
            'razonSocial' => 'ALMACEN SOFIA',
            'nit' => '3544875019',
            'token' => 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbmZvQHNmaWRhLmNvbS5ibyIsImNvZGlnb1Npc3RlbWEiOiI4RDdGRjMxMTgyOEVDQzVDRUUiLCJuaXQiOiJINHNJQUFBQUFBQUFBRE0wTXJDME1EY3dNZ0lBTnJUY2Jna0FBQUE9IiwiaWQiOjUwMDMwMzgsImV4cCI6MTgyNDI4NDEzOCwiaWF0IjoxNzkyNzYyNTA4LCJuaXREZWxlZ2FkbyI6MTIwOTg3MDIyLCJzdWJzaXN0ZW1hIjoiU0ZFIn0.iguDlJUbbzT2DIqYj65yx6Dny_5BJbFvdnMohzaLh3-l0Iu7cCLuteIjlwPB9sA9gwz9OkBwekNobe1jxQKNGg',
            'codigoAmbiente' => 1,   // 1 = PRODUCCION
            'codigoSistema' => '7705A60CB4D41FAA14A6AC7',
            'codigoSucursal' => 0,
            'codigoModalidad' => 1,
            'municipio' => 'Oruro',
            'telefono' => null,
            'direccion' => 'Calle Campo Jordan y Av. Tacna Nro. 28, Zona Norte',
            'puntosVenta' => [
                0 => [
                    'cuis' => '545E1C0C',
                    'cufd' => 'BQXxCQHczREE=NzUZBQTE0QTZBQzc=QlVkSU1GQklhVUFcwNUE2MENCNEQ0M',
                    'codigoControl' => '746E623D070BF74',
                ],
                1 => [
                    'cuis' => '545E1C0C',
                    'cufd' => 'BQXxCQHczREE=NzUZBQTE0QTZBQzc=QlVkSU1GQklhVUFcwNUE2MENCNEQ0M',
                    'codigoControl' => '746E623D070BF74',
                ],
                2 => [
                    'cuis' => 'B76DDAAB',
                    'cufd' => 'FBQUFCQ34qREE=IxOEZFQ0QyMTc=Q1VpWXhKVkdhVUMzcxRjU0NUJFQk',
                    'codigoControl' => '6AEEBD0B8FEAF74',
                ],
            ],
        ],

        /* ------------------------------------------------------------------
         * Configuracion de pruebas antigua (otro NIT). Historico.
         * ------------------------------------------------------------------ */
        'piloto_anterior' => [
            'razonSocial' => 'PRUEBAS PILOTO',
            'nit' => '5062436018',
            'token' => 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGljaGFuYXR5QGdtYWlsLmNvbSIsImNvZGlnb1Npc3RlbWEiOiIzNzFGOTk2OTJEQTBERDRENDFERSIsIm5pdCI6Ikg0c0lBQUFBQUFBQUFETTFNRE15TVRZek1MUUFBSFYxSVZrS0FBQUEiLCJpZCI6NTIyNzQ1NSwiZXhwIjoxODA0ODMxMzQ1LCJpYXQiOjE3NzMzOTYxMTUsIm5pdERlbGVnYWRvIjo1MDYyNDM2MDE4LCJzdWJzaXN0ZW1hIjoiU0ZFIn0.Bf-_BNKpY_-AJyRvep6H_q3S_Eaqpm4xpalEe9vBY9AcojCVBA8tWlYNwpo8W_UVyRRGOO_rWJaKKES39XiEKA',
            'codigoAmbiente' => 2,   // 2 = PRUEBAS
            'codigoSistema' => '371F99692DA0DD4D41DE',
            'codigoSucursal' => 0,
            'codigoModalidad' => 2,
            'municipio' => 'La Paz',
            'telefono' => null,
            'direccion' => 'LA PAZ',
            'puntosVenta' => [
                0 => [
                    'cuis' => 'F498F4FF',
                    'cufd' => 'VBQUE+QmtZR0ZBEwREQ0RDQxREU=Q8KhMm1MRktFYVMzcxRjk5NjkyRE',
                    'codigoControl' => '69E94F741CBAF74',
                ],
                1 => [
                    'cuis' => '6ED780F4',
                    'cufd' => 'JBQT5Ca1lHRkE=EwREQ0RDQxREU=Q3xud0tGS0VhVUMzcxRjk5NjkyRE',
                    'codigoControl' => '9FC9ED741CBAF74',
                ],
            ],
        ],
    ];

    if (!isset($empresas[$empresa])) {
        throw new InvalidArgumentException('Empresa no configurada: ' . $empresa);
    }

    return $empresas[$empresa];
}

/**
 * @param int         $codigoPuntoVenta Punto de venta configurado para la empresa.
 * @param string|null $empresa          Clave de empresa; null usa SIAT_EMPRESA_ACTIVA.
 */
function obtenerDatosSiat(int $codigoPuntoVenta, ?string $empresa = null): array
{
    $config = obtenerEmpresaSiat($empresa);

    if (!isset($config['puntosVenta'][$codigoPuntoVenta])) {
        throw new InvalidArgumentException(
            'Punto de venta no configurado para ' . ($empresa ?? SIAT_EMPRESA_ACTIVA)
            . ': ' . $codigoPuntoVenta
        );
    }

    $puntoVenta = $config['puntosVenta'][$codigoPuntoVenta];

    return [
        'razonSocial' => $config['razonSocial'],
        'nit' => $config['nit'],
        'token' => $config['token'],
        'codigoAmbiente' => $config['codigoAmbiente'],
        'codigoSistema' => $config['codigoSistema'],
        'codigoSucursal' => $config['codigoSucursal'],
        'codigoModalidad' => $config['codigoModalidad'],
        'codigoPuntoVenta' => $codigoPuntoVenta,
        'municipio' => $config['municipio'],
        'telefono' => $config['telefono'],
        'direccion' => $config['direccion'],
        'cuis' => $puntoVenta['cuis'],
        'cufd' => $puntoVenta['cufd'],
        'codigoControl' => $puntoVenta['codigoControl'],
    ];
}

/**
 * Devuelve el XML de <telefono> listo para interpolar: nil cuando no hay dato.
 */
function etiquetaTelefonoSiat(?string $telefono): string
{
    return ($telefono === null || $telefono === '')
        ? "<telefono xsi:nil='true'/>"
        : "<telefono>" . htmlspecialchars($telefono, ENT_XML1) . "</telefono>";
}

/**
 * Guarda el CUFD / codigoControl recien obtenido en este mismo archivo,
 * para no tener que copiarlo a mano cada 24 h.
 */
function guardarCufdSiat(
    int $codigoPuntoVenta,
    string $cufd,
    string $codigoControl,
    ?string $cuis = null,
    ?string $empresa = null
): void {
    $empresa = $empresa ?? SIAT_EMPRESA_ACTIVA;
    $archivo = __FILE__;
    $contenido = file_get_contents($archivo);

    // Aisla el bloque de la empresa pedida para no tocar las demas.
    $inicioEmpresa = strpos($contenido, "'" . $empresa . "' => [");
    if ($inicioEmpresa === false) {
        throw new RuntimeException('No se encontro el bloque de la empresa ' . $empresa);
    }
    $inicioPdv = strpos($contenido, $codigoPuntoVenta . " => [", $inicioEmpresa);
    if ($inicioPdv === false) {
        throw new RuntimeException('No se encontro el punto de venta ' . $codigoPuntoVenta);
    }
    $finPdv = strpos($contenido, '],', $inicioPdv);
    $bloque = substr($contenido, $inicioPdv, $finPdv - $inicioPdv);

    $nuevo = $bloque;
    if ($cuis !== null && $cuis !== '') {
        $nuevo = preg_replace("/'cuis' => '[^']*'/", "'cuis' => '" . $cuis . "'", $nuevo, 1);
    }
    $nuevo = preg_replace("/'cufd' => '[^']*'/", "'cufd' => '" . $cufd . "'", $nuevo, 1);
    $nuevo = preg_replace(
        "/'codigoControl' => '[^']*'/",
        "'codigoControl' => '" . $codigoControl . "'",
        $nuevo,
        1
    );

    file_put_contents($archivo, substr_replace($contenido, $nuevo, $inicioPdv, strlen($bloque)));
}
