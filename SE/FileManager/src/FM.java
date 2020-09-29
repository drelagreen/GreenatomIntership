
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class FM {
    private boolean professionalMode = false;

    public void setProfessionalMode() {
        this.professionalMode = true;
    }

    private static final FM instance = new FM();

    public static FM getInstance() {
        return instance;
    }

    public void todo(String str){
        File file;
        String[] commands;
        try {
            commands = parse(str);
        } catch (Exception e) {
            System.out.println("Error");
            return;
        }
        if (commands.length<2){
            System.out.println("Wrong arguments");
            return;
        }
        file = new File(commands[0]);


        switch (commands[1].toLowerCase()) {

            case "create":
                System.out.println(getAnswer(create(file, commands.length>=3 && commands[2].equalsIgnoreCase("-f"))));
                break;
            case "remove":
                System.out.println(getAnswer(remove(file, commands.length>=3 && commands[2].equalsIgnoreCase("-l"))));
                break;
            case "write":
                System.out.println(getAnswer(write(file, commands[2] == null ? "" : commands[2], commands.length>=4 && commands[3] != null && commands[3].equalsIgnoreCase("-c"))));
                break;
            case "read":
                System.out.println(getAnswer(read(file)));
                break;
            case "exit":
            case "quit":
                System.exit(0);
        }
    }

    private String[] parse(String str) {
        //todo возможно это все можно засунуь в стрим
        ArrayList<Integer> spaceIndexes = new ArrayList<>();
        char[] strings = str.toCharArray();
        String[] commands;
        boolean qFlag = false;
        for (int i = 0; i <strings.length ; i++) {
            if (strings[i]=='\"'){
                qFlag = !qFlag;
            }
            if (strings[i]==' '&&!qFlag){
                spaceIndexes.add(i);
            }
        }
        commands = new String[spaceIndexes.size()+1];
        for (int i = 0;i<spaceIndexes.size()+1;i++) {
            if (i==0){
                commands[i] = new String(strings,1,spaceIndexes.get(i)-2);
            } else {
                if (i==spaceIndexes.size()){
                    commands[i] = new String(strings, spaceIndexes.get(i-1)+1, strings.length-spaceIndexes.get(i-1)-1);
                } else {
                    commands[i] = new String(strings,
                            spaceIndexes.get(i-1)+1,
                            spaceIndexes.get(i)-1-spaceIndexes.get(i-1));
                    commands[i] = commands[i].replaceAll("\"", "");
                }
            }
        }
        return commands;
    }

    int create(File file, boolean force){
        try {
            if (file.exists()){
                if (force){
                    if ((remove(file,true)>0)){
                        return 4;
                    }
                } else {
                    return 5;
                }
            }

            if (file.isDirectory()){
                return file.mkdir() ? 0 : 1;
            } else
                return file.createNewFile() ? -1 : 2;
        } catch (IOException e) {
            if (professionalMode){
                e.printStackTrace();
            }
            return 3;
        }
    }

    int remove(File file, boolean loop){
        if (!file.exists()){
            return 6;
        } else {
            if (file.isDirectory()){
                if (file.listFiles()!=null){
                    if (!loop){
                        return 7;
                    } else {
                        boolean errorWithLoop = false;
                        File[] files = file.listFiles();
                        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                            if (remove(files[i],true)>0)
                                errorWithLoop = true;
                        }
                        return errorWithLoop ? 8 : -2;
                    }
                } else {
                    return file.delete() ? -3 : 9;
                }
            } else {
                return file.delete() ? -4 : 10;
            }
        }
    }

    int read(File file){
        if (!file.exists()){
            return 6;
        }

        if (file.isDirectory()){
            System.out.println(readDir(file));
            return -5;
        }

        try {
            System.out.println("\n");
            Files.lines(file.toPath())
                    .forEach(System.out::println);
            System.out.println("\n");
            return -5;
        } catch (IOException e) {
            if (professionalMode)
                e.printStackTrace();
            return 11;
        }
    }

    String readDir(File file){
        if (file.listFiles()==null){
            return "<EMPTY>";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .forEach((f)->stringBuilder
                        .append(f.isDirectory() ? "<DIR>" : "<FILE>")
                        .append(f.getName())
                        .append("\n"));
        return stringBuilder.toString();
    }

    int write(File file, String str, boolean clear){
        if (file.isDirectory()){
            return 12;
        }

        if (!file.exists()){
            int n = create(file,false);
            if (n>0){
                return n;
            }
        }

        try {
            Files.write(file.toPath(),str.getBytes(), !clear ? StandardOpenOption.APPEND : null);
            return -6;
        } catch (IOException e) {
            if (professionalMode)
                e.printStackTrace();
            return 13;
        }
    }


    String getAnswer(int code) {
        switch (code) {
            case 0:
                return "Dir has been created\n";
            case -1:
                return "File has been created\n";
            case -2:
                return "Directory with files has been deleted";
            case -3:
                return "Directory has been deleted\n";
            case -4:
                return "File has been deleted\n";
            case -5:
                return "\n";
            case -6:
                return "The line has been written\n";
            case 1:
                return "Can not create the directory\n";
            case 2:
                return "Can not create the file\n";
            case 3:
                return "Error with creating\n";
            case 4:
                return "Can not recreate the filed\n";
            case 5:
                return "File is already exist\n";
            case 6:
                return "File does not exist\n";
            case 7:
                return "Directory is not empty";
            case 8:
                return "Some files has not been removed";
            case 9:
                return "Can not remove the directory";
            case 10:
                return "Can not remove the file";
            case 11:
                return "Error with reading ";
            case 12:
                return "This is not a file";
            case 13:
                return "Error with writing";
            default:
                return "Error";
        }
    }
}
