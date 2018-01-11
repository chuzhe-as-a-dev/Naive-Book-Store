<template>
    <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="book-modal">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <!-- info part -->
                    <br>
                    <div class="row">
                        <!-- book cover -->
                        <div class="col-md-4 col-md-offset-1">
                            <img :src="'/img/cover/' + book.coverFilename" :alt="'cover of ' + book.bookName"
                                 class="img-thumbnail">
                        </div>
                        <div class="col-md-6">
                            <!-- book info -->
                            <h2>{{ book.bookTitle }}
                                <small>{{ book.author }}</small>
                            </h2>
                            <br>

                            <div class="book-description">
                                <p v-for="paragraph in book.description.split('\n') ">{{ paragraph }}</p>
                            </div>
                            <br>
                            <p>
                                <span class="label label-info">${{ book.price / 100 }}</span>
                                <span class="label label-info">{{ book.stock }} left</span>
                                <span class="label label-info">{{ book.category }}</span>
                            </p>

                            <!-- add to cart button -->
                            <div class="col-md-5 input-group">
                                <input type="number" class="form-control" name="quantity" min="1"
                                       v-model="quantity" placeholder="quantity">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" id="addButton" @click="addToCart"
                                            aria-label="Left Align" :disabled="!loggedIn">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    </button>
                                </span>
                            </div>
                            <transition name="fade">
                                <p class="text-success" v-show="promptSuccess">
                                    {{ '' + quantity + ' book' + (quantity === 1 ? '' : 's') }}  added to cart!
                                </p>
                            </transition>
                        </div>
                    </div>
                    <br>

                    <!-- comment part -->
                    <template v-if="comments.length > 0">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <h2>User comments</h2>
                            </div>
                        </div>
                    </template>
                    <template v-for="comment in comments">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <h3>{{ comment.title }}</h3>
                                <strong>{{ comment.userByUserId.username }}</strong>
                                at <strong>{{ new Date(comment.commentTime).toLocaleString() }}</strong>
                                <p>{{ comment.content }}</p>
                            </div>
                        </div>
                    </template>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'book-modal',
        props: ['book', 'loggedIn'],
        data: function () {
            return {
                comments: [],
                quantity: 1,
                promptSuccess: false
            }
        },
        watch: {
            book: function (newBook) {
                const self = this;
                $.get("/book/comment/" + newBook.bookId, function (data) {
                    self.comments = data;
                })
            }
        },
        methods: {
            addToCart: function () {
                let self = this;
                $.post('/user/cart', {
                    bookId: this.book.bookId,
                    quantity: this.quantity
                }, function (data) {
                    if (data === true) {
                        self.promptSuccess = true;
                        setTimeout(function () {
                            self.promptSuccess = false;
                        }, 2000);
                        self.$emit('bookAdded');
                    }
                })
            }
        }
    }
</script>

<style>
    .fade-enter-active, .fade-leave-active {
        transition: opacity .5s
    }

    .fade-enter, .fade-leave-active {
        opacity: 0
    }

    .book-description {
        max-height: 300px;
        overflow-y: scroll;
    }
</style>