<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Listado de Productos</title>
<base href="${pageContext.request.contextPath}/">
</head>

<body>

	<div align="center">
		<table>
			<tr>
				<td>Code</td>
				<td>Producto</td>
				<td>Descripción</td>
				<td>Precio</td>
				<td>Categoría</td>
			</tr>

			<c:forEach items="${lstProduct}" var="prod">
				<tr>
					<td><a href="loadProducto.htm?id=${prod.id_product}">${prod.code}</a></td>
					<td>${prod.name}</td>
					<td>${prod.description}</td>
					<td>${prod.price}</td>				
					<td>${prod.category.name}</td>
				</tr>
			</c:forEach>

		</table>

	</div>

</body>
</html>