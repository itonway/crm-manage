package com.itontheway.manage.util;

/**
 * @Author xiegl
 * @Date 2020-8-10 13:49
 * @Desc
 **/
public class ConvertNum {
    public static void main(String[] args) {
        System.out.println("toChinese："+toChinese("12"));
        System.out.println("toChinese2："+toChinese2("1230456"));

        String s = toChines3(0);
        System.out.println(s);

    }
    private static String toChinese(String str) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
    }

    private static String toChinese2(String str) {
        String[] s2 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            sb.append(s2[Integer.parseInt(String.valueOf(c))]);
        }
        return sb.toString();
    }

    static char[] cnArr = new char [] {'一','二','三','四','五','六','七','八','九'};

    public static String toChines3(int intInput){
        String si = String.valueOf(intInput);
        String sd = "";
        if (si.length() == 1) {
            if (intInput == 0) {
                return "零";
            }
            sd += cnArr[intInput - 1];
            return sd;
        } else if (si.length() == 2) {
            if (si.substring(0, 1).equals("1")) {
                sd += "十";
                if (intInput % 10 == 0) {
                    return sd;
                }
            }
            else
                sd += (cnArr[intInput / 10 - 1] + "十");
            sd += toChines3(intInput % 10);
        } else if (si.length() == 3) {
            sd += (cnArr[intInput / 100 - 1] + "百");
            if (String.valueOf(intInput % 100).length() < 2) {
                if (intInput % 100 == 0) {
                    return sd;
                }
                sd += "零";
            }
            sd += toChines3(intInput % 100);
        }
        return sd;
    }
}
