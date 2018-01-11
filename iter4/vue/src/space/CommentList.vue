<template>
    <div>
        <template v-for="comment in comments">
            <comment-panel :commentModalId="commentModalId" :comment="comment"
                           @updateComment="updateComment"></comment-panel>
        </template>
        <div class="well" v-if="comments.length === 0">
            You have not written any comment yet.
        </div>
    </div>
</template>

<script>
    import CommentPanel from "./CommentPanel.vue";

    export default {
        components: {CommentPanel},
        name: 'comment-list',
        props: ['commentModalId'],
        data: function () {
            return {
                comments: []
            }
        },
        created: function () {
            const self = this;
            $.get("/user/comment", function (date) {
                self.comments = date;
            });
        },
        methods: {
            updateComment: function (comment) {
                this.$emit('updateComment', comment)
            }
        }
    }
</script>