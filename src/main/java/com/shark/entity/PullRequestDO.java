package com.shark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pr 相关信息
 *
 * @author Shark.Yin
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PullRequestDO {
    String body;
    Boolean pass;
    PullRequestOperateType operateType;
}
