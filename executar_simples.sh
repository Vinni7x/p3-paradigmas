#!/bin/bash

echo "=== VERSÃO SIMPLIFICADA ==="
echo "Compilando..."
javac *Simples.java

if [ $? -eq 0 ]; then
    echo "✅ Compilado com sucesso!"
    echo "Executando..."
    java SistemaSimples
else
    echo "❌ Erro na compilação!"
fi