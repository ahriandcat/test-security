# Sử dụng image MySQL phiên bản mới nhất
FROM mysql:latest

# Chuyển sang thư mục /docker-entrypoint-initdb.d
WORKDIR /docker-entrypoint-initdb.d

ADD target/myproject.jar /app.jar

# Copy file init.sql vào thư mục /docker-entrypoint-initdb.d
COPY init.sql /docker-entrypoint-initdb.d

# Cấu hình một số biến môi trường cho MySQL
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=mydatabase
ENV MYSQL_USER=myuser
ENV MYSQL_PASSWORD=mypassword

# Sử dụng image phpMyAdmin phiên bản mới nhất
FROM phpmyadmin/phpmyadmin:latest

# Cấu hình một số biến môi trường cho phpMyAdmin
ENV PMA_HOST=mysql_database
ENV PMA_USER=root
ENV PMA_PASSWORD=password
ENV PMA_ARBITRARY=1
