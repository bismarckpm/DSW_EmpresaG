# MercadeoUCAB

## Instalación local
1. Clone el repositorio
2. Abra el directorio `MercadeoUCAB-frontend` en una consola y ejecute el comando `npm i`
3. Finalmente ejecute el comando `ng serve`, esto inicializará la API del frontend en `localhost:4200`

## Servidor de pruebas
 Se puede utilizar json-server para hacer pruebas de request http, para ello, debemos instalar json-server: `npm i -g json-server`, luego, debe abrir una nueva consola que apunte al directorio `json-server` del proyecto y ejecutar el siguiente comando:
 ```json-server --watch mock_data.json```
 
 Esto inicializará el servidor de pruebas en `localhost:3000`

## TODO
1. Cuando se integre el backend, en la parte de mostrar preguntas usara los atributos: tipo, subcategoria, categoria, en la parte de agregar y editar usara los atributos: id_tipo, id_subcategoria, id_categoria

2. El error handler del process http service message debe mostrar los errores que salgan del backend, por ahora solo muestra [Object object]

3. Si al eliminar una pregunta del estudio vuelve a aparecer al actualizarse la pagina es porque las rutas de question y studies tienen referenciados objetos diferentes, cuando se haga la integracion con el backend se va a eliminar la pregunta y la relacion con su estudio con cascade

4. Al concluir un estudio se modifica el id_estado en la ruta "studies" a "Terminado", pero no se actualiza en la ruta "stats", cuando se maneje desde el servidor real deberia ser consistente

5. Si se toma la misma encuesta va a dar error porque la ruta esta usando los id de las encuestas, cuando se integre con el backend este error se va a arreglar solo

6. Al hacer una solicitud el nombre no se refleja en la tabla porque solo está tomando los ID