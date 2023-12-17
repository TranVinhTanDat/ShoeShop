package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoryShopControl", urlPatterns = {"/categoryShop"})
public class CategoryShopControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cateID = request.getParameter("cid");
        //da lay dc category id ve roi
        DAO dao = new DAO();
        List<Product> list = dao.getProductByCID(cateID);
        //in list p day
        PrintWriter out = response.getWriter();
        out.println("<div class=\"product-container\" >"); // Mở thẻ div mới để chứa các card sản phẩm
        for (Product o : list) {
            out.println("<div class=\"col-md-4 col-xs-6\">");
            out.println("    <div class=\"product\" style=\"height: 480px;\">");
            out.println("        <div class=\"product-img\">");
            out.println("            <a href=\"detail?pid=" + o.getId() + "\" title=\"View Product\">");
            out.println("                <img src=\"" + o.getImage() + "\" alt=\"" + o.getName() + "\">");
            out.println("                <div class=\"product-label\">");
            out.println("                    <span class=\"new\">NEW</span>");
            out.println("                </div>");
            out.println("            </a>");
            out.println("        </div>");
            out.println("        <div class=\"product-body\">");
            out.println("            <p class=\"product-category\">Category</p>");
            out.println("            <h3 class=\"product-name\"><a href=\"detail?pid=" + o.getId() + "\">" + o.getName() + "</a></h3>");
            out.println("            <h4 class=\"product-price\">$" + o.getPrice() + " <del class=\"product-old-price\">$990.00</del></h4>");
            out.println("            <div class=\"product-rating\">");
            out.println("                <i class=\"fa fa-star\"></i>");
            out.println("                <i class=\"fa fa-star\"></i>");
            out.println("                <i class=\"fa fa-star\"></i>");
            out.println("                <i class=\"fa fa-star\"></i>");
            out.println("                <i class=\"fa fa-star\"></i>");
            out.println("            </div>");
            out.println("            <div class=\"product-btns\">");
            // Bỏ đi các button và icon tại đây
            out.println("            </div>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</div>");
        }

        out.println("</div>"); // Đóng thẻ div chứa các card sản phẩm
    }

//        List<Category> listC = dao.getAllCategory();
//        Product last = dao.getLast();
//        
//        
//        
//        request.setAttribute("listP", list);
//        request.setAttribute("listCC", listC);
//        request.setAttribute("p", last);
//        request.setAttribute("tag", cateID);
//        request.getRequestDispatcher("Home.jsp").forward(request, response);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}