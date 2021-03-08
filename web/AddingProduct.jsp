<%-- 
    Document   : AddingProduct
    Created on : 07 Mar 2021, 2:23:19 PM
    Author     : Rt Netwa
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.baked.products.models.Category"%>
<%@page import="com.baked.products.models.Product"%>
<%@page import="com.baked.products.models.Ingredient"%>

    <!DOCTYPE html>
<html> 
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
	<% 
		ArrayList<Category> categories = (ArrayList<Category>)session.getAttribute("Categories");
		ArrayList<Ingredient> ingredients= (ArrayList<Ingredient>)session.getAttribute("ingredients");
	%>
    	<h1>Product Registration Form</h1>
    	<div class="container">
        	<form action ="ProductServlet" method="post">
            	<div>
                	<label for="produtName">Product name:</label> 
                	<input id="productName" type="text" name="productName" placeholder="i.e cookie" value="" required="required"/>
                	<label name="error hidden" id="invalidName">Invalid product name:</label>
                	<label for="price">Price:</label> 
                	<input id="price" type="number" name="price" placeholder="i.e 200" required>
            	</div>
            	<div>
                	<br>
                	<label for="Description">Description: 
                    	<textarea name="description" style="width:300px; height:50px;">
                    	</textarea>
                	</label> 
            		</div>
            		<div>
                		<br>
                		<label for="itemWarning">Item warning :
                    	<textarea name = "itemwarning" style="width:300px; height:50px;">
                    	</textarea>
                	</label>
            	</div>
            	<div>
                	<br>
                	<label for="picture">Product picture:</label>
                	<input id="picture" type="file" name="picture">
            	</div>
            	<br>
            	<button>Add new product</button>
        	</form>
   		</div>
    	<div>
        	<br>
        	<h4>Category selection:</h4>
        	<select  name="categories">
            	<%
            		for(int i=0; i<categories.size(); i++){
            			out.println("<option value="+categories.get(i).getName()+"</option>");
            		}
            	%>
       	 	</select>
    	</div>
    	<div class="row">
        	<br>
        	<button onclick="document.location='recipe.html'">Add Recipe</button>
    	</div>
	</body>
</html>
