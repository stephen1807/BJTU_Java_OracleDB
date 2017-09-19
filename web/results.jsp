<%@page contentType="text/html;charset=UTF-8"
        import="project.obj.MovieStat,java.util.Iterator" import="java.util.List" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>RESULTS</title>
    <script src="resource/jquery.js"></script>
    <link href="resource/flat/green.css" rel="stylesheet">
    <script src="resource/icheck.js"></script>

    <!-- Meta -->
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>

    <!-- Bootstrap -->
    <link href="resource/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Glyphicons -->
    <link rel="stylesheet" href="resource/theme/css/glyphicons.css"/>

    <!-- Theme -->
    <link rel="stylesheet" href="resource/theme/css/style.min.css?1361377766"/>

    <script src="resource/Chart.js"></script>

    <!-- LESS 2 CSS -->
    <script src="resource/theme/scripts/less-1.3.3.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script>
        function gotoSubmit() {
            document.getElementById("friendsALL").submit();
        }
    </script>

</head>
<body>
<!-- Start Content -->
<%
    request.setCharacterEncoding("UTF-8");
    //List<MovieStat> states = (List<MovieStat>)request.getAttribute("top");
    //List<String> category = (List<String>)request.getAttribute("category");
%>
<div class="container-fluid fixed">
    <div class="container-fluid fixed">
        <div id="content">
            <ul class="breadcrumb">
                <li><a href="" class="glyphicons home"><i></i>Results</a></li>
            </ul>
            <h3 class="glyphicons show_thumbnails_with_lines"><i></i>Twitter Analysis</h3>

            <div class="separator bottom"></div>
            <div class="row-fluid">

            </div>
            <div class="span9">
                <div style="width: 100%" align="center">
                    <canvas id="canvas" height="450" width="600"></canvas>
                </div>
            </div>
            <!-- <div class="separator line"></div> -->
            <div class="row-fluid">
                <div class="control-group">
                    <form action="AllRecommandation" method="post" id="friendsALL">
                    <label class="control-label"><big><big><big>Source</big></big></big></label>
                    <a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                    <input type="radio" name="mutisubmit" value="friends" checked="checked">&nbsp;&nbsp;<big><big>friends</big></big>&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="mutisubmit" value="all">&nbsp;&nbsp;<big><big>all</big></big>&nbsp;
                    <br><br>
                    <a class="btn btn-primary btn-icon" onclick="gotoSubmit()">Refresh</a>
                    </form>
                </div>
                <div class="row-fluid">
                    <label class="control-label"><big><big><big>General</big></big></big></label>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn btn-primary btn-icon" href=""
                                                                 data-toggle="modal"
                                                                 data-target="#myModal">CHOOSE</a></br></br>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">GENERALS</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="GeneralRecommendation" method="post">
                <div class="tab-content" style="padding: 0;">
                    <div class="tab-pane active" id="account-details">
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="skin-flat">
                                    <h4>CATEGORY:</h4>
                                    <%
                                        List<String> category = (List<String>) request.getAttribute("category");
                                        if (category != null) {
                                            Iterator<String> cateIte = category.iterator();

                                            //for (int j = 0; cateIte.hasNext(); ++j) {
                                            for (int j = 0; j<10; ++j) {
                                                String cateItem = cateIte.next();
                                    %>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="checkbox" name="categoryCheck" id="categoryCheck"
                                           value="<%=cateItem%>">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<big><%=cateItem%>
                                </big><br>
                                    <%
                                            }
                                        }
                                    %>
                                </div>
                            </div>
                            <script>
                                $(document).ready(function () {
                                    $('.skin-flat input').iCheck({
                                        checkboxClass: 'icheckbox_flat-green',
                                        radioClass: 'iradio_flat-green'
                                    });
                                });
                            </script>
                        </div>
                    </div>
                </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var names = [];
    var counts = [];
    <%
    List<MovieStat> states = (List<MovieStat>)request.getAttribute("top");
    if(states != null){
        Iterator<MovieStat> statesIte = states.iterator();
        //for(int i = 0;statesIte.hasNext();++i){
        int stasize = states.size();
        if(stasize>=10){
            stasize = 10;
        }
        for(int i = 0;i<stasize;++i){
            MovieStat stat = statesIte.next();
    %>
    names[<%=i%>] = '<%=stat.getTitle()%>';
    counts[<%=i%>] = '<%=stat.getCount()%>';
    <%
}
}
%>
    var barChartData = {
        labels: names,
        datasets: [
            {
                fillColor: "#C6F0DB",
                strokeColor: "#71c39a",
                highlightFill: "#71c39a",
                highlightStroke: "#71c39a",
                data: counts
            }
        ]

    };
    window.onload = function () {
        var ctx = document.getElementById("canvas").getContext("2d");
        window.myBar = new Chart(ctx).Bar(barChartData, {
            responsive: true
        });
    }
</script>

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
</body>
</html>