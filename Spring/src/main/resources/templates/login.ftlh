<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="h5">
        <h5>Login Page</h5>
    </div>
    <#if message??>
        <div>
            <b>
                ${message}
            </b>
        </div>
    </#if>
    <div class="mt-4">
        <@l.login_form "/login" true/>
    </div>
</@c.page>