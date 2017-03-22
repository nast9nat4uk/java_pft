package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.sun.jna.platform.win32.Netapi32Util;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Настя on 19.03.2017.
 */
public class UserDataGenerator {
    @Parameter (names = "-c", description = "User count")
    public  int count;

    @Parameter (names = "-f", description = "Target file")
    public  String file;

    @Parameter (names = "-d", description = "Data format")
    public  String format;

    public  static  void main(String[] args) throws IOException {
        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        if (format.equals("csv")) {
            saveAsCsv(users, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(users, new File(file));
        } else {
            System.out.println("Unrecognized format %s" + format);
    }
    }

    private void saveAsXml(List<UserData> users, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(UserData.class);//читает подсказки для класса UserData.class, для генерации xml с заданным тегом
        String xml = xStream.toXML(users);
        Writer writer  = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<UserData> users, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (UserData user:users){
            writer.write(String.format(("%s;%s\n"),user.getName(),user.getLastName()));
        }
        writer.close();
    }

    private static List<UserData> generateUsers(int count) {
        List<UserData> users = new ArrayList<UserData>();
        for(int i=0;i<count;i++){
            users.add(new UserData().withName(String.format("name %s",i)).withLastName(String.format("lastname %s",i)));
        }
        return  users;
    }
}
