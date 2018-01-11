<template>
    <div>
        <div class="container">
            <div class="row">
                <template v-for="col in cols">
                    <div class="col-md-3">
                        <template v-for="book in col">
                            <book-thumbnail :book="book" @clickBook="showModal"></book-thumbnail>
                        </template>
                    </div>
                </template>
            </div>
        </div>

        <!-- book modal associated with this book table -->
        <book-modal :loggedIn="loggedIn" :book="selectedBook" @bookAdded="$emit('bookAdded')"></book-modal>
    </div>
</template>

<script>
    import BookThumbnail from './BookThumbnail.vue'
    import BookModal from "./BookModal.vue";

    export default {
        name: 'book-table',
        components: {
            BookModal,
            BookThumbnail
        },
        props: ['loggedIn', 'books'],
        data: function () {
            return {
                selectedBook: {  // placeholder for book modal when created
                    "bookId": 1,
                    "isbn": "9780262033848",
                    "bookTitle": "Introduction to Algorithms, 3rd Edition",
                    "author": "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein",
                    "price": 9900,
                    "stock": 34,
                    "coverFilename": "41-1VkO+1lL._SX359_BO1,204,203,200_.jpg",
                    "description": "Short description.",
                    "forSale": true
                }
            }
        },
        computed: {
            cols: function () {
                let cols = [[], [], [], []];
                for (let i = 0; i < this.books.length; ++i) {
                    cols[i % 4].push(this.books[i]);
                }

                return cols;
            }
        },
        methods: {
            showModal: function (selectedBook) {
                this.selectedBook = selectedBook;
            }
        }
    }
</script>