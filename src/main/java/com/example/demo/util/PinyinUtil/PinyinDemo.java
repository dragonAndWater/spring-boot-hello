package com.example.demo.util.PinyinUtil;

public class PinyinDemo {
    public static void main(String[] args) {
        String name = "北京有才科技有限公司";

//        ------------获取中文全拼
        String s = Pinyin4JUtil.cn2Spell(name);
        System.out.println("获取中文全拼："+s);

//        ------------获取中文首字母拼音大写
        s = ChineseCharacterUtil.getUpperCase(name,false);
        System.out.println("获取中文首字母拼音大写："+s);
        s = ChineseCharacterUtil.getUpperCase(name,true);
        System.out.println("获取中文全拼音大写："+s);

    }
}
