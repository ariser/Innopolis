package LLoydsBankGroup;

import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;

public class Logger {
    public static void log(Account account, String transaction, double amount) {
        String logEntry = account.owner.firstName + " " + account.owner.lastName + " made a transaction: " + transaction + ", with amount: " + amount;

        String filename = DateFormat.getDateInstance().format(new Date());
        File logFile = new File("logs" + File.separator + filename + ".log");

        if (!logFile.exists()) {
            logFile.getParentFile().mkdirs();
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream stream = new FileOutputStream(logFile, true);
            OutputStreamWriter osw = new OutputStreamWriter(stream, Charset.forName("utf-8"));
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(logEntry);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
