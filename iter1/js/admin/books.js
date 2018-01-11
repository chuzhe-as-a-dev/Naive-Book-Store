/**
 * Created by tang on 2017/3/17.
 */

function checkUpdateBook() {
    var update_book_id = document.getElementById("update_book_id");
    if (!update_book_id.textContent()) {
        alert("At least tell me the book ID...");
        return false;
    }

    return true;
}
