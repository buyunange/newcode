package com.jbj.service;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class SensitiveService implements InitializingBean {
    private  static  final Logger logger = LoggerFactory.getLogger(SensitiveService.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            String lineTxt;
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while((lineTxt =  bufferedReader.readLine())!=null){
                addWord(lineTxt);
            }
            reader.close();
        }catch (Exception e){
            logger.error("读取文件失败"+e.getMessage());
        }

    }

    public String fileter(String text){
        if(StringUtils.isBlank(text)){
            return text;
        }
        StringBuilder result = new StringBuilder();
        String repacement = "***";
        TrieNode tempNode = rootNode;
        int begin = 0;
        int position = 0;
        while(position<text.length()){
            char c = text.charAt(position);
            // 空格直接跳过
            if (isSymbol(c)) {
                if (tempNode == rootNode) {
                    result.append(c);
                    ++begin;
                }
                ++position;
                continue;
            }
            tempNode = tempNode.getSubNode(c);
            if(tempNode == null){
                result.append(text.charAt(begin));
                position = begin + 1;
                begin = position;
                tempNode = rootNode;
            }else if(tempNode.isKeywordEnd()){
                //发现敏感词
                result.append(repacement);
                position = position + 1 ;
                begin = position;
                tempNode = rootNode;
            }else{
                ++position;
            }
        }
        return result.toString();
    }

    private void addWord(String lineTxt){

        TrieNode tempNode = rootNode;
        for (int i = 0; i < lineTxt.length(); i++) {
            Character c = lineTxt.charAt(i);
            TrieNode node = tempNode.getSubNode(c);
            if(node == null){
                node = new TrieNode();
                tempNode.addSubNode(c,node);
            }
            tempNode = node;
            if(i == lineTxt.length()-1){
                tempNode.setKeywordEnd(true);
            }
        }
    }

    private  class TrieNode{
        /**
         * true 关键词的终结 ； false 继续
         */
        private boolean end = false;
        /**
         * key下一个字符，value是对应的节点
         */
        private Map<Character,TrieNode> subNodes = new HashMap<>();
        /**
         * 向指定位置添加节点树
         */
        void addSubNode(Character key,TrieNode node){
            subNodes.put(key,node);
        }

        /**
         * 获取下个节点
         */
        TrieNode getSubNode(Character key){
            return  subNodes.get(key);
        }
        boolean isKeywordEnd() {
            return end;
        }

        void setKeywordEnd(boolean end) {
            this.end = end;
        }

        public int getSubNodeCount() {
            return subNodes.size();
        }

    }
    /**
     * 根节点
     */
    private TrieNode rootNode = new TrieNode();

    //过滤符号 空格
    private boolean isSymbol(char c){
        int ic = (int)c;
        //东亚文字 0x2E80-0x9FFF
        return !CharUtils.isAsciiAlphanumeric(c)&&(ic< 0x2E80||ic>0x9FFF);
    }

    public static void main(String[] args) {
        SensitiveService sensitiveService = new SensitiveService();
        sensitiveService.addWord("色情");
        sensitiveService.addWord("赌博");
        System.out.println(sensitiveService.fileter("￥你好 嗯恩  色 ￥情afd"));
    }
}
