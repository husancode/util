package Test;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.UndoableEditListener;

import com.google.common.collect.Lists;

public class Test11 {
	
	private static List<String> pageBy(List<String> cameras, int pageStart, int pageSize){
        int size = cameras.size();
        if(pageStart >= size || pageStart < 0 || pageSize <= 0){
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<String>();
        for(int i=pageStart,y=1; i<size && y<=pageSize; i++,y++){
            result.add(cameras.get(i));
        }
        return result;
    }
	
	   public static String toBlankString(String cardPwd){
	        return cardPwd.substring(0, 4)+" "+cardPwd.substring(4, 8)+" "+cardPwd.substring(8, 12)+" "+cardPwd.substring(12);
	    }
	    
	    public static void main(String[] args) {
	       System.out.println();
	    }
	
    
	
}
