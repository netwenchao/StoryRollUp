package com.luckywc.core.Date;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.*;

/**
 * ũ�����
 */

public class LunarCalendar
{

    /**
     * ������
     */
    private int solarYear = 0;

    /**
     * ������
     */
    private int solarMonth = 0;

    /**
     * ������
     */
    private int solarDay = 0;

    /**
     * ����
     */
    private int week = 0;

    /**
     * �������
     */
    private int lunarYear = 0;

    /**
     * �����·�
     */
    private int lunarMonth = 0;

    /**
     * ��������
     */
    private int lunarDay = 0;

    /**
     * ������Ϣ
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
     * �����·�
     */
    final private String[] nStr1 = new String[]
        {
        "", "��", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "ʮһ", "ʮ��"};

    /**
     * ���
     */
    final private String[] Gan = new String[]
        {
        "��", "��", "��", "��", "��", "��", "��", "��", "��", "��"};

    /**
     * ��֧
     */
    final private String[] Zhi = new String[]
        {
        "��", "��", "��", "î", "��", "��", "��", "δ", "��", "��", "��", "��"};

    /**
     * ��Ф
     */
    final private String[] Animals = new String[]
        {
        "��", "ţ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��"};

    /**
     * ��������
     */
    final private String[] solarTerm = new String[]
        {
        "С��", "��", "����", "��ˮ", "����", "����", "����", "����", "����", "С��", "â��", "����",
        "С��", "����", "����", "����", "��¶", "���", "��¶", "˪��", "����", "Сѩ", "��ѩ", "����"};

    /**
     * ��������
     */
    final private String[] sFtv = new String[]
        {
        "0101*Ԫ��", "0214 ���˽�", "0308 ��Ů��","0312 ֲ����", "0315 ������Ȩ����", "0401 ���˽�",
        "0501 �Ͷ���", "0504 �����", "0512 ��ʿ��", "0601 ��ͯ��", "0701 ������", "0801 ������",
        "0808 ���׽�", "0909 ë����������", "0910 ��ʦ��","0928 ���ӵ���", "1001*�����", "1006 ���˽�",
        "1024 ���Ϲ���", "1112 ����ɽ����", "1220 ���Żع�","1225 ʥ����", "1226 ë�󶫵���"};

    /**
     * ��������
     */
    final private String[] lFtv = new String[]
        {
        "0101*ũ������", "0115 Ԫ����", "0505 �����","0707 ��Ϧ���˽�", "0815 �����", "0909 ������",
        "1208 ���˽�", "1224 С��", "0100*��Ϧ"};

    /**
     * �·��е���������
     */
    final private String[] chinaDay = new String[]
        {
        "","��һ","����","����","����","����","����","����","����","����","��ʮ",
        "ʮһ","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","��ʮ",
        "إһ","إ��","إ��","إ��","إ��","إ��","إ��","إ��","إ��","��ʮ",
        "��һ",
    };

    /**
     * ��������
     */
    final private String[] chinaWeek = new String[]
        {
        "������","����һ","���ڶ�","������","������","������","������","������",
    };


    /**
     * �������캯��
     * @param year �������
     * @param month �����·�
     * @param day ��������
     */
    public LunarCalendar(int year, int month, int day)
    {
        //�ж��·��ǲ�����1��12֮��
        if (month > 12 || month < 1)
        {
            throw new RuntimeException("�·�����ֻ����1��12֮�䣡");
        }

        //�ж�����ǲ�����1��31֮��
        if (day > 31 || day < 1)
        {
            throw new RuntimeException("����ֻ����1��31֮�䣡");
        }

        //��¼������Ϣ
        this.solarYear = year;
        this.solarMonth = month;
        this.solarDay = day;

        //����������
        Date date = new Date();
        date.setDate(day);
        date.setYear(year-1900);
        date.setMonth(month-1);

        //�ж������ǲ�����Ч
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
            throw new RuntimeException(temp.toString() + "������Ч�����ڣ�");
        }

        //�õ�������Ϣ��ע���Calendar��õ����ڻ����
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

        //ת��������ũ��
        long[] l = calElement(year, month, day);

