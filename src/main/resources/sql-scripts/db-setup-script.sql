CREATE ROLE hrms WITH
LOGIN
SUPERUSER
CREATEDB
CREATEROLE
INHERIT
REPLICATION
CONNECTION LIMIT -1
PASSWORD 'hrms';


CREATE DATABASE hrms
    WITH
    OWNER = hrms
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;