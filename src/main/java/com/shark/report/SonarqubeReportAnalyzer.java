package com.shark.report;

import com.shark.commom.AbstractLogable;
import com.shark.entity.CodeCoverageDO;
import com.shark.entity.CodeSmellDO;
import com.shark.entity.ConfigParameterDO;
import lombok.Getter;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 对 Sonarqube 生成的报告进行分析
 *
 * @author Shark.Yin
 * @since 1.0
 */
public class SonarqubeReportAnalyzer extends AbstractLogable implements ReportAnalyzer {

    @Getter
    private List<CodeSmellDO> codeSmells;
    @Getter
    private CodeCoverageDO codeCoverage;
    @Getter
    private boolean pass;
    @Getter
    private String reportSummary;
    @Getter
    private String supportVersionInfo = "10.10.1.51:9000 sonarqube version";
    private ConfigParameterDO cfgParameter;

    public SonarqubeReportAnalyzer(ConfigParameterDO cfgParameter, String url, String projectKey, String userName, String password) throws IOException {
        this.cfgParameter = cfgParameter;
        String data = fetchData(url, projectKey, userName, password);
        analyzeResp(data);
    }

    private String fetchData(String uri, String projectKey, String userName, String password) throws IOException {
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("componentKey", projectKey));
        params.add(new BasicNameValuePair("metricKeys", "bugs,new_bugs,vulnerabilities,new_vulnerabilities,code_smells,new_code_smells,coverage,new_coverage"));

        String url = uri + "/api/measures/component?"
                + EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
        HttpGet request = new HttpGet(url);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //转换为字节输入流
            try (
                    BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), Consts.UTF_8));
            ) {
                String body;
                StringBuilder stringBuilder = new StringBuilder();
                while ((body = br.readLine()) != null) {
                    stringBuilder.append(body);
                }
                return stringBuilder.toString();
            }
        }
        throw new IllegalStateException("Response of " + url + " is none.");
    }

    private void analyzeResp(String data) {
        JSONObject object = new JSONObject(data);
        JSONArray array = object.getJSONObject("component").getJSONArray("measures");
        HashMap<String, String> measures = new HashMap<>(10);
        for (int i = 0; i < array.length(); i++) {
            measures.put(array.getJSONObject(i).getString("metric"), array.getJSONObject(i).getString("value"));
        }
        reportSummary = measures.toString();
        codeCoverage = new CodeCoverageDO(measures.get("coverage"));
        codeSmells = Collections.emptyList();
        int smells = Integer.parseInt(measures.get("bugs"))
                + Integer.parseInt(measures.get("vulnerabilities"))
                + Integer.parseInt(measures.get("code_smells"));
        if (smells > cfgParameter.getCodeSmells()) {
            pass = false;
        } else if (Double.parseDouble(codeCoverage.getInstruction()) < cfgParameter.getCodeCoverage()) {
            pass = false;
        } else {
            pass = true;
        }
    }

}
