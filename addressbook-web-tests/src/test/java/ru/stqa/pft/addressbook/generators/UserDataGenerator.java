package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
        save(users,new File(file));
    }


    private static void save(List<UserData> users, File file) throws IOException {
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
