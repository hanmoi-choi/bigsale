package com.bigsale.util;

import com.bigsale.controller.admin.AdminReportController;
import com.bigsale.orm.model.Seller;
import com.bigsale.orm.model.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 16/10/12
 * Time: 12:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Component("adminPdfReportView")
public class AdminPdfReportView extends PdfReportView{
    static final Logger logger = LoggerFactory.getLogger(AdminReportController.class);

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Map<String, Object> userData = (Map<String, Object>) model.get("userData");
        List<User> userList = (List<User>) userData.get("userList");
        List<Seller> sellerList = (List<Seller>) userData.get("sellerList");

        document.add(new Paragraph("User List"));
        document.add(new Paragraph("\n\n"));

        document.add(new Paragraph("Buyers"));
        document.add(new Paragraph("\n"));
        if(userList != null) document.add(createBuyerTable(userList));
        document.add(new Paragraph("\n\n"));

        document.add(new Paragraph("Sellers"));
        document.add(new Paragraph("\n"));
        if(sellerList != null) document.add(createSellerTable(sellerList));

    }

    private PdfPTable createBuyerTable(List<User> userList)
    {
        // a table with three columns
        PdfPTable table = new PdfPTable(6);
        PdfPCell cell = new PdfPCell();
        cell.setColspan(6);
        cell.setRowspan(userList.size()+1);
        table.addCell(cell);

        table.addCell("User ID");
        table.addCell("Full Name");
        table.addCell("EMail");
        table.addCell("Level");
        table.addCell("Create Date");
        table.addCell("Login Count");

        Iterator<User> iterator = userList.iterator();

        while(iterator.hasNext()){
            User user = iterator.next();

            table.addCell(user.getUserId());
            table.addCell(user.getFullName());
            table.addCell(user.getEmail());
            table.addCell(user.getUserLevel().toString());
            table.addCell(user.getDateCreated().toString());
            table.addCell(String.valueOf(user.getLoginCount()));

        }

        return table;
    }

    private PdfPTable createSellerTable(List<Seller> sellerList) {
        // a table with three columns
        PdfPTable table = new PdfPTable(6);
        // the cell object
        PdfPCell cell = new PdfPCell();
        cell.setColspan(6);
        cell.setRowspan(sellerList.size()+1);
        table.addCell(cell);

        table.addCell("Seller ID");
        table.addCell("Full Name");
        table.addCell("EMail");
        table.addCell("Level");
        table.addCell("Create Date");
        table.addCell("Login Count");

        Iterator<Seller> iterator = sellerList.iterator();

        while(iterator.hasNext()){
            Seller seller = iterator.next();

            table.addCell(seller.getSellerId());
            table.addCell(seller.getFullName());
            table.addCell(seller.getEmail());
            table.addCell(seller.getSellerLevel().toString());
            table.addCell(seller.getDateCreated().toString());
            table.addCell(String.valueOf(seller.getLoginCount()));

        }
        return table;
    }
}


