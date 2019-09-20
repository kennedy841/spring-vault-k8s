#!/usr/bin/env bash

./vault auth enable kubernetes

./vault policy write myapp-kv-ro /Users/salvatore/dev/personal/vault-example-web/src/main/resources/conf.k8s/myapp-kv-ro.hcl

#enable service account
kubectl create serviceaccount vault-auth

#apply service account conf if necessary
kubectl apply --filename /Users/salvatore/dev/personal/vault-example-web/src/main/resources/conf.k8s/vault-auth-service-account.yml

#upload policy
./vault policy write myapp-kv-ro /Users/salvatore/dev/personal/vault-example-web/src/main/resources/conf.k8s/myapp-kv-ro.hcl

#using this only for login with vault for testing roles configuration
#enable userpass engine
#./vault auth enable userpass

#create a test-user with policy
#./vault write auth/userpass/users/test-user password=training policies=myapp-kv-ro

#get kubernetes service account using bash
export VAULT_SA_NAME=$(kubectl get sa vault-auth -o jsonpath="{.secrets[*]['name']}"); echo $VAULT_SA_NAME;

#get kubernetes service account pass

export SA_JWT_TOKEN=$(kubectl get secret $VAULT_SA_NAME -o jsonpath="{.data.token}" | base64 --decode; echo); echo $SA_JWT_TOKEN;

#get k8s cert

export SA_CA_CRT=$(kubectl get secret $VAULT_SA_NAME -o jsonpath="{.data['ca\.crt']}" | base64 --decode; echo); echo $SA_CA_CRT;

#k8s cluster ip

export K8S_HOST=$(minikube ip)

#create a k8s config on vault by passing secret
#This auth method accesses the Kubernetes TokenReview API to validate the provided JWT is still valid.
./vault write auth/kubernetes/config token_reviewer_jwt="$SA_JWT_TOKEN" kubernetes_host="https://$K8S_HOST:8443" kubernetes_ca_cert="$SA_CA_CRT"

#enable app role

./vault auth enable approle

# Create a role named, 'my-app-role' to map Kubernetes Service Account to Vault policies
#The approle auth method allows machines or apps to authenticate with Vault-defined roles.
#An "AppRole" represents a set of Vault policies and login constraints that must be met to receive a token with those policies.


./vault write auth/kubernetes/role/my-app-role \
	bound_service_account_names=vault-auth \
	bound_service_account_namespaces=default \
	policies=myapp-kv-ro \
	ttl=24h


./vault write auth/approle/role/my-app-role \
    secret_id_ttl=5m \
    secret_id_num_uses=3 \
    period=24h \
   bound_cidr_list="127.0.0.1/32" \
    bind_secret_id="true" \
    policies="myapp-kv-ro"


# Create test data in the `secret/myapp` path.
./vault kv put secret/myappsec/config username='appuser' password='suP3rsec(et!' ttl='30s'

#expose 8200 vault port inside k8s container
ssh -i $(minikube ssh-key) docker@$(minikube ip) -R 8200:localhost:8200

#in order to test the comunication between k8s and vault, launch a test container

kubectl run --generator=run-pod/v1 tmp --rm -i --tty --serviceaccount=vault-auth --image alpine:3.7

#execute this command inside the container in ordet to test the comunication

# apk update
# apk add curl jq
# VAULT_ADDR=http://10.0.2.15:8200
# curl -s $VAULT_ADDR/v1/sys/health | jq

#verify login with vault

KUBE_TOKEN=$(cat /var/run/secrets/kubernetes.io/serviceaccount/token)

curl --request POST \
        --data '{"jwt": "'"$KUBE_TOKEN"'", "role": "my-app-role"}' \
        $VAULT_ADDR/v1/auth/kubernetes/login | jq







