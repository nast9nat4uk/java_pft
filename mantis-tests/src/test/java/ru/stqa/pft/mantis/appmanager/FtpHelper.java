package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Настя on 04.04.2017.
 */
public class FtpHelper {

    private  final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper (ApplicationManager app){
        this.app = app;

        ftp = new FTPClient();//создание ftp-клиента, который будет уст. соед, передавать фалы и тп
    }

    public void upload (File file, String target, String backup) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target, backup);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target,new FileInputStream(file));
        ftp.disconnect();
    }

    public void restore(String backup, String target) throws IOException {//восстанавливает старый файл
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }

}
