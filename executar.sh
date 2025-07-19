#!/bin/bash

echo "=== Executando Sistema de Locadora de Carros ==="
echo

# Verificar se Java está instalado
if ! command -v java &> /dev/null; then
    echo "ERRO: java não encontrado. Certifique-se de que o JRE/JDK está instalado."
    exit 1
fi

# Verificar se a classe principal existe
if [ ! -f "SistemaLocadoraGUI.class" ]; then
    echo "ERRO: SistemaLocadoraGUI.class não encontrado."
    echo "Execute primeiro: ./compilar.sh"
    exit 1
fi

# Executar o sistema
echo "Iniciando o sistema..."
java SistemaLocadoraGUI