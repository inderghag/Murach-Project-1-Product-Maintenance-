<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Product Maintenance</title>
    <link rel="stylesheet" href="styles/products.css" type="text/css"/>
</head>
<body>
    
<h1>Product Maintenance</h1>
<table>
    <tr>
        <th>Code</th>
        <th>Description</th>
        <th class="right">Price</th>
        <th>&nbsp;</th>
        <th>&nbsp;</th>
    </tr>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:forEach var="item" items="${products}">
        
    <tr>
        <td>${item.code}</td>
        <td>${item.description}</td>
        <td class="right">${item.price}</td>
        <td><form action="productServlet" method="post">
                <input type="hidden" name="productCode" value="${item.code}">
                <input type="hidden" name="description" value="${item.description}">
                <input type="hidden" name="price" value="${item.price}">
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Edit">
            </form></td>
            <td><form action="productServlet" method="post">
                <input type="hidden" name="productCode" value="${item.code}">
                <input type="hidden" name="action" value="delete">
                <input type="submit" value="Delete">
            </form></td>
    </tr>
    </c:forEach>
</table><br>
<form action="productServlet" method="post">
     <input type="hidden" name="action" value="add">
     <button> Add Product</button>
    </form>
        
</body>
</html>