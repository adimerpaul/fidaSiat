--update seguridad.usuarios set clave2=crypt('123qwe',gen_salt('bf', 8));

--select * from seguridad.usuarios;