<?php

/**
 * Extrae la llave privada y el certificado de un .p12/.pfx hacia key/,
 * que es de donde firmar() lee para firmar los XML.
 *
 * Uso: php InstalarCertificado.php <ruta.p12> <password>
 *
 * El certificado DEBE corresponder al NIT de la empresa activa: el SIAT
 * rechaza la factura con el error 922 "LA FIRMA DEL XML NO CORRESPONDE AL
 * CONTRIBUYENTE" cuando el serialNumber del certificado es de otro NIT.
 */

require_once __DIR__ . '/datos_generales.php';

$ruta = $argv[1] ?? null;
$password = $argv[2] ?? null;

if ($ruta === null || $password === null) {
    exit("Uso: php InstalarCertificado.php <ruta.p12> <password>" . PHP_EOL);
}
if (!is_file($ruta)) {
    exit("No existe el archivo: $ruta" . PHP_EOL);
}

$certs = [];
if (!openssl_pkcs12_read(file_get_contents($ruta), $certs, $password)) {
    exit("No se pudo abrir el .p12 (password incorrecta?): " . openssl_error_string() . PHP_EOL);
}

$info = openssl_x509_parse($certs['cert']);
$serial = $info['subject']['serialNumber'] ?? '';
$cn = $info['subject']['CN'] ?? '';
$nitEmpresa = obtenerEmpresaSiat()['nit'];

echo "Certificado: $cn" . PHP_EOL;
echo "  serialNumber (NIT/CI): $serial" . PHP_EOL;
echo "  valido: " . date('Y-m-d', $info['validFrom_time_t']) . " a "
    . date('Y-m-d', $info['validTo_time_t']) . PHP_EOL;
echo "  NIT de la empresa activa: $nitEmpresa" . PHP_EOL;

if ($serial !== $nitEmpresa) {
    echo PHP_EOL . "ATENCION: el serialNumber del certificado ($serial) no es el NIT "
        . "de la empresa activa ($nitEmpresa). El SIAT rechazara la factura con el "
        . "error 922." . PHP_EOL;
}

if (!is_dir(__DIR__ . '/key')) {
    mkdir(__DIR__ . '/key', 0777, true);
}

openssl_pkey_export($certs['pkey'], $llavePrivada);
file_put_contents(__DIR__ . '/key/privateKey.pem', $llavePrivada);
file_put_contents(__DIR__ . '/key/publicKey.pem', $certs['cert']);

echo PHP_EOL . "Escritos key/privateKey.pem y key/publicKey.pem" . PHP_EOL;
