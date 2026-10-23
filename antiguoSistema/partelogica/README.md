openssl pkcs12 -in softoken.p12 -nocerts -out key.pem
openssl pkcs12 -in softoken.p12 -clcerts -nokeys -out cert.crt
openssl rsa -des3 -in key.pem -out encrypted-key.pem
openssl rsa -in encrypted-key.pem -out key.pem

Crear certificadora
--Contenido configuracion.txt
oid_section = OIDs

[ req ]
default_bits = 2048
prompt = no
encrypt_key = no
default_md = sha1
distinguished_name = dn

[ OIDs ]
uidNumber = 1.3.6.1.1.1.1.0

[ dn ]
CN = SFIDAISA
O = Razon Social
OU = unidad organizacional
title = cargo del representante
C = BO
dnQualifier = NIT
uidNumber = 120987022
uid = 20
serialNumber = 120987022
emailAddress = info@sfida.com.bo

openssl req -new -config configuracion.txt -keyout sfida.pem -out sfida.csr
openssl x509 -req -in sfida.csr -signkey sfida.pem -out sfida.crt

select idfactura, idusuario, idcliente, numerofactura, total, descuento, efectivo, cambio, idpedido
from facturacion.facturas where idusuario=26 and codigo=908;

select sum(total) as total, sum(descuento) as descuento, sum(efectivo) as efectivo, sum(cambio) as cambio
from facturacion.facturas where idusuario=26 and codigo=908;

select idfactura, idusuario, idcliente, numerofactura, total, descuento, efectivo, cambio, idpedido
from facturacion.facturas where idusuario=26 and codigo=905;

select sum(total) as total, sum(descuento) as descuento, sum(efectivo) as efectivo, sum(cambio) as cambio
from facturacion.facturas where idusuario=26 and codigo=905;


