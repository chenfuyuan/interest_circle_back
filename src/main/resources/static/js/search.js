$(function () {
    $("#btn-search").click(function () {
        var start = $("#start").val();
        console.log("start = " + start);
        var end = $("#end").val();
        console.log("end = " + end);

        var search = $("#input-search").val();
        var tableName = $(this).data("table");
        var url = "/get/index/" + tableName + "?";
        if (start != "" && start!=undefined) {
            url += "start=" + start + "&";
        }
        if (end != "" && end!=undefined) {
            url += "end=" + end + "&";
        }
        if (search != "" && search !=undefined) {
            url += "search=" + search + "&";
        }
        url += "pageNum=1";
        console.log("url = " + url);
        window.location.href = url;


    });
})