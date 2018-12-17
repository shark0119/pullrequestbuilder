package com.shark.entity;

import lombok.Data;
import lombok.ToString;

/**
 * CODE Smell 实体
 *
 * @author Shark.Yin
 * @since 1.0
 */

@Data
@ToString
public class CodeSmellDO {
    Integer bugs;
    Integer vulnerabilities;
    Integer codeSmells;
    Double codeCoverage;
}
