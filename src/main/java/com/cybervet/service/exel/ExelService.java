package com.cybervet.service.exel;

import com.cybervet.model.dto.FoodExelDto;
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

    public List<FoodExelDto> readExel(InputStream inputStream) throws IOException {
        List<FoodExelDto> foodExelDtos = new ArrayList<FoodExelDto>();

        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for(int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if(row == null) {
                    continue;
                }
                FoodExelDto dto = new FoodExelDto(row);
                foodExelDtos.add(dto);
            }
            return foodExelDtos;

        } catch (Exception e) {
        throw new RuntimeException("Ошибка чтения Excel-файла", e);
    }
    }
}
