/**
 * Created by tang on 2017/4/9.
 */

$(function () {
    // bind listener to edit button
    $(".edit-button").click(function (event) {
        clearModal();
        var form = $("#info-form");
        var entry = $(this).parent().parent();

        // change modal title
        $(".modal-title").text("Edit user");

        // add specific book id to a hidden input of form
        $("form .hidden").remove();
        form.append($("<input type='number' name='userId' class='hidden' value='" + entry.find("th").text() + "'/>"));

        // change action attribute
        form.attr("action", "/admin/users/update");

        // fill context of the form
        $("#username").val(entry.find(".username").text());
        $("#password").val(entry.find(".password").text());
        $("#email").val(entry.find(".email").text());
        // $("#avatar-filename").val(entry.find(".avatar-filename").text());
        $("#credit").val(entry.find(".credit").text());
        $("#deleted").prop("checked", entry.find(".deleted").text() === "true");
        $("#is-admin").prop("checked", entry.find(".is-admin").text() === "true");
    });

    // bind listener to add button
    $(".add-button").click(function (event) {
        clearModal();
        var form = $("#info-form");

        // change modal title
        $(".modal-title").text("Add user");

        // remove hidden book id
        $("form .hidden").remove();

        // change action attribute
        form.attr("action", "/admin/users/add");
    });

    // bind listener to remove button
    $(".remove-button").click(function (event) {
        var entry = $(this).parent().parent();
        var id = entry.find("th").text();

        var form = $(
            '<form class="hidden" action="/admin/users/delete" method="post">' +
            '<input type="number" name="userId" value="' + id + '" />' +
            '</form>'
        );

        $('body').append(form);
        form.submit();
    });

    // bind listener to submit button
    $("#modal-submit-button").click(function (event) {
        $("#credit").val(Math.round($("#credit").val() * 100));

        $("#info-form").submit();
    })
});

// clear form in modal
function clearModal() {
    $("#deleted").prop("checked", false);
    $("#is-admin").prop("checked", false);
    $("input").val("");
    $("textarea").val("");
}