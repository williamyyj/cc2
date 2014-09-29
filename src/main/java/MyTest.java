
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.cc2.CCMap;
import org.cc2.util.CCUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
public class MyTest {
    
    public static void main(String[] args){
        Map<String, Object> map = Stream.of( "1:one","2:two" )
            .collect( Collectors.toMap( k -> k.split(":")[0], v -> v.split(":")[1] ) );
        System.out.println(map);
        CCMap row1 = new CCMap(){ { put("id",101);  put("name","name1") ; put("value" ,33344 ) ; }} ; 
        CCMap row2 = new CCMap(){ { put("id",102);  put("name","name2") ; put("value" ,33399 ) ; }} ;
        CCMap row3 = new CCMap(){ { put("id",103);  put("name","name3") ; put("value" ,33333 ) ; }} ;
        
        List<Map> list = Arrays.asList(row1,row2,row3);
        Map<String,Object> m = new CCMap(){{ put("101",row1);  put("102",row2); put("103",row3);}};
        Map ret = CCUtils.get(list, "id", 102);
        System.out.println(ret);
        List<Object> ret1 = CCUtils.asList( m, "101","102");
        System.out.println(ret1);
        
        
        List<Integer> list1 = Stream.of(1,2,3,4,5).collect(Collectors.toList());
        
        long ret2 = list1.stream()
            .reduce(0, (a,b)-> a+b).longValue();
 
        
        System.out.println(ret2);
        
    }
    
}
