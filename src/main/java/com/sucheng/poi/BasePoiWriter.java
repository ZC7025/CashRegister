package com.sucheng.poi;

import org.apache.poi.ss.usermodel.Sheet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BasePoiWriter<T> {

    /**
     *  写出excel
     * @param path 路径
     * @param contactList 内容
     * @param title 标题
     * @param headStr 表头
     */
    void writeExcel(HttpServletRequest request, String path, List<T> contactList, String title, String[] headStr);

    /**
     *  写出excel
     * @param path 路径
     * @param ext 文件格式
     * @param contactList 内容
     * @param title 标题
     * @param headStr 表头
     */
    void writeExcel(HttpServletRequest request, String path, String ext, List<T> contactList, String title, String[] headStr);

    /**
     *  写出标题
     * @param sheet 工作簿
     * @param title 标题
     */
    void writeTitle(Sheet sheet, String title);

    /**
     * 写出表头
     * @param sheet 工作簿
     * @param columnNames 表头名称
     */
    void writeHeader(Sheet sheet, String[] columnNames);

    /**
     *  写出内容
     * @param sheet 工作簿
     * @param contactList 内容列表
     */
    void writeContent(Sheet sheet, List<T> contactList, HttpServletRequest request);

    /**
     * 插入图片
     * @param sheet 工作簿
     * @param imagePath 图片路径
     * @param row 行
     * @param col 列
     */
    void drawPicture(Sheet sheet, String imagePath, int row, int col);

    /**
     * 在指定工作表的指定位置插入图片
     * @param sheet 工作表
     * @param imagePath 图片路径
     * @param leftDX 图片在单元格中离左上角的x距离
     * @param topDY 图片在单元格中离左上角的y距离
     * @param widthDX 图片的宽度
     * @param heightDY 图片的高度
     * @param beginRow 图片开始的行
     * @param beginCol 图片开始的列
     * @param endRow 图片结束的行
     * @param endCol 图片结束的列
     */
    void insertPicture(Sheet sheet, String imagePath,
                       int leftDX, int topDY, int widthDX, int heightDY,
                       int beginRow, int beginCol, int endRow, int endCol);
}
