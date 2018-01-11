/**
 * Created by tang on 2017/5/27.
 */
import Vue from 'vue';
import Space from './space/Space.vue';

let app = new Vue({
    el: "#app",
    template: '<Space/>',
    components: {
        Space
    }
});
