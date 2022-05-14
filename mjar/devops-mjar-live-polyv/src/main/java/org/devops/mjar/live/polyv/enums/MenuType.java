package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author bigboss
 * @description：菜单类型
 */
@Getter
public enum MenuType {

    DESC("desc","直播介绍"),
    CHAT("chat","互动聊天"),
    QUIZ("quiz","咨询提问"),
    TEXT( "text","图文菜单" ),
    IFRAME("iframe","推广外链"),
    QA( "qa" ,"问答" ),
    BUY( "buy","商品列表" ),
    INVITE( "invite","邀请榜" );

    MenuType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

}
