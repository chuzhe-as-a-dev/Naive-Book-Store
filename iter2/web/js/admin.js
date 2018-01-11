/**
 * Created by tang on 2017/4/9.
 */

Vue.component('app-nav', {
    template: "#app-nav-temp",
    props: ["active"],
    data: function () {
        return {
            app_name: "Naïve Bookstore Admin",
            search_text: "Search for...",
            book_active: true,
            user_active: false,
            order_active: false
        }
    },
    watch: {
        active: function (new_active) {
            this.book_active = new_active == "book";
            this.user_active = new_active == "user";
            this.order_active = new_active == "order";
        }
    }
});

Vue.component("book-list", {
    template: "#book-list-temp",
    props: ["books"],
    data: function () {
        return {}
    },
    created: function () {
        this.update_list()
    },
    methods: {
        update_list: function () {
            this.$emit("update_books");
        },
        edit_book: function (event) {
            this.$emit("edit_book", event.target.parentElement.parentElement.id);
        },
        add_book: function () {
            this.$emit("add_book");
        },
        remove_book: function (event) {
            var target = this.books[event.target.parentElement.parentElement.id];
            $.post("/remove_book", {bookId: target.bookId});

            var self = this;
            setTimeout(function () {
                self.$emit("book_removed")
            }, 100);
        }
    }
});

Vue.component("user-list", {
    template: "#user-list-temp",
    props: ["users"],
    created: function () {
        this.update_list()
    },
    methods: {
        update_list: function () {
            this.$emit("update_users");
        },
        edit_user: function (event) {
            this.$emit("edit_user", event.target.parentElement.parentElement.id);
        },
        add_user: function () {
            this.$emit("add_user");
        },
        remove_user: function (event) {
            var target = this.users[event.target.parentElement.parentElement.id];
            $.post("/remove_user", {userId: target.userId});

            var self = this;
            setTimeout(function () {
                self.$emit("user_removed")
            }, 100);
        }
    }
});

Vue.component("order-list", {
    template: "#order-list-temp",
    props: ["orders"],
    created: function () {
        this.update_list()
    },
    methods: {
        update_list: function () {
            this.$emit("update_orders");
        },
        edit_order: function (event) {
            this.$emit("edit_order", event.target.parentElement.parentElement.id);
        },
        add_order: function () {
            this.$emit("add_order");
        },
        remove_order: function (event) {
            var target = this.orders[event.target.parentElement.parentElement.id];
            $.post("/remove_order", {orderId: target.orderId});

            var self = this;
            setTimeout(function () {
                self.$emit("order_removed")
            }, 100);
        }
    }
});

Vue.component("book-edit-modal", {
    template: "#book-edit-modal-temp",
    props: ["original"],
    data: function () {
        return {
            book_name: "",
            author: "",
            isbn: "",
            price: "",
            stock: "",
            description: "",
            book_name_valid: true,
            author_valid: true,
            isbn_valid: true,
            price_valid: true,
            stock_valid: true,
            description_valid: true,
            book_name_error: "",
            author_error: "",
            isbn_error: "",
            price_error: "",
            stock_error: "",
            description_error: ""
        }
    },
    computed: {
        modal_header: function () {
            return $.isEmptyObject(this.original) ? "Create new book" : "Edit book";
        },
        submit_text: function () {
            return $.isEmptyObject(this.original) ? "Add new book" : "Save changes";
        }
    },
    watch: {
        original: function (new_original) {
            this.book_name = $.isEmptyObject(this.original) ? "" : new_original.bookName;
            this.author = $.isEmptyObject(this.original) ? "" : new_original.author;
            this.isbn = $.isEmptyObject(this.original) ? "" : new_original.isbn;
            this.price = $.isEmptyObject(this.original) ? "" : new_original.price;
            this.stock = $.isEmptyObject(this.original) ? "" : new_original.stock;
            this.description = $.isEmptyObject(this.original) ? "" : new_original.description;
        },
        book_name: function (new_book_name) {
            if (new_book_name.length == 0) {
                this.book_name_valid = false;
                this.book_name_error = "Book name too short";
            } else {
                this.book_name_valid = true;
                this.book_name_error = ""
            }
        },
        author: function (new_author) {
            if (new_author.length == 0) {
                this.author_valid = false;
                this.author_error = "Author name too short";
            } else {
                this.author_valid = true;
                this.author_error = "";
            }
        },
        isbn: function (new_isbn) {
            if (new_isbn.length != 13 && new_isbn.length != 10) {
                this.isbn_valid = false;
                this.isbn_error = "Invalid ISBN";
            } else {
                this.isbn_valid = true;
                this.isbn_error = "";
            }
        },
        price: function (new_price) {
            if (!$.isNumeric(new_price)) {
                this.price_valid = false;
                this.price_error = "Not a number";
            } else if (new_price <= 0) {
                this.price_valid = false;
                this.price_error = "Too cheap";
            } else if (new_price > 99999999.99) {
                this.price_valid = false;
                this.price_error = "Too expensive";
            } else {
                this.price_valid = true;
                this.price_error = "";
            }
        },
        stock: function (new_stock) {
            if (!$.isNumeric(new_stock)) {
                this.stock_valid = false;
                this.stock_error = "Not a number";
            } else if (parseInt(new_stock) != new_stock) {
                this.stock_valid = false;
                this.stock_error = "Must be an integer";
            } else if (new_stock < 0) {
                this.stock_valid = false;
                this.stock_error = "Stock should not be negative";
            } else {
                this.stock_valid = true;
                this.stock_error = "";
            }
        },
        description: function (new_description) {
            if (new_description.length == 0) {
                this.description_valid = false;
                this.description_error = "Description too short";
            } else {
                this.description_valid = true;
                this.description_error = "";
            }
        }
    },
    methods: {
        submit: function () {
            if (!(this.book_name.length >= 0 && this.book_name_valid &&
                this.author.length >= 0 && this.author_valid &&
                this.isbn.length >= 0 && this.isbn_valid &&
                (typeof this.price == "number" || this.price.length >= 0) && this.price_valid &&
                (typeof this.stock == "number" || this.stock.length >= 0) && this.stock_valid &&
                this.description.length >= 0 && this.description_valid)) {
                return;
            }

            var success = true;
            if ($.isEmptyObject(this.original)) {
                $.post("/submit_new_book", {
                    bookName: this.book_name,
                    author: this.author,
                    isbn: this.isbn,
                    price: this.price,
                    stock: this.stock,
                    description: this.description
                }, function (data) {
                    success = data.status;
                })
            } else {
                $.post("/submit_change_book", {
                    bookId: this.original.bookId,
                    bookName: this.book_name,
                    author: this.author,
                    isbn: this.isbn,
                    price: this.price,
                    stock: this.stock,
                    description: this.description
                }, function (data) {
                    success = data.status;
                })
            }

            if (success) {
                var self = this;
                setTimeout(function () {
                    self.$emit("book_submitted")
                }, 100)
            }
        }
    }
});

