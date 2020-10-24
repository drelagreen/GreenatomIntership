<#import "parts/common.ftlh" as c/>
<#import "parts/login.ftlh" as l/>

<@c.page>
    <div class="h5">
        <h5>Registration Page</h5>
    </div>
    <div class="mt-4">
        <#if message??>
            <div>
                <b>
                    ${message}
                </b>
            </div>
        <#else>
        </#if>
        <@l.login_form "/registration" false/>
    </div>

</@c.page>