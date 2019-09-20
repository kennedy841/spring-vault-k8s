# For K/V v1 secrets engine
path "secret/myappsec/*" {
    capabilities = ["read", "list"]
}
# For K/V v2 secrets engine
path "secret/data/myappsec/*" {
    capabilities = ["read", "list"]
}

path "auth/approle/role/my-app-role/role-id" {
  capabilities = ["read"]
}

path "auth/approle/role/my-app-role/secret-id" {
  capabilities = ["update"]
}
