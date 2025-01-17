package com.file.reader.Excel_File_Reader.helper;

import com.file.reader.Excel_File_Reader.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductExcelHelper {

    //to check file is of Excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType)
                || "application/vnd.ms-excel".equals(contentType);
    }

   // convert Excel to list of products
    public static List<Product> convertExcelToListOfProduct(InputStream inputStream) {
        List<Product> list = new ArrayList<>();
        try {

              XSSFWorkbook workbook =  new XSSFWorkbook(inputStream);
              XSSFSheet sheet =  workbook.getSheet("data");

              int rowNumber = 0;
              Iterator<Row> iterator = sheet.iterator();

              //to skip the header row
              while (iterator.hasNext()){
                  Row row = iterator.next();
                  if(rowNumber == 0){
                      rowNumber++;
                      continue;
                  }

                  //iterate over cells in a row
                  Iterator<Cell> cells = row.iterator();
                  int cellId = 0;
                  Product product = new Product();

                  //reading cell value and mapping to the product
                  while (cells.hasNext()){
                    Cell cell = cells.next();

                    switch (cellId){

                        case 0:
                            product.setProductId((long)cell.getNumericCellValue());
                            break;
                        case 1:
                            product.setProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            product.setDescription(cell.getStringCellValue());
                            break;
                        case 3:
                            product.setPrice(cell.getNumericCellValue());
                            break;
                        default:
                            break;

                    }
                    cellId++;

                  }
                  list.add(product);
              }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
