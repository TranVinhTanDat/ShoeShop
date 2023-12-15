package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;

import com.j2ee.shoestoreweb.entity.Account;
import com.j2ee.shoestoreweb.entity.Product;
import com.j2ee.shoestoreweb.entity.SoLuongDaBan;
import com.j2ee.shoestoreweb.entity.TongChiTieuBanHang;
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
import java.util.List;
import java.util.Random;

@WebServlet(name = "StatisticController", urlPatterns = {"/doanhThuTheoThang", "/doanhThuTheoThu", "/top5khachhang", "/top5nhanvien", "/top10", "/xuatExcelTop5CustomerControl", "/xuatExcelTop5EmployeeControl", "/xuatExcelTop10ProductControl"})
public class StatisticController extends HttpServlet {

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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getServletPath();
        switch (pathInfo) {
            case "/doanhThuTheoThang":
                processRevenueMonthRequest(request, response);
                break;
            case "/doanhThuTheoThu":
                processRevenueInWeekRequest(request, response);
                break;
            case "/top5khachhang":
                processTop5CustomerRequest(request, response);
                break;
            case "/top5nhanvien":
                processTop5StaffRequest(request, response);
                break;
            case "/top10":
                processTop10ProductRequest(request, response);
                break;
            case "/xuatExcelTop5CustomerControl":
                processExportExcelTop5CustomerRequest(request, response);
                break;
            case "/xuatExcelTop5EmployeeControl":
                processExportExcelTop5StaffRequest(request, response);
                break;
            case "/xuatExcelTop10ProductControl":
                processExportExcelTop10ProductRequest(request, response);
                break;
            default:
                break;
        }
    }

    protected void processRevenueMonthRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            request.getSession().setAttribute("page", "doanhThuTheoThang");
            response.sendRedirect("login");
            return;
        }
        if (a.getIsAdmin() == 0) {
            request.getSession().setAttribute("page", "doanhThuTheoThang");
            response.sendRedirect("login");
            return;
        }
        DAO dao = new DAO();
        double totalMoneyMonth1 = dao.totalMoneyMonth(1);
        double totalMoneyMonth2 = dao.totalMoneyMonth(2);
        double totalMoneyMonth3 = dao.totalMoneyMonth(3);
        double totalMoneyMonth4 = dao.totalMoneyMonth(4);
        double totalMoneyMonth5 = dao.totalMoneyMonth(5);
        double totalMoneyMonth6 = dao.totalMoneyMonth(6);
        double totalMoneyMonth7 = dao.totalMoneyMonth(7);
        double totalMoneyMonth8 = dao.totalMoneyMonth(8);
        double totalMoneyMonth9 = dao.totalMoneyMonth(9);
        double totalMoneyMonth10 = dao.totalMoneyMonth(10);
        double totalMoneyMonth11 = dao.totalMoneyMonth(11);
        double totalMoneyMonth12 = dao.totalMoneyMonth(12);

        request.setAttribute("totalMoneyMonth1", totalMoneyMonth1);
        request.setAttribute("totalMoneyMonth2", totalMoneyMonth2);
        request.setAttribute("totalMoneyMonth3", totalMoneyMonth3);
        request.setAttribute("totalMoneyMonth4", totalMoneyMonth4);
        request.setAttribute("totalMoneyMonth5", totalMoneyMonth5);
        request.setAttribute("totalMoneyMonth6", totalMoneyMonth6);
        request.setAttribute("totalMoneyMonth7", totalMoneyMonth7);
        request.setAttribute("totalMoneyMonth8", totalMoneyMonth8);
        request.setAttribute("totalMoneyMonth9", totalMoneyMonth9);
        request.setAttribute("totalMoneyMonth10", totalMoneyMonth10);
        request.setAttribute("totalMoneyMonth11", totalMoneyMonth11);
        request.setAttribute("totalMoneyMonth12", totalMoneyMonth12);

        request.getRequestDispatcher("DoanhThuTheoThang.jsp").forward(request, response);
    }

    protected void processRevenueInWeekRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            request.getSession().setAttribute("page", "doanhThuTheoThu");
            response.sendRedirect("login");
            return;
        }
        if (a.getIsAdmin() == 0) {
            request.getSession().setAttribute("page", "doanhThuTheoThu");
            response.sendRedirect("login");
            return;
        }
        DAO dao = new DAO();
        double totalMoney1 = dao.totalMoneyDay(1);
        double totalMoney2 = dao.totalMoneyDay(2);
        double totalMoney3 = dao.totalMoneyDay(3);
        double totalMoney4 = dao.totalMoneyDay(4);
        double totalMoney5 = dao.totalMoneyDay(5);
        double totalMoney6 = dao.totalMoneyDay(6);
        double totalMoney7 = dao.totalMoneyDay(7);

        request.setAttribute("totalMoney1", totalMoney1);
        request.setAttribute("totalMoney2", totalMoney2);
        request.setAttribute("totalMoney3", totalMoney3);
        request.setAttribute("totalMoney4", totalMoney4);
        request.setAttribute("totalMoney5", totalMoney5);
        request.setAttribute("totalMoney6", totalMoney6);
        request.setAttribute("totalMoney7", totalMoney7);

        request.getRequestDispatcher("DoanhThuTheoThu.jsp").forward(request, response);
    }

    protected void processTop5CustomerRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            request.getSession().setAttribute("page", "top10");
            response.sendRedirect("login");
            return;
        }
        if (a.getIsAdmin() == 0) {
            request.getSession().setAttribute("page", "top10");
            response.sendRedirect("login");
            return;
        }

        DAO dao = new DAO();

        List<Account> listAllAccount = dao.getAllAccount();
        List<TongChiTieuBanHang> listTop5KhachHang = dao.getTop5KhachHang();

        request.setAttribute("listAllAccount", listAllAccount);
        request.setAttribute("listTop5KhachHang", listTop5KhachHang);
