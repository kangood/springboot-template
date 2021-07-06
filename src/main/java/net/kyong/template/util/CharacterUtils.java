package net.kyong.template.util;

/**
 * 字符串对应工具类
 */
public class CharacterUtils {

    public static int getChineseFirst(String character) {
        for (int index = 0; index <= character.length() - 1; index++) {
            //将字符串拆开成单个的字符
            String w = character.substring(index, index + 1);
            if (w.compareTo("\u4e00") > 0 && w.compareTo("\u9fa5") < 0) {// \u4e00-\u9fa5 中文汉字的范围
                return index;
            }
        }
        return 0;
    }

    public static int getChineseLast(String character) {
        int lastIndex = 0;
        for (int index = 0; index <= character.length() - 1; index++) {
            //将字符串拆开成单个的字符
            String w = character.substring(index, index + 1);
            if (w.compareTo("\u4e00") > 0 && w.compareTo("\u9fa5") < 0) {// \u4e00-\u9fa5 中文汉字的范围
                lastIndex = index;
            }
        }
        return lastIndex;
    }

}
