<#import "parts/common.ftlh" as c/>
<#import "parts/messageEdit.ftlh" as m/>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form class="form-inline" method="get" action="/main">
                <label>
                    <input class="form-control" type="text" name="filter" placeholder="Search by tag" value="${filter!}"/>
                </label>
                <button class="btn btn-outline-primary ml-3" type="submit">Search</button>
            </form>
        </div>
    </div>

    <@m.message_edit true/>


    <#include "parts/messageList.ftlh" />
</@c.page>