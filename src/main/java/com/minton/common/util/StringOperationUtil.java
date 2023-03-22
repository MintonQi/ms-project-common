package com.minton.common.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符及其字符串操作工具类
 *
 */
public class StringOperationUtil {

    /**
     * 功能描述: 将执行的字符串转换为用下划线分割的字符串：
     * 比如： HelloWOrld转换之后的字符串就是hello_world;
     *
     * @param targetStr 需要转换的字符串
     * @return 返回结果
     */
    public static String camelToSnake(final String targetStr) {
        String convertedStr =
                targetStr.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z])([A-Z])", "$1_$2");
        return convertedStr.toLowerCase();
    }

    /**
     * 功能描述： 判断一个字符串是不是为空,当为空的时候就返回true否则返回false
     *
     * @param value 需要判断的字符串
     * @return 返回boolean值
     */
    public static boolean isEmpty(final String value) {
        return (value == "") || (value.length() == 0);
    }

    /**
     * 功能描述： 判断一个字符串是不是为空,当为空的时候就返回false否则返回true
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(final String value) {
        return (value != null) && (value.length() > 0);
    }

    /**
     * 功能描述: 假如字符串不为空或者全部为空格组成的话返回false，假如为空字符串的话返回true
     *
     * @param value 判断的字符串
     * @return 返回的boolean值
     */
    public static boolean isBlankOrWhSpace(final String value) {
        return isEmpty(value) || value.matches("^\\s$");
    }

    /**
     * 判断字符串是不是为空：返回false，否则假如为空或者或者全部由空格组成返回true
     *
     * @param value
     * @return
     */
    public static boolean isBlankOrAllSpace(final String value) {
        return isEmpty(value) || value.matches("^\\s|^[\\s\\p{Zs}]+$");
    }

    /**
     * 判断指定的对象是不是为空:这里没有局限于一个字符串了，而是可以传递一个对象
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(final Object str) {
        return (str == null) || ((str instanceof CharSequence) && str.toString().isEmpty());
    }


    /**
     * 功能描述: 将指定的字符串转换为驼峰式的大小写:比如Hello_world_welcome_to_home---->helloWorldWelcomeToHome;
     *
     * @param targetStr 需要转化的字符创
     * @return 转换之后的字符串
     */
    public static String snakeToCamel(final String targetStr) {
        Pattern pattern = Pattern.compile("_([a-z])");
        Matcher matcher = pattern.matcher(targetStr.toLowerCase());

        //初始化一个字符容器
        StringBuffer sb = new StringBuffer(targetStr.length());

        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    /**
     * 将字符串转换为无隐式转换
     *
     * @param value
     * @return
     * @throws ClassCastException
     */
    public static String valueOf(final Object value) throws ClassCastException {
        if (value == null) {
            return null;
        } else if (value instanceof CharSequence) {
            if (value.toString().isEmpty()) {
                return null;
            }
            return value.toString();
        } else {
            throw new ClassCastException();
        }
    }

    /**
     * 功能描述: 将指定的字符串用指定的连接字符连接起来
     *
     * @param slashStr
     * @param args
     * @return
     */
    public static String combineStr(final String slashStr, final String... args) {
        //初始化一个放字符的容器
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            if (!isBlankOrWhSpace(args[i])) {
                if (i == args.length - 1) {
                    //假如是最后一个字符串就直接添加在末尾而不需要添加分割符
                    sb.append(args[i]);
                } else {
                    //在添加字符串之后再添加指定的分割符号
                    sb.append(args[i]).append(slashStr);
                }
            }
        }
        return sb.toString().trim();
    }
}