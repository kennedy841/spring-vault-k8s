#!/usr/bin/env bash

./vault policy write myapp-ro /Users/salvatore/dev/personal/vault-example-web/src/main/resources/conf.secrets/first_token.hcl

./vault token create -policy=myapp-ro

./vault kv put secret/myappsec/test api_key=abc1234 api_secret=1a2b3c4d


