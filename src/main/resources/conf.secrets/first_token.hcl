# For K/V v1 secrets engine
path "secret/myappsec/test/*" {
  capabilities = [ "read" ,"list"]
}

path "secret/myappsec"{
  capabilities = [ "read" ,"list"]
}


# For K/V v2 secrets engine
path "secret/data/myappsec/*" {
  capabilities = ["create", "read", "update", "delete", "list"]
}

path "database/creds/myapp-role" {
  capabilities = ["create", "read", "update", "delete", "list"]
}

path "database/creds/myapp-role/*" {
  capabilities = ["create", "read", "update", "delete", "list"]
}

path "secret/application/*"{
  capabilities = [ "read" ,"list"]
}

path "secret/data/application/*"{
  capabilities = [ "read" ,"list"]
}

path "secret/application"{
  capabilities = [ "read" ,"list"]
}

path "secret/data/application"{
  capabilities = [ "read" ,"list"]
}

