package com.shark.report;

import com.shark.entity.CodeCoverageDO;
import com.shark.entity.CodeSmellDO;

import java.util.List;

/**
 * 检测报告分析
 *
 * @author Shark.Yin
 * @since 1.0
 */
public interface ReportAnalyzer {
    /**
     * 获取检测报告总结
     * @return 检测报告总结
     */
    String getReportSummary();

    /**
     * @return 是否通过检测
     */
    boolean isPass();
    /**
     * 获取检测报告中的代码坏味道
     * @return 代码坏味道集合
     */
    List<CodeSmellDO> getCodeSmells();

    /**
     * 获取代码覆盖率详情
     * @return 代码覆盖率详情
     */
    CodeCoverageDO getCodeCoverage();

    /**
     * @return 获取该分析器支持的元数据版本信息
     */
    String getSupportVersionInfo();
}
