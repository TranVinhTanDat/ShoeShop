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

        for (Product o : list) {
            out.println(" <div class=\"productAdidas col-12 col-md-6 col-lg-3\">\r\n"
                    + "                                <div class=\"card\">\r\n"
                    + "                                <div class=\"view zoom z-depth-2 rounded\">\r\n"
                    + "                                    <img class=\"img-fluid w-100\" src=\"" + o.getImage() + "\" alt=\"Card image cap\">\r\n"
                    + "                                    </div>\r\n"
                    + "                                    <div class=\"card-body\">\r\n"
                    + "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?pid=" + o.getId() + "\" title=\"View Product\">" + o.getName() + "</a></h4>\r\n"
                    + "                                        <p class=\"card-text show_txt\">" + o.getTitle() + "</p>\r\n"
                    + "                                        <div class=\"row\">\r\n"
                    + "                                            <div class=\"col\">\r\n"
                    + "                                                <p class=\"btn btn-success btn-block\">" + o.getPrice() + " $</p>\r\n"
                    + "                                            </div>\r\n"
                    + "                                           \r\n"
                    + "                                        </div>\r\n"
                    + "                                    </div>\r\n"
                    + "                                </div>\r\n"
                    + "                            </div>");
        }
    }

    protected void processLoadMoreNikeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String amount = request.getParameter("exitsNike");
        int iamount = Integer.parseInt(amount);
        DAO dao = new DAO();
        List<Product> list = dao.getNext4NikeProduct(iamount);
        PrintWriter out = response.getWriter();

        for (Product o : list) {
            out.println("<div class=\"productNike col-12 col-md-6 col-lg-3\">\r\n"
                    + "                                <div class=\"card\">\r\n"
                    + "                                 <div class=\"view zoom z-depth-2 rounded\">\r\n"
                    + "                                    <img class=\"img-fluid w-100\" src=\"" + o.getImage() + "\" alt=\"Card image cap\">\r\n"
                    + "                                    </div>\r\n"
                    + "                                    <div class=\"card-body\">\r\n"
                    + "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?pid=" + o.getId() + "\" title=\"View Product\">" + o.getName() + "</a></h4>\r\n"
                    + "                                        <p class=\"card-text show_txt\">" + o.getTitle() + "</p>\r\n"
                    + "                                        <div class=\"row\">\r\n"
                    + "                                            <div class=\"col\">\r\n"
                    + "                                                <p class=\"btn btn-success btn-block\">" + o.getPrice() + " $</p>\r\n"
                    + "                                            </div>\r\n"
                    + "                                            \r\n"
                    + "                                        </div>\r\n"
                    + "                                    </div>\r\n"
                    + "                                </div>\r\n"
                    + "                            </div>");
        }
    }
}