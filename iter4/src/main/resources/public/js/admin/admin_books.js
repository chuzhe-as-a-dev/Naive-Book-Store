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
        $(".modal-title").text("Edit book");

        // add specific book id to a hidden input of form
        $("form .hidden").remove();
        form.append($("<input type='number' name='bookId' class='hidden' value='" + entry.find(".book-id").text() + "'/>"));

        // change action attribute
        form.attr("action", "/admin/books/update");

        // fill context of the form
        $("#book-title").val(entry.find(".book-title").text());
        $("#isbn").val(entry.find(".isbn").text());
        $("#author").val(entry.find(".author").text());
        $("#cover-filename").val(entry.find(".cover-filename").text());
        $("#price").val(entry.find(".price").text());
        $("#stock").val(entry.find(".stock").text());
        $("#for-sale").prop("checked", entry.find(".for-sale").text() === "true");
        $("#description").val(entry.find(".description").text());
        $("#category").val(entry.find(".category").text());
    });

    // bind listener to add button
    $(".add-button").click(function (event) {
        clearModal();
        var form = $("#info-form");

        // change modal title
        $(".modal-title").text("Add book");

        // remove hidden book id
        $("form .hidden").remove();

        // change action attribute
        form.attr("action", "/admin/books/add");
    });

    // bind listener to remove button
    $(".remove-button").click(function (event) {
        var entry = $(this).parent().parent();
        var id = entry.find("th").text();

        var form = $('<form class="hidden" action="/admin/books/delete" method="post">' +
            '<input type="number" name="bookId" value="' + id + '" />' +
            '</form>');
        $('body').append(form);
        form.submit();
    });

    // bind listener to submit button
    $("#modal-submit-button").click(function (event) {
        $("#price").val(Math.round($("#price").val() * 100));

        $("#info-form").submit();
    })
});

// clear form in modal
function clearModal() {
    $("#for-sale").prop("checked", false);
    $("input").val("");
    $("textarea").val("");
}