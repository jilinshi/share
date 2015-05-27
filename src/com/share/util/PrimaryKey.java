package com.share.util;
/**  
 * 字符串主键自增，条件当传进来的Str是空时，自动创建初始化主键  
 * 后面的依次自增  
 * @author lizhenbin  
 *  程序实现字符串主键自增，例如：“SN000001”，希望下一条数据是“SN000002”，
 *  所以需要字符串主键自增，所以需要传入上一次的主键的字符串，通过调用工具类的方法可以获取下一条记录。
 *  
 */  
public class PrimaryKey {
	       
	    // 数字串最小值   
	    private static final char MIN_DATA = '0';   
	    // 数字串最大值   
	    private static final char MAX_DATA = '9';   
	    // 数字串默认从1开始   
	    private static final char START_DATA = '1';    
	    // 主键字符串头部   
	    private static String keyHead;   
	    // 字符串数字位数，不足补0   
	    private static Integer keySize = 0;   
	    // 是否允许数字最大之后自增，默认false   
	    private static boolean keyInc = false;
	    // 字符串数字位数
	    private static String maxvalue;
	    // 程序执行开始系统时间   
	    private static Long startExecute = 0L;   
	    // 程序执行结束系统时间   
	    private static Long finishExecute = 0L;   
	    
	    /**  
	     * 初始化主键字符串格式，默认达到KeySize后不可自增  
	     * @param keyHead 字符串开头部分  
	     * @param keySize 字符串数组长度  
	     */  
	    public PrimaryKey(String prikey, String maxvalue) {   
	        super();   
	        /**  
	         * 设置不可自增  
	         */
	        int prikey_len = prikey.trim().length();
	        Integer keySize=2;
	        if(prikey_len==2){
	        	this.keySize = keySize;   
	        }else if(prikey_len==4){
	        	this.keySize = keySize;
	        }else if(prikey_len==6){
	        	this.keySize = keySize+1;
	        }else if(prikey_len==8||prikey_len==9){
	        	this.keySize = keySize+1;
	        }else{
	        	System.out.println("不符合规则");
	        }
	        if(this.checkValue(maxvalue)){
	        	this.maxvalue = maxvalue;
	        }
	        if(this.checkHead(prikey)){   
	            this.keyHead = prikey;   
	    	}
	    }    
	       
	    /**  
	     * 返回下一个字符串  
	     * @param currentKey 当前主键  
	     * @return 正常：下一个主键值 = 当前主键 + 1;  
	     *         当字符串数字达到KeySize的最大数时  
	     *         KeyInc为true时， 下一个主键字符串返回最大数 + 1  
	     *         KeyInc为false时， 返回空值  
	     */  
	    private static synchronized String nextKey(String prikey, String mvalue) {   
	        
	    	//初始化
	    	int prikey_len = prikey.trim().length();
	        Integer Size=2;
	        if(prikey_len==2){
	        	keySize = Size;   
	        }else if(prikey_len==4){
	        	keySize = Size;
	        }else if(prikey_len==6){
	        	keySize = Size+1;
	        }else if(prikey_len==8||prikey_len==9){
	        	keySize = Size+1;
	        }else{
	        	System.out.println("不符合规则");
	        	return null;
	        }
	        if(checkValue(mvalue)){
	        	maxvalue = mvalue;
	        }else{
	        	return null;
	        }
	        if(checkHead(prikey)){   
	            keyHead = prikey;   
	    	}else{
	    		return null;
	    	}
	        
	        // 记录开始执行程序系统时间   
	        startExecute = System.currentTimeMillis();   
	        try {   
	            /**  
	             * 去掉首尾空字符  
	             */  
	        	maxvalue = maxvalue.trim();   
	            if(!check(maxvalue)) {   
	                System.out.println(maxvalue+" : 不符合规则！"); 
	                return null;   
	            }   
	            StringBuilder sb = new StringBuilder();   
	            sb.append(keyHead);   
	            int charIndex = 0;   
	            for(int i = 0; i < maxvalue.length(); i++) {   
	                char symbol = maxvalue.charAt(i);   
	                if(symbol >= MIN_DATA && symbol <= MAX_DATA) {   
	                    charIndex = i;   
	                    break;   
	                }   
	            }   
	            String dataStr = maxvalue.substring(charIndex, maxvalue.length());   
	            Long dataNum = Long.valueOf(dataStr);   
	            dataNum++;   
	            if(dataNum < splitDataPosition()) {   
	                for(int i = 0; i <= keySize - String.valueOf(dataNum).length() - 1; i++) {   
	                    sb.append(MIN_DATA);   
	                }   
	                sb.append(dataNum);   
	            }else if(dataNum >= splitDataPosition() &&    
	                    dataNum < maxDateNumber()) {   
	                sb.append(dataNum);   
	            }else{   
	                // 超过大小最大数时   
	                if(keyInc) {   
	                    sb.append(dataNum);   
	                }else{   
	                    // 允许自增标志位false的时候返回空值   
	                    return null;   
	                }   
	            }   
	            return sb.toString();   
	        } catch (Exception e) {   
	            System.out.println(e.toString());   
	            return null;   
	        } finally {   
	            finishExecute = System.currentTimeMillis();   
//	          System.out.println(PrimaryKey.class.getSimpleName() + " nextKey() Execute: "   
//	                  + (this.finishExecute - this.startExecute) +"ms.");   
	        }   
	    }   
	       
