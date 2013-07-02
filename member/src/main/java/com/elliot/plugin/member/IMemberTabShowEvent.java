package com.elliot.plugin.member;

import com.elliot.plugin.member.model.Member;

/**
 * User: andy
 * Date: 13-6-10
 *
 * @since:
 */
public interface IMemberTabShowEvent {
    public  String getTabName(Member paramMember);

    public  int getOrder();

    public  boolean canBeExecute(Member paramMember);

    public  String onShowMemberDetailHtml(Member paramMember);
}
