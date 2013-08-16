package com.luckywc.core.Date;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.*;

/**
 * 农历输出
 */

public class LunarCalendar
{

    /**
     * 阳历年
     */
    private int solarYear = 0;

    /**
     * 阳历月
     */
    private int solarMonth = 0;

    /**
     * 阳历天
     */
    private int solarDay = 0;

    /**
     * 星期
     */
    private int week = 0;

    /**
     * 阴历年份
     */
    private int lunarYear = 0;

    /**
     * 阴历月份
     */
    private int lunarMonth = 0;

    /**
     * 阴历日期
     */
    private int lunarDay = 0;

    /**
     * 阴历信息
     */
    final private long[] lunarInfo = new long[]
        {
        0x04bd8, 0x04ae0, 0x0a570,
        0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
        0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0,
        0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50,
        0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566,
        0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0,
        0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4,
        0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550,
        0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950,
        0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260,
        0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0,
        0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
        0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40,
        0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3,
        0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960,
        0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0,
        0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9,
        0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0,
        0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65,
        0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0,
        0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2,
        0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};

    /**
     *
     */
    final private int[] year20 = new int[]
        {
        1, 4, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1};

    /**
     *
     */
    final private int[] year19 = new int[]
        {
        0, 3, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0};

    /**
     *
     */
    final private int[] year2000 = new int[]
        {
        0, 3, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1};

    /**
     * 阴历月份
     */
    final private String[] nStr1 = new String[]
        {
        "", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};

