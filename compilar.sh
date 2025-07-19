#!/bin/bash

echo "=== Compilando Sistema de Locadora de Carros ==="
echo

# Verificar se Java está instalado
if ! command -v javac &> /dev/null; then
    echo "ERRO: javac não encontrado. Certifique-se de que o JDK está instalado."
    exit 1
fi

# Compilar todos os arquivos .java
echo "Compilando arquivos .java..."
javac *.java

# Verificar se a compilação foi bem-sucedida
if [ $? -eq 0 ]; then
    echo "✅ Compilação concluída com sucesso!"
    echo
    echo "Para executar o sistema, use:"
    echo "  ./executar.sh"
    echo "ou"
    echo "  java SistemaLocadoraGUI"
else
    echo "❌ Erro na compilação!"
    exit 1
fi