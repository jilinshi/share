package com.share.util;
/**  
 * �ַ���������������������������Str�ǿ�ʱ���Զ�������ʼ������  
 * �������������  
 * @author lizhenbin  
 *  ����ʵ���ַ����������������磺��SN000001����ϣ����һ�������ǡ�SN000002����
 *  ������Ҫ�ַ�������������������Ҫ������һ�ε��������ַ�����ͨ�����ù�����ķ������Ի�ȡ��һ����¼��
 *  
 */  
public class PrimaryKey {
	       
	    // ���ִ���Сֵ   
	    private static final char MIN_DATA = '0';   
	    // ���ִ����ֵ   
	    private static final char MAX_DATA = '9';   
	    // ���ִ�Ĭ�ϴ�1��ʼ   
	    private static final char START_DATA = '1';    
	    // �����ַ���ͷ��   
	    private static String keyHead;   
	    // �ַ�������λ�������㲹0   
	    private static Integer keySize = 0;   
	    // �Ƿ������������֮��������Ĭ��false   
	    private static boolean keyInc = false;
	    // �ַ�������λ��
	    private static String maxvalue;
	    // ����ִ�п�ʼϵͳʱ��   
	    private static Long startExecute = 0L;   
	    // ����ִ�н���ϵͳʱ��   
	    private static Long finishExecute = 0L;   
	    
	    /**  
	     * ��ʼ�������ַ�����ʽ��Ĭ�ϴﵽKeySize�󲻿�����  
	     * @param keyHead �ַ�����ͷ����  
	     * @param keySize �ַ������鳤��  
	     */  
	    public PrimaryKey(String prikey, String maxvalue) {   
	        super();   
	        /**  
	         * ���ò�������  
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
	        	System.out.println("�����Ϲ���");
	        }
	        if(this.checkValue(maxvalue)){
	        	this.maxvalue = maxvalue;
	        }
	        if(this.checkHead(prikey)){   
	            this.keyHead = prikey;   
	    	}
	    }    
	       
	    /**  
	     * ������һ���ַ���  
	     * @param currentKey ��ǰ����  
	     * @return ��������һ������ֵ = ��ǰ���� + 1;  
	     *         ���ַ������ִﵽKeySize�������ʱ  
	     *         KeyIncΪtrueʱ�� ��һ�������ַ������������ + 1  
	     *         KeyIncΪfalseʱ�� ���ؿ�ֵ  
	     */  
	    private static synchronized String nextKey(String prikey, String mvalue) {   
	        
	    	//��ʼ��
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
	        	System.out.println("�����Ϲ���");
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
	        
	        // ��¼��ʼִ�г���ϵͳʱ��   
	        startExecute = System.currentTimeMillis();   
	        try {   
	            /**  
	             * ȥ����β���ַ�  
	             */  
	        	maxvalue = maxvalue.trim();   
	            if(!check(maxvalue)) {   
	                System.out.println(maxvalue+" : �����Ϲ���"); 
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
	                // ������С�����ʱ   
	                if(keyInc) {   
	                    sb.append(dataNum);   
	                }else{   
	                    // ����������־λfalse��ʱ�򷵻ؿ�ֵ   
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
	     * ��ȡ��Ҫ������������  
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
	     * ��ȡ�����  
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
	     * �򵥵���֤��ֵ  
	     * @param key  
	     * @return  
	     * @throws Exception   
	     */  
	    private static boolean check(String key) throws Exception { 
	        try {   
	            // ��ֵ��֤   
	            if(key == null || key.equals(""))    
	                return false;   
	            // key�ַ���������֤   
	            if(key.length() > keySize)    
	                return false;   
	        } catch (Exception e) {    
	            throw e;   
	        }
	    	return true;
	    }   
	       
	    /**  
	     * ��֤�����KeyHead������ȫ��Ҫ�������� 
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
	        		System.out.println(keyValue+" : ���Ȳ����Ϲ���"); 
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
