<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="stylesheets/bootstrap.min.css">
</head>
<body>

    <div class="container">
        <h1>Articles will be displayed here</h1>

        <div class="col-md-4">
            <form role="form" action="/articles/saveArticle" method="post">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="url">URL:</label>
                    <input type="URL" class="form-control" id="url" name="url" required>
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textArea rows="6" class="form-control" id="description" name="description"></textArea>

                </div>

                <button type="submit" class="btn btn-default">Save</button>
            </form>
        </div>

        <div class="col-md-12">
            <#list articles as article>
            <form action="/articles/delete" method="post">
                <div> id: ${article.id}
                    <input type="hidden" name="id" value="${article.id}"/>
                    <a href="${article.url}">${article.title}</a>
                    <input type="submit" value = "x"/>
                </div>

                <#if article.description??>
                    <div> ${article.description}
                    <br/>
                </#if>
                <br/>

            </form>

            </#list>
            </div>
        </div>

    </body>
    </html>
