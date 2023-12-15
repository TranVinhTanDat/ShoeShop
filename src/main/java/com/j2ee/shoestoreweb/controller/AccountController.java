package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "AccountController", urlPatterns = {"/managerAccount", "/addAccount",
        "/deleteAccount", "/xuatExcelAccountControl", "/searchAccount", "/filterAccount", "/editAcc", "/editAccount"})
public class AccountController extends HttpServlet {

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
            case "/managerAccount":
                processViewAccount(request, response);
                break;
            case "/addAccount":
                processAddAccount(request, response);
                break;
            case "/deleteAccount":
                processDeleteAccount(request, response);
                break;
            case "/xuatExcelAccountControl":
                processExportExcelRequest(request, response);
                break;
            case "/searchAccount":
                processExportSearchAccountRequest(request, response);
                break;
            case "/filterAccount":
                processViewAccountFilter(request, response);
                break;
            case "/editAcc":
                processGetAccount(request, response);
                break;
            case "/editAccount":
                processEditAccount(request, response);
                break;
            default:
                break;
        }
    }

    protected void processViewAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            request.getSession().setAttribute("page", "managerAccount");
            response.sendRedirect("login");
            return;
        }
        if (a.getIsAdmin() == 0) {
            request.getSession().setAttribute("page", "managerAccount");
            response.sendRedirect("login");
            return;
        }
        List<Account> list = dao.getAllAccount();
        request.setAttribute("listAllAccount", list);
        request.getRequestDispatcher("QuanLyTaiKhoan.jsp").forward(request, response);
    }

    protected void processGetAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        String id = request.getParameter("id");
        Account a = dao.getAcc(id);
        List<Account> list = dao.getAllAccount();
        request.setAttribute("listAllAccount", list);
        request.setAttribute("accEdit", a);
        request.getRequestDispatcher("QuanLyTaiKhoan.jsp").forward(request, response);
    }

    protected void processEditAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String role = request.getParameter("add-row_length");
        Account a = dao.getAcc(id);

        a.setUser(name);
        a.setPass(pass);
        if (Integer.parseInt(role) == 0) {
            a.setIsSell(0);
            a.setIsAdmin(1);
        } else {
            a.setIsSell(1);
            a.setIsAdmin(0);
        }

        dao.UpdateAccount(a);
        List<Account> list = dao.getAllAccount();

        request.setAttribute("listAllAccount", list);
        request.getRequestDispatcher("QuanLyTaiKhoan.jsp").forward(request, response);
    }


    protected void processViewAccountFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("add-row_length");
        int length = Integer.parseInt(name);
        List<Account> result = new ArrayList<>();
        DAO dao = new DAO();


        List<Account> list = dao.getAllAccount();
        for (Account value : list) {
            result.add(value);
            length--;
            if (length <= 0)
                break;
        }
        request.setAttribute("listAllAccount", result);
        request.getRequestDispatcher("QuanLyTaiKhoan.jsp").forward(request, response);
    }

    protected void processExportSearchAccountRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("search");
        DAO dao = new DAO();

        List<Account> list = dao.searchAccount(name);

        request.setAttribute("listAllAccount", list);
        request.getRequestDispatcher("QuanLyTaiKhoan.jsp").forward(request, response);
    }


    protected void processAddAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String isSell = request.getParameter("isSell");
        String isAdmin = request.getParameter("isAdmin");
        String email = request.getParameter("email");

        DAO dao = new DAO();
        dao.insertAccount(user, pass, isSell, isAdmin, email);

        request.setAttribute("mess", "Account Added!");
        request.getRequestDispatcher("managerAccount").forward(request, response);
    }

    protected void processDeleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        System.out.println("id: " + id);
        DAO dao = new DAO();

        dao.deleteCartByAccountID(Integer.parseInt(id));
        dao.deleteProductBySellID(id);
        dao.deleteReviewByAccountID(id);
        dao.deleteInvoiceByAccountId(id);
        dao.deleteTongChiTieuBanHangByUserID(id);
        dao.deleteAccount(id);

        request.setAttribute("mess", "Account Deleted!");
        request.getRequestDispatcher("managerAccount").forward(request, response);
    }

    protected void processExportExcelRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO dao = new DAO();
        List<Account> list = dao.getAllAccount();

        int maximum = 2147483647;
        int minimum = 1;

        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;


        FileOutputStream file = new FileOutputStream("C:\\ExcelWebBanGiay\\" + "tai-khoan-" + Integer.toString(randomNum) + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("1");
        XSSFRow row;
        XSSFCell cell0;
        XSSFCell cell1;
        XSSFCell cell2;
        XSSFCell cell3;
        XSSFCell cell4;

        row = workSheet.createRow(0);
        cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        cell1 = row.createCell(1);
        cell1.setCellValue("Username");
        cell2 = row.createCell(2);
        cell2.setCellValue("Là người bán hàng");
        cell3 = row.createCell(3);
        cell3.setCellValue("Là Admin");
        cell4 = row.createCell(4);
        cell4.setCellValue("Email");

        int i = 0;

        for (Account acc : list) {
            i = i + 1;
            row = workSheet.createRow(i);
            cell0 = row.createCell(0);
            cell0.setCellValue(acc.getId());
            cell1 = row.createCell(1);
            cell1.setCellValue(acc.getUser());
            cell2 = row.createCell(2);
            cell2.setCellValue(acc.getIsSell());
            cell3 = row.createCell(3);
            cell3.setCellValue(acc.getIsAdmin());
            cell4 = row.createCell(4);
            cell4.setCellValue(acc.getEmail());
        }

        workbook.write(file);
        workbook.close();
        file.close();

        request.setAttribute("mess", "Đã xuất file Excel thành công!");
        request.getRequestDispatcher("managerAccount").forward(request, response);
    }
}