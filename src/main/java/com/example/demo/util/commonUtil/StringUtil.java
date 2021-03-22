package com.example.demo.util.commonUtil;


import java.io.File;
import java.io.Reader;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;

public class StringUtil {
    private static final char CHAR_BLANK = ' ';
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String EMPTY = "";
    public static final String UNDER_LINE = "_";
    public static final String ENDL = "\n";
    public static final String SLASH = "/";
    public static final String BLANK = " ";
    public static final String DOT = ".";
    public static final String FILE_SEPARATOR;
    public static final int INDEX_NOT_FOUND = -1;
    static char[] hexDigits;
    private static final String ZERO = "000000000000000000000";
    public static final String[] EMPTY_STRING_ARRAY;

    public StringUtil() {
    }

    public static String[] StrList(String valStr) {
        int i = 0;
        String[] returnStr = new String[valStr.length() + 1 - valStr.replace(",", "").length()];

        for(valStr = valStr + ","; valStr.indexOf(44) > 0; ++i) {
            returnStr[i] = valStr.substring(0, valStr.indexOf(44));
            valStr = valStr.substring(valStr.indexOf(44) + 1, valStr.length());
        }

        return returnStr;
    }

    public static boolean isNotBlank(Object obj) {
        return obj != null && !"".equals(obj.toString());
    }

    public static boolean isBlank(Object obj) {
        return !isNotBlank(obj);
    }

    public static boolean isNotBlank(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static boolean isBlank(String str) {
        return !isNotBlank(str);
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }

    public static boolean isUpperCase(String s) {
        boolean result = true;

        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch < 'A' || ch > 'Z') {
                result = false;
            }
        }

