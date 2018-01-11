import Vue from 'vue';
import Cart from './cart/Cart.vue';

let app = new Vue({
    el: "#app",
    template: '<Cart/>',
    components: { Cart }
});