Vue.component("user-edit-modal", {
    template: "#user-edit-modal-temp",
    props: ["original"],
    data: function () {
        return {
            username: "",
            email: "",
            credit: "",
            password: "",
            username_valid: true,
            email_valid: true,
            credit_valid: true,
            password_valid: true,
            username_error: "",
            email_error: "",
            credit_error: "",
            password_error: ""
        }
    },
    computed: {
        modal_header: function () {
            return $.isEmptyObject(this.original) ? "Create new user" : "Edit user";
        },
        submit_text: function () {
            return $.isEmptyObject(this.original) ? "Add new user" : "Save changes";
        }
    },
    watch: {
        original: function (new_original) {
            this.username = $.isEmptyObject(this.original) ? "" : new_original.username;
            this.email = $.isEmptyObject(this.original) ? "" : new_original.email;
            this.credit = $.isEmptyObject(this.original) ? "" : new_original.credit;
            this.password = $.isEmptyObject(this.original) ? "" : new_original.password;
        },
        username: function (new_username) {
            if (new_username.length == 0) {
                this.username_valid = false;
                this.username_error = "User name too short";
            } else if (new_username.length > 32) {
                this.username_valid = false;
                this.username_error = "User name too long";
            } else {
                this.username_valid = true;
                this.username_error = ""
            }
        },
        email: function (new_email) {
            if (new_email.length == 0) {
                this.email_valid = false;
                this.email_error = "Email should not be empty";
            } else if (new_email.indexOf("@") < 0 || new_email.indexOf("@") > new_email.indexOf(".", new_email.indexOf("@"))) {
                this.email_valid = false;
                this.email_error = "Not a valid email address";
            } else {
                this.email_valid = true;
                this.email_error = "";
            }
        },
        credit: function (new_credit) {
            if (typeof new_credit == "string" || new_credit.length == 0) {
                this.credit_valid = false;
                this.credit_error = "Credit should not be empty";
            } else if (!$.isNumeric(new_credit)) {
                this.credit_valid = false;
                this.credit_error = "Not a number";
            } else {
                this.credit_valid = true;
                this.credit_error = "";
            }
        },
        password: function (new_password) {
            if (new_password.length == 0) {
                this.password_valid = false;
                this.password_error = "Password too short";
            } else if (new_password.length > 255) {
                this.password_valid = false;
                this.password_error = "Password too long";
            } else {
                this.password_valid = true;
                this.password_error = "";
            }
        }
    },
    methods: {
        submit: function () {
            if (!(this.username.length >= 0 && this.username_valid &&
                this.email.length >= 0 && this.email_valid &&
                this.password.length >= 0 && this.password_valid &&
                (typeof this.credit == "number" || this.credit.length >= 0) && this.credit_valid)) {
                return;
            }

            var success = true;
            if ($.isEmptyObject(this.original)) {
                $.post("/submit_new_user", {
                    username: this.username,
                    email: this.email,
                    credit: this.credit,
                    password: this.password
                }, function (data) {
                    success = data.status;
                })
            } else {
                $.post("/submit_change_user", {
                    userId: this.original.userId,
                    username: this.username,
                    email: this.email,
                    credit: this.credit,
                    password: this.password
                }, function (data) {
                    success = data.status;
                })
            }

            if (success) {
                var self = this;
                setTimeout(function () {
                    self.$emit("user_submitted")
                }, 100)
            }
        }
    }
});

