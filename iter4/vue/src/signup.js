/**
 * Created by tang on 2017/6/2.
 */
import Vue from 'vue';
import Signup from './signup/Signup.vue';

let app = new Vue({
    el: "#app",
    template: '<Signup/>',
    components: { Signup }
});