    /**
     * 天干
     */
    final private String[] Gan = new String[]
        {
        "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};

    /**
     * 地支
     */
    final private String[] Zhi = new String[]
        {
        "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    /**
     * 生肖
     */
    final private String[] Animals = new String[]
        {
        "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    /**
     * 阴历节气
     */
    final private String[] solarTerm = new String[]
        {
        "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至",
        "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"};

    /**
     * 阳历节日
     */
    final private String[] sFtv = new String[]
        {
        "0101*元旦", "0214 情人节", "0308 妇女节","0312 植树节", "0315 消费者权益日", "0401 愚人节",
        "0501 劳动节", "0504 青年节", "0512 护士节", "0601 儿童节", "0701 建党节", "0801 建军节",
        "0808 父亲节", "0909 毛泽东逝世纪念", "0910 教师节","0928 孔子诞辰", "1001*国庆节", "1006 老人节",
        "1024 联合国日", "1112 孙中山诞辰", "1220 澳门回归","1225 圣诞节", "1226 毛泽东诞辰"};

    /**
     * 阴历节日
     */
    final private String[] lFtv = new String[]
        {
        "0101*农历春节", "0115 元宵节", "0505 端午节","0707 七夕情人节", "0815 中秋节", "0909 重阳节",
        "1208 腊八节", "1224 小年", "0100*除夕"};

    /**
     * 月份中的中文日期
     */
    final private String[] chinaDay = new String[]
        {
        "","初一","初二","初三","初四","初五","初六","初七","初八","初九","初十",
        "十一","十二","十三","十四","十五","十六","十七","十八","十九","二十",
        "廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","三十",
        "三一",
    };

    /**
     * 中文星期
     */
    final private String[] chinaWeek = new String[]
        {
        "星期日","星期一","星期二","星期三","星期四","星期五","星期六","星期日",
    };


    /**
     * 阴历构造函数
     * @param year 阳历年份
     * @param month 阳历月份
     * @param day 阳历日期
     */
    public LunarCalendar(int year, int month, int day)
    {
        //判断月份是不是在1－12之内
        if (month > 12 || month < 1)
        {
            throw new RuntimeException("月份数据只能在1－12之间！");
        }

        //判断年份是不是在1－31之内
        if (day > 31 || day < 1)
        {
            throw new RuntimeException("日期只能在1－31之间！");
        }

        //记录阳历信息
        this.solarYear = year;
        this.solarMonth = month;
        this.solarDay = day;

        //构造日历类
        Date date = new Date();
        date.setDate(day);
        date.setYear(year-1900);
        date.setMonth(month-1);

        //判断日期是不是有效
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        StringBuffer temp = new StringBuffer();
        temp.append(year);
        if (month < 10)
        {
            temp.append(0);
        }
        temp.append(month);
        if (day < 10)
        {
            temp.append(0);
        }
        temp.append(day);
        String formatdate = format.format(date);
        if (!temp.toString().equals(formatdate))
        {
            throw new RuntimeException(temp.toString() + "不是有效的日期！");
        }

        //得到星期信息，注意从Calendar类得到星期会出错
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEEE");
        String tweek = sdf.format(date);
        for (int i = 0; i < chinaWeek.length; i++)
        {
            if (tweek.equals(chinaWeek[i]))
            {
                this.week = i;
                break;
            }
        }

        //转化阳历到农历
        long[] l = calElement(year, month, day);

        //记录农历信息
        this.lunarYear = (int)l[0];
        this.lunarMonth = (int)l[1];
        this.lunarDay = (int)l[2];
    }

    /**
     * 阴历构造函数
     * @param pDate java.util.Date 阳历日期类
     */
    public LunarCalendar(Date pDate)
    {
        //记录阳历信息
        this.solarYear = pDate.getYear() + 1900;
        this.solarMonth = pDate.getMonth() + 1;
        this.solarDay = pDate.getDate();

        //得到星期信息，注意从Calendar类得到星期会出错
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEEE");
        String tweek = sdf.format(pDate);
        for (int i = 0; i < chinaWeek.length; i++)
        {
            if (tweek.equals(chinaWeek[i]))
            {
                this.week = i;
                break;
            }
        }

        //阳历到农历的转化
        long[] l = calElement(solarYear, solarMonth, solarDay);

        //记录农历信息
        this.lunarYear = (int) l[0];
        this.lunarMonth = (int) l[1];
        this.lunarDay = (int) l[2];
    }

    /**
     * 阴历构造函数
     * @param pCalendar java.util.Calendar 阳历日历类
     */
    public LunarCalendar(Calendar pCalendar)
    {
        //记录阳历信息
        this.solarYear = pCalendar.get(Calendar.YEAR);
        this.solarMonth = pCalendar.get(Calendar.MONTH) + 1;
        this.solarDay = pCalendar.get(Calendar.DATE);
        this.week = pCalendar.get(Calendar.DAY_OF_WEEK)-1;

        //农历和阳历的转化
        long[] l = calElement(solarYear, solarMonth, solarDay);

        //记录农历信息
        this.lunarYear = (int) l[0];
        this.lunarMonth = (int) l[1];
        this.lunarDay = (int) l[2];
    }

    /**
     * 阴历构造函数，日期为当天
     */
    public LunarCalendar()
    {
        //今天的日历
        Calendar pCalendar = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);

        //记录阳历信息
        this.solarYear = pCalendar.get(Calendar.YEAR);
        this.solarMonth = pCalendar.get(Calendar.MONTH) + 1;
        this.solarDay = pCalendar.get(Calendar.DATE);
        this.week = pCalendar.get(Calendar.DAY_OF_WEEK)-1;

        //阳历转化成农历
        long[] l = calElement(solarYear, solarMonth, solarDay);

        //记录农历信息
        this.lunarYear = (int) l[0];
        this.lunarMonth = (int) l[1];
        this.lunarDay = (int) l[2];

    }

    /**
     * 得到阴历和阳历信息格式为：2005年4月14日 星期五 农历2005年（乙酉 鸡）三月初六
     * @return 2005年4月14日 星期五 农历2005年（乙酉 鸡）三月初六
     */
    public String toLocalString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(solarYear);
        sb.append("年");
        sb.append(solarMonth);
        sb.append("月");
        sb.append(solarDay);
        sb.append("日 ");
        sb.append(chinaWeek[week]);
        sb.append(" 农历");
        sb.append(lunarYear);
        sb.append("年（");
        sb.append(cyclical(lunarYear));
        sb.append(" ");
        sb.append(AnimalsYear(lunarYear));
        sb.append("）");
        sb.append(nStr1[lunarMonth]);
        sb.append("月");
        sb.append(chinaDay[lunarDay]);

        return sb.toString();
    }

    /**
     * 得到阴历信息，格式为：农历2005年（乙酉 鸡）三月初六 星期五
     * @return 农历2005年（乙酉 鸡）三月初六 星期五
     */
    public String toChinaString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(" 农历");
        sb.append(lunarYear);
        sb.append("年（");
        sb.append(cyclical(lunarYear));
        sb.append(" ");
        sb.append(AnimalsYear(lunarYear));
        sb.append("）");
        sb.append(nStr1[lunarMonth]);
        sb.append("月");
        sb.append(chinaDay[lunarDay]);
        sb.append(" ");
        sb.append(chinaWeek[week]);

        return sb.toString();
    }

    /**
     * 得到阳历年份
     * @return 阳历年
     */
    public int getSolarYear()
    {
        return solarYear;
    }

