<template>
    <div class="container">
        <div class="row">
            <!-- Nav tabs -->
            <ul class="nav nav-pills nav-stacked col-md-2 col-md-push-1" role="tablist">
                <li class="active" role="presentation">
                    <a href="#orderList" aria-controls="My orders" role="tab" data-toggle="tab">Orders</a>
                </li>
                <li role="presentation">

                    <a href="#commentList" role="tab" data-toggle="tab">Comments</a>
                </li>
                <li role="presentation">
                    <a href="#account" role="tab" data-toggle="tab">Account</a>
                </li>
                <li role="presentation">
                    <a href="#profile" role="tab" data-toggle="tab">Profile</a>
                </li>
                <li role="presentation">
                    <a href="#address" role="tab" data-toggle="tab">Addresses</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content col-md-8  col-md-push-1">
                <div role="tabpanel" class="tab-pane active" id="orderList">
                    <order-list :commentModalId="commentModalId" @bookAdded="$emit('bookAdded')"
                                @updateComment="updateComment" @addComment="addComment"></order-list>
                </div>
                <div role="tabpanel" class="tab-pane" id="commentList">
                    <comment-list :commentModalId="commentModalId" @updateComment="updateComment"></comment-list>
                </div>
                <div role="tabpanel" class="tab-pane" id="account">
                    <account-form></account-form>
                </div>
                <div role="tabpanel" class="tab-pane" id="profile">
                    <profile-form :profile="profile"></profile-form>
                </div>
                <div role="tabpanel" class="tab-pane" id="address">
                    <address-list :username="profile.username"></address-list>
                </div>
            </div>
        </div>

        <comment-modal :commentModalId="commentModalId" :comment="comment"></comment-modal>
    </div>
</template>

<script>
    import OrderList from "./OrderList.vue";
    import CommentList from "./CommentList.vue";
    import CommentModal from "./CommentModal.vue";
    import AccountForm from "./AccountForm.vue";
    import ProfileForm from "./ProfileForm.vue";
    import AddressList from "./AddressList.vue";

    export default {
        components: {
            AddressList,
            ProfileForm,
            AccountForm,
            CommentModal,
            CommentList,
            OrderList
        },
        name: 'profile-tab',
        props: ['profile'],
        data: function () {
            return {
                commentModalId: 'commentModal',
                comment: {
                    bookId: 0,
                    title: '',
                    content: ''
                }
            }
        },
        methods: {
            updateComment: function (oldComment) {
                this.comment = oldComment;
            },
            addComment: function (bookId) {
                this.comment = {
                    bookId: bookId,
                    title: '',
                    content: ''
                }
            }
        }
    }
</script>