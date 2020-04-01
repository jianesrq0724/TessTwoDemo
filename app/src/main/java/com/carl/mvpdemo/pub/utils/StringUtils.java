package com.carl.mvpdemo.pub.utils;

import android.text.TextUtils;
import android.util.Base64;

import com.carl.mvpdemo.BuildConfig;

import org.ethereum.crypto.ECKey;
import org.spongycastle.util.encoders.Hex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Carl
 * version 1.0
 * @since 2018/7/6
 */
public class StringUtils {

    public static String base = "abcdefghijklmnopqrstuvwxyzZ0123456789";

    public static String NUMBER_BASE = "0123456789";

    public static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * unicode转中文
     *
     * @param str
     * @return
     * @author yutao
     * @date 2017年1月24日上午10:33:25
     */
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch + "");

        }

        return str;

    }


    public static String getEmail(int lMin, int lMax) {
        int length = getNum(lMin, lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * NUMBER_BASE.length());
            //首字母不为数字
            if (i == 0 && number == 0) {
                number = 6;
            }
            sb.append(NUMBER_BASE.charAt(number));
        }
        sb.append("@qq.com");
        return sb.toString();
    }


    public static String getRandomPassWord(int lMin, int lMax) {
        int length = getNum(lMin, lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomDigit(int lMin, int lMax) {
        String sources = "0123456789";
        int length = getNum(lMin, lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            String digitStr = sources.charAt(new Random().nextInt(9)) + "";
            if (digitStr.equals("0") && i == 0) {
                digitStr = "1";
            }
            sb.append(digitStr);
        }
        return sb.toString();
    }

    public static char getRandomChar() {
        String sources = "0123456789";
        char[] chars = sources.toCharArray();
        int index = new Random().nextInt(chars.length);
        return chars[index];
    }


    public static String getGoodsBarCode() {
//        String code = "69227110" + getRandomDigit(4, 4);
//        String code = "697222057" + getRandomDigit(3, 3);
        String code = "69" + getRandomDigit(10, 10);
        int c1 = 0;
        int c2 = 0;
        for (int i = 0; i < 12; i += 2) {
            c1 += code.charAt(i) - '0';
            c2 += code.charAt(i + 1) - '0';
        }
        int cc = 10 - (c1 + c2 * 3) % 10;
        if (cc == 10) {
            cc = 0;
        }
        return code + cc;
    }


    public static String getRandomWallet() {
        ECKey ecKey = new ECKey();
        String ethStr = "0x" + Hex.toHexString(ecKey.getAddress());
        return ethStr;
    }

    static String mUserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";

    public static String getUserAgent(String userFlat) {
        String userAgent = SPUtils.getInstance().getUserAgent(userFlat);
        if (TextUtils.isEmpty(userFlat) || TextUtils.isEmpty(userAgent)) {
            changeUserAgent();
            userAgent = mUserAgent;
            SPUtils.getInstance().putUserAgent(userFlat, userAgent);
        }
        LogUtils.i("userAgent:" + userAgent);
        return userAgent;
    }

    public static boolean getJiaMiFlavor() {
        return BuildConfig.FLAVOR.contains("cbt") || BuildConfig.FLAVOR.contains("drcc");
    }

    public static void changeUserAgent() {
        String userAgent = "Mozilla/5.0 (Linux; Android 5.1.1; Hisense C2000 Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.97 Mobile Safari/537.36";
        mUserAgent = userAgent.replace("2000", String.valueOf(new Random().nextInt(8000) + 1000));
    }

    public static String getName() {
        final String[] name = {
                "Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan", "Margaret", "Dorothy", "Lisa", "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna", "Carol", "Ruth", "Sharon", "Michelle", "Laura", "Sarah", "Kimberly", "Deborah", "Jessica", "Shirley", "Cynthia", "Angela", "Melissa", "Brenda", "Amy", "Anna", "Rebecca", "Virginia", "Kathleen", "Pamela", "Martha", "Debra", "Amanda", "Stephanie", "Carolyn", "Christine", "Marie", "Janet", "Catherine", "Frances", "Ann", "Joyce", "Diane", "Alice", "Julie", "Heather", "Teresa", "Doris", "Gloria", "Evelyn", "Jean", "Cheryl", "Mildred", "Katherine", "Joan", "Ashley", "Judith", "Rose", "Janice", "Kelly", "Nicole", "Judy", "Christina", "Kathy", "Theresa", "Beverly", "Denise", "Tammy", "Irene", "Jane", "Lori", "Rachel", "Marilyn", "Andrea", "Kathryn", "Louise", "Sara", "Anne", "Jacqueline", "Wanda", "Bonnie", "Julia", "Ruby", "Lois", "Tina", "Phyllis", "Norma", "Paula", "Diana", "Annie", "Lillian", "Emily", "Robin", "Peggy", "Crystal", "Gladys", "Rita", "Dawn", "Connie", "Florence", "Tracy", "Edna", "Tiffany", "Carmen", "Rosa", "Cindy", "Grace", "Wendy", "Victoria", "Edith", "Kim", "Sherry", "Sylvia", "Josephine", "Thelma", "Shannon", "Sheila", "Ethel", "Ellen", "Elaine", "Marjorie", "Carrie", "Charlotte", "Monica", "Esther", "Pauline", "Emma", "Juanita", "Anita", "Rhonda", "Hazel", "Amber", "Eva", "Debbie", "April", "Leslie", "Clara", "Lucille", "Jamie", "Joanne", "Eleanor", "Valerie", "Danielle", "Megan", "Alicia", "Suzanne", "Michele", "Gail", "Bertha", "Darlene", "Veronica", "Jill", "Erin", "Geraldine", "Lauren", "Cathy", "Joann", "Lorraine", "Lynn", "Sally", "Regina", "Erica", "Beatrice", "Dolores", "Bernice", "Audrey", "Yvonne", "Annette", "June", "Samantha", "Marion", "Dana", "Stacy", "Ana", "Renee", "Ida", "Vivian", "Roberta", "Holly", "Brittany", "Melanie", "Loretta", "Yolanda", "Jeanette", "Laurie", "Katie", "Kristen", "Vanessa", "Alma", "Sue", "Elsie", "Beth", "Jeanne", "James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph", "Thomas", "Christopher", "Daniel", "Paul", "Mark", "Donald", "George", "Kenneth", "Steven", "Edward", "Brian", "Ronald", "Anthony", "Kevin", "Jason", "Matthew", "Gary", "Timothy", "Jose", "Larry", "Jeffrey", "Frank", "Scott", "Eric", "Stephen", "Andrew", "Raymond", "Gregory", "Joshua", "Jerry", "Dennis", "Walter", "Patrick", "Peter", "Harold", "Douglas", "Henry", "Carl", "Arthur", "Ryan", "Roger", "Joe", "Juan", "Jack", "Albert", "Jonathan", "Justin", "Terry", "Gerald", "Keith", "Samuel", "Willie", "Ralph", "Lawrence", "Nicholas", "Roy", "Benjamin", "Bruce", "Brandon", "Adam", "Harry", "Fred", "Wayne", "Billy", "Steve", "Louis", "Jeremy", "Aaron", "Randy", "Howard", "Eugene", "Carlos", "Russell", "Bobby", "Victor", "Martin", "Ernest", "Phillip", "Todd", "Jesse", "Craig", "Alan", "Shawn", "Clarence", "Sean", "Philip", "Chris", "Johnny", "Earl", "Jimmy", "Antonio", "Danny", "Bryan", "Tony", "Luis", "Mike", "Stanley", "Leonard", "Nathan", "Dale", "Manuel", "Rodney", "Curtis", "Norman", "Allen", "Marvin", "Vincent", "Glenn", "Jeffery", "Travis", "Jeff", "Chad", "Jacob", "Lee", "Melvin", "Alfred", "Kyle", "Francis", "Bradley", "Jesus", "Herbert", "Frederick", "Ray", "Joel", "Edwin", "Don", "Eddie", "Ricky", "Troy", "Randall", "Barry", "Alexander", "Bernard", "Mario", "Leroy", "Francisco", "Marcus", "Micheal", "Theodore", "Clifford", "Miguel", "Oscar", "Jay", "Jim", "Tom", "Calvin", "Alex", "Jon", "Ronnie", "Bill", "Lloyd", "Tommy", "Leon", "Derek", "Warren", "Darrell", "Jerome", "Floyd", "Leo", "Alvin", "Tim", "Wesley", "Gordon", "Dean", "Greg", "Jorge", "Dustin", "Pedro", "Derrick", "Dan", "Lewis", "Zachary", "Corey", "Herman", "Maurice", "Vernon", "Roberto", "Clyde", "Glen", "Hector", "Shane", "Ricardo", "Sam", "Rick", "Lester", "Brent", "Ramon", "Charlie", "Tyler", "Gilbert", "Gene", "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Jackson", "White", "Harris", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Walker", "Hall", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "Watson", "Brooks", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham", "Sullivan", "Wallace", "Woods", "Cole", "West", "Jordan", "Owens", "Reynolds", "Fisher", "Ellis", "Harrison", "Gibson", "Mcdonald", "Cruz", "Marshall", "Ortiz", "Gomez", "Murray", "Freeman", "Wells", "Webb", "Simpson", "Stevens", "Tucker", "Porter", "Hunter", "Hicks", "Crawford", "Boyd", "Mason", "Morales", "Kennedy", "Dixon", "Ramos", "Reyes", "Burns", "Shaw", "Holmes", "Rice", "Robertson", "Hunt", "Black", "Daniels", "Palmer", "Mills", "Nichols", "Grant", "Knight", "Ferguson", "Stone", "Hawkins", "Dunn", "Perkins", "Hudson", "Spencer", "Gardner", "Stephens", "Payne", "Pierce", "Berry", "Matthews", "Arnold", "Wagner", "Willis", "Watkins", "Olson", "Carroll", "Duncan", "Snyder", "Hart", "Cunningham", "Lane", "Andrews", "Ruiz", "Harper", "Fox", "Riley", "Armstrong", "Carpenter", "Weaver", "Greene", "Elliott", "Chavez", "Sims", "Austin", "Peters", "Kelley", "Franklin", "Lawson",
        };

        int index = new Random().nextInt(name.length);

        return name[index];

    }


    /**
     * SHA加密
     *
     * @param strSrc 明文
     * @return 加密之后的密文
     */
    public static String shaEncrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            // 将此换成SHA-1、SHA-512、SHA-384等参数
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            // to HexString
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts 数据源
     * @return 16进制字符串
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;

    }

    public static String toBase64(String strSrc) {
        byte[] bytes = strSrc.getBytes();
        String base64Str = Base64.encodeToString(bytes, Base64.NO_WRAP);
        return base64Str;
    }


    /**
     * 返回手机号码
     */
    private static String[] telFirst = ("134,135,136,137,138,139,147,150,151,152,157,158,159,178,182,183,184,187,188,198,130,131,132,145,155,156,175,176,185,186,153,173,177,180,181,189,199").split(",");

    public static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }


    public static String trimSmsCode(String text) {
        if (text.contains(TimeUtils.getCurrentYMD())) {
            text = text.replace(TimeUtils.getCurrentYMD(), "");
        }

        int start = 0;
        int end = start + 4;
        //先判断连续4个数字的位置
        while (!isNumericzidai(text.substring(start, end))) {
            start++;
            end++;
        }
        while (end + 1 <= text.length() && isNumericzidai(text.substring(start, end + 1))) {
            end++;
        }
        return text.substring(start, end);
    }

    public static String trimName(String text) {
        int start = 0;
        int end = text.length();
        //先判断连续数字开始位置
        while (start < text.length() && !isNumericzidai(text.substring(start, end))) {
            start++;
        }
        return text.substring(0, start);
    }

    public static String trimIdCard(String text) {
        int start = 0;
        int end = text.length();
        //先判断连续数字开始位置
        while (start < text.length() && !isNumericzidai(text.substring(start, end))) {
            start++;
        }
        return text.substring(start, end);
    }

    /**
     * 判断是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumericzidai(String str) {
        String regEx = "^-?[0-9xX]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 存在汉字
     *
     * @param str
     * @return
     */
    public static boolean exitHanzi(String str) {
        if (str.getBytes().length == str.length()) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 判断全部是汉字
     *
     * @param str
     * @return
     */
    public static boolean isHanzi(String str) {
        int n = 0;
        for (int i = 0; i < str.length(); i++) {
            n = (int) str.charAt(i);
            if (!(19968 <= n && n < 40869)) {
                return false;
            }
        }
        return true;
    }


    /**
     * url拼接
     *
     * @param url
     * @param params
     * @return
     */
    public static String getRqstUrl(String url, Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url);
        boolean isFirst = true;
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null) {
                if (isFirst) {
                    isFirst = false;
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(key)
                        .append("=")
                        .append(params.get(key));
            }
        }
        return builder.toString();
    }

    public static String deleteNum(String content) {
        String str = content.replaceAll("\\d+", "");
        return str;
    }

    public static String getBankCode() {
//        String cardnum = "6222";
//        for (int i = 0; i < 2; i++) {
//            cardnum = cardnum + StringUtils.getNum(1000, 9999);
//        }
//        cardnum = cardnum + StringUtils.getNum(100, 999);
//        return cardnum;

        return getBrankNumber();

    }


    public static String getBankName() {
        String[] bankNmae = {"工商银行", "农业银行", "中国银行", "建设银行", "交通银行", "中国邮政储蓄银行", "中国光大银行", "中国民生银行", "招商银行", "中信银行"};
        int index = new Random().nextInt(bankNmae.length);
        return bankNmae[index];
    }

    public static String getEmailSuffix() {
        String[] email = {"@qq.com", "@163.com", "@163.net", "@126.com"};
        int index = new Random().nextInt(email.length);
        return email[index];
    }


    public synchronized static String getBrankNumber() {
        String cardnum = "6222" + getRandomDigit(14, 14);
        cardnum = cardnum + getBankCardCheckCode(cardnum);
        return cardnum;
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 获取16进制随机数
     *
     * @param len
     * @return
     */
    public static String randomHexString(int len) {
        try {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < len; i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toLowerCase();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String getSimpleCookie(String cookie) {
        if (cookie.contains("path")) {
            cookie = cookie.substring(0, cookie.indexOf("path"));
        }
        cookie = cookie.replace("HttpOnly", "");
        return cookie;
    }


    public static String convertStream2String(InputStream in) throws IOException {
        //inputStream转换为String 要进行gbk或者utf-8转码，否则乱码
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
//        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

//    20190926200040011100400048107287

    public static String getZfbSmOrderId() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String orderId = date + "220014474005298" + getRandomDigit(5, 5);
        return orderId;
    }

    public static String getZfbZzOrderId() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String orderId = date + "2000400111004000480" + getRandomDigit(5, 5);
        return orderId;
    }

    /**
     * 产生随机的2位数
     *
     * @return
     */
    public static String getRandomTwo() {
        Random rad = new Random();

        String result = rad.nextInt(100) + "";

        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }



}
