package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static utils.GetProperties.*;

public class FileUtilities {

    public void uploadFile(String filePath) throws Exception {
        StringSelection file = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);

        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public String getFileNameFromPath(String filePath) {
        if (filePath.contains("\\")) {
            String[] parts = filePath.split("\\\\");
            return parts[parts.length - 1];
        } else {
            String[] parts = filePath.split("/");
            return parts[parts.length - 1];
        }
    }

    public void writeInterpreterDataToFile(List<String> lines) {
        try {
            File output = new File(OUTPUT_FILE);
            boolean create = output.createNewFile();
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE), StandardOpenOption.APPEND);

            removeRegistrationDataWithUserName(USER_EMAIL);

            String date = getTodaysDateAndTime();
            String interpreter = String.format("Registration data for '%s' in %s, %s:\n", USER_EMAIL, ENVIRONMENT.toUpperCase(), date);
            writer.write(interpreter);

            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.write("\n\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeRegistrationDataWithUserName(String userName) {
        try {
            File inputFile = new File(OUTPUT_FILE);
            List<String> lines = FileUtils.readLines(inputFile, "UTF-8");
            List<String> updatedLines = new ArrayList<>();

            int index = 0;
            int i = 0;
            for (String line : lines) {
                if (line.contains(userName)) {
                    index = lines.indexOf(line);
                    i = index + 15;
                } else if (index < i) {
                    index++;
                } else {
                    updatedLines.add(line);
                }
            }

            FileUtils.writeLines(inputFile, updatedLines, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTodaysDateAndTime() {
        String date = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            Date currentDate = new Date();
            date = dateFormat.format(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getLineFromFile(String file, int line) {
        List<String> lines = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File(file));
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines.get(line - 1);
    }

    public void chooseFile(String filePath, WebElement button) {
        try {
            FileUtilities fu = new FileUtilities();
            button.click();
            fu.uploadFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
