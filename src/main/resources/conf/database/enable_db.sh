#!/usr/bin/env bash


#enable database engine
./vault secrets enable database


#configure vault to comunicate with mysql

./vault write database/config/my-mysql-database \
    plugin_name=mysql-database-plugin \
    connection_url="{{username}}:{{password}}@tcp(127.0.0.1:3306)/" \
    allowed_roles="myapp-role" \
    username="root" \
    password="kers"


#configure vault in order to create new credential for app

./vault write database/roles/myapp-role \
    db_name=my-mysql-database \
    creation_statements="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}';GRANT SELECT,UPDATE,INSERT ON *.* TO '{{name}}'@'%';" \
    root_rotation_statements="ALTER USER ‘{{name}}’@‘%’ IDENTIFIED BY '{{password}}'" \
    default_ttl="1m" \
    max_ttl="24h"