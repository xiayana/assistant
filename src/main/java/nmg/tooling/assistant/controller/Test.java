package nmg.tooling.assistant.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpCookie;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 * @author xuzhiqiang
 * @date 2026/1/4
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        LinkedList<String> subjectList = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("sub.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 将全角转为半角
                line = line.replaceAll("（", "(").replaceAll("）", ")");
                subjectList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> errorSubjectList = new ArrayList<>();
        List<List<String>> dataList = new ArrayList<>();
        int count = 0;
        for (String subject: subjectList) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(++count + " " + subject);
            List<String> row = null;
            try {
                row = exec(subject);
            } catch (Exception e) {
                errorSubjectList.add(subject);
                e.printStackTrace();
            }
            if (row != null) {
                dataList.add(row);
            }
        }

        // 将data输出到csv文件中
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.csv"))) {
            for (List<String> rowData : dataList) {
                writer.write(String.join(",", rowData));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(errorSubjectList);
    }

    private static List<String> exec(String subject) {
        // 模拟请求 post
        HttpRequest httpRequest = HttpRequest.post("https://aeolus.bytedance.com/aeolus/vqs/openApi/v2/vizQuery/query?requestId=aeolus.dashboard.xiaoyang.app_1002347.dashboard_16683.sheet_23103.report_121821.dataset_1648410.componentId_121821.queryScene_dashboard.933a9f0b-3892-48c9-8942-5708cfb0892e&reportId=121821")
                .body("{\"version\":4,\"metaData\":{\"appId\":1002347},\"reportId\":121821,\"dataSourceId\":240,\"query\":{\"dataSetId\":1648410,\"dataSetIdList\":[1648410],\"fabricBlendingModelInfo\":{},\"transform\":{\"type\":\"table\"},\"groupByIdList\":[\"1700027636572\",\"1700027636570\",\"1700027636709\",\"1700031768122\"],\"selectIdList\":[],\"fillDateTimeList\":[],\"followFilterRangeList\":[],\"locations\":{\"dimensions\":[\"1700027636572\",\"1700027636570\",\"1700027636709\",\"1700031768122\"],\"rows\":[],\"columns\":[],\"tooltips\":[]},\"dimMetList\":[],\"whereList\":[{\"name\":\"资质-半角\",\"id\":\"1700027636572\",\"preRelation\":\"and\",\"uniqueId\":260104163445018,\"op\":\"like\",\"val\":" +
                        "[\"" + subject + "\"]" +
                        ",\"valOption\":{\"fuzzyLikePattern\":\"Accurate\"},\"option\":{\"isReportFilter\":false,\"isWhereInAggr\":false,\"filterPattern\":\"Condition\"}},{\"name\":\"日期分区\",\"id\":\"1700027636685\",\"preRelation\":\"and\",\"uniqueId\":260104163445019,\"op\":\"lastSync\",\"val\":[1],\"valOption\":{\"anchorOffset\":0,\"datetimeUnit\":\"day\",\"hourSharp\":true},\"option\":{\"isReportFilter\":false,\"isWhereInAggr\":false,\"dateMode\":\"relative\"}}],\"periodCompare\":[],\"calculation\":{\"trendTable\":{}},\"limit\":1000,\"pagination\":{\"size\":1000,\"offset\":0},\"sort\":{\"type\":\"sort\",\"orderByList\":[]},\"topN\":{\"orderByList\":[{\"manualSortList\":[],\"measure\":\"1700027636572\",\"dimensions\":[],\"order\":\"asc\",\"measureUniqueId\":8492020058800387,\"dimensionsUniqueId\":[]}],\"type\":\"by_result\",\"limitUnit\":\"rows\",\"limit\":1,\"dimensions\":[],\"orderByListState\":[{\"manualSortList\":[],\"measure\":\"1700027636572\",\"dimensions\":[],\"order\":\"asc\",\"measureUniqueId\":8492020058800387,\"dimensionsUniqueId\":[]}]},\"paramList\":[],\"cache\":{\"enable\":true,\"expire\":null},\"enableNullJoin\":false,\"hasDynamicField\":false,\"isFirstScreen\":true,\"realMetricTableRouteConfig\":{\"isRealMetricQuery\":false},\"fabricModelInfo\":{},\"extendQuery\":[]},\"schema\":{\"colors\":[],\"rows\":[],\"reportFilterConfig\":{\"structType\":\"LeftRight\",\"layoutSize\":\"Normal\"},\"dimensions\":[{\"roleType\":0,\"index\":0,\"format\":{\"displayName\":\"客户\",\"contentType\":\"link\"},\"aggrConf\":{},\"id\":\"1700027636572\",\"dimMetId\":1700027636572,\"location\":\"dimensions\",\"uniqueId\":8492020058800387,\"aeolusExpr\":\"`资质-半角`\",\"originId\":\"1700027636572\",\"euclidData\":{\"columnName\":\"column2\",\"euclidPillId\":\"1322453\",\"olapType\":2,\"isAdvance\":false}},{\"roleType\":0,\"index\":1,\"format\":{\"displayName\":\"集团\",\"contentType\":\"link\"},\"aggrConf\":{},\"id\":\"1700027636570\",\"dimMetId\":1700027636570,\"location\":\"dimensions\",\"uniqueId\":3970232565837417,\"aeolusExpr\":\"`集团`\",\"originId\":\"1700027636570\",\"euclidData\":{\"columnName\":\"column0\",\"euclidPillId\":\"1322451\",\"olapType\":2,\"isAdvance\":false}},{\"roleType\":0,\"index\":2,\"format\":{\"displayName\":\"是否品牌客户\",\"contentType\":\"link\"},\"aggrConf\":{},\"id\":\"1700027636709\",\"dimMetId\":1700027636709,\"location\":\"dimensions\",\"uniqueId\":4745386567612853,\"aeolusExpr\":\"case when char_length(`集团`) > 0 then '是'\\nelse ''\\nend\",\"originId\":\"1700027636709\",\"euclidData\":{\"columnName\":\"if_brand_1538981\",\"euclidPillId\":\"745523\",\"olapType\":2,\"isAdvance\":false}},{\"roleType\":0,\"index\":3,\"format\":{\"contentType\":\"link\"},\"aggrConf\":{},\"dimMetId\":1700031768122,\"location\":\"dimensions\",\"uniqueId\":230303140933023,\"id\":\"1700031768122\",\"originId\":\"1700031768122\"}],\"parameters\":[],\"whiteList\":[],\"cache\":{\"enable\":true,\"expire\":null},\"details\":[],\"extensions\":{\"data\":{},\"list\":[],\"protocolVersion\":1},\"columns\":[],\"sort\":{\"orderByList\":[],\"type\":\"sort\",\"orderByListState\":[]},\"whereList\":[{\"uniqueId\":260104163445018,\"id\":\"1700027636572\",\"location\":\"whereList\",\"dimMetId\":1700027636572,\"originId\":\"1700027636572\",\"roleType\":0,\"aggrConf\":{},\"format\":{\"contentType\":\"link\"},\"isMetric\":false,\"preRelation\":\"and\",\"filter\":{\"op\":\"like\",\"val\":[\"莆田秀屿星辉医药科技有限公司\"],\"valOption\":{\"fuzzyLikePattern\":\"Accurate\"},\"option\":{\"isReportFilter\":false,\"isWhereInAggr\":false,\"filterPattern\":\"Condition\"}},\"name\":\"资质-半角\",\"dataSetId\":1648410,\"subFilterIds\":[],\"index\":0,\"showEditComponent\":false},{\"uniqueId\":260104163445019,\"id\":\"1700027636685\",\"location\":\"whereList\",\"dimMetId\":1700027636685,\"originId\":\"1700027636685\",\"roleType\":0,\"aggrConf\":{},\"format\":{},\"isMetric\":false,\"preRelation\":\"and\",\"filter\":{\"op\":\"lastSync\",\"val\":[1],\"valOption\":{\"anchorOffset\":0,\"datetimeUnit\":\"day\",\"hourSharp\":true},\"option\":{\"isReportFilter\":false,\"isWhereInAggr\":false,\"dateMode\":\"relative\"}},\"name\":\"日期分区\",\"dataSetId\":1648410,\"subFilterIds\":[],\"index\":1,\"showEditComponent\":false}],\"measures\":[],\"periodCompare\":[],\"subMeasures\":[],\"annotation\":{\"hash\":\"d751713988987e9331980363e24189ce\"},\"sizes\":[],\"topN\":{\"orderByList\":[{\"manualSortList\":[],\"measure\":\"1700027636572\",\"dimensions\":[],\"order\":\"asc\",\"measureUniqueId\":8492020058800387,\"dimensionsUniqueId\":[]}],\"type\":\"by_result\",\"limitUnit\":\"rows\",\"limit\":1,\"dimensions\":[],\"orderByListState\":[{\"manualSortList\":[],\"measure\":\"1700027636572\",\"dimensions\":[],\"order\":\"asc\",\"measureUniqueId\":8492020058800387,\"dimensionsUniqueId\":[]}]},\"enableNullJoin\":false,\"drill\":[],\"referenceLine\":[],\"display\":{\"type\":\"table\",\"conf\":{\"bodyFontUnderline\":false,\"fixedIndex\":-1,\"headerFontColor\":\"#333333\",\"headerColor\":\"#F7F8FA\",\"bodyFontSize\":12,\"gridLine\":true,\"headerFontUnderline\":false,\"gridLineFrameWidth\":1,\"headerFontSize\":12,\"alternateRowColor\":\"#FBFBFC\",\"headerBackground\":true,\"gridLineVertical\":true,\"colSpaceMode\":\"loose\",\"gridLineFrameStyle\":\"solid\",\"headerVertical\":true,\"alignDimensionRow\":\"auto\",\"autoWrap\":true,\"alignDimensionColumn\":\"auto\",\"gridLineVerticalWidth\":1,\"version\":33,\"tableStyle\":\"classic\",\"gridLineVerticalStyle\":\"solid\",\"gridLineHorizontal\":true,\"headerFontItalic\":false,\"headerHorizontal\":true,\"gridLineFrame\":true,\"bodyFontItalic\":false,\"rowSpaceMode\":\"loose\",\"bodyFontColor\":\"#666666\",\"customColWidth\":null,\"rowHeaderWidth\":null,\"gridLineColor\":\"#E1E4E8\",\"gridLineHorizontalStyle\":\"solid\",\"headerFontBold\":true,\"columnNum\":2,\"alternateRow\":true,\"gridLineHorizontalWidth\":1,\"alignMeasure\":\"right\",\"specialValue\":{\"measures\":\"zero\",\"renderInvalid\":\"zero\",\"dimensions\":\"dash\"},\"alignDimension\":\"left\",\"bodyFontBold\":false,\"display\":\"standard\",\"render\":\"dom\",\"compactDirection\":\"horizontal\"},\"enableAdvisor\":true,\"queryType\":\"table\"},\"tableCalculation\":{\"rules\":[]},\"realMetricTableRouteConfig\":{\"isRealMetricQuery\":false}},\"display\":{\"type\":\"table\",\"conf\":{\"bodyFontUnderline\":false,\"fixedIndex\":-1,\"headerFontColor\":\"#333333\",\"headerColor\":\"#F7F8FA\",\"bodyFontSize\":12,\"gridLine\":true,\"headerFontUnderline\":false,\"gridLineFrameWidth\":1,\"headerFontSize\":12,\"alternateRowColor\":\"#FBFBFC\",\"headerBackground\":true,\"gridLineVertical\":true,\"colSpaceMode\":\"loose\",\"gridLineFrameStyle\":\"solid\",\"headerVertical\":true,\"alignDimensionRow\":\"auto\",\"autoWrap\":true,\"alignDimensionColumn\":\"auto\",\"gridLineVerticalWidth\":1,\"version\":33,\"tableStyle\":\"classic\",\"gridLineVerticalStyle\":\"solid\",\"gridLineHorizontal\":true,\"headerFontItalic\":false,\"headerHorizontal\":true,\"gridLineFrame\":true,\"bodyFontItalic\":false,\"rowSpaceMode\":\"loose\",\"bodyFontColor\":\"#666666\",\"customColWidth\":null,\"rowHeaderWidth\":null,\"gridLineColor\":\"#E1E4E8\",\"gridLineHorizontalStyle\":\"solid\",\"headerFontBold\":true,\"columnNum\":2,\"alternateRow\":true,\"gridLineHorizontalWidth\":1,\"alignMeasure\":\"right\",\"specialValue\":{\"measures\":\"zero\",\"renderInvalid\":\"zero\",\"dimensions\":\"dash\"},\"alignDimension\":\"left\",\"bodyFontBold\":false,\"display\":\"standard\",\"render\":\"dom\",\"compactDirection\":\"horizontal\"},\"enableAdvisor\":true,\"queryType\":\"table\",\"fieldsFormat\":{\"1700027636572\":{\"displayName\":\"客户\",\"contentType\":\"link\"},\"1700027636570\":{\"displayName\":\"集团\",\"contentType\":\"link\"},\"1700027636709\":{\"displayName\":\"是否品牌客户\",\"contentType\":\"link\"},\"1700031768122\":{\"contentType\":\"link\"}}},\"requestId\":\"aeolus.dashboard.xiaoyang.app_1002347.dashboard_16683.sheet_23103.report_121821.dataset_1648410.componentId_121821.queryScene_dashboard.933a9f0b-3892-48c9-8942-5708cfb0892e\",\"dashboardId\":16683,\"switchConf\":{\"dashboardId\":16683,\"waitForDataReady\":0}}");
        // 设置 cookie: ttwid=1%7CVKTjKH3C3mVf4P0wkicuaKAQwaNEWa89KdaO2jFtAF4%7C1766742675%7C0b03e471e1491c29ecc7a95f308ce4a3eb6e78910fc8c2038bc8f7bec64991ea; aeolus_gray_appid=1002347; aeolus_gray_agentid=; aeolus_gray_route=external; localeId=zh_CN; aeolus_gray_user=xiaoyang
        ArrayList<HttpCookie> httpCookies = new ArrayList<>();
        httpCookies.add(new HttpCookie("ttwid", "1%7CVKTjKH3C3mVf4P0wkicuaKAQwaNEWa89KdaO2jFtAF4%7C1766742675%7C0b03e471e1491c29ecc7a95f308ce4a3eb6e78910fc8c2038bc8f7bec64991ea"));
        httpCookies.add(new HttpCookie("aeolus_gray_appid", "1002347"));
        httpCookies.add(new HttpCookie("aeolus_gray_agentid", ""));
        httpCookies.add(new HttpCookie("aeolus_gray_route", "external"));
        httpCookies.add(new HttpCookie("localeId", "zh_CN"));
        httpCookies.add(new HttpCookie("aeolus_gray_user", "xiaoyang"));
//        httpRequest.cookie(httpCookies);

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("accept", "application/json, text/plain, */*");
//        headerMap.put("accept-encoding", "gzip, deflate, br, zstd");
        headerMap.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
        headerMap.put("app-id", "1002347");
        headerMap.put("cache-control", "no-cache");
        headerMap.put("connection", "keep-alive");
        headerMap.put("content-language", "zh-CN");
        headerMap.put("content-type", "application/json");
        headerMap.put("cookie", "ttwid=1%7CVKTjKH3C3mVf4P0wkicuaKAQwaNEWa89KdaO2jFtAF4%7C1766742675%7C0b03e471e1491c29ecc7a95f308ce4a3eb6e78910fc8c2038bc8f7bec64991ea; aeolus_gray_appid=1002347; aeolus_gray_agentid=; aeolus_gray_route=external; localeId=zh_CN; aeolus_gray_user=xiaoyang");
        headerMap.put("data-format-unit", "auto");
        headerMap.put("external-header", "8755749afece8db100ab6fd1d4d11e98,200697,0");
        headerMap.put("host", "aeolus.bytedance.com");
        headerMap.put("open-api-token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBfaWQiOiI5RHJEcCtXb2xvcEFucVBoQkw2UVpEU2FtUVJKWHE4YzVhVHpGaFp6YnMxbkpSc1F0aTVmZ0RlWDNiUmhMK00yIiwiZW1haWxfcHJlZml4IjoieGlhb3lhbmciLCJleHBpcmVfdGltZSI6IjIwMjYtMDEtMDUgMDg6MzQ6NDAiLCJ0eXBlIjoidXNlciJ9.c-cbcYoX23Au4DSI76S7Ya4tuf5so96DjJA8myCTSUw");
        headerMap.put("origin", "https://aeolus.bytedance.com");
        headerMap.put("pragma", "no-cache");
        headerMap.put("referer", "https://aeolus.bytedance.com/aeolus-open/");
        headerMap.put("request-id", "e934a093-0a9c-45ef-af12-74b387c6ff75.mEG6Aqq8yxBszrHFkEyVsS.bi.95");
        headerMap.put("request-timestamp", "1767517825883");
        headerMap.put("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"");
        headerMap.put("sec-ch-ua-mobile", "?0");
        headerMap.put("sec-ch-ua-platform", "\"Windows\"");
        headerMap.put("sec-fetch-dest", "empty");
        headerMap.put("sec-fetch-mode", "cors");
        headerMap.put("sec-fetch-site", "same-origin");
        headerMap.put("sec-fetch-storage-access", "active");
        headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36");
        headerMap.put("x-aeolus-gray-env", "aeolus-gray");
        headerMap.put("x-page-url", "https://aeolus.bytedance.com/aeolus-open/#/external/dashboard/16683?appId=1002347&emailPrefix=xiaoyang&externalHeader=8755749afece8db100ab6fd1d4d11e98%2C200697%2C0&feature=&inline=true&lang=zh_CN&layoutType=2&sdkMeta=%7B%22version%22%3A%222.3.1%22%2C%22origin%22%3A%22https%3A%2F%2Fagent.oceanengine.com%22%2C%22pathname%22%3A%22%2Fapps%2Fbi%2Fdashboard%2Fview%22%2C%22component%22%3A%22aeolus-dashboard%22%7D&sheetId=23103&token=");
        headerMap.put("x-request-id", "e934a093-0a9c-45ef-af12-74b387c6ff75.mEG6Aqq8yxBszrHFkEyVsS.bi.95");
        httpRequest.headerMap(headerMap, true);

        String body = httpRequest.execute().charset("UTF-8").body();
        List<String> row = null;
        try {
            JSONObject jsonObject = JSON.parseObject(body);
            JSONObject vizData = jsonObject.getJSONObject("data").getJSONObject("vizData");
            JSONArray datasets = vizData.getJSONArray("datasets");
            if (datasets.isEmpty()) {
                return Arrays.asList("", "", "", subject);
            }
            JSONObject dataset = datasets.getJSONObject(0);
            row = dataset.values().stream().map(Object::toString).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(subject + " : " + body);
            throw new RuntimeException(e);
        }
        return row;
    }

}
