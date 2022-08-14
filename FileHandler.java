import java.io.*;
import java.util.LinkedList;

public class FileHandler {

    public LinkedList<Member> readFile(){
        LinkedList<Member> m = new LinkedList<>();
        String lineRead;
        String[] lineSplit;
        Member mem;

        try(BufferedReader reader = new BufferedReader(new FileReader("members.csv"))){
            lineRead =reader.readLine();
            while(lineRead != null){
                lineSplit = lineRead.split(", ");
                if(lineSplit[0].equals("S")){
                    mem = new SingleClubMember('S',Integer.parseInt(lineSplit[1]),lineSplit[2],Double.parseDouble(lineSplit[3]),Integer.parseInt(lineSplit[4]));
                }else{
                    mem = new SingleClubMember('M',Integer.parseInt(lineSplit[1]),lineSplit[2],Double.parseDouble(lineSplit[3]),Integer.parseInt(lineSplit[4]));
                }
                m.add(mem);
                lineRead = reader.readLine();
            }
            }catch(IOException e){
                System.out.println(e.getMessage());
            }

        return m;

    }

    public void appendFile(String mem){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv",true))) {
            writer.write(mem+"\n");
//            writer.newLine();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void overWriteFile(LinkedList<Member> m){
        String s;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp",false))) {
            for(int i=0;i<m.size();i++){
                s =m.get(i).toString();
                writer.write(s + "\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        try{

            File f = new File("members.csv");
            File tf = new File("members.temp");
            f.delete();
            tf.renameTo(f);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


}
