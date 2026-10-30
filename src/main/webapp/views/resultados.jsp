<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultados de la búsqueda</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<header><h1>Gestión de empleados y nóminas</h1></header>

<main>
    <h2>Resultados de la búsqueda</h2>
    <p>Empleados encontrados: <strong>${empleados.size()}</strong></p>

    <table>
        <tr>
            <th>Nombre</th>
            <th>DNI</th>
            <th>Sexo</th>
            <th>Categoría</th>
            <th>Años trabajados</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="e" items="${empleados}">
            <tr>
                <td><c:out value="${e.nombre}"/></td>
                <td><c:out value="${e.dni}"/></td>
                <td><c:out value="${e.sexo}"/></td>
                <td>${e.categoria}</td>
                <td>${e.anyos}</td>
                <td>
                    <a class="boton" href="${pageContext.request.contextPath}/editar?dni=${e.dni}">Editar</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <button type="button" class="boton secundario" onclick="history.back()">Volver</button>
</main>

</body>
</html>