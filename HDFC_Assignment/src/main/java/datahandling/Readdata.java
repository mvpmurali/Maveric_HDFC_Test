package datahandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readdata {
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sh;
	Cell c;
	String path;
	public Readdata() throws IOException {
		 path=".//Userdata.xlsx";
		fis = new FileInputStream(new File(path));
		 wb = new XSSFWorkbook(fis);
		 sh = wb.getSheet("Data");
	}
	public String exceldata(int row,int column){
		String exceldata;
		return exceldata = sh.getRow(row).getCell(column).getStringCellValue();
	}
	public void writedata(int row,int column) throws IOException{
		FileOutputStream fos = new FileOutputStream(new File(path));
		sh.createRow(row).createCell(column).setCellValue("Passed");
		wb.write(fos);
		fos.close();
	}

}
