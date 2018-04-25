<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div spellcheck="true" aria-label="邮件正文" id="MicrosoftOWAEditorRegion" class="ms-rtestate-write" useinlinestyle="true"
     contenteditable="true" role="textbox"
     style="height:100%;border-style:hidden; outline:none;font-size:12pt;color:#000000;background-color:#FFFFFF;font-family:Calibri,Arial,Helvetica,sans-serif;"
     rtedirty="true"><p rtenodeid="3"><span style="font-size: 11pt;">您好：</span></p>
    <p rtenodeid="6"><span style="font-size: 11pt;">这是班级的名单：<br rtenodeid="7" style=""></span></p>
    <p rtenodeid="10"><span style="font-size: 11pt;">时间： ${date} </span></p>
     <#if spuCount = 0>
         <p rtenodeid="17"><span style="font-size: 11pt;">状态：异常</span></p>
    <#elseif categoryList?? && (categoryList?size > 0) >
    <p rtenodeid="17"><span style="font-size: 11pt;">状态：异常</span></p>
    <p rtenodeid="20"><span style="font-size: 11pt;">具体爬取SPU数据异常分类如下：</span></p>
        <#list categoryList as categorys>
        <p rtenodeid="20"><span style="font-size: 11pt; display: none;"></span></p>
    <p rtenodeid="24"><span style="font-size: 11pt;"> ${categorys.superCategory.name} —— ${categorys.category.name} </span></p>
        </#list>
        <#else>
            <p rtenodeid="17"><span style="font-size: 11pt;">状态：正常</span></p>
    </#if>
</div>
</body>
</html>