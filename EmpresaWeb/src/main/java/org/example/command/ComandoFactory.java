package org.example.command;

import org.example.controller.Mensajes;
import org.example.service.EmpleadoService;

import java.util.HashMap;
import java.util.Map;

/**
 * Patrón Factory: usamos esta clase para obtener el comando que corresponde a cada acción.
 * Así el FrontController no necesita un if/switch ni conocer las clases concretas.
 * Para añadir una acción nueva basta con crear su comando y registrarlo aquí.
 */

public final class ComandoFactory {

    private static final Map<String, Comando> COMANDOS = new HashMap<>();

    static {
        EmpleadoService servicio = new EmpleadoService();

        COMANDOS.put("listar", new ListarCommand(servicio));
        COMANDOS.put("formBuscar", new VistaCommand("buscarEmpleado"));
        COMANDOS.put("buscar", new BuscarCommand(servicio));
        COMANDOS.put("formSalario", new VistaCommand("buscarSalario"));
        COMANDOS.put("salario", new SalarioCommand(servicio));
        COMANDOS.put("editar", new EditarCommand(servicio));
        COMANDOS.put("guardar", new GuardarCommand(servicio));
    }

    private ComandoFactory() {
    }

    public static Comando obtener(String accion) {
        Comando comando = COMANDOS.get(accion);
        if (comando != null) {
            return comando;
        }
        return request -> Mensajes.mostrar(request, "error", "Acción no válida",
                "La acción solicitada no existe.");
    }
}