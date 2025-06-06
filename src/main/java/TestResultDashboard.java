import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;


public class TestResultDashboard extends Application {

    private static final String EXCEL_PATH = "results/exploratory_testing.xlsx"; // Update this path

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HashMap<String, Integer> resultData = readTestResultsFromExcel(EXCEL_PATH);

        PieChart pieChart = new PieChart();
        pieChart.setTitle("Test Results Summary");
        for (String status : resultData.keySet()) {
            pieChart.getData().add(new PieChart.Data(status, resultData.get(status)));
        }

        VBox vbox = new VBox(pieChart);
        Scene scene = new Scene(vbox, 500, 400);
       
        
        stage.setScene(scene);
        

        stage.setTitle("QA Test Dashboard");
        stage.show();
    }

    private HashMap<String, Integer> readTestResultsFromExcel(String filePath) {
        HashMap<String, Integer> resultMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 1) continue; // Skip header
                Cell statusCell = row.getCell(8); // assuming status is in column B
                String status = statusCell.getStringCellValue();
                resultMap.put(status, resultMap.getOrDefault(status, 0) + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}

/*
README NOTES:
- Create a sample Excel file named 'test_results.xlsx' with columns:
  | TestCaseID | Status    |
  |------------|-----------|
  | TC_001     | Pass      |
  | TC_002     | Fail      |
  | TC_003     | Skipped   |

- Place it under a 'results' folder in your project directory.
- Run this JavaFX application to see a live pie chart.
*/