        return result;
    }

    public static boolean isLowerCase(String s) {
        boolean result = true;

        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch < 'a' || ch > 'z') {
                result = false;
            }
        }

        return result;
    }

    public static String clob2String(Clob clob) {
        if (clob == null) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer(65535);
            Reader clobStream = null;

            try {
                clobStream = clob.getCharacterStream();
                char[] b = new char['\uea60'];
                boolean var4 = false;

                int i;
                while((i = clobStream.read(b)) != -1) {
                    sb.append(b, 0, i);
                }
            } catch (Exception var13) {
                sb = null;
            } finally {
                try {
                    if (clobStream != null) {
                        clobStream.close();
                    }
                } catch (Exception var12) {
                }

            }

            return sb == null ? null : sb.toString();
        }
    }

    public static String number2String(Number number) {
        return number == null ? "" : String.valueOf(number);
    }

    public static String getSubUtilSimple(String input, String left, String right) {
        String rgex = left + "(.*?)" + right;
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(input);
        return m.find() ? m.group(1) : "";
    }

    public static String camelCase(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && !name.isEmpty()) {
            if (!name.contains("_")) {
                return name.substring(0, 1).toUpperCase() + name.substring(1);
            } else {
                String[] camels = name.split("_");
                String[] var3 = camels;
                int var4 = camels.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    String camel = var3[var5];
                    if (!camel.isEmpty()) {
                        result.append(camel.substring(0, 1).toUpperCase());
                        result.append(camel.substring(1).toLowerCase());
                    }
                }

                return result.toString();
            }
        } else {
            return "";
        }
    }

    public static String uncapFirst(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    public static String capFirst(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String makeUpLength(String str, int len) {
        return "000000000000000000000".substring(0, len - str.length()) + str;
    }

    @SafeVarargs
    public static <T> String join(T... elements) {
        return join((Object[])elements, (String)null);
    }

    public static String join(Object[] array, String separator) {
        return array == null ? null : join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        } else {
            if (separator == null) {
                separator = "";
            }

            int noOfItems = endIndex - startIndex;
            if (noOfItems <= 0) {
                return "";
            } else {
                StringBuilder buf = new StringBuilder(noOfItems * 16);

                for(int i = startIndex; i < endIndex; ++i) {
                    if (i > startIndex) {
                        buf.append(separator);
                    }

                    if (array[i] != null) {
                        buf.append(array[i]);
                    }
                }

                return buf.toString();
            }
        }
    }

    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                return Objects.toString(first, "");
            } else {
                StringBuilder buf = new StringBuilder(256);
                if (first != null) {
                    buf.append(first);
                }

                while(iterator.hasNext()) {
                    if (separator != null) {
                        buf.append(separator);
                    }

                    Object obj = iterator.next();
                    if (obj != null) {
                        buf.append(obj);
                    }
                }

                return buf.toString();
            }
        }
    }

    public static String join(Iterable<?> iterable, String separator) {
        return iterable == null ? null : join(iterable.iterator(), separator);
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    public static String replace(String text, String searchString, String replacement, int max) {
        return replace(text, searchString, replacement, max, false);
    }

    private static String replace(String text, String searchString, String replacement, int max, boolean ignoreCase) {
        if (!isEmpty(text) && !isEmpty(searchString) && replacement != null && max != 0) {
            String searchText = text;
            if (ignoreCase) {
                searchText = text.toLowerCase();
                searchString = searchString.toLowerCase();
            }

            int start = 0;
            int end = searchText.indexOf(searchString, start);
            if (end == -1) {
                return text;
            } else {
                int replLength = searchString.length();
                int increase = replacement.length() - replLength;
                increase = increase < 0 ? 0 : increase;
                increase *= max < 0 ? 16 : (max > 64 ? 64 : max);

                StringBuilder buf;
                for(buf = new StringBuilder(text.length() + increase); end != -1; end = searchText.indexOf(searchString, start)) {
                    buf.append(text, start, end).append(replacement);
                    start = end + replLength;
                    --max;
                    if (max == 0) {
                        break;
                    }
                }

                buf.append(text, start, text.length());
                return buf.toString();
            }
        } else {
            return text;
        }
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static boolean checkValNotNull(Object object) {
        if (object instanceof CharSequence) {
            return isNotEmpty((CharSequence)object);
        } else {
            return object != null;
        }
    }

    public static boolean checkValNull(Object object) {
        return !checkValNotNull(object);
    }

    public static String format(String template, Object... values) {
        if (!CollectionUtil.isEmpty(values) && !isBlank(template)) {
            StringBuilder sb = new StringBuilder();
            int length = template.length();
            int valueIndex = 0;

            for(int i = 0; i < length; ++i) {
                if (valueIndex >= values.length) {
                    sb.append(sub(template, i, length));
                    break;
                }

                char currentChar = template.charAt(i);
                if (currentChar == '{') {
                    ++i;
                    char nextChar = template.charAt(i);
                    if (nextChar == '}') {
                        sb.append(values[valueIndex++]);
                    } else {
                        sb.append('{').append(nextChar);
                    }
                } else {
                    sb.append(currentChar);
                }
            }

            return sb.toString();
        } else {
            return template;
        }
    }

    public static String sub(String string, int fromIndex, int toIndex) {
        int len = string.length();
        if (fromIndex < 0) {
            fromIndex += len;
            if (fromIndex < 0) {
                fromIndex = 0;
            }
        } else if (fromIndex >= len) {
            fromIndex = len - 1;
        }

        if (toIndex < 0) {
            toIndex += len;
            if (toIndex < 0) {
                toIndex = len;
            }
        } else if (toIndex > len) {
            toIndex = len;
        }

        if (toIndex < fromIndex) {
            int tmp = fromIndex;
            fromIndex = toIndex;
            toIndex = tmp;
        }

        if (fromIndex == toIndex) {
            return "";
        } else {
            char[] strArray = string.toCharArray();
            char[] newStrArray = Arrays.copyOfRange(strArray, fromIndex, toIndex);
            return new String(newStrArray);
        }
    }

    public static String format(String template, Map<?, ?> map) {
        if (null != map && !map.isEmpty()) {
            Entry entry;
            for(Iterator var2 = map.entrySet().iterator(); var2.hasNext(); template = template.replace("{" + entry.getKey() + "}", entry.getValue().toString())) {
                entry = (Entry)var2.next();
            }

            return template;
        } else {
            return template;
        }
    }

    public static boolean isMobile(String mobile) {
        String regex = "^[1][2|3|4|5|6|7|8|9][0-9]{9}$";
        Pattern p = Pattern.compile(regex, 2);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public static boolean isIdCard(String mobile) {
        String regex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
        Pattern p = Pattern.compile(regex, 2);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public static String concatCapitalize(String concatStr, String str) {
        if (isEmpty(concatStr)) {
            concatStr = "";
        }

        if (str != null && str.length() != 0) {
            char firstChar = str.charAt(0);
            return Character.isTitleCase(firstChar) ? str : concatStr + Character.toTitleCase(firstChar) + str.substring(1);
        } else {
            return str;
        }
    }

    public static String[] split(String str) {
        return split(str, (String)null, -1);
    }

    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    public static String[] split(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                List<String> list = new ArrayList();
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;

                while(true) {
                    while(i < len) {
                        if (str.charAt(i) == separatorChar) {
                            if (match || preserveAllTokens) {
                                list.add(str.substring(start, i));
                                match = false;
                                lastMatch = true;
                            }

                            ++i;
                            start = i;
                        } else {
                            lastMatch = false;
                            match = true;
                            ++i;
                        }
                    }

                    if (match || preserveAllTokens && lastMatch) {
                        list.add(str.substring(start, i));
                    }

                    return (String[])list.toArray(new String[list.size()]);
                }
            }
        }
    }

    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                List<String> list = new ArrayList();
                int sizePlus1 = 1;
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;
                if (separatorChars != null) {
                    if (separatorChars.length() != 1) {
                        label87:
                        while(true) {
                            while(true) {
                                if (i >= len) {
                                    break label87;
                                }

                                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    } else {
                        char sep = separatorChars.charAt(0);

                        label71:
                        while(true) {
                            while(true) {
                                if (i >= len) {
                                    break label71;
                                }

                                if (str.charAt(i) == sep) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    }
                } else {
                    label103:
                    while(true) {
                        while(true) {
                            if (i >= len) {
                                break label103;
                            }

                            if (Character.isWhitespace(str.charAt(i))) {
                                if (match || preserveAllTokens) {
                                    lastMatch = true;
                                    if (sizePlus1++ == max) {
                                        i = len;
                                        lastMatch = false;
                                    }

                                    list.add(str.substring(start, i));
                                    match = false;
                                }

                                ++i;
                                start = i;
                            } else {
                                lastMatch = false;
                                match = true;
                                ++i;
                            }
                        }
                    }
                }

                if (match || preserveAllTokens && lastMatch) {
                    list.add(str.substring(start, i));
                }

                return (String[])list.toArray(new String[list.size()]);
            }
        }
    }

//    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
//        if (str1 != null && str2 != null) {
//            if (str1 == str2) {
//                return true;
//            } else {
//                return str1.length() != str2.length() ? false : CharSequenceUtils.regionMatches(str1, true, 0, str2, 0, str1.length());
//            }
//        } else {
//            return str1 == str2;
//        }
//    }

    public static boolean isAnyEmpty(CharSequence... css) {
        if (CollectionUtil.isEmpty(css)) {
            return false;
        } else {
            CharSequence[] var1 = css;
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = var1[var3];
                if (isEmpty(cs)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isAllEmpty(CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return true;
        } else {
            CharSequence[] var1 = css;
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = var1[var3];
                if (isNotEmpty(cs)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isNoneEmpty(CharSequence... css) {
        return !isAnyEmpty(css);
    }

    public static boolean isAnyBlank(CharSequence... css) {
        if (CollectionUtil.isEmpty(css)) {
            return false;
        } else {
            CharSequence[] var1 = css;
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = var1[var3];
                if (isBlank(cs)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isAllBlank(CharSequence... css) {
        if (CollectionUtil.isEmpty(css)) {
            return true;
        } else {
            CharSequence[] var1 = css;
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = var1[var3];
                if (isNotBlank(cs)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isNoneBlank(CharSequence... css) {
        return !isAnyBlank(css);
    }

    public static void main(String[] args) {
//        String s1 = "ABCDEFGabcdefgABCDEFGabcdefg";
//        String s2 = "bcd";
//        String s3 = "234";
//        //总文本，查找的字符串，替换后的字符串，替换次数，是否忽视大小写
//        String ret = replace(s1,s2,s3,-1,true);
//        System.out.println("Ret = "+ret);
        String s1 = "ABCDEFGabcdefgABCDEFGabcdefg";
        String s2 = "b|B";

        String[] ret = splitWorker(s1,s2,2,true);
        for (String s : ret) {
            System.out.println(s);
        }

    }

    static {
        FILE_SEPARATOR = File.separator;
        hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        EMPTY_STRING_ARRAY = new String[0];
    }
}
