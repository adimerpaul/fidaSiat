PGPASSWORD="123qwe" pg_dump -h 127.0.0.1 -p 5432 -U postgres -v sico_sfida > sico_sfida.out
tar -zcvf sico_sfida.tar.gz sico_sfida.out
