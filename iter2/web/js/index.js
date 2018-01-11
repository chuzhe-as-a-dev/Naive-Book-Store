/**
 * Created by tang on 2017/4/1.
 */

Vue.component('app-nav', {
    template: "#app-nav-temp",
    data: function () {
        return {
            app_name: "Naïve Bookstore",
            search_text: "Search for...",
            logged_in: false,
            home_active: true,
            cart_active: false,
            account_active: false
        }
    },
    created: function () {
        this.checkUserStatus();
    },
    methods: {
        checkUserStatus: function () {
            var logged_in = false;
            var self = this;  // avoid ambiguity
            $.get("/logged_in", function (data) {
                self.logged_in = data.logged_in;
            });
        },
        logout: function (event) {
            $.post("/logout");
            this.checkUserStatus();
        }
    }
});

Vue.component('book-grid', {
    template: "#book-grid-temp",
    data: function () {
        return {
            books: []
        }
    },
    computed: {
        book_sets: function () {
            var book_sets = [];
            for (var i = 0; i < this.books.length; ++i) {
                if (i % 4 == 0) {
                    book_sets.push([]);
                }
                book_sets[book_sets.length - 1].push(this.books[i]);
            }

            return book_sets;
        }
    },
    filters: {
        des_preview: function (description) {
            return description.slice(0, 100);
        }
    },
    created: function () {
        var self = this;
        $.get("/book_list", {start: 0, end: 20}, function (data) {
            self.books = data;
        })
    },
    methods: {
        click_cover: function (event) {
            this.$emit("query_book", event.currentTarget.parentElement.parentElement.id);
        },
        click_name: function (event) {
            this.$emit("query_book", event.currentTarget.parentElement.parentElement.parentElement.id);
        }
    }
});

Vue.component("book-modal", {
    template: "#book-modal-temp",
    props: ["book_id"],
    data: function () {
        return {
            book: {},
            comments: []
        }
    },
    created: function () {
        this.update_modal(1);
    },
    watch: {
        book_id: function (new_id) {
            this.update_modal(new_id);
        }
    },
    methods: {
        update_modal: function (book_id) {
            var self = this;
            // todo: back end for book detail query
            $.get("/book_detail", {book_id: book_id}, function (data) {
                if (data.status) {
                    self.book = data.book;
                    self.comments = data.comments;
                } else {
                    alert("book not found");
                    window.location = "/error.html";
                }
            })
        }
    }
});

Vue.component('cart', {});

var app = new Vue({
    el: "#app",
    data: {
        book_id: 1
    },
    methods: {
        query_book: function (book_id) {
            this.book_id = book_id;
        }
    }
});

var login_modal = new Vue({
    el: "#login-modal",
    data: {
        modal_header: "Login",
        identifier: "",
        identifier_valid: true,
        identifier_error: "",
        password: "",
        password_valid: true,
        password_error: ""
    },
    watch: {
        identifier: function (new_identifier) {
            if (new_identifier.indexOf("@") == -1) {
                if (new_identifier.length == 0) {
                    this.identifier_valid = false;
                    this.identifier_error = "Username should not be empty";
                    return;
                }
                if (new_identifier.length > 32) {
                    this.identifier_valid = false;
                    this.identifier_error = "Username too long";
                    return;
                }
                for (var i = 0; i < new_identifier.length; ++i) {
                    if ((new_identifier.charAt(i) < 'a' || new_identifier.charAt(i) > 'z') &&
                        (new_identifier.charAt(i) < 'A' || new_identifier.charAt(i) > 'Z') &&
                        (new_identifier.charAt(i) < '0' || new_identifier.charAt(i) > '9')) {
                        this.identifier_valid = false;
                        this.identifier_error = "Username should only contains letters and numbers";
                        return
                    }
                }
                this.identifier_valid = true;
                this.identifier_error = "";
            } else {
                if (new_identifier.indexOf("@") < new_identifier.indexOf(".", new_identifier.indexOf("@")) &&
                    new_identifier.charAt(new_identifier.length - 1) != ".") {
                    this.identifier_valid = true;
                    this.identifier_error = "";
                } else {
                    this.identifier_valid = false;
                    this.identifier_error = "Not a valid email address";
                }
            }
        },
        password: function (new_password) {
            if (new_password.length == 0) {
                this.password_valid = false;
                this.password_error = "Password should not be empty";
            } else if (new_password.length > 32) {
                this.password_valid = false;
                this.password_error = "Password too long";
            } else {
                this.password_valid = true;
                this.password_error = "";
            }
        }
    },
    methods: {
        validate: function (event) {
            if (!this.identifier_valid || !this.password_valid ||
                this.identifier.length == 0 || this.password.length == 0) {
                event.preventDefault();
            }
        }
    }
});
