$(document).ready(function () {
    $(".editStatus").on('click', function () {
        const list = $(this).closest("ul");
        const form = list.find(".edit");
        form.submit();
    });


    $(".deleteStatus").on('click', function () {
        const list = $(this).closest("ul");
        const form = list.find(".delete");
        form.submit();
    });

    $(".editComment").on('click', function () {
        const list = $(this).closest("ul");
        const form = list.find(".editCommentForm");
        form.submit();
    });

    $(".deleteComment").on('click', function () {
        const list = $(this).closest("ul");
        const form = list.find(".deleteCommentForm");
        form.submit();
    });

    $(".getProfile").on('click', function () {
        const row = $(this).closest("div");
        const form = row.find(".getProfileForm");
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

    $(".getMessages").on('click', function () {
        const row = $(this).closest("li");
        const form = row.find(".getMessagesForm");
        form.submit();
    });

    $(".sendMessageTo").on('click', function () {
        const row = $(this).closest("ul");
        const form = row.find(".sendMessageToForm");
        form.submit();
    });

    $(".groupName").on('click', function () {
        const row = $(this).closest("li");
        const form = row.find(".getGroupForm");
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
});
