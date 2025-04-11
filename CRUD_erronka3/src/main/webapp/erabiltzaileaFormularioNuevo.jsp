<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>ErabiltzaileaFormularioNuevo</title>
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
				<a href="https://www.icrouk.wordpress.com" class="navbar-brand"> Erronka3</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="${pageContext.request.contextPath}/listar"class="nav-link">Erabiltzaileak</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${erabiltzailea != null}">
					<form action="modificar" method="post"/>
				</c:if>
				<c:if test="${erabiltzailea == null}">
					<form action="insertar" method="post"/>
				</c:if>

				<caption>
					<h2>
						<c:if test="${erabiltzailea != null}">
            			Editar Erabiltzailea
            		</c:if>
						<c:if test="${erabiltzailea == null}">
            			Añadir Erabiltzailea
            		</c:if>
					</h2>
				</caption>

				<c:if test="${erabiltzailea != null}">
					<input type="hidden" name="id" value="<c:out value='${erabiltzailea.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>E-mail</label> <input type="email"
						value="<c:out value='${erabiltzailea.email}' />" class="form-control"
						name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Pasahitza</label> <input type="text"
						value="<c:out value='${erabiltzailea.pasahitza}' />" class="form-control"
						name="pasahitza" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Gorde</button>
				<button type="button" class="btn btn-danger" action="listar">Atzera</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>