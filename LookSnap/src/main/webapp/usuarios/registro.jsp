<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Crear Cuenta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

<section class="footer-bg">
    <div class="container">
        <h2>Crear Cuenta</h2>
        <form action="${pageContext.request.contextPath}/RegistrarUsuarioServlet" method="POST">
            <div class="campo-1">
                <input type="text" name="nombre" class="campo" placeholder="Nombre" required />
            </div>
            <div class="campo-1">
                <input type="email" name="correo" class="campo" placeholder="Correo Electrónico" required />
            </div>
            <div class="campo-1">
                <input type="password" name="contrasena" class="campo" placeholder="Contraseña" required />
            </div>
            <div class="campo-1">
                <input type="text" name="telefono" class="campo" placeholder="Teléfono" required />
            </div>
            <div class="campo-1">
                <select name="rol" id="rol" class="campo" onchange="mostrarCamposAdicionales()" required>
                    <option value="">Seleccionar Rol</option>
                    <option value="cliente">Cliente</option>
                    <option value="barbero">Barbero</option>
                </select>
            </div>

            <!-- Campos adicionales -->
            <div id="camposCliente" class="campo-1" style="display:none;">
                <input type="text" name="direccion" class="campo" placeholder="Dirección" />
            </div>

            <div id="camposBarbero" class="campo-1" style="display:none;">
                <input type="text" name="especialidad" class="campo" placeholder="Especialidad" />
            </div>

            <button type="submit" class="btn-1">Registrarse</button>
        </form>
    </div>
</section>

<script>
    function mostrarCamposAdicionales() {
        const rol = document.getElementById("rol").value;
        document.getElementById("camposCliente").style.display = rol === "cliente" ? "block" : "none";
        document.getElementById("camposBarbero").style.display = rol === "barbero" ? "block" : "none";
    }
</script>

</body>
</html>
