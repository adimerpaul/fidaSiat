rm sico_sfida.out
rm sico_sfida.tar.gz
#wget -c http://159.203.172.69/descargas/sico_sfida.tar.gz
wget -c https://sfidatics.xyz/descargas/sico_sfida.tar.gz
tar -xvf sico_sfida.tar.gz
PGPASSWORD=123qwe dropdb -h localhost -p 5432 -U postgres sico_sfida
PGPASSWORD=123qwe createdb -h localhost -p 5432 -U postgres sico_sfida
PGPASSWORD=123qwe psql -h localhost -p 5432 -U postgres -d sico_sfida -f sico_sfida.out
PGPASSWORD=123qwe psql -h localhost -p 5432 -U postgres -d sico_sfida -f facturacion_desarrollo.sql
rm sico_sfida.out
rm sico_sfida.tar.gz