        //��¼ũ����Ϣ
        this.lunarYear = (int)l[0];
        this.lunarMonth = (int)l[1];
        this.lunarDay = (int)l[2];
    }

    /**
     * �������캯��
     * @param pDate java.util.Date ����������
     */
    public LunarCalendar(Date pDate)
    {
        //��¼������Ϣ
        this.solarYear = pDate.getYear() + 1900;
        this.solarMonth = pDate.getMonth() + 1;
        this.solarDay = pDate.getDate();

        //�õ�������Ϣ��ע���Calendar��õ����ڻ����
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

        //������ũ����ת��
        long[] l = calElement(solarYear, solarMonth, solarDay);

        //��¼ũ����Ϣ
        this.lunarYear = (int) l[0];
        this.lunarMonth = (int) l[1];
        this.lunarDay = (int) l[2];
    }

    /**
     * �������캯��
     * @param pCalendar java.util.Calendar ����������
     */
    public LunarCalendar(Calendar pCalendar)
    {
        //��¼������Ϣ
        this.solarYear = pCalendar.get(Calendar.YEAR);
        this.solarMonth = pCalendar.get(Calendar.MONTH) + 1;
        this.solarDay = pCalendar.get(Calendar.DATE);
        this.week = pCalendar.get(Calendar.DAY_OF_WEEK)-1;

        //ũ����������ת��
        long[] l = calElement(solarYear, solarMonth, solarDay);

        //��¼ũ����Ϣ
        this.lunarYear = (int) l[0];
        this.lunarMonth = (int) l[1];
        this.lunarDay = (int) l[2];
    }

    /**
     * �������캯��������Ϊ����
     */
    public LunarCalendar()
    {
        //���������
        Calendar pCalendar = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);

        //��¼������Ϣ
        this.solarYear = pCalendar.get(Calendar.YEAR);
        this.solarMonth = pCalendar.get(Calendar.MONTH) + 1;
        this.solarDay = pCalendar.get(Calendar.DATE);
        this.week = pCalendar.get(Calendar.DAY_OF_WEEK)-1;

        //����ת����ũ��
        long[] l = calElement(solarYear, solarMonth, solarDay);

        //��¼ũ����Ϣ
        this.lunarYear = (int) l[0];
        this.lunarMonth = (int) l[1];
        this.lunarDay = (int) l[2];

    }

    /**
     * �õ�������������Ϣ��ʽΪ��2005��4��14�� ������ ũ��2005�꣨���� �������³���
     * @return 2005��4��14�� ������ ũ��2005�꣨���� �������³���
     */
    public String toLocalString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(solarYear);
        sb.append("��");
        sb.append(solarMonth);
        sb.append("��");
        sb.append(solarDay);
        sb.append("�� ");
        sb.append(chinaWeek[week]);
        sb.append(" ũ��");
        sb.append(lunarYear);
        sb.append("�꣨");
        sb.append(cyclical(lunarYear));
        sb.append(" ");
        sb.append(AnimalsYear(lunarYear));
        sb.append("��");
        sb.append(nStr1[lunarMonth]);
        sb.append("��");
        sb.append(chinaDay[lunarDay]);

        return sb.toString();
    }

    /**
     * �õ�������Ϣ����ʽΪ��ũ��2005�꣨���� �������³��� ������
     * @return ũ��2005�꣨���� �������³��� ������
     */
    public String toChinaString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(" ũ��");
        sb.append(lunarYear);
        sb.append("�꣨");
        sb.append(cyclical(lunarYear));
        sb.append(" ");
        sb.append(AnimalsYear(lunarYear));
        sb.append("��");
        sb.append(nStr1[lunarMonth]);
        sb.append("��");
        sb.append(chinaDay[lunarDay]);
        sb.append(" ");
        sb.append(chinaWeek[week]);

        return sb.toString();
    }

    /**
     * �õ��������
     * @return ������
     */
    public int getSolarYear()
    {
        return solarYear;
    }

    /**
     * �õ������·�
     * @return ������
     */
    public int getSolarMonth()
    {
        return solarMonth;
    }

    /**
     * �õ���������
     * @return ��������
     */
    public int getSolarDate()
    {
        return solarDay;
    }

    /**
     * �õ��������
     * @return ������
     */
    public int getLunarYear()
    {
        return lunarYear;
    }

    /**
     * �õ������·�
     * @return ������
     */
    public int getLunarMonth()
    {
        return lunarMonth;
    }

    /**
     * �õ���������
     * @return ��������
     */
    public int getLunarDate()
    {
        return lunarDay;
    }


    /**
     * �õ����ڱ��
     * @return ���ڱ��0��6
     */
    public int getWeek()
    {
        return week;
    }

    /**
     * �õ���ɵ�֧
     * @return ��ɵ�֧
     */
    public String getChineseERA()
    {
        return cyclical(lunarYear);
    }

    /**
     * �õ���Ф
     * @return ��Ф
     */
    public String getAnimal()
    {
        return AnimalsYear(lunarYear);
    }

    /**
     * �õ�ũ���Ľ�������ʱû����ɣ�
     * @return 24����
     */
    public String getSolarTerm()
    {
        return "";
    }

    /**
     * ���������Ľ���
     * @return �����Ľ���
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
     * ���������Ľ���
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
     * ����ũ�� y���������
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
     * ����ũ�� y�����µ�����
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
     * ����ũ�� y�����ĸ��� 1-12 , û�򴫻� 0
     * @param y
     * @return
     */
    private int leapMonth(int y)
    {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }

    /**
     * ����ũ�� y��m�µ�������
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
     * ����ũ�� y�����Ф
     * @param y
     * @return
     */
    private String AnimalsYear(int y)
    {
        return Animals[ (y - 4) % 12];
    }

    /**
     * ���� ���յ�offset ���ظ�֧,0=����
     * @param num
     * @return
     */
    private String cyclicalm(int num)
    {
        return (Gan[num % 10] + Zhi[num % 12]);
    }

    /**
     * ���� offset ���ظ�֧, 0=����
     * @param y
     * @return
     */
    private String cyclical(int y)
    {
        int num = y - 1900 + 36;
        return (cyclicalm(num));
    }

    /**
     * ����ũ��.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
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
        leap = leapMonth(i); // ���ĸ���
        nongDate[6] = 0;

        for (i = 1; i < 13 && offset > 0; i++)
        {
            // ����
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

            // �������
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
     * ����y��m��d�ն�Ӧ��ũ��.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
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
        leap = leapMonth(i); // ���ĸ���
        nongDate[6] = 0;

        for (i = 1; i < 13 && offset > 0; i++)
        {
            // ����
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

            // �������
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
     * ���ũ����Ϣ
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