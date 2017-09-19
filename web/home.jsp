<%@page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html> <!--<![endif]-->
<head>
    <title>HOME</title>

    <!-- Meta -->
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>

    <!-- Bootstrap -->
    <link href="resource/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Bootstrap Extended -->
    <link href="resource/bootstrap/extend/jasny-bootstrap/css/jasny-bootstrap.min.css" rel="stylesheet"/>
    <link href="resource/bootstrap/extend/jasny-bootstrap/css/jasny-bootstrap-responsive.min.css" rel="stylesheet"/>
    <link href="resource/bootstrap/extend/bootstrap-wysihtml5/css/bootstrap-wysihtml5-0.0.2.css" rel="stylesheet"/>

    <!-- JQueryUI v1.9.2 -->
    <link rel="stylesheet"
          href="resource/theme/scripts/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.min.css"/>

    <!-- Glyphicons -->
    <link rel="stylesheet" href="resource/theme/css/glyphicons.css"/>

    <!-- Bootstrap Extended -->
    <link rel="stylesheet" href="resource/bootstrap/extend/bootstrap-select/bootstrap-select.css"/>
    <link rel="stylesheet"
          href="resource/bootstrap/extend/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css"/>

    <!-- Uniform -->
    <link rel="stylesheet" media="screen" href="resource/theme/scripts/pixelmatrix-uniform/css/uniform.default.css"/>

    <!-- JQuery v1.8.2 -->
    <script src="resource/theme/scripts/jquery-1.8.2.min.js"></script>

    <!-- Modernizr -->
    <script src="resource/theme/scripts/modernizr.custom.76094.js"></script>

    <!-- MiniColors -->
    <link rel="stylesheet" media="screen" href="resource/theme/scripts/jquery-miniColors/jquery.miniColors.css"/>

    <!-- plupload -->
    <style type="text/css">@import url(resource/theme/scripts/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css);</style>

    <!-- Theme -->
    <link rel="stylesheet" href="resource/theme/css/style.min.css?1361377766"/>


    <!-- LESS 2 CSS -->
    <script src="resource/theme/scripts/less-1.3.3.min.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<!-- Start Content -->
<div class="container-fluid fixed">
    <div class="container-fluid fixed">
        <div id="content">
            <ul class="breadcrumb">
                <li><a href="" class="glyphicons home"><i></i>Home</a></li>
            </ul>
            <h3 class="glyphicons show_thumbnails_with_lines"><i></i>Twitter Analysis</h3>

            <div class="separator bottom"></div>
            <div class="row-fluid">
                <form action="BasicRecommandation" method="post">
                    <label for="twitterid" class="control-label"><big><strong>Twitter ID</strong></big></label>

                    <div class="controls"><input type="text" name="username" id="username" class="span8"
                                                 placeholder="Please type in the id of twitter"></div>
                    <button type="submit" class="btn btn-icon btn-primary glyphicons circle_ok"><i></i>GO</button>
                </form>
            </div>
            <div class="separator line"></div>
        </div>
    </div>
</div>

<!-- JQueryUI v1.9.2 -->
<script src="resource/theme/scripts/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.min.js"></script>

<!-- JQueryUI Touch Punch -->
<!-- small hack that enables the use of touch events on sites using the jQuery UI user interface library -->
<script src="resource/theme/scripts/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>

<!-- MiniColors -->
<script src="resource/theme/scripts/jquery-miniColors/jquery.miniColors.js"></script>

<!-- Themer -->
<script>
    var themerPrimaryColor = '#71c39a';
</script>
<script src="resource/theme/scripts/jquery.cookie.js"></script>
<script src="resource/theme/scripts/themer.js"></script>


<!-- Resize Script -->
<script src="resource/theme/scripts/jquery.ba-resize.js"></script>

<!-- Uniform -->
<script src="resource/theme/scripts/pixelmatrix-uniform/jquery.uniform.min.js"></script>

<!-- Bootstrap Script -->
<script src="resource/bootstrap/js/bootstrap.min.js"></script>

<!-- Bootstrap Extended -->
<script src="resource/bootstrap/extend/bootstrap-select/bootstrap-select.js"></script>
<script src="resource/bootstrap/extend/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons.js"></script>
<script src="resource/bootstrap/extend/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"></script>
<script src="resource/bootstrap/extend/jasny-bootstrap/js/jasny-bootstrap.min.js" type="text/javascript"></script>
<script src="resource/bootstrap/extend/jasny-bootstrap/js/bootstrap-fileupload.js" type="text/javascript"></script>
<script src="resource/bootstrap/extend/bootbox.js" type="text/javascript"></script>
<script src="resource/bootstrap/extend/bootstrap-wysihtml5/js/wysihtml5-0.3.0_rc2.min.js"
        type="text/javascript"></script>
<script src="resource/bootstrap/extend/bootstrap-wysihtml5/js/bootstrap-wysihtml5-0.0.2.js"
        type="text/javascript"></script>

<!-- Custom Onload Script -->
<script src="resource/theme/scripts/load.js"></script>

<!-- Third party script for BrowserPlus runtime (Google Gears included in Gears runtime now) -->
<script type="text/javascript" src="resource/bootstrap/js/browserplus-min.js"></script>

<!-- Load plupload and all it's runtimes and finally the jQuery queue widget -->
<script type="text/javascript" src="resource/theme/scripts/plupload/js/plupload.full.js"></script>
<script type="text/javascript"
        src="resource/theme/scripts/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<script type="text/javascript">
    // Convert divs to queue widgets when the DOM is ready
    $(function () {
        $("#pluploadUploader").pluploadQueue({
            // General settings
            runtimes: 'gears,browserplus,html5',
            url: 'resource/theme/scripts/plupload/examples/upload.php',
            max_file_size: '10mb',
            chunk_size: '1mb',
            unique_names: true,

            // Resize images on clientside if we can
            resize: {width: 320, height: 240, quality: 90},

            // Specify what files to browse for
            filters: [
                {title: "Image files", extensions: "jpg,gif,png"},
                {title: "Zip files", extensions: "zip"}
            ],

            // Flash settings
            flash_swf_url: 'resource/theme/scripts/plupload/js/plupload.flash.swf',

            // Silverlight settings
            silverlight_xap_url: 'resource/theme/scripts/plupload/js/plupload.silverlight.xap'
        });

        // Client side form validation
        $('#pluploadForm').submit(function (e) {
            var uploader = $('#pluploadUploader').pluploadQueue();

            // Files in queue upload them first
            if (uploader.files.length > 0) {
                // When all files are uploaded submit form
                uploader.bind('StateChanged', function () {
                    if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
                        $('#pluploadForm').submit();
                    }
                });

                uploader.start();
            } else {
                alert('You must queue at least one file.');
            }

            return false;
        });
    });
</script>

</body>
</html>