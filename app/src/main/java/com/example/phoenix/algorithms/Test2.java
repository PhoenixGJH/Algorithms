package com.example.phoenix.algorithms;

import android.util.Log;

/**
 * Created by Phoenix on 2017/2/28.
 */

public class Test2 {
    /**
     * 比较规则：不区分大小写，字母大于数字，release大于debug
     * <p>
     * 思路：版本号的格式一般为xxx.xxx.xxx，
     * 先对版本内容进行处理，将'-'和'_'转换为'.'，
     * 将"release"和"RELEASE"转换为1，将"debug"和"DEBUG"转换为0
     * 然后获取每一位xxx的内容，通过ASCII码分别进行对比;
     * 如果两个版本号的位数不同，并且相同的位数没有比较出结果，则查看较长版本号的剩余部分。
     * <p>
     * 1.根据小数点，对版本号进行分割，并保存在数组里
     * 2.获取两个数组较短的那个的长度，保存在minLength中；
     * 3.循环对数组相应位置进行字符串比较运算(实质为进行ASCII码比较运算)并保存在result中，如果结果不为0或全部比较完毕，则停止循环
     * 4.对计算结果进行分析，如果结果不为0，则根据结果是否大于0而获得比较结果;如果结果为0，
     * 则查看剩余位置是否有大于0的数值，如果有，则改版本为需求结果
     *
     * @param v1 版本号一
     * @param v2 版本号二
     * @return 版本号较大的那个，如果相同，返回版本号相同
     */
    public static String versionCompare(String v1, String v2) {
        if (v1.isEmpty() || v2.isEmpty()) {
            return "版本号为空";
        }
        if (v1.equals(v2)) {
            return "版本号相同";
        }

        String v1Tmp = v1;
        String v2Tmp = v2;

        v1Tmp = v1Tmp.replaceAll("[-_]", "\\.");
        v1Tmp = v1Tmp.replaceAll("release$", "1");
        v1Tmp = v1Tmp.replaceAll("RELEASE$", "1");
        v1Tmp = v1Tmp.replaceAll("debug$", "0");
        v1Tmp = v1Tmp.replaceAll("DEBUG$", "0");

        v2Tmp = v2Tmp.replaceAll("[-_]", "\\.");
        v2Tmp = v2Tmp.replaceAll("release$", "1");
        v2Tmp = v2Tmp.replaceAll("RELEASE$", "1");
        v2Tmp = v2Tmp.replaceAll("debug$", "0");
        v2Tmp = v2Tmp.replaceAll("DEBUG$", "0");

        String[] array1 = v1Tmp.split("\\.");
        String[] array2 = v2Tmp.split("\\.");
        //数组位置
        int index = 0;
        //相差的结果
        long result = 0;

        //两个数组中最短数组的长度
        int minLength = array1.length > array2.length ? array2.length : array1.length;

        while (index < minLength && (result = array1[index].compareToIgnoreCase(array2[index])) == 0) {
            index++;
        }
        if (result == 0) {
            for (int i = index; i < array1.length; i++) {
                if (array1[i].compareToIgnoreCase("0") > 0) {
                    return v1;
                }
            }
            for (int i = index; i < array2.length; i++) {
                if (array2[i].compareToIgnoreCase("0") > 0) {
                    return v2;
                }
            }
            return "版本号相同";
        } else {
            return result > 0 ? v1 : v2;
        }
    }

    public static void testVersionCompare() {
        String version1 = "1.1.2";
        String version2 = "1.1.0";
        String version3 = "1.2";
        String version4 = "1.1.2";
        String version5 = "1.2.0";
        String version6 = "1.62.08-100";
        String version7 = "1.62.08-108";
        String version8 = "1.62.08-108.M";
        String version9 = "1.62.08-108.release";
        String version10 = "1.62.08-108.debug";
        String version11 = "1.62.C";
        String version12 = "1.62.03";

        Log.d("Result1:", versionCompare(version1, version2));
        Log.d("Result2:", versionCompare(version1, version3));
        Log.d("Result3:", versionCompare(version1, version4));
        Log.d("Result4:", versionCompare(version5, version3));
        Log.d("Result5:", versionCompare(version6, version7));
        Log.d("Result6:", versionCompare(version6, version12));
        Log.d("Result7:", versionCompare(version7, version8));
        Log.d("Result8:", versionCompare(version9, version10));
        Log.d("Result9:", versionCompare(version11, version12));
    }
}