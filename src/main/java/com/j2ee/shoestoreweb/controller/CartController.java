package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Account;
import com.j2ee.shoestoreweb.entity.Cart;
import com.j2ee.shoestoreweb.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CartController", urlPatterns = {"/managerCart", "/addCart", "/addAmountCart", "/subAmountCart", "/deleteCart", "/loadAllAmountCart", "/totalMoneyCart"})
public class CartController extends HttpServlet {

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
            case "/managerCart":
                processManagerCartRequest(request, response);
                break;
            case "/addCart":
                processAddCartRequest(request, response);
                break;
            case "/addAmountCart":
                processAddAmountRequest(request, response);
                break;
            case "/subAmountCart":
                processSubAmountRequest(request, response);
                break;
            case "/deleteCart":
                processDeleteCartRequest(request, response);
                break;
            case "/loadAllAmountCart":
                processLoadAmountCartRequest(request, response);
                break;
            case "/totalMoneyCart":
                processTotalMoneyCartRequest(request, response);
                break;
            default:
                break;
        }
    }

    protected void processManagerCartRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        int accountID = a.getId();
        DAO dao = new DAO();
        List<Cart> list = dao.getCartByAccountID(accountID);
        List<Product> list2 = dao.getAllProduct();

        request.setAttribute("listCart", list);
        request.setAttribute("listProduct", list2);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
        double totalMoney = 0;
        for (Cart o : list) {
            for (Product p : list2) {
                if (o.getProductID() == p.getId()) {
                    totalMoney = totalMoney + (p.getPrice() * o.getAmount());
                }
            }
        }

        double totalMoneyVAT = totalMoney * 0.05 + totalMoney;

        PrintWriter out = response.getWriter();
        out.println(" <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Tổng tiền hàng</strong><strong>" + totalMoney + "</strong></li>\r\n"
                + "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Phí vận chuyển</strong><strong>Free ship</strong></li>\r\n"
                + "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">VAT</strong><strong>10 %</strong></li>\r\n"
                + "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Tổng thanh toán</strong>\r\n"
                + "                                            <h5 class=\"font-weight-bold\">" + totalMoneyVAT + "</h5>\r\n"
                + "                                        </li>");
    }

    protected void processAddCartRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int productID = Integer.parseInt(request.getParameter("pid"));
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        int accountID = a.getId();
        int amount = Integer.parseInt(request.getParameter("quantity"));
        String size = request.getParameter("size");

        DAO dao = new DAO();
        Cart cartExisted = dao.checkCartExist(accountID, productID);
        int amountExisted;
        String sizeExisted;
        if (cartExisted != null) {
            amountExisted = cartExisted.getAmount();
            dao.editAmountAndSizeCart(accountID, productID, (amountExisted + amount), size);
//            request.setAttribute("mess", "Da tang so luong san pham!");
            request.getRequestDispatcher("managerCart").forward(request, response);
        } else {
            dao.insertCart(accountID, productID, amount, size);
//            request.setAttribute("mess", "Da them san pham vao gio hang!");
            request.getRequestDispatcher("managerCart").forward(request, response);
        }
    }

    protected void processAddAmountRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        int accountID = a.getId();
        int productID = Integer.parseInt(request.getParameter("productID"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        amount += 1;
        DAO dao = new DAO();
        dao.editAmountCart(accountID, productID, amount);
        request.setAttribute("mess", "Đã tăng số lượng!");
        request.getRequestDispatcher("managerCart").forward(request, response);
    }

    protected void processSubAmountRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        int accountID = a.getId();
        int productID = Integer.parseInt(request.getParameter("productID"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        amount -= 1;
        DAO dao = new DAO();
        dao.editAmountCart(accountID, productID, amount);
        request.setAttribute("mess", "Đã giảm số lượng!");
        request.getRequestDispatcher("managerCart").forward(request, response);
    }

    protected void processDeleteCartRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int productID = Integer.parseInt(request.getParameter("productID"));
        DAO dao = new DAO();
        dao.deleteCart(productID);
        request.setAttribute("mess", "Đã xóa sản phẩm khỏi giỏ hàng !");
        request.getRequestDispatcher("managerCart").forward(request, response);
    }

    protected void processLoadAmountCartRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int totalAmountCart = 0;
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            PrintWriter out = response.getWriter();
            out.println(totalAmountCart);
            return;
        }
        int accountID = a.getId();
        DAO dao = new DAO();
        List<Cart> list = dao.getCartByAccountID(accountID);
        totalAmountCart = list.size();


        //in list p day
        PrintWriter out = response.getWriter();
        out.println(totalAmountCart);

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
    }

    protected void processTotalMoneyCartRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int accountID = a.getId();
        DAO dao = new DAO();
        List<Cart> list = dao.getCartByAccountID(accountID);
        List<Product> list2 = dao.getAllProduct();

        double totalMoney = 0;
        for (Cart o : list) {
            for (Product p : list2) {
                if (o.getProductID() == p.getId()) {
                    totalMoney = totalMoney + p.getPrice() * o.getAmount();
                }
            }
        }

        double totalMoneyVAT = totalMoney * 0.05 + totalMoney;
        totalMoneyVAT = Math.round(totalMoneyVAT);

        PrintWriter out = response.getWriter();
        out.println(" <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Tổng tiền hàng</strong><strong>" + totalMoney + "</strong></li>\r\n"
                + "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Phí vận chuyển</strong><strong>Free ship</strong></li>\r\n"
                + "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">VAT</strong><strong>5 %</strong></li>\r\n"
                + "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Tổng thanh toán</strong>\r\n"
                + "                                            <h5 class=\"font-weight-bold\">" + totalMoneyVAT + "</h5>\r\n"
                + "                                        </li>");
    }
}