$(document).ready(function () {
    $(".editStatus").on('click', function () {
        var list = $(this).closest("ul");
        var form = list.find(".edit");
        form.submit();
    });


    $(".deleteStatus").on('click', function () {
        var list = $(this).closest("ul");
        var form = list.find(".delete");
        form.submit();
    });
});