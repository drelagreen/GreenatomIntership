<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="h5">
        <h5>Registration Page</h5>
    </div>
    <div class="mt-4">
        <#if message??>
            ${message}
        <#else>
        </#if>
        <@l.login "/login" false/>
    </div>

</@c.page>