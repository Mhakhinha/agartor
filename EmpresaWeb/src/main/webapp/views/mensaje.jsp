<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Aviso</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<header><h1>Gestión de empleados y nóminas</h1></header>

<main>
    <div class="mensaje ${tipo}">
        <h2><c:out value="${titulo}"/></h2>
        <p><c:out value="${texto}"/></p>
    </div>

    <button type="button" class="boton secundario" onclick="history.back()">Volver</button>
    <a class="boton" href="${pageContext.request.contextPath}/">Menú principal</a>
</main>

</body>
</html>