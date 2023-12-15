package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;

import com.j2ee.shoestoreweb.entity.Category;
import com.j2ee.shoestoreweb.entity.Supplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "SupplierController", urlPatterns = {"/managerSupplier", "/addSupplier", "/deleteSupplier", "/xuatExcelSupplierControl", "/searchSupplier", "/addSu", "/addSupplierYour"})
public class SupplierController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getServletPath();
        switch (pathInfo) {
            case "/managerSupplier":
                processManagerSupplierRequest(request, response);
                break;
            case "/addSupplier":
                processAddSupplierRequest(request, response);
                break;
            case "/deleteSupplier":
                processDeleteSupplierRequest(request, response);
                break;
            case "/xuatExcelSupplierControl":
                processExportExcelRequest(request, response);
                break;

            case "/searchSupplier":
                processSearchSupplierRequest(request, response);
                break;
            case "/addSu":
                processAddNewSupplierRequest(request, response);
                break;
            case "/addSupplierYour":
                processAddYourSupplierRequest(request, response);
                break;
            default:
                break;
        }
    }

    protected void processManagerSupplierRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO dao = new DAO();

        List<Supplier> listAllSupplier = dao.getAllSupplier();
        List<Category> listAllCategory = dao.getAllCategory();


        request.setAttribute("listAllSupplier", listAllSupplier);
        request.setAttribute("listAllCategory", listAllCategory);

        request.getRequestDispatcher("NhaPhanPhoi.jsp").forward(request, response);
    }

    protected void processAddYourSupplierRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO dao = new DAO();
        Supplier su = new Supplier();

        String name = request.getParameter("name");
        String sdt = request.getParameter("sdt");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String pp = request.getParameter("add-row_length");

        su.setNameSupplier(name);
        su.setPhoneSupplier(sdt);
        su.setEmailSupplier(email);
        su.setAddressSupplier(address);
        su.setCateID(Integer.parseInt(pp));

        dao.AddSupplier(su);

        List<Supplier> listAllSupplier = dao.getAllSupplier();

        List<Category> listAllCategory = dao.getAllCategory();


        request.setAttribute("listAllSupplier", listAllSupplier);
        request.setAttribute("listAllCategory", listAllCategory);

        request.getRequestDispatcher("NhaPhanPhoi.jsp").forward(request, response);
    }

    protected void processAddNewSupplierRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Supplier su = new Supplier();
        DAO dao = new DAO();

        List<Supplier> listAllSupplier = dao.getAllSupplier();
        List<Category> listAllCategory = dao.getAllCategory();

        request.setAttribute("addSu", su);
        request.setAttribute("listAllSupplier", listAllSupplier);
        request.setAttribute("listAllCategory", listAllCategory);

        request.getRequestDispatcher("NhaPhanPhoi.jsp").forward(request, response);
    }

    protected void processSearchSupplierRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("search");

        DAO dao = new DAO();

        List<Supplier> listAllSupplier = dao.SearchSupplier(name);
        List<Category> listAllCategory = dao.getAllCategory();


        request.setAttribute("listAllSupplier", listAllSupplier);
        request.setAttribute("listAllCategory", listAllCategory);

        request.getRequestDispatcher("NhaPhanPhoi.jsp").forward(request, response);
    }


    protected void processAddSupplierRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String nameSupplier = request.getParameter("nameSupplier");
        String phoneSupplier = request.getParameter("phoneSupplier");
        String emailSupplier = request.getParameter("emailSupplier");
        String addressSupplier = request.getParameter("addressSupplier");
        String cateID = request.getParameter("cateID");

        DAO dao = new DAO();
        dao.insertSupplier(nameSupplier, phoneSupplier, emailSupplier, addressSupplier, cateID);
        request.setAttribute("mess", "Supplier Added!");
        request.getRequestDispatcher("managerSupplier").forward(request, response);
    }

    protected void processDeleteSupplierRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idSupplier = request.getParameter("id");
        DAO dao = new DAO();
        dao.deleteSupplier(idSupplier);
        request.setAttribute("mess", "Supplier Deleted!");
        request.getRequestDispatcher("managerSupplier").forward(request, response);
    }

    protected void processExportExcelRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO dao = new DAO();
        List<Supplier> listAllSupplier = dao.getAllSupplier();
        List<Category> listAllCategory = dao.getAllCategory();

        int maximum = 2147483647;
        int minimum = 1;

        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;


        FileOutputStream file = new FileOutputStream("C:\\ExcelWebBanGiay\\" + "nha-cung-cap-" + Integer.toString(randomNum) + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("1");
        XSSFRow row;
        XSSFCell cell0;
        XSSFCell cell1;
        XSSFCell cell2;
        XSSFCell cell3;
        XSSFCell cell4;
        XSSFCell cell5;

        row = workSheet.createRow(0);
        cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        cell1 = row.createCell(1);
        cell1.setCellValue("Tên nhà cung cấp");
        cell2 = row.createCell(2);
        cell2.setCellValue("Số điện thoại");
        cell3 = row.createCell(3);
        cell3.setCellValue("Email");
        cell4 = row.createCell(4);
        cell4.setCellValue("Địa chỉ");
        cell5 = row.createCell(5);
        cell5.setCellValue("Phân phối cho");

        int i = 0;

        for (Supplier supplier : listAllSupplier) {
            for (Category category : listAllCategory) {
                if (supplier.getCateID() == category.getCid()) {
                    i = i + 1;
                    row = workSheet.createRow(i);
                    cell0 = row.createCell(0);
                    cell0.setCellValue(supplier.getIdSupplier());
                    cell1 = row.createCell(1);
                    cell1.setCellValue(supplier.getNameSupplier());
                    cell2 = row.createCell(2);
                    cell2.setCellValue(supplier.getPhoneSupplier());
                    cell3 = row.createCell(3);
                    cell3.setCellValue(supplier.getEmailSupplier());
                    cell4 = row.createCell(4);
                    cell4.setCellValue(supplier.getAddressSupplier());
                    cell5 = row.createCell(5);
                    cell5.setCellValue(category.getCname());
                }
            }
        }

        workbook.write(file);
        workbook.close();
        file.close();

        request.setAttribute("mess", "Đã xuất file Excel thành công!");
        request.getRequestDispatcher("managerSupplier").forward(request, response);
    }
}