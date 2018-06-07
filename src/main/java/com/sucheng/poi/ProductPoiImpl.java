package com.sucheng.poi;

import com.sucheng.query.ProductQuery;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ProductPoiImpl implements ProductPoi {

    Boolean IS_NEW_VERSION = true;

    @Override
    public void writeExcel(HttpServletRequest request, String path, List<ProductQuery> productList, String title, String[] headStr) {
        if (path.endsWith(".xls")) {
            writeExcel(request, path, ".xls", productList, title, headStr);
        } else if (path.endsWith(".xlsx")) {
            writeExcel(request, path, ".xlsx", productList,title, headStr);
        }
    }

    @Override
    public void writeExcel(HttpServletRequest request, String path, String ext, List<ProductQuery> productList, String title, String[] headStr) {
        Workbook workbook = null;
        if (".xls".equalsIgnoreCase(ext)) {
            IS_NEW_VERSION = false;
            workbook = new HSSFWorkbook();
        } else if (".xlsx".equalsIgnoreCase(ext)) {
            workbook = new XSSFWorkbook();
        }
        if (workbook != null) {
            Sheet sheet = workbook.createSheet("Sheet1");
            writeTitle(sheet, title);
            writeHeader(sheet, headStr);
            writeContent(sheet, productList, request);
            try {
                workbook.write(new FileOutputStream(path));
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void writeTitle(Sheet sheet, String title) {
        Workbook workbook = sheet.getWorkbook();
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) 280);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(title);
    }

    @Override
    public void writeHeader(Sheet sheet, String[] columnNames) {
        Workbook workbook = sheet.getWorkbook();
        Row row = sheet.createRow(1);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        for (int col = 0, len = columnNames.length; col < len; col++) {
            Cell cell = row.createCell(col);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columnNames[col]);
        }
    }

    @Override
    public void writeContent(Sheet sheet, List<ProductQuery> productList, HttpServletRequest request) {
        Workbook workbook = sheet.getWorkbook();
        CellStyle evenRowStyle = workbook.createCellStyle();
        evenRowStyle.setFillBackgroundColor(IndexedColors.GOLD.index);
        evenRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        CellStyle oddRowStyle = workbook.createCellStyle();
        oddRowStyle.setFillBackgroundColor(IndexedColors.GREEN.index);
        oddRowStyle.setFillPattern(FillPatternType.SQUARES);
        ServletContext application = request.getServletContext();
        String absPath = application.getRealPath("/");
        for (int row = 2, totalRows = productList.size() + 2; row < totalRows; row++) {
            ProductQuery productQuery = productList.get(row - 2);
            Row rowData = sheet.createRow(row);
            rowData.createCell(0).setCellValue(productQuery.getId());
            rowData.createCell(1).setCellValue(productQuery.getName());
            rowData.createCell(2).setCellValue(productQuery.getTaste());
            rowData.createCell(3).setCellValue(productQuery.getTypeName());
            rowData.createCell(4).setCellValue(productQuery.getUnit());
            rowData.createCell(5).setCellValue(String.valueOf(productQuery.getPrice()));

            if(productQuery.getProImg1() == null || "".equals(productQuery.getProImg1())) {
                rowData.createCell(6).setCellValue("暂无图片");
            } else {
                rowData.createCell(6);
                insertPicture(sheet, absPath + productQuery.getProImg1(), 0, 0, 300, 300,
                        row, 6, row + 1, 7);
                sheet.setColumnWidth(7, 5000);
            }

            if(productQuery.getProImg2() == null || "".equals(productQuery.getProImg2())) {
                rowData.createCell(7).setCellValue("暂无图片");
            } else {
                rowData.createCell(7);
                insertPicture(sheet, absPath + productQuery.getProImg2(), 0, 0, 300, 300,
                        row, 7, row + 1, 8);
                sheet.setColumnWidth(8, 5000);
            }

            if(productQuery.getProImg3() == null || "".equals(productQuery.getProImg3())) {
                rowData.createCell(8).setCellValue("暂无图片");
            } else {
                rowData.createCell(8);
                insertPicture(sheet, absPath + productQuery.getProImg3(), 0, 0, 300, 300,
                        row, 8, row + 1, 9);
                sheet.setColumnWidth(9, 5000);
            }

            if(productQuery.getProImg4() == null || "".equals(productQuery.getProImg4())) {
                rowData.createCell(9).setCellValue("暂无图片");
            } else {
                rowData.createCell(9);
                insertPicture(sheet, absPath + productQuery.getProImg4(), 0, 0, 300, 300,
                        row, 9, row + 1, 10);
                sheet.setColumnWidth(10, 5000);
            }

            rowData.createCell(10).setCellValue(productQuery.getPriority());
            rowData.createCell(11).setCellValue(productQuery.getStatus());
            Cell cell = rowData.createCell(12);
            CreationHelper creationHelper = workbook.getCreationHelper();
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
            cell.setCellStyle(cellStyle);
            cell.setCellValue(productQuery.getCreatedTime());
        }
    }

    @Override
    public void drawPicture(Sheet sheet, String imagePath, int row, int col) {
        try {
            Workbook workbook = sheet.getWorkbook();
            byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(imagePath));
            int picIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
            CreationHelper creationHelper = workbook.getCreationHelper();
            ClientAnchor clientAnchor = creationHelper.createClientAnchor();
            clientAnchor.setRow1(row);
            clientAnchor.setCol1(col);
            Drawing drawing = sheet.createDrawingPatriarch();
            Picture picture = drawing.createPicture(clientAnchor, picIdx);
            picture.resize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPicture(Sheet sheet, String imagePath, int leftDX, int topDY, int widthDX, int heightDY, int beginRow, int beginCol, int endRow, int endCol) {
        Workbook workbook = sheet.getWorkbook();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor clientAnchor = workbook.getCreationHelper().createClientAnchor();
        clientAnchor.setDx1(leftDX);
        clientAnchor.setDy1(topDY);
        clientAnchor.setDx2(widthDX);
        clientAnchor.setDy2(heightDY);
        clientAnchor.setRow1(beginRow);
        clientAnchor.setCol1(beginCol);
        clientAnchor.setRow2(endRow);
        clientAnchor.setCol2(endCol);
        try {
            drawing.createPicture(clientAnchor,
                    workbook.addPicture(IOUtils.toByteArray(new FileInputStream(imagePath)), Workbook.PICTURE_TYPE_PNG));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
