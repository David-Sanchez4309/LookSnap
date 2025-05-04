<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Listado de Usuarios</h1>

<h2>Clientes</h2>
<table border="1">
    <tr><th>Nombre</th><th>Correo</th><th>Acciones</th></tr>
    <c:forEach var="c" items="${clientes}">
        <tr>
            <td>${c.nombre}</td>
            <td>${c.correo}</td>
            <td>
                <form action="EliminarUsuarioServlet" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${c.id}">
                    <input type="hidden" name="rol" value="cliente">
                    <input type="submit" value="Eliminar">
                </form>
                <!-- Aquí puedes añadir botón para cambiar rol más adelante -->
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Barberos</h2>
<table border="1">
    <tr><th>Nombre</th><th>Correo</th><th>Acciones</th></tr>
    <c:forEach var="b" items="${barberos}">
        <tr>
            <td>${b.nombre}</td>
            <td>${b.correo}</td>
            <td>
                <form action="EliminarUsuarioServlet" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${b.id}">
                    <input type="hidden" name="rol" value="barbero">
                    <input type="submit" value="Eliminar">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Administradores</h2>
<table border="1">
    <tr><th>Nombre</th><th>Correo</th><th>Acciones</th></tr>
    <c:forEach var="a" items="${administradores}">
        <tr>
            <td>${a.nombre}</td>
            <td>${a.correo}</td>
            <td>
                <form action="EliminarUsuarioServlet" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${a.id}">
                    <input type="hidden" name="rol" value="administrador">
                    <input type="submit" value="Eliminar">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
