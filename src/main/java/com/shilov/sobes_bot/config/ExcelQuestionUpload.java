package com.shilov.sobes_bot.config;

import com.shilov.sobes_bot.model.Answer;
import com.shilov.sobes_bot.model.Question;
import com.shilov.sobes_bot.repository.QuestionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelQuestionUpload {
    private final QuestionRepository questionRepository;
    private static final String EXCEL_PATH = "/data/data.xlsx";

    @PostConstruct
    public void init() {
        File file = new File(EXCEL_PATH);
        XSSFWorkbook xssfWorkbook;
        try {
            xssfWorkbook = new XSSFWorkbook(file);
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = xssfWorkbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getCell(0) != null && questionRepository.findQuestionByText(row.getCell(0).getStringCellValue()).isEmpty() &&
                    row.getCell(1) != null
            ) {
                log.warn(row.getCell(0).getStringCellValue());
                log.warn(row.getCell(1).getStringCellValue());
                Question question = new Question();
                question.setText(row.getCell(0).getStringCellValue());
                Answer answer = new Answer();
                answer.setText(row.getCell(1).getStringCellValue());
                question.setAnswer(answer);
                questionRepository.save(question);
            }
        }
    }
}
