<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.request.contextPath}/">
</head>


<body>
<jsp:include page="inicio.jsp"/>
	<div align="center">
		<form:form action="updateProducto.htm"   commandName ="product">

			<form:hidden path="id_product" />

			<table>
				<tr>
					<td>code :</td>
					<td><form:input type="text" name="code" path="code" /></td>
				</tr>
				<tr>
					<td>name:</td>
					<td><form:input type="text" name="name" path="name" /></td>
				</tr>
				<tr>
					<td>descripción:</td>
					<td><form:input type="text" name="descripcion" path="description" /></td>
				</tr>
				<tr>
					<td>precio:</td>
					<td><form:input type="text" name="precio" path="price" /></td>
				</tr>
				<tr>
					<td>categoria:</td>
					<td><form:select path="category.id_Category">
							<c:if test="${product.category.name == 'Gaseosa'}">
								<form:option value="1" selected="true">Gaseosa</form:option>
								<form:option value="2">Cerveza</form:option>
							</c:if>
							<c:if test="${product.category.name == 'Cerveza'}">
								<form:option value="1">Gaseosa</form:option>
								<form:option value="2" selected="true">Cerveza</form:option>
							</c:if>
						</form:select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Actualizar"></td>
				</tr>
			</table>

		</form:form>

	</div>

</body>
</html>