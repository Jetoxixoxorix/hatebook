mysql> create database portal;
mysql> create user 'portaluser'@'localhost' identified by 'qwerty';
mysql> grant all on portal.* to 'portaluser'@'localhost';