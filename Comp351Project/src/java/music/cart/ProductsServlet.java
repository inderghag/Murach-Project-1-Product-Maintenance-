package music.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import music.business.Product;
import music.data.ProductIO;

public class ProductsServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
         ServletContext sc = getServletContext();
         
         String url = "/index.jsp";
         
         String path = sc.getRealPath("/WEB-INF/products.txt");
         
         //Intialize ProductIO array of products
         ProductIO.init(path);
         List<Product> products = ProductIO.selectProducts();
         
         String action = request.getParameter("action");
         
         //Default action is view
         if (action == null) {
             action = "view";  
         }
         
         //Removes product from products list
         if(action.equals("delete")){

             String productCode = request.getParameter("productCode");
             
             Product prod = new Product();
             
             prod = ProductIO.selectProduct(productCode);
             
             System.out.println("Product Code: " + prod.getCode());
             request.setAttribute("product", prod);
             
             url = "/confirmDelete.jsp";
         }
         //Views list of all current products in list
         else if(action.equals("view")){
             
             request.setAttribute("products", products);
             url="/products.jsp";
         }
         //Send product data of the product user wishes to edit to product.jsp
         else if(action.equals("edit")){
             
             Product product = new Product();
             
             String productCode = request.getParameter("productCode");
             String description = request.getParameter("description");
             double price = Double.valueOf(request.getParameter("price"));
             
             //Used to identify if product exists when insert function is called
             String intialCode = request.getParameter("productCode");
             
             product.setCode(productCode);
             product.setDescription(description);
             product.setPrice(price);
             
             request.setAttribute("product", product);
             request.setAttribute("intialCode", intialCode);
             
             url="/product.jsp";
         }
         else if(action.equals("add")){
             
             url="/product.jsp";
         }
         else if(action.equals("insert")){
             
             Product product = new Product();
             
             String message="";
             
             String productCode = request.getParameter("productCode");
             String description = request.getParameter("description");
             
             String intialCode = request.getParameter("intialCode");
             //Checks to see if want was entered is a valid number
             try{
                Double.valueOf(request.getParameter("price"));
             }catch(NumberFormatException e){
                 message ="Please enter valid number for price. ";
             }
             
             //If product or description field is empty will not run
             if(productCode == null || description == null 
                || productCode.isEmpty() || description.isEmpty()){
                 message = message + "Please enter product code and/or description.";
                 url = "/product.jsp";
             } 
             //If price field didn't have a valid number, it will return to product field with message
             else if(message != ""){
                 url = "/product.jsp";
             }
             //If product already exists, it is updated
             else if(ProductIO.exists(intialCode)){
                 double price = Double.valueOf(request.getParameter("price"));
                 
                 product.setCode(productCode);
                 product.setDescription(description);
                 product.setPrice(price);
                 
                 ProductIO.deleteProduct(ProductIO.selectProduct(intialCode));
                 
                 ProductIO.insertProduct(product);
                 
                 url = "/index.jsp";
             }
             else{
                 double price = Double.valueOf(request.getParameter("price"));
                 
                 product.setCode(productCode);
                 product.setDescription(description);
                 product.setPrice(price);

                 ProductIO.insertProduct(product);
 
                 url = "/index.jsp";
             }
             
             request.setAttribute("message", message);
         }
         //Removes product from list
         else if(action.equals("remove")){
             
             Product product = new Product();
             
             String productCode = request.getParameter("productCode");
             
             product = ProductIO.selectProduct(productCode);
             
             ProductIO.deleteProduct(product);
             url="/index.jsp";
         }
         else{
             url="/index.jsp";
         }
         sc.getRequestDispatcher(url).forward(request, response);
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("In GET");
        doPost(request, response);
    }

    
}
