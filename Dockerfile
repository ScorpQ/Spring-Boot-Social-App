FROM mysql:latest

MAINTAINER cansin-macbook

RUN chown -R mysql:root /var/lib/mysql/

ENV MYSQL_DATABASE=social-media
ENV MYSQL_USER=mysql-java
ENV MYSQL_PASSWORD=password
ENV MYSQL_ROOT_PASSWORD=password

EXPOSE 3306