<template>
    <li class="media">
        <div class="media-left">
            <img class="media-object" :src="'/img/cover/' + item.bookByBookId.coverFilename"
                 width="80">
        </div>
        <div class="media-body">
            <div class="row">
                <div class="col-md-8">
                    <h4 class="media-heading">{{ item.bookByBookId.bookTitle }}<br/>
                        <small>{{ item.bookByBookId.author }}</small>
                    </h4>
                    <p>
                        <span class="label label-info">{{ item.quantity }} {{ item.quantity === 1 ? 'copy' : 'copies'
                            }}</span>
                        <span class="label label-info">${{ item.originalUnitPrice / 100 }}</span>
                    </p>
                    <button class="btn btn-default btn-sm" @click="addToCart(item.bookByBookId.bookId)">
                        Buy it again
                    </button>
                    <transition name="fade">
                        <p class="text-success" v-show="promptSuccess">1 book added to cart!</p>
                    </transition>
                </div>
                <div class="col-md-4">
                    <button class="btn btn-default  btn-block" data-toggle="modal"
                            :data-target="'#' + commentModalId"
                            @click="$emit('commentBook', item.bookByBookId.bookId)">
                        Write a comment
                    </button>
                    <button class="btn btn-default btn-block" disabled="disabled">Return book</button>
                </div>
            </div>
        </div>
    </li>
</template>

<script>
    export default {
        name: 'order-item-media-list-item',
        props: ['item', 'commentModalId'],
        data: function () {
            return {
                promptSuccess: false
            }
        },
        methods: {
            addToCart: function (bookId) {
                const self = this;
                $.post('/user/cart', {
                    bookId: bookId,
                    quantity: 1
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
</style>