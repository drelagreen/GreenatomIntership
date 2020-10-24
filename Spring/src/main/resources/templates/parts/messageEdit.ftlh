<#macro message_edit isMain>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Message editor
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" >
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="<#if message??>${message.id}<#else>-1</#if>"/>
                <div class="form-group">
                    <input class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>"
                           type="text" name="text" placeholder="Write your message"/>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input class="form-control ${(tagError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.tag}</#if>"
                           type="text" name="tag" placeholder="Tag"/>
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>
                </div>
                <#if !isMain&&message??&&message.getFilename()??>
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="remImage" id="remImage"/>
                        <label class="form-check-label" for="remImage">
                            Remove previous image
                        </label>
                    </div>
                </div>
                </#if>
                <div class="form-group">
                    <div class="input-group">
                        <div class="custom-file">
                            <input class="custom-file-input" id="customFile" type="file" name="file"/>
                            <label class="custom-file-label" for="customFile">Choose file</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary mr-2" type="submit">Save message</button>
                    <button class="btn btn-outline-secondary" type="reset">Clear</button>
                </div>
            </form>
        </div>
    </div>
</#macro>