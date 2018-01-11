<template>
    <div class="panel panel-default">
        <!-- header -->
        <div class="panel-heading">
            <h3 class="panel-title">My shopping cart</h3>
        </div>

        <!-- Table -->
        <div class="table-responsive">
            <table class="table table-striped" id="cart-table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Book</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <template v-for="(item, index) in items">
                    <cart-item-list-row :item="item" :index="index"
                                        @itemUpdated="$emit('itemUpdated')"></cart-item-list-row>
                </template>
                </tbody>
            </table>
        </div>

        <!-- footer -->
        <div class="panel-footer">
            Books are the ladder of human progress.
        </div>
    </div>
</template>

<script>
    import CartItemListRow from "./CartItemListRow.vue";
    export default {
        components: {CartItemListRow},
        name: 'cart-item-list',
        props: ['items']
    }
</script>