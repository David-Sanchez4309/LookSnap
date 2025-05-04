<%@ page isELIgnored="false" %>
<p>Context path: ${pageContext.request.contextPath}</p>
<form action="${pageContext.request.contextPath}/RegistrarUsuarioServlet" method="post">

<label>Nombre:</label>
    <input type="text" name="nombre" required><br>

    <label>Correo:</label>
    <input type="email" name="correo" required><br>

    <label>Teléfono:</label>
    <input type="text" name="telefono" required><br>

    <label>Contraseña:</label>
    <input type="password" name="contrasena" required><br>

    <label>Rol:</label>
    <select name="rol" id="rol" onchange="mostrarCamposAdicionales()" required>
        <option value="">Seleccionar</option>
        <option value="cliente">Cliente</option>
        <option value="barbero">Barbero</option>
        <option value="administrador">Administrador</option>
    </select><br>

    <div id="camposCliente" style="display:none;">
        <label>Dirección:</label>
        <input type="text" name="direccion"><br>
    </div>

    <div id="camposBarbero" style="display:none;">
        <label>Especialidad:</label>
        <input type="text" name="especialidad"><br>
    </div>

    <input type="submit" value="Registrar">
</form>

<script>
    function mostrarCamposAdicionales() {
        const rol = document.getElementById("rol").value;
        document.getElementById("camposCliente").style.display = rol === "cliente" ? "block" : "none";
        document.getElementById("camposBarbero").style.display = rol === "barbero" ? "block" : "none";
    }
</script>
