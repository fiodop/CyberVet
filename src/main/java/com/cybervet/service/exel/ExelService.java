package com.cybervet.service.exel;

import com.cybervet.model.dto.FoodExelRow;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExelService {

    public List<FoodExelRow> readExel(InputStream inputStream) throws IOException {
        List<FoodExelRow> foodExelRows = new ArrayList<FoodExelRow>();

        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for(int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if(row == null) {
                    continue;
                }
                FoodExelRow dto = new FoodExelRow(row);
                foodExelRows.add(dto);
            }
            return foodExelRows;

        } catch (Exception e) {
        throw new RuntimeException("Ошибка чтения Excel-файла", e);
    }
    }
}
