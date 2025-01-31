FROM mysql:latest
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=DB_registros_futbol
ENV MYSQL_USER=jgnarvaez
ENV MYSQL_PASSWORD=root
COPY init.sql /docker-entrypoint-initdb.d/
EXPOSE 3306