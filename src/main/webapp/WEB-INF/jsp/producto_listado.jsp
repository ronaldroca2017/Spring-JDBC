<%@ include file = "/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Productos</title>
<base href="${pageContext.request.contextPath}/">
</head>
<body>

	<div align="center">
			<table>
				<tr>
					<td>Id</td>
					<td>Producto</td>
					<td>Code</td>
					<td>Precio</td>
					<td>Descripción</td>				
				</tr>
			
				<c:forEach  items="${lstProduct}" var="prod">
					<tr>
						<td>${prod.id_product}</td>
						<td>${prod.name}</td>
						<td>${prod.code}</td>
						<td>${prod.price}</td>
						<td>${prod.description}</td>				
					</tr>
				</c:forEach>
			
			</table>
			
	</div>
	
</body>
</html>