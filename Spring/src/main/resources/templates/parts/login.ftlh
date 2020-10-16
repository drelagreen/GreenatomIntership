<#macro login_form path isLoginPage>
    <h5>${username}</h5>
    ${message?ifExist}
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-5">
                <input class="form-control" type="password" name="password"/>
            </div>
        </div>
         <div class="form-group row">
            <label class="col-sm-2 col-form-label">E-mail: </label>
            <div class="col-sm-5">
                <input class="form-control" type="email" name="email" placeholder="exmple@example.com" value="${email!''}"/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div>
            <input class="btn btn-primary mt-2" type="submit">"Save"<button/>
        </div>
        </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary mr-3" type="submit">Sign out</button>
    </form>
</#macro>

<#macro login>
    <form action="/login" method="get">
        <button class="btn btn-primary mr-3" type="submit">Sign in</button>
    </form>
</#macro>