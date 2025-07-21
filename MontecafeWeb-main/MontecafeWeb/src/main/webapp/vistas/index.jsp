<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Login Montecafe</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
  <div class="login-container">
    <div class="login-box">
      <h2>Montecafe</h2>

      <!-- Formulario corregido -->
      <form id="formLogin" method="post" action="../LoginServlet">

        <label for="usuario">Usuario</label>
        <input type="text" name="usuario" id="usuario" placeholder="Ingrese su usuario" required>

        <label for="contrasena">Contraseña</label>
        <input type="password" name="contrasena" id="contrasena" placeholder="********" required>

        <button type="submit">Iniciar sesión</button>
      </form>

    </div>
  </div>

  <script src="../js/script.js" defer></script>
</body>
</html>