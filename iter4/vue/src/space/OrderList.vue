<template>
    <div>
        <template v-for="order in orders">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-1">
                            <small><b>#{{ order.orderId }}</b></small>
                        </div>
                        <div class="col-md-2">
                            <small>Total: ${{ order.totalPrice / 100 }}</small>
                        </div>
                        <div class="col-md-2">
                            <small>{{ new Date(order.placeTime).toLocaleDateString() }}</small>
                        </div>
                        <div class="col-md-7">
                            <small>Shipped to {{ order.deliverTo }}</small>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <ul class="media-list">
                        <template v-for="(item, index) in order.orderItemsByOrderId">
                            <hr v-if="index != 0">
                            <order-item-media-list-item :item="item" :commentModalId="commentModalId"
                                                        @bookAdded="$emit('bookAdded')"
                                                        @commentBook="loadCommentIfExist">
                            </order-item-media-list-item>
                        </template>
                    </ul>
                </div>
            </div>
        </template>
        <div class="well" v-if="orders.length === 0">
            You have not placed any order yet.
        </div>
    </div>
</template>

<script>
    import OrderItemMediaListItem from "./OrderItemMediaListItem.vue";
    export default {
        components: {OrderItemMediaListItem},
        name: 'order-list',
        props: ['commentModalId'],
        data: function () {
            return {
                orders: [],
                promptSuccess: false
            }
        },
        created: function () {
            const self = this;
            $.get("/user/order", function (date) {
                self.orders = date;
            });
        },
        methods: {
            loadCommentIfExist: function (bookId) {
                const self = this;
                $.get('/user/comment/' + bookId, function (data) {
                    if (data !== null) {
                        self.$emit('updateComment', data)
                    } else {
                        self.$emit('addComment', bookId)
                    }
                })
            }
        }
    }
</script>
