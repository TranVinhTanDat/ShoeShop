package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Account;
import com.j2ee.shoestoreweb.entity.Invoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "HoaDonControl", urlPatterns = {"/hoaDon", "/searchhd", "/filter"})
public class HoaDonControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            request.getSession().setAttribute("page", "hoaDon");
            response.sendRedirect("login");
            return;
        }
        if (a.getIsAdmin() == 0) {
            request.getSession().setAttribute("page", "hoaDon");
            response.sendRedirect("login");
            return;
        }
        String action = request.getServletPath();
        if ("/hoaDon".equals(action)) {
            DAO dao = new DAO();

            double sumAllInvoice = dao.sumAllInvoice();
            List<Invoice> listAllInvoice = dao.getAllInvoice();
            List<Account> listAllAccount = dao.getAllAccount();

            request.setAttribute("listAllInvoice", listAllInvoice);
            request.setAttribute("listAllAccount", listAllAccount);
            request.setAttribute("sumAllInvoice", sumAllInvoice);
            request.getRequestDispatcher("HoaDon.jsp").forward(request, response);
        } else if ("/searchhd".equals(action)) {

            String name = request.getParameter("search");
            DAO dao = new DAO();

            double sumAllInvoice = dao.sumAllInvoice();
            List<Invoice> listAllInvoice = dao.searchInvoice(name);
            List<Account> listAllAccount = dao.getAllAccount();

            request.setAttribute("listAllInvoice", listAllInvoice);
            request.setAttribute("listAllAccount", listAllAccount);
            request.setAttribute("sumAllInvoice", sumAllInvoice);
            request.getRequestDispatcher("HoaDon.jsp").forward(request, response);
        } else if ("/filter".equals(action)) {

            DAO dao = new DAO();
            String name = request.getParameter("add-row_length");
            int length = Integer.parseInt(name);
            double sumAllInvoice = dao.sumAllInvoice();
            List<Invoice> listAllInvoice = dao.getAllInvoice();
            List<Invoice> result = new ArrayList<>();
            for (Invoice value : listAllInvoice) {
                result.add(value);
                length--;
                if (length <= 0)
                    break;
            }

            List<Account> listAllAccount = dao.getAllAccount();

            request.setAttribute("listAllInvoice", result);
            request.setAttribute("listAllAccount", listAllAccount);
            request.setAttribute("sumAllInvoice", sumAllInvoice);
            request.getRequestDispatcher("HoaDon.jsp").forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}