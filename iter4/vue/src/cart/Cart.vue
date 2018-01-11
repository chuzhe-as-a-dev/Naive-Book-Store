<template>
    <div id="app">
        <navbar activeTag="cart" :loggedIn="loggedIn" ref="navbar"></navbar>
        <div class="container">
            <div class="row">
                <div class="col-md-9">
                    <cart-item-list :items="items" @itemUpdated="updateCart"></cart-item-list>
                </div>
                <div class="col-md-3">
                    <cart-summary-panel :totalPrice="totalPrice"></cart-summary-panel>
                </div>
            </div>
        </div>
        <page-footer></page-footer>
    </div>
</template>

<script>
    import Navbar from "../components/Navbar.vue";
    import CartItemList from "./CartItemList.vue";
    import CartSummaryPanel from "./CartSummaryPanel.vue";
    import PageFooter from "../components/PageFooter.vue";

    export default {
        components: {
            PageFooter,
            CartSummaryPanel,
            CartItemList,
            Navbar
        },
        data: function () {
            return {
                loggedIn: false,
                items: []
            }
        },
        computed: {
            totalPrice: function () {
                let totalPrice = 0;
                for (let i = 0; i < this.items.length; ++i) {
                    totalPrice += this.items[i].bookByBookId.price * this.items[i].quantity;
                }
                return totalPrice;
            }
        },
        created: function () {
            const self = this;
            $.get("/logged_in", function (data) {
                self.loggedIn = (data === true);
            });
            $.get("/user/cart", function (date) {
                self.items = date;
            });
        },
        methods: {
            updateCart: function () {
                this.$refs.navbar.updateCartSize();  // directly ask navbar to update cart size. ugly code tho

                const self = this;
                $.get("/user/cart", function (date) {
                    self.items = date;
                });
            }
        }
    }
</script>

<style>
    body {
        padding-top: 70px;
        padding-bottom: 20px;
    }
</style>