    /**
     * 得到阳历月份
     * @return 阳历月
     */
    public int getSolarMonth()
    {
        return solarMonth;
    }

    /**
     * 得到阳历日期
     * @return 阳历日期
     */
    public int getSolarDate()
    {
        return solarDay;
    }

    /**
     * 得到阴历年份
     * @return 阴历年
     */
    public int getLunarYear()
    {
        return lunarYear;
    }

    /**
     * 得到阴历月份
     * @return 阴历月
     */
    public int getLunarMonth()
    {
        return lunarMonth;
    }

    /**
     * 得到阴历日期
     * @return 阴历日期
     */
    public int getLunarDate()
    {
        return lunarDay;
    }


    /**
     * 得到星期编号
     * @return 星期编号0－6
     */
    public int getWeek()
    {
        return week;
    }

    /**
     * 得到天干地支
     * @return 天干地支
     */
    public String getChineseERA()
    {
        return cyclical(lunarYear);
    }

    /**
     * 得到生肖
     * @return 生肖
     */
    public String getAnimal()
    {
        return AnimalsYear(lunarYear);
    }

    /**
     * 得到农历的节气（暂时没有完成）
     * @return 24节气
     */
    public String getSolarTerm()
    {
        return "";
    }

    /**
     * 返回阳历的节日
     * @return 阳历的节日
     */
    public String getSolarFesta()
    {
        StringBuffer sb = new StringBuffer();
        if (solarMonth < 10)
        {
            sb.append("0");
        }
        sb.append(solarMonth);
        if (solarDay < 10)
        {
            sb.append("0");
        }
        sb.append(solarDay);

        String ts = sb.toString();
        for (int i = 0; i < sFtv.length; i++)
        {
            if (sFtv[i].startsWith(ts))
            {
                return sFtv[i].substring(4).trim();
            }
        }
        return "";
    }

    /**
     * 返回阴历的节日
     * @return
     */
    public String getLunarFesta()
    {
        StringBuffer sb = new StringBuffer();
        if (lunarMonth < 10)
        {
            sb.append("0");
        }
        sb.append(lunarMonth);
        if (lunarDay < 10)
        {
            sb.append("0");
        }
        sb.append(lunarDay);

        String ts = sb.toString();
        for (int i = 0; i < lFtv.length; i++)
        {
            if (lFtv[i].startsWith(ts))
            {
                return lFtv[i].substring(4).trim();
            }
        }
        return "";
    }

