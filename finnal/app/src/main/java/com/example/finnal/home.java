package com.example.finnal;


public class home {

    private static String[] names={"电影院","活动室","自习室 ","研讨室","开发室"};
    private static String[] news={"设施：投影仪，椅子若干，麦克风\n"+
            "容纳人数：30人\n"+
            "活动：分享会，观赏电影",
            "设施：桌子4张，壁挂白板2张\n" +
                    "容纳人数：14人\n" +
                    "活动：自习，小声讨论\n",
            "设施：桌椅若干，丰富藏书\n"+
            "容纳人数：40人\n"+
            "活动：自习、借阅图书",
            "设施：会议桌，椅子若干\n"+
            "容纳人数：20人\n"+
            "活动：小组讨论",
            "设施：桌椅、电脑、白板\n"+
            "容纳人数：10人\n"+
            "活动：借用电脑"};


    private static int[] icons={R.drawable.cinema,R.drawable.activityroom,R.drawable.selfstudyroom,R.drawable.seminarroom,R.drawable.developmentroom};

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