//        request.getRequestDispatcher("ManagerAccount.jsp").forward(request, response);
        request.getRequestDispatcher("Top10SanPhamBanChay.jsp").forward(request, response);
    }

    protected void processTop5StaffRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO dao = new DAO();

        List<Account> listAllAccount = dao.getAllAccount();
        List<TongChiTieuBanHang> listTop5NhanVien = dao.getTop5NhanVien();

        request.setAttribute("listAllAccount", listAllAccount);
        request.setAttribute("listTop5NhanVien", listTop5NhanVien);

        request.getRequestDispatcher("Top10SanPhamBanChay.jsp").forward(request, response);
    }

    protected void processTop10ProductRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            request.getSession().setAttribute("page", "top10");
            response.sendRedirect("login");
            return;
        }
        if (a.getIsAdmin() == 0) {
            request.getSession().setAttribute("page", "top10");
            response.sendRedirect("login");
            return;
        }

        DAO dao = new DAO();
        List<Product> listAllProduct = dao.getAllProduct();
        List<SoLuongDaBan> listTop10Product = dao.getTop10SanPhamBanChay();


        request.setAttribute("listAllProduct", listAllProduct);
        request.setAttribute("listTop10Product", listTop10Product);

        request.getRequestDispatcher("Top10SanPhamBanChay.jsp").forward(request, response);
    }

    protected void processExportExcelTop5CustomerRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO dao = new DAO();
        List<Account> listAllAccount = dao.getAllAccount();
        List<TongChiTieuBanHang> listTop5KhachHang = dao.getTop5KhachHang();

        int maximum = 2147483647;
        int minimum = 1;

        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;


        FileOutputStream file = new FileOutputStream("C:\\ExcelWebBanGiay\\" + "top-5-khach-hang-" + Integer.toString(randomNum) + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("1");
        XSSFRow row;
        XSSFCell cell0;
        XSSFCell cell1;
        XSSFCell cell2;
        XSSFCell cell3;

        row = workSheet.createRow(0);
        cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        cell1 = row.createCell(1);
        cell1.setCellValue("Username");
        cell2 = row.createCell(2);
        cell2.setCellValue("Email");
        cell3 = row.createCell(3);
        cell3.setCellValue("Tổng chi tiêu");


        int i = 0;

        for (TongChiTieuBanHang top5 : listTop5KhachHang) {
            for (Account acc : listAllAccount) {
                if (top5.getUserID() == acc.getId()) {
                    i = i + 1;
                    row = workSheet.createRow(i);
                    cell0 = row.createCell(0);
                    cell0.setCellValue(acc.getId());
                    cell1 = row.createCell(1);
                    cell1.setCellValue(acc.getUser());
                    cell2 = row.createCell(2);
                    cell2.setCellValue(acc.getEmail());
                    cell3 = row.createCell(3);
                    cell3.setCellValue(top5.getTongChiTieu());
                }
            }
        }

        workbook.write(file);
        workbook.close();
        file.close();

        request.setAttribute("mess", "Đã xuất file Excel thành công!");
        request.getRequestDispatcher("top5khachhang").forward(request, response);
    }

    protected void processExportExcelTop5StaffRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO dao = new DAO();
        List<Account> listAllAccount = dao.getAllAccount();
        List<TongChiTieuBanHang> listTop5NhanVien = dao.getTop5NhanVien();

        int maximum = 2147483647;
        int minimum = 1;

        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;


        FileOutputStream file = new FileOutputStream("C:\\ExcelWebBanGiay\\" + "top-5-nhan-vien-" + Integer.toString(randomNum) + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("1");
        XSSFRow row;
        XSSFCell cell0;
        XSSFCell cell1;
        XSSFCell cell2;
        XSSFCell cell3;

        row = workSheet.createRow(0);
        cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        cell1 = row.createCell(1);
        cell1.setCellValue("Username");
        cell2 = row.createCell(2);
        cell2.setCellValue("Email");
        cell3 = row.createCell(3);
        cell3.setCellValue("Tổng bán hàng");


        int i = 0;

        for (TongChiTieuBanHang top5 : listTop5NhanVien) {
            for (Account acc : listAllAccount) {
                if (top5.getUserID() == acc.getId()) {
                    i = i + 1;
                    row = workSheet.createRow(i);
                    cell0 = row.createCell(0);
                    cell0.setCellValue(acc.getId());
                    cell1 = row.createCell(1);
                    cell1.setCellValue(acc.getUser());
                    cell2 = row.createCell(2);
                    cell2.setCellValue(acc.getEmail());
                    cell3 = row.createCell(3);
                    cell3.setCellValue(top5.getTongBanHang());
                }
            }
        }

        workbook.write(file);
        workbook.close();
        file.close();

        request.setAttribute("mess", "Đã xuất file Excel thành công!");
        request.getRequestDispatcher("top5nhanvien").forward(request, response);
    }

    protected void processExportExcelTop10ProductRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO dao = new DAO();
        List<Product> listAllProduct = dao.getAllProduct();
        List<SoLuongDaBan> listTop10Product = dao.getTop10SanPhamBanChay();


        int maximum = 2147483647;
        int minimum = 1;

        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;


        FileOutputStream file = new FileOutputStream("C:\\ExcelWebBanGiay\\" + "top-10-san-pham-ban-chay-" + Integer.toString(randomNum) + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("1");
        XSSFRow row;
        XSSFCell cell0;
        XSSFCell cell1;
        XSSFCell cell2;
        XSSFCell cell3;
        XSSFCell cell4;
        XSSFCell cell5;
        XSSFCell cell6;
        XSSFCell cell7;
        XSSFCell cell8;
        XSSFCell cell9;
        XSSFCell cell10;
        XSSFCell cell11;

        row = workSheet.createRow(0);
        cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        cell1 = row.createCell(1);
        cell1.setCellValue("Name");
        cell2 = row.createCell(2);
        cell2.setCellValue("Image");
        cell3 = row.createCell(3);
        cell3.setCellValue("Price");
        cell4 = row.createCell(4);
        cell4.setCellValue("Title");
        cell5 = row.createCell(5);
        cell5.setCellValue("Description");
        cell5 = row.createCell(6);
        cell5.setCellValue("Model");
        cell5 = row.createCell(7);
        cell5.setCellValue("Color");
        cell5 = row.createCell(8);
        cell5.setCellValue("Delivery");
        cell5 = row.createCell(9);
        cell5.setCellValue("Image");
        cell5 = row.createCell(10);
        cell5.setCellValue("Image");
        cell5 = row.createCell(11);
        cell5.setCellValue("Số lượng đã bán");

        int i = 0;


        for (SoLuongDaBan soluong : listTop10Product) {
            for (Product pro : listAllProduct) {
                if (soluong.getProductID() == pro.getId()) {
                    i = i + 1;
                    row = workSheet.createRow(i);
                    cell0 = row.createCell(0);
                    cell0.setCellValue(pro.getId());
                    cell1 = row.createCell(1);
                    cell1.setCellValue(pro.getName());
                    cell2 = row.createCell(2);
                    cell2.setCellValue(pro.getImage());
                    cell3 = row.createCell(3);
                    cell3.setCellValue(pro.getPrice());
                    cell4 = row.createCell(4);
                    cell4.setCellValue(pro.getTitle());
                    cell4 = row.createCell(5);
                    cell4.setCellValue(pro.getDescription());
                    cell4 = row.createCell(6);
                    cell4.setCellValue(pro.getModel());
                    cell4 = row.createCell(7);
                    cell4.setCellValue(pro.getColor());
                    cell4 = row.createCell(8);
                    cell4.setCellValue(pro.getDelivery());
                    cell4 = row.createCell(9);
                    cell4.setCellValue(pro.getImage2());
                    cell4 = row.createCell(10);
                    cell4.setCellValue(pro.getImage3());
                    cell4 = row.createCell(11);
                    cell4.setCellValue(soluong.getSoLuongDaBan());
                }
            }
        }

        workbook.write(file);
        workbook.close();
        file.close();

        request.setAttribute("mess", "Đã xuất file Excel thành công!");
        request.getRequestDispatcher("top10").forward(request, response);
    }
}