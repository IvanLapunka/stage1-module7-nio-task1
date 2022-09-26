package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Path path = Paths.get(file.toURI());
        Profile result = new Profile();
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                final List<String> strings = Files.readAllLines(path);
                strings.forEach(s -> fillColumn(result, s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void fillColumn(Profile pf, String line) {
        String[] args = line.split("\\s");
        try {
            switch (args[0]) {
                case "Name:":
                    pf.setName(args[1]);
                    break;
                case "Age:":
                    pf.setAge(Integer.parseInt(args[1]));
                    break;
                case "Email:":
                    pf.setEmail(args[1]);
                    break;
                case "Phone:":
                    pf.setPhone(Long.parseLong(args[1]));
                    break;
                default:
                    throw new NumberFormatException("There was unknown argument:" + args[0]);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
