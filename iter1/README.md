# Naïve Bookstore Documentation

Please set `iter1` as apache's root directory.

To connect to mysql database, you need:

* Set up mysql server to listen default local port.
* Create mysql user with name `bookstore` and password `0000` (see `/src/conn.php` file).
* Add test data using provided `bookstore.sql` file.

You can visit `chuzhe.me/bookstore_iter1` for a better view of the website.

## For customers
You can view customer's index page via `chuzhe.me/bookstore_iter1`.

## For administrators
You can view administrator's admin page via `chuzhe.me/bookstore_iter1/admin` which should redirects to `chuzhe.me/bookstore_iter1/admin/books.php`.

For CRUD of books, orders and users You can browse 
* `chuzhe.me/bookstore_iter1/admin/books.php`
* `chuzhe.me/bookstore_iter1/admin/orders.php`
* `chuzhe.me/bookstore_iter1/admin/users.php`

respectively.

## Multiple sets of CSS
Rename `bootstrap_xxx.css` files to `bootstrap.css` in `/css` folder to adapt different style.

## Naive use of JS
In order's admin page (`chuzhe.me/bookstore_iter1/admin/books.php`), a small piece of JS will check if `book ID ` field is empty when updating book information, and stall sending data for such situation.
