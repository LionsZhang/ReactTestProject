/**
 *
 */
package app.reactproject.com.utill;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lion
 *         created at 2016/4/27 16:53
 * @desc 字符串工具类
 **/
public class StringUtils {

    private static final String SIMPLE_PWD = "123456";

    public static boolean isOnlyContainBlank(String text) {
        if (TextUtils.isEmpty(text))
            return true;

        return text.trim().length() < 1;
    }

    /**
     * 检测是否为简单密码
     *
     * @param pwd
     * @return
     */
    public static boolean isSimplePassword(String pwd) {
        if (SIMPLE_PWD.equals(pwd))
            return true;
        for (int i = 0; i < pwd.length(); i++) {
            if (i != (pwd.length() - 1)) {
                if (pwd.charAt(i) != pwd.charAt(i + 1))
                    return false;
            }
        }
        return true;
    }

    public static int getStringArrayIndex(String[] str, String value) {

        int index = 0;

        if (null != str && null != value) {
            for (int i = 0; i < str.length; i++) {

                if (value.equals(str[i]) || value == str[i]) {

                    index = i;
                }
            }
        }
        return index;
    }

    /**
     * 删除字符串中指定位置的字符串
     *
     * @param str
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static String getStringDelIndex(String str, int startIndex,
                                           int endIndex) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(str);
        buffer.delete(startIndex, endIndex);
        return buffer.toString();
    }

    /**
     * 根据特定字符切割字符串
     *
     * @param str 需要切割的字符串
     * @param arg 按照切割的字符串
     * @return
     */
    public static List<String> getListByStringDivision(String str, String arg) {
        List<String> list = new ArrayList<String>();
        if (str != null && str.length() > 0) {
            String[] s = str.split(arg);
            if (s.length > 0) {
                for (int i = 0; i < s.length; i++) {
                    list.add(s[i]);
                }
            }
        }
        return list;
    }

