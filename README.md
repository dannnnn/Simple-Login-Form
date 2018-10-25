# Simple-Login-Form
Simple web-application using using Spring/Spring-Boot

To setup the database you first need to install a Postgresql server. 
After you've installed it add the bin folder to your Path system variable.

Then you have to open cmd, and login as postgres superuser, by deafult its postgres:

psql -U postgress postgress and enter your password.

When postgres=# appears you should type 

create database app_data;

And database will be created. 
If you want you can change your database name, but you'll also have to change application.properties file.

spring.datasource.url= jdbc:postgresql://localhost:5432/"here goes name of database, by default its app_data, you have to change here"

By the way, in application.properties you'll need to change password line. 

spring.datasource.password="your password for postgres superuser goes here"

After database is created you can run the application from IDE. Main class is called Application.
I've used IntelijIDEA but I doubt there will be any problems in yours IDE
