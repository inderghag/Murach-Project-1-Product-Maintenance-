<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Maintenance</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <h1>Are you sure you want to delete this product?</h1>
        
        <label>Code:            </label><span>${product.code}</span><br>
        <label>Description:     </label><span>${product.description}</span><br>
        <label>Price:           </label><span>${product.price}</span><br>
        
        <table>
        <form action="productServlet" method="post">
            <input type="hidden" name="action" value="remove">
            <input type="hidden" name="productCode" value="${product.code}">
            <button>Yes</button>
            
        </form>
        <form action="productServlet" method="post">
            <input type="hidden" name="action" value="view">
            <button>No</button>
        </form>
        </table>
            
            
    </body>
</html>
