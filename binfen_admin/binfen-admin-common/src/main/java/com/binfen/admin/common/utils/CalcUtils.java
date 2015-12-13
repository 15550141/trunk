package com.binfen.admin.common.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 费用计算工具类
 *
 * @author leishouguo
 * @author Zhiguo.Chen
 * @since 1.0.0
 */
public class CalcUtils {

//    private static final Long DAYS_OF_YEAR = 360L;

    private static final MathContext mc = new MathContext(16, RoundingMode.HALF_UP);

    /**
     * 计算息费
     *
     * @param amount 金额
     * @param rate   年利率
     * @param days   天数
     * @return
     */
    public static BigDecimal calcScale(BigDecimal amount, BigDecimal rate, long days, int scale) {
        return scale(amount.multiply((rate)).multiply(new BigDecimal(days)), scale);
    }

    /**
     * 计算息费
     *
     * @param amount 金额
     * @param rate   年利率
     * @param days   天数
     * @return
     */
    public static BigDecimal calc(BigDecimal amount, BigDecimal rate, long days) {
        return scale(amount.multiply((rate)).multiply(new BigDecimal(days)));
    }

    /**
     * 计算贴现费
     *
     * @param amount 金额
     * @param rate   年利率
     * @param days   天数
     * @return
     */
    public static BigDecimal calcDiscountAmount(BigDecimal amount, BigDecimal rate, long days) {
        return scale(amount.multiply((rate)));
    }

    /**
     * 保留两个小数
     *
     * @param amount
     * @return
     */
    public static BigDecimal scale(BigDecimal amount) {
        return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal scale(BigDecimal amount, int scale) {
        return amount.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 转为两位小数后比较
     *
     * @param one
     * @param two
     * @return
     */
    public static boolean equalsWithScale(BigDecimal one, BigDecimal two) {
        if (one == two) {
            return true;
        }

        if (one == null || two == null) {
            return false;
        }

        BigDecimal one1 = one.setScale(2, RoundingMode.HALF_UP);
        BigDecimal two1 = two.setScale(2, RoundingMode.HALF_UP);
        return one1.equals(two1);
    }

    /**
     * 两者相关是否在误差内
     *
     * @param one  金额1
     * @param two  金额2
     * @param base 误差
     * @return
     */
    public static boolean amountCompareLess(BigDecimal one, BigDecimal two, BigDecimal base) {
        BigDecimal sub = subBigDicimal(one, two);
        sub = sub.abs();
        return sub.compareTo(base) == -1;
    }


    public static BigDecimal add(BigDecimal... others) {
        if (others == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal tempAmount = BigDecimal.ZERO;
        for (BigDecimal single : others) {
            if (single != null) {
                tempAmount = tempAmount.add(single);
            }
        }

        return tempAmount;
    }

    /**
     * 相减
     *
     * @param total
     * @param others
     * @return
     */
    public static BigDecimal sub(BigDecimal total, BigDecimal... others) {
        if (others == null) {
            return total;
        }
        BigDecimal leftAmount = total;
        for (BigDecimal single : others) {
            if (single != null) {
                leftAmount = leftAmount.subtract(single);
            }
        }

        return leftAmount;
    }

    /**
     * subBigDicimal
     *
     * @param one
     * @param two
     * @return
     */
    public static BigDecimal subBigDicimal(BigDecimal one, BigDecimal two) {
        if (one == two) {
            return BigDecimal.ZERO;
        }

        if (one == null) {
            one = BigDecimal.ZERO;
        }
        if (two == null) {
            two = BigDecimal.ZERO;
        }

        return one.subtract(two);
    }

    public static boolean eq(BigDecimal amount, BigDecimal other) {
        return amount.compareTo(other) == 0;
    }

    public static boolean gt(BigDecimal amount, BigDecimal other) {
        return amount.compareTo(other) > 0;
    }

    public static boolean lt(BigDecimal amount, BigDecimal other) {
        return amount.compareTo(other) < 0;
    }


    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    }
}
