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

    $(".editComment").on('click', function () {
        var list = $(this).closest("ul");
        var form = list.find(".editCommentForm");
        form.submit();
    });

    $(".deleteComment").on('click', function () {
        var list = $(this).closest("ul");
        var form = list.find(".deleteCommentForm");
        form.submit();
    });

    $(".getProfile").on('click', function () {
       var row = $(this).closest("div");
       var form = row.find(".getProfileForm");
       form.submit();
    });

    $(".deleteFriend").on('click', function () {
       var row = $(this).closest("ul");
       var form = row.find(".deleteFriendForm");
       form.submit();
    });

    $(".sendInvitation").on('click', function () {
       var row = $(this).closest("ul");
       var form = row.find(".sendInvitationForm");
       form.submit();
    });

    $(".acceptInvitation").on('click', function () {
        var row = $(this).closest("ul");
        var form = row.find(".acceptInvitationForm");
        form.submit();
    });

    $(".cancelInvitation").on('click', function () {
       var row = $(this).closest("ul");
       var form = row.find(".cancelInvitationForm");
       form.submit();
    });
});