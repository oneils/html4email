<!DOCTYPE html>

<html lang="en">

<body>

    <div> ${title} </div>
    <div>
    <#list topics as t>
        ${t.topic}
            <#list t.articles as article>
                <div>${article.title} </div>
                <div>${article.description} </div>
                <div>${article.url} </div>
                <br/>
            </#list>

    </#list>
    </div>

    <br>
    contributeTo: ${contributeTo}
    <br>

</body>

</html>