	    /**  
	     * 获取需要补零的最大数字  
	     * @return  
	     */  
	    private static Long splitDataPosition() {   
	        StringBuilder sb = new StringBuilder();   
	        sb.append(START_DATA);   
	        for(int i = 0; i < keySize - 1; i++) {   
	            sb.append(MIN_DATA);   
	        }   
	        return Long.valueOf(sb.toString());   
	    }   
	       
	    /**  
	     * 获取最大数  
	     * @return  
	     */  
	    private static Long maxDateNumber() {   
	        StringBuilder sb = new StringBuilder();   
	        for(int i = 0; i < keySize; i++) {   
	            sb.append(MAX_DATA);   
	        }   
	        return Long.valueOf(sb.toString());   
	    }   
	       
	    /**  
	     * 简单的验证空值  
	     * @param key  
	     * @return  
	     * @throws Exception   
	     */  
	    private static boolean check(String key) throws Exception { 
	        try {   
	            // 空值验证   
	            if(key == null || key.equals(""))    
	                return false;   
	            // key字符串长度验证   
	            if(key.length() > keySize)    
	                return false;   
	        } catch (Exception e) {    
	            throw e;   
	        }
	    	return true;
	    }   
	       
	    /**  
	     * 验证输入的KeyHead，条件全部要求是数字 
	     * @param keyHead  
	     * @return  
	     */  
	    private static synchronized boolean checkHead(String keyHead) {   
	        if(keyHead != null && !keyHead.equals("")) {   
	            for(int i = 0; i < keyHead.length(); i++) {
	                char symbol = keyHead.charAt(i);   
	                if(symbol >= MIN_DATA && symbol <= MAX_DATA) {  
	                    return true;   
	                }else{
	                	return false;
	                }
	            }    
	        }
	        return false;   
	    }
	    
	    private static synchronized boolean checkValue(String keyValue) {   
	        if(keyValue != null && !keyValue.equals("")) { 
	        	if(keyValue.length()!=keySize){
	        		System.out.println(keyValue+" : 长度不符合规则！"); 
	        		return false;
	        	}else{
		            for( int i = 0; i < keyValue.length(); i++ ) {   
		                char symbol = keyValue.charAt(i);   
		                if(symbol >= MIN_DATA && symbol <= MAX_DATA) {  
		                    return true;
		                }else{
		                	return false;
		                }
		            }
	        	}
	        }
	        return false;   
	    }
	    
	    
	    public static void main(String[] args) {
			String str1 = PrimaryKey.nextKey("212102", "0010");
			System.out.println(str1);

		}
}
