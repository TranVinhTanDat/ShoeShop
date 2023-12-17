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

@WebServlet(name = "ShopController", urlPatterns = {"/searchForm","/shop", "/searchAjaxShop", "/searchAjaxPriceUnder100Shop", "/searchAjaxPrice100To200Shop", "/searchAjaxPriceAbove200Shop", "/searchAjaxPriceMinToMax", "/searchAjaxColorBlack", "/searchAjaxColorGray", "/searchAjaxColorWhite", "/searchAjaxColorYellow"})
public class ShopController extends HttpServlet {

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
            case "/shop":
                processShopRequest(request, response);
                break;
            case "/searchAjaxShop":
                processSearchRequest(request, response);
                break;
            case "/searchAjaxPriceUnder100Shop":
                processFilterPriceUnder100Request(request, response);
                break;
            case "/searchAjaxPrice100To200Shop":
                processFilterPrice100to200Request(request, response);
                break;
            case "/searchAjaxPriceAbove200Shop":
                processFilterPriceAbove200Request(request, response);
                break;
            case "/searchAjaxPriceMinToMax":
                processFilterPriceMinToMaxRequest(request, response);
                break;
            case "/searchAjaxColorBlack":
                processFilterColorBlackRequest(request, response);
                break;
            case "/searchAjaxColorGray":
                processFilterColorGrayRequest(request, response);
                break;
            case "/searchAjaxColorWhite":
                processFilterColorWhiteRequest(request, response);
                break;
            case "/searchAjaxColorYellow":
                processFilterColorYellowRequest(request, response);
                break;
            case "/searchForm":
                processSearchaaRequest(request, response);
                break;
            default:
                break;
        }
    }

    protected void processShopRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from dao
        DAO dao = new DAO();
//        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();


        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int indexPage = Integer.parseInt(index);

        List<Product> list = dao.getProductByIndex(indexPage);
//        List<Category> listC = dao.getAllCategory();
        int allProduct = dao.countAllProduct();
        int endPage = allProduct / 9;
        if (allProduct % 9 != 0) {
            endPage++;
        }
        request.setAttribute("tag", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listCC", listC);
        request.setAttribute("listP", list);


        request.getRequestDispatcher("Shop.jsp").forward(request, response);
        //404 -> url
        //500 -> jsp properties
    }
    protected void processSearchaaRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from dao
        DAO dao = new DAO();
        String name = request.getParameter("search");
//       List<Product> list = dao.SearchProduct(name);
        List<Category> listC = dao.getAllCategory();


        String index = request.getParameter("index");
        index = "1";
        int indexPage = Integer.parseInt(index);

        List<Product> list = dao.SearchProduct(name);
//        List<Category> listC = dao.getAllCategory();
        int allProduct = dao.countAllProduct();
        int endPage = allProduct / 9;
        if (allProduct % 9 != 0) {
            endPage++;
        }
        request.setAttribute("tag", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listCC", listC);
        request.setAttribute("listP", list);


        request.getRequestDispatcher("Shop.jsp").forward(request, response);
        //404 -> url
        //500 -> jsp properties
    }

    protected void processSearchRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("txt");
        DAO dao = new DAO();
        List<Product> list = dao.searchByName(txtSearch);
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

    protected void processFilterPriceUnder100Request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();
        List<Product> list = dao.searchPriceUnder100();
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

    protected void processFilterPrice100to200Request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();
        List<Product> list = dao.searchPrice100To200();
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

    protected void processFilterPriceAbove200Request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();
        List<Product> list = dao.searchPriceAbove200();
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

    protected void processFilterPriceMinToMaxRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");
        String priceMin = request.getParameter("priceMin");
        String priceMax = request.getParameter("priceMax");
        DAO dao = new DAO();
        List<Product> list = dao.searchByPriceMinToMax(priceMin, priceMax);
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

    protected void processFilterColorBlackRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();
        List<Product> list = dao.searchColorBlack();
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

    protected void processFilterColorGrayRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();
        List<Product> list = dao.searchColorGray();
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

    protected void processFilterColorWhiteRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();
        List<Product> list = dao.searchColorWhite();
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

    protected void processFilterColorYellowRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();
        List<Product> list = dao.searchColorYellow();
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
}