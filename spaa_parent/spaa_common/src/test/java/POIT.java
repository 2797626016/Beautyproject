import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class POIT {


    //使用Poit读取excel表中的数据
    @Test
    public void testRead() throws IOException {
        //使用流读取文件
        FileInputStream fileInputStream =new FileInputStream(new File("C:\\Users\\chen\\Desktop\\WWWW.xlsx"));
        //使用xssf去创建workbook
        XSSFWorkbook excel= new XSSFWorkbook(fileInputStream);
        //使用sheet 索引
        XSSFSheet sheet = excel.getSheetAt(0);
        //遍历循环row
        for (Row row:sheet) {
            System.out.println(row);
            for (Cell cell:row) {
                System.out.println(cell.getStringCellValue());
            }
        }
        excel.close();
    }

    //使用poit写入excel
    @Test
    public void write() throws IOException {
        //使用流写入文件

        //使用xssf去创建workbook
        XSSFWorkbook excel= new XSSFWorkbook();
        //创建sheet
        XSSFSheet sheet = excel.createSheet("sheet");
        //3.创建row cell
        XSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("年龄");
        title.createCell(2).setCellValue("地址");
        //内容
        XSSFRow dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("ccccc");
        dataRow.createCell(1).setCellValue("sss");
        dataRow.createCell(2).setCellValue("ttt");

        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\chen\\Desktop\\tt.xlsx"));

        excel.write(fileOutputStream);
        fileOutputStream.flush();
        excel.close();

    }
}
