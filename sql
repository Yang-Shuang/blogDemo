 CREATE TABLE blog_users(
 userId INT UNSIGNED AUTO_INCRE
 userName VARCHAR(100) NOT NULL
 userPassWord VARCHAR(100) NOT
 userMail VARCHAR(50),
 userMobile VARCHAR(20),
 userHeadPic VARCHAR(200),
 PRIMARY KEY (userId));


 CREATE USER 'tomcat'@'localhost' IDENTIFIED BY '654321';