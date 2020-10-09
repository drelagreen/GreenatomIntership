<#macro login path isLoginPage>
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
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div>
            <input class="btn btn-primary mt-2" type="submit" value=<#if isLoginPage>"Sign In"<#else>"Create"</#if>/>
        </div>
        <#if isLoginPage>
            <a href="/registration">add new user</a>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary mr-3" type="submit">Sign out</button>
    </form>
</#macro>