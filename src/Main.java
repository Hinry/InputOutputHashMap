/**
 * Created by mrhri on 20.11.2016.
 */
import java.io.*;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {
    static HashMap<Integer, String> map1 = new HashMap<>();
    static HashMap<Integer, String> map2 = new HashMap<>();
    static HashMap<Integer, String> map3 = new HashMap<>();

    public static void main(String[] args) throws IOException {

        map1.put(1, "Samsung");
        map1.put(2, "Accer");
        map1.put(3, "Dell");

        map2.put(1, "Hewlett Packard");
        map2.put(2, "Lenovo");
        map2.put(4, "Apple");
        map2.put(5, "Asus");
        map2.put(6, "Toshiba");

        File f = new File("C:\\Users\\mrhri\\Desktop\\file1.txt");
        File f2 = new File("C:\\Users\\mrhri\\Desktop\\file2.txt");
        File f3 = new File("C:\\Users\\mrhri\\Desktop\\file3.txt");

        fwrite(f2, map2);
        fwrite(f,map1);
        ReadAndWrite(f, f2, f3);


    }
    private static void close(Closeable out) {
        if(out!=null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fwrite(File f, Map m ){

        OutputStream out  = null;
        StringBuilder ar1  = new StringBuilder();
        for(Object a : m.entrySet()){
            ar1.append(a+"\r\n");
        }
        try {
            if(!f.exists()) {
                f.createNewFile();

            }
            out  = new FileOutputStream(f, true) ;
            out.write( ar1.toString().getBytes() );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(out);
        }

    }
    public static void ReadAndWrite(File f1, File f2, File f3) throws IOException {

        try {
            if(!f3.exists()) {
                f3.createNewFile();

            }
            OutputStream out = null;
            InputStream in = null;
            InputStream in2 = null;
            in  = new FileInputStream(f1);
            in2  = new FileInputStream(f2);

            byte[] buf = new byte[in.available()];
            byte[] buf2 = new byte[in2.available()];

            int readed = in.read(buf);
            int readed2 = in2.read(buf2);

            StringBuilder sb = new StringBuilder();

            while ( readed > 0){
                sb.append(new String(buf,0,readed));
                readed = in.read(buf);
            }
            StringBuilder sb2 = new StringBuilder();
            while(readed2 > 0){
                sb2.append(new String(buf2,0,readed2));
                readed2 = in2.read(buf2);
            }

            System.out.println(sb.toString());
            System.out.println(sb2.toString());

            out  = new FileOutputStream(f3, true) ;
            // записываем из буфера в файл
            out.write(have1ButNotHave2().getBytes());

            System.out.println("Запись в третий файл: "+have1ButNotHave2().toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            in.close();
            out.close();
        }

    }
    public static String have1ButNotHave2(){
        Set<Integer> str1 = new TreeSet<>();

        Set<Integer> keys1 = map1.keySet();
        for(int a : keys1){
            if(!map2.containsKey(a)){
                str1.add(a);
            }
        }

        Set<Map.Entry<Integer, String>> set2 = map2.entrySet();
        for(int a : str1){
            if(!set2.contains(a)){
                map3.put( a, map1.get(a).toString() );
            }
        }
        StringBuilder ar1  = new StringBuilder();
        for(Object a : map3.entrySet()){
            ar1.append(a+"\r\n");
        }

        return ar1.toString();
    }
}
