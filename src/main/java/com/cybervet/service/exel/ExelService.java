package com.cybervet.service.exel;

import com.cybervet.model.FoodExel;
import com.cybervet.service.model.FoodExelService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ExelService {
    private final FoodExelService foodExelService;

    public int readExel(InputStream inputStream) throws IOException {
        int cnt = 0;
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for(int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if(row == null) {
                    continue;
                }
                FoodExel dto = new FoodExel(row);
                foodExelService.save(dto);
                cnt++;
            }
        } catch (Exception e) {
        throw new RuntimeException("Ошибка чтения Excel-файла", e);
    }
        return cnt;
    }
}
