<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Consultar salario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<header><h1>Gestión de empleados y nóminas</h1></header>

<main>
    <h2>Consultar el salario de un empleado</h2>

    <form action="${pageContext.request.contextPath}/salario" method="get">
        <label>DNI del empleado:
            <input type="text" name="dni" maxlength="9" placeholder="Ej. 12345678A" required>
        </label>
        <button type="submit" class="boton">Consultar salario</button>
        <button type="button" class="boton secundario" onclick="history.back()">Volver</button>
    </form>
</main>

</body>
</html>