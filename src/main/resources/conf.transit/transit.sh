#!/usr/bin/env bash
#Mount transit backend
./vault secrets enable transit

#Create transit key
./vault write -f transit/keys/pan