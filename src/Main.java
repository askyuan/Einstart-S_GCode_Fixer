import java.io.*;

public class Main{


    public static void main(String[] args) {
        String path;
        path = args [0];
        String gcode = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                gcode = sb.toString();
            } finally {
                br.close();
            }
        }
        catch (IOException e) {e.printStackTrace();
        }
        gcode = gcode.replace("Z-", "Z");
        gcode = gcode.replace("M84", "");
        gcode = gcode.replace("G28 X0", "G28");
        System.out.println(gcode);
        BufferedWriter writer = null;
        try {
            //create a temporary file
            File logFile = new File(path);

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(gcode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }

    }
}
