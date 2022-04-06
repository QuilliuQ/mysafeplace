<#-- @ftlvariable name="entries" type="kotlin.collections.List<com.jetbrains.handson.website.BlogEntry>" -->
<!DOCTYPE html>
<html lang="en">

<body style="text-align: center; font-family: sans-serif">
<h1>Kotlin Ktor Journal </h1>
<hr>
<#list entries as item>
    <div>
        <h3>${item}</h3>
    </div>
</#list>
<hr>
</body>
</html>