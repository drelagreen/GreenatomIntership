<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form class="form-inline" method="get" action="/main">
                <label>
                    <input class="form-control" type="text" name="filter" placeholder="Search by tag" value="${filter!}">
                </label>
                <button class="btn btn-outline-primary ml-3" type="submit">Search</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new message
    </a>

    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" >
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <input class="form-control" type="text" name="text" placeholder="Введите сообщение">
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="tag" placeholder="Тэг">
                </div>
                <div class="form-group">
                    <div class="input-group">
                    <div class="custom-file">
                        <input class="custom-file-input" id="customFile" type="file" name="file">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary mr-2" type="submit">Добавить</button>
                    <button class="btn btn-outline-secondary" type="reset">Очистить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-columns">
    <#if messages??>
        <#list messages as message>
            <div class="card my-3">
                <div class="card-img-top text-center">
                    <#if message.filename??>
                        <img  src="/img/${message.filename}" onerror="this.src = '/img/blank.png'" >
                    </#if>
                </div>
                <div>
                    <div class="m-2">
                        <span>${message.text}</span>
                        <i>${message.tag}</i>
                    </div>
                    <div class="card-footer text-muted">
                        ${message.authorName}
                    </div>
                </div>
            </div>
        </#list>
    <#else>
        No messages
    </#if>

    </div>
</@c.page>