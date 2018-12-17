package com.shark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 配置参数实体
 *
 * @author Shark.Yin
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConfigParameterDO {
    Integer codeSmells;
    Double codeCoverage;
}
