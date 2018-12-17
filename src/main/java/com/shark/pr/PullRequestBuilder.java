package com.shark.pr;

import com.shark.commom.AbstractLogable;
import com.shark.entity.PullRequestDO;
import com.shark.entity.PullRequestOperateType;
import com.shark.report.ReportAnalyzer;
import com.shark.report.SonarqubeReportAnalyzer;

/**
 * 业务调用
 *
 * @author Shark.Yin
 * @since 1.0
 */
public class PullRequestBuilder extends AbstractLogable {

    public static void buildPRFromMaven(String projectPath) {

    }

    public static void buildPRFromSonarqube(String sonarqubeUrl, String project,
                                            String userName, String password,
                                            String gitUrl, String token) {
        ReportAnalyzer analyzer = new SonarqubeReportAnalyzer(sonarqubeUrl, project, userName, password);
        PullRequestOperateType operateType = PullRequestOperateType.COMMENT;
        PullRequestDealer dealer = new PullRequestDealer(gitUrl, token);
        PullRequestDO pr = new PullRequestDO(analyzer.reportSummary(), analyzer.pass(), operateType);
        dealer.comment(pr);
    }
}
