<template>
    <div class="thumbnail" :id="book.bookId">
        <img class="book-cover" :src="'/img/cover/' + book.coverFilename"
             :alt="'cover of ' + book.bookTitle" @click="clickBook" data-toggle="modal"
             data-target="#book-modal">
        <div class="caption">
            <h3 class="book-title" @click="clickBook" data-toggle="modal"
                data-target="#book-modal">{{ book.bookTitle }}
                <small>{{ book.author }}</small>
            </h3>
            <p>{{ book.description | descriptionPreview }}</p>
            <span class="label label-info">${{ book.price / 100 }}</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'book-thumbnail',
        props: ['book'],
        methods: {
            clickBook: function () {
                this.$emit("clickBook", this.book);
            }
        },
        filters: {
            descriptionPreview: function (description) {
                return description.slice(0, 100);
            }
        }
    }
</script>