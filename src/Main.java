import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("args = " + args);
        String url="https://www.educity.cn/ucapi/uc/paper/loadScantron.do";
        String url2="https://www.educity.cn/ucapi/uc/paper/loadShitiInfo.do";

        try {
            Map<String, String> head=new HashMap<String,String>();
            head.put("clienttype","PC");
            head.put("cookie","_sid_=072e40382209064d0c2b6069a01a56d8; fromUrl=https%3A%2F%2Fuc.educity.cn%2FpersonalCenter%2FmyOrder.html; Hm_lvt_555d9dcffdcb317595de82b0fc125cdf=1554703033; _rme=T; cstk=0bb09bb0e1769601cdd3c4d44631cc88; uid=8504356; uname=%u4E07%u6587%u6770; Hm_lpvt_555d9dcffdcb317595de82b0fc125cdf=1554707888");
            head.put("user-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
            Map<String, String> head2=new HashMap<String,String>();
            head2.put("clienttype","PC");
            head2.put("cookie","_sid_=072e40382209064d0c2b6069a01a56d8; fromUrl=https%3A%2F%2Fuc.educity.cn%2FpersonalCenter%2FmyOrder.html; Hm_lvt_555d9dcffdcb317595de82b0fc125cdf=1554703033; _rme=T; cstk=0bb09bb0e1769601cdd3c4d44631cc88; uid=8504356; uname=%u4E07%u6587%u6770; Hm_lpvt_555d9dcffdcb317595de82b0fc125cdf=1554708375");
            head2.put("user-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

            Document doc = Jsoup.connect(url).headers(head).data("paperLogId","2150321").ignoreContentType(true).post();
            System.out.println(doc.body().text());
            JSONObject json1=JSON.parseObject(doc.body().text());
            System.out.println(json1.get("model"));
            JSONObject json2=JSON.parseObject(json1.get("model")+"");
            System.out.println(json2.get("data"));
            JSONArray json3= JSON.parseArray(json2.get("data")+"");

                System.out.println(json3.get(0));
                JSONObject json4=JSON.parseObject(json3.get(0)+"");
                System.out.println(json4.get("shitiLogList")+"");
                JSONArray json5= JSON.parseArray(json4.get("shitiLogList")+"");
            for(int i=0;i<json5.size();i++){
                //System.out.println(json5.get(i)+"");
                JSONObject json6=JSON.parseObject(json5.get(i)+"");
                //System.out.println(json6.get("stId")+"");
                String stId=json6.get("stId")+"";

                Document doc2 = Jsoup.connect(url2).headers(head2).data("id",stId).ignoreContentType(true).post();
                //System.out.println(doc2.body().text());
                try {
                    JSONObject json7 = JSON.parseObject(doc2.body().text());

                //System.out.println(json7.get("model")+"");
                JSONObject json8=JSON.parseObject(json7.get("model")+"");
                JSONObject json9=JSON.parseObject(json8.get("shiti")+"");
                System.out.println("* "+json9.get("analysis")+"");
                }catch (JSONException e){
                    continue;
                }




            }




        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
