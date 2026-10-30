<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar empleado</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<header><h1>Gestión de empleados y nóminas</h1></header>

<main>
    <h2>Modificar empleado</h2>

    <form action="${pageContext.request.contextPath}/editar" method="post">
        <input type="hidden" name="dni" value="<c:out value='${empleado.dni}'/>">

        <label>DNI (no modificable):
            <input type="text" value="<c:out value='${empleado.dni}'/>" disabled>
        </label>
        <label>Nombre:
            <input type="text" name="nombre" value="<c:out value='${empleado.nombre}'/>" required>
        </label>
        <label>Sexo:
            <select name="sexo">
                <option value="M" ${empleado.sexo == 'M' ? 'selected' : ''}>M</option>
                <option value="F" ${empleado.sexo == 'F' ? 'selected' : ''}>F</option>
            </select>
        </label>
        <label>Categoría (1 a 6):
            <input type="number" name="categoria" min="1" max="6" value="${empleado.categoria}" required>
        </label>
        <label>Años trabajados:
            <input type="number" name="anyos" min="0" value="${empleado.anyos}" required>
        </label>

        <c:if test="${nomina != null}">
            <p>Sueldo actual:
                <strong><fmt:formatNumber value="${nomina.sueldo}" minFractionDigits="2" maxFractionDigits="2"/> €</strong>
                <br><small>El sueldo no se edita: se recalcula al cambiar la categoría o los años.</small>
            </p>
        </c:if>

        <button type="submit" class="boton">Guardar cambios</button>
        <button type="button" class="boton secundario" onclick="history.back()">Volver</button>
    </form>
</main>

</body>
</html>