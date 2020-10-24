<#include  "security.ftlh"/>
<#import "pager.ftlh" as p/>

<@p.pager url page/>

<div class="card-columns" id="message-list">
    <#if page??>
        <#list page.content as message>
            <div class="card my-3" data-id="${message.id}">
                <div class="card-img-top text-center">
                    <#if message.filename??>
                        <img  src="/img/${message.filename}"/>
                    </#if>
                </div>
                <div>
                    <div class="m-2">
                        <span>${message.text}</span><br/>
                        <i>#${message.tag}</i>
                    </div>
                    <div class="card-footer text-muted">
                        <a href="/user-messages/${message.author.id}">
                            ${message.authorName}
                        </a>
                        <#if message.author.id == currentUserId>
                            <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                                Edit
                            </a>
                        </#if>
                    </div>
                </div>
            </div>
        </#list>
    <#else>
        No messages
    </#if>
</div>

<@p.pager url page/>