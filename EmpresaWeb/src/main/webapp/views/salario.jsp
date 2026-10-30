<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Salario del empleado</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<header><h1>Gestión de empleados y nóminas</h1></header>

<main>
    <h2>Salario del empleado</h2>

    <c:if test="${empleado != null && nomina != null}">
        <table>
            <tr><th>Nombre</th><th>DNI</th><th>Salario</th></tr>
            <tr>
                <td><c:out value="${empleado.nombre}"/></td>
                <td><c:out value="${empleado.dni}"/></td>
                <td><fmt:formatNumber value="${nomina.sueldo}" minFractionDigits="2" maxFractionDigits="2"/> €</td>
            </tr>
        </table>
    </c:if>

    <button type="button" class="boton secundario" onclick="history.back()">Volver</button>
</main>

</body>
</html>