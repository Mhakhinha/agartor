<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar empleados</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<header><h1>Gestión de empleados y nóminas</h1></header>

<main>
    <h2>Buscar empleados</h2>
    <p>Rellena uno o varios campos. Los que dejes vacíos se ignoran.</p>

    <form action="${pageContext.request.contextPath}/buscar" method="get">
        <label>Nombre:
            <input type="text" name="nombre">
        </label>
        <label>DNI:
            <input type="text" name="dni" maxlength="9">
        </label>
        <label>Sexo:
            <select name="sexo">
                <option value="">Cualquiera</option>
                <option value="M">M</option>
                <option value="F">F</option>
            </select>
        </label>
        <label>Categoría:
            <input type="number" name="categoria" min="1">
        </label>
        <label>Años trabajados:
            <input type="number" name="anyos" min="0">
        </label>

        <button type="submit" class="boton">Buscar</button>
        <button type="button" class="boton secundario" onclick="history.back()">Volver</button>
    </form>
</main>

</body>
</html>