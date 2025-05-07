<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Listado de Usuarios</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <style>
        .tabla-usuarios {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 2rem;
        }

        .tabla-usuarios th, .tabla-usuarios td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }

        .tabla-usuarios th {
            background-color: #f4f4f4;
        }

        .seccion-usuarios {
            margin: 40px auto;
            width: 90%;
            max-width: 1000px;
        }

        .btn-eliminar {
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 8px 14px;
            cursor: pointer;
            border-radius: 4px;
        }

        .btn-eliminar:hover {
            background-color: #c0392b;
        }

        h1, h2 {
            text-align: center;
        }
    </style>
</head>
<body>

<section class="seccion-usuarios">
    <h1>Listado de Usuarios</h1>

    <h2>Clientes</h2>
    <table class="tabla-usuarios">
        <tr><th>Nombre</th><th>Correo</th><th>Acciones</th></tr>
        <c:forEach var="c" items="${clientes}">
            <tr>
                <td>${c.nombre}</td>
                <td>${c.correo}</td>
                <td>
                    <form action="EliminarUsuarioServlet" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${c.id}" />
                        <input type="hidden" name="rol" value="cliente" />
                        <input type="submit" value="Eliminar" class="btn-eliminar" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Barberos</h2>
    <table class="tabla-usuarios">
        <tr><th>Nombre</th><th>Correo</th><th>Acciones</th></tr>
        <c:forEach var="b" items="${barberos}">
            <tr>
                <td>${b.nombre}</td>
                <td>${b.correo}</td>
                <td>
                    <form action="EliminarUsuarioServlet" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${b.id}" />
                        <input type="hidden" name="rol" value="barbero" />
                        <input type="submit" value="Eliminar" class="btn-eliminar" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Administradores</h2>
    <table class="tabla-usuarios">
        <tr><th>Nombre</th><th>Correo</th><th>Acciones</th></tr>
        <c:forEach var="a" items="${administradores}">
            <tr>
                <td>${a.nombre}</td>
                <td>${a.correo}</td>
                <td>
                    <form action="EliminarUsuarioServlet" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${a.id}" />
                        <input type="hidden" name="rol" value="administrador" />
                        <input type="submit" value="Eliminar" class="btn-eliminar" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>