Vue.component("order-edit-modal", {
    template: "#order-edit-modal-temp",
    props: ["original"],
    data: function () {
        return {
            userId: "",
            orderDate: "",
            totalPrice: "",
            deliverTo: new Date(),
            userId_valid: true,
            orderDate_valid: true,
            totalPrice_valid: true,
            deliverTo_valid: true,
            userId_error: "",
            orderDate_error: "",
            totalPrice_error: "",
            deliverTo_error: "",
            books: []
        }
    },
    computed: {
        modal_header: function () {
            return $.isEmptyObject(this.original) ? "Create new order" : "Edit order";
        },
        submit_text: function () {
            return $.isEmptyObject(this.original) ? "Add new order" : "Save changes";
        }
    },
    watch: {
        original: function (new_original) {
            this.userId = $.isEmptyObject(this.original) ? "" : new_original.user.userId;
            this.orderDate = $.isEmptyObject(this.original) ? "" : new_original.orderDate;
            this.totalPrice = $.isEmptyObject(this.original) ? "" : new_original.totalPrice;
            this.deliverTo = $.isEmptyObject(this.original) ? "" : new_original.deliverTo;
            this.books = $.isEmptyObject(this.original) ? "" : new_original.bookInOrders;
        },
        userId: function (new_userId) {
            if (new_userId.length == 0) {
                this.userId_valid = false;
                this.userId_error = "User ID should not be empty";
            } else {
                this.userId_valid = true;
                this.userId_error = ""
            }
        },
        orderDate: function (new_orderDate) {
            if (new_orderDate.length == 0) {
                this.orderDate_valid = false;
                this.orderDate_error = "Order date should not be empty";
            } else {
                this.orderDate_valid = true;
                this.orderDate_error = "";
            }
        },
        totalPrice: function (new_totalPrice) {
            if (new_totalPrice.length == 0) {
                this.totalPrice_valid = false;
                this.totalPrice_error = "Total price should not be empty";
            } else {
                this.totalPrice_valid = true;
                this.totalPrice_error = "";
            }
        },
        deliverTo: function (new_deliverTo) {
            if (new_deliverTo.length == 0) {
                this.deliverTo_valid = false;
                this.deliverTo_error = "Delivery address should not be empty";
            } else {
                this.deliverTo_valid = true;
                this.deliverTo_error = "";
            }
        }
    },
    methods: {
        submit: function () {
            if (!((typeof this.userId == "number" || $.isNumeric(this.userId)) && this.userId_valid &&
                this.orderDate.length >= 0 && this.orderDate_valid &&
                this.deliverTo.length >= 0 && this.deliverTo_valid &&
                ((typeof this.totalPrice == "number" || $.isNumeric(this.totalPrice))) && this.totalPrice_valid)) {
                return;
            }

            var success = true;
            if ($.isEmptyObject(this.original)) {
                $.post("/submit_new_order", {
                    userId: this.userId,
                    orderDate: this.orderDate,
                    totalPrice: this.totalPrice,
                    deliverTo: this.deliverTo
                }, function (data) {
                    success = data.status;
                })
            } else {
                $.post("/submit_change_order", {
                    orderId: this.original.orderId,
                    userId: this.userId,
                    orderDate: this.orderDate,
                    totalPrice: this.totalPrice,
                    deliverTo: this.deliverTo
                }, function (data) {
                    success = data.status;
                })
            }

            if (success) {
                var self = this;
                setTimeout(function () {
                    self.$emit("order_submitted")
                }, 100)
            }
        }
    }
});

var app = new Vue({
    el: "#app",
    data: {
        show: "book",
        books: [],
        users: [],
        orders: [],
        selected_book: {},
        selected_user: {},
        selected_order: {}
    },
    methods: {
        change_table: function (target) {
            this.show = target
        },
        update_books: function () {
            var self = this;
            $.get("/book_list", {start: 0, end: 20}, function (data) {
                self.books = data;
            })
        },
        edit_book: function (book_index) {
            this.selected_book = this.books[book_index]
        },
        add_book:function () {
            this.selected_book = {}
        },
        update_users: function () {
            var self = this;
            $.get("/user_list", {start: 0, end: 20}, function (data) {
                self.users = data;
            })
        },
        edit_user: function (user_index) {
            this.selected_user = this.users[user_index]
        },
        add_user:function () {
            this.selected_user = {}
        },
        update_orders: function () {
            var self = this;
            $.get("/order_list", {start: 0, end: 20}, function (data) {
                self.orders = data;
            })
        },
        edit_order: function (order_index) {
            this.selected_order = this.orders[order_index]
        },
        add_order:function () {
            this.selected_order = {}
        }
    }
});