<template>
    <div class="modal fade" :id="commentModalId" tabindex="-1" role="dialog" aria-labelledby="commentModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="commentModalLabel">Edit comment</h4>
                </div>

                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="commentTitle">Comment title</label>
                            <input type="text" class="form-control" id="commentTitle" placeholder="Comment title"
                                   v-model="title">
                        </div>
                        <div class="form-group">
                            <label for="commentContent">Content</label>
                            <textarea id="commentContent" class="form-control" rows="12" placeholder="Content"
                                      v-model="content"></textarea>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" @click="submitComment">Submit</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'comment-modal',
        props: ['commentModalId', 'comment'],
        data: function () {
            return {
                bookId: 0,
                title: '',
                content: ''
            }
        },
        watch: {
            comment: function (oldComment) {
                this.bookId = oldComment.bookId;
                this.title = oldComment.title;
                this.content = oldComment.content;
            }
        },
        methods: {
            submitComment: function () {
                const self = this;
                $.ajax({
                    url: '/user/comment',
                    type: 'PUT',
                    data: {
                        bookId: this.bookId,
                        title: this.title,
                        content: this.content
                    },
                    success: function (data) {
                        if (data === true) {
                            window.location.reload();
                        }
                    }
                });
            }
        }
    }
</script>