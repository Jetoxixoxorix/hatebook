$(document).ready(function () {
    $(".editStatus").on('click', function () {
        var list = $(this).closest("ul");
        var statusId = list.find(".statusId").html();
        window.location.href = '/userprofile/' + 1 + '/editstatus/' + statusId;
    });
});