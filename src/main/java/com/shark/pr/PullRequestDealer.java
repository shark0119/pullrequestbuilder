package com.shark.pr;

import com.shark.commom.AbstractLogable;
import com.shark.entity.PullRequestDO;

/**
 * 对 pull request 进行回写
 *
 * @author Shark.Yin
 * @since 1.0
 */
public class PullRequestDealer extends AbstractLogable {

    private String url;

    public PullRequestDealer(String url, String token) {
        url = url.replaceAll("", "");
        this.url = url + "";
    }
    /**
     * 对 PR 添加评论
     *
     * @param pr 相关信息
     */
    public void comment(PullRequestDO pr) {

    }
}
