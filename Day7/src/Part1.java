import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
public class Part1 implements Runnable {
    String input;
    String path = "Day7-DirectoryBased\\src\\directories.txt";
    String[] lines;

    public Part1() {
        getInputFromFile();
    }

    static String[] dirs;

    public void getInputFromFile() {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.input = contentBuilder.toString();
        lines = input.split("\n");
    }

    public String getInput() {
        return input;
    }

    @Override
    public void run() {
        Part1 p1 = new Part1();
        String[] dirs = p1.getInput().split("\n");

        ArrayList<Directory> directories = new ArrayList<>();
//        System.out.println(Arrays.toString(dirs));
        Directory home = new Directory("/");
        setDirs(dirs);
        home.setDirectories(getDirectory(home.toString(), 0));
        home.setFiles(getFiles(home.toString(), 0));

//        int numberOfDirectoriesLess = numberOfDirectories(home, 100_000);
//        numberOfDirectoriesLess += (home.getDirectorySize() < 100_000 ? 1 : 0);

        System.out.println(numberOfDirectories(home, 100_000));
    }

    private void setDirs(String[] dirs) {
        Part1.dirs = dirs;
    }

    public static ArrayList<Directory> getDirectory(String name, int position) {

        ArrayList<String> contents = new ArrayList<>();
        for (int i = position; i < dirs.length; i++) {
            if (dirs[i].equals("$ cd " + name)) {
                i += 2;
                position = i;
                while (!dirs[i].contains("$ cd")) {
                    contents.add(dirs[i]);
                    i++;
                }
                break;
            }
        }
        ArrayList<Directory> directoryList = new ArrayList<>();
        ArrayList<File> fileContents = new ArrayList<>();

        for (String content : contents) {
            if (content.contains("dir")) {
                directoryList.add(new Directory(content, getDirectory(content.substring("dir ".length()), position), getFiles(content.substring("dir ".length()), position)));
            } else {
                fileContents.add(new File(content));
            }
        }
        return directoryList;
    }

    public static ArrayList<File> getFiles(String name, int position) {
        ArrayList<String> contents = new ArrayList<>();

        for (int i = position; i < dirs.length; i++) {
            if (dirs[i].equals("$ cd " + name)) {
                i += 2;
                while (!dirs[i].contains("$ cd")) {
                    contents.add(dirs[i]);
                    i++;
                }
                break;
            }
        }

        ArrayList<File> fileContents = new ArrayList<>();

        for (String content : contents) {
            if (!(content.contains("dir"))) {
                fileContents.add(new File(content));
            }
        }
        return fileContents;
    }


    public static int numberOfDirectories(Directory root, int number) {
        int sum = 0;
        if (root.getDirectories().size() == 0) {
            return (root.getDirectorySize() <= number ? sum + root.getDirectorySize() : sum);
        } else {
            for (Directory dir : root.getDirectories()) {
                sum += numberOfDirectories(dir, number);
            }
            sum += (root.getDirectorySize() <= number ? root.getDirectorySize() : 0);
        }

        return sum;
    }
}