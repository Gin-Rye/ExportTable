package com.export.model.output.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.export.base.exception.BussinessException;
import com.export.base.utils.DateUtils;
import com.export.model.store.ResultStore;

public class XLSExporter extends Exporter {

	@Override
	public void export(OutputStream outputStream, ResultStore resultStore)
			throws BussinessException {
		// 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(resultStore.getTableName());

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        List<String> headers = resultStore.getColumnNames();
        for (int i = 0; i < headers.size(); i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
            cell.setCellValue(text);
        }
        
        HSSFDataFormat format = workbook.createDataFormat();
        HSSFCellStyle floatStyle = workbook.createCellStyle();
        floatStyle.setDataFormat(format.getFormat("#,##0.00"));
        
        // 遍历集合数据，产生数据行
        int index = 1;
        while(resultStore.hasNext()) {
        	resultStore.next();
            row = sheet.createRow(index);
        	for(int column = 0; column < resultStore.getColumnCount(); column++) {
        		Object object = resultStore.getColumnData(column);
        		HSSFCell cell = row.createCell(column);
        		if(object == null) {
        			cell.setCellValue("");
        		} else if(object instanceof Number) {
        			if(object instanceof Float || object instanceof Double) {
        				cell.setCellStyle(floatStyle);
        			}
        			double data = Double.parseDouble(object.toString());
        			cell.setCellValue(data);
        		} else if(object instanceof Date) {
        			Date data = (Date) object;
        			cell.setCellValue(DateUtils.dateToString(data, "yyyy-MM-dd HH:mm:ss"));
        		} else {
        			cell.setCellValue(object.toString());
        		}
        	}
        	index++;
        }
        
        for(int i = 0; i <resultStore.getColumnCount(); i++) {
        	sheet.autoSizeColumn(i);
        }
        
        try {
	        workbook.write(outputStream);
        } catch(IOException e) {
        	throw new BussinessException(e);
        }
	}

	@Override
	public void effectiveExport(OutputStream outputStream, ResultStore resultStore)
			throws BussinessException {
		export(outputStream, resultStore);
	}

}
