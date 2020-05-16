import com.emc.nw.BloomFilter;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter();

        List<String> dictionary = populateData();
        for (String str : dictionary) {
            bloomFilter.add(str);
        }

        System.out.println(bloomFilter.contains("youtube.com"));    //not available in data file
        System.out.println(bloomFilter.contains("gmail.com"));      //available in data file
        System.out.println(bloomFilter.contains("qq.com"));         //not available in data file
        System.out.println(bloomFilter.contains("baidu.com"));      //not available in data file
        System.out.println(bloomFilter.contains("sohu.com"));       //not available in data file
        System.out.println(bloomFilter.contains("facebook.com"));   //not available in data file
        System.out.println(bloomFilter.contains("weibo.com"));      // available in data file
        System.out.println(bloomFilter.contains("jd.com"));         //not available in data file
        System.out.println(bloomFilter.contains("wikipedia.org"));     //not available in data file

//        Console output - false,true,false,false,false,false,true,false,false

    }

    /**
     * I have populated data.csv which contains more than 80,000 words.
     */
    private static List<String> populateData() {
        List<String> dictionary = new ArrayList<>();
        URL fileName = Test.class.getResource("data.csv");
        String file = fileName.getFile().replaceFirst("/", ""); //pre-processing
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            stream.forEach(word -> dictionary.add(word));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}
