package com.cot.app.backend.scheduled;

import static com.cot.app.backend.scheduled.ReportDownloader.REPORT_DOWNLOAD_LOCATION;
import static com.cot.app.backend.scheduled.ReportDownloader.REPORT_UNZIPPED_FILENAME;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.cot.app.backend.db.DbOperation;
import com.cot.app.backend.model.ReportDto;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class ExcelFileUtils {

    private static final String DEFAULT_FIELD_VALUE = "0.0";

    private static final int LONGS_CELL = 18;
    private static final int SHORTS_CELL = 19;
    private static final int INSTRUMENT_CELL = 0;
    private static final int REPORT_DATE_CELL = 2;
    private static final int PERCENT_LONG_CELL = 58;
    private static final int PERCENT_SHORT_CELL = 59;
    private static final int LONG_POSITIONS_CELL = 38;
    private static final int SHORT_POSITIONS_CELL = 39;

    private static final int FIRST_SHEET_POSITION = 0;

    @Autowired
    private DbOperation<ReportDto> dbOperation;

    public void saveReportToDb() throws IOException {
        List<ReportDto> reportDtos = new ArrayList<>();

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(REPORT_DOWNLOAD_LOCATION + REPORT_UNZIPPED_FILENAME)));
        HSSFSheet worksheet = workbook.getSheetAt(FIRST_SHEET_POSITION);

        for (int rowIndex = 1; rowIndex < worksheet.getPhysicalNumberOfRows(); rowIndex++) {
            ReportDto reportDto = new ReportDto();

            Optional<String> optLongPositions = Optional.empty();
            if(!ObjectUtils.isEmpty(worksheet.getRow(rowIndex).getCell(LONG_POSITIONS_CELL))){
                optLongPositions = Optional.ofNullable(worksheet.getRow(rowIndex).getCell(LONG_POSITIONS_CELL).toString());
            }

            Optional<String> optShortPositions = Optional.empty();
            if(!ObjectUtils.isEmpty(worksheet.getRow(rowIndex).getCell(SHORT_POSITIONS_CELL))){
                optShortPositions = Optional.ofNullable(worksheet.getRow(rowIndex).getCell(SHORT_POSITIONS_CELL).toString());
            }

            Optional<String> optPercentageShort = Optional.empty();
            if(!ObjectUtils.isEmpty(worksheet.getRow(rowIndex).getCell(PERCENT_SHORT_CELL))){
                optPercentageShort = Optional.ofNullable(worksheet.getRow(rowIndex).getCell(PERCENT_SHORT_CELL).toString());
            }

            Optional<String> optPercentageLong = Optional.empty();
            if(!ObjectUtils.isEmpty(worksheet.getRow(rowIndex).getCell(PERCENT_LONG_CELL))){
                optPercentageLong = Optional.ofNullable(worksheet.getRow(rowIndex).getCell(PERCENT_LONG_CELL).toString());
            }
///
            Optional<String> optInstrument = Optional.empty();
            if(!ObjectUtils.isEmpty(worksheet.getRow(rowIndex).getCell(INSTRUMENT_CELL))){
                optInstrument = Optional.ofNullable(worksheet.getRow(rowIndex).getCell(INSTRUMENT_CELL).toString());
            }

            Optional<String> optReportDate = Optional.empty();
            if(!ObjectUtils.isEmpty(worksheet.getRow(rowIndex).getCell(REPORT_DATE_CELL))){
                optReportDate = Optional.ofNullable(worksheet.getRow(rowIndex).getCell(REPORT_DATE_CELL).toString());
            }

            Optional<String> optLongs = Optional.empty();
            if(!ObjectUtils.isEmpty(worksheet.getRow(rowIndex).getCell(LONGS_CELL))){
                optLongs = Optional.ofNullable(worksheet.getRow(rowIndex).getCell(LONGS_CELL).toString());
            }

            Optional<String> optShorts = Optional.empty();
            if(!ObjectUtils.isEmpty(worksheet.getRow(rowIndex).getCell(SHORTS_CELL))){
                optShorts = Optional.ofNullable(worksheet.getRow(rowIndex).getCell(SHORTS_CELL).toString());
            }

            reportDto.setChangeLong(optLongPositions.orElseGet(() -> DEFAULT_FIELD_VALUE));
            reportDto.setChangeShort(optShortPositions.orElseGet(() -> DEFAULT_FIELD_VALUE));
            reportDto.setPercentageLong(optPercentageLong.orElseGet(() -> DEFAULT_FIELD_VALUE));
            reportDto.setPercentageShort(optPercentageShort.orElseGet(() -> DEFAULT_FIELD_VALUE));

            reportDto.setInstrument(optInstrument.orElseGet(() -> DEFAULT_FIELD_VALUE));
            reportDto.setReportDate(worksheet.getRow(rowIndex).getCell(REPORT_DATE_CELL).toString());
            reportDto.setLongPositions(worksheet.getRow(rowIndex).getCell(LONGS_CELL).getNumericCellValue());
            reportDto.setShortPositions(worksheet.getRow(rowIndex).getCell(SHORTS_CELL).getNumericCellValue());
            reportDto.setNetPositions(reportDto.getLongPositions() - reportDto.getShortPositions());

            reportDtos.add(reportDto);
        }

        dbOperation.save(reportDtos);
    }

}
