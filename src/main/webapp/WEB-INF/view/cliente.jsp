<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cliente</title>
</head>
<body>
	<div align="center">
		<jsp:include page="menu.jsp"></jsp:include>
	</div>
	<br />
	<div align="center">
	<form action="cliente" method="post">
		<br />
		<H1>Aula Servlet</H1>
		<br />
		<table>
			<tr>
				<td colspan="3"><input type="number" min="0" step="1" id="id" name="id" 
					placeholder="#ID" value="${cliente.id }">
				</td> 			
				<td><input type="submit" id="botao" name="botao"
					value="BUSCAR"> 	
			</tr>
			<tr>
				<td colspan="4"><input type="text" id="nome" name="nome"
					placeholder="Nome" value="${cliente.nome }">
				</td>
			</tr>
			<tr>
				<td colspan="4"><input type="date" id="nascimento" name="nascimento" 
				value="${cliente.nascimento }">
				</td>
			</tr>
			<tr>
				<td><input type="submit" id="botao" name="botao"
					value="INSERT">
				</td>
				<td><input type="submit" id="botao" name="botao"
					value="UPDATE">
				</td>
				<td><input type="submit" id="botao" name="botao"
					value="DELETE">
				</td>
				<td><input type="submit" id="botao" name="botao"
					value="LISTAR">
				</td>
			</tr>
		</table>
		<br />
		<br />
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" ></c:out></H2>
		</c:if>
		<c:if test="${not empty erro }">
			<H3><c:out value="${erro }"></c:out></H3>
		</c:if>
	</form>
	</div>
	<div align="center">
		<c:if test="${not empty clientes }">
		<table border="1">
		<thead>
			<tr>
				<th>#ID</th>
				<th>Nome</th>
				<th>Nascimento</th>
				<th></th>
				<th></th>
			</tr>
		</thead> 

		<c:forEach var="c" items="${clientes }">
			<tr>
				<td><c:out value="${c.id }"></c:out>
				<td><c:out value="${c.nome }"></c:out>
				<td><c:out value="${c.nasc }"></c:out>
				<td><a href="${pageContext.request.contextPath}/cliente?id=${c.id }&acao=EDITAR">EDITAR</a>
				<td><a href="${pageContext.request.contextPath}/cliente?id=${c.id }&acao=EXCLUIR">EXCLUIR</a>
			</tr>
		</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>