    /**
     * 字符串按照arg拼接
     *
     * @param list
     * @return
     */
    public static String getStringByList(List<String> list, String arg) {
        String str = "";
        StringBuilder sss = new StringBuilder();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    sss.append(list.get(i)).append(arg);
                } else {
                    sss.append(list.get(i));
                }
            }
            str = sss.toString();
        }
        return str;
    }

    /**
     * 去除字符串中的所有空字符
     *
     * @param str
     * @return
     */
    public static String getStringNoCharacter(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return str.replace(" ", "");
    }

    /**
     * 手机号码里面添加空字符
     *
     * @param str
     * @return
     */
    public static String getPhoneNumByString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.substring(0, 3) + " " + str.substring(3, 7) + " "
                + str.substring(7);
    }

    /**
     * 截取国家码，去掉前面的+
     * +86 -> 86
     *
     * @param str
     * @return
     */
    public static String getPhoneCountryCodeNum(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains("+"))
            str = str.replace("+", "");
        return str;
    }

    /**
     * 语音文件切割
     *
     * @param audioPath
     * @return
     */
    public static String getBareAudioFileId(String audioPath) {
        if (TextUtils.isEmpty(audioPath))
            return "";
        if (!audioPath.contains("http://")) {
            return audioPath;
        }
        String s = "";
        try {
            s = audioPath.split("fileid/")[1];
        } catch (Exception e) {
            ALLog.d("语音上传下载地址截取失败:" + s);
        }
        return s;
    }


    /**
     * 获取body里面图片内容
     *
     * @param str
     * @return
     */
    public static String getUrlImage(String str) {
        String imageFile = "";
        if (TextUtils.isEmpty(str)) {
            return imageFile;
        }

        int startIndex = 0;
        int endIndex = 0;
        if (str.indexOf("img src='") != -1) {
            startIndex = str.indexOf("img src='");
            endIndex = str.indexOf("' />");
        } else {
            return imageFile;
        }

        imageFile = str.substring(startIndex + "img src='".length(), endIndex);
        ALLog.d("图片的id是:" + imageFile);
        return imageFile;
    }

    /**
     * 获取body里面title内容
     *
     * @param str
     * @return
     */
    public static String getTxtUrl(String str) {
        String txt = "";
        if (TextUtils.isEmpty(str)) {
            return txt;
        }

        int startIndex = 0;
        int endIndex = 0;
        if (str.indexOf("title='") != -1) {
            startIndex = str.indexOf("title='");
            endIndex = str.indexOf("'>");
        } else {
            return txt;
        }

        txt = str.substring(startIndex + "title='".length(), endIndex);
        ALLog.d("title内容是:" + txt);
        return txt;

    }

    /**
     * 获取连接内容
     *
     * @param str
     * @return
     */
    public static String getLinkedUrl(String str) {
        String linked = "";
        if (TextUtils.isEmpty(str)) {
            return linked;
        }

        int startIndex = 0;
        int endIndex = 0;
        if (str.indexOf("href='") != -1) {
            startIndex = str.indexOf("href='");
            endIndex = str.indexOf("' title");
        } else {
            return linked;
        }

        linked = str.substring(startIndex + "href='".length(), endIndex);
        ALLog.d("linked内容是:" + linked);
        return linked;
    }


    public static String removeSuffix(String s) {
        if (TextUtils.isEmpty(s)) return s;
        int end = s.lastIndexOf(".");
        if (end > 0) {
            s = s.substring(0, end);
        }
        return s;
    }

    /**
     * 计算两个数的百分比
     *
     * @param num1 被除数
     * @param num2 粗俗
     * @param n    设置精确到小数点后N位
     * @return
     */
    public static String numberPercentage(int num1, int num2, int n) {
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后n位
        numberFormat.setMaximumFractionDigits(n);
        return numberFormat.format((float) num1 / (float) num2 * 100);
    }

    /**
     * 字符串是否是字母和数字 校验
     *
     * @param value 字符串
     * @return
     */
    public static boolean isNumberOrLetter(String value) {
        final String reg = "^[a-zA-z0-9]{6,}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 判断手机号以1开始的11位
     *
     * @param value 字符串
     * @return
     */
    public static boolean isPhone(String value) {
        final String reg = "^[1][0-9]{10}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * 字符串是否是字母和数字 校验
     *
     * @param value 字符串
     * @return
     */
    public static boolean isNumberAndLetter(String value) {
        //  final String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$";数字和字母
        final String reg = "^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,}$";//数字和字母特殊符号中的两种
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 数字有两个小数点
     *
     * @param value 字符串
     * @return
     */
    public static boolean isNumberHas2point(String value) {
        final String reg = "^[0-9]+\\.[0-9]{3}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 是否中文
     *
     * @param value 字符串
     * @return
     */
    public static boolean isContainChinese(String value) {
        final String reg = "^.*[\\u4e00-\\u9fa5].*$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 是否中文2-10个汉字
     *
     * @param value 字符串
     * @return
     */
    public static boolean isTwoToTenChinese(String value) {
        final String reg = "^[\\u4e00-\\u9fa5]{2,10}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 是否以点开始的数字
     *
     * @param value 字符串
     * @return
     */
    public static boolean isNotnumberPointStart(String value) {
        final String reg = "^\\.[0-9]*$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 银行卡号15-19位数字
     *
     * @param value 字符串
     * @return
     */
    public static boolean isBankCard(String value) {
        final String reg = "^[0-9]{15,19}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 是否中文
     *
     * @param value 字符串
     * @return
     */
    public static boolean isNotnumberPointEnd(String value) {
        final String reg = "^[0-9]*\\.$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /* 基本原理是将字符串中所有的非标准字符（双字节字符）替换成两个标准字符（**，或其他的也可以）。这样就可以直接例用length方法获得字符串的字节长度了 */
    public static int getWordCountRegex(String s) {

        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    /* 按特定的编码格式获取长度 */
    public static int getWordCountCode(String str, String code)
            throws UnsupportedEncodingException {
        return str.getBytes(code).length;
    }

    /**
     * 将手机中间四位用*号替换
     *
     * @param mobile 输入：15012345678<br/>
     *               输出：150****5678<br/>
     * @return
     */
    public static String fuzzyMobile(String mobile) {
        if (mobile != null && mobile.length() == 11) {
            StringBuilder fuzzy = new StringBuilder();
            for (int i = 0; i < mobile.length(); i++) {
                if (i > 2 && i < 7) {
                    fuzzy.append("*");
                } else {
                    fuzzy.append(mobile.charAt(i));
                }
            }
            return fuzzy.toString();
        }
        return mobile;
    }

    /**
     * 将数字向左移动四位
     *
     * @return
     */

    public static String mathLeftMove4(String math) {
        BigDecimal bg1, bg2;
        bg1 = new BigDecimal(math);
        bg2 = bg1.movePointLeft(4); // 4 points left
        String f1 = bg2.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
        return f1;
    }
    public static Spannable changeColor(String beforeText, String centerText, String endText, String color) {
        return (Spannable) Html.fromHtml(beforeText + "<font color=\"#" + color + "\">" + centerText + "</font>"
                + endText);
    }

    public static Spannable changeColor(String centerText, String endText, String color) {
        return (Spannable) Html.fromHtml("<font color=\"#" + color + "\">" + centerText + "</font>" + endText);
    }
    public static Spannable changeTextColor(String beforeText, String endText, String beforecolor, String endColor) {
        return (Spannable) Html.fromHtml("<font color=\"#" + beforecolor + "\">" + beforeText + "</font>" +
                "<font color=\"#" + endColor + "\">" + endText+ "</font>" );
    }

//&nbsp;&nbsp;
    public static CharSequence getBindCardInstruction(){
        return Html.fromHtml("<font color='#999999'>\n什么是同卡进出,如何更换银行卡？<Br/><Br/><Br/>" +
                "为了保障您的资金安全，请添加本人的银行卡，添加完成后，充值和提现均只能使用该银行卡。<Br/>如您在平台的交易和账户资金已清零，" +
                "或该卡丢失/注销，请先联系客服热线4008-318-999申请更换。<Br/><Br/><Br/>再发送以下资料至kefu@liminwang.com:<Br/>\n" +
                "1.手持身份证正面照<Br/>\n" +
                "2.身份证正面及反面照<Br/>\n" +
                "3.原银行卡丢失或注销凭证<Br/>\n" +
                "4.近3个月的原银行卡交易流水证明及利民网账户的交易记录截图。</font>");
    }
    public static CharSequence getTransferInstruction(){
        return Html.fromHtml("1.在封闭期（60天）后,且剩余期限大于33天可支持最多1次转让操作;" +
                "<Br/>2.发起后不可撤回;<Br/>3.项目结算/回款日前3天内(含3日)不允许转让.");
    }
    public static Spannable getRiskHintDefault(){
        return (Spannable) Html.fromHtml("<font color=\"#666666\">利民网默认您的风险承受能力为保<Br/>守型，如不符，您可以</font><font color=\"#629eff\"><u>重新评测</u>。</font>");
    }

    /**
     * 得到一个改变指定字体颜色的Spannable
     *
     * @param color  颜色 int
     * @param allStr 需要设置的整个字符串
     * @param num    传入的指定改变颜色的字符
     * @return
     */
    public static SpannableStringBuilder getColorSpannBuilder(int color, String allStr, String... num) {
        StringBuilder builder = new StringBuilder(allStr);
        SpannableStringBuilder builderSpannable = new SpannableStringBuilder(builder);

        if (num != null && num.length > 0) {
            int index = 0 - num[0].length();
            String last = "";
            for (int i = 0; i < num.length; i++) {
                if (i == 0) {
                    index = builder.indexOf(num[i], 0);
                } else {
                    index = builder.indexOf(num[i], index + last.length());
                }
                if (index != -1) {
                    builderSpannable.setSpan(new ForegroundColorSpan(color),
                            index,
                            index + num[i].length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                last = num[i];
            }

        }

        return builderSpannable;
    }

}
