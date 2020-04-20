package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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

public class GeneralUtilities {

    public static int maxNumber;
    public static int count;
    public static int number2=1;
    public static int number3=0;


 // A function that prints
// given number in words
    public static void convert_to_words(char[] num)
    {
        // Get number of digits
        // in given number
        int len = num.length;

        // Base cases
        if (len == 0)
        {
            System.out.println("empty string");
            return;
        }
        if (len > 4)
        {
            System.out.println("Length more than 4 is not supported");
            return;
        }

	/* The first string is not used, it is to make
		array indexing simple */
        String[] single_digits = new String[]{ "zero", "one",
                "two", "three", "four",
                "five", "six", "seven",
                "eight", "nine"};

	/* The first string is not used, it is to make
		array indexing simple */
        String[] two_digits = new String[]{"", "ten", "eleven", "twelve",
                "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen",
                "eighteen", "nineteen"};

        /* The first two string are not used, they are to make array indexing simple*/
        String[] tens_multiple = new String[]{"", "", "twenty", "thirty", "forty",
                "fifty","sixty", "seventy",
                "eighty", "ninety"};

        String[] tens_power = new String[] {"hundred", "thousand"};

        /* Used for debugging purpose only */
        System.out.print(String.valueOf(num)+": ");

        /* For single digit number */
        if (len == 1)
        {
            System.out.println(single_digits[num[0] - '0']);
            return;
        }

	/* Iterate while num
		is not '\0' */
        int x = 0;
        while (x < num.length)
        {

            /* Code path for first 2 digits */
            if (len >= 3)
            {
                if (num[x]-'0' != 0)
                {
                    System.out.print(single_digits[num[x] - '0']+" ");
                    System.out.print(tens_power[len - 3]+" ");
                    // here len can be 3 or 4
                }
                --len;
            }

            /* Code path for last 2 digits */
            else
            {
			/* Need to explicitly handle
			10-19. Sum of the two digits
			is used as index of "two_digits"
			array of strings */
                if (num[x] - '0' == 1)
                {
                    int sum = num[x] - '0' +
                            num[x] - '0';
                    System.out.println(two_digits[sum]);
                    return;
                }

                /* Need to explicitely handle 20 */
                else if (num[x] - '0' == 2 &&
                        num[x + 1] - '0' == 0)
                {
                    System.out.println("twenty");
                    return;
                }

			/* Rest of the two digit
			numbers i.e., 21 to 99 */
                else
                {
                    int i = (num[x] - '0');
                    if(i > 0)
                        System.out.print(tens_multiple[i]+" ");
                    else
                        System.out.print("");
                    ++x;
                    if (num[x] - '0' != 0)
                        System.out.println(single_digits[num[x] - '0']);
                }
            }
            ++x;
        }
    }


    public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        //Call getScreenshotAs method to create image file

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        File DestFile = new File(fileWithPath);

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);


    }


    public void printFibonacci(int count) {
        // Set it to the number of elements you want in the Fibonacci Series
        maxNumber = count;
        int previousNumber = 0;
        int nextNumber = 1;

        System.out.println("Fibonacci Series of " + maxNumber + " numbers:");

        for (int i = 1; i <= maxNumber; ++i)
        {
            if (i ==maxNumber) {
                //System.out.println(previousNumber + " ");
                String fibmaxNumStr = Integer.toString(previousNumber);
                convert_to_words(fibmaxNumStr.toCharArray());

            }
                /* On each iteration, we are assigning second number
             * to the first number and assigning the sum of last two
             * numbers to the second number
             */


            int sum = previousNumber + nextNumber;
            previousNumber = nextNumber;
            nextNumber = sum;
        }
        }


}

