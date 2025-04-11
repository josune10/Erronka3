<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Listar Erabiltzaileak</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: Green">
			<div>
				<a href="https://www.icrouk.wordpress.com" class="navbar-brand"> Listar Erabiltzaileak</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="${pageContext.request.contextPath}/listar" class="nav-link">Erabiltzaileak</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">

		<div class="container">
			<h2 class="text-center">Erabiltzaileak</h2>
			(<%=request.getContextPath()%>)
			${pageContext.request.contextPath}
			<hr>
			<div class="container text-left">
				<a href="${pageContext.request.contextPath}/nuevo" class="btn btn-success">Añadir Nuevo Erabiltzailea</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/exportarDxml" class="btn btn-success" style="background-color: Red">Importar desde XML</a>
				<a href="${pageContext.request.contextPath}/" class="btn btn-warning" style="background-color: Orange">Exportar a Excel</a>
			</div>
			<div class="container text-right">
				
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="text-center">Identifikatzailea</th>
						<th class="text-center">Email</th>
						<th class="text-center">Pasahitza</th>
						<th class="text-center">Rola</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="usuario" items="${listarTodosLosErabiltzaileak}">

						<tr>
							<td class="text-center"><c:out value="${erabiltzailea.id_erabiltzaile}" /></td>
							<td class="text-center"><c:out value="${erabiltzailea.email}" /></td>
							<td class="text-center"><c:out value="${erabiltzailea.pasahitza}" /></td>
							<td class="text-center"><c:out value="${erabiltzailea.fk_id_rola}" /></td>
						
							<td class="text-center">
								<a href="editar?id=<c:out value='${erabiltzailea.id_erabiltzaile}' />">Editar</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="eliminar?id=<c:out value='${erabiltzailea.id_erabiltzaile}' />" onclick="return confirm('¿Seguro que desea elimiralo?');">Eliminar</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	<footer class="navbar-nav">
  		<div class="container"></div>
	</footer>
</body>
</html>