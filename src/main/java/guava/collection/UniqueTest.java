package guava.collection;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/6/21
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class UniqueTest {
    public static void main(String[] args) throws IOException {
        List<String> lines =  Files.readLines(new File("D:\\111.txt"), Charsets.UTF_8);
        Set<String> set = new HashSet<>();
        for(String line : lines){
            int index = line.indexOf("agreementId=");
            int end = line.indexOf("&serviceCode=");
            String aggr = line.substring(index+12, end);
            if(!set.add(aggr)){
                System.out.println(aggr);
            }
        }
    }
}
