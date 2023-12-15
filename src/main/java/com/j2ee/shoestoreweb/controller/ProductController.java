package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Account;
import com.j2ee.shoestoreweb.entity.Category;
import com.j2ee.shoestoreweb.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = {"/managerProduct", "/add", "/edit", "/delete", "/searchProduct"})
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            request.getSession().setAttribute("page", "managerProduct");
            response.sendRedirect("login");
            return;
        }
        if (a.getIsAdmin() == 0) {
            request.getSession().setAttribute("page", "managerProduct");
            response.sendRedirect("login");
            return;
        }

        int id;
        if (a.getIsSell() == 0)
            id = 1;
        else
            id = 0;
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int indexPage = Integer.parseInt(index);

        DAO dao = new DAO();
        List<Product> list = dao.getProductBySellIDAndIndex(id, indexPage);
        List<Category> listC = dao.getAllCategory();
        int allProductBySellID = dao.countAllProductBySellID(id);
        int endPage = allProductBySellID / 5;
        if (allProductBySellID % 5 != 0) {
            endPage++;
        }

        request.setAttribute("tag", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listCC", listC);
        request.setAttribute("listP", list);

        String action = request.getServletPath();
        if ("/managerProduct".equals(action)) {


            request.getRequestDispatcher("QuanLySanPham.jsp").forward(request, response);
        } else if ("/add".equals(action)) {
            String pname = request.getParameter("name");
            String pimage = request.getParameter("image");
            String pimage2 = request.getParameter("image2");
            String pimage3 = request.getParameter("image3");
            String pimage4 = request.getParameter("image4");
            String pmodel = request.getParameter("model");
            String pcolor = request.getParameter("color");
            String pdelivery = request.getParameter("delivery");
            String pprice = request.getParameter("price");
            String ptitle = request.getParameter("title");
            String pdescription = request.getParameter("description");
            String pcategory = request.getParameter("category");

            dao.insertProduct(pname, pimage, pprice, ptitle, pdescription, pcategory, id, pmodel, pcolor, pdelivery, pimage2, pimage3, pimage4);
            request.setAttribute("mess", "Product Added!");
            request.getRequestDispatcher("managerProduct").forward(request, response);
        } else if ("/edit".equals(action)) {
            String pid = request.getParameter("id");
            String pname = request.getParameter("name");
            String pimage = request.getParameter("image");
            String pimage2 = request.getParameter("image2");
            String pimage3 = request.getParameter("image3");
            String pimage4 = request.getParameter("image4");
            String pmodel = request.getParameter("model");
            String pcolor = request.getParameter("color");
            String pdelivery = request.getParameter("delivery");
            String pprice = request.getParameter("price");
            String ptitle = request.getParameter("title");
            String pdescription = request.getParameter("description");
            String pcategory = request.getParameter("category");

            dao.editProduct(pname, pimage, pprice, ptitle, pdescription, pcategory, pmodel, pcolor, pdelivery, pimage2, pimage3, pimage4, pid);
            request.setAttribute("mess", "Edited!!!!");
            request.getRequestDispatcher("managerProduct").forward(request, response);
        } else if ("/delete".equals(action)) {
            String pid = request.getParameter("pid");
            dao.deleteCartByProductID(pid);
            dao.deleteReviewByProductID(pid);
            dao.deleteSoLuongDaBanByProductID(pid);
            dao.deleteProduct(pid);
            request.setAttribute("mess", "Deleted!");
            request.getRequestDispatcher("managerProduct").forward(request, response);
        } else if ("/searchProduct".equals(action)) {
            String name = request.getParameter("search");
            list = dao.SearchProduct(name);
            request.setAttribute("tag", 1);
            request.setAttribute("endPage", 5);
            request.setAttribute("listP", list);
            request.getRequestDispatcher("QuanLySanPham.jsp").forward(request, response);
        }
    }

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
}