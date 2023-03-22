package support;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class StringHelper {
    private static long start;

    public String floatToString(float value) {
        int i = Math.round(value);
        String x = String.valueOf(i);
        return x;
    }

    public String decimalToPercentage(float value) {
        float v = value * 100;
        int i = Math.round(v);
        String x = String.valueOf(i);
        return x;
    }

    public String stopTime(long start, WebDriver driver) {
        while (System.currentTimeMillis() > start) {
            if (String.valueOf(
                            ((JavascriptExecutor) driver)
                                    .executeScript("return document.readyState"))
                    .equals("complete"))
                System.out.println("Dom loaded completely");
            {
                break;
            }
        }
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        double valueInSeconds = totalTime;
        System.out.println("Values in Second-" + valueInSeconds / 1000);

        return String.valueOf(valueInSeconds / 1000);

    }
}
