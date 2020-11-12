# MercadeoUCAB

## Instalación local
1. Clone el repositorio
2. Abra el directorio `MercadeoUCAB-frontend` en una consola y ejecute el comando `npm i`
3. Finalmente ejecute el comando `ng serve`, esto inicializará la API del frontend en `localhost:4200`

## Servidor de pruebas
 Se puede utilizar json-server para hacer pruebas de request http, para ello, debemos instalar json-server: `npm i -g json-server`, luego, debe abrir una nueva consola que apunte al directorio `json-server` del proyecto y ejecutar el siguiente comando:
 ```json-server --watch mock_data.json```
 
 Esto inicializará el servidor de pruebas en `localhost:3000`
