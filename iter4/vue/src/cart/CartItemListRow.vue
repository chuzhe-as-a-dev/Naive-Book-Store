<template>
    <tr>
        <th>{{ index }}</th>
        <td>{{ item.bookByBookId.bookTitle }}</td>
        <td>
            <div class="input-group input-group-sm" style="width: 100px;">
                <input type="number" class="form-control" min="1" step="1" v-model="currentQuantity">
                <span class="input-group-btn">
                    <button class="btn btn-default" @click="updateItem">
                        <span class="glyphicon glyphicon-refresh"></span>
                    </button>
                </span>
            </div>
        </td>
        <td>${{ item.bookByBookId.price / 100 }}</td>
        <td>
            <button type="button" class="btn btn-danger btn-xs" @click="removeItem">
                Remove
            </button>
        </td>
    </tr>
</template>

<script>
    export default {
        name: 'cart-item-list-row',
        props: ['item', 'index'],
        data: function () {
            return {
                currentQuantity: 1
            }
        },
        created: function () {
            this.currentQuantity = this.item.quantity;
        },
        watch: {
            item: function (item) {
                this.currentQuantity = item.quantity;
            }
        },
        methods: {
            updateItem: function () {
                const self = this;
                $.ajax({
                    url: '/user/cart',
                    type: 'PUT',
                    data: {
                        bookId: this.item.bookByBookId.bookId,
                        quantity: this.currentQuantity
                    },
                    success: function (data) {
                        if (data === true) {
                            self.$emit('itemUpdated');
                        }
                    }
                });
            },
            removeItem: function () {
                const self = this;
                $.ajax({
                    url: '/user/cart/' + this.item.cartItemId,
                    type: 'DELETE',
                    success: function (data) {
                        if (data === true) {
                            self.$emit('itemUpdated');
                        }
                    }
                });
            }
        }
    }
</script>