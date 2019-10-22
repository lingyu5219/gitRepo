
package com.center.utils;

import java.util.UUID;

/**
* ClassName: UuidUti <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月19日 下午10:48:27 <br/>
*
* @author Tony
* @version 
*/

public class UuidUtil {

	public static String getUUID() {  
        return UUID.randomUUID().toString().replace("-", "");  
    }
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {  
            System.out.println("ss[" + i + "]=====" + getUUID());  
        }
	}

}