    /**
     * 传回农历 y年的总天数
     * @param y
     * @return
     */
    private int lYearDays(int y)
    {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1)
        {
            if ( (lunarInfo[y - 1900] & i) != 0)
            {
                sum += 1;
            }
        }
        return (sum + leapDays(y));
    }

    /**
     * 传回农历 y年闰月的天数
     * @param y
     * @return
     */
    private int leapDays(int y)
    {
        if (leapMonth(y) != 0)
        {
            if ( (lunarInfo[y - 1900] & 0x10000) != 0)
            {
                return 30;
            }
            else
            {
                return 29;
            }
        }
        else
        {
            return 0;
        }
    }

    /**
     * 传回农历 y年闰哪个月 1-12 , 没闰传回 0
     * @param y
     * @return
     */
    private int leapMonth(int y)
    {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }

    /**
     * 传回农历 y年m月的总天数
     * @param y
     * @param m
     * @return
     */
    private int monthDays(int y, int m)
    {
        if ( (lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
        {
            return 29;
        }
        else
        {
            return 30;
        }
    }

    /**
     * 传回农历 y年的生肖
     * @param y
     * @return
     */
    private String AnimalsYear(int y)
    {
        return Animals[ (y - 4) % 12];
    }

    /**
     * 传入 月日的offset 传回干支,0=甲子
     * @param num
     * @return
     */
    private String cyclicalm(int num)
    {
        return (Gan[num % 10] + Zhi[num % 12]);
    }

    /**
     * 传入 offset 传回干支, 0=甲子
     * @param y
     * @return
     */
    private String cyclical(int y)
    {
        int num = y - 1900 + 36;
        return (cyclicalm(num));
    }

    /**
     * 传出农历.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
     * @param y
     * @param m
     * @return
     */
    private long[] Lunar(int y, int m)
    {
        long[] nongDate = new long[7];
        int i = 0, temp = 0, leap = 0;
        //Date baseDate = new Date(1900, 1, 31);
        Date baseDate = new GregorianCalendar(1900 + 1900, 1, 31).getTime();
        //Date objDate = new Date(y, m, 1);
        Date objDate = new GregorianCalendar(y + 1900, m, 1).getTime();
        long offset = (objDate.getTime() - baseDate.getTime()) / 86400000L;
        if (y < 2000)
        {
            offset += year19[m - 1];
        }
        if (y > 2000)
        {
            offset += year20[m - 1];
        }
        if (y == 2000)
        {
            offset += year2000[m - 1];
        }
        nongDate[5] = offset + 40;
        nongDate[4] = 14;

        for (i = 1900; i < 2050 && offset > 0; i++)
        {
            temp = lYearDays(i);
            offset -= temp;
            nongDate[4] += 12;
        }
        if (offset < 0)
        {
            offset += temp;
            i--;
            nongDate[4] -= 12;
        }
        nongDate[0] = i;
        nongDate[3] = i - 1864;
        leap = leapMonth(i); // 闰哪个月
        nongDate[6] = 0;

        for (i = 1; i < 13 && offset > 0; i++)
        {
            // 闰月
            if (leap > 0 && i == (leap + 1) && nongDate[6] == 0)
            {
                --i;
                nongDate[6] = 1;
                temp = leapDays( (int) nongDate[0]);
            }
            else
            {
                temp = monthDays( (int) nongDate[0], i);
            }

            // 解除闰月
            if (nongDate[6] == 1 && i == (leap + 1))
            {
                nongDate[6] = 0;
            }
            offset -= temp;
            if (nongDate[6] == 0)
            {
                nongDate[4]++;
            }
        }

        if (offset == 0 && leap > 0 && i == leap + 1)
        {
            if (nongDate[6] == 1)
            {
                nongDate[6] = 0;
            }
            else
            {
                nongDate[6] = 1;
                --i;
                --nongDate[4];
            }
        }
        if (offset < 0)
        {
            offset += temp;
            --i;
            --nongDate[4];
        }
        nongDate[1] = i;
        nongDate[2] = offset + 1;
        return nongDate;
    }

    /**
     * 传出y年m月d日对应的农历.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
     * @param y
     * @param m
     * @param d
     * @return
     */
    private long[] calElement(int y, int m, int d)
    {
        long[] nongDate = new long[7];
        int i = 0, temp = 0, leap = 0;
        //Date baseDate = new Date(0, 0, 31);
        Date baseDate = new GregorianCalendar(0 + 1900, 0, 31).getTime();
        //Date objDate = new Date(y - 1900, m - 1, d);
        Date objDate = new GregorianCalendar(y, m - 1, d).getTime();
        long offset = (objDate.getTime() - baseDate.getTime()) / 86400000L;
        nongDate[5] = offset + 40;
        nongDate[4] = 14;

        for (i = 1900; i < 2050 && offset > 0; i++)
        {
            temp = lYearDays(i);
            offset -= temp;
            nongDate[4] += 12;
        }
        if (offset < 0)
        {
            offset += temp;
            i--;
            nongDate[4] -= 12;
        }
        nongDate[0] = i;
        nongDate[3] = i - 1864;
        leap = leapMonth(i); // 闰哪个月
        nongDate[6] = 0;

        for (i = 1; i < 13 && offset > 0; i++)
        {
            // 闰月
            if (leap > 0 && i == (leap + 1) && nongDate[6] == 0)
            {
                --i;
                nongDate[6] = 1;
                temp = leapDays( (int) nongDate[0]);
            }
            else
            {
                temp = monthDays( (int) nongDate[0], i);
            }

            // 解除闰月
            if (nongDate[6] == 1 && i == (leap + 1))
            {
                nongDate[6] = 0;
            }
            offset -= temp;
            if (nongDate[6] == 0)
            {
                nongDate[4]++;
            }
        }

        if (offset == 0 && leap > 0 && i == leap + 1)
        {
            if (nongDate[6] == 1)
            {
                nongDate[6] = 0;
            }
            else
            {
                nongDate[6] = 1;
                --i;
                --nongDate[4];
            }
        }
        if (offset < 0)
        {
            offset += temp;
            --i;
            --nongDate[4];
        }
        nongDate[1] = i;
        nongDate[2] = offset + 1;
        return nongDate;
    }

    /**
     * 输出农历信息
     * @return
     */
    public String toString()
    {
        String ts = toLocalString();
        String term = getSolarFesta();
        if (term.length() > 0)
        {
            ts += " ";
            ts += term;
        }

        term = getLunarFesta();
        if (term.length() > 0)
        {
            ts += " ";
            ts += term;
        }

        return ts;
    }

}