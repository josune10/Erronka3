<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    window.onload = function() {
        var mensaje = document.getElementById('mensajeError');
        if (mensaje.innerHTML.trim() !== '') {
            mensaje.style.display = 'block';
            setTimeout(function() {
                mensaje.style.display = 'none';
            }, 5000);
        }
    };
</script>
<html>
<head>
<title>Login</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: Green">
			<div>
				<a href="https://www.icrouk.wordpress.com" class="navbar-brand"> LOGIN: APP Mantenimiento MAltuna LHII</a>
			</div>

		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			<caption class="text-center">
				<h2 class="text-center"> Login MAltuna</h2>
			</caption>
				
			<form method="post" action="LoginServlet"/>
					
					<fieldset class="form-group">
						<label>E-mail</label> <input type="email" value="" class="form-control" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required="required" placeholder="example@imaltuna.com"/>
					</fieldset>

					<fieldset class="form-group">
						<label>Password</label> <input type="password" value="" class="form-control" name="password" required="required" placeholder="xxxxxxx">
					</fieldset>

					<button type="submit" class="btn btn-success">Entrar</button>
				</form>
				<div class="text-center" id="mensajeError" style="display: none; color: red;">
    				${mensajeError}
				</div>
			</div>
		</div>
	</div>
</body>
</html>