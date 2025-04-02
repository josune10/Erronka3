<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>APP Mantenimineto Usuarios</title>
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
				<a href="https://www.marca.com" class="navbar-brand"> APP Mantenimiento Usuarios</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listar"
					class="nav-link">Usuarios</a></li>
					
						<li><a href="<%=request.getContextPath()%>/listar"
					class="nav-link">Aulas</a></li>
					
						<li><a href="<%=request.getContextPath()%>/listar"
					class="nav-link">edificios</a></li>
					
						<li><a href="<%=request.getContextPath()%>/listar"
					class="nav-link">ordenadores</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Usuarios</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/nuevo" class="btn btn-success">Añadir
					Nuevo Usuario</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>Email</th>
						
						<th>Acciones/Operaciones</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="usuario" items="${listarTodoLosUsuarios}">

						<tr>
							<td><c:out value="${usuario.id}" /></td>
							<td><c:out value="${usuario.nombre}" /></td>
							<td><c:out value="${usuario.email}" /></td>
						
							<td><a href="editar?id=<c:out value='${usuario.id}' />">Editar</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="eliminar?id=<c:out value='${usuario.id}' />">Eliminar</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>