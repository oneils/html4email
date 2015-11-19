<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

        <link rel="stylesheet" media="screen" href="stylesheets/main.css">
        <link rel="stylesheet" media="screen" href="stylesheets/bootstrap.css">
        <link rel="stylesheet" media="screen" href="stylesheets/bootstrap.min.css">
        <link rel="shortcut icon" type="image/png" href="images/favicon.png">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans&subset=latin,cyrillic-ext' rel='stylesheet' type='text/css'>
</head>
<body>

<div class="container">
    <form class="margin" method="POST" enctype="multipart/form-data"
        action="/upload">
        <div class="form-group col-md-12">
            <label for="import-json-path">Path to Digest Json file for import:</label>

            <input type="file" class="btn btn-default btn-file" id="import-json-path" name="file">
        </div>

        <div class="form-group col-md-12">
            <input type="submit" class="btn btn-default" value="Upload">
            <label>Press here to upload the file!</label>
        </div>

        </form>
</div>

</body>
</html>