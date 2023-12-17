package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Category;
import com.j2ee.shoestoreweb.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/home", "/load", "/loadAdidas", "/loadNike"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getServletPath();
        switch (pathInfo) {
            case "/home":
                processHomeRequest(request, response);
                break;
            case "/load":
                processLoadMoreRequest(request, response);
                break;
            case "/loadAdidas":
                processLoadMoreAdidasRequest(request, response);
                break;
            case "/loadNike":
                processLoadMoreNikeRequest(request, response);
                break;

            default:
                break;
        }
    }

    protected void processHomeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from dao
        DAO dao = new DAO();
//        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        List<Product> list = dao.getTop3();
        List<Product> list8Last = dao.get8Last();
        List<Product> list4NikeLast = dao.get4NikeLast();
        List<Product> list4AdidasLast = dao.get4AdidasLast();


        Product last = dao.getLast();

        //b2: set data to jsp
        request.setAttribute("listP", list);
        request.setAttribute("listCC", listC);
        request.setAttribute("list8Last", list8Last);
        request.setAttribute("list4NikeLast", list4NikeLast);
        request.setAttribute("list4AdidasLast", list4AdidasLast);
        request.setAttribute("p", last);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
        //404 -> url
        //500 -> jsp properties
    }

    protected void processLoadMoreRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //tam thoi load ra 3 san pham truoc
        String amount = request.getParameter("exits");
        int iamount = Integer.parseInt(amount);
        DAO dao = new DAO();
        List<Product> list = dao.getNext3Product(iamount);
        PrintWriter out = response.getWriter();

        for (Product o : list) {
            out.println("<div class=\"product col-12 col-md-6 col-lg-4\">\n"
                    + "                                <div class=\"card\">\n"
                    + "                                    <img class=\"card-img-top\" src=\"" + o.getImage() + "\" alt=\"Card image cap\">\n"
                    + "                                    <div class=\"card-body\">\n"
                    + "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?pid=" + o.getId() + "\" title=\"View Product\">" + o.getName() + "</a></h4>\n"
                    + "                                        <p class=\"card-text show_txt\">" + o.getTitle() + "</p>\n"
                    + "                                        <div class=\"row\">\n"
                    + "                                            <div class=\"col\">\n"
                    + "                                                <p class=\"btn btn-danger btn-block\">" + o.getPrice() + " $</p>\n"
                    + "                                            </div>\n"
                    + "                                            <div class=\"col\">\n"
                    + "                                                <a href=\"addCart?pid=" + o.getId() + "\" class=\"btn btn-success btn-block\">Add to cart</a>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>");
        }
    }

    protected void processLoadMoreAdidasRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String amount = request.getParameter("exitsAdidas");
        int iamount = Integer.parseInt(amount);
        DAO dao = new DAO();
        List<Product> list = dao.getNext4AdidasProduct(iamount);
        PrintWriter out = response.getWriter();

        out.println("<div class=\"product-container row\" style=\"margin-left: 38px;\">"); // Mở thẻ div mới để chứa các card sản phẩm và thiết lập lưới
        for (Product o : list) {
            out.println("<div class=\"col-md-4 col-xs-6\">");
            out.println("    <div class=\"product productAdidas\" style=\"height: 500px;\">");
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
            out.println("        </div>");
            out.println("    </div>");
            out.println("</div>");
        }

        out.println("</div>"); // Đóng thẻ div chứa các card sản phẩm

    }

    protected void processLoadMoreNikeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String amount = request.getParameter("exitsNike");
        int iamount = Integer.parseInt(amount);
        DAO dao = new DAO();
        List<Product> list = dao.getNext4NikeProduct(iamount);
        PrintWriter out = response.getWriter();
        out.println("<div class=\"product-container row\" style=\"margin-left: 38px;\">"); // Mở thẻ div mới để chứa các card sản phẩm và thiết lập lưới

        for (Product o : list) {
            out.println("<div class=\"col-md-4 col-xs-6\">");
            out.println("    <div class=\"product productAdidas\" style=\"height: 500px;\">");
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
            out.println("        </div>");
            out.println("    </div>");
            out.println("</div>");
        }


        out.println("</div>"); // Đóng thẻ div chứa các card sản phẩm


    }
}