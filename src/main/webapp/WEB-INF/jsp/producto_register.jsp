<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Primera página</title>
<base href="${pageContext.request.contextPath}/">
</head>

<body>

	<div align="center">
		<h1>Registro de Productos</h1>

		<form action="registrarProducto.htm" method="POST">

			<table>
				<tr>
					<td>code :</td>
					<td><input type="text" name="code"></td>
				</tr>
				<tr>
					<td>name:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>descripción:</td>
					<td><input type="text" name="descripcion"></td>
				</tr> 
				<tr>
					<td>precio:</td>
					<td><input type="text" name="precio"></td>
				</tr>
				<tr>
					<td>categoria:</td>
					<td><select name="categoriaId">
							<option value="1">gaseosa</option>
							<option value="2">cerveza</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Registrar"></td>
				</tr>
			</table>

		</form>

	</div>

</body>
</html>