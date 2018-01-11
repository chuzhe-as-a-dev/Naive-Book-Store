<template>
    <div id="app">
        <navbar activeTag="none" :loggedIn="loggedIn" ref="navbar"></navbar>
        <sign-up-form></sign-up-form>
        <page-footer></page-footer>
    </div>
</template>

<script>
    import Navbar from "../components/Navbar.vue";
    import PageFooter from "../components/PageFooter.vue";
    import SignUpForm from "./SignupForm.vue";

    export default {
        components: {
            SignUpForm,
            PageFooter,
            Navbar
        },
        data: function () {
            return {
                loggedIn: false
            }
        },
    }
</script>

<style>
    body {
        padding-top: 70px;
        padding-bottom: 20px;
    }
</style>