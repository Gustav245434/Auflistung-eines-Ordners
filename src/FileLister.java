import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class FileLister {

    private static int einrueckungen = 0;

    public static void listFiles(File file) {
        if (file == null) {
            System.out.println(getEinrueckungen() + "File is NULL!");
            return;
        }
        if (file.isFile()) {
            System.out.println(getEinrueckungen() + listFile(file));
        } else {
            System.out.println(getEinrueckungen() + listDirectory(file));
            if(file.listFiles() == null) return;
            einrueckungen += 1;
            for(File fileIt : file.listFiles()) {
                listFiles(fileIt);
            }
            einrueckungen -= 1;
        }


    }

    private static String listFile(File file) {
        return file.getName() + " - " + LocalDateTime.ofEpochSecond(file.lastModified(), 0, ZoneOffset.UTC) + " - " + file.getTotalSpace() + "Byte";
    }

    private static String listDirectory(File file) {
        return "+" + file.getName();
    }

    private static String getEinrueckungen() {
        String einrueckungenString = "";
        for (int i = 0; i < einrueckungen; i++) {
            einrueckungenString += " ";
        }
        return einrueckungenString;
    }
}
