<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>APP Mantenimineto Usuarios</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: Green">
			<div>
				<a href="https://www.icrouk.wordpress.com" class="navbar-brand"> APP Mantenimiento MAltuna LHII</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="${pageContext.request.contextPath}/listar" class="nav-link">Usuarios</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h2 class="text-center">Usuarios</h2>
			(<%=request.getContextPath()%>)
			${pageContext.request.contextPath}
			<hr>
			<div class="container text-left">
				<a href="${pageContext.request.contextPath}/nuevo" class="btn btn-success">Añadir Nuevo Usuario</a>
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
						<th class="text-center">Identifikatzailea (id)</th>
						<th class="text-center">Nombre</th>
						<th class="text-center">Email</th>
						<th class="text-center">PassWord</th>
						<th class="text-center">Acciones/Operaciones</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="usuario" items="${listarTodoLosUsuarios}">

						<tr>
							<td class="text-center"><c:out value="${usuario.id}" /></td>
							<td class="text-center"><c:out value="${usuario.nombre}" /></td>
							<td class="text-center"><c:out value="${usuario.email}" /></td>
							<td class="text-center"><c:out value="${usuario.password}" /></td>
						
							<td class="text-center">
								<a href="editar?id=<c:out value='${usuario.id}' />">Editar</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="eliminar?id=<c:out value='${usuario.id}' />" onclick="return confirm('¿Seguro que desea elimiralo?');">Eliminar</a>
							</td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	<footer class="navbar-nav">
  		<div class="container">Iker Coranti -- <a href="mailto:icoranti@imaltuna.com"> icoranti@imaltuna.com</a></div>
	</footer>
</body>
</html>