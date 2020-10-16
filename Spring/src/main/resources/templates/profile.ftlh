<#import "parts/common.ftlh" as c>

<@c.page>
    <
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-5">
                <input class="form-control" type="text" name="username"/>
                <small id="emailHelp" class="form-text text-muted">We'll never share your data with anyone else.</small>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-5">
                <input class="form-control" type="password" name="password"/>
            </div>
        </div>
        <#if !isLoginPage>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">E-mail: </label>
                <div class="col-sm-5">
                    <input class="form-control" type="email" name="email" placeholder="exmple@example.com"/>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div>
            <input class="btn btn-primary mt-2" type="submit" value=<#if isLoginPage>"Sign In"<#else>"Create"</#if>/>
        </div>
        <#if isLoginPage>
            <a href="/registration">add new user</a>
        </#if>
    </form>
</@c.page>