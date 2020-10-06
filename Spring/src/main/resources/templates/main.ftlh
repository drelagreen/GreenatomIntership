<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l >

<@c.page>
<div>
    <@l.logout/>
    <span><a href="/user">User List</a></span>
</div>
<div>
    <form method="post" enctype="multipart/form-data" >
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <label>
            <input type="text" name="text" placeholder="Введите сообщение">
        </label>
        <label>
            <input type="text" name="tag" placeholder="Тэг">
        </label>
        <input type="file" name="file">
        <button type="submit">Добавить</button>
        <button type="reset">Очистить</button>
    </form>
</div>
<div>Список сообщений:
    <form method="get" action="/main">
        <label>
            <input type="text" name="filter" value="${filter!}">
        </label>
        <button type="submit">Найти</button>
    </form>
</div>
<div>
    <#list messages as message>
        <#if messages??>
            <div>
                <b>${message.id}</b>
                <span>${message.text}</span>
                <i>${message.tag}</i>
                <strong>${message.authorName}</strong>
                <div>
                    <#if message.filename??>
                        <img src="/img/${message.filename}" onerror="this.src = '/img/blank.png'" >
                    </#if>
                </div>
            </div>
        <#else>
        No messages
    </#if>
    </#list>

</div>
</@c.page>