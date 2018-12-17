package com.shark.report;

import com.shark.entity.CodeCoverageDO;
import com.shark.entity.CodeSmellDO;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 只支持对 jacoco 与 pmd 生成的报告进行分析
 *
 * @author Shark.Yin
 * @since 1.0
 */
public class MavenReportAnalyzer implements ReportAnalyzer {

    private static final String CODE_COVERAGE_REPORT = "";
    private static final String CODE_SMELL_REPORT = "";

    @Getter
    private List<CodeSmellDO> codeSmells;
    @Getter
    private CodeCoverageDO codeCoverage;
    @Getter
    private boolean pass;
    @Getter
    private String reportSummary;
    @Getter
    private String supportVersionInfo = "pmd & jacoco";
    private String codeCoverageReportPath = "target/sit/jacoco/jacoco.xml";
    private String codeSmellReportPath = "target/pmd.xml";

    public MavenReportAnalyzer(String projectBaseDir) {
        codeSmells = new ArrayList<>();
        this.codeCoverageReportPath = projectBaseDir + CODE_COVERAGE_REPORT;
        this.codeSmellReportPath = projectBaseDir + CODE_SMELL_REPORT;
    }

}
