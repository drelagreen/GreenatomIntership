<#import "parts/common.ftlh" as c/>

<@c.page>
    <form method="post">
        <h5>${username}</h5>
        <#if message??>
            <div>
                <b>
                    ${message}
                </b>
            </div>
        <#else>
        </#if>
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
                <button class="btn btn-primary mt-2" type="submit">Save</button>
            </div>
        </form>
    </form>
</@c.page>