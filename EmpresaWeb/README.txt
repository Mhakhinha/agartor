Este proyecto tiene el puerto de conexión en el 4306 por lo que MariaDB escucha en ese puerto.

También hay que saber que el javadoc esta en la siguiente ruta : ApacheTomcat\doc\index.htm 
hay un pequeño problema con el javadoc ya que aunque están los comentarios hechos no los asimila y no se ven.

-------------------------------------------------------------------------------------------------------------------
Los patrones que hemos usado y porque. 

Lo primero que hay que recalcar es que cada patron que metemos lo usamos para resolver un problema que tenia nuestro código.

Antes teníamos 4 servlets que repetían el control de errores pero ahora al añadir el patrón Front Controller resolvemos ese error.

Antes cada cosa que pasaba nos hacía crear un objeto y teníamos un objeto por cada if por ejemplo. Ahora tenemos el patron Command que alamacena toda la lógica y solo tenemos que llamarlo y pedirle sus servicios.

Hemos añadido un patron que va un poco de la mano con el patron Command el cual es el patron Factory. Este nos ayuda a refactorizar los if por ejemplo lo que sería así: 

ejemplo:

if (accion.equals("listar")) {
    vista = new ListarCommand(servicio).ejecutar(request);
} else if (accion.equals("buscar")) {
    vista = new BuscarCommand(servicio).ejecutar(request);
} else if (accion.equals("salario")) {
    vista = new SalarioCommand(servicio).ejecutar(request);
} else if (accion.equals("editar")) {


se quedaría así: 

vista = ComandoFactory.obtener(accion).ejecutar(request);

Para explicar un poco como funciona por dentro tendríamos que explicar que la fabrica guarda una especie de tabla que relaciona cada texto con su comando. 

Por ejemplo:

COMANDOS.put("listar", new ListarCommand(servicio));
COMANDOS.put("buscar", new BuscarCommand(servicio));
COMANDOS.put("salario", new SalarioCommand(servicio));


Un gran problema que teníamos es que todo el tema de las validaciones y el calculo de la nomina lo teníamos repartido entre varios Servlets y con el patron Service Layer lo que hace es que esa lógica pasa a estar en una sola clase (EmpleadoService) separada de la parte web de modo que no se repite en cada acción y si algo cambia solo hay que modificarla en un sitio.

El ultimo patrón que hemos usado es el Encoding filter que lo que lo que hace es que todas las peticiones lees hace antes de que lleguen al controlador y fijar UTF-8 en un solo sitio, para no repetir setCharacterEncoding en cada servlet y evitar problemas con los acentos
