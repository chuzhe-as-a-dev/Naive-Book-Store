<template>
    <div id="app">
        <navbar activeTag="home" :loggedIn="loggedIn" @queryBook="reloadBooks" ref="navbar"></navbar>
        <book-table :loggedIn="loggedIn" :books="books" @bookAdded="updateCartSize"></book-table>

        <div class="text-center">
            <button class="btn btn-default" v-show="!lastPage" @click="showMore">Show more</button>
        </div>

        <page-footer></page-footer>
    </div>
</template>

<script>
    import Navbar from "../components/Navbar.vue";
    import BookTable from "./BookTable.vue";
    import PageFooter from "../components/PageFooter.vue";

    export default {
        components: {
            PageFooter,
            BookTable,
            Navbar
        },
        data: function () {
            return {
                loggedIn: false,
                books: [],
                lastPage: true,
                page: 0,
                totalPages: 0,
                q: ''
            }
        },
        created: function () {
            const self = this;
            $.get("/logged_in", function (data) {
                self.loggedIn = (data === true);
            });

            this.reloadBooks();
        },
        methods: {
            updateCartSize: function () {
                this.$refs.navbar.updateCartSize();  // directly ask navbar to update cart size. ugly code tho
            },
            showMore: function () {
                if (this.lastPage) {
                    return;
                }

                let data;
                if (this.q === '') {
                    data = {
                        page: this.page + 1
                    }
                } else {
                    data = {
                        page: this.page + 1,
                        q: this.q
                    }
                }

                const self = this;
                $.get("/book", data, function (data) {
                        self.books.push.apply(self.books, data.content)
                        self.lastPage = data.last;
                        self.page = data.number;
                        self.totalPages = data.totalPages;
                    }
                )
            },
            reloadBooks: function (keyword) {
                const self = this;

                if (keyword === undefined) {
                    $.get("/book", {page: 0}, function (data) {
                            self.books = data.content;
                            self.lastPage = data.last;
                            self.page = data.number;
                            self.totalPages = data.totalPages;
                        }
                    )
                } else {
                    this.q = keyword;
                    $.get("/book", {page: 0, q: keyword}, function (data) {
                            self.books = data.content;
                            self.lastPage = data.last;
                            self.page = data.number;
                            self.totalPages = data.totalPages;
                        }
                    )
                }
            }
        }
    }
</script>

<style>
    body {
        padding-top: 70px;
        padding-bottom: 20px;
    }
</style>