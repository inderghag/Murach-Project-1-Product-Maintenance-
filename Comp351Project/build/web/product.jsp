<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Maintenance</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <h1>Product</h1>
        <p><b>${message}</b></p>
        
        <form name="productServlet" method="post">

            <input type="hidden" name="action" value="insert">
            <input type="hidden" name="intialCode" value="${intialCode}">
            <label class="pad_top">Code:</label>
            <input type="text" name="productCode" 
                   value="${product.code}"><br>
            <label class="pad_top">Description:</label>
            <input type="text" name="description" 
                   value="${product.description}"><br>
            <label class="pad_top">Price:</label>
            <input type="text" name="price" 
                   value="${product.price}"><br>
        
            <input type="submit" class="floated" value="Update Product">
        </form>
        <form name="productServlet" method="post">
            <input type="hidden" name="action" value="view">
            <input type="submit" class="floated" value="View Products">   
        </form>
                   
    </body>
</html>
