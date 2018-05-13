$(document).ready(function () {
    $(".deleteStatus").on('click', function () {
        const list = $(this).closest("ul");
        const form = list.find(".delete");
        form.submit();
    });

    $(".deleteComment").on('click', function () {
        const list = $(this).closest("ul");
        const form = list.find(".deleteCommentForm");
        form.submit();
    });

    $(".deleteFriend").on('click', function () {
        const row = $(this).closest("ul");
        const form = row.find(".deleteFriendForm");
        form.submit();
    });

    $(".sendInvitation").on('click', function () {
        const row = $(this).closest("ul");
        const form = row.find(".sendInvitationForm");
        form.submit();
    });

    $(".acceptInvitation").on('click', function () {
        const row = $(this).closest("ul");
        const form = row.find(".acceptInvitationForm");
        form.submit();
    });

    $(".cancelInvitation").on('click', function () {
        const row = $(this).closest("ul");
        const form = row.find(".cancelInvitationForm");
        form.submit();
    });

    $(".deleteUser").on('click', function () {
        const row = $(this).closest("li");
        const form = row.find(".deleteUserForm");
        form.submit();
    });

    $(".makeAdmin").on('click', function () {
        const row = $(this).closest("li");
        const form = row.find(".makeAdminForm");
        form.submit();
    });

    $(".deleteFriend").on('click', function () {
        const row = $(this).closest("li");
        const form = row.find(".deleteFriendForm");
        form.submit();
    });

    $(".addFriend").on('click', function () {
        const row = $(this).closest("li");
        const form = row.find(".addFriendForm");
        form.submit();
    });
});
