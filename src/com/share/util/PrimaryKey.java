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
	    // Ĭ�ϳ���   
	    private static final int DEFAULT_SIZE = 8;   
	    // KeySize�������   
	    // Long����󳤶���19λ����ֹ���   
	    private static final int MAX_KEYSIZE_VALUE = 18;   
	    // Ĭ���ַ���Head   
	    private static final String DEFAULT_HEAD = "KEY";   
	    // �����ַ���ͷ��   
	    private String keyHead;   
	    // �ַ�������λ�������㲹0   
	    private Integer keySize = 8;   
	    // �Ƿ������������֮��������Ĭ��false   
	    private boolean keyInc = false;   
	    // ����ִ�п�ʼϵͳʱ��   
	    private Long startExecute = 0L;   
	    // ����ִ�н���ϵͳʱ��   
	    private Long finishExecute = 0L;   
	       
	    /**  
	     * ��ʼ�������ַ�����ʽ��Ĭ�ϴﵽKeySize�󲻿�����  
	     * @param keyHead �ַ�����ͷ����  
	     * @param keySize �ַ������鳤��  
	     */  
	    public PrimaryKey(String keyHead, Integer keySize) {   
	        super();   
	        /**  
	         * ���ò�������  
	         */  
	        if(this.checkSize(keySize))   
	            this.keySize = keySize;   
	        else  
	            this.keySize = this.DEFAULT_SIZE;   
	        if(this.checkHead(keyHead))   
	            this.keyHead = keyHead;   
	        else  
	            this.keyHead = this.DEFAULT_HEAD;   
	    }   
	  
	    /**  
	     * ��ʼ�������ַ����ĸ�ʽ  
	     * @param keyHead �ַ�����ͷ����  
	     * @param keySize �ַ������鳤��  
	     * @param keyInc ��ֵ���ֵ֮���Ƿ���������  
	     */  
	    public PrimaryKey(String keyHead, Integer keySize, boolean keyInc) {   
	        super();   
	        if(this.checkSize(keySize))   
	            this.keySize = keySize;   
	        else  
	            this.keySize = this.DEFAULT_SIZE;   
	        if(this.checkHead(keyHead))   
	            this.keyHead = keyHead;   
	        else  
	            this.keyHead = this.DEFAULT_HEAD;   
	        this.keyInc = keyInc;   
	    }   
	       
	    /**  
	     * ������һ���ַ���  
	     * @param currentKey ��ǰ����  
	     * @return ��������һ������ֵ = ��ǰ���� + 1;  
	     *         ���ַ������ִﵽKeySize�������ʱ  
	     *         KeyIncΪtrueʱ�� ��һ�������ַ������������ + 1  
	     *         KeyIncΪfalseʱ�� ���ؿ�ֵ  
	     */  
	    public synchronized String nextKey(String currentKey) {   
	           
	        // ��¼��ʼִ�г���ϵͳʱ��   
	        this.startExecute = System.currentTimeMillis();   
	        try {   
	            /**  
	             * ȥ����β���ַ�  
	             */  
	            currentKey = currentKey.trim();   
	            if(!this.check(currentKey)) {   
	                System.out.println(PrimaryKey.class.getSimpleName() +    
	                        " Error: Input CurrentKey Str Type Illegal, Check '" + currentKey +"' is Right!");   
	                return null;   
	            }   
	            StringBuilder sb = new StringBuilder();   
	            sb.append(this.keyHead);   
	            int charIndex = 0;   
	            for(int i = 0; i < currentKey.length(); i++) {   
	                char symbol = currentKey.charAt(i);   
	                if(symbol >= this.MIN_DATA && symbol <= this.MAX_DATA) {   
	                    charIndex = i;   
	                    break;   
	                }   
	            }   
	            String dataStr = currentKey.substring(charIndex, currentKey.length());   
	            Long dataNum = Long.valueOf(dataStr);   
	            dataNum++;   
	            if(dataNum < this.splitDataPosition()) {   
	                for(int i = 0; i <= this.keySize - String.valueOf(dataNum).length() - 1; i++) {   
	                    sb.append(this.MIN_DATA);   
	                }   
	                sb.append(dataNum);   
	            }else if(dataNum >= this.splitDataPosition() &&    
	                    dataNum < this.maxDateNumber()) {   
	                sb.append(dataNum);   
	            }else{   
	                // ������С�����ʱ   
	                if(this.keyInc) {   
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
	            this.finishExecute = System.currentTimeMillis();   
//	          System.out.println(PrimaryKey.class.getSimpleName() + " nextKey() Execute: "   
//	                  + (this.finishExecute - this.startExecute) +"ms.");   
	        }   
	    }   
	       
	    /**  
	     * ��ȡ��ʼ���ַ���  
	     * @return  
	     */  
	    public synchronized String initStartKey() {   
	        StringBuilder sb = new StringBuilder();   
	        sb.append(this.keyHead);   
	        for(int i = 0; i < this.keySize - 1; i++) {   
	            sb.append(this.MIN_DATA);   
	        }   
	        sb.append(this.START_DATA);   
	        return sb.toString();   
	    }   
	       
	    /**  
	     * ��ȡ��Ҫ������������  
	     * @return  
	     */  
	    private Long splitDataPosition() {   
	        StringBuilder sb = new StringBuilder();   
	        sb.append(this.START_DATA);   
	        for(int i = 0; i < this.keySize - 1; i++) {   
	            sb.append(this.MIN_DATA);   
	        }   
	        return Long.valueOf(sb.toString());   
	    }   
	       
	    /**  
	     * ��ȡ�����  
	     * @return  
	     */  
	    private Long maxDateNumber() {   
	        StringBuilder sb = new StringBuilder();   
	        for(int i = 0; i < this.keySize; i++) {   
	            sb.append(this.MAX_DATA);   
	        }   
	        return Long.valueOf(sb.toString());   
	    }   
	       
	    /**  
	     * �򵥵���֤��ֵ  
	     * @param key  
	     * @return  
	     * @throws Exception   
	     */  
	    private boolean check(String key) throws Exception {       
	        try {   
	            // ��ֵ��֤   
	            if(key == null || key.equals(""))    
	                return false;   
	            // key�ַ���������֤   
	            if(key.length() <= this.keyHead.length())    
	                return false;   
	            // �Ƿ���ϳ�ʼ������ͷ��֤   
	            String head = key.substring(0, this.keyHead.length()); 
	            if(!head.equals(this.keyHead))    
	                return false;   
	            /**  
	             * �����ֳ�����֤�����������������ʱ�򲻼��  
	             * ��������ﵽ�������ʱ��֤���ȺϷ���  
	             */  
	            String data = key.substring(this.keyHead.length(), key.length());   
	            if(data.length() != this.keySize && !this.keyInc)   
	                return false;   
	            // ��֤�Ƿ������ִ���ͨ��һ��ת������   
	            for(int i = 0; i < data.length(); i++) {   
	                char symbol = data.charAt(i);   
	                if(symbol > this.MAX_DATA || symbol < this.MIN_DATA) {   
	                    return false;   
	                }   
	            }   
	            return true;               
	        } catch (Exception e) {    
	            throw e;   
	        }   
	    }   
	       
	    /**  
	     * ��֤�����KeySize�Ϸ���  
	     * @param keySize  
	     * @return  
	     */  
	    private synchronized boolean checkSize(Integer keySize) {   
	        if(keySize != null && keySize > 0    
	                && keySize <= this.MAX_KEYSIZE_VALUE)   
	            return true;   
	        return false;   
	    }   
	       
	    /**  
	     * ��֤�����KeyHead������ȫ��Ҫ������ĸ  
	     * @param keyHead  
	     * @return  
	     */  
	    private synchronized boolean checkHead(String keyHead) {   
	        if(keyHead != null && !keyHead.equals("")) {   
	            for(int i = 0; i < keyHead.length(); i++) {   
	                char symbol = keyHead.charAt(i);   
	                if(symbol >= this.MIN_DATA && symbol <= this.MAX_DATA) {   
	                    return false;   
	                }   
	            }   
	            return true;   
	        }   
	        return true;   
	    }   
	    
	    
	    public static void main(String[] args) {
			PrimaryKey pk = new PrimaryKey("", 3);
			String str = "199";
			String str1 = pk.nextKey(str);
			System.out.println(str1);
			
			
		}
}
