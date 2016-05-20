package com.malus.pushshow.http;

import android.text.TextUtils;

import com.malus.pushshow.utils.SPHelper;

/********************
 * 作者：malus
 * 日期：16/2/23
 * 时间：下午2:02
 * 注释：
 ********************/
public class Url {
    public static final String ZL_SEARCH_JOB = "http://mi.zhaopin.com/iPhone/My/Recommend?channel=android&city=685&d=F02BAF04B19136422CA9A025AD6858F7&key=135486907262855&keyword=编辑&pageIndex=1&pageSize=40&v=6.01&t=1456207171&e=4f09f650ab5ddce798ac75ba6fe841a5";
    public static final String ZL_JOB_DETAIL1 = "http://mi.zhaopin.com/iphone/Position/Detail?channel=apple&d=F02BAF04B19136422CA9A025AD6858F7&key=135486907262855&need5Dot0=1&number=CC263615414J90250008000&v=6.10&t=1457404749&e=0ffbbe5f70c0ed9553141a46d8b94bcb";
    public static final String ZL_JOB_DETAIL2 = "http://mi.zhaopin.com/iphone/Position/Detail?channel=apple&d=F02BAF04B19136422CA9A025AD6858F7&key=135486907262855&need5Dot0=1&number=CC699455925J90250003000&v=6.10&t=1457404749&e=fb779767647468405f5190954d9407b9";
    public static final String ZL_JOB_DETAIL3 = "http://mi.zhaopin.com/iphone/Position/Detail?channel=apple&d=F02BAF04B19136422CA9A025AD6858F7&key=135486907262855&need5Dot0=1&number=CC263615414J90250008000&v=6.10&t=1457405590&e=97326af9f2ffe6f4fd907e35f3c1153b";
    public static final String ZL_JOB_DETAIL4 = "http://mi.zhaopin.com/iphone/Position/Detail?channel=apple&d=F02BAF04B19136422CA9A025AD6858F7&key=135486907262855&need5Dot0=1&number=CC323790117J90252481000&v=6.10&t=1457405790&e=71224b09507503b0e3be3ef8fc3a081a";

    public static final String JOB_51_City_PROVINCE = "http://api.51job.com/api/datadict/get_jobarea.php?ddtype=dd_jobarea&code=&language=c&productname=51job&partner=f86fc3eba6eebd58e4f5cdcf32a96444&uuid=a99088b8b513d21e4afc0c7d33f956e5&version=321&guid=25deb3c7493b03ce3936a801fed91844";

    public static String getZlSearchJob(String keyword,int pageIndex){
        return "http://mi.zhaopin.com/iPhone/My/Recommend?channel=android&city=685&d=F02BAF04B19136422CA9A025AD6858F7&key=135486907262855&keyword="+keyword+"&pageIndex="+pageIndex+"&pageSize=40&v=6.01&t=1456207171&e=4f09f650ab5ddce798ac75ba6fe841a5";
    }

    public static String getJob51CityCity(String code){
        return "http://api.51job.com/api/datadict/get_jobarea.php?ddtype=dd_jobarea&code="+code+"&language=c&productname=51job&partner=f86fc3eba6eebd58e4f5cdcf32a96444&uuid=a99088b8b513d21e4afc0c7d33f956e5&version=321&guid=25deb3c7493b03ce3936a801fed91844";
    }
    public static String get51SearchJob(String keyword,int pageIndex){
        String code = "000000";
        if(!TextUtils.isEmpty(SPHelper.getCityCode())){
            code = SPHelper.getCityCode();
        }
        return "http://api.51job.com/api/job/search_job_list.php?postchannel=0000&keyword="+keyword+"&keywordtype=2&jobarea="+code+"&pagesize=20&pageno="+pageIndex+"&accountid=&key=&productname=51job&partner=f86fc3eba6eebd58e4f5cdcf32a96444&uuid=a99088b8b513d21e4afc0c7d33f956e5&version=321&guid=25deb3c7493b03ce3936a801fed91844";
    }
    public static String get51JobDetail(String jobId){
        return "http://api.51job.com/api/job/get_job_info.php?jobid="+jobId+"&accountid=&key=&productname=51job&partner=f86fc3eba6eebd58e4f5cdcf32a96444&uuid=a99088b8b513d21e4afc0c7d33f956e5&version=321&guid=25deb3c7493b03ce3936a801fed91844";
    }
    public static String get51CompDetail(String coid){
        return "http://api.51job.com/api/job/get_co_info.php?coid="+coid+"&productname=51job&partner=f86fc3eba6eebd58e4f5cdcf32a96444&uuid=a99088b8b513d21e4afc0c7d33f956e5&version=321&guid=25deb3c7493b03ce3936a801fed91844";
    }
    public static String get51CompJobs(String coid){
        return "http://api.51job.com/api/job/get_co_alljob.php?jobarea=&coid="+coid+"&pageno=1&pagesize=20&productname=51job&partner=f86fc3eba6eebd58e4f5cdcf32a96444&uuid=a99088b8b513d21e4afc0c7d33f956e5&version=321&guid=25deb3c7493b03ce3936a801fed91844";
    }

}
