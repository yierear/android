package com.example.finnal;

//推荐文本库--->改成数据库
public class home {

    private static String[] names={"电影院","活动室","自习室1","自习室2",
            "研讨室1","研讨室2","开发室"};
    private static String[] news={"配备投影仪", "空旷场地", "安静","安静",
            "隔音", "隔音", "带电脑"};

    private static int[] icons={R.drawable.cinema,R.drawable.activityroom,
            R.drawable.selfstudyroom,R.drawable.selfstudyroom,
            R.drawable.seminarroom,R.drawable.seminarroom,R.drawable.developmentroom};

    public static String[] getNames() {
        return names;
    }

    public static String[] getNews() {
        return news;
    }

    public static int[] getIcons() {
        return icons;
    }
}
