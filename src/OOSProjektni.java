import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public class OOSProjektni {
    private static File currentPath;
    private static final List<String> commands = Arrays.asList(new String[]{"login", "where", "go", "create", "list", "print", "findDat", "find"});
    public static  void where(){
        System.out.println(currentPath.getPath());
    }
    public static void go(String name){
        if(name.equals("..")) {
            if (currentPath.getParentFile() != null)
                currentPath = currentPath.getParentFile();
        }
        else{
            File temp = new File(name.startsWith("/")? name : currentPath+File.separator+name);
            currentPath = temp.exists() ? temp : currentPath;
            if(!temp.exists()) System.out.println("error: command go: "+name+" is not a directory");
        }
    }
    public static void create(String name) throws IOException, ExecutionException, InterruptedException {
        boolean isMkdir = name.startsWith("-d");
        if(isMkdir) name= name.substring(3);
        Process process = Runtime.getRuntime().exec(isMkdir ? "mkdir "+name: "touch "+name, null, name.startsWith("/") ? null : currentPath).onExit().get();
        Scanner errorScanner = new Scanner(process.getErrorStream());
        if(errorScanner.hasNextLine()){
            String output = errorScanner.nextLine();
            System.out.println(output.contains("mkdir") ? output.replace("mkdir", "create -d") : output.replace("touch", "create"));
        }
        errorScanner.close();
    }
    public static void listTree(String name) throws IOException, ExecutionException, InterruptedException {
        Path start = name.startsWith("/") ? Paths.get(name) : Paths.get(currentPath+File.separator+name);
        final int[] depth = {0};
        Files.walkFileTree(start, new SimpleFileVisitor<Path>(){
            private void depthLen(int depth){
                int i =-1;
                while(++i<depth) System.out.print("   ");
            }
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                depthLen(depth[0]);
                System.out.println(dir.getFileName());
                depth[0]++;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                depthLen(depth[0]);
                System.out.println(file.getFileName());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                depth[0]--;
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("command list: unable to open directory "+exc.getMessage());
                return FileVisitResult.TERMINATE;
            }
        });

    }
    public static void find(String text, String file){
        try { //NoSuchFileExc is subset of IOException
            int lineNumber = Files.readAllLines(Paths.get(currentPath+ File.separator + file)).indexOf(text);
            System.out.println(lineNumber == -1 ? "text "+text+" does not exist in the file "+file : "Number of line where first occurrence of the \""+text+"\" is found is: "+(lineNumber+1));
        }
        catch(IOException exception){
            System.out.println("File "+file+" does not exist");
        }
    }
    public static  void findFile(String file, String path) throws IOException, ExecutionException, InterruptedException {
        File fileReference = new File(path);
        if(fileReference.isDirectory()){
            Process process = Runtime.getRuntime().exec("find "+path+" -name "+file).onExit().get();
            Scanner scanner = new Scanner(process.getInputStream());
            if(!scanner.hasNextLine())
                System.out.println("error: file "+file+" does not exist in "+path);
            else
                while (scanner.hasNextLine())
                    System.out.println(scanner.nextLine());
            scanner.close();
        }
        else
            System.out.println("error: command findDat: no such directory");
    }
    public static boolean login(String username , String password) {
        try {
            return Files.readAllLines(Paths.get("userDatabase")).contains(username+" "+password);
        }
        catch (IOException exception){
            System.out.println(exception.fillInStackTrace());
            return false;
        }
    }
    public static void forcefullyDelete(String dir){
        try {
            Runtime.getRuntime().exec("rm -rf " + dir).onExit().get();
        }
        catch (IOException | InterruptedException | ExecutionException exception){
            System.out.println("Deletion unsuccessful "+exception.getMessage());
        }
    }
    public static void print(String file){
        try{
            if(file.endsWith(".txt"))
                System.out.println(Files.readString(Paths.get(currentPath + File.separator + file)));
            else System.out.println("error: command print: parameter must be textual file");
        }
        catch (IOException ioException){
            System.out.println("error: file not found. "+ioException.getMessage());
        }
    }
    public static void main(String[] args) throws Exception {
        if (System.getProperty("os.name").contains("nux") || System.getProperty("os.name").contains("nix")) {
            Scanner scanner = new Scanner(System.in);
            String input;
            do {
                System.out.print("OOS:~$ ");
                input = scanner.nextLine();
                if (input.matches("login [\\w]+ [\\w]+")) {
                    if (login(input.split(" ")[1], input.split(" ")[2])) {
                        System.out.println("Login successful");
                        String username = input.split(" ")[1];
                        currentPath = new File(System.getProperty("user.home") + File.separator + username);
                        if(!currentPath.exists() && !currentPath.mkdir()) {
                            System.out.println("error: user directory could not be created");
                            return;
                        }
                        do {
                            System.out.print("OOS@" + username + ":~$ ");
                            input = scanner.nextLine();
                            if (input.trim().equals("where"))
                                where();
                            else if (input.trim().matches("(go (/?[\\w]+/?)+)|(go \\.\\.)|go"))
                                go(input.trim().equals("go") ? System.getProperty("user.home") + File.separator + username : input.split(" ")[1]);
                            else if (input.trim().matches("create (-d )?(\\/?[\\w\\.?]+\\/?)+"))
                                create(input.substring(6, input.length()).trim());
                            else if (input.trim().matches("(list (\\/?[\\w]+\\/?)+)|list"))
                                listTree(input.trim().equals("list") ? "" : input.split(" ")[1]);
                            else if (input.trim().matches("print (\\/?[\\w\\.]+\\/?)+"))
                                print(input.split(" ")[1]);
                            else if (input.trim().matches("find \"[^\"]*\" [\\w\\.?]+"))
                                find(input.split(" ")[1].substring(1, input.split(" ")[1].length() - 1), input.split(" ")[2]);
                            else if (input.trim().matches("findDat [\\w]+(\\.[a-z]+)? (\\/?[\\w.,]+\\/?)+"))
                                findFile(input.split(" ")[1], input.split(" ")[2]);
                            else {
                                String finalInput = input, localTemp = commands
                                        .stream()
                                        .filter(item -> finalInput.startsWith(item+" ")||finalInput.equals(item))
                                        .findFirst()
                                        .orElse(null);
                                if (localTemp != null)
                                    System.out.println("error: command " + localTemp + ": invalid usage");
                                else if (!input.equals("logout")) System.out.println(input.split(" ")[0]+": command not found");
                            }
                        } while (!input.equals("logout"));
                        currentPath = new File(System.getProperty("user.home") + File.separator + username);
                        if (!currentPath.delete())
                            forcefullyDelete(System.getProperty("user.home") + File.separator + username);
                        System.out.println("Logging out successful");
                    } else System.out.println("Invalid username or password.");
                } else if (input.startsWith("login "))
                    System.out.println("error: command login: invalid usage ");
                else if (!input.equals("exit")) System.out.println("error: login required");
            } while (!input.equals("exit"));
            scanner.close();
        }
        else System.out.println("OOS console supports only Linux operating system");
    }
}
