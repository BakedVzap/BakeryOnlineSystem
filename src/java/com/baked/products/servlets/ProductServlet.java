package com.baked.products.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import com.baked.products.models.*;
import com.baked.products.service.ProductsServiceImp;
import com.baked.products.service.ProductsServiceInterface;
import com.baked.rest.ProductRestClient;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rt Netwa
 */
public class ProductServlet extends HttpServlet {
    
    private static ArrayList<Category> categories;
    private static ArrayList<Recipe> allRecipe;
    private static ArrayList<Ingredient> allIng;
    private static ProductRestClient restEasy;
    //************************************************************************************************
    public ProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
            ServletContext sc = getServletContext();
            HttpSession session =request.getSession();
            String prodName = (String)request.getParameter("productName");
            
            restEasy =new ProductRestClient();
            
            Product prod=restEasy.getProduct(prodName);
            System.out.println(prod.toString());
            
//            session.setAttribute("viewProduct", prod);
//            RequestDispatcher rd=sc.getRequestDispatcher("/productView.jsp");
//            rd.include(request, response);
            
	}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		String name = (String)request.getAttribute("productName");
		Double price = (Double)request.getAttribute("price");
		String descript = (String)request.getAttribute("description");
		String itemWarning = (String)request.getAttribute("itemWarning");
		String nutritionalInformation= (String)request.getAttribute("nutrition");
		String picture = (String)request.getAttribute("picture");
		categories = (ArrayList<Category>)request.getAttribute("categories");
		allRecipe = (ArrayList<Recipe>)request.getAttribute("recipes");
		
		Product prod= new Product(name, descript, itemWarning, nutritionalInformation, picture, price, categories, allRecipe);
		System.out.println(prod.toString());
		restEasy = new ProductRestClient();
                restEasy.addProduct(prod);
		RequestDispatcher rd= sc.getRequestDispatcher("ProductAdded.jsp");
		rd.include(request, response);
	} 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void main(String [] args) {
        Product prodct=restEasy.getProduct("BlackForest");
        System.out.println(prodct.toString());
    }
}
